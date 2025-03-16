package cesaaanwar.city_suggestion;

import cesaaanwar.city_suggestion.datasource.CityDataSource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@SpringBootTest
public class CityDataSourceTests {

    @Autowired
    private CityDataSource cityDataSource;

    @Test
    void testIndicesLondon() {
        String[] expected = {
                "l",
                "o",
                "lo",
                "n",
                "on",
                "lon",
                "d",
                "nd",
                "ond",
                "lond",
                "do",
                "ndo",
                "ondo",
                "londo",
                "on",
                "don",
                "ndon",
                "ondon",
                "london",
        };
        List<String> indices = cityDataSource.getIndices("london");
        assert new HashSet<>(indices).equals(new HashSet<>(Arrays.asList(expected)));
    }

    @Test
    void testIndicesNewYork() {
        String[] expected = {
                "n",
                "e",
                "ne",
                "w",
                "ew",
                "new",
                "y",
                "o",
                "yo",
                "r",
                "or",
                "yor",
                "k",
                "rk",
                "ork",
                "york",
        };
        List<String> indices = cityDataSource.getIndices("new york");
        assert new HashSet<>(indices).equals(new HashSet<>(Arrays.asList(expected)));
    }

}
