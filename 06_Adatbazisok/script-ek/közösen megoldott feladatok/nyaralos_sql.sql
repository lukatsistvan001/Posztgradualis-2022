--adjuk meg orszagonkent a nyaralok atlagarat
SELECT Orszagok.OrszagNev, AVG(Ar)
FROM Nyaralok JOIN Orszagok ON
Nyaralok.OrszagID=Orszagok.OrszagID
GROUP BY Nyaralok.OrszagID, Orszagok.OrszagNev

--adjuk meg orszagonkent a 40 euronal dragabb atlagaru nyaralokat
SELECT Orszagok.OrszagNev, AVG(Ar)
FROM Nyaralok JOIN Orszagok ON
Nyaralok.OrszagID=Orszagok.OrszagID
GROUP BY Nyaralok.OrszagID, Orszagok.OrszagNev
HAVING AVG(Ar)>40

--hatarozzuk meg azokat a tulajdonosokat akiknek CSAK Olaszorszagban van nyaralojuk
SELECT Tulajdonosok.Nev
FROM Tulajdonosok JOIN Nyaralok ON
Tulajdonosok.TulajID=Nyaralok.TulajID
JOIN Orszagok ON
Nyaralok.OrszagID=Orszagok.OrszagID
WHERE Orszagok.OrszagNev='Olaszorszag'
EXCEPT
SELECT Tulajdonosok.Nev
FROM Tulajdonosok JOIN Nyaralok ON
Tulajdonosok.TulajID=Nyaralok.TulajID
JOIN Orszagok ON
Nyaralok.OrszagID=Orszagok.OrszagID
WHERE Orszagok.OrszagNev<>'Olaszorszag'

--hatarozzuk meg azokat a tulajdonosokat akiknek Olaszorszagban ES Magyarorszagon van nyaralojuk
SELECT Tulajdonosok.Nev
FROM Tulajdonosok JOIN Nyaralok ON
Tulajdonosok.TulajID=Nyaralok.TulajID
JOIN Orszagok ON
Nyaralok.OrszagID=Orszagok.OrszagID
WHERE Orszagok.OrszagNev='Olaszorszag'
INTERSECT
SELECT Tulajdonosok.Nev
FROM Tulajdonosok JOIN Nyaralok ON
Tulajdonosok.TulajID=Nyaralok.TulajID
JOIN Orszagok ON
Nyaralok.OrszagID=Orszagok.OrszagID
WHERE Orszagok.OrszagNev='Magyarorszag'

--hatarozzuk meg azokat a tulajdonosokat akiknek Olaszorszagban VAGY Magyarorszagon van nyaralojuk
SELECT Tulajdonosok.Nev
FROM Tulajdonosok JOIN Nyaralok ON
Tulajdonosok.TulajID=Nyaralok.TulajID
JOIN Orszagok ON
Nyaralok.OrszagID=Orszagok.OrszagID
WHERE Orszagok.OrszagNev='Olaszorszag'
UNION
SELECT Tulajdonosok.Nev
FROM Tulajdonosok JOIN Nyaralok ON
Tulajdonosok.TulajID=Nyaralok.TulajID
JOIN Orszagok ON
Nyaralok.OrszagID=Orszagok.OrszagID
WHERE Orszagok.OrszagNev='Magyarorszag'

--masik megoldas:
SELECT DISTINCT Tulajdonosok.Nev
FROM Tulajdonosok JOIN Nyaralok ON
Tulajdonosok.TulajID=Nyaralok.TulajID
JOIN Orszagok ON
Nyaralok.OrszagID=Orszagok.OrszagID
WHERE Orszagok.OrszagNev='Olaszorszag' OR Orszagok.OrszagNev='Magyarorszag'

--harmadik megoldas:
SELECT DISTINCT Tulajdonosok.Nev
FROM Tulajdonosok JOIN Nyaralok ON
Tulajdonosok.TulajID=Nyaralok.TulajID
JOIN Orszagok ON
Nyaralok.OrszagID=Orszagok.OrszagID
WHERE Orszagok.OrszagNev IN ('Olaszorszag','Magyarorszag')

--adjuk meg azokat a nyaralokat, amelyek  minden az adatbazisban szereplo felszerelessel rendelkeznek
SELECT Nyaralok.NyaraloNev, COUNT(Felszerelesek.FelszerelesID)
FROM Nyaralok JOIN Felszerelesei ON
Nyaralok.NyaraloID=Felszerelesei.NyaraloID
JOIN Felszerelesek ON
Felszerelesek.FelszerelesID=Felszerelesei.FelszerelesID
GROUP BY Nyaralok.NyaraloID, Nyaralok.NyaraloNev
HAVING COUNT(Felszerelesek.FelszerelesID)=(SELECT COUNT(Felszerelesek.FelszerelesID)
										   FROM Felszerelesek)

--adjuk meg azon nyaralokat, melyek legalabb a Nyaralo1 felszereleseivel rendelkeznek.
SELECT Nyaralok.NyaraloNev, count(Felszerelesek.FelszerelesID)
FROM Felszerelesek JOIN Felszerelesei ON
Felszerelesek.FelszerelesID=Felszerelesei.FelszerelesID
JOIN Nyaralok ON
Nyaralok.NyaraloID=Felszerelesei.NyaraloID
WHERE Nyaralok.NyaraloNev<>'Nyaralo1' and
Felszerelesek.FelszerelesID IN (SELECT Felszerelesek.FelszerelesID
								FROM Felszerelesek JOIN Felszerelesei ON
								Felszerelesek.FelszerelesID=Felszerelesei.FelszerelesID
								JOIN Nyaralok ON
								Nyaralok.NyaraloID=Felszerelesei.NyaraloID
								WHERE Nyaralok.NyaraloNev='Nyaralo1')
GROUP BY Nyaralok.NyaraloID, Nyaralok.NyaraloNev
HAVING COUNT(Felszerelesek.FelszerelesID)>(SELECT COUNT(Felszerelesek.FelszerelesID)
											FROM Felszerelesek JOIN Felszerelesei ON
											Felszerelesek.FelszerelesID=Felszerelesei.FelszerelesID
											JOIN Nyaralok ON
											Nyaralok.NyaraloID=Felszerelesei.NyaraloID
											WHERE Nyaralok.NyaraloNev='Nyaralo1')


--adjuk meg a masodik legdragabb nyaralo nevet
SELECT Nyaralok.NyaraloNev
FROM Nyaralok
WHERE Nyaralok.Ar =(SELECT MAX(Ar)
				   FROM Nyaralok
				   WHERE Ar< (SELECT MAX(Ar)
								FROM Nyaralok))

--adjuk meg azokat az orszagokat ahol NINCS nyaralo
SELECT Orszagok.OrszagNEv
FROM Orszagok
EXCEPT
SELECT Orszagok.OrszagNev
FROM Orszagok JOIN Nyaralok ON
Orszagok.OrszagID=Nyaralok.OrszagID