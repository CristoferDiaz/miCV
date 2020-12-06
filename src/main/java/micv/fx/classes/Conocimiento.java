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
//@XmlSeeAlso({(Idioma.class)})
public class Conocimiento {

	public enum Nivel {

		BASICO, MEDIO, AVANZADO
	}

	private StringProperty denominacion = new SimpleStringProperty("");
	private ObjectProperty<Nivel> nivel = new SimpleObjectProperty<Nivel>();

	public Conocimiento() {
	}

	public Conocimiento(Nivel nivel, String denominacion) {
		this.denominacion.set(denominacion);
		this.nivel.set(nivel);
	}

	public static ObservableList<Nivel> getNiveles() {

		ObservableList<Nivel> niveles = FXCollections.observableArrayList(new ArrayList<>());
		niveles.addAll(Nivel.BASICO, Nivel.MEDIO, Nivel.AVANZADO);

		return niveles;
	}

	public final StringProperty denominacionProperty() {
		return this.denominacion;
	}

	@XmlAttribute
	public final String getDenominacion() {
		return this.denominacionProperty().get();
	}

	public final void setDenominacion(final String denominacion) {
		this.denominacionProperty().set(denominacion);
	}

	public final ObjectProperty<Nivel> nivelProperty() {
		return this.nivel;
	}

	@XmlAttribute
	public final Nivel getNivel() {
		return this.nivelProperty().get();
	}

	public final void setNivel(final Nivel nivel) {
		this.nivelProperty().set(nivel);
	}

}
