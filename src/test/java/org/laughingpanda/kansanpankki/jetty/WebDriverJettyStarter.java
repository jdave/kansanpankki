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
package org.laughingpanda.kansanpankki.jetty;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * @author Marko Sibakov / Reaktor Innovations Oy
 */
public class WebDriverJettyStarter extends Jetty {
	private static final int PORT = 8082;

	public static void main(String... args) {
		new WebDriverJettyStarter().start();
	}

	@Override
	public void start() {
		if (!isServerRunning()) {
			try {
				super.start();
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		} else {
			System.out.println("Server already running.");
		}
	}

	@Override
	public int getPort() {
		return PORT;
	}

	private boolean isServerRunning() {
		try {
			new Socket("localhost", getPort());
			return true;
		} catch (UnknownHostException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			return false;
		}
	}
}
