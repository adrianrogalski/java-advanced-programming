package app;

import model.Person;
import repository.PersonRepository;

import java.util.*;
import java.util.stream.Collectors;

public class PersonApp {
    public static void main(String[] args) {
        PersonRepository repository = new PersonRepository();
        // Z każdej kolekcji mogę utworzyć strumień
        // metoda findAll() utworzy strumień z listy będącej polem PersonRepo
        // OPERACJE POŚREDNIE
        // 1. filter()
        List<Person> people = repository.findAll()
                .filter(person -> person.getHeight() > 170)
                .collect(Collectors.toList());
        // odpowiednik dla strumienia wyżej
        List<Person> result = new ArrayList<>();
        for(Person p: repository.findAll().collect(Collectors.toList())) {
            if (p.getHeight() > 170) {
                result.add(p);
            }
        }

        //2. Mapowanie zamieniam każdy element w strumieniu na inny element(inna wartość lub nawet typ)
        // np. wchodzi strumien imion String wychodzi strumien dat LocalDate

        repository.findAll()
                // zamienia obiekty Person w strumieniu na imiona String
                .map(person -> person.getName())
                .map(name -> name.toUpperCase(Locale.ROOT))
                .forEach(System.out::println);

        //3. distinct() w zaleźności od umiejscowienia usuwa powtórzenia
        // np. przed mapowaniem => usunie powtorzenia osob Person
        //np. po mapowaniu => usunie powotrzenia imion
        repository.findAll()
                // zamienia obiekty Person w strumieniu na imiona String
                .map(person -> person.getName())
                .map(name -> name.toUpperCase(Locale.ROOT))
                .distinct()
                .forEach(System.out::println);
        // ALTERNATYWNYM ROZWIAZANIEM moze byc uzycie operacji collect() z argumentem Collector.toSet()
        repository.findAll()
                // zamienia obiekty Person w strumieniu na imiona String
                .map(person -> person.getName())
                .map(name -> name.toUpperCase(Locale.ROOT))
                .collect(Collectors.toSet())
                .forEach(System.out::println);


        // odpowiednik funkcyjny instrukcji for each
        people.forEach(person -> System.out.println(person));
        // wyswietlam forem(ale moge zrobić też strumieniem)
        for (Person p: people) {
            System.out.println(p);
        }

        // METODY KOŃCZĄCE DZIAŁANIE STRUMIENIA -> METODY TERMINALNE

        // mogą zamienić strumień na 1 element, zwrócić np. liste czy nie zwracać niczego
        // 1) findFirst -> znajduje pierwszy element ze strumienia
        // 2) findAny -> znajduje dowolny element ze strumienia

        // Z racji tego że może się okazać że element w strumieniu nie występuje metoda
        // findFirst wyrzuci wyjątek aby temu zapobiec przypisuje cale wyrazenie do typu opakowującego / pudełka
        // Optional który ma metode isPresent dzieki ktorej moge upewnic sie że metoda findFirst coś zwróciła
        Optional<Person> first = repository.findAll().filter(person -> person.getHeight() > 170).findFirst();
        if (first.isPresent()) {
            System.out.println(first.get());
        }

        // odpowiadają logicznie na strumień
        //3) allMatch -> czy wszystkie osoby w strumieniu pasują
        boolean allNamesWithK = repository.findAll().allMatch(person -> person.getName().startsWith("K"));
        //4) anyMatch -> czy jakakolwiek osoba w strumieniu
        boolean anyNameWithK = repository.findAll().anyMatch(person -> person.getName().startsWith("K"));

        //5) reduce() -> redukuje elementy strumienia do pojedynczego wyniku
        // reduce(wartoscPoczatkowa, funkcjaSpajającaKolejneElementy)
        // funkcja spajajaca jako pierwszy arhument przyjmuje akumulator ktory bedzie rozszerzany a
        // drugim jest kolejny element strumienia ktory bedzie z nim spajany
        Integer sumOfNameLength = repository.findAll()
                .map(person -> person.getName().length())
                .reduce(0, (accumulator, number) -> accumulator + number);

        String firstLetters = repository.findAll()
                .map(person -> person.getName().substring(0))
                .reduce("", (accumulator, nextChar) -> accumulator + nextChar);
        repository.findAll()
                .max(Comparator.comparingDouble(Person::getHeight));


    }
}
