package com.wojdera.Material_Recaps.Views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.wojdera.Material_Recaps.Models.Item;
import com.wojdera.Material_Recaps.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by wojder on 02.12.14.
 */
public class RecapAdapter extends ArrayAdapter<Item>{

    private DateFormat dateFormat = SimpleDateFormat.getDateInstance(SimpleDateFormat.MEDIUM, Locale.getDefault());

    public RecapAdapter(Context context, List<Item> objects) {
        super(context, 0, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;

        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            view = inflater.inflate(R.layout.recaps_list_view, parent, false);
        } else {
            view = convertView;
        }

        ViewHolder viewHolder = (ViewHolder) view.getTag();
        if (viewHolder == null) {
            TextView title = (TextView) view.findViewById(R.id.feed_item_title);
            TextView description = (TextView) view.findViewById(R.id.feed_item_description);
            TextView date = (TextView) view.findViewById(R.id.feed_item_date);
            viewHolder = new ViewHolder(title, description, date);
            view.setTag(viewHolder);
        }
        Item item = getItem(position);
        viewHolder.setTitle(item.getTitle());
//        viewHolder.setDescription(Html.fromHtml(item.getDescription()));
        viewHolder.setDate(dateFormat.format(new Date(item.getDate())));

        return view;
    }

    private static final class ViewHolder {

        private final TextView title;
        private final TextView description;
        private final TextView date;

        private ViewHolder(TextView title, TextView description, TextView date) {
            this.title = title;
            this.description = description;
            this.date = date;
        }

        public void setTitle(CharSequence text) {
            title.setText(text);
        }
        public void setDescription(CharSequence text){
            description.setText(text);
        }

        public void setDate(CharSequence text) {
            date.setText(text);
        }
    }
}
