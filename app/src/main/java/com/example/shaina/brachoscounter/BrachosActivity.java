package com.example.shaina.brachoscounter;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Created by Shaina on 5/17/2016.
 */
public abstract class BrachosActivity extends Activity {

    ListView lview;
    BirchosHaneheninAdapter listAdapter;
    String[] brachos; //Should this be abstract with a constructor to force initialization in child classes?


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hanehenin);
        lview = (ListView) findViewById(R.id.listView);
        ArrayAdapter adapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, brachos);

        // Assign adapter to ListView
        lview.setAdapter(adapter);

        lview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1,
                                    int position, long arg3) {

                int itemPosition = position;

                // ListView Clicked item value
                String itemValue = (String) lview.getItemAtPosition(position);

                // Show Alert
                Toast.makeText(getApplicationContext(),
                        itemValue + ": ", Toast.LENGTH_LONG)
                        .show();
            }
        });
    }

    //What is this for?
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


}
