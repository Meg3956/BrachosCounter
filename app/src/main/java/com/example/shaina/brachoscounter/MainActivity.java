package com.example.shaina.brachoscounter;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;

//import com.google.gson.Gson;

//TODO: DEAL With LG user crash?
public class MainActivity extends AppCompatActivity {

    private final static String sPREFS_FIELDS = "PREFS_FIELDS";
    private final static String sBRACHOS_DESCRIPTION="BRACHOS_DESCRIPTIONS";
    private final static String sBRACHOS_NUMBERS="BRACHOS_NUMBERS";
    final int SINGLE_BRACHOS_REQUEST_CODE = 1;
    final int MULTI_BRACHOS_REQUEST_CODE = 2;
    final int MULTI_BRACHOS_MULTIPLE_REQUEST_CODE = 3;
    ArrayList<String> brachosDescriptions;
    ArrayList<Integer> brachosNumbers;
    ArrayList<String> brachosDescriptionsToAdd;
    ArrayList<Integer> brachosNumbersToAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        brachosDescriptions = new ArrayList<>();
        brachosNumbers = new ArrayList<>();
        brachosDescriptionsToAdd=new ArrayList<>();
        brachosNumbersToAdd = new ArrayList<>();


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case MULTI_BRACHOS_MULTIPLE_REQUEST_CODE:
                    ArrayList<Integer> numbers = data.getIntegerArrayListExtra("BRACHOS_NUMBERS");
                    if (!numbers.isEmpty()) {
                        brachosDescriptionsToAdd.addAll(data.getStringArrayListExtra("BRACHOS_DESCRIPTIONS"));
                        brachosNumbersToAdd.addAll(numbers);
                    }
                    break;
                case MULTI_BRACHOS_REQUEST_CODE:
                    int number = data.getIntExtra("BRACHOS_NUMBER", 1);
                    if (number > 0) {
                        brachosDescriptionsToAdd.add(data.getStringExtra("BRACHOS_DESCRIPTION"));
                        brachosNumbersToAdd.add(number);
                    }
                    break;
                case SINGLE_BRACHOS_REQUEST_CODE:
                    break;
            }
        }

    }

    public void launchDaveningPage(View view) {
        Intent intent = new Intent(this, DaveningActivity.class);
        startActivityForResult(intent, MULTI_BRACHOS_MULTIPLE_REQUEST_CODE);
    }

    public void launchAddYourOwnPage(View view) {
        Intent intent = new Intent(this, AddYourOwnActivity.class);
        startActivityForResult(intent, MULTI_BRACHOS_REQUEST_CODE);
    }

    public void launchFoodDrink(View view) {
        Intent intent = new Intent(this, FoodDrinkActivity.class);
        String[] foodBrachos = {"Hamotzi", "Mezonos", "Hagafen", "Haetz", "Ha'adama", "Shehakol",
                "Birkas Hamazon", "Al Hamichya", "Borei Nefashos"};
        intent.putExtra(getString(R.string.brachosList), foodBrachos);
        startActivityForResult(intent, MULTI_BRACHOS_REQUEST_CODE);
    }

    @Override
    protected void onStart() {
        super.onStart();
       // restorePreferencesSavedFromSettingsActivity();
        restoreNonSettingsActivityPreferences();
        addBrachosFromRestoredActivity();

    }

    //TODO: Do we need an onResume to restore things?
    @Override
    protected void onResume() {
        super.onResume();
       /* applyNightModePreference();
        showHideBackground ();*/
    }

    @Override
    protected void onStop() {
        super.onStop();
        saveNonSettingsActivityPreferences();
    }

    private void restorePreferencesSavedFromSettingsActivity() {
        String currentKey;
        String currentDefaultValue;

        // Get handle to custom preferences (not from settings menu)
        // Used for persisting state to storage

        // First, get handle to user settings/preferences
     //   SharedPreferences defaultSharedPreferences =
           //     PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        //TODO: Set to our preferences!!!
        // Show Background Picture Preference
       /* currentKey = getString(R.string.showBackgroundKey);
        mUsePicBackground = defaultSharedPreferences.getBoolean(currentKey, true);*/
    }


    private void restoreNonSettingsActivityPreferences() {
        //TODO: Set to our lists.


        SharedPreferences settings = getSharedPreferences (sPREFS_FIELDS, MODE_PRIVATE);
        String descriptionString=settings.getString(sBRACHOS_DESCRIPTION,"");
        if (!descriptionString.isEmpty()){
            brachosDescriptions=(ArrayList<String>)restoreListFromJSON(descriptionString);

            brachosNumbers=(ArrayList<Integer>)restoreListFromJSON(settings.getString(sBRACHOS_NUMBERS, "[]"));



        }

       /* Toast.makeText(
                getApplicationContext(),
                brachosDescriptions.toString() + "", Toast.LENGTH_SHORT)
                .show();*/

       /*
        String numbersString=settings.getString(sBRACHOS_NUMBERS,"");
        brachosNumbers=(ArrayList<Integer>)restoreListFromJSON(numbersString);
        brachosDescriptions=(ArrayList<String>)restoreListFromJSON(descriptionString);*/
       /*  String currentString;
      //MP==0

       currentString =
                settings.getString (mSUBTOTAL_PREF_KEY, mSubTotalField.getText ().toString ());
        mSubTotalField.setText (currentString);

        currentString = settings.getString (mPAYERS_PREF_KEY, mPayersField.getText ().toString ());
        mPayersField.setText (currentString);
*/
    }

    private void saveNonSettingsActivityPreferences() {
        SharedPreferences settings = getSharedPreferences(sPREFS_FIELDS, MODE_PRIVATE); //MP==0
        SharedPreferences.Editor settingsEditor = settings.edit();

        settingsEditor.clear();

        String jsonBrachosDescriptions = getJSON(brachosDescriptions);
        String jsonBrachosNumbers=getJSON(brachosNumbers);
        settingsEditor.putString(sBRACHOS_DESCRIPTION,jsonBrachosDescriptions);
        settingsEditor.putString(sBRACHOS_NUMBERS,jsonBrachosNumbers);
        // Tax and tip are derived from values stored automatically via Settings Activity
        // So we need to store only the other two EditTexts
        //TODO: add our prefs
     /*
        */
        // settingsEditor.putString (mSUBTOTAL_PREF_KEY, mSubTotalField.getText ().toString ());
        //settingsEditor.putString (mPAYERS_PREF_KEY, mPayersField.getText ().toString ());

        settingsEditor.apply();
    }

    private String getJSON(ArrayList obj) {
        Gson gson = new Gson();
        String json = gson.toJson(obj);


        return json;
    }

    private ArrayList restoreListFromJSON(String json){
        Gson gson = new Gson();
        ArrayList obj = gson.fromJson(json, ArrayList.class);
        return obj;
    }

    private void addBrachosFromRestoredActivity(){
        brachosDescriptions.addAll(brachosDescriptionsToAdd);
        brachosDescriptionsToAdd.clear();

       brachosNumbers.addAll(brachosNumbersToAdd);
        brachosNumbersToAdd.clear();
    }

    public void viewTotalBrachos(View view) {
        int counter=0;
        for (Integer brachosNumber : brachosNumbers) {
            counter+=brachosNumber;
        }
      /*  Toast.makeText(
                getApplicationContext(),
                brachosNumbers.toString() + "", Toast.LENGTH_SHORT)
                .show();*/
        Toast.makeText(
                getApplicationContext(),
                counter + "", Toast.LENGTH_SHORT)
                .show();
    }

    public void clearBrachos(View view) {
        //call addBrachosFromRestoredActivity to flush the 'ToAdd' ArrayLists if click before they are flushed
        addBrachosFromRestoredActivity();
        brachosDescriptions.clear();
        brachosNumbers.clear();
    }


   /* public <T> void setList(String key, List<T> list)
    {
        Gson gson = new Gson();
        String json = gson.toJson(list);

        set(key, json);
    }*/
}
