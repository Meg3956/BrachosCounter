package com.example.shaina.brachoscounter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Shaina on 5/17/2016.
 */
public class BrachosActivity extends AppCompatActivity {

    private BrachosAdapter mBrachosAdapter; // The adapter we used for this ListView
    protected String[] brachos;
    private ArrayList<String> mListOfCheckedItems; // ArrayList of items to be
    // passed to the adapter which will add selected items to the list

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putStringArrayList("CHECKED_ITEMS", mListOfCheckedItems);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hanehenin);
        initializeArrays(savedInstanceState);
        setupActionBar();
        processIncomingData();
        //processSavedState(savedInstanceState);
        setupListView();

// not relevant here
    }

    private void setupActionBar() {
        try {
            getDelegate().getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        } catch (NullPointerException nullPointerException) {
            //nullPointerException.printStackTrace();
        }
    }

    //I don't think we need this method... at least not as is
    private void processIncomingData() {
        Intent intent = getIntent();
        mListOfCheckedItems = intent.getStringArrayListExtra("CARDS_SHOWN");
    }

    private void initializeArrays(Bundle savedInstanceState) {
        // initialize the list to be put into the ListView
        String brachosList = null;
        getIntent().getStringArrayExtra(brachosList);

        // initialize the list to be passed into the Adapter
        // to hold the items whose respective buttons are clicked

        // if we have no saved bundle, then make a new list; else, use the ArrayList from bundle
        mListOfCheckedItems = (savedInstanceState == null ?
                new ArrayList<String>(18) :
                savedInstanceState.getStringArrayList("CHECKED_ITEMS"));
    }

    private void setupListView() {
        ListView list = (ListView) findViewById(R.id.listView);

        assert mListOfCheckedItems != null;
        mBrachosAdapter = new BrachosAdapter(this, brachos, R.layout.listview_row,
                R.id.brachaOption, R.id.addButton,
                mListOfCheckedItems);
        list.setAdapter(mBrachosAdapter);
    }

    /*private void setupFAB() {
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Items added: " + mListOfCheckedItems.size()
                        + "; Total items " + mBrachosAdapter.getCount() + ".", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    } */


    /*protected void onListItemClick(ListView l, View v, int position, long id) {
        String item = (String) getListAdapter().getItem(position);
        Toast.makeText(this, item + " selected", Toast.LENGTH_LONG).show();
    }*/


    //What is this for?
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
