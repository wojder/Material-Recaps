package com.wojdera.Material_Recaps.Models;

/**
 * Created by wojder on 02.12.14.
 */
public interface FeedConsumer {
    void setFeed(Feed feed);
    void handleError(String message);
}
