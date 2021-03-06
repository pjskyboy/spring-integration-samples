/*
 * Copyright 2002-2010 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.integration.samples.multipart;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;
/**
 * @author Oleg Zhurakousky
 *
 */
public class MultipartClientForHttpOutboundClient {

	private static Logger logger = Logger.getLogger(MultipartClientForHttpOutboundClient.class);
	private static String resourcePath = "spring09_logo.png";

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		ApplicationContext context = new ClassPathXmlApplicationContext("/META-INF/spring/integration/http-outbound-config.xml");
		Resource s2logo = new ClassPathResource(resourcePath);
		Map<String, Object> multipartMap = new HashMap<String, Object>();
		multipartMap.put("company", new String[]{"SpringSource", "VMWare"});
		multipartMap.put("company-logo", s2logo);
		logger.info("Created multipart request: " + multipartMap);
		MultipartRequestGateway requestGateway = context.getBean("requestGateway", MultipartRequestGateway.class);
		HttpStatus reply = requestGateway.postMultipartRequest(multipartMap);
		System.out.println("Replied with HttpStatus code: " + reply);
	}
}
