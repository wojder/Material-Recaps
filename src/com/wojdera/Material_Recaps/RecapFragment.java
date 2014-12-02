package com.wojdera.Material_Recaps;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;

/**
 * Created by wojder on 01.12.14.
 */
public class RecapFragment extends Fragment implements Response.Listener<Feed>, Response.ErrorListener {
    private RecapControllerSingleton volley;
    private Feed feed;
    private FeedConsumer feedConsumer;
    private boolean isLoading = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Context context = inflater.getContext();

        initVolley(context);
        update();
        return null;
    }

    private void update() {
        if (feed == null && !isLoading()) {

            String urlBlog = getString(R.string.url_blog);
            volley.addToRequestQueue(new RssRequest(Request.Method.GET, urlBlog, this, this));
            isLoading = true;
        } else {
            if (feedConsumer != null) {
                feedConsumer.setFeed(feed);
            }
        }
    }

    private boolean isLoading() {
        return isLoading;
    }

    private void initVolley(Context context) {
        if (volley == null) {
            volley = RecapControllerSingleton.getInstance(context);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        feedConsumer=null;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        volley.stop();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof FeedConsumer) {
            feedConsumer = (FeedConsumer) activity;
        }
    }

    @Override
    public void onErrorResponse(VolleyError volleyError) {

        if (feedConsumer != null) {
            feedConsumer.handleError(volleyError.getLocalizedMessage());
        }
    }

    @Override
    public void onResponse(Feed feed) {

        this.feed = feed;
        if (feedConsumer != null) {
            feedConsumer.setFeed(feed);
        }
        isLoading = false;
    }
}
