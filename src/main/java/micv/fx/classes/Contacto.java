package micv.fx.classes;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlType;

import com.sun.xml.txw2.annotation.XmlElement;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

@XmlType
public class Contacto {

	private ListProperty<Telefono> telefonos = new SimpleListProperty<>(
			FXCollections.observableArrayList(new ArrayList<>()));
	private ListProperty<Email> emails = new SimpleListProperty<>(FXCollections.observableArrayList(new ArrayList<>()));
	private ListProperty<Web> webs = new SimpleListProperty<>(FXCollections.observableArrayList(new ArrayList<>()));

	public Contacto() {
	}

	public Contacto(ArrayList<Telefono> telefonos, ArrayList<Email> emails, ArrayList<Web> webs) {
		this.telefonos.addAll(telefonos);
		this.emails.addAll(emails);
		this.webs.addAll(webs);
	}

	public final ListProperty<Telefono> telefonosProperty() {
		return this.telefonos;
	}

	@XmlElement
	public final ObservableList<Telefono> getTelefonos() {
		return this.telefonosProperty().get();
	}

	public final void setTelefonos(final ObservableList<Telefono> telefonos) {
		this.telefonosProperty().set(telefonos);
	}

	public final ListProperty<Email> emailsProperty() {
		return this.emails;
	}

	@XmlElement
	public final ObservableList<Email> getEmails() {
		return this.emailsProperty().get();
	}

	public final void setEmails(final ObservableList<Email> emails) {
		this.emailsProperty().set(emails);
	}

	public final ListProperty<Web> websProperty() {
		return this.webs;
	}

	@XmlElement
	public final ObservableList<Web> getWebs() {
		return this.websProperty().get();
	}

	public final void setWebs(final ObservableList<Web> webs) {
		this.websProperty().set(webs);
	}

}
