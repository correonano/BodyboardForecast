package com.forecast.activities;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.app.NotificationCompat;

import com.forecast.connection.ForecastConnector;
import com.forecast.connection.ForecastSearchResult;
import com.forecast.util.Constants;
import com.nano.bodyboardforecast.R;

public class CallForecastServiceTask extends AsyncTask<String, Integer, ForecastSearchResult> {
	
	PointSelectionActivity activity;
	ForecastConnector t;
	String name;
	
    protected void onProgressUpdate(Integer... progress) {

    }

    protected void onPreExecute(){
		t = new ForecastConnector(activity.getApplicationContext());
    }	
    
	@Override
	protected ForecastSearchResult doInBackground(String... params) {
		name = params[0];
		ForecastSearchResult results = t.doRequest(com.forecast.util.Constants.key, Constants.mapAll.get(name).toString());		
		return results;
	}
	
	@Override
	protected void onPostExecute(ForecastSearchResult result){

    	Context context = activity.getApplicationContext();
//    	if (activity.hasDialogFocus()){
//    	}
//    	else{
	    	NotificationManager notifManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE); 
	    	Notification notification = new NotificationCompat.Builder(context).setContentTitle(context.getResources().getString(R.string.notification_text, name))
														    	         .setContentText("Come back! The forecast is ready!")
														    	         .setSmallIcon(R.drawable.forecast_launcher).build();
	    	notifManager.notify(1, notification);  
//    	}
		activity.closeDialog();
    	activity.showForecastResult(result);
    }
    
    public void setActivity(PointSelectionActivity activity){
    	this.activity = activity;
    }

	public synchronized void setT(ForecastConnector t) {
		this.t = t;
	}
    
}
