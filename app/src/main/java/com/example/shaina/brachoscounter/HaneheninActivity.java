package com.example.shaina.brachoscounter;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by Shaina on 5/17/2016.
 */
public class HaneheninActivity extends Activity {

    ListView lview;
    BirchosHaneheninAdapter listAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hanehenin);
        lview = (ListView) findViewById(R.id.listView);
        ArrayAdapter adapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, planets);


    }
}
