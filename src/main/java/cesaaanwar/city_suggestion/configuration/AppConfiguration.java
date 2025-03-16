package cesaaanwar.city_suggestion.configuration;

import cesaaanwar.city_suggestion.datasource.CityDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;

@Configuration
public class AppConfiguration {

    @Autowired
    ResourceLoader resourceLoader;

    @Bean
    public CityDataSource cityDataSource() {
        return new CityDataSource(resourceLoader);
    }
}
