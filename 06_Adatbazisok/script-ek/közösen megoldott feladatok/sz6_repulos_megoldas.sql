/*Sz6.
Írjunk tárolt eljárást, mely segítségével repülőjegye(ke)t tudunk vásárolni A pontból B pontba. 
Az eljárást egyidőben többen is meghívhatják.

Az eljárás paraméterei legyenek: @pJaratID-int, @pHonnan-string, @pHova-string, @pDatum-dátum, @pOra-time, @pUtasID-int, @pUUlohely-int, @pSpecKeres-string. A tárolt eljárás segítségével:

Ellenőrizzük, hogy létezik-e a járat, az adott utas, 
az ülőhely megfelelő-e (1 és FerohelyeSzama közötti érték-e). 
Ha nem, akkor hibaüzenet kiíratása mellett térítsünk vissza -1-t, -2-t, illetve -3-t.
Ellenőrizzük, hogy a megadott időpontban van-e szabad hely az adott járatra. 
Ha van szabad hely, akkor ellenőrizzük le, hogy szabad-e a kívánt ülőhely. Ha szabad, akkor a visszatérítési érték 0 legyen. Emellett ekkor szúrjunk be egy sort a Foglalas táblába, majd adjunk hozzá egyet az EladottHelyekSzama attribútumhoz a megfelelő repülésnél. 
Ha nem szabad az ülőhely, akkor a visszatérítési érték legyen egy szabad ülőhely sorszáma.

*/
USE Repules_Sepsi
GO

CREATE OR ALTER PROCEDURE spRepjegyVasarlas 
	(@pJaratID INT, @pHonnan varchar(50), @pHova varchar(50), @pDatum date, 
	 @pOra time, @pUtasID INT, @pUUlohely INT, @pSpecKeres varchar(50))
AS
BEGIN
	SET NOCOUNT ON

	SET TRANSACTION ISOLATION LEVEL SERIALIZABLE
	BEGIN TRANSACTION
	/*
	Ellenőrizzük, hogy létezik-e a járat, az adott utas, 
	az ülőhely megfelelő-e (1 és FerohelyeSzama közötti érték-e). 
	Ha nem, akkor hibaüzenet kiíratása mellett térítsünk vissza -1-t, -2-t, illetve -3-t.
	*/

	IF NOT EXISTS (SELECT  1 --vagy: SELECT *
					FROM Utasok
					WHERE UtasID = @pUtasID)
	/*vagy:
		IF (SELECT COUNT(*)
			FROM Utasok
			WHERE UtasID = @pUtasID) = 0
	*/
			BEGIN
				RAISERROR('Nem létezik az utas.', 11, 1)
				ROLLBACK
				RETURN -2
			END

	IF NOT EXISTS (SELECT 1 
					FROM Jaratok j
					WHERE JaratID = @pJaratID 
						AND Honnan = (SELECT RepterID 
									  FROM Repterek
									  WHERE Repterek.RNev = @pHonnan)
						AND Hova = (SELECT RepterID 
									  FROM Repterek
									  WHERE Repterek.RNev = @pHova)
				  )
	   	/*Megj.
					SELECT 1 
					FROM Jaratok j
						JOIN Repterek r ON  j.Honnan = r.RepterID OR j.Hova = r.RepterID
					WHERE JaratID = @pJaratID 
		*/
				BEGIN 
					RAISERROR('Nem letezik az adott jarat.', 11, 2)
					ROLLBACK
					RETURN -1		
				END

		DECLARE @ferohelyszam INT, @RepulesID INT, @EladottHelyekSzama INT
		SELECT @ferohelyszam=FerohelyekSzama
		FROM Jaratok
		WHERE JaratID = @pJaratID

		IF @pUUlohely < 1 AND @pUUlohely>@ferohelyszam
				BEGIN 
					RAISERROR('Nem megfelelo az ulohely.', 11, 3)
					ROLLBACK
					RETURN -3		
				END
				
		/*Ellenőrizzük, hogy a megadott időpontban van-e szabad hely az adott járatra.*/
		SELECT @RepulesID = RepulesID, @EladottHelyekSzama = EladottHelyekSzama
		FROM Repules
		WHERE JaratID = @pJaratID AND Datum = @pDatum AND Ora = @pOra

		IF (@ferohelyszam <= @EladottHelyekSzama)
				BEGIN 
					RAISERROR('Nincs elegendő hely az adott járatra.', 12, 1)
					ROLLBACK
					RETURN -4		
				END

		/*Ha van szabad hely, akkor ellenőrizzük le, 
		hogy szabad-e a kívánt ülőhely. 
		Ha szabad, akkor a visszatérítési érték 0 legyen. 
		Emellett ekkor szúrjunk be egy sort a Foglalas táblába, majd adjunk hozzá egyet az EladottHelyekSzama attribútumhoz a megfelelő repülésnél. 
		*/

		IF EXISTS (SELECT * -- vagy: SELECT 1
				   FROM Foglalas
			       WHERE RepulesID = @RepulesID AND Ulohely = @pUUlohely)
			    BEGIN 
					RAISERROR('Az adott ülőhely foglalt.', 12, 2)
					ROLLBACK
					RETURN -5 --egy szabad ülőhely sorszámát kellene visszatéríteni		
				END
		
		--ha szabad az ülőhely
		INSERT INTO Foglalas VALUES (@RepulesID, @pUtasID, @pUUlohely, @pSpecKeres)
		IF (@@ERROR != 0)
				BEGIN 
					RAISERROR('Sikertelen beszúrás.', 16, 1)
					ROLLBACK
					RETURN -10 		
				END

		UPDATE Repules
		SET EladottHelyekSzama += 1
		WHERE RepulesID = @RepulesID
		IF (@@ERROR != 0)
				BEGIN 
					RAISERROR('Sikertelen módosítás.', 17, 1)
					ROLLBACK
					RETURN -10 		
				END

		PRINT 'Minden rendben.'
		COMMIT 
		RETURN 0
END

---------------------------------------
DECLARE @ret INT
EXEC @ret = spRepjegyVasarlas  1, 'Avram Iancu repuloter', 'Muncheni repuloter', '2020-04-10', '12:00:00', 11, 5, 'vegetarianus'  --nemlétező reptér
--EXEC @ret = spRepjegyVasarlas  1, 'Avram Iancu repuloter', 'London-Luton repuloter', '2020-04-10', '12:00:00', 11, 5, 'vegetarianus'  --helyes
SELECT @ret
---------------------------------------
--SELECT * FROM Foglalas

--SELECT @@TRANCOUNT