
import javax.inject.Inject;
import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.assertThat;
import org.atteo.moonshine.jta.Transactional;
import org.atteo.moonshine.tests.MoonshineConfiguration;
import org.atteo.moonshine.tests.MoonshineTest;
import org.atteo.moonshine.webdriver.WebDriverHelper;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

@MoonshineConfiguration(fromString = ""
		+ "<config>"
		+ "    <servlet-container/>"
		+ "    <h2/>"
		+ "    <atomikos/>"
		+ "    <transactional/>"
		+ "    <hibernate>"
		+ "        <initSchema>update</initSchema>"
		+ "    </hibernate>"
		+ "    <jetty/>"
		+ "    <jersey/>"
		+ "    <webjars/>"
		+ "    <note/>"
		+ "    <webdriver/>"
		+ "    <webdriver-helper/>"
		+ "</config>")
public class FrontendTest extends MoonshineTest {
	@Inject
	private RemoteWebDriver driver;

	@Inject
	private WebDriverHelper helper;

	@Inject
	private EntityManager manager;

	@Transactional
	Note getNote() {
		return manager.createQuery("select n from Note n", Note.class).getSingleResult();
	}

	@Test
	public void shouldStoreNote3() throws InterruptedException {
		// given
		helper.go("/index.html");
		WebElement textarea = driver.findElementById("content");
		textarea.clear();
		textarea.sendKeys("A");

		Thread.currentThread().sleep(2000);

		// when
		Note note = getNote();

		// then
		assertThat(note.getContent()).contains("A");
	}

}
