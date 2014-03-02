package com.forecast.activities;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.forecast.adapters.ForecastListAdapter;
import com.forecast.adapters.ImageListAdapter;
import com.forecast.connection.ForecastSearchResult;
import com.forecast.json.ChartItem;
import com.forecast.json.Charts;
import com.forecast.json.ForecastItem;
import com.forecast.util.Constants;
import com.nano.bodyboardforecast.R;
import com.nostra13.universalimageloader.core.ImageLoader;

public class ForecastReportActivity extends Activity {
	
	Button buttonForecast;
	Button buttonExtendedForecast;
	Button buttonCharts;
	ImageView mapImageView;
	View forecastView;
	ListView extendedListView;
	ListView chartListView;
	
	TextView title;
	
	TextView wavesHeight;
	RatingBar forecastRatingBar;
	TextView windDirection;
	TextView windSpeed;
	ImageView cursorWind;
	
	TextView primaryWavesHeight;
	TextView primaryPeriodHeight;
	ImageView primaryCursor;

	TextView secondaryWavesHeight;
	TextView secondaryPeriodHeight;
	ImageView secondaryCursor;

	ImageView weatherImage;
	TextView weatherAirTemp;
	TextView weatherSeaTemp;
	
	ForecastItem forecastNow;
	ForecastSearchResult data;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) { 

		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.forecastlayout);
		
		data = PointSelectionActivity.getData();
		forecastNow = getForecastNow(data);
		forecastView = (View) findViewById(R.id.forecast_relative_layout);

		fillForecastInfo();
		
		extendedListView = (ListView) findViewById(R.id.listforecast);
		ForecastListAdapter adapter = new ForecastListAdapter(this, R.layout.line, data.getResults());
		extendedListView.setAdapter(adapter);
		
		chartListView =  (ListView) findViewById(R.id.listimages);
		chartListView.setAdapter(new ImageListAdapter(this, R.layout.line_image, getList()));
		
		buttonForecast = (Button) findViewById(R.id.forecastButton);
		buttonExtendedForecast = (Button) findViewById(R.id.extendedForecastButton);	
		buttonCharts = (Button) findViewById(R.id.chartsButton);	
		
		buttonForecast.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				switchViews(forecastView);
			}
		});
		
		buttonExtendedForecast.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				switchViews(extendedListView);
			}
		});
		
		buttonCharts.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				switchViews(chartListView);
			}
		});
		
	}
	
	private void fillForecastInfo() {
		
		title = (TextView) findViewById(R.id.point_title);
		wavesHeight = (TextView) findViewById(R.id.maxMinWavesText);
		forecastRatingBar = (RatingBar) findViewById(R.id.ratingBar);
		windDirection = (TextView) findViewById(R.id.windDirection);
		windSpeed = (TextView) findViewById(R.id.windSpeed);
		cursorWind = (ImageView) findViewById(R.id.imageCursorWind);
		
		primaryWavesHeight = (TextView) findViewById(R.id.waves_primary_swell_text);
		primaryPeriodHeight = (TextView) findViewById(R.id.period_primary_swell_text);
		primaryCursor = (ImageView) findViewById(R.id.primary_swell_direction_cursor);
		
		secondaryWavesHeight = (TextView) findViewById(R.id.waves_secondary_swell_text);
		secondaryPeriodHeight = (TextView) findViewById(R.id.period_secondary_swell_text);
		secondaryCursor = (ImageView) findViewById(R.id.secondary_swell_direction_cursor);

		weatherImage = (ImageView) findViewById(R.id.weather_image);
		weatherAirTemp = (TextView) findViewById(R.id.weather_air_temperature);
		weatherSeaTemp = (TextView) findViewById(R.id.weather_sea_temperature);
		
		title.setText(data.getName());
		
		wavesHeight.setText(forecastNow.getSwell().getAbsMaxBreakingHeight() +"-" + forecastNow.getSwell().getAbsMinBreakingHeight() + forecastNow.getSwell().getUnit());
		forecastRatingBar.setRating(forecastNow.getSolidRating());
		windDirection.setText(forecastNow.getWind().getDirection() + " " + forecastNow.getWind().getSpeed() + forecastNow.getWind().getUnit());
		cursorWind.setRotation(forecastNow.getWind().getDirection());
		
		primaryWavesHeight.setText(forecastNow.getSwell().getComponents().getPrimary().getHeight() + forecastNow.getSwell().getUnit());
		primaryPeriodHeight.setText(forecastNow.getSwell().getComponents().getPrimary().getPeriod()+ "s");
		primaryCursor.setRotation(forecastNow.getSwell().getComponents().getPrimary().getDirection());
		
		if(forecastNow.getSwell().getComponents().getSecondary() != null) {
			secondaryWavesHeight.setText(forecastNow.getSwell().getComponents().getSecondary().getHeight() + forecastNow.getSwell().getUnit());
			secondaryPeriodHeight.setText(forecastNow.getSwell().getComponents().getSecondary().getPeriod()+ "s");
			secondaryCursor.setRotation(forecastNow.getSwell().getComponents().getSecondary().getDirection());
		}
		else{
			View sec = findViewById(R.id.secondary_swell_layout);
			View secText = findViewById(R.id.secundaryText);
			sec.setVisibility(View.GONE);
			secText.setVisibility(View.GONE);
		}
		mapImageView = (ImageView) findViewById(R.id.imagePointMap);
		mapImageView.setImageDrawable(getCompleteMap());
		
        weatherImage = (ImageView) findViewById(R.id.weather_image);
        ImageLoader.getInstance().displayImage("http://cdnimages.magicseaweed.com/30x30/11.png", weatherImage);	
        
        weatherAirTemp.setText(forecastNow.getCondition().getTemperature().toString() + forecastNow.getCondition().getUnit() + "º");
        weatherSeaTemp.setText(forecastNow.getCondition().getPressure().toString() + forecastNow.getCondition().getUnitPressure());
	}
	
	private List<ChartItem> getList() {
	    ArrayList<ChartItem> list = new ArrayList<ChartItem>();  
	    Charts charts = forecastNow.getCharts();
	    
	    list.add(new ChartItem(charts.getSwell(), getResources().getString(R.string.swell)));
	    list.add(new ChartItem(charts.getPeriod(), getResources().getString(R.string.period)));
	    list.add(new ChartItem(charts.getWind(), getResources().getString(R.string.wind)));
	    list.add(new ChartItem(charts.getPressure(), getResources().getString(R.string.pressure)));
		return list;
	}


	private Drawable getCompleteMap() {
		BitmapDrawable swellBitmap = getRotatedBitmapDrawable(R.drawable.swell_image1, forecastNow.getSwell().getComponents().getCombined().getDirection());
		BitmapDrawable windBitmap = getRotatedBitmapDrawable(R.drawable.wind_image1, Float.valueOf(forecastNow.getWind().getDirection()));
		
		BitmapDrawable map = (BitmapDrawable) getResources().getDrawable(getResources().getIdentifier("map" + Constants.mapAll.get(data.getName()).toString(), "drawable", getPackageName()));
		BitmapDrawable compass = (BitmapDrawable) getResources().getDrawable(R.drawable.img_compass_overlay);
  
	    Drawable drawableArray[]= new Drawable[]{swellBitmap, windBitmap};
	    LayerDrawable layerDraw = new LayerDrawable(drawableArray);
    
	    Bitmap anglesBitmap = Bitmap.createBitmap(layerDraw.getMinimumWidth(), layerDraw.getMinimumHeight(), Bitmap.Config.ARGB_8888);
	    Canvas canvas = new Canvas(anglesBitmap);
	    layerDraw.setBounds(0, 0, layerDraw.getMinimumWidth(), layerDraw.getMinimumHeight());
	    layerDraw.draw(canvas);
    
	    Integer startX = (anglesBitmap.getHeight() - map.getMinimumHeight()) / 2;
	    Integer startY = (anglesBitmap.getWidth() - map.getMinimumWidth()) / 2;
	    Bitmap cropped = Bitmap.createBitmap(anglesBitmap, startX, startY, map.getMinimumHeight(), map.getMinimumWidth());

	    BitmapDrawable anglesBitmapDrawable = new BitmapDrawable(getResources(), cropped); 
	    Drawable drawableArray2[]= new Drawable[]{map, compass, anglesBitmapDrawable};

	    LayerDrawable resultDrawable = new LayerDrawable(drawableArray2);
	    return resultDrawable;
	}

	private BitmapDrawable getRotatedBitmapDrawable(int id, Float angle) {
        Bitmap forMap = BitmapFactory.decodeResource(getResources(), id);
        Matrix matrix = new Matrix();
        matrix.setRotate(angle, forMap.getWidth()/2, forMap.getHeight()/2);
        Bitmap rotatedForMap = Bitmap.createBitmap(forMap , 0, 0, forMap.getWidth(), forMap.getHeight(), matrix, true);
        BitmapDrawable rotatedBitmapDrawable = new BitmapDrawable(getResources(), rotatedForMap); 
        return rotatedBitmapDrawable;
	}
	
	private ForecastItem getForecastNow(ForecastSearchResult data) {
		return data.getResults().get(0);
	}



	private void switchViews(View forecastView2) {
		if(forecastView2.getVisibility() == View.VISIBLE) {
			forecastView2.setVisibility(View.GONE);
		} else {
			forecastView2.setVisibility(View.VISIBLE);
		}	
	}

}
