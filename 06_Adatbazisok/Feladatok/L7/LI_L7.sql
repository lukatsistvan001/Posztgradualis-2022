--1.
--Írjunk DELETE triggert, mely akkor aktiválódik, ha törlünk egy sort az Autok táblából!
--Figyelem! A hivatkozási épség megszorítások fenntartása miatt probléma lehet, ha egy olyan autót szeretnék törölni,
--melyhez tartozik extra és/vagy amelyet kibéreltek. Ezt a problémát orvosoljuk a trigger segítségével!
--Az adott autó mindenképp törlődjön a trigger végrehajtásának következtében!
GO
CREATE OR ALTER TRIGGER trAutoTorlese
ON Autok
INSTEAD OF DELETE
AS
BEGIN
	SET NOCOUNT ON
	SELECT * FROM deleted
	DECLARE @torolni int
	SELECT @torolni = AutoKod FROM deleted
	IF (@torolni IN (SELECT AutoKod FROM AutoExtraja))
	BEGIN
		PRINT 'Extrával felszerelt autó volt.'
		DELETE FROM AutoExtraja WHERE AutoKod = @torolni
	END
	IF (@torolni IN (SELECT AutoKod FROM Berel))
	BEGIN
		PRINT 'Bérelve volt.'
		DELETE FROM Berel WHERE AutoKod = @torolni
	END
	DELETE FROM Autok WHERE AutoKod = @torolni
END
GO

--DROP TRIGGER trAutoTorlese

--2.
--a.
--Hozzunk létre egy új táblát:
--AutokNapiAr_Log(ID, Idopont, Muvelet, AutoKod, Rendszam, RegiAr, UjAr),
--ahol Idopont-datetime, Muvelet-varchar(50), RegiAr-int, UjAr-int típusú mezők.
CREATE TABLE AutokNapiAr_Log (
	ID INT IDENTITY CONSTRAINT AutokNapiAr_ID_PK PRIMARY KEY,
	Idopont DATETIME,
	Muvelet VARCHAR(50),
	AutoKod INT CONSTRAINT AutokNapiAr_AutoKod_FK FOREIGN KEY REFERENCES Autok(AutoKod),
	Rendszam VARCHAR(10),
	RegiAr INT,
	UjAr INT)

--b.
--Oldjuk meg, hogy az Autok táblából való törlés esetén az AutokNapiAr_Log tábla AutoKod mezőjének értéke NULL-ra módosuljon!
--Megoldási javaslatok:
--DELETE trigger módosítása az Autok táblára vonatkozóan.
GO
--DROP TRIGGER trAutoKodNullra
CREATE OR ALTER TRIGGER trAutoKodNullra
ON Autok
INSTEAD OF DELETE
AS
BEGIN
	SET NOCOUNT ON
	SELECT * FROM deleted
	DECLARE @torolni int
	SELECT @torolni = AutoKod FROM deleted
	IF (@torolni IN (SELECT AutoKod FROM AutoExtraja))
	BEGIN
		PRINT 'Extrával felszerelt autó volt.'
		DELETE FROM AutoExtraja WHERE AutoKod = @torolni
	END
	IF (@torolni IN (SELECT AutoKod FROM Berel))
	BEGIN
		PRINT 'Bérelve volt.'
		DELETE FROM Berel WHERE AutoKod = @torolni
	END
	IF (@torolni IN (SELECT AutoKod FROM AutokNapiAr_Log))
	BEGIN
		PRINT 'AutokNapiAr_Log megfelelő AutoKod mezője NULL-ra állítva.'
		UPDATE AutokNapiAr_Log SET AutoKod = NULL WHERE AutoKod = @torolni
	END
	DELETE FROM Autok WHERE AutoKod = @torolni
END
GO

--tábla létrehozásakor SET NULL záradék hozzárendelése az AutoKod mezőhöz;
--DROP TABLE AutokNapiAr_Log
CREATE TABLE AutokNapiAr_Log (
	ID INT IDENTITY CONSTRAINT AutokNapiAr_ID_PK PRIMARY KEY,
	Idopont DATETIME,
	Muvelet VARCHAR(50),
	AutoKod INT CONSTRAINT AutokNapiAr_AutoKod_FK FOREIGN KEY REFERENCES Autok(AutoKod) ON DELETE SET NULL,
	Rendszam VARCHAR(10),
	RegiAr INT,
	UjAr INT)

--c.
--Írjunk UPDATE triggert, mely az Autok tábla NapiAr mezőjének módosításakor beszúr egy új sort az AutokNapiAr_Log táblába.
--Az időpont legyen a módosítás időpontja, művelet szövege: ‘ár növelése’ vagy ‘ár csökkentése’, RegiAr-régi érték, UjAr-új érték.
--Megj. Oldjuk meg a feladatot kétféleképpen:

--i. Feltételezzük, hogy egyszerre csak egy sort módosíthatunk a táblában!
GO
--DROP TRIGGER trAutokUpdateNapiAr
CREATE OR ALTER TRIGGER trAutokUpdateNapiAr
ON Autok
FOR UPDATE AS
BEGIN
	SET NOCOUNT ON
	--ID INT, Idopont DATETIME,	Muvelet VARCHAR(50), AutoKod INT, Rendszam VARCHAR(10), RegiAr INT,	UjAr INT
	DECLARE @ID INT, @autoKod INT, @rendszam VARCHAR(10), @rNapiDij INT, @uNapiDij INT, @muvelet VARCHAR(30)
	SELECT @autoKod = AutoKod, @rendszam = Rendszam, @rNapiDij = NapiDij FROM deleted
	SELECT @uNapiDij = NapiDij FROM inserted
	IF (@rNapiDij > @uNapiDij)
		SET @muvelet = 'ár csökkentése'
	ELSE IF (@rNapiDij < @uNapiDij)
			SET @muvelet = 'ár növelése'
		ELSE SET @muvelet = 'ár változatlan'
	INSERT INTO AutokNapiAr_Log VALUES (GETDATE(), @muvelet, @autoKod, @rendszam, @rNapiDij, @uNapiDij)
END
GO

--ii. (fakultatív) Feltételezzük, hogy egyszerre több sort módosíthatunk a táblában! Megj. Kurzor használata szükséges.

--3.
--Írjunk INSERT triggert az Autók táblára vonatkozóan, amely csak akkor engedélyezi a művelet végrehajtását, ha a napiár értéke 50 és 200 RON közötti!
--Megj. Feltételezzük, hogy egyszerre csak egy sort szúrunk be a táblába!
GO
--DROP TRIGGER trAutok50_200
CREATE OR ALTER TRIGGER trAutok50_200 ON Autok INSTEAD OF INSERT AS
BEGIN
	SET NOCOUNT ON
	DECLARE @napiDij INT
	SELECT @napiDij = NapiDij FROM inserted
	IF (@napiDij < 50 OR @napiDij > 200)
		RAISERROR ('A NapiDij értéke nem megengedett. Megendett érték 50 és 200 közötti.', 11,1)
	ELSE
	BEGIN
		DECLARE @rendszam VARCHAR(10), @tipusID INT, @szinKod INT, @gyartasiEv INT, @csillag INT
		SELECT @rendszam = Rendszam, @tipusID = TipusID, @szinKod = SzinKod, @gyartasiEv = GyartasiEv, @csillag = Csillag FROM inserted
		INSERT INTO Autok VALUES (@rendszam, @tipusID, @szinKod, @gyartasiEv, @napiDij, @csillag)
	END
END
GO