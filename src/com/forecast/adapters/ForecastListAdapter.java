package com.forecast.adapters;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.forecast.json.ForecastItem;
import com.nano.bodyboardforecast.R;
import com.nostra13.universalimageloader.core.ImageLoader;

@SuppressLint("SimpleDateFormat")
public class ForecastListAdapter extends ArrayAdapter<ForecastItem> {
	
	private Context context;

	public ForecastListAdapter(Context context, int resource, List<ForecastItem> objects){
		super(context, resource, objects);
		this.context = context;
	}

	static class ViewHolder {
		  TextView dia;
		  TextView diaNumero;
		  TextView surf;
		  RatingBar rating;
		  TextView oleajePrimario;
		  TextView airTemp;
		  TextView seaTemp;
		  ImageView weatherIcon;
		  
	}
	

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {

        ViewHolder viewHolder;
        
    	if(convertView == null) {
    		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    		convertView = inflater.inflate(R.layout.line, parent, false);
    		
    	    viewHolder = new ViewHolder();
    	    convertView.setTag(viewHolder);
			final View w = (View) convertView.findViewById(R.id.extended_weather); 
    	    convertView.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					if(w.getVisibility() == View.VISIBLE) {
						w.setVisibility(View.GONE);
					} else {
						w.setVisibility(View.VISIBLE);
					}
				}
			});
    	} else {
    		
    		viewHolder = (ViewHolder) convertView.getTag();
    	}
        

        ForecastItem info = getItem(position);
        
        viewHolder.dia = (TextView) convertView.findViewById(R.id.textDay); 
        viewHolder.diaNumero = (TextView) convertView.findViewById(R.id.textDayNumber); 
        viewHolder.rating = (RatingBar) convertView.findViewById(R.id.ratingBar); 
        viewHolder.surf = (TextView) convertView.findViewById(R.id.surf); 
        viewHolder.airTemp = (TextView) convertView.findViewById(R.id.weather_air_temperature); 
        viewHolder.seaTemp = (TextView) convertView.findViewById(R.id.weather_sea_temperature); 
        viewHolder.weatherIcon = (ImageView) convertView.findViewById(R.id.weather_extended_image); 
        populateView(viewHolder, info);
		return convertView;
    }
    
	private void populateView(ViewHolder holder, ForecastItem info){
		
	    Date date = new Date();
	    date.setTime((long)info.getTimestamp()*1000);
	    
	    SimpleDateFormat dateFormat = new SimpleDateFormat(com.forecast.util.Constants.DATE_FORMAT); 
	    SimpleDateFormat dayAndMonthFormat = new SimpleDateFormat(com.forecast.util.Constants.DAY_FORMAT);
	    String asWeek = dateFormat.format(date);
	    holder.dia.setText(asWeek);
	    holder.diaNumero.setText(dayAndMonthFormat.format(date) + "hs");
	    String surf = info.getSwell().getMaxBreakingHeight()+"-"+info.getSwell().getMinBreakingHeight() + com.forecast.util.Constants.METER;
	    holder.surf.setText(surf);
	    holder.rating.setRating(info.getSolidRating());
	    
//	    holder.seaTemp.setText(info.get);

        ImageLoader.getInstance().displayImage("http://cdnimages.magicseaweed.com/30x30/11.png", holder.weatherIcon);

	}
		

}
