package micv.fx.tabs;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import micv.fx.classes.Contacto;
import micv.fx.classes.Email;
import micv.fx.classes.Telefono;
import micv.fx.classes.Web;
import micv.fx.utils.TelefonoDialog;
import micv.fx.classes.Telefono.TipoTelefono;

public class ContactoController implements Initializable {

	// Propiedades de diálogo de las tablas
	// se supone que es mejor ponerlo asi ?¿
	private final String tlfButtonID = "tlf_removeBt";
	private final String tlfTableRemoveHeader = "Eliminar teléfono";
	private final String tlfTableRemoveContent = "¿Está seguro de eliminar este teléfono?";

	private final String emailButtonID = "email_removeBt";
	private final String emailTableRemoveHeader = "Eliminar e-mail";
	private final String emailTableRemoveContent = "¿Está seguro de eliminar este e-mail?";

	private final String webButtonID = "web_removeBt";
	private final String webTableRemoveHeader = "Eliminar web";
	private final String webTableRemoveContent = "¿Está seguro de eliminar esta URL?";

	// View : FXML

	@FXML
	private VBox view;

	@FXML
	private TableView<Telefono> tlfTable;

	@FXML
	private Button tlf_addBt;

	@FXML
	private Button tlf_removeBt;

	@FXML
	private TableView<Email> emailTable;

	@FXML
	private Button email_addBt;

	@FXML
	private Button email_removeBt;

	@FXML
	private TableView<Web> webTable;

	@FXML
	private Button web_addBt;

	@FXML
	private Button web_removeBt;

	@FXML
	private TableColumn<Telefono, TipoTelefono> tipoColumn;

	// Model
	private ObjectProperty<Contacto> contacto = new SimpleObjectProperty<Contacto>();

	public ContactoController() throws IOException {

		FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/ContactoView.fxml"));
		loader.setController(this);
		loader.load();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		// Para la lista de tipos de teléfonos, tenemos que hacerlo manualmente
		tipoColumn.setCellFactory(ComboBoxTableCell.forTableColumn(Telefono.getTiposTelefono()));

		contacto.addListener((o, ol, nv) -> updateBinds(ol, nv));
		contacto.set(new Contacto());

		tlf_addBt.setOnAction(evt -> onAddTelefono());
		email_addBt.setOnAction(evt -> onAddEmail());
		web_addBt.setOnAction(evt -> onAddWeb());

		tlf_removeBt.setOnAction(evt -> onRemoveAction(evt));
		email_removeBt.setOnAction(evt -> onRemoveAction(evt));
		web_removeBt.setOnAction(evt -> onRemoveAction(evt));

		tlf_removeBt.disableProperty().bind(tlfTable.getSelectionModel().selectedItemProperty().isNull());
		email_removeBt.disableProperty().bind(emailTable.getSelectionModel().selectedItemProperty().isNull());
		web_removeBt.disableProperty().bind(webTable.getSelectionModel().selectedItemProperty().isNull());
	}

	private void onRemoveAction(ActionEvent evt) {

		// Por defecto, los ID son el nombre del objeto
		String id = ((Node) evt.getSource()).getId();

		switch (id) {

		case tlfButtonID:

			Telefono tlfItem = tlfTable.getSelectionModel().getSelectedItem();
			if (tlfItem != null && confirmRemove(tlfTableRemoveHeader, tlfTableRemoveContent)) {
				getContacto().getTelefonos().remove(tlfItem);
			}

			break;

		case emailButtonID:

			Email emailItem = emailTable.getSelectionModel().getSelectedItem();
			if (emailItem != null && confirmRemove(emailTableRemoveHeader, emailTableRemoveContent)) {
				getContacto().getEmails().remove(emailItem);
			}

			break;

		case webButtonID:

			Web webItem = webTable.getSelectionModel().getSelectedItem();
			if (webItem != null && confirmRemove(webTableRemoveHeader, webTableRemoveContent)) {
				getContacto().getWebs().remove(webItem);
			}

			break;

		default:
			break;
		}
	}

	private boolean confirmRemove(String headerText, String contentText) {

		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setHeaderText(headerText);
		alert.setContentText(contentText);

		return (alert.showAndWait().get() == ButtonType.OK);
	}

	private void onAddEmail() {

		TextInputDialog dialog = new TextInputDialog();
		dialog.setTitle("Nuevo e-mail");
		dialog.setHeaderText("Crear una nueva dirección de correo.");
		dialog.setContentText("E-mail:");

		Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
		stage.getIcons().add(new Image(getClass().getResource("/images/cv64x64.png").toString()));

		Optional<String> emailTxt = dialog.showAndWait();

		if (emailTxt.isPresent() && emailTxt.get() != null && !emailTxt.get().isBlank()) {
			getContacto().getEmails().add(new Email(emailTxt.get()));
		}
	}

	private void onAddWeb() {

		TextInputDialog dialog = new TextInputDialog("http://");
		dialog.setTitle("Nueva web");
		dialog.setHeaderText("Crear una nueva dirección web.");
		dialog.setContentText("URL:");

		Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
		stage.getIcons().add(new Image(getClass().getResource("/images/cv64x64.png").toString()));

		Optional<String> webTxt = dialog.showAndWait();

		if (webTxt.isPresent() && webTxt.get() != null && !webTxt.get().isBlank()) {
			getContacto().getWebs().add(new Web(webTxt.get()));
		}
	}

	private void onAddTelefono() {

		TelefonoDialog dialog = new TelefonoDialog();

		Optional<Telefono> tlf = dialog.showAndWait();

		if (tlf.isPresent() && tlf.get() != null) {

			// Añadimos el nuevo teléfono
			getContacto().getTelefonos().add(tlf.get());
		}
	}

	private void updateBinds(Contacto oldContacto, Contacto nuevoContacto) {

		if (oldContacto != null) {
			tlfTable.itemsProperty().unbindBidirectional(oldContacto.telefonosProperty());
			emailTable.itemsProperty().unbindBidirectional(oldContacto.emailsProperty());
			webTable.itemsProperty().unbindBidirectional(oldContacto.websProperty());
		}

		Bindings.bindBidirectional(tlfTable.itemsProperty(), nuevoContacto.telefonosProperty());
		Bindings.bindBidirectional(emailTable.itemsProperty(), nuevoContacto.emailsProperty());
		Bindings.bindBidirectional(webTable.itemsProperty(), nuevoContacto.websProperty());
	}

	public VBox getRootView() {
		return view;
	}

	public final ObjectProperty<Contacto> contactoProperty() {
		return this.contacto;
	}

	public final Contacto getContacto() {
		return this.contactoProperty().get();
	}

	public final void setContacto(final Contacto contacto) {
		this.contactoProperty().set(contacto);
	}

}
