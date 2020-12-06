package micv.fx.classes;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

@XmlRootElement
public class CV {

	private ObjectProperty<Personal> personal = new SimpleObjectProperty<Personal>();

	private ListProperty<Titulo> formacion = new SimpleListProperty<>(
			FXCollections.observableArrayList(new ArrayList<>()));
	private ListProperty<Experiencia> experiencias = new SimpleListProperty<>(
			FXCollections.observableArrayList(new ArrayList<>()));
	private ListProperty<Conocimiento> habilidades = new SimpleListProperty<>(
			FXCollections.observableArrayList(new ArrayList<>()));

	private ObjectProperty<Contacto> contacto = new SimpleObjectProperty<Contacto>();

	public CV() {

	}

	public CV(Personal personal, ArrayList<Titulo> titutlos, ArrayList<Experiencia> experiencias,
			ArrayList<Idioma> idiomas, Contacto contacto) {
		this.personal.set(personal);
		this.contacto.set(contacto);
		this.formacion.addAll(formacion);
		this.experiencias.addAll(experiencias);
		this.habilidades.addAll(idiomas);
	}

	public final ObjectProperty<Personal> personalProperty() {
		return this.personal;
	}

	@XmlElement
	public final Personal getPersonal() {
		return this.personalProperty().get();
	}

	public final void setPersonal(final Personal personal) {
		this.personalProperty().set(personal);
	}

	public final ListProperty<Titulo> formacionProperty() {
		return this.formacion;
	}

	@XmlElement
	public final ObservableList<Titulo> getFormacion() {
		return this.formacionProperty().get();
	}

	public final void setFormacion(final ObservableList<Titulo> formacion) {
		this.formacionProperty().set(formacion);
	}

	public final ListProperty<Experiencia> experienciasProperty() {
		return this.experiencias;
	}

	@XmlElement
	public final ObservableList<Experiencia> getExperiencias() {
		return this.experienciasProperty().get();
	}

	public final void setExperiencias(final ObservableList<Experiencia> experiencia) {
		this.experienciasProperty().set(experiencia);
	}

	public final ListProperty<Conocimiento> habilidadesProperty() {
		return this.habilidades;
	}

	@XmlElement
	public final ObservableList<Conocimiento> getHabilidades() {
		return this.habilidadesProperty().get();
	}

	public final void setHabilidades(final ObservableList<Conocimiento> habilidades) {
		this.habilidadesProperty().set(habilidades);
	}

	public final ObjectProperty<Contacto> contactoProperty() {
		return this.contacto;
	}

	@XmlElement
	public final Contacto getContacto() {
		return this.contactoProperty().get();
	}

	public final void setContacto(final Contacto contacto) {
		this.contactoProperty().set(contacto);
	}

}
