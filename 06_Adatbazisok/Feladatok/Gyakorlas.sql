--CREATE DATABASE FilmesAB

CREATE TABLE Mufajok(
Mufaj_ID INT CONSTRAINT Mufaj_PK primary key,
Nev VARCHAR(30)
);

CREATE TABLE Szineszek(
Szinesz_ID INT CONSTRAINT Szinesz_PK primary key,
Nev VARCHAR(30),
SzulDatum DATE
);

CREATE TABLE Filmek(
Film_ID INT CONSTRAINT Film_PK primary key,
Cim VARCHAR(50),
Koltsegvetes MONEY,
Mufaj_ID INT,
FOREIGN KEY (Mufaj_ID) REFERENCES Mufajok(Mufaj_ID)
);

CREATE TABLE Szerepel(
Film_ID INT,
Szinesz_ID INT,
PRIMARY KEY (Film_ID, Szinesz_ID),
FOREIGN KEY (Film_ID) REFERENCES Filmek(Film_ID),
FOREIGN KEY (Szinesz_ID) REFERENCES Szineszek(Szinesz_ID),
);

ALTER TABLE Filmek DROP COLUMN Koltsegvetes

ALTER TABLE Filmek ADD Megjelenes DATE

ALTER TABLE Filmek ADD Koltsegvetes MONEY

ALTER TABLE Filmek
ADD CONSTRAINT Koltsegvetes_C CHECK (Koltsegvetes >= 100000)