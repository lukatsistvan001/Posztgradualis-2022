-- I. Hozzuk létre SQL-ben az alábbi táblákat (vigyázzunk a megszorításokra).

-- EkszerTipusok (EkszerTipusID, EkszerTipusNev, Leiras)
CREATE TABLE EkszerTipusok(
	EkszerTipusID INT CONSTRAINT EkszerTipusID_PK PRIMARY KEY,
	EkszerTipusNev VARCHAR(30),
	Leiras VARCHAR(50)
	);

-- Anyagok (AnyagID, AnyagNev, EgysegAr)
CREATE TABLE Anyagok(
	AnyagID INT CONSTRAINT AnyagID_PK PRIMARY KEY,
	AnyagNev VARCHAR(30),
	EgysegAr MONEY
	);

-- Gyartok (GyartoID, GyartoCegNev, Varos)
CREATE TABLE Gyartok(
	GyartoID INT CONSTRAINT GyartoID_PK  PRIMARY KEY,
	GyartoCegNev VARCHAR(30),
	Varos VARCHAR(50)
	);

-- Ekszerek (EkszerID, EkszerNev, GyartoID, EkszerTipusID, EkszerAnyagID, Leiras, Ar, DbSzam)
CREATE TABLE Ekszerek(
	EkszerID INT CONSTRAINT EkszerID_PK PRIMARY KEY,
	EkszerNev VARCHAR(30),
	Leiras VARCHAR(50),
	Ar MONEY,
	DbSzam INT,
	GyartoID INT CONSTRAINT GyartoID_FK FOREIGN KEY REFERENCES Gyartok(GyartoID),
	EkszerTipusID INT CONSTRAINT EkszerTipusID_FK FOREIGN KEY REFERENCES EkszerTipusok(EkszerTipusID),
	EkszerAnyagID INT CONSTRAINT EkszerAnyagID_FK FOREIGN KEY REFERENCES Anyagok(AnyagID)
	);

-- Megrendelok (MegrendeloID, MegrendeloNev)
CREATE TABLE Megrendelok(
	MegrendeloID INT CONSTRAINT MegrendeloID_PK PRIMARY KEY,
	MegrendeloNev VARCHAR(30)
	);

-- Rendelesek (RendelesID, MegrendeloID, Datum, Vegosszeg)
CREATE TABLE Rendelesek(
	RendelesID INT CONSTRAINT RendelesID_PK PRIMARY KEY,
	MegrendeloID INT CONSTRAINT MegrendeloID_FK FOREIGN KEY REFERENCES Megrendelok(MegrendeloID),
	Datum DATE,
	Vegosszeg MONEY
	);

-- RendeltEkszerek (RendelesID, EkszerID, Mennyiseg)
CREATE TABLE RendeltEkszerek(
	RendelesID INT CONSTRAINT RendelesID_FK FOREIGN KEY REFERENCES Rendelesek(RendelesID),
	EkszerID INT CONSTRAINT EkszerID_FK FOREIGN KEY REFERENCES Ekszerek(EkszerID),
	Mennyiseg INT,
	CONSTRAINT RendelesID_EkszerID_PK PRIMARY KEY(RendelesID, EkszerID)
	);

-- II. Generáljunk ki egy diagrammot!

 

-- III.

-- a. Szúrjunk be egy Neme mezőt a Megrendelok táblába (lehetséges értékek: ‘f’ és ‘n’)!
ALTER TABLE Megrendelok
ADD Neme CHAR(1) CONSTRAINT Neme_CK CHECK (Neme IN ('f', 'n'));

-- b. Állítsunk be a Mennyiség oszlopot alapértelmezetten 1-re.
ALTER TABLE RendeltEkszerek
ADD CONSTRAINT Mennyiseg_DF DEFAULT 1 FOR Mennyiseg;

-- c. Töröljük a Leiras oszlopot az EkszerTipusok táblából!
ALTER TABLE EkszerTipusok
DROP COLUMN Leiras;

-- d. Módosítsuk a Gyartok tábla Varos mezőjét, oly módon, hogy NE lehessen NULL!
ALTER TABLE Gyartok
ALTER COLUMN Varos VARCHAR(50) NOT NULL;

-- e. Módosítsuk az Ekszerek tabla DbSzam mezőjét, oly módon, hogy értéke 1 és 20 közötti legyen!
ALTER TABLE Ekszerek
ADD CONSTRAINT DbSzam_CK CHECK (DbSzam >=1 and DbSzam<=20)
 


-- IV. Szúrjunk be minden táblába legalább 3 sort!
INSERT INTO EkszerTipusok(EkszerTipusID, EkszerTipusNev) VALUES (1, 'gyűrű');
INSERT INTO EkszerTipusok(EkszerTipusID, EkszerTipusNev) VALUES (2, 'karkötő');
INSERT INTO EkszerTipusok(EkszerTipusID, EkszerTipusNev) VALUES (3, 'nyaklánc');

INSERT INTO Anyagok(AnyagID, AnyagNev, EgysegAr) VALUES (1, 'arany', 10);
INSERT INTO Anyagok(AnyagID, AnyagNev, EgysegAr) VALUES (2, 'ezüst', 5);
INSERT INTO Anyagok(AnyagID, AnyagNev, EgysegAr) VALUES (3, 'bronz', 3);

INSERT INTO Gyartok(GyartoID, GyartoCegNev, Varos) VALUES (1, 'Van Cleef', 'Párizs')
INSERT INTO Gyartok(GyartoID, GyartoCegNev, Varos) VALUES (2, 'Piaget', 'Zürich')
INSERT INTO Gyartok(GyartoID, GyartoCegNev, Varos) VALUES (3, 'Harry Winston', 'New York')

INSERT INTO Megrendelok(MegrendeloID, MegrendeloNev, Neme) VALUES (1, 'Liam Noah', 'f')
INSERT INTO Megrendelok(MegrendeloID, MegrendeloNev, Neme) VALUES (2, 'Oliver Elijah', 'f')
INSERT INTO Megrendelok(MegrendeloID, MegrendeloNev, Neme) VALUES (3, 'Olivia Emma Charlotte', 'n')

INSERT INTO Rendelesek (RendelesID, MegrendeloID, Datum, Vegosszeg) VALUES (1, 2, '2012-09-11', 41)
INSERT INTO Rendelesek (RendelesID, MegrendeloID, Datum, Vegosszeg) VALUES (2, 3, '2020-01-30', 40)
INSERT INTO Rendelesek (RendelesID, MegrendeloID, Datum, Vegosszeg) VALUES (3, 1, '2022-06-01', 33)

INSERT INTO Ekszerek (EkszerID, EkszerNev, Leiras, Ar, DbSzam, GyartoID, EkszerTipusID, EkszerAnyagID)
	VALUES (1, 'Vörösbegy', 'rubinokkal', 11, 5, 3, 3, 2)
INSERT INTO Ekszerek (EkszerID, EkszerNev, Leiras, Ar, DbSzam, GyartoID, EkszerTipusID, EkszerAnyagID)
	VALUES (2, 'Sárgaság', 'arany', 8, 6, 1, 1, 1)
INSERT INTO Ekszerek (EkszerID, EkszerNev, Leiras, Ar, DbSzam, GyartoID, EkszerTipusID, EkszerAnyagID)
	VALUES (3, 'Lélegzet', 'csak egy ezüst karkötö', 3, 10, 2, 2, 2)

INSERT INTO RendeltEkszerek (RendelesID, EkszerID, Mennyiseg) VALUES (1,1,1)
INSERT INTO RendeltEkszerek (RendelesID, EkszerID, Mennyiseg) VALUES (2,2,1)
INSERT INTO RendeltEkszerek (RendelesID, EkszerID, Mennyiseg) VALUES (3,3,1)