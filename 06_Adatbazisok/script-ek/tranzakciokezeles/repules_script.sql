USE master;
GO

IF EXISTS(select * from sys.databases where name='Repules_Sepsi')
	DROP DATABASE Repules_Sepsi

SET DATEFORMAT YMD

CREATE DATABASE Repules_Sepsi;
GO

USE Repules_Sepsi;
GO

create table Repterek(
 RepterID int primary key identity,
 RNev varchar(50),
 RCim varchar(30),
 RVaros varchar(30)
 )

insert into Repterek values ('Avram Iancu repuloter', 'Traian Vuia 200', 'Kolozsvar'),
                            ('Liszt Ferenc repuloter', 'Ferihegy', 'Budapest'),
                            ('Henri Coanda repuloter', 'Calea Bucureştilor 224E', 'Otopeni'),
                            ('Roma-Ciampino repuloter', ' Via Appia Nuova, 1651', 'Roma'),
                            ('London-Luton repuloter', ' Airport Way, LU2 9LY', 'Luton')

create table Jaratok(
 JaratID int primary key identity,
 --JaratKod varchar(10),
 Honnan int references Repterek(RepterID),
 Hova int references Repterek(RepterID),
 FerohelyekSzama int
)

insert into Jaratok values (1, 5, 100), --1
                           (3, 5, 100), --2
                           (5, 3, 150), --3
                           (2, 4, 100), --4
                           (1, 3, 50),  --5
						   (1, 2, 70)	--6					   

create table Repules(
 RepulesID int primary key identity,
 JaratID int references Jaratok(JaratID),
 Datum date,
 Ora time, 
 EladottHelyekSzama int,
 Ar int
)

insert into Repules values (1,'2020/04/10','12:00pm',8, 100), --1
                           (1,'2020/03/12','9:00pm',9, 500),
                           (4,'2020/04/10','11:00am',10, 1000),
                           (1,'2020/04/05','10:00pm',75, 1500),
                           (5,'2020/03/24','4:00pm',12, 600),  --5
                           (4,'2020/03/25','6:00pm',30, 450),
                           (4,'2020/03/26','11:00pm',40, 1200),
                           (2,'2020/03/20','3:00pm',30, 2000),  --8
                           (3,'2020/04/10','7:00pm',50, 600),
                           (3,'2020/04/30','11:00am',50, 750),
                           (3,'2020/03/15','8:00am',30, 800),
                           (5,'2020/03/25','10:00am',45, 150)
						   

create table Utasok(
UtasID INT primary key identity,
UtasNev varchar(30) unique,
Cim varchar(30),
Varos varchar(30),
Telefon varchar(10)
)

insert into Utasok values('Szilagyi Jeno','Scortarilor 79','Kolozsvar','0732067895'),
                          ('Kiraly Lorand','Unirii 1','Kolozsvar','0264789678'),
                          ('Csizmar Karoly','Closca 90','Nagyvarad','0260361739'),
                          ('Balogh Imre','Paris 3','Kolozsvar', '0728345678'),
                          ('Andras Hannah','Gr. Alexandrescu 5','Kolozsvar','0264435672'),
                          ('Andras Ferenc','Gr. Alexandrescu 5','Kolozsvar','0264435672'),
                          ('Andras Katalin','Gr. Alexandrescu 5','Kolozsvar','0264435672'),
                          ('Kis Mihaly','Titulescu 5','Kolozsvar','0264435672'),
                          ('Andor Zoltan','Fantanele 34','Kolozsvar','0780345678'),
                          ('Nagy Ildiko','Motilor 2','Kolozsvar','0751234786'),
                          ('Magyar Ingrid','Somesul 67','Szatmarnemeti','0261868685'),
                          ('Petok Ilona','Agoston 52', 'Nagykaroly','0728798789')

create table Foglalas(
 RepulesID int references Repules(RepulesID),
 UtasID int references Utasok(UtasID),
 Ulohely int,
 SpecKeres varchar(20),
 Primary Key(RepulesID, UtasID)
)

insert into Foglalas values (1, 1, 7,'vegetarianus'),
                            (1, 2, 8, Null),
                            (1, 3, 9, Null),
                            (1, 4, 10,'vegetarianus'),
                            (1, 8, 1, Null),
                            (1, 5, 2, Null),
                            (1, 7, 3,'Kave'),
                            (1, 6, 4, Null),
                            (3,9, 5,'gyumolcs'),
                            (3,11,6, Null),
                            (3,2,7, Null),
                            (4,3,17, Null),
                            (4,8,18,'gyumolcs'),
                            (4,4,28,'vegetarianus'),
                            (4,9,29,'Kave'),
                            (4,12,30, Null),
                            (5,3,1, Null),
                            (5,9,2,'Kave'),
                            (5,4,3,'vegetarianus'),
                            (5,11,4, Null),
                            (5,10,5, Null),
                            (5,2,6, Null),
                            (5,8,7,'gyumolcs'),
                            (5,5,8, Null),
                            (5,6,9, Null),
                            (5,7,10, Null),
                            (5,12,11, Null),
                            (5,1,12,'vegetarianus'),
                            (8,10,10, Null),
                            (8,12,11,'gyumolcs'),
                            (8,1,12,'vegetarianus'),
                            (8,11,38, Null),
                            (8,4,39,'vegetarianus'),
                            (8,5,40, Null)