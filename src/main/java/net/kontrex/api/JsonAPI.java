package net.kontrex.api;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class JsonAPI {

	private KontrexAPI api;
	
	public JsonAPI(KontrexAPI api) {
		this.api = api;
	}

	private JsonObject parseJson(String domain) throws IOException {
		URL url = new URL(domain);
		HttpURLConnection request = (HttpURLConnection) url.openConnection();
		request.connect();
		
		JsonParser jp = new JsonParser();
		JsonElement root = jp.parse(new InputStreamReader((InputStream)request.getContent()));
		JsonObject rootobj = root.getAsJsonObject();
		return rootobj;
	}
	
	public JsonObject readObj(String url) {
		try {
			return parseJson(url);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String readString(String url, String key) {
		try {
			JsonObject obj = parseJson(url);
			String s = obj.get(key).getAsString();
			return s;
		} catch (Exception e) {
			System.out.println("[JsonAPI] Error occured while fetching json: " + e.getMessage());
		}
		return "";
	}

}
