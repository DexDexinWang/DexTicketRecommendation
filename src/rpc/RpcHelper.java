package rpc;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import entity.Item;

public class RpcHelper {
	
	/**
	* A helper class to handle rpc related parsing logics.
	*/
	 public static JSONObject readJsonObject(HttpServletRequest request) {
		   StringBuffer jb = new StringBuffer();
		   String line = null;
		   try {
		     BufferedReader reader = request.getReader();//Body
		     while ((line = reader.readLine()) != null) {
		       jb.append(line);
		     }
		     reader.close();
		     return new JSONObject(jb.toString());
		   } catch (Exception e) {
		     e.printStackTrace();
		   }
		   return null;
		 }
	 
	 // Converts a list of Item objects to JSONArray.
	 public static JSONArray getJSONArray(List<Item> items) {
	   JSONArray result = new JSONArray();
	   try {
	     for (Item item : items) {
	       result.put(item.toJSONObject());
	     }
	   } catch (Exception e) {
	     e.printStackTrace();
	   }
	   return result;
	 }


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
