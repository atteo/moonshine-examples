
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.atteo.moonshine.jaxrs.ResourceModel;

@XmlRootElement(name = "note")
@XmlAccessorType(XmlAccessType.FIELD)
@ResourceModel
public class Note {
	@XmlElement
	private String content;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
