package com.forecast.connection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.content.Context;
import android.widget.Toast;

import com.forecast.json.Result;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class ForecastConnector {
	
	private URL url;
	private HttpURLConnection connection;
	ForecastSearchResult result = new ForecastSearchResult();
	Context context;
	
	public ForecastConnector(Context context) {
		this.context = context;
	}

	public ForecastSearchResult doRequest(String key, String spotId){
		try {
			url = new URL("http://magicseaweed.com/api/" + key + "/forecast/?spot_id=" + spotId);
			connection = (HttpURLConnection) url.openConnection();
			
			BufferedReader input = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		     
     	    String line = "";
		    String completo = "";
		     
		    while ((line = input.readLine()) != null) {
		    	 completo += line;
		    }
		     
		    Gson gson = new Gson();
		    
		    JsonParser parser = new JsonParser();
		    JsonArray Jarray = parser.parse(completo).getAsJsonArray();
		    Long time = System.currentTimeMillis() / 1000L;

		    for(JsonElement obj : Jarray)
		    {
		    	Result cse = gson.fromJson(obj , Result.class);
		    	if (cse.getLocalTimestamp() > time) {
		    		result.getResults().add(cse);
		    	}
		    }
		     
		    return result;
		     
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
	    	Toast.makeText(context, "no anda la conexion" , Toast.LENGTH_LONG).show();
			e.printStackTrace();
		} finally {
	    	connection.disconnect();
	    }
		return null;
		
	}

}
