package app;

import java.util.Arrays;

public class CalculatorApp {
    public static void main(String[] args) {
        System.out.println(args.length);
        Arrays.stream(args).forEach(System.out::println);
        if (args.length == 0) {
            help();
            return;
        }

        if (args.length != 3) {
            error();
            return;
        }

        double a = Double.parseDouble(args[0]);
        double b = Double.parseDouble(args[1]);
        String character = String.valueOf(args[2]);

        // Operując na typach prostych moge spokojnie używać instrukcji for, switch, if etc.
        switch (character) {
            case "+":
                System.out.println(a + b);
                break;
            case "-":
                System.out.println(a - b);
                break;
            case "*":
                System.out.println(a * b);
                break;
            case "/":
                System.out.println(a / b);
                break;
            default:
                System.out.println("Nie rozpoznano znaku");
        }
    }

    public static void help() {
        System.err.println("Super kalkulator");
        System.err.println("Wywołanie: CalculatorApp <double> <double> <operator> ");
        System.err.println("<liczba> - dowolna liczba dziesietna");
        System.err.println("<operator> - operator +, -, /, *");
    }

    public static void error() {
        System.err.println("Niepoprawna liczba argumentów");
    }
}
