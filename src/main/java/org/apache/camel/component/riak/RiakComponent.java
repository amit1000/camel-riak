/**
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

package org.apache.camel.component.riak;

import java.util.Map;
import org.apache.camel.CamelContext;
import org.apache.camel.Endpoint;
import org.apache.camel.impl.DefaultComponent;
import com.basho.riak.client.IRiakClient;
import com.basho.riak.client.RiakFactory;

/**
 * Hello world!
 * 
 */
public class RiakComponent extends DefaultComponent {
	String nodeIP = "127.0.0.1";
	int nodePort = 8087;
	IRiakClient pbClient;
	private Boolean createOwnInstance;

	public RiakComponent() {
	}

	public RiakComponent(CamelContext context) {
		super(context);
	}

	@Override
	protected Endpoint createEndpoint(String uri, String remaining,
			Map<String, Object> parameters) throws Exception {
		
		System.out.println("remaining" + remaining);
		return new RiakEndpoint(uri, this, remaining, pbClient);

	}

	
	@Override
	public void doStart() throws Exception {
		super.doStart();
		if (pbClient == null) {
			createOwnInstance = true;
			pbClient = RiakFactory.pbcClient(nodeIP, nodePort);
			pbClient.ping();
		}
	}

	@Override
	public void doStop() throws Exception {
		if (createOwnInstance && pbClient != null) {
			// riakInstance.getLifecycleService().shutdown();
			pbClient = null;
		}
		super.doStop();
	}

}
