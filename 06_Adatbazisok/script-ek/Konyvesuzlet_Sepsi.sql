USE master;
GO
IF EXISTS(SELECT * FROM sys.databases WHERE name='Konyvesuzlet_Sepsi')
	DROP DATABASE Konyvesuzlet_Sepsi

CREATE DATABASE Konyvesuzlet_Sepsi;
GO
USE Konyvesuzlet_Sepsi;
GO


CREATE TABLE Kiadok (
  KiadoID INT IDENTITY 
	CONSTRAINT PK_Kiadok_KiadoID PRIMARY KEY(KiadoID),
  KNev VARCHAR(30),
  KHelyseg VARCHAR(30),
  KUtcaNev VARCHAR(30),
  KHazSzam INT,
  KTelefon CHAR(10) DEFAULT '1234567890');

CREATE TABLE Mufajok (
  MufajID INT IDENTITY 
	CONSTRAINT PK_Mufajok_MufajID PRIMARY KEY(MufajID),
  MufajNev VARCHAR(30) NOT NULL UNIQUE(MufajNev));

CREATE TABLE Szerzok (
  SzerzoID INT IDENTITY 
	CONSTRAINT PK_Szerzok_SzerzoID PRIMARY KEY(SzerzoID),
  SzerzoNev VARCHAR(30) NOT NULL,
  SzuletesiEv INT,
  Neme CHAR(1) CHECK (Neme in ('f','n')));

CREATE TABLE Konyvek (
  KonyvKod INT IDENTITY 
	CONSTRAINT PK_Konyvek_KonyvKod PRIMARY KEY(KonyvKod),
  KonyvCim VARCHAR(50) NOT NULL,
  MegjEv INT,
  Oldalszam INT, 
  KiadoID INT 
	CONSTRAINT FK_Konyvek_KiadoID_Kiadok_KiadoID FOREIGN KEY (KiadoID) REFERENCES Kiadok(KiadoID),
  Peldanyszam INT,
  EladasiAr FLOAT 
	CONSTRAINT CK_EladasiAr CHECK (EladasiAr>0)); 

CREATE TABLE KonyvMufajok(
	KonyvKod INT 
		CONSTRAINT FK_KonyvMufajok_KonyvKod_Konyvek_KonyvKod FOREIGN KEY(KonyvKod) REFERENCES Konyvek(KonyvKod),
	MufajID INT 
	CONSTRAINT FK_KonyvMufajok_MufajID_Mufajok_MufajID FOREIGN KEY (MufajID) REFERENCES Mufajok(MufajID),
	CONSTRAINT PK_KonyvMufajok_KonyvKod_MufajID PRIMARY KEY(KonyvKod,MufajID));

CREATE TABLE KonyvSzerzok(
	KonyvKod INT 
		CONSTRAINT FK_KonyvSzerzok_KonyvKod_Konyvek_KonyvKod FOREIGN KEY(KonyvKod) REFERENCES Konyvek(KonyvKod),
	SzerzoID INT 
		CONSTRAINT FK_KonyvSzerzok_SzerzoID_Szerzok_SzerzoID FOREIGN KEY(SzerzoID) REFERENCES Szerzok(SzerzoID),
	CONSTRAINT PK_KonyvSzerzok_KonyvKod_SzerzoID PRIMARY KEY(KonyvKod,SzerzoID) );

CREATE TABLE Vasarlok(
	VasarloID INT IDENTITY 
		CONSTRAINT PK_Vasarlok_VasarloID PRIMARY KEY(VasarloID),
	VNev VARCHAR(30) NOT NULL,
    VCim VARCHAR(30),
    VTelefon CHAR(10),
    TorzsvasarloE BIT);

CREATE TABLE Vasarlasok(
    VID INT IDENTITY
		CONSTRAINT PK_Vasarlasok_VID PRIMARY KEY(VID),
    VasarloID INT
		CONSTRAINT FK_Vasarlasok_VasarloID_Vasarlok_VID FOREIGN KEY(VasarloID) REFERENCES Vasarlok(VasarloID),
    Datum datetime DEFAULT GETDATE(), 
	Vegosszeg FLOAT
		CONSTRAINT CK_Vegosszeg CHECK (Vegosszeg>0));

CREATE TABLE VasarlasTartalma(
    VasarlasID INT 
		CONSTRAINT FK_VasarlasTartalma_VasarlasID_Vasarlasok_VID FOREIGN KEY(VasarlasID) REFERENCES Vasarlasok(VID),
    KonyvKod INT 
		CONSTRAINT FK_VasarlasTartalma_KonyvKod_Konyvek_KonyvKod FOREIGN KEY(KonyvKod) REFERENCES Konyvek(KonyvKod),
	Mennyiseg INT,
	Kedvezmeny FLOAT --adott vasarlas eseten a konyvre szamitott kedvezmeny;
					 --0 es 1 kozotti ertek (pl. 0.1->10% kedvezmeny)
		CONSTRAINT CK_Kedvezmeny CHECK (Kedvezmeny BETWEEN 0 AND 1), 		
	PRIMARY KEY(VasarlasID,KonyvKod));

INSERT INTO Kiadok(KNev,KHelyseg,KUtcaNev, KHazSzam) VALUES 
			('Móra Ferenc Könyvkiadó','Budapest','Vaci',19),    --1
			('PALATINUS','Budapest','Raho',16),				    --2
			('Könyvmolyképző Kiadó','Szeged','Gal',18),			--3
			('Animus Kiadó','Budapest','Nemtudni',1301),		--4
			('Ulpius Kiadó','Budapest','Pozsonyi',20),			--5
			('Roxfort KönyvKiadó','Roxsmorts','Főút', 13),		--6 
			('Alexandra Kiadó', 'Budapest','Szentmihályi út',13),--7
			('Ábel Kiadó', 'Kolozsvár','Tipografiei',21),		--8
			('Viking','New York','Nemtudni',1301),				--9
			('Houghton Mifflin Harcourt', 'Boston','Nemtudni',1301) --10

INSERT INTO Mufajok VALUES 
	('regeny'),  						--1
	('ifjúsági'),						--2
	('sci-fi'),							--3
	('verseskötet'),					--4
	('tankönyv'),						--5
	('horror'),							--6
	('disztópikus fikció'),				--7
	('fantasy');						--8

INSERT INTO Szerzok(SzerzoNev) VALUES 
	 ('Szabó Magda'),			--1
	 ('FEKETE ISTVÁN'),			--2
	 ('Takács Imre'),			--3
	 ('Lengyel Balázs'),		--4
	 ('CHARLOTTE BRONTE'),		--5
	 ('FEHÉR KLÁRA'),			--6
	 ('Robert Louis Stevenson'),--7
	 ('J. K. Rowling'),			--8
	 ('Jane Austen'),			--9
	 ('Emily Bronte'),			--10
	 ('Méhes György'),			--11
	 ('John Galsworthy'),		--12
	 ('Minerva McGalagony'),	--13
	 ('Perselus Piton'),		--14
     ('Albus Dumbledore'),		--15
	 ('George R.R. Martin'),	--16
	 ('Stephen King'),			--17
	 ('Lois Lowry'),			--18
	 ('John Doe'),				--19
	 ('Filius Flitwick');		--20
	 	 
INSERT INTO Konyvek(KonyvCim, MegjEv, Oldalszam, KiadoID, Peldanyszam, EladasiAr) VALUES 
	('Abigél',2002,300, 1, 25, 45.5),								--1
	('Vuk',2005,100, 1, 10, 23.5),									--2
    ('Kölcsey Ferenc válogatott versei',1957,150, 1, 10, 12.75),    --3
	('József Attila válogatott versei',1977,150, 1, 1, 15),			--4
	('Jane Eyre',2007,450, 2, 3, 55),								--5
	('BEZZEG AZ ÉN IDŐMBEN',2004,200,3, 12, 35),					--6
	('Kincses sziget',2007,300,3, 12, 29.99),						--7	
	('Harry Potter es a Fonix Rendje',2005,500,4, 26, 35),			--8
	('Harry Potter es a Felver Herceg',2006,600,4, 10, 55),			--9
	('HARRY POTTER ÉS A HALÁL EREKLYÉI',2008,700,4, 15, 65),		--10
	('Buszkeseg és balitelet',2006,250,5, 5, 39.99),				--11
	('Uvolto szelek',2006,300,5, 5, 27.99),							--12
	('Szép szerelmek krónikája',2006,250,5, 5, 35.99),				--13
	('Értelem és érzelem',2006,300,5, 10, 55.99),					--14
	('A Forsyte-Saga',2006,250,5, 12, 33),							--15
	('Bűbájtan tankönyv', 2000, 200, 6, 25, 36),					--16
	('Átváltoztatástan tankönyv', 2001, 250, 6, 25, 36),			--17
	('Felkészítő az RBF-re (Roxfort)', 2004, 450, 6, 25, 75),		--18
	('Trónok harca', 2018, 925, 7, 25, 72),							--19
	('It', 1986, 1138, 9, 12, 78),									--20
	('The Giver', 1993, 136, 10, 5, 42),							--21
	('Bájitaltan tankönyv', 2000, 200, 6, 25, 36)					--22
		
INSERT INTO KonyvMufajok VALUES (1,1),  (2,2), (3,4),
							 (4,4),  (5,1),  (6,2),
							 (7,1),  (8,4),  (9,4),
							 (10,4), (11,1), (12,1),
							 (13,1), (14,1), (15,1),
							 (16,5), (17,5), (18,5),
							 (19,8), (20,6), (21,1), (21,2), (21,7);
							 
INSERT INTO KonyvSzerzok VALUES (1,1), (2,2), (3,3),
							(4,4), (5,5), (6,6),
							(7,7), (8,8), (9,8),
							(10,8), (11,9), (12,10),
							(13,11),(14,9),(15,12),
							(16,20), (17,13), 
							(18,13), (18,14), (18,15), (18,20), 
							(19,16),
							(20,17), (21,18), (22,14);

INSERT INTO Vasarlok(VNev, VCim) VALUES('Pista','Kolozsvar, Dorobantilor, 32'),
					('Kati','Nagykaroly,Agoston 100'),
					('Albus Dumbledore','Roxfort'),
					('Hermione Granger','London'),
					('Jolán','Szatmarnemeti,Karolyi,34');

INSERT INTO Vasarlasok(VasarloID, Datum, Vegosszeg) VALUES 
		(1,'10/12/2017', 200), --1
		(2,'9/20/2018', 100),  --2
		(2,'11/10/2019', 50), --3
        (3,'9/28/2018', 170),  --4
		(4,'9/27/2018', 200),  --5
		(4,'10/20/2019', 100), --6
		(2,'11/01/2020', 75), --7
		(1,'10/10/2020', 50), --8
		(1,'05/06/2020', 35) --9
				
INSERT INTO VasarlasTartalma(VasarlasID, KonyvKod, Mennyiseg, Kedvezmeny) VALUES 
		(1, 1, 5, 0.0), (1, 5, 6, 0.01), (1, 8, 1, 0.1), (1, 19, 1, 0.0),
		(2, 2, 2, 0.0), 
        (3, 14, 1, 0.0), (3, 12, 5, 0.0), (3, 19,1, 0.1),
		(4, 16, 1, 0.5), (4, 17, 1, 0.4), (4, 18,1, 0.0),(4, 1, 2, 0.2),
		(5, 14, 1, 0.0), (5, 16, 1, 0.0), (5, 18, 1, 0.15), (5, 22, 1, 0.25),
		(6, 2, 1, 0.05), (6, 12, 4, 0.03), (6, 19, 1, 0.01);