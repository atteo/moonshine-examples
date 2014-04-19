package org.atteo.moonshine.example;

import javax.inject.Inject;

import static org.assertj.core.api.Assertions.assertThat;
import org.atteo.moonshine.tests.MoonshineConfiguration;
import org.atteo.moonshine.tests.MoonshineTest;
import org.junit.Test;

@MoonshineConfiguration(autoConfiguration = true)
public class MessageProviderServiceTest extends MoonshineTest {
	@Inject
	private String message;

	@Test
	public void shouldReturnTestMessage() {
		assertThat(message).isEqualTo("Hello World!");
	}
}
