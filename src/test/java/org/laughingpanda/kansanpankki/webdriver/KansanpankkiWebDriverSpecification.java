/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.laughingpanda.kansanpankki.webdriver;

import jdave.Specification;
import jdave.webdriver.Retry;
import jdave.webdriver.WebDriverHolder;
import jdave.webdriver.Retry.WaitForCondition;

/**
 * @author Marko Sibakov / Reaktor Innovations Oy
 */
public class KansanpankkiWebDriverSpecification<T> extends Specification<T> {
    public void openBaseUrl() {
		WebDriverHolder.get().navigate().to(
				"http://localhost:8082/kansanpankki/");
        Retry.maximumTimesOf(10).with(new WaitForCondition() {
            public boolean isSatisfied() {
                return textContainsInPage("</html>");
            }
        });
	}
    
    public boolean textContainsInPage(String text) {
        return WebDriverHolder.get().getPageSource().contains(text);
    }
}
