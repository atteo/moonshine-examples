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
import javax.xml.bind.annotation.XmlRootElement;

import org.atteo.moonshine.TopLevelService;
import org.atteo.moonshine.services.ImportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Service which prints message.
 */
@XmlRootElement(name = "hello")
public class HelloWorldService extends TopLevelService {
	private static final Logger logger = LoggerFactory.getLogger(HelloWorldService.class);

	@ImportService
	private MessageProviderService provider;

	@Inject
	private String message;

	@Override
	public void start() {
		logger.warn("Message is: " + message);
	}
}

