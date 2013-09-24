/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.atteo.moonshine.example;

import javax.inject.Inject;

import static org.assertj.core.api.Assertions.assertThat;
import org.atteo.moonshine.tests.MoonshineConfiguration;
import org.atteo.moonshine.tests.MoonshineTest;
import org.junit.Test;

@MoonshineConfiguration(fromString = ""
		+ "<config>"
		+ "    <provider>"
		+ "        <message>Test message</message>"
		+ "    </provider>"
		+ "</config>")
public class MessageProviderServiceTest extends MoonshineTest {
	@Inject
	private MessageProvider provider;

	@Test
	public void shouldReturnTestMessage() {
		assertThat(provider.getMessage()).isEqualTo("Test message");
	}

}
