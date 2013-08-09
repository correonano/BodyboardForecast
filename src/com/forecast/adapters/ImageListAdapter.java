package com.forecast.adapters;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class ImageListAdapter extends BaseAdapter {

	static class ViewHolder {
		  ImageView image;
	}
	
	private Context context;
	private List<String> list = new ArrayList<String>();
	
	public ImageListAdapter(Context context, List<String> list){
		this.context = context;
		this.list = list;
	}
	
	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int i) {
		return list.get(i);
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LineImageView l;
        if (convertView == null) {
            l = new LineImageView(context, list.get(position));
        } else {
            l = (LineImageView) convertView;
        }
        return l;
	}

}
