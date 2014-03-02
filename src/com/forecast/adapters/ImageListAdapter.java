package com.forecast.adapters;

import java.util.ArrayList;
import java.util.List;

import com.forecast.json.ChartItem;
import com.forecast.json.ForecastItem;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class ImageListAdapter extends ArrayAdapter<ChartItem> {

	static class ViewHolder {
		  ImageView image;
	}
	
	private Context context;
	
	public ImageListAdapter(Context context, int resource, List<ChartItem> objects){
		super(context, resource, objects);
		this.context = context;
	}
	
	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LineImageView l;
        if (convertView == null) {
            l = new LineImageView(context, getItem(position));
        } else {
            l = (LineImageView) convertView;
        }
        return l;
	}

}
