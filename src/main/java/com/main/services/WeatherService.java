package com.main.services;

import com.main.api.responce.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
@Component
public class WeatherService {
    private static final String key="afe699d684d25efaa49b703924f8ea7c";
    private static final String url="http://api.weatherstack.com/current?access_key=API_KEY&query=CITY";

    @Autowired
    RestTemplate restTemplate;

    public WeatherResponse getWeatherDetails(String city){
        String finalApi=    url.replace("CITY",city).replace("API_KEY",key);
         ResponseEntity<WeatherResponse> response= restTemplate.exchange(finalApi, HttpMethod.GET,null, WeatherResponse.class);
        WeatherResponse body=  response.getBody();
       return body;
    }
}
