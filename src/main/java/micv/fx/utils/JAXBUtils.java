package micv.fx.utils;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class JAXBUtils {

	public static void save(Object rootObject, File target) throws Exception {
		JAXBContext context = JAXBContext.newInstance(rootObject.getClass());
		Marshaller marshaller = context.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		marshaller.marshal(rootObject, target);
	}

	public static <T> T load(Class<T> rootClass, File source) throws Exception {
		JAXBContext context = JAXBContext.newInstance(rootClass);
		Unmarshaller unmarshaller = context.createUnmarshaller();
		return rootClass.cast(unmarshaller.unmarshal(source));
	}

}
