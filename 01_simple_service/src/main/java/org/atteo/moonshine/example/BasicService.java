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

import javax.xml.bind.annotation.XmlRootElement;

import org.atteo.moonshine.services.TopLevelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Basic service with start/stop logic.
 */
@XmlRootElement(name = "basic")
public class BasicService extends TopLevelService {
	private Logger logger = LoggerFactory.getLogger(BasicService.class);

	@Override
	public void start() {
		logger.info("Basic service start() method called");
	}

	@Override
	public void stop() {
		logger.info("Basic service stop() method called");
	}
}
