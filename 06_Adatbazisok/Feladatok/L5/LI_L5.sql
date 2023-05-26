--1. Adjuk meg a (‘I love you too’, ‘Incident at Oglala’, ‘Incubus, The’) című filmek rendezőit! Minden rendező csak egyszer jelenjen meg az eredményben!
SELECT RendezoNev
FROM Filmek JOIN Rendezte ON Filmek.FilmID = Rendezte.FilmID
			JOIN Rendezok ON Rendezte.RendezoID = Rendezok.RendezoID
WHERE FilmCim LIKE 'I love you too' OR
	  FilmCim LIKE 'Incident at Oglala' OR
	  FilmCim LIKE 'Incubus, The'
GROUP BY RendezoNev

--2. Adjuk meg minden film esetén műfajai számát! (Filmek.FilmCím, MűfajokSzáma)
SELECT Filmek.FilmCim, COUNT(Filmek.FilmID) AS MufajokSzama
FROM Filmek JOIN Mufaja ON Filmek.FilmID = Mufaja.FilmID
GROUP BY Filmek.FilmCim

--3. Adjuk meg a studiók átlagköltségeit! (Studiok.StudioNev, AtlagKoltseg)
SELECT Studiok.StudioNev, SUM(Filmek.Koltseg) AS AtlagKoltseg
FROM Filmek JOIN Studiok ON Filmek.StudioID = Studiok.StudioID
GROUP BY Studiok.StudioNev

--4. Adjuk meg a legkisebb költségvetésű film(ek) címét és költségét! (Filmek.FilmCim, Filmek.Koltseg)
SELECT FilmCim, Koltseg
FROM Filmek
WHERE Filmek.Koltseg = (SELECT MIN(Filmek.Koltseg)
						FROM Filmek)

--5. Adjuk meg azon filmeket, melyeknek legalább 3 műfajuk van! (Filmek.FilmCim)
SELECT FilmCim
FROM Filmek JOIN Mufaja ON Filmek.FilmID = Mufaja.FilmID
GROUP BY FilmCim
HAVING COUNT(Mufaja.FilmID) >=3

--6. Adjuk meg a `Mayert-Luettgen` stúdióban hány különböző rendező dolgozott.
SELECT COUNT(DISTINCT Rendezok.RendezoID) AS RendezokSzama
FROM Filmek JOIN Studiok ON Filmek.StudioID=Studiok.StudioID
			JOIN Rendezte ON Filmek.FilmID = Rendezte.FilmID
			JOIN Rendezok ON Rendezte.RendezoID = Rendezok.RendezoID
WHERE StudioNev LIKE 'Mayert-Luettgen'

--7. Adjuk meg a horror filmek átlagköltségét! (AtlagKoltseg)
SELECT AVG(koltseg) AS AtlagKoltseg
FROM Filmek JOIN Mufaja ON Filmek.FilmID = Mufaja.FilmID
			JOIN Mufajok ON Mufaja.MufajID = Mufajok.MufajID
WHERE MufajNev LIKE 'horror'

--8. Adjuk meg azon rendezőket, akik rendeztek drámát és komédiát is! (Rendezok.RendezoNev)
SELECT Rendezok.RendezoNev
FROM Rendezok
WHERE Rendezok.RendezoID IN (SELECT Rendezok.RendezoID
							 FROM Filmek JOIN Mufaja ON Filmek.FilmID = Mufaja.FilmID
							    		 JOIN Mufajok ON Mufaja.MufajID = Mufajok.MufajID
										 JOIN Rendezte ON Filmek.FilmID = Rendezte.FilmID
										 JOIN Rendezok ON Rendezte.RendezoID = Rendezok.RendezoID
							 WHERE Mufajok.MufajNev LIKE 'Drama')
AND Rendezok.RendezoID IN (SELECT Rendezok.RendezoID
						   FROM Filmek JOIN Mufaja ON Filmek.FilmID = Mufaja.FilmID
									   JOIN Mufajok ON Mufaja.MufajID = Mufajok.MufajID
									   JOIN Rendezte ON Filmek.FilmID = Rendezte.FilmID
									   JOIN Rendezok ON Rendezte.RendezoID = Rendezok.RendezoID
						   WHERE Mufajok.MufajNev LIKE 'Comedy')

--9. Adjuk meg azon filmeket, melyek legalább annyiba kerültek, mint az 'I Love You Too' című film! (Filmek.Filmcim)
SELECT FilmCim
FROM Filmek
WHERE Koltseg >= (SELECT Koltseg
				  FROM Filmek
				  WHERE FilmCim LIKE 'I Love You Too')

--10. Adjuk meg a második legkisebb költségű filmet! (Filmek.Filmcim)
SELECT TOP 1 FilmCim
FROM Filmek
WHERE Koltseg > (SELECT MIN(Koltseg)
				 FROM Filmek)
ORDER BY Koltseg

--11. Adjuk meg azon filmeket, melyeknek legalább két műfaja van! (Filmek.Filmcim)
SELECT Filmek.FilmCim
FROM Filmek JOIN Mufaja ON Filmek.FilmID = Mufaja.FilmID
		    JOIN Mufajok ON Mufaja.MufajID = Mufajok.MufajID
GROUP BY Filmek.FilmID, Filmek.FilmCim
HAVING COUNT(Mufajok.MufajNev)>=2

--12. Adjuk meg, mely rendező(k) rendezte(k) a legtöbb filmet! (Rendezők.RendezoNev)
SELECT Rendezok.RendezoNev
FROM Rendezte JOIN Rendezok ON Rendezte.RendezoID = Rendezok.RendezoID
GROUP BY Rendezte.RendezoID, Rendezok.RendezoNev
HAVING COUNT(Rendezte.FilmID) = (SELECT MAX(TT.FilmekSzama)
								FROM (SELECT COUNT(Rendezte.RendezoID) AS FilmekSzama
										FROM Rendezte
										GROUP BY Rendezte.RendezoID) AS TT)

--13. Adjuk meg azon rendezőket, akik CSAK horror filmet rendeztek! (Rendezők.RendezoNev)
SELECT Rendezok.RendezoNev
FROM Rendezok	JOIN Rendezte ON Rendezok.RendezoID = Rendezte.RendezoID
				JOIN Filmek ON Rendezte.FilmID = Filmek.FilmID
				JOIN Mufaja ON Filmek.FilmID = Mufaja.FilmID
				JOIN Mufajok ON Mufaja.MufajID = Mufajok.MufajID
WHERE Mufajok.MufajNev = 'Horror'
GROUP BY Rendezok.RendezoID, Rendezok.RendezoNev
EXCEPT
SELECT Rendezok.RendezoNev
FROM Rendezok	JOIN Rendezte ON Rendezok.RendezoID = Rendezte.RendezoID
				JOIN Filmek ON Rendezte.FilmID = Filmek.FilmID
				JOIN Mufaja ON Filmek.FilmID = Mufaja.FilmID
				JOIN Mufajok ON Mufaja.MufajID = Mufajok.MufajID
WHERE Mufajok.MufajNev <> 'Horror'
GROUP BY Rendezok.RendezoID, Rendezok.RendezoNev

--14. Adjuk meg azon stúdiókat, melyek minden műfajban készítettek filmet! (Studiok.StudioNev)
SELECT TT.SNev
FROM (SELECT Studiok.StudioNev AS SNev, COUNT(DISTINCT Mufaja.MufajID) AS MufajokSzama
				FROM Mufajok	JOIN Mufaja ON Mufajok.MufajID = Mufaja.MufajID
								JOIN Filmek ON Mufaja.FilmID = Filmek.FilmID
								JOIN Studiok ON Filmek.StudioID = Studiok.StudioID
				GROUP BY Studiok.StudioID, Studiok.StudioNev) AS TT
WHERE TT.MufajokSzama = (SELECT COUNT(Mufajok.MufajID) FROM Mufajok)

--15. Adjuk meg studiónként a filmek számát! (Azon stúdiókat is, amelyekhez egy film sincs rendelve!) (Studiok.MufajNev, FilmekSzama)
SELECT Studiok.StudioNev, COUNT(*) AS FilmekSzama
FROM Studiok JOIN Filmek ON Studiok.StudioID = Filmek.StudioID
GROUP BY Studiok.StudioID, Studiok.StudioNev

--16. Adjuk meg azon rendezőket, akik rendeztek drámát, DE sci-fi-t ÉS horrort NEM! (Rendezők.RendezoNev)
SELECT Rendezok.RendezoNev
FROM Rendezok	JOIN Rendezte ON Rendezok.RendezoID = Rendezte.RendezoID
				JOIN Mufaja ON Rendezte.FilmID = Mufaja.FilmID
				JOIN Mufajok ON Mufaja.MufajID = Mufajok.MufajID
WHERE Mufajok.MufajNev = 'Drama'
GROUP BY Rendezok.RendezoID, Rendezok.RendezoNev
EXCEPT
SELECT Rendezok.RendezoNev
FROM Rendezok	JOIN Rendezte ON Rendezok.RendezoID = Rendezte.RendezoID
				JOIN Mufaja ON Rendezte.FilmID = Mufaja.FilmID
				JOIN Mufajok ON Mufaja.MufajID = Mufajok.MufajID
WHERE Mufajok.MufajNev = 'Horror' OR Mufajok.MufajNev = 'Sci-fi'
GROUP BY Rendezok.RendezoID, Rendezok.RendezoNev

--17. Adjuk meg melyik rendező készítette a legkisebb költségű filmet! (Rendezok.RendezőNev)
SELECT TOP 1 Rendezok.RendezoNev
FROM Rendezok	JOIN Rendezte ON Rendezok.RendezoID = Rendezte.RendezoID
				JOIN Filmek ON Rendezte.FilmID = Filmek.FilmID
ORDER BY Koltseg

--18. Adjuk meg azokat a studiókat, ahol nem készült horror film. (Studiok.Nev)
SELECT Studiok.StudioNev
FROM Studiok
WHERE Studiok.StudioID NOT IN (SELECT Studiok.StudioID
								FROM Studiok	JOIN Filmek ON Studiok.StudioID = Filmek.StudioID
												JOIN Mufaja ON Filmek.FilmID = Mufaja.FilmID
												JOIN Mufajok ON Mufaja.MufajID = Mufajok.MufajID
								WHERE Mufajok.MufajNev = 'Horror'
								GROUP BY Studiok.StudioID)