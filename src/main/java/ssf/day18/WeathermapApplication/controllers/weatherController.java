package ssf.day18.WeathermapApplication.controllers;

import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ssf.day18.WeathermapApplication.WeathermapApplication;
import ssf.day18.WeathermapApplication.model.Weather;
import ssf.day18.WeathermapApplication.services.weatherService;

@Controller
@RequestMapping(path="/weather")
public class weatherController {
   
    private final Logger logger = Logger.getLogger(WeathermapApplication.class.getName());


    @Autowired
    private weatherService weatherService;

    @GetMapping
    public String getWeather(@RequestParam (required = true) String city, Model model ){
        
        logger.log(Level.INFO, "City: %s".formatted(city));

        //list that is empty to be returned in case there is no weather information
        List<Weather> weatherList = Collections.EMPTY_LIST;

        //to call the weather service to get the weather of the city
        try {
            weatherList = weatherService.getWeather(city);
    
        } catch (Exception ex) {
            logger.log(Level.WARNING, "Warning: %s".formatted(ex.getMessage()));
        }
        model.addAttribute("city", city);
        model.addAttribute("weather", weatherList);
            //TODO: handle exception
        
        // final Logger logger = Logger.getLogger(WeathermapApplication.class.getName());
    
        // String cityName = form.getFirst("city");

        // logger.log(Level.INFO, "city: %s".formatted(cityName));

        return "result";
    }

}
