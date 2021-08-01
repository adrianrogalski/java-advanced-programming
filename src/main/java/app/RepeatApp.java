package app;

public class RepeatApp {
    // NAZYWANIE ARGUMENTOW
    public static void main(String[] args) {
        if (args.length != 2) {
            return;
        }
        System.out.println("Program wyświetlajacy komunikat n razy: ");
        String message = "";
        int count = 0;
        // Rozpoznawanie argumentow
        for (String arg : args) {
            // podziel argumenty w konsoli na ich nazwy i wartosci
            String[] split = arg.split("=");
            // sprawdz czy sa 2 komorki
            if (split.length != 2) {
                return;
            }
            String parameterName = split[0];
            String parameterValue = split[1];
            if (parameterName.equals("-message")) {
                message = parameterValue;
            }
            if (parameterName.equals("-count")) {
                count = Integer.parseInt(parameterValue);
            }
        }
        System.out.println((message+"\n").repeat(count));
    }
}
