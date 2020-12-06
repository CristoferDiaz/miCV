package micv.fx.classes;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

@XmlType
public class Nacionalidad {

	private StringProperty denominacion = new SimpleStringProperty();

	public Nacionalidad() {
	}

	public Nacionalidad(String denominacion) {
		this.denominacion.set(denominacion);
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

	/**
	 * Para que se nos vea bien las nacionalidades en las listas mostramos su
	 * denominación
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return getDenominacion();
	}

}
