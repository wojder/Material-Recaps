package com.wojdera.Material_Recaps.Logic.Parse;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.android.volley.Request;
import com.android.volley.toolbox.HttpHeaderParser;
import com.wojdera.Material_Recaps.Models.Feed;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 * Created by wojder on 02.12.14.
 */
public class RssRequest extends Request<Feed> {

    private final Response.Listener<Feed> feedListener;

    public RssRequest(int method, String url, Response.Listener<Feed> feedListener, Response.ErrorListener errorListener){
        super(method, url, errorListener);

        this.feedListener = feedListener;


    }
    @Override
    protected Response<Feed> parseNetworkResponse(NetworkResponse networkResponse) {
        InputStream inputStream = new ByteArrayInputStream(networkResponse.data);
        SaRssParser parser = SaRssParser.newInstance(inputStream);
        Feed feed;

        try {
            feed = parser.parse();
        } catch (Exception e) {
            return Response.error(new ParseError(e)) ;
        }

        return Response.success(feed, HttpHeaderParser.parseCacheHeaders(networkResponse));
    }

    @Override
    protected void deliverResponse(Feed response) {

        feedListener.onResponse(response);

    }
}
