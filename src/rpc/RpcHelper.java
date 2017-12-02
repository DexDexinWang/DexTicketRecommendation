package rpc;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

public class RpcHelper {
	//Create two static methods to reduce duplicated code
	//write JSON object
	public static void writeJSONObject(HttpServletResponse reponse, JSONObject obj) {
		try {
			reponse.setContentType("application/json");
			reponse.addHeader("Access-Control-Allow-Origin", "*");
			PrintWriter out = reponse.getWriter();
			out.print(obj);
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//write JSON array
	public static void writeJSONArray(HttpServletResponse reponse, JSONArray array) {
		try {
			reponse.setContentType("application/json");
			reponse.addHeader("Access-Control-Allow-Origin", "*");
			PrintWriter out = reponse.getWriter();
			out.print(array);
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
