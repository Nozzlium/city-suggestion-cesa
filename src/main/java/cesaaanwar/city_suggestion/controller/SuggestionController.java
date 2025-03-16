package cesaaanwar.city_suggestion.controller;

import cesaaanwar.city_suggestion.dto.SuggestionWebResponse;
import cesaaanwar.city_suggestion.model.CitySuggestion;
import cesaaanwar.city_suggestion.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class SuggestionController {

    @Autowired
    private CityService cityService;

    @GetMapping("suggestions")
    public SuggestionWebResponse suggestions(
            @RequestParam String q,
            @RequestParam Optional<Double> latitude,
            @RequestParam Optional<Double> longitude,
            @RequestParam Optional<Integer> pageNo,
            @RequestParam Optional<Integer> pageSize
    ) {
        Double lat = latitude.orElse(0.0);
        Double log = longitude.orElse(0.0);
        Integer pageNoVal = pageNo.orElse(1);
        Integer pageSizeVal = pageSize.orElse(5);
        List<CitySuggestion> suggestions =
                cityService.getCities(
                        q,
                        lat,
                        log,
                        (pageNoVal - 1) * pageSizeVal,
                        pageSizeVal
                );
        return new SuggestionWebResponse(
                suggestions
        );
    }

}
