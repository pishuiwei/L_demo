package config;

import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Named
@Path("")
@Produces("application/json")
public class Service {
	
	@Inject
	private Data data;
	
	@GET
	@Path("/getData")
	public Map<String, String> getData() {
		return data.getData();
	}
	
}
