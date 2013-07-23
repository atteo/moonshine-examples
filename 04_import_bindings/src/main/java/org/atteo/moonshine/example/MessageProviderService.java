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

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.atteo.evo.config.XmlDefaultValue;
import org.atteo.moonshine.services.TopLevelService;

import com.google.inject.AbstractModule;
import com.google.inject.Module;

/**
 * Service which produces message.
 */
@XmlRootElement(name = "provider")
public class MessageProviderService extends TopLevelService {
	@XmlElement
	@XmlDefaultValue("Hello World!")
	private String message;

	@Override
	public Module configure() {
		return new AbstractModule() {
			@Override
			protected void configure() {
				bind(MessageProvider.class).toInstance(new MessageProvider() {
					public String getMessage() {
						return message;
					}
				});
			}
		};
	}
}
