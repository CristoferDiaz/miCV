package micv.fx.classes;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

@XmlType
public class Telefono {

	public enum TipoTelefono {

		DOMICILIO, MOVIL
	}

	private StringProperty numero = new SimpleStringProperty();
	private ObjectProperty<TipoTelefono> tipo = new SimpleObjectProperty<>();

	public Telefono() {

	}

	public Telefono(String numero, TipoTelefono tipo) {
		this.numero.set(numero);
		this.tipo.set(tipo);
	}

	public static ObservableList<TipoTelefono> getTiposTelefono() {

		ObservableList<TipoTelefono> tiposTelefono = FXCollections.observableArrayList(new ArrayList<>());
		tiposTelefono.add(TipoTelefono.DOMICILIO);
		tiposTelefono.add(TipoTelefono.MOVIL);

		return tiposTelefono;
	}

	public Telefono(String numero) {
		this.numero.set(numero);
	}

	public final StringProperty numeroProperty() {
		return this.numero;
	}

	@XmlAttribute
	public final String getNumero() {
		return this.numeroProperty().get();
	}

	public final void setNumero(final String numero) {
		this.numeroProperty().set(numero);
	}

	public final ObjectProperty<TipoTelefono> tipoProperty() {
		return this.tipo;
	}

	@XmlAttribute
	public final TipoTelefono getTipo() {
		return this.tipoProperty().get();
	}

	public final void setTipo(final TipoTelefono tipo) {
		this.tipoProperty().set(tipo);
	}

}
