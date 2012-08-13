Riak Component
=============
The Riak: component allows you to work with the Riak, an open source, highly scalable, fault-tolerant distributed database.
Using this component Apache Camel route can execute put, get and delete operations against Riak database bucket.

This component assume that Rick should installed on 127.0.0.1:8087. In feature release, we will change to configure Riak <IP>:<PORT> dynamically.

Maven
---------------
##Maven users will need to add the following dependency to their pom.xml for this component:
 
<repositories>
    <repository>
      <id>xxxxxxxx</id>
      <url>xxxxxxx</url>
    </repository>
 </repositories>
</project>

<dependency>
    <groupId>org.apache.camel</groupId>
    <artifactId>camel-riak</artifactId>
    <version>x.x.x</version>
    <!-- use the same version as your Camel core version -->
</dependency>

##You can call the samples with:
------------------------------------
template.sendBodyAndHeader("direct:[riak.put|riak.get|riak.delete]", "my-foo", RiakConstants.OBJECT_ID, "5111");



##Sample for put
---------------
Java DSL:

from("direct:riak.put")
.setHeader(RiakConstants.OPERATION,constant(RiakConstants.PUT_OPERATION))
.to("riak:myMap").to("mock:result");

Spring DSL:

<route>
	<from uri="direct:riak.put" />
        <!-- set headerName to "CamelRiakOperationType" -->
	<setHeader headerName="CamelRiakOperationType">
		<constant>put</constant>
	</setHeader>
	<to uri="riak:myMap" />
	<to uri=""mock:result" />
</route>

##Sample for get
---------------
Java DSL:
from("direct:riak.get")
.setHeader(RiakConstants.OPERATION,constant(RiakConstants.GET_OPERATION))
.to("riak:myMap").to("mock:result");

Spring DSL:

<route>
	<from uri="direct:riak.get" />
        <!-- set headerName to "CamelRiakOperationType" -->
	<setHeader headerName="CamelRiakOperationType">
		<constant>get</constant>
	</setHeader>
	<to uri="riak:myMap" />
	<to uri=""mock:result" />
</route>

##Sample for delete
------------------
Java DSL:

from("direct:riak.delete").
setHeader(RiakConstants.OPERATION,constant(RiakConstants.DELETE_OPERATION))
.to("riak:myMap").to("mock:result");

Spring DSL:

<route>
	<from uri="direct:riak.delete" />
        <!--  set headerName to "CamelHazelcastOperationType" -->
	<setHeader headerName="CamelRiakOperationType">
		<constant>delete</constant>
	</setHeader>
	<to uri="riak:myMap" />
	<to uri=""mock:result" /
</route>


