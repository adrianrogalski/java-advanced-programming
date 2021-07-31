package app;

import model.City;
import model.Person;
import repository.CityRepository;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;
import java.util.function.Predicate;

public class Exercise2 {
    public static void main(String[] args) throws IOException {
        // Tworze input stream do danych w resources pod postacia paczki zip
        // skoro Exercise2 jest w katalogu app który nie ma dostepu do katalogu res wchodze do katalogu rodzica ../
        // ze wzgledu na to że cities500 paczka zip wewnetrzna i uzytkownik w nia nie ingeruje moge uzyc throws
        CityRepository repository = CityRepository.fromZip(Exercise2.class.getResourceAsStream("../cities500.zip"));
        // referencja do metody -> argument lambdy ma byc argumentem metody WARUNEK lambda musi mieć 1 argument
        repository.findAll().filter(city -> city.getCode().equals("US")).forEach(System.out::println);

        repository.findAll().anyMatch(city -> city.getName().split(" ").length == 4);


        Predicate<City> sameDigitPopulation = city -> {
            long population = city.getPopulation();
            char[] populationNumber = String.valueOf(population).toCharArray();
            for (int i = 0; i < populationNumber.length; i++) {
                if (i < populationNumber.length - 1 && (int) populationNumber[i] != (int) populationNumber[i + 1]) {
                    return false;
                }
            }
            return true;
        };
        repository.findAll().anyMatch(sameDigitPopulation);

        repository.findAll().allMatch(city -> city.getName().length() > 3);
        repository.findAll().allMatch(city -> city.getPopulation() > 500);

    }
}
