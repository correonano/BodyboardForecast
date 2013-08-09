package com.forecast.database;

import java.util.ArrayList;

import com.forecast.database.ForecastContentProvider.Forecast;
import com.forecast.json.Combined;
import com.forecast.json.Components;
import com.forecast.json.Condition;
import com.forecast.json.Primary;
import com.forecast.json.Result;
import com.forecast.json.Secondary;
import com.forecast.json.Swell;
import com.forecast.json.Wind;

import android.content.ContentProviderOperation;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.OperationApplicationException;
import android.database.Cursor;
import android.net.Uri;
import android.os.RemoteException;
import android.util.Log;

public class ForecastDAO {
	
	ContentResolver cr;
	
	ArrayList<ContentProviderOperation> operations = new ArrayList<ContentProviderOperation>();
	
	public ForecastDAO(ContentResolver cr){
		this.cr = cr;
	}
	
	public void insert(Long localTimeStamp, Float maxBreakingHeight, Float minBreakingHeight, Integer solidRating,
						Float height1, Float period1, Float direction1, Float height2, Float period2, Float direction2,
						Float height3, Float period3, Float direction3, Integer speed, Integer direction, String compass,
						Float pressure, Float temperature, String chartSwell, String chartPeriod,
						String chartWind, String chartPressure){
		
		ContentValues values = new ContentValues();
		 
		values.put(Forecast.COL_LOCAL_TIMESTAMP, localTimeStamp);
		values.put(Forecast.COL_MAX_BREAKING_HEIGHT, maxBreakingHeight);
		values.put(Forecast.COL_MIN_BREAKING_HEIGHT, minBreakingHeight);
		values.put(Forecast.COL_SOLID_RATING, solidRating);
		values.put(Forecast.COL_COMBINED_HEIGHT, height1);
		values.put(Forecast.COL_COMBINED_PERIOD, period1);
		values.put(Forecast.COL_COMBINED_DIRECTION, direction1);
		values.put(Forecast.COL_PRIMARY_HEIGHT, height2);
		values.put(Forecast.COL_PRIMARY_PERIOD, period2);
		values.put(Forecast.COL_PRIMARY_DIRECTION, direction2);
		values.put(Forecast.COL_SECUNDARY_HEIGHT, height3);
		values.put(Forecast.COL_SECUNDARY_PERIOD, period3);
		values.put(Forecast.COL_SECUNDARY_DIRECTION, direction3);
		values.put(Forecast.COL_WIND_SPEED, speed);
		values.put(Forecast.COL_WIND_DIRECTION, direction);
		values.put(Forecast.COL_WIND_COMPASS, compass);
		values.put(Forecast.COL_PRESSURE, pressure);
		values.put(Forecast.COL_TEMPERATURE, temperature);
		values.put(Forecast.COL_CHART_SWELL, chartSwell);
		values.put(Forecast.COL_CHART_PERIOD, chartPeriod);
		values.put(Forecast.COL_CHART_WIND, chartWind);
		values.put(Forecast.COL_CHART_PRESSURE, chartPressure);
		
		operations.add(ContentProviderOperation.newInsert(ForecastContentProvider.CONTENT_URI).withValues(values).build());
		 
//		cr.insert(TwitterContentProvider.CONTENT_URI, values);
	}
	
	public void deleteAll() {
		cr.delete(ForecastContentProvider.CONTENT_URI, null, null);
		
	}
	
	public void applyBatch(){
		try {
			cr.applyBatch("com.nano.forecast", operations);
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (OperationApplicationException e) {
			e.printStackTrace();
		}
		operations = new ArrayList<ContentProviderOperation>();
	}
	
	public Result getForecastNow(Long timeStamp) {
		String[] projection = new String[] {
			    Forecast._ID,
			    Forecast.COL_LOCAL_TIMESTAMP,
			    Forecast.COL_MAX_BREAKING_HEIGHT,
			    Forecast.COL_MIN_BREAKING_HEIGHT,
			    Forecast.COL_SOLID_RATING, 
			    Forecast.COL_COMBINED_HEIGHT,
			    Forecast.COL_COMBINED_PERIOD,
			    Forecast.COL_COMBINED_DIRECTION,
			    Forecast.COL_PRIMARY_HEIGHT,
			    Forecast.COL_PRIMARY_PERIOD,
			    Forecast.COL_PRIMARY_DIRECTION,
			    Forecast.COL_SECUNDARY_HEIGHT,
			    Forecast.COL_SECUNDARY_PERIOD,
			    Forecast.COL_SECUNDARY_DIRECTION,
			    Forecast.COL_WIND_DIRECTION,
			    Forecast.COL_WIND_SPEED,
			    Forecast.COL_WIND_COMPASS,
			    Forecast.COL_TEMPERATURE,
			    Forecast.COL_PRESSURE};

		Uri forecastUri =  ForecastContentProvider.CONTENT_URI;
		 
		//Hacemos la consulta
		Cursor cur = cr.query(forecastUri, projection, null, null, null); 
		
	    Long localTimeStamp;
	    Float maxBreakingHeight;
	    Float minBreakingHeight;
	    Integer solidRating;
	    Float combinedHeight;
	    Float combinedDirection;
	    Float combinedPeriod;
	    Float primaryHeight;
	    Float primaryDirection;
	    Float primaryPeriod;
	    Float secundaryHeight;
	    Float secundaryPeriod;
	    Float secundaryDirection;
	    Integer windDirection;
	    Integer windSpeed;
	    String windCompass;
	    Float pressure;
	    Float temperature;
		
		if (cur.moveToFirst())
		{

		    int colTime = cur.getColumnIndex(Forecast.COL_LOCAL_TIMESTAMP);
		    int colMax = cur.getColumnIndex(Forecast.COL_MAX_BREAKING_HEIGHT);
		    int colMin = cur.getColumnIndex(Forecast.COL_MIN_BREAKING_HEIGHT);
		    int colRating = cur.getColumnIndex(Forecast.COL_SOLID_RATING);
		    int colCombinedHeight = cur.getColumnIndex(Forecast.COL_COMBINED_HEIGHT);
		    int colCombinedDirection = cur.getColumnIndex(Forecast.COL_COMBINED_PERIOD);
		    int colCombinedPeriod = cur.getColumnIndex(Forecast.COL_COMBINED_PERIOD);
		    int colPrimaryHeight = cur.getColumnIndex(Forecast.COL_PRIMARY_HEIGHT);
		    int colPrimaryDirection = cur.getColumnIndex(Forecast.COL_PRIMARY_PERIOD);
		    int colPrimaryPeriod = cur.getColumnIndex(Forecast.COL_PRIMARY_PERIOD);
		    int colSecundaryHeight = cur.getColumnIndex(Forecast.COL_SECUNDARY_HEIGHT);
		    int colSecundaryDirection = cur.getColumnIndex(Forecast.COL_SECUNDARY_DIRECTION);
		    int colSecundaryPeriod = cur.getColumnIndex(Forecast.COL_SECUNDARY_PERIOD);
		    int colWindDirection = cur.getColumnIndex(Forecast.COL_WIND_DIRECTION);
		    int colWindSpeed = cur.getColumnIndex(Forecast.COL_WIND_SPEED);
		    int colWindCompass = cur.getColumnIndex(Forecast.COL_WIND_COMPASS);
		    int colPressure = cur.getColumnIndex(Forecast.COL_PRESSURE);
		    int colTemperature = cur.getColumnIndex(Forecast.COL_TEMPERATURE);
		  
	        localTimeStamp = cur.getLong(colTime);
	        maxBreakingHeight = cur.getFloat(colMax);
	        minBreakingHeight = cur.getFloat(colMin);
	        solidRating = cur.getInt(colRating);
	       
	        combinedHeight = cur.getFloat(colCombinedHeight);
	        combinedDirection = cur.getFloat(colCombinedDirection);
	        combinedPeriod = cur.getFloat(colCombinedPeriod);	        
	        primaryHeight = cur.getFloat(colPrimaryHeight);
	        primaryDirection = cur.getFloat(colPrimaryDirection);
	        primaryPeriod = cur.getFloat(colPrimaryPeriod);
	        secundaryHeight = cur.getFloat(colSecundaryHeight);
	        secundaryDirection = cur.getFloat(colSecundaryDirection);
	        secundaryPeriod = cur.getFloat(colSecundaryPeriod);
	        windDirection = cur.getInt(colWindDirection);
	        windSpeed = cur.getInt(colWindSpeed);
	        windCompass = cur.getString(colWindCompass);
	        temperature = cur.getFloat(colTemperature);
	        pressure = cur.getFloat(colPressure);
	 
	        Result result = new Result();
	        result.setSwell(new Swell());
	        result.setWind(new Wind());
	        result.setCondition(new Condition());
	        result.getSwell().setComponents(new Components());

	        result.getSwell().getComponents().setCombined(new Combined());
	        result.getSwell().getComponents().setPrimary(new Primary());
	        result.getSwell().getComponents().setSecondary(new Secondary());
	        
	        result.setLocalTimestamp(localTimeStamp);
	        result.getSwell().setMaxBreakingHeight(maxBreakingHeight);
	        result.getSwell().setMinBreakingHeight(minBreakingHeight);
	        result.setSolidRating(solidRating);

	        result.getSwell().getComponents().getCombined().setHeight(primaryHeight);
	        result.getSwell().getComponents().getCombined().setDirection(primaryDirection);
	        result.getSwell().getComponents().getCombined().setPeriod(primaryPeriod);
	        
	        result.getSwell().getComponents().getPrimary().setHeight(primaryHeight);
	        result.getSwell().getComponents().getPrimary().setDirection(primaryDirection);
	        result.getSwell().getComponents().getPrimary().setPeriod(primaryPeriod);

	        result.getSwell().getComponents().getSecondary().setHeight(secundaryHeight);
	        result.getSwell().getComponents().getSecondary().setPeriod(secundaryPeriod);
	        result.getSwell().getComponents().getSecondary().setDirection(secundaryDirection);
	        
	        result.getWind().setSpeed(windSpeed);
	        result.getWind().setDirection(windDirection);
	        result.getWind().setCompassDirection(windCompass);
	        
	        result.getCondition().setPressure(pressure);
	        result.getCondition().setTemperature(temperature);
		    
	        return result;
		}
		return null;
	}
	
	public void get(){
		String[] projection = new String[] {
			    Forecast._ID,
			    Forecast.COL_LOCAL_TIMESTAMP,
			    Forecast.COL_MAX_BREAKING_HEIGHT,
			    Forecast.COL_MIN_BREAKING_HEIGHT,
			    Forecast.COL_SOLID_RATING };
			 
			Uri forecastUri =  ForecastContentProvider.CONTENT_URI;
			 
			//Hacemos la consulta
			Cursor cur = cr.query(forecastUri,
			        projection, //Columnas a devolver
			        null,       //Condici?n de la query
			        null,       //Argumentos variables de la query
			        null); 
			
			if (cur.moveToFirst())
			{
			    Long localTimeStamp;
			    Float maxBreakingHeight;
			    Float minBreakingHeight;
			    Integer solidRating;
			 
			    int colTime = cur.getColumnIndex(Forecast.COL_LOCAL_TIMESTAMP);
			    int colMax = cur.getColumnIndex(Forecast.COL_MAX_BREAKING_HEIGHT);
			    int colMin = cur.getColumnIndex(Forecast.COL_MIN_BREAKING_HEIGHT);
			    int colRating = cur.getColumnIndex(Forecast.COL_SOLID_RATING);
			  
			    String txtResultados = "";
			 
			    do
			    {
			        localTimeStamp = cur.getLong(colTime);
			        maxBreakingHeight = cur.getFloat(colMax);
			        minBreakingHeight = cur.getFloat(colMin);
			        solidRating = cur.getInt(colRating);
			 
			        txtResultados += " " + localTimeStamp + " " + maxBreakingHeight + " " + minBreakingHeight + " " + solidRating;
			 
			    } while (cur.moveToNext());
			    
			    Log.i("resultados","prueba:  " + txtResultados);
			}
	}
}
