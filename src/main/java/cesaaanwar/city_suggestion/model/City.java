package cesaaanwar.city_suggestion.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class City {
    private long id;
    private String name;
    private String ascii;
    private String altName;
    private double lat;
    private double lon;
    private String country;
    private Map<String, List<String>> indices;
}
