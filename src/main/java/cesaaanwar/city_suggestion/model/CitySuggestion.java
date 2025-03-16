package cesaaanwar.city_suggestion.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CitySuggestion implements Comparable<CitySuggestion> {
    private String name;
    private double latitude;
    private double longitude;
    private double score;

    @Override
    public int compareTo(CitySuggestion other) {
        if (this.getScore() > other.getScore()) return -1;
        if (this.getScore() < other.getScore()) return 1;
        return 0;
    }
}
