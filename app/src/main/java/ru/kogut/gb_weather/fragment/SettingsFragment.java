package ru.kogut.gb_weather.fragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import ru.kogut.gb_weather.R;
import ru.kogut.gb_weather.activitystate.TemporaryDatas;
import ru.kogut.gb_weather.observer.Publisher;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;

import java.util.Objects;

public class SettingsFragment extends Fragment {

    private Switch swTheme;
    private Switch swDergreeFahrenheit;
    private Switch swPressureSpeed;
    private ImageView btnBack;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_settings, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.swTheme = view.findViewById(R.id.swTheme);
        this.swDergreeFahrenheit = view.findViewById(R.id.swDergreeFahrenheit);
        this.btnBack = view.findViewById(R.id.back);
        this.swPressureSpeed = view.findViewById(R.id.swPressureSpeed);
        initButtons();
        initSw();
        fillSw();
    }

    private void fillSw() {
        final TemporaryDatas restoreActivity = TemporaryDatas.getInstance();
        swTheme.setChecked(restoreActivity.isSetTheme());
        swDergreeFahrenheit.setChecked(restoreActivity.isSetDergreeFahrenheit());
        swPressureSpeed.setChecked(restoreActivity.getPressureAndSpeed());
    }

    private void initSw() {
        swTheme();
        swDF();
        swPressureSpeed();
    }

    private void swPressureSpeed() {
        swPressureSpeed.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                final TemporaryDatas  temporaryDatas = TemporaryDatas.getInstance();
                temporaryDatas.setPressureAndSpeed(swPressureSpeed.isChecked());
            }
        });
    }

    private void swDF() {
        swDergreeFahrenheit.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                final TemporaryDatas restoreActivity = TemporaryDatas.getInstance();
                restoreActivity.setSetDergreeFahrenheit(swDergreeFahrenheit.isChecked());
            }
        });
    }

    private void swTheme() {
        swTheme.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                final TemporaryDatas restoreActivity = TemporaryDatas.getInstance();
                restoreActivity.setSetTheme(swTheme.isChecked());
            }
        });

    }

    private void initButtons() {
        initBtnBack();
    }

    private void initBtnBack() {
       btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                back();
                final Publisher publisher = Publisher.getInstance();
                publisher.notifyMain();
            }
        });
    }

    private void back() {
        FragmentManager fm = Objects.requireNonNull(getActivity()).getSupportFragmentManager();
        if (fm.getBackStackEntryCount() > 0) {
            fm.popBackStack();
        } else {
            getActivity().finish();
        }
    }

}
