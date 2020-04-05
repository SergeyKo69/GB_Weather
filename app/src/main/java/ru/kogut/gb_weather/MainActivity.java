package ru.kogut.gb_weather;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import ru.kogut.gb_weather.activitystate.TemporaryDatas;
import ru.kogut.gb_weather.adapter.RecyclerDaysDataAdapter;
import ru.kogut.gb_weather.enumeration.ActivityEnum;
import ru.kogut.gb_weather.fragment.CityFragment;
import ru.kogut.gb_weather.fragment.PressureAndSpeedFragment;
import ru.kogut.gb_weather.fragment.SettingsFragment;
import ru.kogut.gb_weather.interfaces.UpdateValuesInt;
import ru.kogut.gb_weather.observer.IObserver;
import ru.kogut.gb_weather.observer.Publisher;
import ru.kogut.gb_weather.service.WeatherService;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.Objects;

public class MainActivity extends AppCompatActivity implements IObserver, UpdateValuesInt {

    private String TAG = "Life cycle";

    private final static int REQUEST_CODE = 1;
    private final String CITY_DEFAULT = "Moscow";

    private WeatherService weatherService;

    private TextView txtCity;
    private TextView txtDegrees;
    private Fragment pressureAndSpeedFragment;
    private Fragment cityFragment;
    private Fragment settingsFragment;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initWeatherServivce();
        initRecyclerView();
        final TemporaryDatas restoreActivity = TemporaryDatas.getInstance();
        if (restoreActivity.getDegree() == null) {
            if (restoreActivity.getCity() == null) {
                restoreActivity.setCity(CITY_DEFAULT);
            }
            weatherService.getDegree(restoreActivity.getCity(), getAssets(),this);
        }
        initTextLabels();
        initBtn();
        initDegrees();
        initFragments();
        String message;
        if (savedInstanceState == null) {
            message = "Первый запуск";
        } else {
            message = "Повторный запуск";
        }
        Toast.makeText(getApplicationContext(), message + " - onCreate()", Toast.LENGTH_SHORT).show();
        Log.d(TAG, message + " - onCreate()");
    }


    private void initFragments() {
        updatePressureSpeed();
    }

    private void initBtn() {
        initBtnCity();
        initBtnSettings();
     }

    private void initView() {
        updatePressureSpeed();
    }

    private void initPressureAndSpeed() {
        if (this.pressureAndSpeedFragment == null) {
            this.pressureAndSpeedFragment = new PressureAndSpeedFragment();
        }
        replaceFragment(R.id.fragment_container_pressure, pressureAndSpeedFragment);
    }

    private void removeFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.remove(fragment);
        fragmentTransaction.commit();
        this.pressureAndSpeedFragment = null;
    }

    private void replaceFragment(int idView, Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(idView, fragment);
        fragmentTransaction.commit();
    }

    private void replaceFragmentAndAddBackStack(int idView, Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(idView, fragment);
        fragmentTransaction.addToBackStack("");
        fragmentTransaction.commit();
    }

    private void initBtnSettings() {
        MaterialButton btnSettings = findViewById(R.id.checkSettings);
        btnSettings.setOnClickListener(view -> {
            settingsFragment = new SettingsFragment();
            replaceFragmentAndAddBackStack(R.id.fragment_container_settings, settingsFragment);
            Publisher publisher = Publisher.getInstance();
            publisher.subscribe(MainActivity.this);
        });
    }

    private void initTextLabels() {
        txtCity = findViewById(R.id.city);
        txtCity.setOnClickListener(view -> {
            String uriStr = String.format("https://ru.wikipedia.org/wiki/%s", txtCity.getText().toString());
            Uri uri = Uri.parse(uriStr);
            Intent runEchoIntent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(runEchoIntent);
        });
    }

    public void initBtnCity() {
        MaterialButton btnCity = findViewById(R.id.btnCheckCity);
        btnCity.setOnClickListener(view -> {
            cityFragment = new CityFragment();
            replaceFragmentAndAddBackStack(R.id.fragment_container_city, cityFragment);
            Publisher publisher = Publisher.getInstance();
            publisher.subscribe(MainActivity.this);
        });
    }

    private void initWeatherServivce() {
        weatherService = new WeatherService();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void initDegrees() {
        txtDegrees = findViewById(R.id.txtDegrees);
        if (txtDegrees != null) {
            final TemporaryDatas restoreActivity = TemporaryDatas.getInstance();
            if (restoreActivity.getDegree() ==  null) {
                weatherService.getDegree(restoreActivity.getCity(), getAssets(), this);
            } else {
                txtDegrees.setText(String.valueOf(restoreActivity.getDegree()));
            }
            if (restoreActivity.getCity() != null) {
                txtCity.setText(restoreActivity.getCity());
            }
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
        initView();
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
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        Toast.makeText(getApplicationContext(), "onSaveInstanceState()", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "onSaveInstanceState()");
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode != REQUEST_CODE) {
            super.onActivityResult(requestCode, resultCode, data);
            return;
        }

        if (resultCode == RESULT_OK) {
            assert data != null;
            updateCity(data.getStringExtra(ActivityEnum.CITY.name()));
            updateDegrees();
        }
    }

    private void updateCity(String city) {
        txtCity = findViewById(R.id.city);
        txtCity.setText(city);
    }

    @Override
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void updateDegrees() {
        final TemporaryDatas restoreActivity = TemporaryDatas.getInstance();
        if (txtDegrees != null) {
            txtDegrees.setText(String.valueOf(restoreActivity.getDegree()));
            if (txtDegrees.getText().equals("-999")) {
                Toast.makeText(getApplicationContext(), "Ошибка получения данных о погоде", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onBackPressed() {
        if(getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void update() {
        final TemporaryDatas restoreActivity = TemporaryDatas.getInstance();
        if (restoreActivity.getCity() != null) {
            updateCity(restoreActivity.getCity());
        }
        weatherService.getDegree(restoreActivity.getCity(), getAssets(), this);
        updatePressureSpeed();
        Publisher publisher = Publisher.getInstance();
        publisher.unsubscribe(this);
    }

    private void updatePressureSpeed() {
        final TemporaryDatas value = TemporaryDatas.getInstance();
        if (value.getPressureAndSpeed() && this.pressureAndSpeedFragment == null) {
            initPressureAndSpeed();
        } else {
            if (this.pressureAndSpeedFragment != null) {
                removeFragment(this.pressureAndSpeedFragment);
            }
        }
    }

    private void initRecyclerView() {
        final String[] listData = weatherService.findDegreesToDays();
        RecyclerView recyclerView = findViewById(R.id.recyclerToDays);
        RecyclerDaysDataAdapter adapter = new RecyclerDaysDataAdapter(listData);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        DividerItemDecoration itemDecoration = new DividerItemDecoration(this,  LinearLayoutManager.HORIZONTAL);
        itemDecoration.setDrawable(Objects.requireNonNull(getDrawable(R.drawable.separator)));
        recyclerView.addItemDecoration(itemDecoration);
   }
}
