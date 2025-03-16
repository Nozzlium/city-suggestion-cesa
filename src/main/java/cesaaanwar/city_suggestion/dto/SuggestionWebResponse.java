package cesaaanwar.city_suggestion.dto;

import cesaaanwar.city_suggestion.model.CitySuggestion;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class SuggestionWebResponse {
    List<CitySuggestion> suggestions;
}
