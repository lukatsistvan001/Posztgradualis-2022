USE master;
GO
IF EXISTS(select * from sys.databases where name='nyaralo')
	DROP DATABASE nyaralo

CREATE DATABASE nyaralo;
GO
USE nyaralo;
GO


CREATE TABLE Tulajdonosok(
	TulajID INT PRIMARY KEY,
	Nev VARCHAR(30),
	Cim VARCHAR(30),
	EmailCim VARCHAR(30)
);

CREATE TABLE Orszagok(
	OrszagID INT PRIMARY KEY,
	OrszagNev VARCHAR(30),
);

CREATE TABLE Nyaralok(
	NyaraloID INT PRIMARY KEY,
	NyaraloNev VARCHAR(30),
	NyaraloCim VARCHAR (30),
	OrszagID INT REFERENCES Orszagok (OrszagID),
	TulajID INT REFERENCES Tulajdonosok (TulajID),
	Ferohely INT,
	Ar INT
);

CREATE TABLE Felszerelesek(
	FelszerelesID INT PRIMARY KEY,
	FelszerelesNev VARCHAR(30),
);

CREATE TABLE Felszerelesei(
	FelszereleseiID INT PRIMARY KEY IDENTITY,
	NyaraloID INT REFERENCES Nyaralok (NyaraloID),
	FelszerelesID INT REFERENCES Felszerelesek (FelszerelesID)
);

CREATE TABLE Berlesek(
	BerlesID INT PRIMARY KEY IDENTITY,
	NyaraloID INT REFERENCES Nyaralok (NyaraloID),
	BerloNev VARCHAR(30),
	KezdoDatum DATETIME,
	IdoTartam INT,
	Ertek INT 
);

INSERT INTO Orszagok VALUES (1, 'Romania');
INSERT INTO Orszagok VALUES (2, 'Magyarorszag');
INSERT INTO Orszagok VALUES (3, 'Ausztria');
INSERT INTO Orszagok VALUES (4, 'Olaszorszag');
INSERT INTO Orszagok VALUES (5, 'Franciaorszag');
INSERT INTO Orszagok VALUES (6, 'Csehorszag');

INSERT INTO Tulajdonosok VALUES(1, 'Antal Attila', NULL, 'attila@email.com')
INSERT INTO Tulajdonosok VALUES(2, 'Kelemen Melinda', NULL, 'melinda@email.com')
INSERT INTO Tulajdonosok VALUES(3, 'Gergely Emese', NULL, 'emese@email.com')
INSERT INTO Tulajdonosok VALUES(4, 'Kovacs Annamaria', NULL, 'anna@email.com')
INSERT INTO Tulajdonosok VALUES(5, 'Horvath Levente', NULL, 'levente@email.com')
INSERT INTO Tulajdonosok VALUES(6, 'Bereczki Gergely', NULL, 'gergely@email.com')
INSERT INTO Tulajdonosok VALUES(7, 'Incze Istvan', NULL, 'istvan@email.com')

INSERT INTO Felszerelesek VALUES (1, 'szauna')
INSERT INTO Felszerelesek VALUES (2, 'medence')
INSERT INTO Felszerelesek VALUES (3, 'jacuzzi')
INSERT INTO Felszerelesek VALUES (4, 'tv')
INSERT INTO Felszerelesek VALUES (5, 'terasz')
INSERT INTO Felszerelesek VALUES (6, 'teniszpalya')
INSERT INTO Felszerelesek VALUES (7, 'grillsuto')

INSERT INTO Nyaralok VALUES (1, 'Nyaralo1', null, 3, 2, 4, 25)
INSERT INTO Nyaralok VALUES (2, 'Nyaralo2', null, 4, 2, 2, 20)
INSERT INTO Nyaralok VALUES (3, 'Nyaralo3', null, 4, 4, 4, 31)
INSERT INTO Nyaralok VALUES (4, 'Nyaralo4', null, 4, 1, 3, 20)
INSERT INTO Nyaralok VALUES (5, 'Nyaralo5', null, 3, 1, 4, 12)
INSERT INTO Nyaralok VALUES (6, 'Nyaralo6', null, 1, 5, 12, 100)
INSERT INTO Nyaralok VALUES (7, 'Nyaralo7', null, 4, 3, 4, 12)
INSERT INTO Nyaralok VALUES (8, 'Nyaralo8', null, 1, 5, 2, 20)
INSERT INTO Nyaralok VALUES (9, 'Nyaralo9', null, 4, 3, 4, 12)
INSERT INTO Nyaralok VALUES (10, 'Nyaralo10', null, 2, 2, 5, 30)
INSERT INTO Nyaralok VALUES (11, 'Nyaralo11', null, 1, 1, 6, 25)
INSERT INTO Nyaralok VALUES (12, 'Nyaralo12', null, 2, 1, 8, 35)
INSERT INTO Nyaralok VALUES (13, 'Nyaralo13', null, 3, 1, 2, 10)
INSERT INTO Nyaralok VALUES (14, 'Nyaralo14', null, 4, 1, 16, 50)
INSERT INTO Nyaralok VALUES (15, 'Nyaralo15', null, 5, 1, 20, 50)
INSERT INTO Nyaralok VALUES (16, 'Nyaralo16', null, 6, 1, 4, 15)

INSERT INTO Felszerelesei VALUES(1, 2)
INSERT INTO Felszerelesei VALUES(1, 1)
INSERT INTO Felszerelesei VALUES(1, 3)
INSERT INTO Felszerelesei VALUES(2, 1)
INSERT INTO Felszerelesei VALUES(3, 2)
INSERT INTO Felszerelesei VALUES(4, 1)
INSERT INTO Felszerelesei VALUES(4, 2)
INSERT INTO Felszerelesei VALUES(4, 3)
INSERT INTO Felszerelesei VALUES(4, 4)
INSERT INTO Felszerelesei VALUES(4, 5)
INSERT INTO Felszerelesei VALUES(4, 6)
INSERT INTO Felszerelesei VALUES(4, 7)

INSERT INTO Berlesek VALUES (1, 'Mate Eniko', '2009-12-20', 5, 150)
INSERT INTO Berlesek VALUES (3, 'Kekekes Barna', '2009-12-24', 2, 50)
INSERT INTO Berlesek VALUES (3, 'Szabo Edina', '2008-5-24', 3, 60)
INSERT INTO Berlesek VALUES (6, 'Fulop Emese', '2008-11-20', 5, 150)
INSERT INTO Berlesek VALUES (3, 'Laszlo Zoltan', '2009-12-01', 2, 50)
INSERT INTO Berlesek VALUES (3, 'Balazs Csongor', '2009-5-24', 3, 60)
INSERT INTO Berlesek VALUES (5, 'Sandor Attila', '2008-3-20', 5, 150)
INSERT INTO Berlesek VALUES (3, 'Fogarasi Arpad', '2009-12-05', 2, 250)
INSERT INTO Berlesek VALUES (4, 'Balazs Csongor', '2009-6-24', 3, 60)
