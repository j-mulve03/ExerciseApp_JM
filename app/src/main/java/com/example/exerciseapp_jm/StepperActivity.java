package com.example.exerciseapp_jm;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class StepperActivity extends AppCompatActivity implements SensorEventListener {
    private SensorManager sensorManager;
    private boolean running = false;
    private float totalSteps = 0f;
    private float previousTotalSteps = 0f;

    public StepperActivity(){
        super(R.layout.step_screen_frag);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.step_main);

        loadData();
        resetSteps();
        calcDistance();

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        running = true;

        Sensor stepSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);

        if (stepSensor == null) {
            Toast.makeText(this, "No sensor detected on this device", Toast.LENGTH_SHORT).show();
        } else {
            sensorManager.registerListener(this, stepSensor, SensorManager.SENSOR_DELAY_UI);
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        TextView tv_stepsTaken = findViewById(R.id.currentsteps);
        TextView calSpent = findViewById(R.id.calories);

        if (running) {
            totalSteps = event.values[0];
            int currentSteps = (int) (totalSteps - previousTotalSteps);
            double calories = calcCalories();

            calSpent.setText(String.valueOf(calories) + " calories");
            tv_stepsTaken.setText(String.valueOf(currentSteps) + "/10,000");

        }
    }

    public void resetSteps() {
        TextView tv_stepsTaken = findViewById(R.id.tv_stepsTaken);
        tv_stepsTaken.setOnClickListener(v -> {
            Toast.makeText(this, "Long tap to reset steps", Toast.LENGTH_SHORT).show();
        });

        tv_stepsTaken.setOnLongClickListener(v -> {
            previousTotalSteps = totalSteps;
            tv_stepsTaken.setText("0");
            saveData();
            return true;
        });
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
        Log.d("StepActivity", String.valueOf(savedNumber));
        previousTotalSteps = savedNumber;
    }

    public double calcCalories(){
        double cal = totalSteps * 0.063;
        return cal;
    }

    public void calcDistance(){

        LocationListener locationListener = location -> {
            float distance = 0;
            Location previousLocation = null;

            for(;;) {
                if (previousLocation != null) {
                    distance = previousLocation.distanceTo(location);
                    float totalDistance = +distance;
                    previousLocation = location;
                    TextView distanceText = findViewById(R.id.distance);
                    distanceText.setText(totalDistance + "meters");
                } else {
                    previousLocation = location;
                }
            }
        };

        LocationManager locationmanager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (checkSelfPermission(Manifest.permission.DYNAMIC_RECEIVER_NOT_EXPORTED_PERMISSION) == PackageManager.PERMISSION_GRANTED) {
            locationmanager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // We do not have to write anything in this function for this app
    }
}