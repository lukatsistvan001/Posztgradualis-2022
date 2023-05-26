--Adott a Files menüpont alatt található L6.sql script. Futassuk le, majd oldjuk meg a következő feladatokat!
--Megj. Minden tárolt eljárást, függvényt hívjunk is meg!

--Markak (MarkaID, MarkaNev)
--Tipusok (TipusID, TipusNev, MarkaID)
--Szinek (SzinKod, SzinNev)
--Autok (AutoKod, Rendszam, TipusID, SzinKod, GyartasiDatum, NapiDij, Csillag) 
--Extrak (ExtraID, ExtraNev)
--AutoExtraja (Rendszam, ExtraID)
--Berlok (BerloID, SzemSzam, Nev, Helyseg, Cim, SzulDatum)
--Berel (BerlesID, AutoKod, BerloID, Mikortol, Meddig, Ar) 

--1. Írjunk tárolt eljárást, amely kilistázza az adatbázisban szereplő autótipusokat és azok számát (AutokSzama)!
GO
CREATE OR ALTER PROCEDURE spFeladat1
AS
BEGIN
	SET NOCOUNT ON
	SELECT Autok.TipusID, Tipusok.TipusNev, COUNT(Autok.TipusID) AS AutokSzama
	FROM Autok JOIN Tipusok ON Autok.TipusID = Tipusok.TipusID
	GROUP BY Autok.TipusID, Tipusok.TipusNev
END
GO
EXEC spFeladat1

--2. Írjunk tárolt eljárást, melynek bemenő paramétere egy természetes szám (@pSzam)!
--Ha @pSzam<0, írjunk ki megfelelő hibaüzenetet!
--Ellenkező esetben, írassuk ki azon típusokat (TipusNev, MarkaNev), mely(ek) több színben elérhető(ek), mint a paraméterként megadott szám!
GO
CREATE OR ALTER PROCEDURE spFeladat2(@pSzam INT)
AS
BEGIN
	SET NOCOUNT ON
	IF @pSzam < 0
	BEGIN
		RAISERROR ('A paraméterként megadott szám negatív szám.', 12,1);
	END
	ELSE
	BEGIN
		SELECT TipusNev, MarkaNev
		FROM Tipusok JOIN Markak ON Tipusok.MarkaID = Markak.MarkaID
		WHERE TipusID IN (SELECT TipusID
							FROM (SELECT TipusID, COUNT(DISTINCT SzinKod) AS SzinekSzama
									FROM Autok
									GROUP BY TipusID) AS SzinekSz
							WHERE SzinekSz.SzinekSzama >= @pSzam)
	END
END
GO
EXEC spFeladat2 '2'

--3. Írjunk tárolt eljárást, mely a @pNapiDij-nál olcsóbb autók napidíját megemeli 20%-kal!
GO
CREATE OR ALTER PROCEDURE spFeladat3(@pNapiDij INT)
AS
BEGIN
	SET NOCOUNT ON
	UPDATE Autok
	SET NapiDij *= 1.20
	WHERE NapiDij < @pNapiDij
END
GO
EXEC spFeladat3 '50'

--4. Írjunk tárolt eljárást, mely megadja a paraméterként megadott típus (@pTipusNev) és ugyancsak a paraméterként megadott extra (@ExtraNev)
--esetén a bérlések átlagárát (adott típusba tartozó, adott extrával rendelkező autók)!
--Ha az adott típus egyetlen autója sem rendelkezik az adott extrával, akkor a @pOut kimeneti paraméter értékét állítsuk -2-re, ellenkező esetben 0-ra!
GO
CREATE OR ALTER PROCEDURE spFeladat4(@pTipusNev VARCHAR(30), @ExtraNev VARCHAR(30), @pOut INT OUT)
AS
BEGIN
	SET NOCOUNT ON
	DECLARE @Atlag INT = (SELECT AVG(NapiDij) AS BérlésiÁtlag
							FROM Tipusok
								JOIN Autok ON Tipusok.TipusID = Autok.TipusID
								JOIN AutoExtraja ON Autok.AutoKod = AutoExtraja.AutoKod
								JOIN Extrak ON AutoExtraja.ExtraID = Extrak.ExtraID
							WHERE Extrak.ExtraNev = @pTipusNev AND TipusNev = @ExtraNev)
	IF @Atlag IS NOT NULL
	BEGIN
		PRINT 'Átlag bérlési ár: ' + CONVERT(VARCHAR(5), @Atlag)
		SET @pOut=0
	END
	ELSE
	BEGIN
		SET @pOut=-2
	END
END
GO
DECLARE @pOut INT
EXEC spFeladat4 'Autoriaszto', 'Passat', @pOut OUT
SELECT @pOut AS KimenetiParameter

--5. Írjunk tárolt eljárást, melynek bemeneti paramétere: @pEv- int típusú!
--A tárolt eljárás segítségével írassuk ki azon bérlő(k) nevét, aki(k) a legtöbb autót bérelték ki a paraméterként megadott évben!
GO
CREATE OR ALTER PROCEDURE spFeladat5(@pEv INT)
AS
BEGIN
	SET NOCOUNT ON
	SELECT BerlesID, BerloID, Mikortol INTO #Azévi
	FROM Berel
	WHERE YEAR(Mikortol) = @pEv

	DECLARE @Max INT = (SELECT MAX(T.BérlésekSzáma)
						FROM (SELECT COUNT(BerlesID) AS BérlésekSzáma
								FROM #Azévi
								GROUP BY BerloID) AS T)
	SELECT Nev
	FROM Berlok
	WHERE BerloID IN (SELECT BerloID
						FROM #Azévi
						GROUP BY BerloID
						HAVING COUNT(BerlesID) = @Max)
END
GO
EXEC spFeladat5 '2018'

--6. Írjunk függvényt, mely visszatéríti a legdrágább autó árát!
GO
CREATE OR ALTER FUNCTION fnFeladat6()
RETURNS INT AS
BEGIN
	DECLARE @legdragabb INT = (SELECT TOP 1 NapiDij
								FROM Autok	
								ORDER BY NapiDij DESC)
RETURN @legdragabb
END
GO

DECLARE @legdragabb INT
EXEC @legdragabb = fnFeladat6
SELECT @legdragabb AS Legdrágább

--7. Írjunk függvényt, mely visszatéríti, hogy egy adott extrával (@pExtraNev) hány autó rendelkezik!
GO
CREATE OR ALTER FUNCTION fnFeladat7 (@pExtraNev VARCHAR(30))
RETURNS INT AS
BEGIN
	DECLARE @darabszam INT = (SELECT COUNT(AutoExtraja.AutoKod)
								FROM Extrak
										JOIN AutoExtraja ON Extrak.ExtraID = AutoExtraja.ExtraID
								WHERE ExtraNev = @pExtraNev
								GROUP BY Extrak.ExtraID)

	RETURN @darabszam
END
GO

DECLARE @darabszam INT
EXEC @darabszam = fnFeladat7 'Klima'
SELECT @darabszam AS AutókSzáma

--8. Írjunk függvényt, mely a paraméterként megadott autómárka (@pMarkaNev-string típusú) esetén megnézi,
--hogy a márkához tartozó autóknál mennyi a legnagyobb csillag értéke!
--Ha Csillag értéke 1-2 között van, akkor térítsünk vissza 1-et, ha Csillag=3, akkor 2-t,
--ha pedig Csillag>=4, akkor 3 legyen a visszatérítendő érték!
GO
CREATE OR ALTER FUNCTION fnFeladat8(@pMarkaNev VARCHAR(30))
RETURNS INT AS
BEGIN
	DECLARE @csillag INT = (SELECT TOP 1 Autok.Csillag
							FROM Markak
								JOIN Tipusok ON Markak.MarkaID = Tipusok.MarkaID
								JOIN Autok ON Tipusok.TipusID = Autok.TipusID
							WHERE Markak.MarkaNev = @pMarkaNev
							ORDER BY Autok.Csillag DESC)
	DECLARE @ertek INT
	IF @csillag = 1 OR @csillag = 2 SET @ertek = 1
		ELSE IF @csillag = 3 SET @ertek = 2
		ELSE IF @csillag >= 4 SET @ertek = 3
	RETURN @ertek
END
GO
DECLARE @ertek INT
EXEC @ertek = fnFeladat8 'Audi'
SELECT @ertek AS VisszatérítettÉrték