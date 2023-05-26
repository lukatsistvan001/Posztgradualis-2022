use nyaralo_Sepsi
go

----------------------------------------------
select NyaraloNev, Ar*10 AS UjAR
from Nyaralok
--where Ar*10 > 20 -- helytelen: where UjAR>20
order by UjAR

----------------------------------------------
--Q1:
SELECT TOP 1 Nyaralok.NyaraloNev, Ar
FROM Nyaralok
ORDER BY Nyaralok.Ar DESC

--Q1':
SELECT TOP 1 WITH TIES Nyaralok.NyaraloNev, Ar
FROM Nyaralok
ORDER BY Nyaralok.Ar DESC

--Q2:
SELECT Nyaralok.NyaraloNev, Ar
FROM Nyaralok
WHERE Ar = (SELECT MAX(Ar)
			FROM Nyaralok)

--Q1' es Q2 ekvivalensek:

/*
UPDATE Nyaralok
SET Ar = 100
WHERE NyaraloID = 1
*/
----------------------------------------------------
SELECT *
FROM Nyaralok

SELECT Nyaralok.NyaraloNev, Ar
FROM Nyaralok
WHERE Ar IN (SELECT MAX(Ar)
			FROM Nyaralok
			GROUP BY OrszagID)

--Mit terit vissza?
--NEM ezt: Azon nyaralok nevet es arat, amelyek az 1-es ID-ju orszagban talalhatoak.
--Hanem: Ha van legalabb 1 olyan nyaralo, amelyik az 1-es ID-ju orszagban talalhato, 
--akkor irassuk ki az osszes nyaralo nevet es arat.
SELECT Nyaralok.NyaraloNev, Ar
FROM Nyaralok
WHERE EXISTS 
	(SELECT *
	 FROM Nyaralok
	 WHERE OrszagID=10 --WHERE OrszagID=100
	)

--hatarozzuk meg azokat a tulajdonosokat, akiknek Olaszorszagban ES Magyarorszagon van nyaralojuk
--1.mod:
SELECT Tulajdonosok.Nev
FROM Tulajdonosok 
		JOIN Nyaralok ON Tulajdonosok.TulajID=Nyaralok.TulajID
		JOIN Orszagok ON Nyaralok.OrszagID=Orszagok.OrszagID
WHERE Orszagok.OrszagNev='Olaszorszag'
INTERSECT
SELECT Tulajdonosok.Nev
FROM Tulajdonosok 
	JOIN Nyaralok ON Tulajdonosok.TulajID=Nyaralok.TulajID
	JOIN Orszagok ON Nyaralok.OrszagID=Orszagok.OrszagID
WHERE Orszagok.OrszagNev='Magyarorszag'

--2."mod"-helytelen!!! az AND miatt:
SELECT Tulajdonosok.Nev
FROM Tulajdonosok 
	JOIN Nyaralok ON Tulajdonosok.TulajID=Nyaralok.TulajID
	JOIN Orszagok ON Nyaralok.OrszagID=Orszagok.OrszagID
WHERE Orszagok.OrszagNev='Olaszorszag' AND Orszagok.OrszagNev='Magyarorszag'

--Megj: self-join

--3.mod:
SELECT t.TulajID, t.Nev
FROM Tulajdonosok t
WHERE t.TulajID IN (SELECT TulajID 
					FROM Nyaralok ny
					WHERE OrszagID IN (SELECT OrszagID
									   FROM Orszagok
									   WHERE Orszagok.OrszagNev='Olaszorszag'))
INTERSECT
SELECT t.TulajID, t.Nev
FROM Tulajdonosok t
WHERE t.TulajID IN (SELECT TulajID 
					FROM Nyaralok ny
					WHERE OrszagID IN (SELECT OrszagID
									   FROM Orszagok
									   WHERE Orszagok.OrszagNev='Magyarorszag'))

--4.mod:
SELECT t.TulajID, t.Nev
FROM Tulajdonosok t
WHERE t.TulajID IN (SELECT TulajID 
					FROM Nyaralok ny
					WHERE OrszagID IN (SELECT OrszagID
									   FROM Orszagok
									   WHERE Orszagok.OrszagNev='Olaszorszag'))
AND t.TulajID IN (SELECT TulajID 
					FROM Nyaralok ny
					WHERE OrszagID IN (SELECT OrszagID
									   FROM Orszagok
									   WHERE Orszagok.OrszagNev='Magyarorszag'))

--5.mod:
SELECT Tulajdonosok.TulajID, Tulajdonosok.Nev, OrszagNev INTO #TulajokOrszagai
FROM Tulajdonosok 
	JOIN Nyaralok ON Tulajdonosok.TulajID=Nyaralok.TulajID
	JOIN Orszagok ON Nyaralok.OrszagID=Orszagok.OrszagID

SELECT Nev
FROM #TulajokOrszagai
WHERE OrszagNev ='Olaszorszag'
INTERSECT
SELECT Nev
FROM #TulajokOrszagai
WHERE OrszagNev ='Magyarorszag'

--hatarozzuk meg azokat a tulajdonosokat, akiknek CSAK Olaszorszagban van nyaralojuk
--1.mod:
SELECT Tulajdonosok.Nev
FROM Tulajdonosok 
	JOIN Nyaralok ON Tulajdonosok.TulajID=Nyaralok.TulajID
	JOIN Orszagok ON Nyaralok.OrszagID=Orszagok.OrszagID
WHERE Orszagok.OrszagNev='Olaszorszag'
EXCEPT
SELECT Tulajdonosok.Nev
FROM Tulajdonosok 
	JOIN Nyaralok ON Tulajdonosok.TulajID=Nyaralok.TulajID
	JOIN Orszagok ON Nyaralok.OrszagID=Orszagok.OrszagID
WHERE Orszagok.OrszagNev<>'Olaszorszag'

--2.mod:
SELECT DISTINCT t.Nev
FROM Tulajdonosok t
WHERE t.TulajID IN (SELECT TulajID 
					FROM Nyaralok ny
					WHERE OrszagID IN (SELECT OrszagID
									   FROM Orszagok
									   WHERE Orszagok.OrszagNev='Olaszorszag'))
AND t.TulajID NOT IN (SELECT TulajID 
					  FROM Nyaralok ny
					  WHERE OrszagID IN (SELECT OrszagID
									     FROM Orszagok
									     WHERE Orszagok.OrszagNev<>'Olaszorszag'))

--3.mod:
SELECT Tulajdonosok.Nev
FROM Tulajdonosok 
	JOIN Nyaralok ON Tulajdonosok.TulajID=Nyaralok.TulajID
	JOIN Orszagok ON Nyaralok.OrszagID=Orszagok.OrszagID
WHERE Orszagok.OrszagNev='Olaszorszag'
INTERSECT
--azon tulajok, akiknek csak 1orszagban van nyaralojuk:
SELECT Tulajdonosok.Nev
FROM Tulajdonosok 
	JOIN Nyaralok ON Tulajdonosok.TulajID=Nyaralok.TulajID
	--JOIN Orszagok ON Nyaralok.OrszagID=Orszagok.OrszagID --felesleges
GROUP BY Tulajdonosok.TulajID, Tulajdonosok.Nev
HAVING COUNT(DISTINCT OrszagID) = 1

--4.mod: --ideiglenes (temporális) tábla használatával
SELECT Nev
FROM #TulajokOrszagai
WHERE OrszagNev ='Olaszorszag'
EXCEPT
SELECT Nev
FROM #TulajokOrszagai
WHERE OrszagNev !='Olaszorszag'


--nem szukseges:
DROP TABLE #TulajokOrszagai
DROP TABLE ##TulajokOrszagai

--5.mod: Nezetek hasznalataval:
GO
CREATE VIEW vTulajokOrszagai AS
SELECT Tulajdonosok.TulajID, Tulajdonosok.Nev, OrszagNev 
FROM Tulajdonosok 
	JOIN Nyaralok ON Tulajdonosok.TulajID=Nyaralok.TulajID
	JOIN Orszagok ON Nyaralok.OrszagID=Orszagok.OrszagID
GO

SELECT Nev
FROM vTulajokOrszagai
WHERE OrszagNev ='Olaszorszag'
EXCEPT
SELECT Nev
FROM vTulajokOrszagai
WHERE OrszagNev !='Olaszorszag'