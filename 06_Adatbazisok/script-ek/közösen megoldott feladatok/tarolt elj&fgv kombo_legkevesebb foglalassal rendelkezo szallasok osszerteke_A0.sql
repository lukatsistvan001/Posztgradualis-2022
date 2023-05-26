--Class Notebook Maj 19-20.: A. 0.
USE Szallasfoglalas
GO

-----------------------------------------------
SELECT SUM(sz.NapiAr*f.NapokSzama)
FROM Foglalasok f
	JOIN Szallasok sz ON f.SzID=sz.SzID 
WHERE FoglalasID = 39

SELECT * 
FROM Foglalasok 
order by SzID

SELECT SUM(sz.NapiAr*f.NapokSzama)
FROM Foglalasok f
	JOIN Szallasok sz ON f.SzID=sz.SzID 
WHERE sz.SzID = 18

---------------------------------------------
GO
CREATE FUNCTION fnSzallasFoglalasainakErteke(@pszallasID INT)
RETURNS FLOAT
AS
BEGIN
	DECLARE @osszertek FLOAT
	SELECT @osszertek = SUM(sz.NapiAr*f.NapokSzama)
	FROM Foglalasok f
		JOIN Szallasok sz ON f.SzID=sz.SzID 
	WHERE f.SzID = @pszallasID
	RETURN @osszertek
END
GO

--legkevesebbszer lefoglalt szállás:
--nem teljesen jó megoldás:-mi hiányzik?!!!
SELECT sz.szid, COUNT(*) AS foglalasokszama --INTO #t1
FROM Foglalasok f
	RIGHT JOIN Szallasok sz ON f.SzID=sz.SzID 
GROUP BY sz.szid
HAVING COUNT(*) <= ALL
			(SELECT COUNT(*)
			FROM Foglalasok f
				RIGHT JOIN Szallasok sz ON f.SzID=sz.SzID 
			GROUP BY sz.szid)
GO
--Ha tobb szallas is van, amelyik rENDelkezik legkevesebb foglalassal, 
--akkor irassuk ki az id-kat, azonban kozuluk 
--csak a legkisebb id-val rENDelkezo szállás foglalásainak 
--osszerteket irassuk ki (0-t, ha egyszer sem foglaltak le)
--visszatérítési érték: -1, ha vannak egyszer sem lefoglalt szállások
--0, más esetben.

CREATE PROCEDURE spLegkevesebbszerFoglalaltSzallasOsszerteke
AS
BEGIN
	SET NOCOUNT ON
	SELECT SzID INTO #t
    FROM szallasok
	EXCEPT 
	SELECT SzID
	FROM Foglalasok

	IF EXISTS(SELECT 1 FROM #t)
		BEGIN
			SELECT szid, 0 FROM #t
			RETURN -1
		END
	--drop table #t1 --csak tesztelesre
	DECLARE @szallasid INT
	
	SELECT szid, COUNT(*) AS foglalasokszama INTO #t1
	FROM Foglalasok
	GROUP BY szid
	HAVING COUNT(*) <= all 
			(SELECT COUNT(*)
			FROM Foglalasok
			GROUP BY szid)
	SELECT * FROM #t1 
	SELECT TOP 1 @szallasid=szid 
	FROM #t1
	DECLARE @osszertek FLOAT
	EXEC @osszertek = fnSzallasFoglalasainakErteke @szallasid
	SELECT @osszertek
	RETURN 0
END
GO

DECLARE @rt INT
EXEC @rt = spLegkevesebbszerFoglalaltSzallasOsszerteke
SELECT @rt