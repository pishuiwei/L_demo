package config;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("")
@Produces("application/json")
public class Rest {
	
	
	
	@GET
	@Path("/home")
	public Map<String, String> test() {
		Map<String, String> map = new HashMap<>();
		map.put("name", "pw");
		map.put("age", "18");
		return map;
	}

	
	
	
}
