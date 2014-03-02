package com.forecast.activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.forecast.connection.ForecastSearchResult;
import com.forecast.util.Constants;
import com.nano.bodyboardforecast.R;

public class PointSelectionActivity extends Activity {

	private static ForecastSearchResult data;
	Button button;
	Spinner countrySpinner;
	Spinner pointSpinner;
    Context context;
    PointSelectionActivity activity;
	private ProgressDialog dialog = null;
    private CallForecastServiceTask task;
	
	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.point_selection_layout);
		context = this;
		
		countrySpinner = (Spinner) findViewById(R.id.country_spinner);
		countrySpinner.setBackgroundResource(R.drawable.custom_spinner);
		
		pointSpinner = (Spinner) findViewById(R.id.points_spinner);
		pointSpinner.setBackgroundResource(R.drawable.custom_spinner);
		
		countrySpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
					
					ArrayAdapter<String> adapter = new ArrayAdapter<String>(context,  android.R.layout.simple_spinner_item, 
							Constants.mapSpinner.get(countrySpinner.getSelectedItemPosition()));	
					pointSpinner.setAdapter(adapter);
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
			}
		});

		task = (CallForecastServiceTask) getLastNonConfigurationInstance();		
		if(task != null) {
			task.setActivity(this);
        }
		activity = this;
		button = (Button) findViewById(R.id.button1);		 
		button.setOnClickListener(new OnClickListener() {
 
			@Override
			public void onClick(View arg0) {
				task = new CallForecastServiceTask();
				task.setActivity(activity);
				task.execute(pointSpinner.getSelectedItem().toString());
				showDialog();
			}
 
		});
	}

	public void showDialog() {
        this.dialog = ProgressDialog.show(this, getResources().getString(R.string.forecast), "Wait...", true);
	}
	
	public void closeDialog() {
		this.dialog.dismiss();
	}

	public void showForecastResult(ForecastSearchResult result) {
		result.setName(pointSpinner.getSelectedItem().toString());
		PointSelectionActivity.data = result;
		Intent intent = new Intent(PointSelectionActivity.this,ForecastReportActivity.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		context.startActivity(intent);
	}
    
	public static synchronized ForecastSearchResult getData() {
		return data;
	}
}
