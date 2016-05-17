package com.example.shaina.brachoscounter;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

//DEAL WITH OPTIONS
public class DaveningActivity extends Activity {
    DaveningCategoriesAdapter listAdapter;
    ExpandableListView expListView;
    ArrayList<String> daveningCategoryNames;

    HashMap<String, List<String>> brachosNames;
    HashMap<String, List<Integer>> brachosNumbers;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_davening);
        // get the listview
        expListView = (ExpandableListView) findViewById (R.id.lvExp);

        // preparing list data
        prepareListData ();

        listAdapter = new DaveningCategoriesAdapter (this, daveningCategoryNames,
                brachosNames,
                brachosNumbers);

        // setting list adapter
        expListView.setAdapter (listAdapter);

        // Listview Group click listener
        expListView.setOnGroupClickListener (new ExpandableListView.OnGroupClickListener()
        {

            @Override
            public boolean onGroupClick (ExpandableListView parent, View v,
                                         int groupPosition, long id)
            {
                // Toast.makeText(getApplicationContext(),
                // "Group Clicked " + daveningCategoryNames.get(groupPosition),
                // Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        // Listview Group expanded listener
        expListView.setOnGroupExpandListener (new ExpandableListView.OnGroupExpandListener()
        {

            @Override
            public void onGroupExpand (int groupPosition)
            {
                Toast.makeText (getApplicationContext (),
                        daveningCategoryNames.get (groupPosition) + " Expanded",
                        Toast.LENGTH_SHORT).show ();
            }
        });

        // Listview Group collasped listener
        expListView.setOnGroupCollapseListener (new ExpandableListView.OnGroupCollapseListener()
        {

            @Override
            public void onGroupCollapse (int groupPosition)
            {
                Toast.makeText (getApplicationContext (),
                        daveningCategoryNames.get (groupPosition) + " Collapsed",
                        Toast.LENGTH_SHORT).show ();

            }
        });

        // Listview on child click listener
        expListView.setOnChildClickListener (new ExpandableListView.OnChildClickListener()
        {

            @Override
            public boolean onChildClick (ExpandableListView parent, View v,
                                         int groupPosition, int childPosition, long id)
            {
                // TODO Auto-generated method stub
                Toast.makeText (
                        getApplicationContext (),
                        daveningCategoryNames.get (groupPosition)
                                + " : "
                                + brachosNames.get (
                                daveningCategoryNames.get (groupPosition)).get (
                                childPosition), Toast.LENGTH_SHORT)
                        .show ();
                return false;
            }
        });

        Button button = (Button) findViewById (R.id.button);
        final TextView textView = (TextView) findViewById (R.id.textView);


        button.setOnClickListener (new View.OnClickListener ()
        {
            public void onClick (View v)
            {
                int countOfItems = 0, sumOfNumericValueOfItems=0;
                for (int mGroupPosition = 0;
                     mGroupPosition < listAdapter.getGroupCount ();
                     mGroupPosition++) {

                    countOfItems+= listAdapter.getNumberOfCheckedItemsInGroup (mGroupPosition);
                    sumOfNumericValueOfItems+= listAdapter.getSumOfCheckedItemsInGroup (mGroupPosition);

                }
                textView.setText (getString(R.string.items_checked_colon) + countOfItems
                        + getString(R.string.semicolon)
                        + getString(R.string.sum_colon) + sumOfNumericValueOfItems);
            }
        });

    }
    private void prepareListData(){
        daveningCategoryNames = new ArrayList<String> ();
        // Add child data
        brachosNames = new HashMap<String, List<String>> ();
        // Header, Child numeric value data
        brachosNumbers = new HashMap<String, List<Integer>> ();
        prepareShachrisData();
        prepareMinchaData();
        prepareMaarivData();
        prepareHallelData();
        prepareMussafData();
    }

    private void prepareShachrisData(){

        daveningCategoryNames.add(getString(R.string.Shachris));

        ArrayList<String> brachos=new ArrayList<>();
        brachos.add(getString(R.string.Birchos_HaShachar));
        brachos.add(getString(R.string.Birchos_HaTorah));
        brachos.add(getString(R.string.Psukei_DZimra));
        brachos.add(getString(R.string.Birchos_Krias_Shema));
        brachos.add(getString(R.string.Shemoneh_Esrei));

        ArrayList<Integer> numbers=new ArrayList<>();
        numbers.add(getApplicationContext().getResources().getInteger(R.integer.Birchos_HaShachar));
        numbers.add(getApplicationContext().getResources().getInteger(R.integer.Birchos_HaTorah));
        numbers.add(getApplicationContext().getResources().getInteger(R.integer.Psukei_DZimra));
        numbers.add(getApplicationContext().getResources().getInteger(R.integer.Birchos_Krias_Shema));
        numbers.add(getApplicationContext().getResources().getInteger(R.integer.Shemoneh_Esrei));

        brachosNames.put(getString(R.string.Shachris),brachos);
        brachosNumbers.put(getString(R.string.Shachris),numbers);


    }
    private void prepareMinchaData(){
        daveningCategoryNames.add(getString(R.string.Mincha));

        ArrayList<String> brachos=new ArrayList<>();
        brachos.add(getString(R.string.Shemoneh_Esrei));

        ArrayList<Integer> numbers=new ArrayList<>();
        numbers.add(getApplicationContext().getResources().getInteger(R.integer.Shemoneh_Esrei));

        brachosNames.put(getString(R.string.Mincha),brachos);
        brachosNumbers.put(getString(R.string.Mincha),numbers);

    }
    private void prepareMaarivData(){

        daveningCategoryNames.add(getString(R.string.Maariv));

        ArrayList<String> brachos=new ArrayList<>();
        brachos.add(getString(R.string.Birchos_Krias_Shema));
        brachos.add(getString(R.string.Shemoneh_Esrei));

        ArrayList<Integer> numbers=new ArrayList<>();
        numbers.add(getApplicationContext().getResources().getInteger(R.integer.Birchos_Krias_Shema));
        numbers.add(getApplicationContext().getResources().getInteger(R.integer.Shemoneh_Esrei));

        brachosNames.put(getString(R.string.Maariv),brachos);
        brachosNumbers.put(getString(R.string.Maariv),numbers);
    }
    private void prepareHallelData(){

        daveningCategoryNames.add(getString(R.string.Hallel));

        ArrayList<String> brachos=new ArrayList<>();
        brachos.add(getString(R.string.Hallel));

        ArrayList<Integer> numbers=new ArrayList<>();
        numbers.add(getApplicationContext().getResources().getInteger(R.integer.Hallel));

        brachosNames.put(getString(R.string.Hallel),brachos);
        brachosNumbers.put(getString(R.string.Hallel),numbers);


    }

    private void prepareMussafData(){

        daveningCategoryNames.add(getString(R.string.Mussaf));

        ArrayList<String> brachos=new ArrayList<>();
        brachos.add(getString(R.string.Shemoneh_Esrei));

        ArrayList<Integer> numbers=new ArrayList<>();
        numbers.add(getApplicationContext().getResources().getInteger(R.integer.Shemoneh_Esrei));

        brachosNames.put(getString(R.string.Mussaf),brachos);
        brachosNumbers.put(getString(R.string.Mussaf),numbers);


    }


    }

