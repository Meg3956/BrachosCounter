package com.example.shaina.brachoscounter;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;
//import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;

//TODO: DEAL With LG user crash?
public class MainActivity extends AppCompatActivity {

    private final static String sPREFS_FIELDS = "PREFS_FIELDS";
    final int SINGLE_BRACHOS_REQUEST_CODE=1;
    final int MULTI_BRACHOS_REQUEST_CODE=2;
    final int MULTI_BRACHOS_MULTIPLE_REQUEST_CODE=3;
    ArrayList<String> brachosDescriptions;
    ArrayList<Integer> brachosNumbers;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        brachosDescriptions=new ArrayList<>();
        brachosNumbers=new ArrayList<>();



    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode==RESULT_OK){
            switch (requestCode){
                case MULTI_BRACHOS_MULTIPLE_REQUEST_CODE:
                    ArrayList<Integer> numbers=data.getIntegerArrayListExtra("BRACHOS_NUMBERS");
                    if (!numbers.isEmpty()){
                        brachosDescriptions.addAll(data.getStringArrayListExtra("BRACHOS_DESCRIPTIONS"));
                        brachosNumbers.addAll(numbers);
                    }
                    break;
                case MULTI_BRACHOS_REQUEST_CODE:
                    int number=data.getIntExtra("BRACHOS_NUMBER",1);
                    if (number>0){
                        brachosDescriptions.add(data.getStringExtra("BRACHOS_DESCRIPTION"));
                        brachosNumbers.add(number);
                    }
                    break;
                case SINGLE_BRACHOS_REQUEST_CODE:
                    break;
            }
        }


        Toast.makeText (
                getApplicationContext (),
                brachosDescriptions.toString()+"", Toast.LENGTH_SHORT)
                .show ();
    }

    public void launchDaveningPage(View view) {
        Intent intent = new Intent(this, DaveningActivity.class);
        startActivityForResult(intent, MULTI_BRACHOS_MULTIPLE_REQUEST_CODE);
    }

    public void launchAddYourOwnPage(View view) {
        Intent intent = new Intent(this, AddYourOwnActivity.class);
        startActivityForResult(intent, MULTI_BRACHOS_REQUEST_CODE);
    }

    public void launchHanehenin(View view) {
        Intent intent = new Intent(this, FoodDrinkActivity.class);
        startActivityForResult(intent, SINGLE_BRACHOS_REQUEST_CODE);
    }
    @Override
    protected void onStart ()
    {
        super.onStart ();
        restorePreferencesSavedFromSettingsActivity();
        restoreNonSettingsActivityPreferences();
    }

    //TODO: Do we need an onResume to restore things?
    @Override
    protected void onResume ()
    {
        super.onResume ();
       /* applyNightModePreference();
        showHideBackground ();*/
    }

    @Override
    protected void onStop ()
    {
        super.onStop ();
        saveNonSettingsActivityPreferences();
    }

    private void restorePreferencesSavedFromSettingsActivity() {
        String currentKey;
        String currentDefaultValue;

        // Get handle to custom preferences (not from settings menu)
        // Used for persisting state to storage

        // First, get handle to user settings/preferences
        SharedPreferences defaultSharedPreferences =
                PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        //TODO: Set to our preferences!!!
        // Show Background Picture Preference
       /* currentKey = getString(R.string.showBackgroundKey);
        mUsePicBackground = defaultSharedPreferences.getBoolean(currentKey, true);*/
    }


    private void restoreNonSettingsActivityPreferences()
    {
        //TODO: Set to our lists.
       /*  String currentString;
        SharedPreferences settings = getSharedPreferences (sPREFS_FIELDS, MODE_PRIVATE); //MP==0

       currentString =
                settings.getString (mSUBTOTAL_PREF_KEY, mSubTotalField.getText ().toString ());
        mSubTotalField.setText (currentString);

        currentString = settings.getString (mPAYERS_PREF_KEY, mPayersField.getText ().toString ());
        mPayersField.setText (currentString);
*/
    }
    private void saveNonSettingsActivityPreferences()
    {
        SharedPreferences settings = getSharedPreferences (sPREFS_FIELDS, MODE_PRIVATE); //MP==0
        SharedPreferences.Editor settingsEditor = settings.edit ();

        settingsEditor.clear ();

        // Tax and tip are derived from values stored automatically via Settings Activity
        // So we need to store only the other two EditTexts
        //TODO: add our prefs
       // settingsEditor.putString (mSUBTOTAL_PREF_KEY, mSubTotalField.getText ().toString ());
        //settingsEditor.putString (mPAYERS_PREF_KEY, mPayersField.getText ().toString ());

        settingsEditor.apply ();
    }

   /* public <T> void setList(String key, List<T> list)
    {
        Gson gson = new Gson();
        String json = gson.toJson(list);

        set(key, json);
    }*/
}
