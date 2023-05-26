USE master;
GO

IF EXISTS(select * from sys.databases where name='Lab6')
	DROP DATABASE Lab6

CREATE DATABASE Lab6;
GO

USE Lab6;
GO


CREATE TABLE Markak (
	MarkaID INT IDENTITY
		CONSTRAINT MarkaID_PK PRIMARY KEY(MarkaID),
	MarkaNev VARCHAR(30),
);

CREATE TABLE Tipusok (
	TipusID INT IDENTITY 
		CONSTRAINT TipusID_PK PRIMARY KEY(TipusID),
	TipusNev VARCHAR(30) NOT NULL
		CONSTRAINT TipusNev_U UNIQUE(TipusNev),
	MarkaID INT
		CONSTRAINT MarkaID_FK FOREIGN KEY(MarkaID) REFERENCES Markak(MarkaID)
);

CREATE TABLE Szinek (
	SzinKod INT IDENTITY
		CONSTRAINT SzinKod_PK PRIMARY KEY(SzinKod),
	SzinNev VARCHAR(10) NOT NULL
		CONSTRAINT SzinNev_U UNIQUE(SzinNev)
)

CREATE TABLE Autok (
	AutoKod INT IDENTITY
		CONSTRAINT AutoKod_PK PRIMARY KEY(AutoKod),
	Rendszam VARCHAR(10) 
		CONSTRAINT Rendszam_U UNIQUE(Rendszam),
	TipusID INT
		CONSTRAINT TipusID_FK FOREIGN KEY (TipusID) REFERENCES Tipusok(TipusID),
	SzinKod INT
		CONSTRAINT SzinKod_FK FOREIGN KEY (SzinKod) REFERENCES Szinek(SzinKod),
	GyartasiEv INT DEFAULT 1920,
	NapiDij INT 
		CONSTRAINT NapiDij_CK CHECK (NapiDij > 0),
	Csillag INT
		CONSTRAINT Csillag_CK CHECK (Csillag BETWEEN 1 AND 5)	
);

CREATE TABLE Extrak (
	ExtraID INT IDENTITY
		CONSTRAINT ExtraID_PK PRIMARY KEY(ExtraID),
	ExtraNev VARCHAR(30)
		CONSTRAINT ExtraNev_U UNIQUE(ExtraNev)
);

CREATE TABLE AutoExtraja (
	AutoKod INT	
		CONSTRAINT AutoID_FK FOREIGN KEY(AutoKod) REFERENCES Autok(AutoKod),
	ExtraID INT
		CONSTRAINT ExtraID_FK FOREIGN KEY(ExtraID) REFERENCES Extrak(ExtraID),
		CONSTRAINT AutoExtraja_PK PRIMARY KEY(AutoKod,ExtraID)
);

CREATE TABLE Berlok (
	BerloID INT IDENTITY		
		CONSTRAINT Berlok_PK PRIMARY KEY(BerloID),
	SzemSzam CHAR(13)
		CONSTRAINT SzemSzam_U UNIQUE(SzemSzam),
	Nev VARCHAR(50) NOT NULL,
	Helyseg VARCHAR(50),
	Cim VARCHAR(50),
	SzulDatum DATE DEFAULT '1900-01-01',
	Telefonszam CHAR(10),
);

CREATE TABLE Berel (
	BerlesID INT IDENTITY
		CONSTRAINT BerlesID_PK PRIMARY KEY(BerlesID),
	AutoKod INT
		CONSTRAINT AutoKod_FK FOREIGN KEY(AutoKod) REFERENCES Autok(AutoKod),
	BerloID INT
		CONSTRAINT BerloID_FK FOREIGN KEY(BerloID) REFERENCES Berlok(BerloID),
	Mikortol date NOT NULL,
	Meddig date,
	Ar INT
);

insert into Markak values ('Audi'),		 --1
						  ('BMW'),		 --2
						  ('Dacia'),	 --3
						  ('Ford'),		 --4
						  ('Kia'),		 --5
						  ('Opel'),		 --6
						  ('Suzuki'),	 --7
						  ('Volkswagen') --8

insert into Tipusok values ('Passat', 8),	--1
						   ('Astra',6),		--2
						   ('Focus',4),		--3
						   ('Logan',3),		--4
						   ('Polo',8),		--5
						   ('A4',1),		--6
						   ('Sandero',3),	--7
						   ('Mondeo',4)		--8

insert into Szinek values ('feher'),		--1
						  ('kek'),			--2
						  ('piros'),		--3
						  ('szurke')		--4

insert into Autok values ('AR 11ABB',5,4,2008,30,3),	--1
						 ('B 10AAA',3,1,1998,60,2),		--2
						 ('B 11AAA',1,3,2011,55,4),		--3
						 ('B 20ZZA',2,3,2010,45,5),		--4
						 ('BR 90HFD',4,1,2000,35,1),	--5
						 ('BR 99EGR',3,3,1995,60,2),	--6
						 ('CJ 11BBB',5,4,2001,30,5),	--7
						 ('HD 78OER',2,4,2006,45,3),	--8
						 ('SM 12AAA',1,2,2000,55,4),	--9
						 ('SM 57EWW',1,3,1990,55,5),	--10
						 ('HR 78QWE',2,2,1999,45,1),	--11
						 ('CJ 58EWG',7,3,2005,40,1),	--12
						 ('HD 13OOO',6,1,2006,70,4),	--13
						 ('SM 13OOO',6,1,2006,70,5),	--14
						 ('SM 14ABC',2,1,2016,90,5)		--15

insert into Extrak values ('Klima'),		--1
						  ('CD-lejatszo'),	--2
						  ('Legzsak'),		--3
						  ('Autoriaszto'),	--4
						  ('GPS');			--5

insert into AutoExtraja values (4,1),(4,2), (4,3), (4,5),
							   (9,1), (9,4),
							   (8,4), (8,5), 
							   (3,5);

insert into Berlok values 
	('1980305235445','Szilagyi Jeno','Kolozsvar','Scortarilor 79','1998-03-05','0732067895'),       --1
	('1580703343678','Andras Mihaly','Kolozsvar','Gr. Alexandrescu 5','1958-07-03', '0264435672'),  --2
	('2651220674387','Kiraly Aliz','Kolozsvar','Unirii 1','1965-12-20','0264789678'),			    --3
	('1710101102031','Csizmar Karoly','Nagyvarad','Closca 90','1971-01-01','0260361739'),		    --4
	('1860428679432','Balogh Imre','Kolozsvar','Paris 3','1986-04-28','0728345678'),				--5
	('2860709345213','Andras Hannah','Kolozsvar','Gr. Alexandrescu 5', '1986-07-09','0264435672'),	--6
	('1880123134389','Andor Zoltan','Szatmarnemeti','Karolyi ut 34','1988-01-23','0780345678'),		--7
	('1880919433235','Andras Ferenc','Kolozsvar','Gr. Alexandrescu 5','1988-09-19','0264435672'),	--8	
	('2491030269678','Nagy Ildiko','Kolozsvar','Motilor 2','1949-10-30','0751234786'),				--9
	('2900624986747','Kollo Ingrid','Csikszereda','Somesul 67','1990-06-24','0261868685');			--10

insert into Berel values (4,4,'01/10/2018','01/17/2018',300),
						 (9,4,'06/10/2018','07/10/2018',1650),
						 (4,5,'10/10/2018','10/11/2018',45),
						 (6,5,'09/25/2018','09/28/2018',180),
						 (6,5,'10/24/2018','10/25/2018',60),
						 (8,6,'08/20/2018','08/30/2018',450),
						 (7,10,'08/20/2018','08/30/2018',300),
						 (10,3,'10/24/2018','10/31/2018',385)

