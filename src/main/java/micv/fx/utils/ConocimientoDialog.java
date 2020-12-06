package micv.fx.utils;

import javafx.beans.binding.BooleanBinding;
import javafx.beans.binding.ObjectExpression;
import javafx.beans.binding.StringExpression;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import micv.fx.classes.Conocimiento;
import micv.fx.classes.Idioma;
import micv.fx.classes.Conocimiento.Nivel;
import micv.fx.tabs.ConocimientosController.eTipoConocimiento;

public class ConocimientoDialog extends Dialog<Conocimiento> {

	private class DialogValid extends BooleanBinding {

		private StringExpression denominacion;
		private ObjectExpression<Nivel> nivel;

		public DialogValid(StringExpression denominacion, ObjectExpression<Nivel> nivel) {

			this.denominacion = denominacion;
			this.nivel = nivel;

			bind(this.denominacion, this.nivel);
		}

		@Override
		protected boolean computeValue() {

			if (denominacion.get() == null || nivel.get() == null) {
				return false;
			}

			if (denominacion.get().length() <= 0) {
				return false;
			}

			return true;
		}

	}

	public ConocimientoDialog(eTipoConocimiento tipo) {

		setTitle("Nuevo conocimiento");

		Stage stage = (Stage) getDialogPane().getScene().getWindow();
		stage.getIcons().add(new Image(getClass().getResource("/images/cv64x64.png").toString()));

		GridPane root = new GridPane();
		root.setHgap(5);
		root.setVgap(5);

		Label denLbl = new Label("Denominación:");
		TextField denTxt = new TextField();
		root.addRow(0, denLbl, denTxt);

		Label nivelLbl = new Label("Tipo: ");
		ComboBox<Nivel> nivel = new ComboBox<Nivel>();
		nivel.setPromptText("Seleccione un nivel");
		nivel.getItems().addAll(Conocimiento.getNiveles());
		Button resetBt = new Button("X"); // El botón reset
		resetBt.setOnAction(evt -> nivel.setValue(null));
		root.addRow(1, nivelLbl, new HBox(5, nivel, resetBt));

		Label idiomaLbl = null;
		TextField idiomaTxt = new TextField(); // Los TextField es obligatorio instanciarlos
		if (tipo == eTipoConocimiento.CON_IDIOMA) {
			idiomaLbl = new Label("Certificación");
			root.addRow(2, idiomaLbl, idiomaTxt);
		}

		getDialogPane().setContent(root);

		ButtonType addBt = new ButtonType("Añadir", ButtonData.OK_DONE);
		ButtonType cancelBt = new ButtonType("Cancelar", ButtonData.CANCEL_CLOSE);

		getDialogPane().getButtonTypes().addAll(addBt, cancelBt);

		// Nos aseguramos de que los datos sean válidos
		Button okButton = (Button) getDialogPane().lookupButton(addBt);
		okButton.disableProperty().bind(new DialogValid(denTxt.textProperty(), nivel.valueProperty()).not());

		setResultConverter(bt -> {

			if (bt.getButtonData() == ButtonData.OK_DONE) {

				if (tipo == eTipoConocimiento.CON_IDIOMA) {
					return new Idioma(nivel.getValue(), denTxt.getText(), idiomaTxt.getText());
				}

				return new Conocimiento(nivel.getValue(), denTxt.getText());
			}

			return null;
		});

	}
}
