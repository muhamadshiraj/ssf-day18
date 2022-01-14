package ssf.day18.WeathermapApplication.services;

import org.springframework.boot.system.ApplicationPid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import ssf.day18.WeathermapApplication.WeathermapApplication;

import static ssf.day18.Constants.*;

import java.util.logging.Logger;

public class weatherService {
    
    private final Logger logger = Logger.getLogger(WeathermapApplication.class.getName());

    // private final String appId;

    // public weatherService() {
    //     String k = System.getenv(ENV_WEATHERMAP_KEY);
    //     if ((null != k) && (k.trim().length() > 0))
    //         appId = k;
    //     else
    //         appId = "abc123";
    // }

    public String getWeather(String city){
        //to create a POST request with query parameters

        String url = UriComponentsBuilder.fromUriString(WEATHERMAP_URL)
        .queryParam("q", city)
        // .queryParam("appid", appId)
        .queryParam("units", "metric")
        .toUriString();

        RestTemplate template = new RestTemplate();

        ResponseEntity<String> resp = template.getForEntity("https://openweathermap.org/current", String.class);

        return null;
        //get environment variable key
        //weather, temperature, other properties
    }

   
}


