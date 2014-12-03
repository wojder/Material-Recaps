package com.wojdera.Material_Recaps.Models;

import java.io.Serializable;

/**
 * Created by wojder on 02.12.14.
 */
public class Item implements Serializable {
    public static final Item NONE = new Item();

    private String title;
    private String id;
    private String content;
    private long updated;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return id;
    }

    public void setDescription(String description) {
        this.id = description;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getDate() {
        return updated;
    }

    public void setDate(long pubDate) {
        this.updated = pubDate;
    }
}
