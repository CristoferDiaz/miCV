package micv.fx.classes;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

@XmlType
public class Email {

	private StringProperty email = new SimpleStringProperty();

	public Email() {
	}

	public Email(String email) {
		this.email.set(email);
	}

	public final StringProperty emailProperty() {
		return this.email;
	}

	@XmlAttribute(name = "direccion")
	public final String getEmail() {
		return this.emailProperty().get();
	}

	public final void setEmail(final String email) {
		this.emailProperty().set(email);
	}

}
