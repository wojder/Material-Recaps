package com.wojdera.Material_Recaps.Activities;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.TextView;
import com.wojdera.Material_Recaps.Logic.DialogCreator;
import com.wojdera.Material_Recaps.Models.Item;
import com.wojdera.Material_Recaps.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by wojder on 02.12.14.
 */
public class RecapDetailActivity extends Activity {
    public static final String ARG_ITEM = "ARG";
    private static final String NEWLINE = "\\n";
    private static final String BR = "<br />";
    private static final String BLOG_MIME = "text/html; charset=UTF-8";
//    private static final String ENCODING = "charset=UTF-8";
    private DateFormat dateFormat = SimpleDateFormat.getDateInstance(SimpleDateFormat.MEDIUM, Locale.getDefault());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.posts_detail_view);

        ActionBar actionBar = getActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        Item item = (Item) getIntent().getSerializableExtra(ARG_ITEM);

        TextView title = (TextView) findViewById(R.id.feed_detail_title);
        TextView date = (TextView) findViewById(R.id.feed_detail_date);
        WebView webView = (WebView) findViewById(R.id.feed_detail_body);
//        webView.getSettings().setPluginState(WebSettings.PluginState.ON_DEMAND);
//        webView.getSettings().setJavaScriptEnabled(true);
//        webView.getSettings().setAllowFileAccess(true);
//        webView.setWebChromeClient(new WebChromeClient());

        title.setText(item.getTitle());
        date.setText(dateFormat.format(new Date(item.getDate())));
        String html = item.getContent();

        html = html.replaceAll(NEWLINE, BR);
        webView.loadData(html, BLOG_MIME, null);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
            return true;
        } else if (id == R.id.action_settings) {
            new DialogCreator().show(getFragmentManager(), getString(R.string.dialog_text));
        }
        return super.onOptionsItemSelected(item);
    }
}
