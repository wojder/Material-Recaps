package com.wojdera.Material_Recaps;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by wojder on 02.12.14.
 */
public class RecapDetailActivity extends Activity{
    public static final String ARG_ITEM = "ARG";
    private static final String NEWLINE = "\\n";
    private static final String BR = "<br />";
    private static final String BLOG_MIME = "text/html";
    private DateFormat dateFormat = SimpleDateFormat.getDateInstance(SimpleDateFormat.MEDIUM, Locale.getDefault());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.posts_view);

        ActionBar actionBar = getActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        Item item = (Item) getIntent().getSerializableExtra(ARG_ITEM);

        TextView title = (TextView) findViewById(R.id.feed_item_title);
        TextView date = (TextView) findViewById(R.id.feed_item_date);
        WebView blogView = (WebView) findViewById(R.id.post_detail_body);

        title.setText(item.getTitle());
        date.setText(dateFormat.format(new Date(item.getDate())));
        String html = item.getContent();

        html = html.replaceAll(NEWLINE, BR);
        blogView.loadData(html, BLOG_MIME, null);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}