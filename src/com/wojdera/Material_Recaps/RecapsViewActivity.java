package com.wojdera.Material_Recaps;

import android.app.FragmentTransaction;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

public class RecapsViewActivity extends ListActivity implements FeedConsumer {

    private static final String RECAP_FRAGMENT_TAG = RecapFragment.class.getCanonicalName();
    private RecapAdapter adapter;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(android.R.layout.list_content);

        RecapFragment recapFragment = (RecapFragment) getFragmentManager().findFragmentByTag(RECAP_FRAGMENT_TAG);
        if (recapFragment == null) {
            recapFragment = (RecapFragment) RecapFragment.instantiate(this, RecapFragment.class.getName());
            recapFragment.setRetainInstance(true);
            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.add(recapFragment, RECAP_FRAGMENT_TAG);
            fragmentTransaction.commit();
        }
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        Intent recapIntent = new Intent(this, RecapDetailActivity.class);
        Item item = adapter.getItem(position);
        recapIntent.putExtra(RecapDetailActivity.ARG_ITEM, item);
        startActivity(recapIntent);
    }

    @Override
    public void setFeed(Feed feed) {
        adapter = new RecapAdapter(this, feed.getItems());
        setListAdapter(adapter);
    }

    @Override
    public void handleError(String message) {

    }
}
