--1.
SELECT DISTINCT Fogorvosok.OrvosVNev, Fogorvosok.OrvosKNev
FROM Fogorvosok
	JOIN Elojegyzesek ON Fogorvosok.OrvosID = Elojegyzesek.OrvosID
	JOIN Kezel ON Elojegyzesek.ElojegyzesID = Kezel.ElojegyzesID
	JOIN Kezelesek ON Kezel.KezelesID=Kezelesek.KezelesID
WHERE Kezelesek.KezelesNev='Fogfeherites' AND YEAR(Elojegyzesek.Datum)=2022 AND MONTH(Elojegyzesek.Datum)=10

--2.
SELECT *
FROM KezeloHelyisegek
WHERE SzobaSzam IN (SELECT KezeloHelyisegek.SzobaSzam
					FROM KezeloHelyisegek
						JOIN Elojegyzesek ON KezeloHelyisegek.SzobaSzam = Elojegyzesek.Szobaszam
						JOIN Kezel ON Elojegyzesek.ElojegyzesID = Kezel.ElojegyzesID
						JOIN Kezelesek ON Kezel.KezelesID = Kezelesek.KezelesID
					WHERE Kezelesek.KezelesNev = 'Rontgen keszitese'
					EXCEPT
					SELECT KezeloHelyisegek.SzobaSzam
					FROM KezeloHelyisegek
						JOIN Elojegyzesek ON KezeloHelyisegek.SzobaSzam = Elojegyzesek.Szobaszam
						JOIN Kezel ON Elojegyzesek.ElojegyzesID = Kezel.ElojegyzesID
						JOIN Kezelesek ON Kezel.KezelesID = Kezelesek.KezelesID
					WHERE Kezelesek.KezelesNev = 'Fogszabalyzo allitasa')

--3.
SELECT OrvosVNev, OrvosKNev, TT2.KSz AS KezelesekSzama
FROM Fogorvosok
	JOIN (SELECT OrvosID, COUNT(DISTINCT KezelesID) AS KSz
			FROM Elojegyzesek
			JOIN Kezel ON Elojegyzesek.ElojegyzesID = Kezel.ElojegyzesID
			GROUP BY OrvosID
			HAVING COUNT(DISTINCT KezelesID)=(SELECT MAX(TT.KSz)
												FROM (SELECT OrvosID, COUNT(DISTINCT KezelesID) AS KSz
														FROM Elojegyzesek
														JOIN Kezel ON Elojegyzesek.ElojegyzesID = Kezel.ElojegyzesID
														GROUP BY OrvosID) AS TT)) AS TT2 ON Fogorvosok.OrvosID = TT2.OrvosID

--4.
SELECT Betegek.BetegVNev, Betegek.BetegKNev, TT.BetegID AS ElorejegyzesekSzama
FROM Betegek
	LEFT JOIN (SELECT BetegID, COUNT(ElojegyzesID) AS ESz
			FROM Elojegyzesek
			GROUP BY BetegID) AS TT ON Betegek.BetegID = TT.BetegID

--5.
--a1.
ALTER TABLE Elojegyzesek
ADD KezelesekSzama INT DEFAULT 0
CONSTRAINT CKezelesekSzama CHECK (KezelesekSzama>=0)

--a2.
ALTER TABLE Betegek DROP COLUMN Hazszam

--b
GO
CREATE OR ALTER TRIGGER tr_KezelTorles
ON Kezel  AS 
BEGIN

END
GO

--6
GO
CREATE OR ALTER PROCEDURE prEljaras (
	@pOrvosID INT,
	@pOrvosVNev VARCHAR(20),
	@pOrvosKNev VARCHAR(20),
	@pDatum DATE,
	@pOra INT,
	@pBetegID INT,
	@pSzobaSzam INT)
AS
BEGIN
	BEGIN TRANSACTION
	SET TRANSACTION ISOLATION LEVEL SERIALIZABLE
	SET NOCOUNT ON
	IF ((@pOrvosID NOT IN (SELECT OrvosID FROM Fogorvosok)) OR
		(@pOrvosVNev NOT IN (SELECT OrvosVNev FROM Fogorvosok)) OR
		(@pOrvosKNev NOT IN (SELECT OrvosKNev FROM Fogorvosok)))
	BEGIN
		RAISERROR ('Az adott orvos nem létezik.', 11, 1)
		ROLLBACK
		RETURN -1
	END
	IF (@pBetegID NOT IN (SELECT BetegID FROM Betegek))
	BEGIN
		RAISERROR ('Az adott beteg nem létezik', 11, 2)
		ROLLBACK
		RETURN -2
	END
	IF (@pSzobaSzam NOT IN (SELECT SzobaSzam FROM KezeloHelyisegek))
	BEGIN
		RAISERROR ('Az adott kezelőhelyiség nem létezik', 11, 3)
		ROLLBACK
		RETURN -3
	END
	IF EXISTS (SELECT * FROM Elojegyzesek WHERE Datum = @pDatum AND Ora = @pOra)
	BEGIN
		RAISERROR('Az adott kezelőhelyiség foglalt a megadott időpontban.', 11, 4)
		ROLLBACK
		RETURN 1
	END
	ELSE
	BEGIN
		INSERT INTO Elojegyzesek (Datum, Ora, BetegID, OrvosID, Szobaszam)
			VALUES (@pDatum, @pOra, @pBetegID, @pOrvosID, @pSzobaSzam)
		COMMIT
		RETURN 0
	END
END
GO

DECLARE @ret INT
EXEC prEljaras 1, 'Nagy', 'Istvan', '2023-10-12', 12, 1, 1
SELECT @ret

SELECT * FROM Elojegyzesek