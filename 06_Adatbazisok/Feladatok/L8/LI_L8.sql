GO
--Írjunk tárolt eljárást, mely segítségével kibérelhetünk egy autót.
--Az eljárás paraméterei legyenek:
	--@pRendszam-string,
	--@pSzemSzam-string,
	--@pBerloNev-string,
	--@pBerloHelyseg-string,
	--@pBerloCim-string,
	--@pBerloSzulDatum-dátum,
	--@pMikortol-dátum,
	--@pMeddig-dátum típusú attribútumok.

CREATE OR ALTER PROCEDURE spL8(
	@pRendszam VARCHAR(10),
	@pSzemSzam VARCHAR(13),
	@pBerloNev VARCHAR(50),
	@pBerloHelyseg VARCHAR(50),
	@pBerloCim VARCHAR(50),
	@pBerloSzulDatum DATE,
	@pMikortol DATE,
	@pMeddig DATE
	)
AS
BEGIN
	SET NOCOUNT ON
	SET TRANSACTION ISOLATION LEVEL SERIALIZABLE
	BEGIN TRANSACTION
	DECLARE @pBerloID INT
--A tárolt eljárás segítségével valósítsuk meg a következőket:
--Ellenőrizzük a bemeneti adatok helyességét:
--létezik-e az adott autó, típus, bérlő; dátumok megfelelőek-e (aktuális dátumnál nem-e régebbi, Mikortol>Meddig stb.):
--Ha az autó nem létezik: hibaüzenet kiíratása mellett térítsünk vissza -1-t.
	IF (@pRendszam NOT IN (SELECT Rendszam FROM Autok	))
	BEGIN
		SELECT @pRendszam
		RAISERROR('Nincs ilyen rendszámú autó.', 11, 1)
		ROLLBACK
		RETURN -1
	END

--Ha a dátumok értéke nem megfelelő: hibaüzenet kiíratása mellett térítsünk vissza -2-t.
	IF (@pMikortol>=@pMeddig)
	BEGIN
		RAISERROR('Nem megfelelő dátumok.', 11, 2)
		ROLLBACK
		RETURN -2
	END

--Ha a bérlő nem létezik, szúrjuk be az adatait a Berlok táblába, majd folytassuk a következő alpontokkal.
	IF (@pSzemSzam NOT IN (SELECT SzemSzam FROM Berlok))
	BEGIN
		INSERT INTO Berlok (SzemSzam, Nev, Helyseg, Cim, SzulDatum, OsszBerlesSzam)
			VALUES (@pSzemSzam, @pBerloNev, @pBerloHelyseg, @pBerloCim, @pBerloSzulDatum, 0)	
		SELECT @pBerloID = BerloID FROM Berlok WHERE SzemSzam=@pSzemSzam
		SELECT @pBerloID
	END
	ELSE
	BEGIN
		SELECT @pBerloID = BerloID FROM Berlok WHERE SzemSzam=@pSzemSzam
	END

--Ellenőrizzük, hogy a megadott időintervallumban kibérelhető-e az autó (csak pontos időintervallum-egyezést vizsgáljunk).
--Ha nem szabad a megadott időintervallumban az autó, akkor megfelelő üzenet kiíratása mellett térítsünk vissza -3-t. 
	IF (SELECT TOP 1 Berel.BerlesID
		FROM Berel JOIN Autok ON Berel.AutoKod=Autok.AutoKod
		WHERE Autok.Rendszam=@pRendszam AND @pMikortol>(SELECT TOP 1 Meddig
															FROM Berel JOIN Autok ON Berel.AutoKod=Autok.AutoKod
															WHERE Autok.Rendszam=@pRendszam
															ORDER BY Meddig DESC)
		ORDER BY Berel.BerlesID) IS NULL
	BEGIN
		RAISERROR('Az autót már bérlik az adott intervallumra', 11, 3)
		ROLLBACK
		RETURN -3
	END

--Ha szabad az autó, akkor a visszatérítési érték 0 legyen.
--Emellett ekkor szúrjunk be egy sort a Berel táblába, majd adjunk hozzá egyet az OsszBerlesSzam attribútumhoz a megfelelő bérlőnél.
	ELSE
	BEGIN
		--DECLARE @pKovBerlesID INT
		--SET @pKovBerlesID = (SELECT TOP 1 BerlesID FROM Berel ORDER BY BerlesID DESC)+1
		DECLARE @pAutoKod INT
		SELECT @pAutoKod = AutoKod FROM Autok WHERE Rendszam=@pRendszam
		INSERT INTO Berel VALUES (@pAutoKod, @pBerloID, @pMikortol, @pMeddig, (SELECT NapiDij FROM Autok WHERE Rendszam='B 20ZZA') * DATEDIFF(DAY, @pMikortol, @pMeddig))
		UPDATE Berlok
		SET OsszBerlesSzam = OsszBerlesSzam + 1
		WHERE BerloID=@pBerloID
	END
	COMMIT
	RETURN 0
END
GO

DECLARE @ret INT
EXEC @ret = spL8 'B 20ZZA', '2491030269678', 'XY', 'Helyseg', 'Cim', '2000-03-01', '2020-08-01', '2020-08-10'
SELECT @ret