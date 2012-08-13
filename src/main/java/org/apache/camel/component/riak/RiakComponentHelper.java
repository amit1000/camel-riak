package org.apache.camel.component.riak;

import java.util.HashMap;
import java.util.Map;

import org.apache.camel.Exchange;

public class RiakComponentHelper {
	private final HashMap<String, Integer> mapping = new HashMap<String, Integer>();

	public RiakComponentHelper() {
		this.init();
	}

	public static void copyHeaders(Exchange ex) {
		// get in headers
		Map<String, Object> headers = ex.getIn().getHeaders();

		// delete item id
		if (headers.containsKey(RiakConstants.OBJECT_ID)) {
			headers.remove(RiakConstants.OBJECT_ID);
		}

		if (headers.containsKey(RiakConstants.OPERATION)) {
			headers.remove(RiakConstants.OPERATION);
		}

		// propagate headers if OUT message created
		if (ex.hasOut()) {
			ex.getOut().setHeaders(headers);
		}
	}
	
	 /**
     * Allows the use of speaking operation names (e.g. for usage in Spring DSL)
     */
    public int lookupOperationNumber(String operation) {
        if (this.mapping.containsKey(operation)) {
            return this.mapping.get(operation);
        } else {
            throw new IllegalArgumentException(String.format("Operation '%s' is not supported by this component.", operation));
        }
    }


	private void init() {
		// fill map with values
		this.mapping.put("put", RiakConstants.PUT_OPERATION);
		this.mapping.put("delete", RiakConstants.DELETE_OPERATION);
		this.mapping.put("get", RiakConstants.GET_OPERATION);
		//this.mapping.put("update", RiakConstants.UPDATE_OPERATION);
		//this.mapping.put("query", RiakConstants.QUERY_OPERATION);

	}

}
