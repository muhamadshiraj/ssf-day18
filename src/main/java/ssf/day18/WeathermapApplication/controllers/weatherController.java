package ssf.day18.WeathermapApplication.controllers;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ssf.day18.WeathermapApplication.WeathermapApplication;

@Controller
@RequestMapping(path="/weather")
public class weatherController {
   
    @GetMapping
    public String getWeather(@RequestParam (required = true) String city, Model model ){
        
        model.addAttribute("city", city);
            //TODO: handle exception
        
        // final Logger logger = Logger.getLogger(WeathermapApplication.class.getName());
    
        // String city = form.getFirst("city");

        // logger.log(Level.INFO, "city: %s".formatted(city));

        return "result";
    }

}
