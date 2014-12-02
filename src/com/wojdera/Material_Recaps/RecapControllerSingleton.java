package com.wojdera.Material_Recaps;

import android.content.Context;
import com.android.volley.Cache;
import com.android.volley.Request;
import com.android.volley.Network;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;

/**
 * Created by wojder on 02.12.14.
 */
public final class RecapControllerSingleton {
    private static final int CACHE_SIZE = 1024 * 1024 * 10;
    private static RecapControllerSingleton mInstance;

    private Context context;
    private RequestQueue requestQueue;

    private RecapControllerSingleton(Context context) {

        this.context = context;
        requestQueue = getRequestQueue();
    }

    public RequestQueue getRequestQueue() {
        if (requestQueue == null) {
            Cache cache = new DiskBasedCache(context.getCacheDir(), CACHE_SIZE);
            Network network = new BasicNetwork(new HurlStack());
            requestQueue = new RequestQueue(cache, network);
            requestQueue.start();
        }
        return requestQueue;
    }


    public static synchronized RecapControllerSingleton getInstance(Context context) {

        if (mInstance == null) {
            mInstance = new RecapControllerSingleton(context);
        }
        return mInstance;

    }

    public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }

    public void stop(){
        getRequestQueue().stop();
    }
}
