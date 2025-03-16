package cesaaanwar.city_suggestion.repository;

import cesaaanwar.city_suggestion.datasource.CityDataSource;
import cesaaanwar.city_suggestion.model.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CityRepository {

    @Autowired
    private CityDataSource cityDataSource;

    public List<City> getCitiesByQuery(String query) {
        if (!cityDataSource.getCityIndices().containsKey(query)) return new ArrayList<>();
        return cityDataSource.getCityIndices().get(query);
    }

}
