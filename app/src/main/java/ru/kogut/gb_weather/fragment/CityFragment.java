package ru.kogut.gb_weather.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.Objects;

import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import ru.kogut.gb_weather.R;
import ru.kogut.gb_weather.activitystate.TemporaryDatas;
import ru.kogut.gb_weather.adapter.RecyclerCityDataAdapter;
import ru.kogut.gb_weather.observer.Publisher;
import ru.kogut.gb_weather.service.WeatherService;


public class CityFragment extends Fragment {

    private WeatherService weatherService;
    private Publisher publisher;
    private TemporaryDatas temporaryDatas;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_city, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final MaterialButton btnBackCity = view.findViewById(R.id.backCity);
        this.weatherService = new WeatherService();
        this.publisher = Publisher.getInstance();
        this.temporaryDatas = TemporaryDatas.getInstance();
        initRecyclerView(view);
        btnBackCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBack();
            }
        });
    }

    private void onBack() {
        FragmentManager fm = Objects.requireNonNull(getActivity()).getSupportFragmentManager();
        if (fm.getBackStackEntryCount() > 0) {
            fm.popBackStack();
        } else {
            getActivity().finish();
        }
    }

    private void initRecyclerView(View view) {
        final String[] listData = weatherService.findAllCities();
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        RecyclerCityDataAdapter adapter = new RecyclerCityDataAdapter(listData);
        LinearLayoutManager layoutManager = new LinearLayoutManager(Objects.requireNonNull(getActivity()).getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        adapter.SetOnItemClickListener(new RecyclerCityDataAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                String city = ((TextView) view).getText().toString();
                Snackbar.make(view, "Выбран горд: " + city, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                temporaryDatas.setCity(city);
                publisher.notifyMain();
                onBack();
            }
        });
    }
}
