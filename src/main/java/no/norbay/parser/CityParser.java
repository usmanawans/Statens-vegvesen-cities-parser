package no.norbay.parser;

import com.google.gson.Gson;
import no.norbay.model.City;

import java.util.Arrays;
import java.util.List;

public class CityParser {
        public static List<City> parseStringToCities(String citiesJson){
            City[] userArray = new Gson().fromJson(citiesJson, City[].class);
            return Arrays.asList(userArray);
        }
}
