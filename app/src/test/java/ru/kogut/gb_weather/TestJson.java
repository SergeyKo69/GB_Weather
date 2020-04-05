package ru.kogut.gb_weather;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.junit.Assert;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

import ru.kogut.gb_weather.controller.CityController;
import ru.kogut.gb_weather.model.ModelCity;

/**
 * Created by Sergey Kogut on 05.04.2020.
 */
public class TestJson {

    private final String PROPERTY_FILE_NAME = "main.properties";

    @Test
    public void testJsonCity() throws IOException {
        Properties properties = new Properties();
        FileInputStream fis = new FileInputStream(PROPERTY_FILE_NAME);

        properties.load(fis);

        Gson gson = new Gson();
        File file = new File(properties.getProperty("file_name_city_list"));
        Assert.assertTrue(file.exists());
        BufferedReader br = new BufferedReader(new FileReader(file));
        String jsonText = br.lines().collect(Collectors.joining("\n"));
        Type listType = new TypeToken<ArrayList<ModelCity>>(){}.getType();
        final ArrayList<ModelCity> modelCityListResult = gson.fromJson(jsonText, listType);
        List<ModelCity> modelCityList = modelCityListResult.stream()
                .filter(mc->mc.getCountry().equals("RU"))
                .collect(Collectors.toList());
        String strRu = gson.toJson(modelCityList);
        BufferedWriter writer = new BufferedWriter(new FileWriter("city_list.json", true));
        writer.append(strRu);
        writer.close();
    }

    @Test
    public void testGetCityJson() {

    }
}
