package micv.fx.tabs;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import micv.fx.classes.Titulo;
import micv.fx.utils.InsertTableDialog;
import micv.fx.utils.LocalDateTableCell;

public class FormacionController implements Initializable {

	// View : FXML
	@FXML
	private HBox view;

	@FXML
	private TableView<Titulo> formacionTbl;

	@FXML
	private Button addBt;

	@FXML
	private Button removeBt;

	@FXML
	private TableColumn<Titulo, LocalDate> desdeCol;

	@FXML
	private TableColumn<Titulo, LocalDate> hastaCol;

	// Model
	private ListProperty<Titulo> titulos = new SimpleListProperty<>(
			FXCollections.observableArrayList(new ArrayList<>()));

	public FormacionController() throws IOException {

		FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/FormacionView.FXML"));
		loader.setController(this);
		loader.load();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		// Usamos una librería para poder implementar el LocalDateTableCell,
		desdeCol.setCellFactory(LocalDateTableCell::new); // Equivalente a desdeCol.setCellFactory( t -> {return new
															// LocalDateTableCell<>(t); });
		hastaCol.setCellFactory(LocalDateTableCell::new);

		formacionTbl.itemsProperty().bindBidirectional(titulos);

		addBt.setOnAction(action -> onAddAction());
		removeBt.setOnAction(action -> onRemoveAction());

		removeBt.disableProperty().bind(formacionTbl.getSelectionModel().selectedItemProperty().isNull());
	}

	private void onRemoveAction() {

		Titulo titulo = formacionTbl.getSelectionModel().getSelectedItem();

		if (titulo != null) {

			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setHeaderText("Eliminar título");
			alert.setContentText(String.format("¿Está seguro de eliminar el título %s?", titulo.getDenominacion()));

			if (alert.showAndWait().get() == ButtonType.OK) {
				titulos.remove(titulo);
			}
		}
	}

	private void onAddAction() {

		InsertTableDialog<Titulo> dialog = new InsertTableDialog<>("Añadir título", "Organizador", Titulo.class);

		Optional<Titulo> titulo = dialog.showAndWait();

		if (titulo.isPresent()) {
			titulos.add(titulo.get());
		}
	}

	public HBox getRootView() {
		return view;
	}

	public final ListProperty<Titulo> titulosProperty() {
		return this.titulos;
	}

	public final ObservableList<Titulo> getTitulos() {
		return this.titulosProperty().get();
	}

	public final void setTitulos(final ObservableList<Titulo> titulos) {
		this.titulosProperty().set(titulos);
	}

}
