package app;

import repository.CityRepository;
import java.io.IOException;
import java.io.InputStream;
import java.text.Collator;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

public class CityApp {
    public static void main(String[] args) {
        System.err.println("ERROR");
        try (InputStream input = CityApp.class.getResourceAsStream("/cities500.zip");
             CityRepository repository = CityRepository.fromZip(input);
        ) {
            //Mapa kodów krajów z liczbą ich miast
            Map<String, Long> citiesCountByCountryCode = repository.findAll()
                    .collect(Collectors.groupingBy(city -> city.getCode(), Collectors.counting()));
            System.out.println(citiesCountByCountryCode);
            System.out.println(citiesCountByCountryCode.get("PL"));
            //Lista polskich miast posortowana alfabetycznie
            repository.findAll()
                    .filter(city -> "PL".equals(city.getCode()))
                    .sorted((c1, c2) -> Collator.getInstance(Locale.forLanguageTag("pl-PL")).compare(c1.getName(), c2.getName()))
                    .forEach(city -> System.out.println(city.format()));
        } catch (IOException e) {
            System.err.println(e.getMessage());
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
