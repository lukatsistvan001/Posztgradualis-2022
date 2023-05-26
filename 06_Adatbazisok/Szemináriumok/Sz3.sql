--A. Konyvesuzlet_Sepsi View in a new windowadatbázist használva:
--1. Szúrjunk be a Konyvek táblába egy új könyvet az alábbi adatokkal:
--cím: Ady válogatott versei, megj.év: 2016, oldalak száma:532, kiadó: Kossuth Kiadó,  eladási ár: 12.
--Ügyeljünk rá, hogy a kiadó még NEM létezik az adatbázisban.     
INSERT INTO Kiadok (KNev)
VALUES ('Kossuth Kiadó')
--
DECLARE @KiadoID INT
SELECT @KiadoID = KiadoID
FROM Kiadok
WHERE KNev = 'Kossuth Kiadó'
INSERT INTO Konyvek (KonyvCim, MegjEv, Oldalszam, KiadoID, EladasiAr)
VALUES ('Ady válogatott versei', '2016', 532, @KiadoID, 12)
--

--2. Módosítsuk a Szerzok tábla esetén Szabó Magda nevét oly módon, hogy csupa nagybetűkkel legyen írva a neve!
UPDATE Szerzok
SET SzerzoNev = UPPER('Szabó Magda')
WHERE SzerzoNev = 'Szabó Magda'

--3. Másoljuk át az #UjKonyvek temporális táblába azon könyvek adatait, amelyekből az elmúlt 10 évben legalább egyszer vásároltak!
SELECT * INTO #UjKonyvek 
FROM Konyvek
WHERE KonyvKod IN (SELECT DISTINCT KonyvKod
					FROM VasarlasTartalma
					WHERE VasarlasID IN (SELECT VID
											FROM Vasarlasok
											WHERE YEAR(GETDATE())-YEAR(Datum)<=10))
SELECT * FROM #UjKonyvek

--4. Növeljük az #UjKonyvek táblában a könyvek eladási árát 15%-kal!
UPDATE #UjKonyvek
SET EladasiAr *= 1.15

SELECT * FROM #UjKonyvek
SELECT * FROM Konyvek

drop table #UjKonyvek

--5. Töröljük az #UjKonyvek táblából azokat a könyveket, amelyekből az elmúlt 4 évben NEM vásároltak!
DELETE
FROM #UjKonyvek
WHERE KonyvKod NOT IN (SELECT DISTINCT KonyvKod
						FROM VasarlasTartalma
						WHERE VasarlasID IN (SELECT VID
												FROM Vasarlasok
												WHERE YEAR(GETDATE())-YEAR(Datum)<=4))

--B. Az alábbi feladatok esetén: minden feladathoz írjunk legalább 2 ekvivalens lekérdezést, 
--valamint használjunk temporális táblákat, tábla típusú változókat, nézeteket és lokális változókat!
--Ekvivalens lekérdezések=olyan lekérdezések, melyek értékei megegyeznek minden relációelőfordulás esetén.

--1. Adjuk meg az(oka)t a kiadó(ka)t, amely(ek) adtak/adott ki regényt és ifjúsági könyvet is! 

--2. Adjuk meg az(oka)t a kiadó(ka)t, amely(ek) csak regényt adtak/adott ki! 

--3. Adjuk meg azokat a kiadó(ka)t, amely(ek) könyveiből jelenleg NEM található könyv az üzletben!

--4. Adjuk meg az(oka)t a törzs!!!vásárló(ka)t, aki(k) minden szerzőtől vásárolt(ak) könyvet! 

--5. Adjuk meg az(oka)t a törzs!!!vásárló(ka)t, aki(k) a legtöbb szerzőtől vásároltak könyvet!

--6. a. Adjuk meg az(oka)t a könyve(ke)t, amelyekből a legtöbb példány található az üzletben 'J. K. Rowling' művei közül!

--b. A feladatot bővítsük olyan formában, hogy ellenőrizzük le azt is, létezik-e  'J. K. Rowling' nevű szerző az adatbázisban,
--illetve hogy legalább 1 könyv el van tárolva a szerzőtől az adatbázisban! Ha nem teljesül egyik vagy másik feltétel, írassunk ki megfelelő hibaüzenetet! 

--c. A feladatot oldjuk meg olyan formában, hogy bármilyen szerzőt megadva 'J. K.Rowling' helyett, végezze el az a,b. alpontban kért utasításokat!