package no.norbay.controller;

import no.norbay.model.City;
import no.norbay.parser.CityParser;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class CitiesController {

    @Value("${no.vegvesen.cities.url}")
    private String citiesUrl;

    @RequestMapping(value="/", method= RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
    public List<City> getCities()
    {
        try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
            HttpGet httpGet = new HttpGet(citiesUrl);
            try (CloseableHttpResponse response1 = httpclient.execute(httpGet)) {
                HttpEntity httpEntity = response1.getEntity();
                String result = EntityUtils.toString(httpEntity);
                return CityParser.parseStringToCities(result);
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return List.of();
    }
}