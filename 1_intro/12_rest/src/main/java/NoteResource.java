import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/note")
@Consumes("application/json")
@Produces("application/json")
public class NoteResource {
	@GET
	public String get() {
		return "Hello World";
	}
}
