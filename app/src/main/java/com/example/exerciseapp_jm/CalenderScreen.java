package com.example.exerciseapp_jm;

import static android.app.ProgressDialog.show;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class CalenderScreen extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    String[] activities = {"act1", "act2", "act3"};
    String[] hours = new String[24];
    String[] minutes = new String[60];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calender_screen_frag);
        Spinner actspin = (Spinner) findViewById(R.id.activityspinner);
        Spinner hourspin = (Spinner) findViewById(R.id.hourspinner);
        Spinner minspin = (Spinner) findViewById(R.id.minspinner);

        actspin.setOnItemSelectedListener(this);
        hourspin.setOnItemSelectedListener(this);
        minspin.setOnItemSelectedListener(this);

        ArrayAdapter aAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,activities);
        aAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter hourAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,hours);
        hourAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter minAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,minutes);
        minAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch(parent.getId()){
            case R.id.activityspinner:
                Toast.makeText(getApplicationContext(), activities[position], Toast.LENGTH_LONG).show();
                break;
            case R.id.hourspinner:
                Toast.makeText(getApplicationContext(),hours[position], Toast.LENGTH_LONG).show();
                break;
            case R.id.minspinner:
                Toast.makeText(getApplicationContext(),minutes[position],Toast.LENGTH_LONG).show();
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
