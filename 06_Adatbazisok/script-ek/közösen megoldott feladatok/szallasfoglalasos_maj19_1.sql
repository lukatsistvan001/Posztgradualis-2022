--Class Notebook: A. 
---Minden foglalás esetén számoljuk ki a foglalás értékét (lsd. Foglalasok.Osszar mező - az itt megadott értékek nem tükrözik a valóságot, így azok értékét mi kellene meghatározzuk)! 
use Szallasfoglalas
go

SELECT FoglalasID, SUM(NapiAr*NapokSzama) AS Osszar
FROM Foglalasok f
	JOIN Szallasok sz on f.SzID=sz.SzID 
GROUP BY FoglalasID

--mukodik csoportositas nelkul is:
SELECT FoglalasID, NapiAr*NapokSzama AS Osszar
FROM Foglalasok f
	JOIN Szallasok sz on f.SzID=sz.SzID 


--B.
ALTER TABLE Foglalasok
ADD CONSTRAINT ck_NapokSzama CHECK (NapokSzama >=3)

ALTER TABLE Szallasok
ADD CONSTRAINT ck_NapiAr CHECK (NapiAr <= 100)

--C.
--nem lehetséges sima megszorítással:
/*ALTER TABLE Helysegek 
ADD CONSTRAINT ck_AtlagAr CHECK 
	(SELECT AVG(NapiAr)
	 FROM Szallasok 
		JOIN Helysegek ON Szallasok.HKod=Helysegek.HKod
     GROUP BY Helysegek.HKod	
	)<=100
*/
--helyes: triggerrel 
GO
--feltetelezzuk, hogy egyszerre csak 1sort szurhatunk meg
--a Szallasok tablaba
CREATE OR ALTER TRIGGER trHelysegekSzallasainakAtlagara
ON Szallasok
INSTEAD OF INSERT --[, UPDATE, DELETE]
AS
BEGIN
	--vizsgaljuk, hogy az adott helyseg szalalsainak 
	--atlagara ne nojon
	---100euro fole
	declare @iHKod int, @iNapiAr float, @ossz float, @db int, @ujatlag float
	select @iHKod = HKod, @iNapiAr = NapiAr 
	from inserted
	--select * from deleted
	--select * from updated --nincs ilyen tabla
	
	SELECT @ossz = SUM(NapiAr), @db = COUNT(SzID)
	FROM Szallasok 
	WHERE HKod = @iHKod

	set @ossz += @iNapiAr
	set @db+=1

	set @ujatlag = @ossz/@db

	select @ujatlag
	
	if @ujatlag > 100
		RAISERROR ('A beszúrás nem lehetséges.', 11, 1)
	else 
		BEGIN
		PRINT 'Minden rendben. A beszúrás lehetséges.'
		INSERT INTO Szallasok 
			SELECT SzNev, HKod, SztID, Csillag, SzobakSzama, NapiAr
			FROM inserted
		--vagy:
		--INSERT INTO Szallasok VALUES (@iSzNev, @iHKod, @iSztID, @iCsillag, @iSzobakSzama, @iNapiAr

		END
END
GO
-----------------------------------------
--select * from inserted

select * from Szallasok

SELECT AVG(NapiAr)
FROM Szallasok 
WHERE HKod = 1
----------------------
--trigger "meghívása":
INSERT INTO Szallasok (HKod, NapiAr) VALUES (1, 500)
--INSERT INTO Szallasok (HKod, NapiAr) VALUES (1, 50)
----------------------
select * from Szallasok
-----------------------------------------

--Így is megoldható a feladat, de ezt a megoldást NE használjuk!
GO
CREATE OR ALTER TRIGGER trHelysegekSzallasainakAtlagara_NeIgy
ON Szallasok
FOR INSERT --[, UPDATE, DELETE]
AS
BEGIN
	--vizsgaljuk, hogy az adott helyseg szalalsainak 
	--atlagara ne nojon
	---100euro fole
	declare @iHKod int
	select @iHKod = HKod from inserted
	
	--itt mar be lesz szurva a Szallasok tablaba az uj sor:
	IF (SELECT AVG(NapiAr)
		FROM Szallasok 
		WHERE HKod = @iHKod) > 100
			BEGIN
				RAISERROR ('A beszúrás nem lehetséges.', 11, 1)
				ROLLBACK
			END
END
GO
----------------------------------------
--erdemes letiltani az instead of triggert, amig ezt kiprobaljuk:
ALTER TABLE Szallasok
DISABLE TRIGGER trHelysegekSzallasainakAtlagara

select * from Szallasok

SELECT AVG(NapiAr)
FROM Szallasok 
WHERE HKod = 1
----------------------
--trigger "meghívása":
INSERT INTO Szallasok (HKod, NapiAr) VALUES (1, 500)
--INSERT INTO Szallasok (HKod, NapiAr) VALUES (1, 50)
----------------------
select * from Szallasok
-----------------------------------------