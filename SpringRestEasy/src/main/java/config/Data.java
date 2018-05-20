package config;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Named;

@Named
public class Data {

	public Map<String, String> getData() {
		Map<String, String> map = new HashMap<>();
		map.put("name", "pwx");
		map.put("age", "18");
		return map;
	}
	
}
