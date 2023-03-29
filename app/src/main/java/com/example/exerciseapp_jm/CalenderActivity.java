package com.example.exerciseapp_jm;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.navigation.NavigationBarView;

public class CalenderActivity extends AppCompatActivity {

    ImageButton back;
    Spinner actSpin;
    Spinner minSpin;
    Spinner hourSpin;
    String[] activities = {"act1", "act2", "act3"};
    String[] hours = new String[24];
    String[] minutes = new String[60];

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calender_screen);

        //Screen Switch Code
        back = findViewById(R.id.backButton);
        back.setOnClickListener(v -> {
            Intent intent = new Intent(CalenderActivity.this, StepperActivity.class);

            startActivity(intent);
        });

        //Spinner Code
        actSpin = findViewById(R.id.activityspinner);
        hourSpin = findViewById(R.id.hourspinner);
        minSpin = findViewById(R.id.minspinner);

//        actSpin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(getApplicationContext(), activities[position], Toast.LENGTH_LONG).show();
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });

//        hourSpin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(getApplicationContext(),hours[position], Toast.LENGTH_LONG).show();
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });

//        minSpin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(getApplicationContext(),minutes[position],Toast.LENGTH_LONG).show();
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });

//        ArrayAdapter aAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,activities);
//        aAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        ArrayAdapter hourAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,hours);
//        hourAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//
//        ArrayAdapter minAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,minutes);
//        minAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
