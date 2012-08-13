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
