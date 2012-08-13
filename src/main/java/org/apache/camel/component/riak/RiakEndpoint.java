package org.apache.camel.component.riak;
import org.apache.camel.Component;
import org.apache.camel.Consumer;
import org.apache.camel.Processor;
import org.apache.camel.Producer;
import org.apache.camel.impl.DefaultEndpoint;

import com.basho.riak.client.IRiakClient;

public class RiakEndpoint extends DefaultEndpoint {
	protected IRiakClient pbClient;
	protected String mapName;

	public String getMapName() {
		return mapName;
	}

	public void setMapName(String mapName) {
		this.mapName = mapName;
	}

	public RiakEndpoint(String endpointUri, Component component,
			String mapName, IRiakClient pbClient) {
		super(endpointUri, component);
		this.mapName = mapName;
		this.pbClient = pbClient;
	}

	public Consumer createConsumer(Processor arg0) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public Producer createProducer() throws Exception {
		return new RiakProducer(this, mapName, pbClient);
	}

	public boolean isSingleton() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	protected String createEndpointUri() {
		return "riak:" + getMapName();
	}
}
