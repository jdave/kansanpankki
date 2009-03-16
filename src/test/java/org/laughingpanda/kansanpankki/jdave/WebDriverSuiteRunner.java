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
package org.laughingpanda.kansanpankki.jdave;

import jdave.webdriver.WebDriverGroupRunner;
import jdave.webdriver.WebDriverHolder;

import org.laughingpanda.kansanpankki.jetty.WebDriverJettyStarter;

/**
 * @author Marko Sibakov / Reaktor Innovations Oy
 */
public class WebDriverSuiteRunner extends WebDriverGroupRunner {
    private WebDriverJettyStarter server = new WebDriverJettyStarter();

    public WebDriverSuiteRunner(Class<?> suite) {
        super(suite);
    }

    @Override
    protected void onBeforeRun() {
        server.start();
        super.onBeforeRun();
    }

    @Override
    protected void onAfterRun() {
        if (WebDriverHolder.get() != null) {
            super.onAfterRun();
        } else {
            System.out.println("WebDriver was null in WebDriverSuiteRunner");
        }
        server.stop();
    }
}
