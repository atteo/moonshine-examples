/*
 * Copyright 2013 Atteo.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package org.atteo.moonshine.example;

import javax.inject.Inject;

import static org.assertj.core.api.Assertions.assertThat;
import org.atteo.moonshine.Moonshine;
import org.atteo.moonshine.tests.MoonshineConfiguration;
import org.atteo.moonshine.tests.MoonshineConfigurator;
import org.atteo.moonshine.tests.MoonshineTest;
import org.junit.Test;

@MoonshineConfiguration(configurator = MessageProviderServiceTest.Configurator.class)
public class MessageProviderServiceTest extends MoonshineTest {
	public static class Configurator implements MoonshineConfigurator {
		public void configureMoonshine(Moonshine.Builder builder) {
			builder.addPropertyResolver(new DatePropertyResolver());
		}
	}

	@Inject
	private MessageProvider provider;

	@Test
	public void shouldReturnSpecifiedMessage() {
		assertThat(provider.getMessage()).startsWith("Today is");
	}

}
