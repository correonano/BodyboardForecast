package com.forecast.activities;

import java.util.ArrayList;

import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.forecast.adapters.ForecastListAdapter;
import com.forecast.adapters.ImageListAdapter;
import com.forecast.database.ForecastContentProvider;
import com.forecast.database.ForecastDAO;
import com.forecast.json.Result;
import com.nano.bodyboardforecast.R;

public class ForecastReportActivity extends Activity {
	
	Button button;
	Button button2;
	ListView listView;
	ListView listView2;

	BitmapFactory.Options opts;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.forecastlayout);
		
		ContentResolver cr = getContentResolver();
		ForecastDAO dao = new ForecastDAO(cr);
		Result res = dao.getForecastNow(System.currentTimeMillis() / 1000L);

		TextView maxMinSwell = (TextView) findViewById(R.id.maxMinWavesText);	
		maxMinSwell.setText(res.getSwell().getMaxBreakingHeight() + "-" + res.getSwell().getMinBreakingHeight());
		
		RatingBar ratingBar = (RatingBar) findViewById(R.id.ratingBar);
		ratingBar.setRating(res.getSolidRating());
		
		TextView windCompass = (TextView) findViewById(R.id.windDirection);
		TextView windSpeed = (TextView) findViewById(R.id.windSpeed);
		windCompass.setText("WIND: " + res.getWind().getCompassDirection());
		windSpeed.setText(res.getWind().getSpeed() + "km/h");
		
		if(res.getSwell().getComponents().getPrimary().getHeight() != null) {
			TextView primarySwell = (TextView) findViewById(R.id.secundaryText);	
			primarySwell.setText(primarySwell.getText() +  " " + res.getSwell().getComponents().getPrimary().getHeight() + "m " 
				+ res.getSwell().getComponents().getPrimary().getPeriod() + "s");
		}
		
		if(res.getSwell().getComponents().getSecondary().getHeight() != null) {
			TextView secundarySwell = (TextView) findViewById(R.id.terciaryText);	
			secundarySwell.setText(secundarySwell.getText() +  " " + res.getSwell().getComponents().getSecondary().getHeight() + "m " 
				+ res.getSwell().getComponents().getSecondary().getPeriod() + "s");
		}
		
		TextView temperature = (TextView) findViewById(R.id.temperatureText);
		TextView pressure = (TextView) findViewById(R.id.pressureText);
		temperature.setText(res.getCondition().getTemperature()+ "c");
		pressure.setText(res.getCondition().getPressure() + "hp");

		opts = new BitmapFactory.Options();
		opts.inScaled = false;
		
        ImageView cursorSwell = (ImageView)findViewById(R.id.imageCursorWind);
		BitmapDrawable swellCursor = getRotatedBitmapDrawable(R.drawable.cursor_viento, res.getSwell().getComponents().getCombined().getDirection());
		swellCursor.setGravity(Gravity.CENTER);
        cursorSwell.setImageDrawable(swellCursor);
        
        BitmapDrawable swellBitmap = getRotatedBitmapDrawable(R.drawable.swell_image1, res.getSwell().getComponents().getCombined().getDirection());
        BitmapDrawable windBitmap = getRotatedBitmapDrawable(R.drawable.wind_image1, res.getWind().getDirection().floatValue());
        
        BitmapDrawable rosa = (BitmapDrawable) getResources().getDrawable(R.drawable.rosa_viento);
        BitmapDrawable map = (BitmapDrawable) getResources().getDrawable(R.drawable.teahupomap);
        
        swellBitmap.setGravity(Gravity.CENTER);
        windBitmap.setGravity(Gravity.CENTER);
	    Drawable drawableArray[]= new Drawable[]{map, rosa, swellBitmap, windBitmap};
	    LayerDrawable layerDraw = new LayerDrawable(drawableArray);

        ImageView imageMap = (ImageView)findViewById(R.id.imagePointMap);
        imageMap.setImageDrawable(layerDraw);
	    
	    /*****/
        
		listView = (ListView) findViewById(R.id.listforecast);
		Cursor cursor = cr.query(ForecastContentProvider.CONTENT_URI, null, null, null, null);
		ForecastListAdapter adapter = new ForecastListAdapter(this, cursor);
		listView.setAdapter(adapter);
	
		
		
		button = (Button) findViewById(R.id.listButton);		 
		button.setOnClickListener(new OnClickListener() {
 
			@SuppressWarnings("static-access")
			@Override
			public void onClick(View arg0) {
				if(!(listView.getVisibility() == listView.VISIBLE)) {
					listView.setVisibility(listView.VISIBLE);
				}
				else {
					listView.setVisibility(listView.GONE);
				}
			}
		});
		 
	    ArrayList<String> list = new ArrayList<String>();  
	    list.add("http://chart-1-us.msw.ms/wave/750/38-1375401600-1.gif");
	    list.add("http://chart-1-us.msw.ms/wave/750/38-1375401600-2.gif");
	    list.add("http://chart-1-us.msw.ms/gfs/750/38-1375401600-4.gif");
	    list.add("http://chart-1-us.msw.ms/gfs/750/38-1375401600-4.gif");
	    
		listView2 =  (ListView) findViewById(R.id.listimages);
		listView2.setAdapter(new ImageListAdapter(this, list));

		button2 = (Button) findViewById(R.id.listButtonImages);		 
		button2.setOnClickListener(new OnClickListener() {
 
			@SuppressWarnings("static-access")
			@Override
			public void onClick(View arg0) {
				if(!(listView2.getVisibility() == listView2.VISIBLE)) {
					listView2.setVisibility(listView2.VISIBLE);
				}
				else {
					listView2.setVisibility(listView2.GONE);
				}
			}
		});
	}


	private BitmapDrawable getRotatedBitmapDrawable(int id, Float angle) {
        Bitmap forMap = BitmapFactory.decodeResource(getResources(), id, opts);
        Matrix matrix = new Matrix();
        matrix.setRotate(angle, forMap.getWidth()/2, forMap.getHeight()/2);
        Bitmap rotatedForMap = Bitmap.createBitmap(forMap , 0, 0, forMap.getWidth(), forMap.getHeight(), matrix, true);
        rotatedForMap = Bitmap.createBitmap(rotatedForMap , 0, 0, forMap.getWidth(), forMap.getHeight());
        Log.d("width y heigh ", rotatedForMap.getWidth() + " " + forMap.getWidth());
        BitmapDrawable rotatedBitmapDrawable = new BitmapDrawable(getResources(), rotatedForMap); 
        return rotatedBitmapDrawable;
	}
}
