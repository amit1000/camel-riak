package org.apache.camel.component.riak;

public class RiakConstants {
   /*
     * incoming header properties
   */
   public static final String OBJECT_ID = "CamelRiakObjectId";
    
    // actions (put, delete, get, update)
    public static final String OPERATION = "CamelRiakOperationType";
    public static final int PUT_OPERATION = 1;
    public static final int DELETE_OPERATION = 2;
    public static final int GET_OPERATION = 3;
    public static final int UPDATE_OPERATION = 4;
    public static final int QUERY_OPERATION = 5;

}
