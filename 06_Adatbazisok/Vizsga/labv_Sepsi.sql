USE master;
GO
IF EXISTS(SELECT * FROM sys.databases WHERE name='Labv_Sepsi')
	DROP DATABASE Labv_Sepsi

CREATE DATABASE Labv_Sepsi;
GO
USE Labv_Sepsi;
go 

CREATE TABLE Betegek (
  BetegID INT PRIMARY KEY IDENTITY, 
  CNP NCHAR(13),
  BetegVNev VARCHAR(20),
  BetegKNev VARCHAR(20),
  Helyseg VARCHAR(20),
  Utca VARCHAR(20),
  Hazszam INT,
  BetegTfSzam NCHAR(10),
  RegDatuma DATE
);

CREATE TABLE Fogorvosok (
  OrvosID INT IDENTITY PRIMARY KEY,
  OrvosVNev VARCHAR(20),
  OrvosKNev VARCHAR(20),
  OrvosTfSzam NCHAR(10)  
);

CREATE TABLE Kezelesek (
  KezelesID INT PRIMARY KEY IDENTITY,
  KezelesNev VARCHAR(30),
  KezelesAr INT
);

CREATE TABLE KezeloHelyisegek (
  SzobaSzam INT PRIMARY KEY IDENTITY,
  Alapterulet INT,
  Emelet TINYINT
);

CREATE TABLE Elojegyzesek (
  ElojegyzesID INT PRIMARY KEY IDENTITY,
  Datum DATE,
  Ora INT,
  BetegID INT REFERENCES Betegek(BetegID),
  OrvosID INT REFERENCES Fogorvosok(OrvosID),
  Szobaszam INT REFERENCES KezeloHelyisegek(SzobaSzam)
);

CREATE TABLE Kezel (
	ElojegyzesID INT REFERENCES Elojegyzesek(ElojegyzesID),
	KezelesID INT REFERENCES Kezelesek(KezelesID),
    PRIMARY KEY(ElojegyzesID, KezelesID)
);

INSERT INTO Betegek (CNP,BetegVNev,BetegKNev,Helyseg,Utca,Hazszam,BetegTfSzam, RegDatuma) 
	values ('1570305235445','Szilagyi','Jeno','Kolozsvar','Scortarilor',79,'0732067895', '2015-05-15'),		 --1
			('1580703343678','Andras','Mihaly','Kolozsvar','Gr. Alexandrescu',5,'0264435672', '2015-05-30'), --2
			('1651220674387','Kiraly','Lorand','Kolozsvar','Unirii',1,'0264789678', '2014-01-01'),           --3
			('1740518284529','Csizmar','Karoly','Nagyvarad','Closca',90,'0260361739', '2017-10-10'),		 --4
			('1860428679432','Balogh','Imre','Kolozsvar','Paris',3,'0728345678', '2015-05-11'),				 --5
			('1860709345213','Andras','Hannah','Kolozsvar','Gr. Alexandrescu',5,'0264435672', '2022-03-25'), --6
			('1880123134389','Andor','Zoltan','Kolozsvar','Fantanele',34,'0780345678', '2022-06-10'),		 --7
			('1880919433235','Andras','Ferenc','Kolozsvar','Gr. Alexandrescu',5,'0264435672', '2014-12-15'), --8
			('2491030269678','Nagy','Ildiko','Kolozsvar','Motilor',2,'0751234786', '2022-06-12'),			 --9
			('2590428453222','Andras','Katalin','Kolozsvar','Gr. Alexandrescu',5,'0264435672', '2016-03-02'),--10
			('2800101100011','Kis','Mariann','Kolozsvar','Izlazului',25,'0264435672', '2017-07-15'),		 --11
			('2670928235673','Kiraly','Piroska','Kolozsvar','Unirii',1,'0264789678', '2017-02-01'),			 --12
			('2800329834563','Czinege','Boglarka','Kolozsvar','Lalelelor',45,'0732034675', '2022-01-15'),	 --13
			('2800101100000','Marosi','Eszter','Kolozsvar','Dorobantilor',28,'0740234856', '2020-04-01'),	 --14
			('2900109435235','Petok','Ilona','Csikszereda','Paris',1,'0728798789', '2022-02-10'),			 --15
			('2920624986747','Kollo','Ingrid','Szatmarnemeti','somesul',67,'0261868685', '2022-08-10'),		 --16
			('2820624986748','Kollo','Ingrid','Nagyvarad','Crisul',55,'0730123456', '2019-01-10')			 --17


insert into KezeloHelyisegek (Alapterulet,Emelet) values 
			(10,0), (34,1), (20,1), 
			(15,1), (12,2), (27,2);

insert into Kezelesek (KezelesNev, KezelesAr) values 
			('Altalanos fogtomes', 40),
			('Altalanos vizsgalat', 35),
			('Foghuzas', 60),
			('Mufogsor', 500),
			('Rontgen keszitese', 20),
			('Fogfeherites', 55),
			('Gyokerkezeles', 100),
			('Korona', 200),
			('Fogszabalyzo feltetele', 1000),
			('Fogszabalyzo allitasa', 15);

insert into Fogorvosok (OrvosVNev,OrvosKNev,OrvosTfSzam) values 
			('Nagy','Istvan','0734583729'),
			('Mako','Izabella','0745928346'),
			('Leiber','Otto','0751948441');

insert into Elojegyzesek (Datum,Ora,BetegID,OrvosID, SzobaSzam) values 
		('10/12/2022',12,14,1,2),
		('10/12/2022',13,5,2,2),
		('10/15/2022',15,11,2,3),
		('10/16/2022',13,3,1,6),
		('10/19/2022',11,5,3,4),
		('10/21/2022',9,16,1,3),
		('10/22/2021',14,7,1,4),
		('10/24/2021',17,16,1,1),
		('10/26/2021',10,8,2,2),
		('10/26/2021',12,13,2,3),
		('10/27/2021',16,3,1,2),
		('10/27/2021',16,10,3,4),
		('10/27/2022',11,4,1,1),
		('10/27/2022',11,2,2,2),
		('10/27/2022',12,6,2,2),
		('10/29/2021',9,7,3,1),
		('10/29/2021',16,10,3,6),
		('10/29/2021',11,1,3,4),
		('11/1/2021',10,9,1,2),
		('11/2/2021',16,10,3,6),
		('11/17/2021',17,4,1,5),
		('11/17/2021',17,12,2,1),
		('11/19/2021',10,15,2,1),
		('11/22/2021',11,16,1,1),
		('11/22/2021',10,15,2,3),
		('11/26/2021',9,4,1,2),
		('12/2/2021',16,13,3,6),
		('12/3/2022',16,4,1,3),
		('12/3/2022',17,4,2,4),
		('12/3/2022',18,4,3,6),
		('12/4/2022',18,4,3,6);

insert into Kezel (ElojegyzesID,KezelesID) values 
				(1,3), (2,1), (2,6), (3,2), (3,5),
				(5,2), (5,5), (6,4), (8,1), (8,5),
				(10,10), (10,3), 
				(11,1), (11,2), 
				(13,2), (13,5), (14,10), (15,6), 
				(16,6), (22,4), (25,1), (25,4), (27,6);
