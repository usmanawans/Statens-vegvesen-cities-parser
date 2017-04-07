package com.svv.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Usman on 07.04.2017.
 *
 * This service detects current location and city based on IP
 * It communicates with 3rd party service
 */
@RestController
public class LocationController {

    @RequestMapping(value="/city", method= RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
    public String geo() {

        String city = null;
        String urlString = "http://freegeoip.net/json/";

        try{
            // connetion
            URL url = new URL(urlString);
            HttpURLConnection request = (HttpURLConnection) url.openConnection();
            request.connect();

            // Convert to a JSON object to print data
            JsonParser jp = new JsonParser(); //from gson
            JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent())); //Convert the input stream to a json element
            JsonObject rootobj = root.getAsJsonObject(); //May be an array, may be an object.

            city = rootobj.get("city").getAsString(); //just grab the zipcode

        }catch (Exception e){
            e.printStackTrace();
        }
        return city;
    }
}
