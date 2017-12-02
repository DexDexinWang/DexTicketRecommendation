package external;

import org.json.JSONArray;
import org.json.JSONObject;

public class TicketMasterAPI {
	private static final String API_HOST = "app.ticketmaster.com";
	private static final String SEARCH_PATH = "/discovery/v2/events.json";
	private static final String DEFAULT_TERM = "";  // no restriction
	private static final String API_KEY = "vdIdLhDohOWdwL6y41SKJxQPbudMARpw";
	
	public JSONArray search(double lat, double lon, String term) {
		return null;
	}
	
	//convert a string as a URL with "UTF-8" encode
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
}
