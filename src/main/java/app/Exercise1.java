package app;

import model.Person;
import repository.PersonRepository;

import java.time.Month;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Exercise1 {
    public static void main(String[] args) {
        PersonRepository repository = new PersonRepository();

        repository.findAll()
                .filter(person -> person.getName().length() == 3);
        repository.findAll()
                .filter(person -> person.getName().startsWith("K") && person.getName().endsWith("a"));
        repository.findAll()
                .filter(person -> person.getWeight() > 75);
        repository.findAll()
                // przyrównuje za pomocą typu ENUM Month!
                .filter(person -> person.getBirthDate().getMonth() == Month.MAY);
        repository.findAll()
                .filter(person -> person.getBirthDate().getYear() >= 1995 && person.getBirthDate().getYear() <= 2000);
        repository.findAll()
                .filter(person -> !person.getName().endsWith("a") && (person.getWeight() == person.getBirthDate().getYear() % 100)).forEach(person -> System.out.println(person));

    }
}
