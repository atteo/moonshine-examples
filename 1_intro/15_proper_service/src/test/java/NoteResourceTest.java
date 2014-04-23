
import javax.inject.Inject;
import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.assertThat;
import org.atteo.moonshine.jta.Transactional;
import org.atteo.moonshine.tests.MoonshineConfiguration;
import org.atteo.moonshine.tests.MoonshineConfiguration.Config;
import org.atteo.moonshine.tests.MoonshineTest;
import org.atteo.moonshine.webserver.WebServerAddress;
import static org.hamcrest.CoreMatchers.equalTo;
import org.junit.Test;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;


@MoonshineConfiguration(fromString = ""
		+ "<config>"
		+ "    <servlet-container/>"
		+ "    <h2/>"
		+ "    <atomikos/>"
		+ "    <transactional/>"
		+ "    <hibernate>"
		+ "        <initSchema>update</initSchema>"
		+ "    </hibernate>"
		+ "    <jersey/>"
		+ "    <webjars/>"
		+ "    <note/>"
		+ "</config>",
		forEach = {
			@Config(id = "jetty", fromString = ""
					+ "<config>"
					+ "    <jetty/>"
					+ "</config>"),
			@Config(id = "tomcat", fromString = ""
					+ "<config>"
					+ "    <tomcat/>"
					+ "</config>")})
public class NoteResourceTest extends MoonshineTest {
	@Inject
	private WebServerAddress address;

	@Test
	public void shouldStoreNote() throws Exception {
		// given
		Note note = new Note();
		note.setContent("Hello World!");

		RestAssured.given().port(address.getPort())
				.when().contentType(ContentType.JSON).body(note).post("/note")
				.then().statusCode(204);

		// when
		RestAssured.given().port(address.getPort())
				.when().get("/note")
				//then
				.then().content(equalTo("{\"content\":\"Hello World!\"}"));
	}



	@Inject
	private EntityManager manager;

	@Transactional
	Note getNote() {
		return manager.createQuery("select n from Note n", Note.class).getSingleResult();
	}

	@Test
	public void shouldStoreNote2() {
		// given
		Note note = new Note();
		note.setContent("Another Hello World!");

		RestAssured.given().port(address.getPort())
				.when().contentType(ContentType.JSON).body(note).post("/note")
				.then().statusCode(204);

		// when
		note = getNote();

		// then
		assertThat(note.getContent()).contains("Another Hello World!");
	}
}
