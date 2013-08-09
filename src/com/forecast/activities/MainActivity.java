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
import android.widget.Button;
import android.widget.Spinner;

import com.forecast.services.ForecastDataService;
import com.nano.bodyboardforecast.R;

public class MainActivity extends Activity implements OnItemSelectedListener {

	Button button;
	Spinner spinner;
    private ProgressDialog pd = null;
    Context context;
    private Boolean flag = false;  
    private Integer lastSelection = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);
		context = this;
		
		spinner = (Spinner) findViewById(R.id.points_spinner);
		spinner.setOnItemSelectedListener(this);

		button = (Button) findViewById(R.id.button1);		 
		button.setOnClickListener(new OnClickListener() {
 
			@Override
			public void onClick(View arg0) {
 
				startTwitterService();
		        // Show the ProgressDialog on this thread
		        pd = ProgressDialog.show(context, "Working..", "Downloading Data...", true, false);

 
			}
 
		});
	}


	protected void startTwitterService() {
		Intent intent = new Intent(this, ForecastDataService.class);
		
		startService(intent);		
	}

    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            if (flag) {
            	startTwitterService();
		        pd = ProgressDialog.show(context, "Working..", "Downloading Data...", true, false);
            }
            else {
            	flag = true;
            }
    }
    
	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		
	}

}
