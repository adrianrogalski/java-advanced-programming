package app;

import model.City;
import model.Person;
import repository.CityRepository;
import repository.PersonRepository;

import java.io.IOException;
import java.util.Comparator;
import java.util.Optional;

public class Exercise4 {
    public static void main(String[] args) throws IOException {
        PersonRepository repository = new PersonRepository();

        long count = repository.findAll()
                .map(person -> person.getName())
                .filter(name -> name.endsWith("a"))
                .count();

        System.out.println(count);

        double weight = repository.findAll()
                .filter(person -> person.getBirthDate().getYear() < 1990)
                .map(person -> person.getWeight())
                // suma, wagaN
                .reduce(0.0, (peopleWeight, personWeight) -> peopleWeight + personWeight);

        System.out.println(weight);

        Optional<Person> smallestCarol = repository.findAll()
                // !!!!!!! BŁĄD !!!!!! .filter(person -> person.getName() == "Karol")
                .filter(person -> person.getName().equals("Karol"))
                .min(Comparator.comparingDouble(Person::getHeight));

        if (smallestCarol.isPresent()) {
            System.out.println(smallestCarol.get());
        }

        CityRepository cityRepository = CityRepository.fromZip(Exercise4.class.getResourceAsStream("../city500.zip"));

    }
}
