package ssf.day18.WeathermapApplication.model;

import jakarta.json.JsonObject;

public class Weather {
    private String cityName;
    private String main;
    private String description;
    private String icon;
    private Float temperature;
    private Float latitude;
    private Float longitude;
    

    
    public String getCityName() {
        return cityName;
    }

    
    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

   
    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    
    public String getIcon() {
        return icon;
    }

    
    public void setIcon(String icon) {
        this.icon = "http://openweathermap.org/img/wn/%s@2x.png".formatted(icon);
    }

    
    public Float getTemperature() {
        return temperature;
    }


    public void setTemperature(Float temperature) {
        this.temperature = temperature;
    }


    public Float getLatitude() {
        return latitude;
    }

   
    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }

   
    public Float getLongitude() {
        return longitude;
    }

   
    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }

    public static Weather create(JsonObject o) {
        final Weather w = new Weather();
        w.setMain(o.getString("main"));
        w.setDescription(o.getString("description"));
        w.setIcon(o.getString("icon"));
        return w;
    }

}
