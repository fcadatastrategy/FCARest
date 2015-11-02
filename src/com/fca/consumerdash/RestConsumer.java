package com.fca.consumerdash;

import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/consumer")
public class RestConsumer {

	ADOConsumerBusObjs adoConsumer = new ADOConsumerBusObjs();

	
	@GET
	@Path("/choice/{crossTabOption}")
	@Produces({ MediaType.APPLICATION_JSON })
	public List<JSONConsumerOption> BuildProdProvTimeJSON(@PathParam("crossTabOption") String crossTabOption) {
		return adoConsumer.findAllConsumerChoice(crossTabOption);
	}
	
}
