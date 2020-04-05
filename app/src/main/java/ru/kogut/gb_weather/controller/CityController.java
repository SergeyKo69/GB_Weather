package ru.kogut.gb_weather.controller;

import android.content.res.AssetManager;
import android.os.Build;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Properties;
import java.util.stream.Collectors;

import androidx.annotation.RequiresApi;
import ru.kogut.gb_weather.model.ModelCity;

/**
 * Created by Sergey Kogut on 05.04.2020.
 */
public class CityController {

    private final String PROPERTY_FILE_NAME = "main.properties";
    private final String PROPERTY_CITY_NAME = "file_name_city_list";

    @RequiresApi(api = Build.VERSION_CODES.N)
    public String[] getAllCityJSON(AssetManager assetManager) {
        Properties properties = new Properties();
        try {
            properties.load(assetManager.open(PROPERTY_FILE_NAME));

                BufferedReader br = new BufferedReader(new InputStreamReader(assetManager.open(properties.getProperty(PROPERTY_CITY_NAME))));
                String jsonText = br.lines().collect(Collectors.joining("\n"));
                Type listType = new TypeToken<ArrayList<ModelCity>>(){}.getType();
                final ArrayList<ModelCity> modelCityListResult = new Gson().fromJson(jsonText, listType);
                if (modelCityListResult != null && modelCityListResult.size() > 0) {
                    String[] result = new String[modelCityListResult.size()];
                    for (int i = 0; i < modelCityListResult.size(); i++) {
                        result[i] = modelCityListResult.get(i).getName();
                    }
                     return result;
                }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new String[]{"Нет данных"};
    }
}
