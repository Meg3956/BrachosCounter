package com.example.shaina.brachoscounter;

import android.content.Context;
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

    private final Context mCONTEXT;
    private final String[] mBRACHOS;

    public BrachosAdapter(Context context, String[] values) {
        super(context, R.layout.listview_row, values);
        mCONTEXT = context;
        mBRACHOS = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) mCONTEXT.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.listview_row, parent, false);
        TextView textView = (TextView) rowView.findViewById(R.id.brachaOption);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
        textView.setText(mBRACHOS[position]);

       /* // Change the icon for Windows and iPhone
        String s = values[position];
        if (s.startsWith("Windows7") || s.startsWith("iPhone")
                || s.startsWith("Solaris")) {
            imageView.setImageResource(R.drawable.no);
        } else {
            imageView.setImageResource(R.drawable.ok);
        } */

        return rowView;
    }
}
