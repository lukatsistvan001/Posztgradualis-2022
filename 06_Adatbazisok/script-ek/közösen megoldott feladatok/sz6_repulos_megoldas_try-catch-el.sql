--Sz6. feladat megoldása try/catch blokk használatával:
CREATE OR ALTER PROCEDURE spRepjegyVasarlas_TC
	(@pJaratID INT, @pHonnan varchar(50), @pHova varchar(50), @pDatum date, 
	 @pOra time, @pUtasID INT, @pUUlohely INT, @pSpecKeres varchar(50))
AS
BEGIN
	SET NOCOUNT ON
	DECLARE @retv INT
	/*Ellenőrizzük, hogy létezik-e a járat, az adott utas, az ülőhely megfelelő-e (1 és FerohelyekSzama
	közötti érték-e). Ha nem, akkor hibaüzenet kiíratása mellett térítsünk vissza -1-t, -2-t, illetve -3-t.*/
	BEGIN TRY
	SET TRANSACTION ISOLATION LEVEL SERIALIZABLE
	BEGIN TRANSACTION
	DECLARE @ferohelyszam INT, @eladotthelyekszama INT, @repulesid INT
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
				BEGIN 
					SET @retv = -1
					RAISERROR('Nem letezik az adott jarat.', 11, 1)	
				END

		--utas ellenorzese
		IF NOT EXISTS (SELECT  1 --vagy: SELECT *
					FROM Utasok
					WHERE UtasID = @pUtasID)
			BEGIN
				SET @retv = -2
				RAISERROR('Nem létezik az utas.', 11, 2)
			END

		SELECT @ferohelyszam=FerohelyekSzama
		FROM Jaratok
		WHERE JaratID = @pJaratID

		IF @pUUlohely < 1 AND @pUUlohely>@ferohelyszam
				BEGIN 
					SET @retv = -3
					RAISERROR('Nem megfelelo az ulohely.', 11, 3)
				END

		/*Ellenőrizzük, hogy a megadott időpontban van-e szabad hely az adott járatra. 
		o Ha van szabad hely, akkor ellenőrizzük le, hogy szabad-e a kívánt ülőhely. Ha szabad, akkor a
		visszatérítési érték 0 legyen. Emellett ekkor szúrjunk be egy sort a Foglalas táblába, majd
		adjunk hozzá egyet az EladottHelyekSzama attribútumhoz a megfelelő repülésnél. 
		o Ha nem szabad az ülőhely, akkor a visszatérítési érték legyen egy szabad ülőhely sorszáma.
		*/

		SELECT @RepulesID = RepulesID, @EladottHelyekSzama = EladottHelyekSzama
		FROM Repules
		WHERE JaratID = @pJaratID AND Datum = @pDatum AND Ora = @pOra

		if (@eladotthelyekszama>=@ferohelyszam)
			BEGIN 
				SET @retv = -4 
				RAISERROR('Nincs elegendő hely az adott járatra.', 12, 1)
			END
		/*Ha van szabad hely, akkor ellenőrizzük le, 
		hogy szabad-e a kívánt ülőhely. 
		*/

		IF EXISTS (SELECT * -- vagy: SELECT 1
				   FROM Foglalas
			       WHERE RepulesID = @RepulesID AND Ulohely = @pUUlohely)
			    BEGIN 
					SET @retv = -5 --egy szabad ülőhely sorszámát kellene visszatéríteni
					RAISERROR('Az adott ülőhely foglalt.', 12, 2)
				END

		/*
		Ha szabad, akkor a visszatérítési érték 0 legyen. 
		Emellett ekkor szúrjunk be egy sort a Foglalas táblába, majd adjunk hozzá egyet az EladottHelyekSzama attribútumhoz a megfelelő repülésnél. 
		*/

		INSERT INTO Foglalas VALUES (@RepulesID, @pUtasID, @pUUlohely, @pSpecKeres)

		UPDATE Repules
		SET EladottHelyekSzama += 1
		WHERE RepulesID = @RepulesID

		PRINT 'Minden rendben.'
		COMMIT 
		RETURN 0

	END TRY 

	BEGIN CATCH
	 /*--ha szeretnenk kiíratni a hibaüzeneteket is:
		SELECT ERROR_MESSAGE(), ERROR_SEVERITY(), ERROR_STATE()
	 */
	 ROLLBACK
	 RETURN @retv	
	END CATCH
END

---------------------------------------
DECLARE @ret INT
--EXEC @ret = spRepjegyVasarlas_TC 1, 'Avram Iancu repuloter', 'Muncheni repuloter', '2020-04-10', '12:00:00', 11, 5, 'vegetarianus'  --nemlétező reptér
EXEC @ret = spRepjegyVasarlas_TC  1, 'Avram Iancu repuloter', 'London-Luton repuloter', '2020-04-10', '12:00:00', 11, 5, 'vegetarianus'  --helyes
SELECT @ret