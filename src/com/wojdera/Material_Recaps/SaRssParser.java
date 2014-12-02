package com.wojdera.Material_Recaps;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by wojder on 02.12.14.
 */
public final class SaRssParser {

    private static final String DEFAULT_CHARSET = "UTF-8";
    private static final String AUTHOR = "author";
    private static final String ITEM = "entry";
    private static final String TITLE = "title";
    private static final String DESCRIPTION = "id";
    private static final String CONTENT = "content";
    private static final String UPDATED = "updated";

    private final XmlPullParser parser;
    private Feed feed = Feed.NONE;
    private Item currentItem = Item.NONE;
    private ParseState state = ParseState.NONE;
    private final DateFormat dateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss ZZZZZ", Locale.getDefault());

    private SaRssParser(XmlPullParser parser) {
        this.parser = parser;
    }

    public static SaRssParser newInstance(InputStream inputStream) {

        XmlPullParserFactory factory;
        XmlPullParser parser = null;

        try {
            factory = XmlPullParserFactory.newInstance();
            parser = factory.newPullParser();
            parser.setInput(inputStream, DEFAULT_CHARSET);
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }
        return new SaRssParser(parser);
    }

    public Feed parse() throws Exception {

        int eventType = parser.getEventType();
        while (eventType != XmlPullParser.END_DOCUMENT) {
          parseEvent(eventType);
            eventType = parser.next();
        }
        return feed;
    }

    private void parseEvent(int eventType) {

        switch (eventType) {
            case XmlPullParser.START_TAG:
                startTag(parser.getName());
                break;
            case XmlPullParser.END_TAG:
                endTag(parser.getName());
                break;
            case XmlPullParser.TEXT:
                text(parser.getText());
                break;
            default:
        }

    }

    private void text(String text) {
        switch (state) {
        case ITEM_TITLE:
            currentItem.setTitle(text);
            break;
        case ITEM_CONTENT:
            currentItem.setContent(text);
            break;
            case DESCRIPTION:
                currentItem.setDescription(text);
        case ITEM_UPDATED:
            Date date;
            try {
                date = dateFormat.parse(text);
            } catch (ParseException e) {
                e.printStackTrace();
                return;
            }
            currentItem.setDate(date.getTime());
            break;
        default:
            break;
        }

    }

    private void endTag(String name) {

        if (ITEM.equals(name)) {
            feed.addItem(currentItem);
            currentItem = Item.NONE;
        }
        state = ParseState.NONE;
    }

    private void startTag(String name) {
        if (AUTHOR.equals(name)) {
            feed = new Feed();
        } else if (ITEM.equals(name)) {
            currentItem = new Item();
        } else if (TITLE.equals(name)) {
            if (currentItem != Item.NONE) {
                state = ParseState.ITEM_TITLE;
            }
        } else if (CONTENT.equals(name)) {
            state = ParseState.ITEM_CONTENT;
        } else if (UPDATED.equals(name)) {
            state = ParseState.ITEM_UPDATED;
        } else if (DESCRIPTION.equals(name)) {
            if (currentItem != Item.NONE) {
                state = ParseState.DESCRIPTION;
            }
        }

    }

    private static enum ParseState {
        NONE,
        ITEM_TITLE,
        DESCRIPTION,
        ITEM_CONTENT,
        ITEM_UPDATED
    }
}
