package org.example.weather.service;

import org.example.weather.model.State;
import org.example.weather.model.Weather;
import org.example.weather.model.WeatherDTO;
import org.example.weather.repository.WeatherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WeatherServiceImp implements WeatherService {


    @Autowired
    private WeatherRepository weatherRepository;


    @Override
    public Weather getWeather(String city) {
        return weatherRepository.findByCity(city);
    }

    @Override
    public List<Weather> getWeatherByState(State state) {
        return weatherRepository.findByState(state);
    }

    @Override
    public void addWeather(WeatherDTO weatherDTO) throws CityAndStateAlreadyExistsException {
        if (weatherRepository.findByStateAndCity(weatherDTO.getState(), weatherDTO.getCity()) != null)
            throw new CityAndStateAlreadyExistsException();
    }
        Weather weather = new Weather(
                weatherDTO.getCity(),
                weatherDTO.getState(),
                weatherDTO.getTempC(),
                weatherDTO.getPrecipitation(),
                weatherDTO.getHumidity(),
                weatherDTO.getWindKmH()
        );
        weatherRepository.save(weather);
    }

    @Override
    public void updateWeather(WeatherDTO weather) {
        Weather existingWeather = getWeather(weather, getCity());
        if (existingWeather !=null) {
            existingWeather.setState(weather.getState());
            existingWeather.setTempC(weather.getTempC());
            existingWeather.setPrecipitation(weather.getPrescipitation());
            existingWeather.setHumidity(weather.getWindKmH());
            weatherRepository.save(existingWeather);
    }

    @Override
    public void deleteWeather(String city, State state) {
        Weather weather = getWeather(city);
        if(weather != null) {
            weatherRepository.delete(weather);
        }
    }
}

