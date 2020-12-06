package micv.fx.classes;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

@XmlType
public class Web {

	private StringProperty web = new SimpleStringProperty();

	public Web() {

	}

	public Web(String web) {
		this.web.set(web);
	}

	public final StringProperty webProperty() {
		return this.web;
	}

	@XmlAttribute(name = "url")
	public final String getWeb() {
		return this.webProperty().get();
	}

	public final void setWeb(final String web) {
		this.webProperty().set(web);
	}

}
