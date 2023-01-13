# UnitedBanks

### Technologie: <br/>
    - Back-end: Spring
    - Front-end: JavaFX
    - Baza danych: H2

### OPIS:
Aplikacje obserwuje storage, czyli w naszym przypadku jest to folder do którego możemy wrzucać wyciągi z różnych banków w postaci plików '.csv'. <br/> 
Po dodaniu pliku z odpowiednim przedimkiem, nasz system indetyfikuje bank z którego pochodzi plik i wyciaga z niego najważniejsze dla nas informacje,
wspólne dla wszytkich banków (obecnie obsługujemy historie transakacji z dwóch banków: Millennium oraz PKOBP, ale jest to w łatwy sposób rozszeżalne).<br/> 
Wymiana danych w API realizowana jest za pomocą Retrofit. Proste UI wyświetla tabelę z rekordami transakcji ze wszystkich banków z możliwością tagowania poszczegolnych rekordów oraz filtrowanie ich po tych tagach. <br/> 
