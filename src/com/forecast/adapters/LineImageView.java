package com.forecast.adapters;

import java.io.InputStream;
import java.net.URL;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.forecast.json.ChartItem;
import com.nano.bodyboardforecast.R;

public class LineImageView extends FrameLayout{

	ImageView image;
	TextView name;
	
	public LineImageView(Context context, ChartItem item) {
		super(context);
		
		LayoutInflater li = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	    li.inflate(R.layout.line_image, this);

	    image = (ImageView) findViewById(R.id.imageViewList);
	    name = (TextView) findViewById(R.id.textChart);
	    
	    DownloadImageTask imageDownloader = new DownloadImageTask(image);
	    imageDownloader.execute(item.getUrl());
	    name.setText(item.getName());
	}
	
	private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
	    ImageView bmImage;

	    public DownloadImageTask(ImageView bmImage) {
	        this.bmImage = bmImage;
	    }

	    protected Bitmap doInBackground(String... urls) {
	        String urldisplay = urls[0];
	        Bitmap mIcon11 = null;
	        try {
	            InputStream in = new URL(urldisplay).openStream();
	            mIcon11 = BitmapFactory.decodeStream(in);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return mIcon11;
	    }

	    protected void onPostExecute(Bitmap result) {
	    	result = getResizedBitmap(result, result.getHeight()/2, result.getWidth()/2);
	    	bmImage.setImageBitmap(result);
	    }
	    
	    public Bitmap getResizedBitmap(Bitmap bm, int newHeight, int newWidth) {
	        int width = bm.getWidth();
	        int height = bm.getHeight();
	        float scaleWidth = ((float) newWidth) / width;
	        float scaleHeight = ((float) newHeight) / height;

	        // create a matrix for the manipulation
	        Matrix matrix = new Matrix();

	        // resize the bit map
	        matrix.postScale(scaleWidth, scaleHeight);

	        // recreate the new Bitmap
	        Bitmap resizedBitmap = Bitmap.createBitmap(bm, 0, 0, width, height, matrix, false);

	        return resizedBitmap;
	    }
	}

}
