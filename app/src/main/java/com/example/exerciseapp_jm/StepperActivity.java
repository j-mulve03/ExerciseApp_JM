package com.example.exerciseapp_jm;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class StepperActivity extends AppCompatActivity implements SensorEventListener {
    private SensorManager sensorManager;
    private Sensor stepSensor;
    private boolean running = false;
    ImageButton back;
    TextView tv_stepsTaken;
//    TextView calSpent = findViewById(R.id.calories);
//    TextView distTravelled = findViewById(R.id.distance);
    private float totalSteps = 0f;
    private float previousTotalSteps = 0f;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.step_screen);
        tv_stepsTaken = findViewById(R.id.currentsteps);
        running = true;
        back = findViewById(R.id.backButton);

            loadData();
//            calcDistance();

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        stepSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);

        back.setOnClickListener(v -> {
            Intent intent = new Intent(StepperActivity.this, CalenderActivity.class);

            startActivity(intent);
        });
    }



    @Override
    protected void onResume() {
        super.onResume();
        running = true;

        sensorManager.registerListener(this, stepSensor, SensorManager.SENSOR_DELAY_NORMAL);

        if (stepSensor == null) {
            Toast.makeText(this, "No sensor detected on this device", Toast.LENGTH_SHORT).show();
        } else {
            sensorManager.registerListener(this, stepSensor, SensorManager.SENSOR_DELAY_UI);
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        if (running) {
            totalSteps = event.values[0];
            int currentSteps = (int) (totalSteps - previousTotalSteps);
            double calories = calcCalories();
            double dist = calcDistance();

//            calSpent.setText(calories + " calories");
            tv_stepsTaken.setText(currentSteps + "/10,000");
//            distTravelled.setText(dist + "metres");
        }
    }

    private void saveData() {
        Context context = getApplicationContext();
        SharedPreferences sharedPreferences = context.getSharedPreferences("myPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putFloat("key1", previousTotalSteps);
        editor.apply();

    }

    private void loadData() {
        Context context = getApplicationContext();
        SharedPreferences sharedPreferences = context.getSharedPreferences("myPrefs", Context.MODE_PRIVATE);
        float savedNumber = sharedPreferences.getFloat("key1", 0f);
        Log.d("StepperActivity", String.valueOf(savedNumber));
        previousTotalSteps = savedNumber;
    }

    public double calcCalories(){
        double cal = totalSteps * 0.063;
        return cal;
    }

    public double calcDistance(){
        double distance = 0;
        return distance;
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // We do not have to write anything in this function for this app
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}