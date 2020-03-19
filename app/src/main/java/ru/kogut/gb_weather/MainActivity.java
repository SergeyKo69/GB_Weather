package ru.kogut.gb_weather;

import androidx.appcompat.app.AppCompatActivity;
import ru.kogut.gb_weather.activitystate.RestoreActivity;
import ru.kogut.gb_weather.service.WeatherService;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private String TAG = "Life cycle";

    private WeatherService weatherService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initWeatherServivce();
        final RestoreActivity restoreActivity = RestoreActivity.getInstance();
        if (restoreActivity.getDegree() == null) {
            restoreActivity.setDegree(weatherService.getDegree());
        }
        initDegrees();
        String message;
        if (savedInstanceState == null) {
            message = "Первый запуск";
        } else {
            message = "Повторный запуск";
        }
        Toast.makeText(getApplicationContext(), message + " - onCreate()", Toast.LENGTH_SHORT).show();
        Log.d(TAG, message + " - onCreate()");
    }

    private void initWeatherServivce() {
        weatherService = new WeatherService();
    }

    private void initDegrees() {
        TextView txtDegrees = findViewById(R.id.txtDegrees);
        if (txtDegrees != null) {
            final RestoreActivity restoreActivity = RestoreActivity.getInstance();
            txtDegrees.setText(String.valueOf(restoreActivity.getDegree()));
        }
        TextView txtDegreesLand = findViewById(R.id.txtDegreesLand);
        if (txtDegreesLand != null) {
            final RestoreActivity restoreActivity = RestoreActivity.getInstance();
            txtDegreesLand.setText(String.valueOf(restoreActivity.getDegree()));
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(getApplicationContext(), "onStart()", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "onStart()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(getApplicationContext(), "onStop()", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(getApplicationContext(), "onDestroy()", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "onDestroy()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(getApplicationContext(), "onPause()", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "onPause()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(getApplicationContext(), "onResume()", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "onResume()");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Toast.makeText(getApplicationContext(), "Повторный запуск - onRestoreInstanceState()", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "Повторный запуск - onRestoreInstanceState()");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Toast.makeText(getApplicationContext(), "onRestart()", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "onRestart()");
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        Toast.makeText(getApplicationContext(), "onSaveInstanceState()", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "onSaveInstanceState()");
    }
}
