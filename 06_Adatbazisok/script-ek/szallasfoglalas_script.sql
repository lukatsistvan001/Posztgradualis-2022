USE master;
GO

IF EXISTS(select * from sys.databases where name='Szallasfoglalas')
	DROP DATABASE Szallasfoglalas

SET DATEFORMAT YMD

CREATE DATABASE Szallasfoglalas;
GO

USE Szallasfoglalas;
GO

set dateformat ymd;

CREATE TABLE Orszagok(
	OKod INT PRIMARY KEY IDENTITY,
	ONev VARCHAR(30)
);

INSERT INTO Orszagok VALUES ('Romania'); --1
INSERT INTO Orszagok VALUES ('Magyarorszag'); --2
INSERT INTO Orszagok VALUES ('Ausztria'); --3
INSERT INTO Orszagok VALUES ('Olaszorszag'); --4
INSERT INTO Orszagok VALUES ('Franciaorszag'); --5
INSERT INTO Orszagok VALUES ('Csehorszag'); --6

CREATE TABLE Helysegek(
	HKod INT PRIMARY KEY IDENTITY,
	HNev VARCHAR(30) UNIQUE(HNev),
	OKod INT REFERENCES Orszagok (OKod)
);

INSERT INTO Helysegek VALUES ('Szovata',1);
INSERT INTO Helysegek VALUES ('Tusnad',1);
INSERT INTO Helysegek VALUES ('Gyula',2);
INSERT INTO Helysegek VALUES ('H-Szoboszlo',2);
INSERT INTO Helysegek VALUES ('Rimini',4);
INSERT INTO Helysegek VALUES ('Catania',4);
INSERT INTO Helysegek VALUES ('Praga',6);
INSERT INTO Helysegek VALUES ('Kolozsvar',1);

CREATE TABLE Turistak(
	TID INT PRIMARY KEY IDENTITY, 
	TNev VARCHAR(30),
	TTelefon VARCHAR(15),
	EmailCim VARCHAR(30) UNIQUE(EmailCim),
	HKod INT references Helysegek(HKod)
);

INSERT INTO Turistak VALUES('Antal Attila', NULL, 'attila@email.com', 1)
INSERT INTO Turistak VALUES('Kelemen Melinda', NULL, 'melinda@email.com', 3)
INSERT INTO Turistak VALUES('Gergely Emese', NULL, 'emese@email.com', 2)
INSERT INTO Turistak VALUES('Kovacs Annamaria', NULL, 'anna@email.com', 4)
INSERT INTO Turistak VALUES('Horvath Levente', NULL, 'levente@email.com',2)
INSERT INTO Turistak VALUES('Bereczki Gergely', NULL, 'gergely@email.com',1)
INSERT INTO Turistak VALUES('Incze Istvan', NULL, 'istvan@email.com', 4)
insert into Turistak values('Nagy Ildiko',NULL,'ildiko@email.com', 2);
insert into Turistak values('Magyar Julia',NULL,'juli@email.com', 8);
insert into Turistak values('Magyar Julia',NULL,'mjuli@email.com', 1);

CREATE TABLE Szallastipusok(
	SzTID INT PRIMARY KEY IDENTITY,
	SzTNev VARCHAR(30) UNIQUE(SzTNev)
);

INSERT INTO Szallastipusok VALUES ('hotel'), 
							  	  ('panzio'), 
								  ('apartman'), 
								  ('hostel')

CREATE TABLE Szallasok(
	SzID INT PRIMARY KEY IDENTITY,
	SzNev VARCHAR(30),
	HKod INT REFERENCES Helysegek(HKod),
	SztID INT REFERENCES Szallastipusok(SztID),
	Csillag INT, 
	SzobakSzama INT,
	NapiAr INT
);

INSERT INTO Szallasok VALUES ('Szallas1',  3, 1, 2, 5, 25)
INSERT INTO Szallasok VALUES ('Szallas2',  4, 2, 2, 10, 20)
INSERT INTO Szallasok VALUES ('Szallas3',  4, 3, 4, 5, 31)
INSERT INTO Szallasok VALUES ('Szallas4',  4, 1, 3, 10, 20)
INSERT INTO Szallasok VALUES ('Szallas5',  3, 3, 3, 10, 12)
INSERT INTO Szallasok VALUES ('Szallas6',  1, 1, 5, 12, 10)
INSERT INTO Szallasok VALUES ('Szallas7',  4, 2, 3, 5, 12)
INSERT INTO Szallasok VALUES ('Szallas8',  2, 1, 5, 5, 20)
INSERT INTO Szallasok VALUES ('Szallas9',  4, 2, 3, 100, 12)
INSERT INTO Szallasok VALUES ('Szallas10',  2, 1, 2, 120, 30)
INSERT INTO Szallasok VALUES ('Szallas11',  1, 3, 3, 60, 25)
INSERT INTO Szallasok VALUES ('Szallas12',  2, 3, 2, 80, 35)
INSERT INTO Szallasok VALUES ('Szallas13',  3, 2, 3, 20, 10)
INSERT INTO Szallasok VALUES ('Szallas14',  4, 1, 3, 16, 50)
INSERT INTO Szallasok VALUES ('Szallas15',  5, 1, 3, 20, 50)
INSERT INTO Szallasok VALUES ('Szallas16',  6, 2, 2, 40, 15)
INSERT INTO Szallasok VALUES ('Szallas17',  7, 2, 2, 40, 15)
INSERT INTO Szallasok VALUES ('Szallas18',  8, 2, 2, 40, 15)

CREATE TABLE Foglalasok(
	FoglalasID INT PRIMARY KEY IDENTITY,
	SzID INT REFERENCES Szallasok (SzID),
	TID INT REFERENCES Turistak (TID),
	KezdDatum DATETIME,
	NapokSzama INT,
	Osszar INT 
);

INSERT INTO Foglalasok VALUES (3, 1, '2018-06-20', 5, 150)
INSERT INTO Foglalasok VALUES (3, 2, '2018-06-30', 2, 50)
INSERT INTO Foglalasok VALUES (3, 5, '2018-07-12', 3, 60)
INSERT INTO Foglalasok VALUES (1, 7, '2018-06-20', 5, 150)
INSERT INTO Foglalasok VALUES (3, 3, '2018-07-20', 2, 50)
INSERT INTO Foglalasok VALUES (3, 4, '2018-07-27', 3, 60)
INSERT INTO Foglalasok VALUES (1, 1, '2018-07-20', 5, 150)
INSERT INTO Foglalasok VALUES (3, 5, '2018-08-05', 2, 250)
INSERT INTO Foglalasok VALUES (1, 2, '2018-06-25', 13, 60)
INSERT INTO Foglalasok VALUES (5, 8, '2018-08-01', 14, 168)
INSERT INTO Foglalasok VALUES (2, 4, '2018-06-20', 6, 120)
INSERT INTO Foglalasok VALUES (14, 3, '2018-07-02', 5, 250)

INSERT INTO Foglalasok VALUES (3, 1, '2017-06-20', 5, 150)
INSERT INTO Foglalasok VALUES (3, 2, '2017-06-30', 2, 50)
INSERT INTO Foglalasok VALUES (3, 5, '2017-07-12', 3, 60)
INSERT INTO Foglalasok VALUES (1, 7, '2017-06-20', 5, 150)
INSERT INTO Foglalasok VALUES (3, 3, '2017-07-20', 2, 50)
INSERT INTO Foglalasok VALUES (3, 4, '2017-07-27', 3, 60)
INSERT INTO Foglalasok VALUES (1, 1, '2017-07-20', 5, 150)
INSERT INTO Foglalasok VALUES (3, 5, '2017-08-05', 2, 250)
INSERT INTO Foglalasok VALUES (1, 2, '2017-06-25', 13, 60)
INSERT INTO Foglalasok VALUES (6, 3, '2017-07-26', 10, 100)
INSERT INTO Foglalasok VALUES (5, 4, '2017-08-01', 10, 120)
INSERT INTO Foglalasok VALUES (2, 4, '2017-06-20', 6, 120)
INSERT INTO Foglalasok VALUES (14, 6, '2017-07-02', 5, 250)

INSERT INTO Foglalasok VALUES (3, 1, '2018-09-20', 5, 150)
INSERT INTO Foglalasok VALUES (3, 2, '2018-09-30', 2, 50)
INSERT INTO Foglalasok VALUES (3, 5, '2018-10-12', 3, 60)
INSERT INTO Foglalasok VALUES (1, 7, '2018-10-20', 5, 150)
INSERT INTO Foglalasok VALUES (3, 3, '2018-04-20', 2, 50)
INSERT INTO Foglalasok VALUES (3, 4, '2018-01-27', 3, 60)
INSERT INTO Foglalasok VALUES (1, 1, '2018-02-20', 5, 150)
INSERT INTO Foglalasok VALUES (3, 5, '2018-03-05', 2, 250)
INSERT INTO Foglalasok VALUES (1, 2, '2018-10-26', 13, 60)
INSERT INTO Foglalasok VALUES (6, 7, '2018-04-05', 13, 60)
INSERT INTO Foglalasok VALUES (5, 5, '2018-03-10', 10, 120)
INSERT INTO Foglalasok VALUES (14, 3, '2018-07-20', 5, 250)
INSERT INTO Foglalasok VALUES (17, 8, '2018-08-10', 10, 120)
INSERT INTO Foglalasok VALUES (18, 9, '2018-08-20', 5, 250)

INSERT INTO Foglalasok VALUES (17, 10, '2020-03-15', 3, 250)

--Tesztadatok
/*
--parameterek: pSzallodaID, pTuristaNév, pTTel, pTemail, pDatumKezdo, pDatumVegso
--van hely az adott szallodaban:
--a kovetkezo szallodak eseten barmilyen periodusban: 7,8,9,10,11,12,14,15,16
--mas pelda:
14,'Szilagyi Jeno', '0732067895','jeno@yahoo.com', '2011-07-02','2011-07-07'
--visszateritesi ertek: 0
--SzobaSzam-5,...,15
--Ertek-250 (5-os szoba eseten)

--nincs hely az adott szallodaban:
--van hely mas szallodaban:
5,'Szilagyi Jeno', '0732067895','jeno@yahoo.com', '2011-08-01','2011-08-11' 
--@outSzalloda erteke-pl. 2,13
--visszateritesi ertek: -1

3,'Szilagyi Jeno', '0732067895','jeno@yahoo.com', '2011-06-20','2011-06-25' 
--@outSzalloda erteke - pl. 2,4,7,9,14
--visszateritesi ertek: -1

--nincs hely mas szallodaban sem:
1,'Szilagyi Jeno', '0732067895','jeno@yahoo.com', '2011-06-20','2011-06-25'
--visszateritesi ertek: 2

*/

