/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.atteo.moonshine.example;

import org.atteo.moonshine.tests.MoonshineConfiguration;
import org.atteo.moonshine.tests.MoonshineTest;
import org.junit.Test;

@MoonshineConfiguration(fromString = ""
		+ "<config>"
		+ "    <provider id='first'>"
		+ "        <message>First Message</message>"
		+ "    </provider>"
		+ "    <provider id='second'>"
		+ "        <message>Second Message</message>"
		+ "    </provider>"
		+ "    <printer id='first'>"
		+ "        <provider>first</provider>"
		+ "    </printer>"
		+ "    <printer id='second'>"
		+ "        <provider>second</provider>"
		+ "    </printer>"
		+ "</config>",
		skipDefault = true)
public class RobotLegsProblemTest extends MoonshineTest {
	@Test
	public void shouldExecuteBothPrinters() {
	}
}
