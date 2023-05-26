--1. Adjuk meg a ‘Bűbájtan tankönyv’ megjelenési évét és eladási árát!
SELECT MegjEv, EladasiAr
FROM Konyvek
WHERE KonyvCim LIKE 'Bűbájtan tankönyv';

--2. Adjuk meg a ‘sci-fi’ műfajú könyvek címét és kiadójának nevét, melyekből van raktáron és oldalszámuk <200!
--Ügyeljünk rá, hogy minden könyv csak egyszer jelenjen meg az eredményben.
SELECT DISTINCT Konyvek.KonyvCim, Kiadok.KNev
FROM Mufajok JOIN KonyvMufajok ON
Mufajok.MufajID=KonyvMufajok.MufajID
JOIN Konyvek ON
KonyvMufajok.KonyvKod=Konyvek.KonyvKod
JOIN Kiadok ON
Konyvek.KiadoID=Kiadok.KiadoID
WHERE Konyvek.Peldanyszam>0 AND Oldalszam<200;

--3. Adjuk meg azon könyvek címét és kiadójának nevét, melyeket ‘budapesti’ kiadók adtak ki, vásároltak
--belőlük az elmúlt egy hétben, van belőlük raktáron és eladási áruk 50 és 100 közötti, cím szerint
--ábécésorrendben! Ügyeljünk rá, hogy minden könyv csak egyszer jelenjen meg az eredményben.
SELECT DISTINCT Konyvek.KonyvCim, Kiadok.KNev
FROM Konyvek	JOIN Kiadok ON Konyvek.KiadoID = Kiadok.KiadoID
				JOIN VasarlasTartalma ON Konyvek.KonyvKod = VasarlasTartalma.KonyvKod
				JOIN Vasarlasok ON VasarlasTartalma.VasarlasID = Vasarlasok.VID
WHERE	Kiadok.KHelyseg = 'Budapest' AND
		YEAR(GETDATE()) - YEAR(Vasarlasok.Datum) < 5 AND
		Konyvek.EladasiAr > 50 AND Konyvek.EladasiAr < 100
ORDER BY Konyvek.KonyvCim

--4. Adjuk meg azon vásárló(ka)t, aki(k) megadtá(k) a telefonszámukat!
SELECT VNev
FROM Vasarlok
WHERE VTelefon IS NOT NULL

--5. Adjuk meg a ’McGalagony’ által írt (akkor is, ha csak társszerző volt) könyvek számát és átlagos eladási árát!
SELECT COUNT(Konyvek.KonyvKod) AS KonyvSzam, AVG(EladasiAr) AS AtlagAr
FROM Szerzok	JOIN KonyvSzerzok ON Szerzok.SzerzoID=KonyvSzerzok.SzerzoID
				JOIN Konyvek ON KonyvSzerzok.KonyvKod=Konyvek.KonyvKod
WHERE Szerzok.SzerzoNev LIKE '%McGalagony%'

--Szerzonkent konyvek szama és átlagos eladási ár
SELECT Szerzok.SzerzoNev, COUNT(Konyvek.KonyvKod) AS KonyvSzam, AVG(EladasiAr) AS AtlagAr
FROM Szerzok	JOIN KonyvSzerzok ON Szerzok.SzerzoID=KonyvSzerzok.SzerzoID
				JOIN Konyvek ON KonyvSzerzok.KonyvKod=Konyvek.KonyvKod
GROUP BY Szerzok.SzerzoID, Szerzok.SzerzoNev
ORDER BY 2 DESC

--6. Adjuk meg minden vásárló esetén a vásárlásai számát (NEM a könyvek száma érdekel)!
SELECT Vasarlok.VasarloID, Vasarlok.VNev, COUNT(Vasarlasok.VID) AS VasarlasokSzama
FROM Vasarlok JOIN Vasarlasok ON Vasarlok.VasarloID=Vasarlasok.VasarloID
GROUP BY Vasarlok.VasarloID, Vasarlok.VNev

--7. Adjuk meg azon vásárló(ka)t, aki(k) az elmúlt félévben több, mint 2x vásárolt(ak) az üzletben!
SELECT Vasarlok.VasarloID, Vasarlok.VNev, COUNT(Vasarlasok.VID) AS VSzam
FROM Vasarlok JOIN Vasarlasok ON Vasarlok.VasarloID=Vasarlasok.VasarloID
GROUP BY Vasarlok.VasarloID, Vasarlok.VNev
HAVING COUNT(Vasarlasok.VID)>=2

--8. Adjuk meg minden kiadó esetén, szerzőnként csoportosítva az eladott könyvek összmennyiségét
--(KiadoNev, SzerzoNev, KonyvekSzama) formában, összmennyiség szerint csökkenő sorrendben!
SELECT KNev, SzerzoNev, SUM(VasarlasTartalma.Mennyiseg) AS KonyvekSzama
FROM VasarlasTartalma JOIN Konyvek ON
VasarlasTartalma.KonyvKod=Konyvek.KonyvKod
JOIN Kiadok ON
Kiadok.KiadoID=Konyvek.KiadoID
JOIN KonyvSzerzok ON
Konyvek.KonyvKod=KonyvSzerzok.KonyvKod
JOIN Szerzok ON
Szerzok.SzerzoID=KonyvSzerzok.SzerzoID
GROUP BY Kiadok.KiadoID, Kiadok.KNev, Szerzok.SzerzoID, SzerzoNev
ORDER BY 3 DESC

--9. Adjuk meg minden kiadó esetén, hogy hány szerzővel dolgoznak!
SELECT Kiadok.KNev, COUNT(Szerzok.SzerzoID)
FROM Kiadok JOIN Konyvek ON
Kiadok.KiadoID=Konyvek.KiadoID
JOIN KonyvSzerzok ON
Konyvek.KonyvKod=KonyvSzerzok.KonyvKod
JOIN Szerzok ON
KonyvSzerzok.SzerzoID=Szerzok.SzerzoID
GROUP BY Kiadok.KiadoID, Kiadok.KNev

--10. Adjuk meg minden szerző esetén az általa írt könyvek számát! Azokat a szerzőket is vegyük figyelembe, akikhez egy könyv sincs felvezetve!
SELECT Szerzok.SzerzoNev, COUNT(Konyvek.KonyvKod) AS KonyvSzam
FROM Szerzok LEFT JOIN KonyvSzerzok ON
Szerzok.SzerzoID=KonyvSzerzok.SzerzoID
LEFT JOIN Konyvek ON
KonyvSzerzok.KonyvKod=Konyvek.KonyvKod
GROUP BY Szerzok.SzerzoID, Szerzok.SzerzoNev

--11. Adjuk meg azon szerző(ke)t, aki(k) ‘ifjusagi’ és ‘disztopikus fikcio’ műfajban írt(ak) könyvet!
SELECT Szerzok.SzerzoNev
FROM Szerzok JOIN KonyvSzerzok ON
Szerzok.SzerzoID=KonyvSzerzok.SzerzoID
JOIN Konyvek ON
Konyvek.KonyvKod=KonyvSzerzok.KonyvKod
JOIN KonyvMufajok ON
Konyvek.KonyvKod=KonyvMufajok.KonyvKod
JOIN Mufajok ON
KonyvMufajok.MufajID=Mufajok.MufajID
WHERE MufajNev LIKE '%ifjúsági%'
INTERSECT
SELECT Szerzok.SzerzoNev
FROM Szerzok JOIN KonyvSzerzok ON
Szerzok.SzerzoID=KonyvSzerzok.SzerzoID
JOIN Konyvek ON
Konyvek.KonyvKod=KonyvSzerzok.KonyvKod
JOIN KonyvMufajok ON
Konyvek.KonyvKod=KonyvMufajok.KonyvKod
JOIN Mufajok ON
KonyvMufajok.MufajID=Mufajok.MufajID
WHERE MufajNev LIKE '%disztópikus fikció%'

--12. Adjuk meg azon szerző(ke)t, aki(k) CSAK ‘tankonyv’ műfajban írt(ak) könyvet!
SELECT Szerzok.SzerzoNev
FROM Szerzok JOIN KonyvSzerzok ON
Szerzok.SzerzoID=KonyvSzerzok.SzerzoID
JOIN Konyvek ON
Konyvek.KonyvKod=KonyvSzerzok.KonyvKod
JOIN KonyvMufajok ON
Konyvek.KonyvKod=KonyvMufajok.KonyvKod
JOIN Mufajok ON
KonyvMufajok.MufajID=Mufajok.MufajID
WHERE MufajNev LIKE '%tankönyv%'
EXCEPT
SELECT Szerzok.SzerzoNev
FROM Szerzok JOIN KonyvSzerzok ON
Szerzok.SzerzoID=KonyvSzerzok.SzerzoID
JOIN Konyvek ON
Konyvek.KonyvKod=KonyvSzerzok.KonyvKod
JOIN KonyvMufajok ON
Konyvek.KonyvKod=KonyvMufajok.KonyvKod
JOIN Mufajok ON
KonyvMufajok.MufajID=Mufajok.MufajID
WHERE MufajNev NOT LIKE '%tankönyv%'