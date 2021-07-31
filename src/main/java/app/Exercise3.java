package app;

import model.Person;
import repository.PersonRepository;

import java.time.LocalDate;
import java.time.Period;
import java.util.Locale;
import java.util.stream.Collectors;

enum SEX {
    MALE, FEMALE;
}

public class Exercise3 {
    public static void main(String[] args) {
        PersonRepository repository = new PersonRepository();

        repository.findAll()
                .map(person -> person.getName().toUpperCase(Locale.ROOT))
                .collect(Collectors.toList())
                .forEach(System.out::println);

        repository.findAll()
                .map(Person::getWeight)
                .map(Math::rint)
                .collect(Collectors.toList())
                .forEach(System.out::println);

        repository.findAll()
                .map(person -> person.getBirthDate())
                .map(birthday -> Period.between(birthday, LocalDate.now()))
                .collect(Collectors.toList())
                .forEach(System.out::println);

        repository.findAll()
                .map(person -> person.getName())
                .map(name -> name.endsWith("a") ? SEX.FEMALE : SEX.MALE)
                .collect(Collectors.toList())
                .forEach(System.out::println);

        repository.findAll()
                .map(person -> person.getWeight() / (double) Math.pow(person.getHeight(), 2))
                .collect(Collectors.toList())
                .forEach(System.out::println);
    }
}
