package micv.fx.classes;

import java.time.LocalDate;
import java.util.ArrayList;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.bind.annotation.XmlAttribute;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import micv.fx.utils.DateAdapter;

@XmlType
public class Personal {

	private StringProperty identificacion = new SimpleStringProperty("");
	private StringProperty nombre = new SimpleStringProperty("");
	private StringProperty apellidos = new SimpleStringProperty("");
	private ObjectProperty<LocalDate> fechaNacimiento = new SimpleObjectProperty<LocalDate>(LocalDate.now());
	private StringProperty direccion = new SimpleStringProperty("");
	private StringProperty codigoPostal = new SimpleStringProperty("");
	private StringProperty localidad = new SimpleStringProperty("");
	private StringProperty pais = new SimpleStringProperty("");
	private ListProperty<Nacionalidad> nacionalidades = new SimpleListProperty<Nacionalidad>(
			FXCollections.observableList(new ArrayList<Nacionalidad>()));

	public Personal() {
	}

	public Personal(String identificacion, String nombre, String apellidos, LocalDate fechaNacimiento, String direccion,
			String codigoPostal, String localidad, String pais, ArrayList<Nacionalidad> nacionalidades) {

		this.identificacion.set(identificacion);
		this.nombre.set(nombre);
		this.apellidos.set(apellidos);
		this.fechaNacimiento.set(fechaNacimiento);
		this.direccion.set(direccion);
		this.codigoPostal.set(codigoPostal);
		this.localidad.set(localidad);
		this.pais.set(pais);
		this.nacionalidades.addAll(nacionalidades);
	}

	public final StringProperty identificacionProperty() {
		return this.identificacion;
	}

	@XmlAttribute
	public final String getIdentificacion() {
		return this.identificacionProperty().get();
	}

	public final void setIdentificacion(final String identificacion) {
		this.identificacionProperty().set(identificacion);
	}

	public final StringProperty nombreProperty() {
		return this.nombre;
	}

	@XmlElement
	public final String getNombre() {
		return this.nombreProperty().get();
	}

	public final void setNombre(final String nombre) {
		this.nombreProperty().set(nombre);
	}

	public final StringProperty apellidosProperty() {
		return this.apellidos;
	}

	@XmlElement
	public final String getApellidos() {
		return this.apellidosProperty().get();
	}

	public final void setApellidos(final String apellidos) {
		this.apellidosProperty().set(apellidos);
	}

	public final ObjectProperty<LocalDate> fechaNacimientoProperty() {
		return this.fechaNacimiento;
	}

	@XmlElement
	@XmlJavaTypeAdapter(value = DateAdapter.class)
	public final LocalDate getFechaNacimiento() {
		return this.fechaNacimientoProperty().get();
	}

	public final void setFechaNacimiento(final LocalDate fechaNacimiento) {
		this.fechaNacimientoProperty().set(fechaNacimiento);
	}

	public final StringProperty direccionProperty() {
		return this.direccion;
	}

	@XmlElement
	public final String getDireccion() {
		return this.direccionProperty().get();
	}

	public final void setDireccion(final String direccion) {
		this.direccionProperty().set(direccion);
	}

	public final StringProperty codigoPostalProperty() {
		return this.codigoPostal;
	}

	@XmlElement
	public final String getCodigoPostal() {
		return this.codigoPostalProperty().get();
	}

	public final void setCodigoPostal(final String codigoPostal) {
		this.codigoPostalProperty().set(codigoPostal);
	}

	public final StringProperty localidadProperty() {
		return this.localidad;
	}

	@XmlElement
	public final String getLocalidad() {
		return this.localidadProperty().get();
	}

	public final void setLocalidad(final String localidad) {
		this.localidadProperty().set(localidad);
	}

	public final StringProperty paisProperty() {
		return this.pais;
	}

	@XmlElement
	public final String getPais() {
		return this.paisProperty().get();
	}

	public final void setPais(final String pais) {
		this.paisProperty().set(pais);
	}

	public final ListProperty<Nacionalidad> nacionaliadesProperty() {
		return this.nacionalidades;
	}

	@XmlElement(name = "nacionalidades")
	public final ObservableList<Nacionalidad> getNacionaliades() {
		return this.nacionaliadesProperty().get();
	}

	public final void setNacionaliades(final ObservableList<Nacionalidad> nacionaliades) {
		this.nacionaliadesProperty().set(nacionaliades);
	}

}
