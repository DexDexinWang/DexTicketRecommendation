package external;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONObject;

public class TicketMasterAPI {
	private static final String API_PROTOCOL = "http://";
	private static final String API_HOST = "app.ticketmaster.com";
	private static final String SEARCH_PATH = "/discovery/v2/events.json";
	private static final String DEFAULT_TERM = "";  // no restriction
	private static final String API_KEY = "vdIdLhDohOWdwL6y41SKJxQPbudMARpw";
	
	public JSONArray search(double lat, double lon, String term) {
		//get first part of url 
		String url = API_PROTOCOL + API_HOST + SEARCH_PATH;
		//Add Terms
		if (term == null) {
			term = DEFAULT_TERM;
		}
		String geoHash = GeoHash.encodeGeohash(lat, lon, 4);
		//Encode term in url since it may contain special characters
		term = urlEncodeHeloper(term);
		//default term format:
		//"apikey=12345&geoPoint=abcd&keyword=music&radius=50"
		String query = String.format("apikey=%s&geoPoint=%s&keyword=%s&radius=50", API_KEY,geoHash,term);
		
		//open url and get JSON
		try {
			//create connection
			HttpURLConnection connection = (HttpURLConnection) new URL(url+"?"+query).openConnection();
			connection.setRequestMethod("GET");
			//test connection status
			int responseCode = connection.getResponseCode();
			System.out.println("\nSending 'GET' request to URL : " + url + "?" + query);
			System.out.println("Response Code : " + responseCode);
			
			//read response body to get events data
			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String inputLine;
			StringBuilder response = new StringBuilder();
			while((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			
			//Json structure: json object -> embedded object -> event arrays  
			JSONObject responseJson = new JSONObject(response.toString());
			JSONObject embedded = (JSONObject) responseJson.get("_embedded");
			JSONArray events = (JSONArray) embedded.get("events");
			return events;
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//convert a string as a URL value with "UTF-8" encode
	private String urlEncodeHeloper(String term) {
		try {
			term = java.net.URLEncoder.encode(term,"UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return term;
	}
	
	//debug, get JSON from TicketMaster and print
	private void queryAPI(double lat, double lon) {
		JSONArray events = search(lat, lon, null);
		try {
			//traverse all value from events
			for (int i = 0; i < events.length(); i++) {
				JSONObject obj = events.getJSONObject(i);
				System.out.println(obj);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//use main founction to test
	public static void main(String[] args) {
		TicketMasterAPI test = new TicketMasterAPI();
		test.queryAPI(37.37, -122.08);
	}
}
