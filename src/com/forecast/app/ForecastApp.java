package com.forecast.app;



import com.nostra13.universalimageloader.cache.disc.impl.TotalSizeLimitedDiscCache;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration.Builder;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.nostra13.universalimageloader.utils.StorageUtils;

import android.app.Application;

public class ForecastApp extends Application{

    private static final int IMAGE_MEM_CACHE_SIZE = 2 * 1024 * 1024;
    private static final int IMAGE_DISK_CACHE_SIZE = 5 * 1024 * 1024;
    private static ForecastApp instance;
    
    public static ForecastApp getInstance()
    {
        return instance;
    }
    
    @Override
    public void onCreate()
    {
        super.onCreate();
        instance = this;
        
        DisplayImageOptions.Builder displayOptionsBuilder = new DisplayImageOptions.Builder();
        displayOptionsBuilder.cacheInMemory(true);
        displayOptionsBuilder.cacheOnDisc(true);
        DisplayImageOptions defaultDisplayImageOptions = displayOptionsBuilder.build();

        Builder builder = new ImageLoaderConfiguration.Builder(getApplicationContext());
        builder.threadPoolSize(3);
        builder.threadPriority(Thread.NORM_PRIORITY - 1);
        builder.tasksProcessingOrder(QueueProcessingType.FIFO);
        builder.denyCacheImageMultipleSizesInMemory();
        builder.memoryCache(new LruMemoryCache(IMAGE_MEM_CACHE_SIZE));
        builder.discCache(new TotalSizeLimitedDiscCache(StorageUtils.getCacheDirectory(getApplicationContext()), IMAGE_DISK_CACHE_SIZE));
        builder.discCacheSize(IMAGE_DISK_CACHE_SIZE);
        builder.defaultDisplayImageOptions(defaultDisplayImageOptions);
        builder.imageDownloader(new BaseImageDownloader(getBaseContext(), BaseImageDownloader.DEFAULT_HTTP_CONNECT_TIMEOUT, BaseImageDownloader.DEFAULT_HTTP_READ_TIMEOUT));

        ImageLoader.getInstance().init(builder.build());
    }
}
