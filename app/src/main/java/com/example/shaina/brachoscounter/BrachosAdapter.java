package com.example.shaina.brachoscounter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Shaina on 5/16/2016.
 */
public class BrachosAdapter extends ArrayAdapter<String> {

    private final Activity mCONTEXT;
    private final String[] mBRACHOS;

    static class ViewHolder {
        public TextView text;
        public ImageView image;
    }

    public BrachosAdapter(Activity context, String[] values) {
        super(context, R.layout.listview_row, values);
        mCONTEXT = context;
        mBRACHOS = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = convertView;
        // reuse views
        if (rowView == null) {
            LayoutInflater inflater = mCONTEXT.getLayoutInflater();
            rowView = inflater.inflate(R.layout.listview_row, null);
            // configure view holder
            ViewHolder viewHolder = new ViewHolder();
            viewHolder.text = (TextView) rowView.findViewById(R.id.brachaOption);
            viewHolder.image = (ImageView) rowView
                    .findViewById(R.id.addSymbol);
            rowView.setTag(viewHolder);
        }

        // fill data
        ViewHolder holder = (ViewHolder) rowView.getTag();
        String s = mBRACHOS[position];
        holder.text.setText(s);

        return rowView;
    }
}

