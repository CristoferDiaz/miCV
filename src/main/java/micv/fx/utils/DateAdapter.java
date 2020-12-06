package micv.fx.utils;

import java.text.ParseException;
import java.time.LocalDate;


import javax.xml.bind.annotation.adapters.XmlAdapter;

public class DateAdapter extends XmlAdapter<String, LocalDate> {
	 
	 
	    @Override
	    public String marshal(LocalDate v) {
	        return v.toString();
	    }
	 
	    @Override
	    public LocalDate unmarshal(String v) throws ParseException {
	        return LocalDate.parse(v);
	    }
}
