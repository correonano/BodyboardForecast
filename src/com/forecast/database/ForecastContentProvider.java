package com.forecast.database;


import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.provider.BaseColumns;

public class ForecastContentProvider extends ContentProvider{
	
	private static final String uri = "content://com.nano.forecast/forecast";
	public static final Uri CONTENT_URI = Uri.parse(uri);
	private static final String BD_NOMBRE = "DBForecast";
	private static final int BD_VERSION = 1;
	
	//uris
	private static final String TABLA_FORECAST = "Forecast";	
	
	private ForecastContentProviderHelper forecastHelper;
	
	private static final int FORECAST = 1;
	private static final int FORECAST_ID = 2;
	private static final UriMatcher uriMatcher;
	 
	//Inicializamos el UriMatcher
	static {
	    uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
	    uriMatcher.addURI("com.nano.forecast", "forecast", FORECAST);
	    uriMatcher.addURI("com.nano.forecast", "forecast/#", FORECAST_ID);
	}	

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		 int cont;

	    String where = selection;
	 
	    SQLiteDatabase db = forecastHelper.getWritableDatabase();
	    cont = db.delete(TABLA_FORECAST, where, selectionArgs);
	    return cont;
	}

	@Override
	public String getType(Uri uri) {
	    int match = uriMatcher.match(uri);
	    
	    switch (match)
	    {
	        case FORECAST:
	            return "vnd.android.cursor.dir/vnd.nano.forecast";
	        case FORECAST_ID:
	            return "vnd.android.cursor.item/vnd.nano.forecast";
	        default:
	            return null;
	    }
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		
	    long regId = 1;
	    SQLiteDatabase db = forecastHelper.getWritableDatabase();
	    regId = db.insert(TABLA_FORECAST, null, values);
	 
	    Uri newUri = ContentUris.withAppendedId(CONTENT_URI, regId);
	 
	    return newUri;
	}

	@Override
	public boolean onCreate() {
		  forecastHelper = new ForecastContentProviderHelper(getContext(), BD_NOMBRE, null, BD_VERSION);
		  return true;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		
	    String where = selection;
	 
	    SQLiteDatabase db = forecastHelper.getWritableDatabase();
	    Cursor c = db.query(TABLA_FORECAST, projection, where, selectionArgs, null, null, sortOrder);
	    return c;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {

	    int cont;
	    String where = selection;
	 
	    SQLiteDatabase db = forecastHelper.getWritableDatabase();
	    cont = db.update(TABLA_FORECAST, values, where, selectionArgs);
	    return cont;
	}

	public static final class Forecast implements BaseColumns
	{
	    private Forecast() {}
	    //Nombres de columnas
	    public static final String COL_LOCAL_TIMESTAMP = "local_timestamp";
	    public static final String COL_SOLID_RATING = "solid_rating";
	    public static final String COL_MIN_BREAKING_HEIGHT = "min_breaking_height";
	    public static final String COL_MAX_BREAKING_HEIGHT = "max_breaking_height";
	    public static final String COL_COMBINED_HEIGHT = "combined_height";
	    public static final String COL_COMBINED_PERIOD = "combined_period";
	    public static final String COL_COMBINED_DIRECTION = "combined_direction";
	    public static final String COL_PRIMARY_HEIGHT = "primary_height";
	    public static final String COL_PRIMARY_PERIOD = "primary_period";
	    public static final String COL_PRIMARY_DIRECTION = "primary_direction";
	    public static final String COL_SECUNDARY_HEIGHT = "secundary_height";
	    public static final String COL_SECUNDARY_PERIOD = "secundary_period";
	    public static final String COL_SECUNDARY_DIRECTION = "secundary_direction";
	    public static final String COL_WIND_SPEED = "wind_speed";
	    public static final String COL_WIND_DIRECTION = "wind_direction";
	    public static final String COL_WIND_COMPASS = "wind_compass";
	    public static final String COL_PRESSURE = "pressure";
	    public static final String COL_TEMPERATURE = "temperature";
	    public static final String COL_CHART_SWELL = "chart_swell";
	    public static final String COL_CHART_PERIOD = "chart_period";
	    public static final String COL_CHART_WIND = "chart_wind";
	    public static final String COL_CHART_PRESSURE = "chart_pressure";
	    
	}
}
