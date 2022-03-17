package framework;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.List;

public class JsonReader {

	public static void ReadJson(String JsonFilePath) throws FileNotFoundException, IOException, ParseException {
		JSONParser parser = new JSONParser();
		Object obj = parser.parse(new FileReader(JsonFilePath));
		JSONObject jsonObject = (JSONObject) obj;
		
		JSONArray jsonArray = (JSONArray) jsonObject.get("capabilities");
		JSONObject configuration = (JSONObject) jsonObject.get("configuration");
		JSONObject capabilities = (JSONObject) jsonArray.get(0);

		List<String> keyList = new ArrayList<String>();
		keyList.add("deviceName");
		keyList.add("udid");
		keyList.add("version");
		keyList.add("port");
		
		for (String key : keyList) {
			if (key.equals("port")) {
				HashMapContainer.add(key, configuration.get(key).toString());
			}else
			{
				HashMapContainer.add(key, capabilities.get(key).toString());
			}
			System.out.println(HashMapContainer.get(key));
		}
		
	}
}
