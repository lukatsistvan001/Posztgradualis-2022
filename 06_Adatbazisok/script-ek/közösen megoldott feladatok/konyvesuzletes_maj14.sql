--1. Írjunk tárolt eljárást, mely segítségével megadhatjuk azon szerzőket 
--(SzerzoNev, SzuletesiEv), aki(k)nek legalább @pN könyve megtalálható a könyvesüzletben 
--(@pN-int típusú, bemeneti paraméter)! Ha nincs egyetlen szerző sem, 
--aki eleget tenne a feltételnek, írassunk ki megfelelő hibaüzenetet!
--visszatérítési értéket is állítsunk (hibás bemenetnél: -1, egyetlen szerző sincs: -2; 
	--van leg. 1 szerzőnk: 0
--kimeneti paraméterben adjuk meg a szerzők számát, akik teljesítik a feltételt

USE Konyvesuzlet_Sepsi
GO
CREATE OR ALTER PROCEDURE spSzerzokLegalabbNKonyvvel(@pN INT, @pOut INT OUT)
AS 
BEGIN
	SET NOCOUNT ON
	IF (@pN <= 0) 
		BEGIN
		RAISERROR('Hibás bemenet', 12, 1)
		RETURN -1
		END
	
	/*azon szerzők meghatározása, akik eleget tesznek a feltételnek:
	SELECT SzerzoID, COUNT(KonyvKod) AS KonyvekSzama
	FROM KonyvSzerzok
	GROUP BY SzerzoID
	HAVING COUNT(KonyvKod) >= @pN
	*/

	--megvizsgáljuk, hogy hány szerző tesz eleget a feltételnek
	--1.mód: megkapjuk azokat, akik nem tesznek eleget a feltételnek
	/*
	IF (SELECT COUNT(*) 
		FROM (
			SELECT SzerzoID
			FROM Szerzok
			EXCEPT 
			SELECT SzerzoID--, COUNT(KonyvKod) AS KonyvekSzama
			FROM KonyvSzerzok
			GROUP BY SzerzoID
			HAVING COUNT(KonyvKod) < @pN) AS T) = (SELECT COUNT(*) FROM Szerzok) --...
	*/
	--2.mód:
	SELECT SzerzoID, COUNT(KonyvKod) AS KonyvekSzama INTO #t
	FROM KonyvSzerzok
	GROUP BY SzerzoID
	HAVING COUNT(KonyvKod) >= @pN

	SELECT @pOut = COUNT(*) 
	FROM #t

	IF (@pOut = 0)--VAGY: IF(SELECT COUNT(*) FROM #t) = 0
		---nincs egyetlen szerző sem, amelyik eleget tesz a feltételnek
		BEGIN
		RAISERROR('Nincs egyetlen szerző sem, amelyik eleget tesz a feltételnek.', 13, 1)
		RETURN -2
		END

	SELECT SzerzoNev, SzuletesiEv
	FROM Szerzok 
	WHERE SzerzoID IN (SELECT SzerzoID FROM #t)
	--vagy:
	/*SELECT SzerzoNev, SzuletesiEv
	  FROM Szerzok JOIN #t ON Szerzok.SzerzoID = #t.SzerzoID*/

	RETURN 0
END
GO

--meghívás
DECLARE @Out INT, @rv INT
--EXEC @rv = spSzerzokLegalabbNKonyvvel 2, @Out OUT --helyes eredmény
--EXEC @rv = spSzerzokLegalabbNKonyvvel_1 20, @Out OUT --nincs egyetlen ilyen szerző sem
EXEC @rv = spSzerzokLegalabbNKonyvvel_1 -20, @Out OUT --hibás bemenet
SELECT @Out AS SzerzokSzama, @rv AS ReturnValue



--2.variáció:
GO
CREATE OR ALTER PROCEDURE spSzerzokLegalabbNKonyvvel_2(@pN INT, @pOut INT OUT)
AS 
BEGIN
	SET NOCOUNT ON
	IF (@pN <= 0) 
		BEGIN
		RAISERROR('Hibás bemenet', 12, 1)
		RETURN -1
		END
	
	--azon szerzők meghatározása, akik eleget tesznek a feltételnek:
	SELECT SzerzoNev, SzuletesiEv 
	FROM KonyvSzerzok ksz
		JOIN Szerzok sz ON ksz.SzerzoID = sz.SzerzoID 
	GROUP BY sz.SzerzoID, SzerzoNev, SzuletesiEv
	HAVING COUNT(KonyvKod) >= @pN

	SET @pOut = @@ROWCOUNT

	IF (@pOut = 0)
		---nincs egyetlen szerző sem, amelyik eleget tesz a feltételnek
		BEGIN
		RAISERROR('Nincs egyetlen szerző sem, amelyik eleget tesz a feltételnek.', 13, 1)
		RETURN -2
		END

	RETURN 0
END

GO
--meghívás
DECLARE @Out INT, @rv INT
EXEC @rv = spSzerzokLegalabbNKonyvvel_2 2, @Out OUT
SELECT @Out AS SzerzokSzama, @rv AS ReturnValue

--Sz4-3.b.
/*b. Az a. alpontbeli feladatot oldjuk meg függvény segítségével oly módon, 
hogy kiíratás helyett térítsen vissza:
Hibás bemenet esetén –> -1;
Könyvek száma = 0 –> 0;
Könyvek száma >= 1 és <= 10 –> 1;
Könyvek száma  >= 10 –> 2.
*/
GO
CREATE OR ALTER FUNCTION fnSzerzokKonyvei 
	(@pSzerzoNev VARCHAR(30), @pSzulEv INT)
RETURNS INT
AS
BEGIN
	DECLARE @vSzerzoID INT
	SELECT @vSzerzoID = SzerzoID  --vigyázni arra, hogy csak egy skalár értéket térítsen vissza a lekérdezés
	FROM Szerzok 
	WHERE SzerzoNev = @pSzerzoNev AND SzuletesiEv = @pSzulEv

	IF (@vSzerzoID IS NULL)
		RETURN -1
	
	DECLARE @vKonyvekSzama INT, @vProduktivitas INT
	SELECT @vKonyvekSzama = count(*)
	from KonyvSzerzok
	where SzerzoID=@vSzerzoID

	SET @vProduktivitas =
	CASE
		WHEN @vKonyvekSzama=0 THEN 0
		WHEN @vKonyvekSzama between 1 and 10 THEN 1
		ELSE 2
	END;

	RETURN @vProduktivitas
END
GO

--meghívás
DECLARE @rv INT
EXEC @rv = fnSzerzokKonyvei 'J. K. Rowling', 1960
SELECT @rv


--Írjunk függvényt, amelyik egy táblában visszatéríti azon szerzők nevét, akik @pEv (int típusú) 
--után születtek és a legtöbb könyvvel rendelkeznek!
GO
CREATE OR ALTER FUNCTION fnLegtobbKonyvuSzerzo (@pEv INT)
RETURNS TABLE AS
RETURN
	SELECT SzerzoNev, SzuletesiEv, COUNT(KonyvKod) AS KonyvekSzama 
	FROM KonyvSzerzok ksz
		JOIN Szerzok sz ON ksz.SzerzoID = sz.SzerzoID 
	--WHERE SzuletesiEv >= @pEv
	GROUP BY sz.SzerzoID, SzerzoNev, SzuletesiEv
	HAVING COUNT(KonyvKod) >=ALL (SELECT COUNT(KonyvKod) AS KonyvekSzama 
							      FROM KonyvSzerzok ksz
								     JOIN Szerzok sz ON ksz.SzerzoID = sz.SzerzoID 
								  --WHERE SzuletesiEv >= @pEv
								  GROUP BY sz.SzerzoID, SzerzoNev, SzuletesiEv)
GO

SELECT * FROM fnLegtobbKonyvuSzerzo (1960)

--azon szerzők meghatározása, akik a legtöbb könnyvel rendelkeznek:
	/*	
	SELECT SzerzoID, COUNT(KonyvKod) AS KonyvekSzama INTO #t
	FROM KonyvSzerzok
	GROUP BY SzerzoID
	*/

	SELECT SzerzoNev, SzuletesiEv, COUNT(KonyvKod) AS KonyvekSzama INTO #t 
	FROM KonyvSzerzok ksz
		JOIN Szerzok sz ON ksz.SzerzoID = sz.SzerzoID 
	GROUP BY sz.SzerzoID, SzerzoNev, SzuletesiEv

	SELECT * 
	FROM #t
	WHERE KonyvekSzama = (SELECT MAX(KonyvekSzama) FROM #t)

	DROP TABLE #t
