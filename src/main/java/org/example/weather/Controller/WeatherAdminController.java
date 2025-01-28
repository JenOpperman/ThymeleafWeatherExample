package org.example.weather.Controller;

import org.example.weather.model.State;
import org.example.weather.model.Weather;
import org.example.weather.model.WeatherDTO;
import org.example.weather.service.CityAndStateAlreadyExistsException;
import org.example.weather.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/weather")
public class WeatherAdminController {

    @Autowired
    private WeatherService weatherService;

   @PostMapping
    public ResponseEntity<WeatherDTO> addWeather(@RequestBody WeatherDTO weatherDTO){
       try{
           weatherService. addWeather(weatherDTO);
           return new ResponseEntity<>(HttpStatus.CREATED);
       }catch (CityAndStateAlreadyExistsException e){
           return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
       }
   }

   @GetMapping("/city")
    public ResponseEntity<?> getWeatherByCity(@PathVariable String city) {
        Weather weather = weatherService.getWeather(city);
        return weather != null ? ResponseEntity.ok(weather) : ResponseEntity.notFound().build();
   }

}

