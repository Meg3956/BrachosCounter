package com.example.shaina.brachoscounter;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Created by Shaina on 5/17/2016.
 */
public abstract class BrachosActivity extends ListActivity {

    ListView lview;
    protected String[] brachos;

    abstract String[] createArray(); //to force initialization in child classes?

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        brachos = createArray();
        BrachosAdapter adapter = new BrachosAdapter(this, brachos);

        // Assign adapter to ListView
        setListAdapter(adapter);

       /* lview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1,
                                    int position, long arg3) {

                // ListView Clicked item value
                String itemValue = (String) lview.getItemAtPosition(position);

                // Show Alert
                Toast.makeText(getApplicationContext(),
                        itemValue + ": ", Toast.LENGTH_LONG)
                        .show();
            }
        });*/
    }

    protected void onListItemClick(ListView l, View v, int position, long id) {
        String item = (String) getListAdapter().getItem(position);
        Toast.makeText(this, item + " selected", Toast.LENGTH_LONG).show();
    }


    //What is this for?
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //   getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}
