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
import micv.fx.classes.Experiencia;
import micv.fx.classes.Titulo;
import micv.fx.utils.InsertTableDialog;
import micv.fx.utils.LocalDateTableCell;


public class ExperienciaController implements Initializable {

	// View : FXML
	
    @FXML
    private HBox view;

    @FXML
    private TableView<Experiencia> experienciaTbl;

    @FXML
    private Button addBt;

    @FXML
    private Button removeBt;
    
    @FXML
    private TableColumn<Titulo, LocalDate> desdeCol;

    @FXML
    private TableColumn<Titulo, LocalDate> hastaCol;
        
    // Model
    private ListProperty<Experiencia> listExperiencia = new SimpleListProperty<>(FXCollections.observableArrayList(new ArrayList<>()));
    
	public ExperienciaController() throws IOException {
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/ExperienciaView.fxml"));
		loader.setController(this);
		loader.load();
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		desdeCol.setCellFactory(LocalDateTableCell::new); 
		hastaCol.setCellFactory(LocalDateTableCell::new);
		
		experienciaTbl.itemsProperty().bindBidirectional(listExperiencia);
		
		addBt.setOnAction( action -> onAddAction() );
		removeBt.setOnAction( action -> onRemoveAction() );
		
		removeBt.disableProperty().bind( experienciaTbl.getSelectionModel().selectedItemProperty().isNull() );		
		
	}
	
	private void onRemoveAction() {
		
		Experiencia experiencia = experienciaTbl.getSelectionModel().getSelectedItem();
		
		if( experiencia != null ) {
			
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setHeaderText("Eliminar experiencia");
			alert.setContentText(String.format("¿Está seguro de eliminar %s?", experiencia.getDenominacion()));
			
			if( alert.showAndWait().get() == ButtonType.OK ) {
				listExperiencia.remove(experiencia);
			}
		}
	}

	private void onAddAction() {

		InsertTableDialog<Experiencia> tablaexp = new InsertTableDialog<>("Añadir experiencia", "Empleador", Experiencia.class);
		
		Optional<Experiencia> experiencia = tablaexp.showAndWait();
		
		if( experiencia.isPresent() ) {
			listExperiencia.add(experiencia.get());
		}
	}
	
	public HBox getRootView() {
		return view;
	}

	public final ListProperty<Experiencia> listExperienciaProperty() {
		return this.listExperiencia;
	}
	

	public final ObservableList<Experiencia> getListExperiencia() {
		return this.listExperienciaProperty().get();
	}
	

	public final void setListExperiencia(final ObservableList<Experiencia> listExperiencia) {
		this.listExperienciaProperty().set(listExperiencia);
	}
		

}
