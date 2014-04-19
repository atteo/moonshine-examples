package org.atteo.moonshine.example;

import org.atteo.moonshine.tests.MoonshineConfiguration;
import org.atteo.moonshine.tests.MoonshineTest;
import org.junit.Test;

@MoonshineConfiguration(fromString = ""
		+ "<config>"
		+ "    <provider id='firstProvider'>"
		+ "        <message>First Message</message>"
		+ "    </provider>"
		+ "    <provider id='secondProvider'>"
		+ "        <message>Second Message</message>"
		+ "    </provider>"
		+ "    <hello id='first'>"
		+ "        <provider>firstProvider</provider>"
		+ "    </hello>"
		+ "    <hello id='second'>"
		+ "        <provider>secondProvider</provider>"
		+ "    </hello>"
		+ "</config>")
public class HelloWorldServiceTest extends MoonshineTest {
	@Test
	public void shouldExecuteBothPrinters() {
	}
}
