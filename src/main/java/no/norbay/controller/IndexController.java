package no.norbay.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Usman on 07.04.2017.
 */

@RestController
public class IndexController {

    @RequestMapping(value="/", method= RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
    public String index()
    {
        String index = "{\n" +
                "\t\"current_city\": \"http://localhost:8080/city\",\n" +
                "\t\"contract_areas\": \"http://localhost:8080/vegvesen\",\n" +
                "\t\"information\": \"This is project has been created for the strudents Software Architecture and Frameworks (DAVE3615)\"\n" +
                "}";

        return index;
    }
}
