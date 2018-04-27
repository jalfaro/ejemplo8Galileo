package edu.galileo.consultagithub;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class WebApplication extends Application {
    private RequestQueue queue;

    public RequestQueue getQueue() {
        if (queue == null) {
            queue = Volley.newRequestQueue(this);
        }
        return queue;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        queue.cancelAll("FINALIZADO");
    }
}
