package ru.kogut.gb_weather;

import androidx.appcompat.app.AppCompatActivity;
import ru.kogut.gb_weather.activitystate.RestoreActivity;
import ru.kogut.gb_weather.enumeration.ActivityEnum;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.Objects;

public class CityActivity extends AppCompatActivity {

    EditText txtSearch;
    CheckBox checkPressureAndSpeed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city);
        initTextVariable();
        initBooleanVariable();
        fillBooleanVariable();
        fillTextVariable();
        initButton();
    }

    private void fillBooleanVariable() {
        final RestoreActivity  restoreActivity = RestoreActivity.getInstance();
        checkPressureAndSpeed.setChecked(restoreActivity.getPressureAndSpeed());
    }

    private void initBooleanVariable() {
        checkPressureAndSpeed = findViewById(R.id.checkViewPressureAndSpeed);
        checkPressureAndSpeed.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                final RestoreActivity restoreActivity = RestoreActivity.getInstance();
                restoreActivity.setPressureAndSpeed(checkPressureAndSpeed.isChecked());
            }
        });
    }

    private void initButton() {
        initChooseCityButton();
        initBackButton();
    }

    private void initChooseCityButton() {
        ImageView button = findViewById(R.id.chooseCity);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentResult = new Intent();
                intentResult.putExtra(ActivityEnum.CITY.name(), txtSearch.getText().toString());
                setResult(RESULT_OK, intentResult);
                finish();
           }
        });
    }

    private void fillTextVariable() {
        String city = Objects.requireNonNull(getIntent().getExtras()).getString(ActivityEnum.MAIN_ACTIVITY.name());
        txtSearch.setText(city);
    }

    private void initTextVariable() {
        txtSearch = findViewById(R.id.txtSearch);
    }

    private void initBackButton() {
        ImageView back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
