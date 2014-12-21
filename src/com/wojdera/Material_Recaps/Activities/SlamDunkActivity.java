package com.wojdera.Material_Recaps.Activities;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.wojdera.Material_Recaps.R;

/**
 * Created by wojder on 21.12.14.
 */
public class SlamDunkActivity extends Activity {

    WebView webview;
    private String url="http://slamdunk.com.pl/forum/index.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview);

        webview = (WebView) findViewById(R.id.webview);
        webview.setWebViewClient(new WebViewClient());
        webview.getSettings().setJavaScriptEnabled(true);
        webview.loadUrl(url);
    }
}
