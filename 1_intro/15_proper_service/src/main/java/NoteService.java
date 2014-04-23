
import javax.xml.bind.annotation.XmlRootElement;

import org.atteo.moonshine.TopLevelService;
import org.atteo.moonshine.jaxrs.Jaxrs;
import org.atteo.moonshine.jpa.JpaService;
import org.atteo.moonshine.jta.TransactionalService;
import org.atteo.moonshine.services.ImportService;

import com.google.inject.Module;
import com.google.inject.PrivateModule;

/**
 * Service which allows note storage and retrieval.
 */
@XmlRootElement(name = "note")
public class NoteService extends TopLevelService {
	@ImportService
	private Jaxrs jaxrs;

	@ImportService
	private JpaService jpa;

	@ImportService
	private TransactionalService transactional;

	@Override
	public Module configure() {
		return new PrivateModule() {
			@Override
			protected void configure() {
				bind(NoteResource.class);
				jaxrs.registerResource(NoteResource.class, getProvider(NoteResource.class));
			}
		};
	}
}
