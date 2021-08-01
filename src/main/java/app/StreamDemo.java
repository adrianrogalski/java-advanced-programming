package app;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

class Names {
    private List<String> names;

    public Names(List<String> names) {
        this.names = names;
    }

    // UWAGA PO POBRANIU LISTY MOZNA JA WYCZYSCIC CZY MODYFIKOWAC(gettery na kolekcji nie są zalecane chyba że celowo)
    public List<String> getNames() {
        return names;
    }

    // DOBRA PRAKTYKA
    public Stream<String> getNamesAsStream() {
        return names.stream();
    }
}

public class StreamDemo {
    public static void main(String[] args) {
        // STREAMY DLA TYPOW PROSTYCH
        // tworze strumien liczb od 0 do 99
        /*
        Cechy strumieni:
        1)leniwe przetwarzanie: dopiero wtedy kiedy strumień zakończy sie mnetoda terminalna(zwlekam z przepływem elementów dopóki nie określe źródła)
        -> moge przekazywać strumień bez marnowania pamięci moge zdefiniować jak uzyskać elementy a nie same elementy
        -> analogia dopóki nie zamknę obwodu prąd nie płynie przez niego
        2)wykonywana  jest tylko jedna iteracja po elementach strumienia, operacje pośrednie są składane i wykonywane na elemencie
        3) Strumień nie modyfikuje źródła np(kolekcji) z której jest tworzony
        -> zabezpieczenie przed modyfikacją kolekcji na zewnątrz


         */
        IntStream.range(0, 100).peek(System.out::println)
        .filter(number -> {
            System.out.println("Filter " + number);
            return number % 2 == 0;
        })
        .forEach(System.out::println);

        // PRZYKŁAD Z BEZPIECZEŃSTWA UŻYWANIA GETERÓW VS STREAMÓW
        List<String> list = new ArrayList<>();
        list.add("Kasia");
        list.add("Dominika");
        Names names = new Names(list);
        // w teorii lista ta nie powinna być już zmienialna -> nie ma settera i mam tylko getter a pole jest priv
        names.getNames().add("Arek");
        names.getNames().clear();
        // ktoś z zewnątrz może jednak bez problemu modyfikować lub usunąc te dane

        // Użycie stream
        // użytkownik zewnetrzny w tym przypadku operuje tylko na KOPI prawdziej listy
        // moge zwracać strumień zamiast kolekcji w klasie
        System.out.println(names.getNamesAsStream().collect(Collectors.toList()));

    }
}
