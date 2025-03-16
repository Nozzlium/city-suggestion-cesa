package cesaaanwar.city_suggestion.datasource;

import cesaaanwar.city_suggestion.model.City;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.*;

@Getter
public class CityDataSource {

    private ResourceLoader resourceLoader;
    private final Map<String, List<City>> cityIndices;

    public CityDataSource(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
        this.cityIndices = new HashMap<>();
        initialize();
    }

    public void initialize() {
        try {
            String filename = "cities_canada-usa.tsv";
            assert resourceLoader != null;
            Resource resource = resourceLoader.getResource("classpath:" + filename);
            InputStream inputStream = resource.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));

            cityIndices.clear();

            List<City> cities = new ArrayList<>();

            br.readLine();
            String line = "";
            while ((line = br.readLine()) != null) {
                String[] values = line.split("\t");
                cities.add(new City(
                        Long.parseLong(values[0]),
                        values[1],
                        values[2],
                        values[3],
                        Double.parseDouble(values[4]),
                        Double.parseDouble(values[5]),
                        values[8],
                        new HashMap<>()
                ));
            }
            buildIndices(cities);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void buildIndices(List<City> cities) {
        for (int i = 0; i < cities.size(); i++) {
            System.out.printf("Building indices: %d/%d\r", i+1, cities.size());
            City city = cities.get(i);
            processCity(city);
        }
    }

    private void processCity(City city) {
        String[] tokens = city.getAscii().split("[ -]+");
        for (int i = 0; i < tokens.length; i++) {
            String token = tokens[i].toLowerCase();
            List<String> indices = getIndices(token);
            for (String index : indices) {
                if (cityIndices.containsKey(index)) {
                    cityIndices.get(index).add(city);
                } else {
                    List<City> cityToRecord = new ArrayList<>();
                    cityToRecord.add(city);
                    cityIndices.put(index, cityToRecord);
                }

                if (city.getIndices().containsKey(index)) {
                    city.getIndices().get(index).add(token);
                } else {
                    List<String> tokenToKeep = new ArrayList<>();
                    tokenToKeep.add(token);
                    city.getIndices().put(index, tokenToKeep);
                }
            }
        }
    }

    public List<String> getIndices(String text) {
        List<char[]> indices = new ArrayList<>();
        String[] tokens = text.split("[ -]+");
        for (String token : tokens) {
            List<char[]> tokenIndices = new ArrayList<>();
            int starting = 0;
            for (char c : token.toCharArray()) {
                int tokenLength = tokenIndices.size();
                List<char[]> toAppends = new ArrayList<>();
                toAppends.add(new char[]{c});
                for (int j = starting; tokenLength != 0 && j < tokenLength; j++) {
                    char[] toAppend = tokenIndices.get(j);
                    char[] newlyAppended = Arrays.copyOf(toAppend, toAppend.length + 1);
                    newlyAppended[toAppend.length] = c;
                    toAppends.add(newlyAppended);
                }
                tokenIndices.addAll(toAppends);
                starting = tokenIndices.size() - toAppends.size();
            }
            indices.addAll(tokenIndices);
        }

        Set<String> res = new HashSet<>();
        for (char[] charArr : indices) {
            String toAdd = new String(charArr);
            res.add(toAdd);
        }
        return res.stream().toList();
    }

}
