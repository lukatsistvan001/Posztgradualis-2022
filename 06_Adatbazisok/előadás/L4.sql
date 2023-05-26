USE master;
GO
IF EXISTS(select * from sys.databases where name='Lab4')
	DROP DATABASE Lab4

CREATE DATABASE Lab4;
GO
USE Lab4;
GO

CREATE TABLE Rendezok(
	RendezoID INT PRIMARY KEY,
	RendezoNev VARCHAR(30),
	SzulDatum DATE)
CREATE TABLE Mufajok(
	MufajID INT PRIMARY KEY,
	MufajNev VARCHAR(30) UNIQUE)
CREATE TABLE Studiok(
	StudioID INT PRIMARY KEY,
	StudioNev VARCHAR(30),
	StudioCim VARCHAR(30) DEFAULT 'UNKNOWN')
CREATE TABLE Filmek(
	FilmID INT PRIMARY KEY,
	FilmCim VARCHAR(30),
	Koltseg INT,
	MegjEv INT,
	StudioID INT FOREIGN KEY REFERENCES Studiok(StudioID))
CREATE TABLE Rendezte(
	RendezoID INT FOREIGN KEY REFERENCES Rendezok(RendezoID),
	FilmID INT FOREIGN KEY REFERENCES Filmek(FilmID),
	PRIMARY KEY(RendezoID, FilmID))
CREATE TABLE Mufaja(
	MufajID INT FOREIGN KEY REFERENCES Mufajok(MufajID),
	FilmID INT FOREIGN KEY REFERENCES Filmek(FilmID),
	PRIMARY KEY(MufajID, FilmID))

insert into Rendezok (RendezoID, RendezoNev, SzulDatum) values (1, 'Giustino Hourican', '2016-12-06');
insert into Rendezok (RendezoID, RendezoNev, SzulDatum) values (2, 'Burk Guinan', '2016-12-20');
insert into Rendezok (RendezoID, RendezoNev, SzulDatum) values (3, 'Robena Linacre', '2017-08-26');
insert into Rendezok (RendezoID, RendezoNev, SzulDatum) values (4, 'Cale Donke', '2017-07-07');
insert into Rendezok (RendezoID, RendezoNev, SzulDatum) values (5, 'Inger Muggleton', '2016-12-07');
insert into Rendezok (RendezoID, RendezoNev, SzulDatum) values (6, 'Bernadene Milbourne', '2017-09-16');
insert into Rendezok (RendezoID, RendezoNev, SzulDatum) values (7, 'Keith Bartleman', '2017-10-08');
insert into Rendezok (RendezoID, RendezoNev, SzulDatum) values (8, 'Carena Scarlet', '2017-08-28');
insert into Rendezok (RendezoID, RendezoNev, SzulDatum) values (9, 'Pen Warrender', '2017-10-12');
insert into Rendezok (RendezoID, RendezoNev, SzulDatum) values (10, 'Dolly Alchin', '2017-08-30');
insert into Rendezok (RendezoID, RendezoNev, SzulDatum) values (11, 'Sheila Yellowlea', '2016-12-25');
insert into Rendezok (RendezoID, RendezoNev, SzulDatum) values (12, 'Audrey Haines', '2017-09-12');
insert into Rendezok (RendezoID, RendezoNev, SzulDatum) values (13, 'Gawen Acres', '2017-04-01');
insert into Rendezok (RendezoID, RendezoNev, SzulDatum) values (14, 'Garvy Pakeman', '2017-06-17');
insert into Rendezok (RendezoID, RendezoNev, SzulDatum) values (15, 'Darren Gloucester', '2017-01-06');

insert into Mufajok (MufajID, MufajNev) values (1, 'Drama')
insert into Mufajok (MufajID, MufajNev) values (2, 'Sci-fi')
insert into Mufajok (MufajID, MufajNev) values (3, 'Comedy')
insert into Mufajok (MufajID, MufajNev) values (4, 'Horror')

insert into Studiok (StudioID, StudioNev, StudioCim) values (1, 'Heathcote-Frami', '42 Duke Drive');
insert into Studiok (StudioID, StudioNev, StudioCim) values (2, 'Orn-Hickle', '64448 Springs Trail');
insert into Studiok (StudioID, StudioNev, StudioCim) values (3, 'Gleichner-Okuneva', '099 Declaration Lane');
insert into Studiok (StudioID, StudioNev, StudioCim) values (4, 'Mayert-Luettgen', '15 Prairie Rose Street');
insert into Studiok (StudioID, StudioNev, StudioCim) values (5, 'Daniel LLC', '7 Lakeland Park');

insert into Filmek (FilmID, FilmCim, Koltseg, MegjEv, StudioID) values (1, 'Invisible Ray', 75275, 1954, 2);
insert into Filmek (FilmID, FilmCim, Koltseg, MegjEv, StudioID) values (2, 'Carry On Teacher', 98356, 1964, 3);
insert into Filmek (FilmID, FilmCim, Koltseg, MegjEv, StudioID) values (3, 'It''s Complicated', 71002, 1971, 1);
insert into Filmek (FilmID, FilmCim, Koltseg, MegjEv, StudioID) values (4, 'Don''t Look in the Basement!', 19936, 1987, 2);
insert into Filmek (FilmID, FilmCim, Koltseg, MegjEv, StudioID) values (5, 'I Love You Too', 76389, 1983, 4);
insert into Filmek (FilmID, FilmCim, Koltseg, MegjEv, StudioID) values (6, 'Jekyll & Hyde', 59985, 1947, 5);
insert into Filmek (FilmID, FilmCim, Koltseg, MegjEv, StudioID) values (7, 'The Flesh and the Devil', 73455, 1927, 3);
insert into Filmek (FilmID, FilmCim, Koltseg, MegjEv, StudioID) values (8, 'Story of a Love Affair', 85930, 1990, 1);
insert into Filmek (FilmID, FilmCim, Koltseg, MegjEv, StudioID) values (9, 'Incident at Oglala', 93452, 2014, 3);
insert into Filmek (FilmID, FilmCim, Koltseg, MegjEv, StudioID) values (10, 'Uninvited Guest', 11777, 1967, 2);
insert into Filmek (FilmID, FilmCim, Koltseg, MegjEv, StudioID) values (11, 'Just Like a Woman', 99770, 1921, 3);
insert into Filmek (FilmID, FilmCim, Koltseg, MegjEv, StudioID) values (12, 'Long, Hot Summer, The', 16489, 1951, 1);
insert into Filmek (FilmID, FilmCim, Koltseg, MegjEv, StudioID) values (13, 'What to Do in Case of Fire', 26340, 1969, 5);
insert into Filmek (FilmID, FilmCim, Koltseg, MegjEv, StudioID) values (14, 'My Friend Irma Goes West', 19674, 1990, 1);
insert into Filmek (FilmID, FilmCim, Koltseg, MegjEv, StudioID) values (15, 'Incubus, The', 40858, 1993, 5);
insert into Filmek (FilmID, FilmCim, Koltseg, MegjEv, StudioID) values (16, 'Girls About Town', 11777, 1930, 2);
insert into Filmek (FilmID, FilmCim, Koltseg, MegjEv, StudioID) values (17, 'Wedding Date, The', 46203, 1932, 5);
insert into Filmek (FilmID, FilmCim, Koltseg, MegjEv, StudioID) values (18, 'Confiance r?gne, La', 25345, 1987, 4);
insert into Filmek (FilmID, FilmCim, Koltseg, MegjEv, StudioID) values (19, 'Traffic (Trafic)', 76174, 1924, 2);
insert into Filmek (FilmID, FilmCim, Koltseg, MegjEv, StudioID) values (20, 'Moordwijven', 92778, 1999, 3);

insert into Rendezte (RendezoID, FilmID) values (2, 18);
insert into Rendezte (RendezoID, FilmID) values (13, 15);
insert into Rendezte (RendezoID, FilmID) values (7, 5);
insert into Rendezte (RendezoID, FilmID) values (5, 5);
insert into Rendezte (RendezoID, FilmID) values (14, 9);
insert into Rendezte (RendezoID, FilmID) values (9, 19);
insert into Rendezte (RendezoID, FilmID) values (6, 8);
insert into Rendezte (RendezoID, FilmID) values (12, 3);
insert into Rendezte (RendezoID, FilmID) values (14, 5);
insert into Rendezte (RendezoID, FilmID) values (12, 8);

insert into Mufaja (MufajID, FilmID) values (1, 1);
insert into Mufaja (MufajID, FilmID) values (4, 15);
insert into Mufaja (MufajID, FilmID) values (1, 2);
insert into Mufaja (MufajID, FilmID) values (2, 19);
insert into Mufaja (MufajID, FilmID) values (4, 9);
insert into Mufaja (MufajID, FilmID) values (1, 3);
insert into Mufaja (MufajID, FilmID) values (1, 16);
insert into Mufaja (MufajID, FilmID) values (3, 8);
insert into Mufaja (MufajID, FilmID) values (2, 15);
insert into Mufaja (MufajID, FilmID) values (4, 13);
insert into Mufaja (MufajID, FilmID) values (2, 12);
insert into Mufaja (MufajID, FilmID) values (4, 18);
insert into Mufaja (MufajID, FilmID) values (4, 12);
insert into Mufaja (MufajID, FilmID) values (1, 15);