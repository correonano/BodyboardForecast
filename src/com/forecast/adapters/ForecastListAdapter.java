package com.forecast.adapters;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import com.forecast.database.ForecastContentProvider.Forecast;
import com.nano.bodyboardforecast.R;

public class ForecastListAdapter extends CursorAdapter {

	public ForecastListAdapter(Context context, Cursor c){
		super(context, c, 0);
	}

	static class ViewHolder {
		  TextView dia;
		  TextView diaNumero;
		  TextView surf;
		  RatingBar rating;
		  TextView oleajePrimario;
		  
	}
	
	@Override
	public void bindView(View view, Context context, Cursor cursor) {
    	ViewHolder holder = (ViewHolder)view.getTag(); 
    	populateView(cursor, holder);		
	}

	@Override
	public View newView(Context context, Cursor cursor, ViewGroup parent) {
		LayoutInflater li = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	    View l = li.inflate(R.layout.line, null);  
	    ViewHolder holder = new ViewHolder();
    	holder.dia = (TextView) l.findViewById(R.id.textDay); 
    	holder.diaNumero = (TextView) l.findViewById(R.id.textDayNumber); 
    	holder.rating = (RatingBar) l.findViewById(R.id.ratingBar); 
    	holder.surf = (TextView) l.findViewById(R.id.surf); 
    	l.setTag(holder);
		return l;
	}
	
	private void populateView(Cursor cur, ViewHolder holder){
		
	    int colTimestamp = cur.getColumnIndex(Forecast.COL_LOCAL_TIMESTAMP);
	    int colmax = cur.getColumnIndex(Forecast.COL_MAX_BREAKING_HEIGHT);
	    int colmin = cur.getColumnIndex(Forecast.COL_MIN_BREAKING_HEIGHT);
	    int colrating = cur.getColumnIndex(Forecast.COL_SOLID_RATING);
	    
	    Date date = new Date();
	    date.setTime((long)cur.getLong(colTimestamp)*1000);
	    
	    SimpleDateFormat dateFormat = new SimpleDateFormat("EEE"); 
	    SimpleDateFormat dayAndMonthFormat = new SimpleDateFormat("dd/MM HH");
	    String asWeek = dateFormat.format(date);
	    holder.dia.setText(asWeek);
	    holder.diaNumero.setText(dayAndMonthFormat.format(date) + "hs");
	    String surf = cur.getFloat(colmax)+"-"+cur.getFloat(colmin) + "m";
	    holder.surf.setText(surf);
	    holder.rating.setRating(cur.getInt(colrating));
//	    holder.oleajePrimario.setText(text);

	}
		

}
