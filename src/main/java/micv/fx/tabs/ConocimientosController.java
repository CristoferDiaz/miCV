package micv.fx.tabs;

import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import micv.fx.classes.Conocimiento;
import micv.fx.classes.Idioma;
import micv.fx.utils.ConocimientoDialog;

public class ConocimientosController implements Initializable {

	public enum eTipoConocimiento {// enumerador de los tipos

		CON_ESTANDAR, CON_IDIOMA
	};

	// View : FXML
	@FXML
	private HBox view;

	@FXML
	private TableView<Conocimiento> conocimientoTbl;

	@FXML
	private Button addKBt;

	@FXML
	private Button addLBt;

	@FXML
	private Button delBt;

	@FXML
	private TableColumn<Conocimiento, Conocimiento.Nivel> nivelCol;

	// Model
	private ListProperty<Conocimiento> conocimientos = new SimpleListProperty<Conocimiento>(
			FXCollections.observableArrayList(new ArrayList<Conocimiento>()));

	public ConocimientosController() throws IOException {

		FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/ConocimientosView.fxml"));
		loader.setController(this);
		loader.load();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		// Ajustamos la columna de la tabla nivel
		nivelCol.setCellFactory(ComboBoxTableCell.forTableColumn(Conocimiento.getNiveles()));
		conocimientoTbl.itemsProperty().bindBidirectional(conocimientos);

		addKBt.setOnAction(action -> onAddAction(eTipoConocimiento.CON_ESTANDAR));
		addLBt.setOnAction(action -> onAddAction(eTipoConocimiento.CON_IDIOMA));

		delBt.setOnAction(action -> onRemoveAction());

		delBt.disableProperty().bind(conocimientoTbl.getSelectionModel().selectedItemProperty().isNull());
	}

	private void onRemoveAction() {

		Conocimiento conocimiento = conocimientoTbl.getSelectionModel().getSelectedItem();

		if (conocimiento != null) {

			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setHeaderText("Eliminar conocimiento");
			alert.setContentText(String.format("¿Está seguro de eliminar %s?", conocimiento.getDenominacion()));

			if (alert.showAndWait().get() == ButtonType.OK) {
				conocimientos.remove(conocimiento);
			}
		}
	}

	private void onAddAction(eTipoConocimiento tipoConocimiento) {

		ConocimientoDialog dialog = new ConocimientoDialog(tipoConocimiento);

		if (tipoConocimiento == eTipoConocimiento.CON_ESTANDAR) {
			Optional<Conocimiento> conocimiento = dialog.showAndWait();

			if (conocimiento.isPresent()) {
				conocimientos.add(conocimiento.get());
			}

		} else {
			Optional<Conocimiento> idioma = dialog.showAndWait();

			if (idioma.isPresent()) {
				Idioma nuevoIdioma = (Idioma) idioma.get();
				conocimientos.add(nuevoIdioma); // Para los idiomas es igual
			}
		}
	}

	public HBox getRootView() {
		return view;
	}

	public final ListProperty<Conocimiento> conocimientosProperty() {
		return this.conocimientos;
	}

	public final ObservableList<Conocimiento> getConocimientos() {
		return this.conocimientosProperty().get();
	}

	public final void setConocimientos(final ObservableList<Conocimiento> conocimientos) {
		this.conocimientosProperty().set(conocimientos);
	}

}
