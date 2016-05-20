package com.example.shaina.brachoscounter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    final int BRACHOS_REQUEST_CODE=1;
    ArrayList<String> brachosDescriptions;
    ArrayList<Integer> brachosNumbers;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==BRACHOS_REQUEST_CODE && resultCode==RESULT_OK){
            brachosDescriptions=data.getStringArrayListExtra("BRACHOS_DESCRIPTIONS");
            brachosNumbers=data.getIntegerArrayListExtra("BRACHOS_NUMBERS");
            Toast.makeText (
                    getApplicationContext (),
                    brachosDescriptions.toString()+"", Toast.LENGTH_SHORT)
                    .show ();
        }
    }

    public void launchDaveningPage(View view) {
        Intent intent = new Intent(this, DaveningActivity.class);
        startActivityForResult(intent, BRACHOS_REQUEST_CODE);
    }
}
