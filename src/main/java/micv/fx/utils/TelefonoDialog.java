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
import javafx.stage.Stage;
import micv.fx.classes.Telefono;
import micv.fx.classes.Telefono.TipoTelefono;

public class TelefonoDialog extends Dialog<Telefono> {

	private class DialogValid extends BooleanBinding {

		private StringExpression numero;
		private ObjectExpression<TipoTelefono> tipo;

		public DialogValid(StringExpression numero, ObjectExpression<TipoTelefono> tipo) {

			this.numero = numero;
			this.tipo = tipo;

			bind(this.numero, this.tipo);
		}

		@Override
		protected boolean computeValue() {

			if (numero.get() == null || tipo.get() == null) {
				return false;
			}

			if (numero.get().length() <= 0) {
				return false;
			}

			return true;
		}

	}

	public TelefonoDialog() {

		setTitle("Nuevo teléfono");
		setHeaderText("Introduzca el nuevo número de telefono");

		Stage stage = (Stage) getDialogPane().getScene().getWindow();
		stage.getIcons().add(new Image(getClass().getResource("/images/cv64x64.png").toString()));

		GridPane root = new GridPane();
		root.setHgap(5);
		root.setVgap(5);

		Label numero = new Label("Número:");
		TextField numTxt = new TextField();
		numTxt.setPromptText("Número de teléfono");
		root.addRow(0, numero, numTxt);

		Label tipo = new Label("Tipo: ");
		ComboBox<TipoTelefono> tipoTlf = new ComboBox<Telefono.TipoTelefono>();
		tipoTlf.setPromptText("Seleccione un tipo");
		tipoTlf.getItems().addAll(Telefono.getTiposTelefono());
		root.addRow(1, tipo, tipoTlf);

		getDialogPane().setContent(root);

		ButtonType addBt = new ButtonType("Añadir", ButtonData.OK_DONE);
		ButtonType cancelBt = new ButtonType("Cancelar", ButtonData.CANCEL_CLOSE);

		getDialogPane().getButtonTypes().addAll(addBt, cancelBt);

		// Nos aseguramos de que los datos sean válidos
		Button okButton = (Button) getDialogPane().lookupButton(addBt);
		okButton.disableProperty().bind(new DialogValid(numTxt.textProperty(), tipoTlf.valueProperty()).not());

		setResultConverter(bt -> {

			if (bt.getButtonData() == ButtonData.OK_DONE) {
				return new Telefono(numTxt.getText(), tipoTlf.getSelectionModel().getSelectedItem());
			}

			return null;
		});

	}
}
