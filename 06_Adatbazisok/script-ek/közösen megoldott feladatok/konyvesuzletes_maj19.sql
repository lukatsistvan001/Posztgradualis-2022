use Konyvesuzlet_Sepsi
go

--Sz5. 1. lsd Triggeres bemutato
--Megj. Vasarlasok tablaba torteno beszuras utan fogja 
--vegrehajtani a rendszer a triggert.

--Sz5. 2.
CREATE TABLE Naplo1(
	ID INT IDENTITY,
	Datum DATETIME,
	KonyvKod INT,
	Esemeny VARCHAR(50)
)
GO

CREATE OR ALTER TRIGGER trKonyvAranakValtozasa
ON Konyvek
FOR UPDATE
AS
BEGIN
SET NOCOUNT ON
DECLARE @ujAr FLOAT, @regiAr FLOAT
SELECT @regiAr = EladasiAr FROM deleted
SELECT @ujAr = EladasiAr FROM inserted
SELECT @regiAr AS RegiAr, @ujAr AS UjAr

--feltetelezzuk, hogy a konyv kodjat nem valtoztattuk, ha megis:
IF (SELECT KonyvKod FROM deleted) != (SELECT KonyvKod FROM inserted)
	BEGIN
		RAISERROR ('A k�nyv k�dj�t NE m�dos�tsuk!', 11, 1)
		RETURN
	END
IF (@ujAr = @regiAr)
	PRINT 'Nem v�ltozott az �r.'
	ELSE IF (@ujAr > @regiAr)
		BEGIN
		PRINT 'N�vekedett az �r.'
		INSERT INTO Naplo1 VALUES (GETDATE(), (SELECT KonyvKod FROM deleted), 'N�vekedett az �r.')
		--vagy: ha a konyvkodot elmentjuk a @KonyvKod valtozoba:
		--INSERT INTO Naplo1 VALUES (GETDATE(), @KonyvKod, 'N�vekedett az �r.')
		END
		ELSE PRINT 'Cs�kkent az �r.'
END
GO
-------------------------
SELECT * 
FROM Konyvek
WHERE KonyvKod = 4
---------------------
--trigger "megh�v�sa":
UPDATE Konyvek
SET EladasiAr = 10
--SET EladasiAr = 20
WHERE KonyvKod = 4
---------------------
select * from Naplo1
-------------------------

--3. �rjunk DELETE triggert, amely nem enged t�r�lni t�rzsv�s�rl�t!
UPDATE Vasarlok
SET TorzsvasarloE = 1
WHERE VasarloID IN (1,2,3)

UPDATE Vasarlok
SET TorzsvasarloE = 0
WHERE VasarloID IN (4,5)
-------------
--=csak akkor engedelyezi a torles vegrehajtasat,
--ha a torlendo szemely NEM torzsvasarlo
GO
CREATE OR ALTER TRIGGER trVasarloTorlese
ON Vasarlok
INSTEAD OF DELETE
AS
BEGIN
	SET NOCOUNT ON
	--megj. elmenthetjuk az attr. erteket egy 
	--valtozoban is
	IF (SELECT TorzsvasarloE
		FROM deleted) = 1
		BEGIN
		RAISERROR ('Nem lehet t�rzsv�s�rl�t t�r�lni.', 11, 1)
		RETURN
		END

	UPDATE Vasarlasok
	SET VasarloID = NULL
	WHERE VasarloID = (SELECT VasarloID FROM deleted) 

	IF (@@ERROR !=0)
	BEGIN
		RAISERROR('Hiba a m�dos�t�sn�l.', 11, 1)
		RETURN
	END
	
	DELETE FROM Vasarlok
	WHERE VasarloID = (SELECT VasarloID FROM deleted) 
	
	IF (@@ERROR != 0)
	BEGIN
		RAISERROR('Hiba a t�rl�sn�l.', 12, 1)
		RETURN
	END
END
GO

select * from Vasarlasok
select * from Vasarlok

--trigger "megh�v�sa"
DELETE FROM Vasarlok
WHERE VasarloID = 4