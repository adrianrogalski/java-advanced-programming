package javafx;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
// APLIKACJE URUCHAMIAM PRZEZ MAVEN javafx:run NIE PRZEZ ZIELONA STRZALKE KOMPILACJI
// 1) klasa musi rozszerzać klase Application
public class JavaFxApp extends Application {

    // stage jest to obiekt okna wygenerowany przez system
    @Override
    public void start(Stage stage) throws Exception {
        // Podstawowy kontener z klasy JavaFx
        // tworze kontener do którego moge dodawać elementy -> kontrolki, linie, kółka etc.
        Group root = new Group();
        // ĆWICZENIA NA KOLEKCJACH
        Rectangle rectangle = new Rectangle(50,50);
        Circle circle = new Circle(100,100,20);
        // uzywam pochodnej klasy Paint, Color
        rectangle.setFill(Color.ALICEBLUE);
        rectangle.setX(200);
        rectangle.setY(200);
        circle.setFill(Color.RED);
        circle.setCenterX(500);
        Line line = new Line(0,0,300,300);
        line.setStroke(Color.RED);
        line.setStrokeWidth(30.0);
        // tworze dzieci dla kontenera tzw.node'a ktore dziedzicza po nim
        root.getChildren().add(line);
        root.getChildren().add(rectangle);
        root.getChildren().add(circle);
        // OBSŁUGIWANIE ZDARZEŃ
        // eventHandler jest interfejsem funkcyjnym którego obiekt (tu e) zawiera informacje o calym zdarzeniu
        circle.setOnMouseClicked(e -> {
            System.out.println(e.getButton());
            circle.setFill(circle.getFill() == Color.RED ? Color.GREEN : Color.RED);
        });

        // Okno zawiera scene (to co się wyswietla)
        Scene scene = new Scene(root, 600,400);
        // Scene przekazuje do systemu operacyjnego
        stage.setScene(scene);
        // Wyswietlenie okna
        stage.show();

        // APLIKACJA NIE MA LINIOWEGO CIAGU KODU W PRZECIWENSTWIE DO APLIKACJI KONSOLOWEJ WIEC KAZDA INTERAKCJE Z ELEMENTEM MUSZE OPROGRAMOWAC

    }
    // musi istnieć metoda main
    public static void main(String[] args) {
        // poczatek wykonywania programu zaczyna sie od metody launch()
        launch();
    }
}
