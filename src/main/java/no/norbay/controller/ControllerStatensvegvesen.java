package no.norbay.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@RestController
public class ControllerStatensvegvesen {

    @RequestMapping(value="/vegvesen", method= RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
    public String vv()
    {
        String data = null;
        int size = 0;

        String sURL1 = "https://www.vegvesen.no/nvdb/api/v2/omrader/kontraktsomrader"; //just a string

        // Connect to the URL using java's native library
        try{
            URL url = new URL(sURL1);
            HttpURLConnection request1 = (HttpURLConnection) url.openConnection();
            request1.connect();

            // Convert to a JSON object to print data
            JsonParser jp = new JsonParser(); //from gson
            JsonElement root = jp.parse(new InputStreamReader((InputStream) request1.getContent())); //Convert the input stream to a json element
            JsonArray rootobj = root.getAsJsonArray(); //May be an array, may be an object.
            data = rootobj.get(1).getAsJsonObject().get("navn").getAsString();


            request1.disconnect();

        }catch (Exception e){}

        return data;
    }


}