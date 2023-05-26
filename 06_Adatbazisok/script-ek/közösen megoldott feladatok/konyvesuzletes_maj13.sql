use Konyvesuzlet_Sepsi
go

SELECT GETDATE(), YEAR(GETDATE()) --datetime tipusu
SELECT * FROM Konyvek
--Konyvek(KonyvKod, KonyvCim, MegjEv, Oldalszam, KiadoID, Peldanyszam, EladasiAr)
insert into Konyvek values ('UjKonyv', YEAR(GETDATE()), 200, 1, 10, 55)
insert into Konyvek(KonyvCim, EladasiAr) values ('UjKonyv', 100)

SELECT @@IDENTITY

DECLARE @ujKonyvKod INT = @@IDENTITY
--SELECT @ujKonyvKod AS v

DELETE 
FROM Konyvek
WHERE KonyvKod = @ujKonyvKod 

---------------------------------------------------------
--valtozo ertekadas:
--1.mod:
GO
DECLARE @KonyvKod INT = (SELECT KonyvKod
						FROM Konyvek
						WHERE KonyvCim LIKE 'A Forsyte-Saga') --'A%'
DECLARE @EladasiAr FLOAT = (SELECT EladasiAr
						FROM Konyvek
						WHERE KonyvCim LIKE 'A Forsyte-Saga') --'A%'
SELECT @KonyvKod, @EladasiAr

--2.mod:
GO
DECLARE @KonyvKod INT, @EladasiAr FLOAT
SET @KonyvKod = (SELECT KonyvKod
				 FROM Konyvek
				 WHERE KonyvCim LIKE 'A Forsyte-Saga') --'A%'
SET @EladasiAr = (SELECT EladasiAr
				  FROM Konyvek
				  WHERE KonyvCim LIKE 'A Forsyte-Saga') --'A%'

SELECT @KonyvKod, @EladasiAr

--3.mod: --ezt hasznaljatok!
GO
DECLARE @KonyvKod INT, @EladasiAr FLOAT
SELECT @KonyvKod = KonyvKod, @EladasiAr = EladasiAr 
FROM Konyvek
WHERE KonyvCim LIKE 'A Forsyte-Saga' --'A%'
SELECT @KonyvKod AS KonyvKod, @EladasiAr AS EladasiAr

--1. Szúrjunk be a Konyvek táblába egy új könyvet az alábbi adatokkal:
	--cím: Ady válogatott versei, megj.év: 2016, oldalak száma:532, kiadó: Kossuth Kiadó,  eladási ár: 12. 
	--Ügyeljünk rá, hogy a kiadó még NEM létezik az adatbázisban.     

SELECT * FROM Konyvek
SELECT * FROM Kiadok

--két kiadó beszúrása:
--1.lehetőség:
insert into Kiadok (KNev) values ('Kossuth Kiadó_törölhető')
insert into Kiadok (KNev) values ('Kossuth Kiadó')

--2.lehetőség:--gyorsabb, érdemesebb így dolgozni
insert into Kiadok (KNev) values ('Kossuth Kiadó_törölhető'), ('Kossuth Kiadó')

--könyv beszúrása
--1.lehetőség:
DECLARE @ujKiado INT
SELECT @ujKiado = @@IDENTITY
insert into Konyvek (KonyvCim, MegjEv, Oldalszam, KiadoID, EladasiAr) 
	--values ('Ady válogatott versei', 2016, 532, @@IDENTITY, 12) 
	values ('Ady válogatott versei', 2016, 532, @ujKiado, 12) 
	--values ('Ady válogatott versei', 2016, 532, (SELECT KiadoKod FROM Kiadok WHERE KNev = 'Kossuth Kiadó'), 12)  --csak akkor hasznaljuk, ha a KNev unique megszorítással van ellátva

--2.lehetőség:
GO
insert into Kiadok (KNev) values ('Kossuth Kiadó_törölhető'), ('Kossuth Kiadó')
DECLARE @ujKiado INT
SELECT @ujKiado = @@IDENTITY -- rögtön a beszúrás után elmentjük az értéket
insert into Konyvek (KonyvCim, MegjEv, Oldalszam, EladasiAr) 
	values ('Ady válogatott versei', 2016, 532, 12)
DECLARE @ujKonyvKod INT
SELECT @ujKonyvKod = @@IDENTITY
--ha nem identity típusú a KonyvKod: --erre kell vigyázni
--SELECT @ujKonyvKod = MAX(KonyvKod)
--FROM Konyvek


UPDATE Konyvek
SET KiadoID = @ujKiado --, további attr.-k, ha szükséges
WHERE KonyvKod = @ujKonyvKod

GO

--2. Módosítsuk a Szerzok tábla esetén Szabó Magda nevét oly módon, hogy csupa nagybetűkkel legyen írva a neve!

--3. Másoljuk át az #UjKonyvek temporális táblába azon könyvek adatait, 
--amelyekből az elmúlt 1 évben legalább egyszer vásároltak! (#UjKonyvek sémája megegyezik
--a Konyvek tábla sémájával)
--1.lehetőség:
/*--temp tábla létrehozása: 
  CREATE TABLE #UjKonyvek (....)
   INSERT INTO #UjKonyvek 
	SELECT * 
	FROM Konyvek WHERE .... (lsd. Q_UjKonyvek)
*/
--2.lehetőség:
--Q_UjKonyvek:
SELECT * INTO #UjKonyvek
FROM Konyvek
WHERE KonyvKod IN (SELECT KonyvKod
				   FROM VasarlasTartalma
				   WHERE VasarlasID IN (SELECT VID
										FROM Vasarlasok
										WHERE DATEDIFF(year, Vasarlasok.Datum, GETDATE()) <= 10))

SELECT * FROM #UjKonyvek

--Megj. 
--1. Léteznek tábla típusú változók (table variable).--rövid ideig (batch-ben látható) van szükségünk kevés adat ideiglenes tárolására
--CREATE TABLE @UjKonyvek (....) 

--2. Nézettel való definiálás:
GO
CREATE VIEW vUjKonyvek AS
SELECT * 
FROM Konyvek
WHERE KonyvKod IN (SELECT KonyvKod
				   FROM VasarlasTartalma
				   WHERE VasarlasID IN (SELECT VID
										FROM Vasarlasok
										WHERE DATEDIFF(year, Vasarlasok.Datum, GETDATE()) <= 10))
GO

SELECT * 
FROM vUjKonyvek

--4. Növeljük az #UjKonyvek táblában az összes könyv eladási árát 15%-kal, példányszámát pedig csökkentsük 5-tel!
UPDATE #UjKonyvek
SET EladasiAr *= 1.15, Peldanyszam -=5
--nincs szükség ebben a kivételes esetben!!! where záradékra

SELECT * FROM #UjKonyvek
SELECT * FROM Konyvek
SELECT * FROM vUjKonyvek

select * from KonyvSzerzok

--5. Töröljük az #UjKonyvek táblából azokat a könyveket, amelyekből az elmúlt 1 hétben NEM vásároltak!
--Megj. Töröljük az 1-es könyvkódú könyvet!
DELETE FROM #UjKonyvek
WHERE KonyvKod = 1

--Megj. 1-es könyvkódú könyv törlése:
--először törlünk (vagy módosítunk) azokból a táblákból, ahol a KonyvKod megjelenik külső kulcsként
--ha lehet, akkor inkább módosítsunk
--ebben az esetben nem tudunk módosítást végezni
DELETE FROM KonyvMufajok
WHERE KonyvKod = 1

DELETE FROM KonyvSzerzok
WHERE KonyvKod = 1

DELETE FROM VasarlasTartalma
WHERE KonyvKod = 1

DELETE FROM Konyvek
WHERE KonyvKod = 1

select * from Konyvek

--azon könyveket keressük, amelyeket egyszer sem vásároltak még meg:
--1.mód:
SELECT KonyvKod
FROM Konyvek
EXCEPT
SELECT KonyvKod
FROM VasarlasTartalma

--2.mód:
SELECT KonyvKod
FROM Konyvek
WHERE KonyvKod NOT IN (SELECT KonyvKod
					   FROM VasarlasTartalma)

--3.mód:
SELECT k.KonyvKod
FROM Konyvek k
	LEFT JOIN VasarlasTartalma vt ON k.KonyvKod = vt.KonyvKod
WHERE VasarlasID IS NULL  --vagy: vt.KonyvKod IS NULL

--azokat a könyveket keressük, amelyekből NEM vásároltak az elmúlt 1 hétben
--Q_MegNemVasaroltKonyvek
SELECT KonyvKod
FROM Konyvek
WHERE KonyvKod NOT IN (SELECT KonyvKod
					   FROM VasarlasTartalma
					   WHERE VasarlasID IN (SELECT VID
											FROM Vasarlasok
											WHERE DATEDIFF(day, Vasarlasok.Datum, GETDATE()) <= 7))

--törlés:
--1.mód:
/*
SELECT KonyvKod INTO #Ujkonyvek2
FROM ... (lsd. Q_MegNemVasaroltKonyvek)

DELETE FROM #UjKonyvek
WHERE KonyvKod IN (SELECT KonyvKod FROM #UjKonyvek2)
*/

--2.mód:
DELETE FROM #UjKonyvek
WHERE KonyvKod NOT IN (SELECT KonyvKod
					   FROM VasarlasTartalma
					   WHERE VasarlasID IN (SELECT VID
											FROM Vasarlasok
											WHERE DATEDIFF(day, Vasarlasok.Datum, GETDATE()) <= 7))
											
--B. Konyvesuzlet_Sepsi View adatbázist használva, az alábbi feladatok esetén: minden feladathoz írjunk legalább 2  ekvivalens lekérdezést, valamint használjunk temporális táblákat, tábla típusú változókat, nézeteket és lokális változókat!

--Ekvivalens lekérdezések=olyan lekérdezések, melyek értékei megegyeznek minden relációelőfordulás esetén.
--1. Adjuk meg az(oka)t a kiadó(ka)t, amely(ek) adtak/adott ki regényt és ifjúsági könyvet is! 
--2. Adjuk meg az(oka)t a kiadó(ka)t, amely(ek) csak regényt adtak/adott ki! 

--3. Adjuk meg azokat a kiadó(ka)t, amely(ek) könyveiből jelenleg NEM található könyv az üzletben!

--4. Adjuk meg az(oka)t a törzs!!!vásárló(ka)t, aki(k) minden szerzőtől vásárolt(ak) könyvet! 

--5. Adjuk meg az(oka)t a törzs!!!vásárló(ka)t, aki(k) a legtöbb szerzőtől vásároltak könyvet!

--6. a. Adjuk meg az(oka)t a könyve(ke)t, 
--amelyekből a legtöbb példány található az üzletben 'J. K. Rowling' művei közül!
SELECT KonyvCim, Peldanyszam -- DISTINCT felesleges
FROM Konyvek k 
	JOIN KonyvSzerzok ksz ON k.KonyvKod = ksz.KonyvKod
	JOIN Szerzok sz ON ksz.SzerzoID = sz.SzerzoID
WHERE sz.SzerzoNev = 'J. K. Rowling'


--Q_RowlingKonyveiPeldanya
SELECT Konyvek.KonyvCim, Konyvek.Peldanyszam
FROM Konyvek JOIN KonyvSzerzok
ON Konyvek.KonyvKod=KonyvSzerzok.KonyvKod
JOIN Szerzok
ON KonyvSzerzok.SzerzoID=Szerzok.SzerzoID
WHERE Szerzok.SzerzoNev= 'J. K. Rowling' AND Konyvek.Peldanyszam = (SELECT MAX(Konyvek.Peldanyszam)
                                                                    FROM Konyvek JOIN KonyvSzerzok
                                                                    ON Konyvek.KonyvKod=KonyvSzerzok.KonyvKod
                                                                    JOIN Szerzok
                                                                    ON KonyvSzerzok.SzerzoID=Szerzok.SzerzoID
                                                                    WHERE Szerzok.SzerzoNev= 'J. K. Rowling' )
                                       /*--másik lehetőség (kevésbé átlátható + felesleges a legbelső alkérdés)
									     AND Konyvek.Peldanyszam = (SELECT MAX(T.MENNYISEG)
                                                                    FROM (SELECT Konyvek.Peldanyszam AS MENNYISEG
                                                                          FROM Konyvek JOIN KonyvSzerzok ON Konyvek.KonyvKod=KonyvSzerzok.KonyvKod
                                                                          JOIN Szerzok ON KonyvSzerzok.SzerzoID=Szerzok.SzerzoID
                                                                          WHERE Szerzok.SzerzoNev= 'J. K. Rowling'
                                                                          ) AS T)
									   */
DROP TABLE #UjT
SELECT Konyvek.KonyvCim, Konyvek.Peldanyszam INTO #UjT
FROM Konyvek JOIN KonyvSzerzok
ON Konyvek.KonyvKod=KonyvSzerzok.KonyvKod
JOIN Szerzok
ON KonyvSzerzok.SzerzoID=Szerzok.SzerzoID
WHERE Szerzok.SzerzoNev = 'J. K. Rowling' 

SELECT * 
FROM #UjT
WHERE Peldanyszam = (SELECT MAX(Peldanyszam)
					 FROM #UjT)

--b. A feladatot bővítsük olyan formában, hogy 
--ellenőrizzük le azt is, létezik-e  'J. K. Rowling' nevű szerző az adatbázisban, 
--illetve hogy legalább 1 könyv el van tárolva a szerzőtől az adatbázisban! 
--Ha nem teljesül egyik vagy másik feltétel, írassunk ki megfelelő hibaüzenetet! 

IF NOT EXISTS (SELECT SzerzoID
			FROM Szerzok 
			WHERE SzerzoNev = 'J.K.Rowling')
  --RAISERROR('Nem létezik a szerző!', 12, 1) 
  THROW 50001, 'Nem létezik a szerző!', 1 	
  ELSE IF NOT EXISTS (SELECT sz.SzerzoID
					FROM Szerzok sz
						JOIN KonyvSzerzok ksz ON sz.SzerzoID = ksz.SzerzoID
 					WHERE SzerzoNev = 'J. K. Rowling')
	RAISERROR('Nincs könyve a szerzőnek!', 12, 2) 
	ELSE 
		BEGIN
		PRINT 'Minden feltétel teljesült!'
		--Q_RowlingKonyveiPeldanya
		END

--c. A feladatot oldjuk meg olyan formában, 
--hogy bármilyen szerzőt megadva 'J. K.Rowling' helyett, 
--végezze el az a,b. alpontban kért utasításokat!

--változóval:
--1.mód:
DECLARE @vSzerzoNev VARCHAR(30) = 'J.K.Rowling'

IF NOT EXISTS (SELECT SzerzoID
			FROM Szerzok 
			WHERE SzerzoNev = @vSzerzoNev)
  --RAISERROR('Nem létezik a szerző!', 12, 1) 
  THROW 50001, 'Nem létezik a szerző!', 1 	
  ELSE IF NOT EXISTS (SELECT sz.SzerzoID
					FROM Szerzok sz
						JOIN KonyvSzerzok ksz ON sz.SzerzoID = ksz.SzerzoID
 					WHERE SzerzoNev = @vSzerzoNev)
	RAISERROR('Nincs könyve a szerzőnek!', 12, 2) 
	ELSE 
		BEGIN
		PRINT 'Minden feltétel teljesült!'
		--Q_RowlingKonyveiPeldanya
		END
GO

--2.mód:
DECLARE @vSzerzoID INT, @vSzerzoNev VARCHAR(30) = 'J.K.Rowling'

SELECT @vSzerzoID = SzerzoID  --vigyázni arra, hogy csak egy skalár értéket térítsen vissza a lekérdezés
FROM Szerzok 
WHERE SzerzoNev = @vSzerzoNev

IF (@vSzerzoID IS NULL)
  RAISERROR('Nem létezik a szerző!', 12, 1) 
  ELSE IF NOT EXISTS (SELECT SzerzoID
					  FROM KonyvSzerzok 
 					  WHERE SzerzoID = @vSzerzoID)
	RAISERROR('Nincs könyve a szerzőnek!', 12, 2) 
	ELSE 
		BEGIN
		PRINT 'Minden feltétel teljesült!'
		--Q_RowlingKonyveiPeldanya
		END

--tárolt eljárással:
GO
CREATE OR ALTER PROCEDURE spSzerzokKonyveiPeldanya 
	(@pSzerzoNev VARCHAR(30))
AS
BEGIN
	SET NOCOUNT ON
	DECLARE @vSzerzoID INT
	SELECT @vSzerzoID = SzerzoID  --vigyázni arra, hogy csak egy skalár értéket térítsen vissza a lekérdezés
	FROM Szerzok 
	WHERE SzerzoNev = @pSzerzoNev

	IF (@vSzerzoID IS NULL)
	  RAISERROR('Nem létezik a szerző!', 12, 1) 
	  ELSE IF NOT EXISTS (SELECT SzerzoID
						  FROM KonyvSzerzok 
 						  WHERE SzerzoID = @vSzerzoID)
		RAISERROR('Nincs könyve a szerzőnek!', 12, 2) 
		ELSE 
			BEGIN
			PRINT 'Minden feltétel teljesült!'
			SELECT Konyvek.KonyvCim, Konyvek.Peldanyszam
			FROM Konyvek JOIN KonyvSzerzok
			ON Konyvek.KonyvKod=KonyvSzerzok.KonyvKod
			WHERE SzerzoID = @vSzerzoID AND Peldanyszam = (SELECT MAX(Konyvek.Peldanyszam)
                                             			   FROM Konyvek JOIN KonyvSzerzok
                                                             				ON Konyvek.KonyvKod=KonyvSzerzok.KonyvKod
															WHERE SzerzoID = @vSzerzoID)
			END
END
GO
--meghivas:
EXEC spSzerzokKonyveiPeldanya 'J. K. Rowling'

/*Sz4:
3. a. Írjunk tárolt eljárást, mely egy bemenetként megadott szerző esetén 
(@pSzerzoNev-string, @pSzulEv-int típusúak) megvizsgálja, 
hogy hány könyve található meg a szerzőnek az üzletben s ennek megfelelően írat ki 
üzenetet a képernyőre:
Könyvek száma = 0  –> 'Egy könyve sem kapható.';
Könyvek száma >=1 és <= 10  –> 'Kevés könyve kapható.';
Könyvek száma  > = 10 –> 'Sok könyve kapható.'
Ellenőrizzük le, hogy az adott szerző létezik-e az adatbázisban! 
Ha nem, írassunk ki hiba(!)üzenetet, valamint a visszatérítési érték legyen -1, ellenkező esetben legyen 0!
*/

GO
CREATE OR ALTER PROCEDURE spSzerzokKonyvei 
	(@pSzerzoNev VARCHAR(30), @pSzulEv INT)
AS
BEGIN
	SET NOCOUNT ON
	DECLARE @vSzerzoID INT
	SELECT @vSzerzoID = SzerzoID  --vigyázni arra, hogy csak egy skalár értéket térítsen vissza a lekérdezés
	FROM Szerzok 
	WHERE SzerzoNev = @pSzerzoNev AND SzuletesiEv = @pSzulEv

	IF (@vSzerzoID IS NULL)
	  BEGIN
		  RAISERROR('Nem létezik a szerző!', 12, 1) 
		  RETURN -1
	  END

	DECLARE @vKonyvekSzama INT
	SELECT @vKonyvekSzama = count(*)
	from KonyvSzerzok
	where SzerzoID=@vSzerzoID

	IF (@vKonyvekSzama = 0)
		PRINT 'Egy könyve sem kapható.'
	ELSE IF (@vKonyvekSzama BETWEEN 1 AND 10)
				PRINT 'Kevés könyve kapható.'
		ELSE IF @vKonyvekSzama > 10  --if itt tképp felesleges
			PRINT 'Sok könyve kapható.'
	RETURN 0
END
GO

/*
update Szerzok
set SzuletesiEv = 1960
where SzerzoNev = 'J. K. Rowling'
*/

--meghívás:
DECLARE @rv INT
EXEC @rv = spSzerzokKonyvei 'J. K. Rowling', 1960
SELECT @rv
GO

--2.lehetőség: case használatával:
CREATE OR ALTER PROCEDURE spSzerzokKonyvei_2
	(@pSzerzoNev VARCHAR(30), @pSzulEv INT)
AS
BEGIN
	SET NOCOUNT ON
	DECLARE @vSzerzoID INT
	SELECT @vSzerzoID = SzerzoID  --vigyázni arra, hogy csak egy skalár értéket térítsen vissza a lekérdezés
	FROM Szerzok 
	WHERE SzerzoNev = @pSzerzoNev AND SzuletesiEv = @pSzulEv

	IF (@vSzerzoID IS NULL)
	  BEGIN
		  RAISERROR('Nem létezik a szerző!', 12, 1) 
		  RETURN -1
	  END

	DECLARE @vKonyvekSzama INT, @vProduktivitas varchar(30)
	SELECT @vKonyvekSzama = count(*)
	from KonyvSzerzok
	where SzerzoID=@vSzerzoID

	set @vProduktivitas =
	CASE
		WHEN @vKonyvekSzama=0 THEN 'Egy könyve sem kapható.'
		WHEN @vKonyvekSzama between 1 and 10 THEN 'Kevés könyve kapható.'
		ELSE 'Sok könyve kapható.'
	END;
	print @vProduktivitas
	RETURN 0
END

--meghívás:
DECLARE @rv INT
EXEC @rv = spSzerzokKonyvei_2 'J. K. Rowling', 1960
SELECT @rv
GO


--Megj.
--minden szerző esetén érdekel a könyveinek a száma:
select --szerzoid,
	count(*)
from KonyvSzerzok
group by SzerzoID

--egy adott szerző könyveinek a száma:
select count(*)
from KonyvSzerzok
where SzerzoID=8

--ebben az esetben a group by felesleges:
--select --szerzoid,
--	count(*)
--from KonyvSzerzok
--where SzerzoID=8
--group by SzerzoID