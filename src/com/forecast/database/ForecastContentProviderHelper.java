package com.forecast.database;

import com.forecast.database.ForecastContentProvider.Forecast;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class ForecastContentProviderHelper extends SQLiteOpenHelper  {

	public ForecastContentProviderHelper(Context context, String name, CursorFactory factory, int version) {
		super(context, name, factory, version);
	}

	private String sqlCreate = "CREATE TABLE Forecast " 
								+ "(_id INTEGER PRIMARY KEY AUTOINCREMENT, "
								+ Forecast.COL_LOCAL_TIMESTAMP + " LONG, "
								+ Forecast.COL_MAX_BREAKING_HEIGHT + " FLOAT, "
								+ Forecast.COL_MIN_BREAKING_HEIGHT + " FLOAT, " 
								+ Forecast.COL_SOLID_RATING + " INTEGER ,"  
								+ Forecast.COL_COMBINED_HEIGHT + " FLOAT,"
								+ Forecast.COL_COMBINED_PERIOD + " INTEGER, "
								+ Forecast.COL_COMBINED_DIRECTION + " FLOAT, "
								+ Forecast.COL_PRIMARY_HEIGHT + " FLOAT, "
								+ Forecast.COL_PRIMARY_PERIOD + " INTEGER, "
								+ Forecast.COL_PRIMARY_DIRECTION + " FLOAT, "
								+ Forecast.COL_SECUNDARY_HEIGHT + " FLOAT, "
								+ Forecast.COL_SECUNDARY_PERIOD + " INTEGER, "
								+ Forecast.COL_SECUNDARY_DIRECTION + " FLOAT, "
								+ Forecast.COL_WIND_SPEED + " INTEGER, "
								+ Forecast.COL_WIND_DIRECTION + " INTEGER, "
								+ Forecast.COL_WIND_COMPASS + " TEXT, "
								+ Forecast.COL_PRESSURE + " INTEGER, "
								+ Forecast.COL_TEMPERATURE + " INTEGER, " 
								+ Forecast.COL_CHART_SWELL + " STRING, "
								+ Forecast.COL_CHART_PERIOD + " STRING, "
								+ Forecast.COL_CHART_WIND + " STRING, "
								+ Forecast.COL_CHART_PRESSURE + " STRING" + ")";
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(sqlCreate);		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int versionAnterior, int versionNueva) {
        db.execSQL("DROP TABLE IF EXISTS Forecast");
        db.execSQL(sqlCreate);		
	}

}
