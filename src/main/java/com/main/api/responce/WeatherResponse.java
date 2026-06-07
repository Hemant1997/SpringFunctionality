package com.main.api.responce;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
@Getter
@Setter
public class WeatherResponse {

    public Current current;

    @Getter
    @Setter
    public class Current{

        @JsonProperty("observation_time")
        private String observation_time;
        @JsonProperty("temperature")
        private int temperature;
        @JsonProperty("weather_code")
        private int weather_code;

        @JsonProperty("weather_descriptions")
        private ArrayList<String> weather_descriptions;
        
    }



}
