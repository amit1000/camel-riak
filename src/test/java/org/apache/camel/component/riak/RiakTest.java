package org.apache.camel.component.riak;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.camel.Component;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.CamelTestSupport;

/**
 * Unit test for simple App.
 */
public class RiakTest extends CamelTestSupport {


	public void testPut() throws Exception {
		MockEndpoint mock = getMockEndpoint("mock:result");
		mock.expectedMessageCount(1);
		Map<String, Object> headers = new HashMap<String, Object>();
		Object o = new String("my-foo");
		//template.sendBody("direct:riak.put", o);
		template.sendBodyAndHeader("direct:riak.put", o, RiakConstants.OBJECT_ID, "4711");
		mock.assertIsSatisfied();
	}

	public void testGet() throws Exception {

		MockEndpoint mock = getMockEndpoint("mock:result");
		Object o = "";
		mock.expectedMessageCount(1);
		template.sendBodyAndHeader("direct:riak.get", "1-foo", RiakConstants.OBJECT_ID, "4711");
     	mock.assertIsSatisfied();
		//assertEquals("my-foo", (String) mock.getReceivedExchanges()
			//	.get(0).getIn().getBody().toString());

		System.out.println((String) mock.getReceivedExchanges().get(0).getIn()
				.getBody());

	}

	public void testDelete() throws Exception {
		MockEndpoint mock = getMockEndpoint("mock:result");
		Object o = "test2";
		mock.expectedMessageCount(1);
		template.sendBodyAndHeader("direct:riak.delete", o, RiakConstants.OBJECT_ID, "4711");
		mock.assertIsSatisfied();
		

		System.out.println((String) mock.getReceivedExchanges().get(0).getIn()
				.getBody());

	}
	public void testGetAfterDelete() throws Exception {

		MockEndpoint mock = getMockEndpoint("mock:result");
		Object o = "";
		mock.expectedMessageCount(1);
		template.sendBodyAndHeader("direct:riak.get", "1-foo", RiakConstants.OBJECT_ID, "4711");
     	mock.assertIsSatisfied();
		assertNull(mock.getReceivedExchanges()
				.get(0).getIn().getBody());

		System.out.println("test" +  mock.getReceivedExchanges().get(0).getIn()
				.getBody());

	}
	protected RouteBuilder createRouteBuilder() {
		return new RouteBuilder() {
			public void configure() {
				getContext().addComponent("riak",
						new RiakComponent(getContext()));
				getContext().getComponent("riak", RiakComponent.class);
				from("direct:riak.get")
						.setHeader(RiakConstants.OPERATION,
								constant(RiakConstants.GET_OPERATION))
						.to("riak:myMap").to("mock:result");
				from("direct:riak.put")
						.setHeader(RiakConstants.OPERATION,
								constant(RiakConstants.PUT_OPERATION))
						.to("riak:myMap").to("mock:result");
				from("direct:riak.delete")
				.setHeader(RiakConstants.OPERATION,
						constant(RiakConstants.DELETE_OPERATION))
				.to("riak:myMap").to("mock:result");
			}

		};
	}

}
