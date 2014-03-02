package com.forecast.services;

import java.util.Iterator;
import java.util.List;

import android.app.IntentService;
import android.app.PendingIntent;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;

import com.forecast.activities.ForecastReportActivity;
import com.forecast.connection.ForecastConnector;
import com.forecast.connection.ForecastSearchResult;
import com.forecast.database.ForecastDAO;
import com.forecast.json.ForecastItem;


public class ForecastDataService extends IntentService {
	
	PendingIntent pendingIntent;
	
	Context context;
	
	public ForecastDataService() {
		super("ForecastDataService");
		
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		context = this.getApplicationContext();
		search(intent);

	}
	
	private void search(Intent intent){
		ForecastConnector t = new ForecastConnector(context);
		ForecastSearchResult results = t.doRequest(com.forecast.util.Constants.key, "619");		
		
		ContentResolver cr = getContentResolver();
		ForecastDAO dao = new ForecastDAO(cr);
		
		if(results != null){
			List<ForecastItem> list = results.getResults();
			
			dao.deleteAll();
			
			Long timeStamp = System.currentTimeMillis() / 1000L;
			
			for (Iterator<ForecastItem> it = list.iterator(); it.hasNext();){
				ForecastItem r = it.next();
				if(r.getLocalTimestamp() > timeStamp){
					dao.insert(r.getLocalTimestamp(), 
							   r.getSwell().getMaxBreakingHeight(), 
							   r.getSwell().getMinBreakingHeight(),
							   r.getSolidRating(),
							   r.getSwell().getComponents().getCombined().getHeight(),
							   r.getSwell().getComponents().getCombined().getPeriod(),
							   r.getSwell().getComponents().getCombined().getDirection(),	
							   r.getSwell().getComponents().getPrimary().getHeight(),
							   r.getSwell().getComponents().getPrimary().getPeriod(),
							   r.getSwell().getComponents().getPrimary().getDirection(),	
							   r.getSwell().getComponents().getSecondary().getHeight(),
							   r.getSwell().getComponents().getSecondary().getPeriod(),
							   r.getSwell().getComponents().getSecondary().getDirection(),
							   r.getWind().getSpeed(),
							   r.getWind().getDirection(),
							   r.getWind().getCompassDirection(),
							   r.getCondition().getPressure(),
							   r.getCondition().getTemperature(),
							   r.getCharts().getSwell(),
							   r.getCharts().getPeriod(),
							   r.getCharts().getWind(),
							   r.getCharts().getPressure());
				}
			}
			dao.applyBatch();
			
			Intent intent2 = new Intent(context, ForecastReportActivity.class);    
			intent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			context.startActivity(intent2);
		}
	}


}
