package ru.kogut.gb_weather;

import androidx.appcompat.app.AppCompatActivity;
import ru.kogut.gb_weather.activitystate.RestoreActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;

public class SettingsActivity extends AppCompatActivity {

    Switch swTheme;
    Switch swDergreeFahrenheit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        initButtons();
        initSw();
        fillSw();
    }

    private void fillSw() {
        final RestoreActivity restoreActivity = RestoreActivity.getInstance();
        swTheme.setChecked(restoreActivity.isSetTheme());
        swDergreeFahrenheit.setChecked(restoreActivity.isSetDergreeFahrenheit());
    }

    private void initSw() {
        swTheme();
        swDF();
    }

    private void swDF() {
        swDergreeFahrenheit = findViewById(R.id.swDergreeFahrenheit);
        swDergreeFahrenheit.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                final RestoreActivity restoreActivity = RestoreActivity.getInstance();
                restoreActivity.setSetDergreeFahrenheit(swDergreeFahrenheit.isChecked());
            }
        });
    }

    private void swTheme() {
        swTheme = findViewById(R.id.swTheme);
        swTheme.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                final RestoreActivity restoreActivity = RestoreActivity.getInstance();
                restoreActivity.setSetTheme(swTheme.isChecked());
            }
        });

    }

    private void initButtons() {
        initBtnBack();
    }

    private void initBtnBack() {
        ImageView btnBack = findViewById(R.id.back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
