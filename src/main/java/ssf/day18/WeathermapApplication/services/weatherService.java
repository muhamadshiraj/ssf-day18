package ssf.day18.WeathermapApplication.services;

import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import ssf.day18.WeathermapApplication.WeathermapApplication;
import ssf.day18.WeathermapApplication.model.Weather;

import static ssf.day18.Constants.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;


@Service
public class weatherService {
    
    private final Logger logger = Logger.getLogger(WeathermapApplication.class.getName());

    private final String appId;

    public weatherService() {
        String k = System.getenv(ENV_WEATHERMAP_KEY);
        if ((null != k) && (k.trim().length() > 0))
            appId = k;
        else
            appId = "c354b89429bee7bb78f046c9e914d255";
    }

    public List<Weather> getWeather(String city){
        // to create a POST request with query parameters

        String url = UriComponentsBuilder.fromUriString(WEATHERMAP_URL)
        .queryParam("q", city)
        .queryParam("appid", appId)
        .queryParam("units", "metric")
        .toUriString();


        System.out.println("URL OUT>>>>" + url);
        
        final RequestEntity<Void> req = RequestEntity.get(url).build();
        final RestTemplate template = new RestTemplate();
        final ResponseEntity<String> resp = template.exchange(req, String.class);

        if (resp.getStatusCode() != HttpStatus.OK)
            throw new IllegalArgumentException(
                "ERROR: status code %s".formatted(resp.getStatusCode().toString())
            );
        
            final String body = resp.getBody();
            logger.log(Level.INFO, "payload: %s".formatted(body));
            System.out.println("payload: %s" + body);


        try(InputStream is = new ByteArrayInputStream(body.getBytes())){
            final JsonReader reader = Json.createReader(is);
            final JsonObject result = reader.readObject();
            final JsonArray readings = result.getJsonArray("weather");
           
            final String cityName = result.getString("name");
            final float temperature = (float)result.getJsonObject("main").getJsonNumber("temp").doubleValue();
            
            return readings.stream()
            .map(v -> (JsonObject)v)
            .map(Weather::create)
            .map(w -> {
                w.setCityName(cityName);
                w.setTemperature(temperature);
                return w;
            }).collect(Collectors.toList());

            // for (JsonValue v : readings) {
            //     Weather weather = new Weather();
            //     weather.setCityName(cityName);
            //     weather.setTemperature(temperature);
            //     randomList.add(weather);
            // }


        } catch (Exception e){
            e.printStackTrace();
        }

        return Collections.EMPTY_LIST;
        // get environment variable key
        // weather, temperature, other properties
    }

   
}


