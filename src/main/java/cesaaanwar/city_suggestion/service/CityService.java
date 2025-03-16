package cesaaanwar.city_suggestion.service;

import cesaaanwar.city_suggestion.model.City;
import cesaaanwar.city_suggestion.model.CitySuggestion;
import cesaaanwar.city_suggestion.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import static cesaaanwar.city_suggestion.Utils.Location.haversine;

@Service
public class CityService {

    @Autowired
    private CityRepository cityRepository;

    public List<CitySuggestion> getCities(
            String query,
            Double latitude,
            Double longitude,
            Integer pageNo,
            Integer pageSize
    ) {
        List<CitySuggestion> res = new ArrayList<>();

        Map<Long, City> citiesCollection = new HashMap<>();

        String[] queryTokens = query.toLowerCase().split("[ -]+");
        for (int i = 0; i < queryTokens.length; i++) {
            List<City> cities = cityRepository.getCitiesByQuery(queryTokens[i]);
            for (City city : cities) {
                if (!citiesCollection.containsKey(city))
                    citiesCollection.put(city.getId(), city);
            }
        }

        double latVal = 0, longVal = 0;
        if (latitude != null) latVal = latitude;
        if (longitude != null) longVal = longitude;

        for (City city : citiesCollection.values()) {
            CitySuggestion citySuggestion = scoreCityResult(city, queryTokens, latVal, longVal);
            if (citySuggestion.getScore() > 0) res.add(citySuggestion);
        }
        Collections.sort(res);

        int realPageSize = pageSize;
        if (res.size() < pageSize) realPageSize = res.size();
        return res.subList(pageNo, realPageSize);
    }

    private CitySuggestion scoreCityResult(City city, String[] tokens, double latVal, double longVal) {
        double value = 0;
        double modifier = 0;

        for (int i = 0; i < tokens.length; i++) {
            String token = tokens[i];
            List<String> indices = city.getIndices().get(token);
            if (indices != null) {
                for (String index : indices) {
                    value += ((double) token.length() / index.length());
                }
            }
        }
        String[] cityNameTokens = city.getAscii().split("[ -]+");
        modifier += cityNameTokens.length;

        if (latVal != 0 && longVal != 0) {
            modifier++;
            double distance = haversine(city.getLat(), city.getLon(), latVal, longVal);
            if (distance <= 4) {
                value += 1;
            } else {
                value += (1 - (0.1 * (distance - 4)));
            }
        }

        return new CitySuggestion(
                city.getName(),
                city.getLat(),
                city.getLon(),
                (value / modifier)
        );
    }

}
