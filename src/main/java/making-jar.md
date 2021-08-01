# Tworzenie paczki jar

## Plik MANIFEST.MF
W katalogu `META-INF` należy umieścić plik manifestu, w którym jednym z najważniejszych elementów jest określenie
punktu wejścia. Przykładowa treść:
```
Manifest-Version: 1.0
Created-By: 1.7.0_06 (Oracle Corporation)
Main-Class: nazwaKlasy
```
W miejscu `nazwaKlasy` należy wpisać nazwę klasy z metodą `main`. Ta klasa zostanie załadowana jako pierwsza i
program zacznie się od wykonania jej metody `main`.

## Dodanie pluginu w <code>pom.xml</code>
Dodanie tego pluginu powoduje, że Maven tworzy plik manifestu ze wskazaną klasą stanowiąca punkt wejścia.

```xml
<build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-jar-plugin</artifactId>
                <version>3.2.0</version>
                <configuration>
                    <archive>
                        <manifest>
                            <mainClass>app.CityApp</mainClass>
                        </manifest>
                    </archive>
                </configuration>
            </plugin>
        </plugins>
    </build>
```
W sekcji <code><mainClass>paczka.Klasa</mainClass></code> należy wpisać klasę, która zawiera metodę `main`.

## Określenie metody `packaging` w `pom.xml`
```xml
<project>
    <packaging>jar</packaging>
    ...
</project>
```


## Kompilacja i tworzenie paczki
Dla skonfigurowanego projektu Maven należy wywołać cel (View-->Tool Windows-->Maven, ikona z literą M - Execute Maven goal):
```text
    mvn package
```
To spowoduje, że zostanie utworzony plik `artifatId-version.jar` w katalogu `target`.

## Uruchomienie
W konsoli systemu operacyjnego np. `cmd.exe` wpisujemy polecenie uruchamiające paczkę `jar`.

```text
java -jar paczka.jar
```

Aby uzyskać poprawne kodowanie znaków w UTF-8 (Java koduje znaki w UTF-16) należy dodać przełącznik:
```text
java -jar -Dfile.encoding=UTF-8 paczka.jar
```

W przypdaku powłok, które interpretują znak kropki należy przełącznik podać w cudzysłowiu.
```text
java -jar "-Dfile.encoding=UTF-8" paczka.jar
```
W windowsie moge utworzyć pilk z rozszerzeniem .bat w którym skopiuje parametry wywołania programu na macu .command na któremu daje uprawnienia 
chmod 755 plik.command a nastepnie uruchamiam bash plik.command


