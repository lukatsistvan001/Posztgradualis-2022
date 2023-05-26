--1. Kérdezzük le a Műfajok tábla tartalmát!
SELECT *
FROM Mufajok

--2. Adjuk meg a B betűvel kezdődő filmek címeit és költségeit.
SELECT FilmCim, Koltseg
FROM Filmek
WHERE FilmCim LIKE 'B%'

--3. ABC sorrendben adjuk meg a filmek címeit a költségeikkel együtt!
SELECT FilmCim, Koltseg
FROM Filmek
ORDER BY 1 ASC

--4. Adjuk meg az 1950 és 2015 között megjelent filmek címét!
SELECT FilmCim
FROM Filmek
WHERE MegjEv BETWEEN 1950 AND 2015

--5. Adjuk meg az 1990 előtt született rendezők nevét!
SELECT RendezoNev
FROM Rendezok
WHERE SzulDatum < '1990'

--6. Adjuk meg az A betűvel kezdődő rendezők nevét és születési dátumát.
SELECT RendezoNev, SzulDatum
FROM Rendezok
WHERE RendezoNev LIKE 'A%'

--7. Adjuk meg az 50 évnél idősebb rendezők nevét, akik nevének harmadik betűje ‘T’!
SELECT RendezoNev
FROM Rendezok
WHERE YEAR(GETDATE())-50 > YEAR(SzulDatum) AND RendezoNev LIKE '__T%'

--8. Adjuk meg azon filmek címét és költségét, amelyek 2000 előtt készültek és W-vel kezdődnek! Minden film csak egyszer jelenjen meg az eredményben!
SELECT DISTINCT FilmCim, Koltseg
FROM Filmek
WHERE MegjEv < '2000' AND FilmCim LIKE 'W%'

--9. Adjuk meg a 4500 EURO-nál költségesebb filmek címét és stúdióik nevét! (Filmek.FilmCim, Studiok.StudioNev)
SELECT FilmCim, StudioNev
FROM Filmek JOIN Studiok ON Filmek.StudioID = Studiok.StudioID
WHERE Koltseg > 4500

--10. Adjuk meg a `Audrey Haines` által rendezett filmek stúdióinak nevét! (Studiok.StudioNev)
SELECT DISTINCT StudioNev
FROM Filmek JOIN Rendezte ON Filmek.FilmID = Rendezte.FilmID
			JOIN Rendezok ON Rendezte.RendezoID = Rendezok.RendezoID
			JOIN Studiok ON Filmek.StudioID = Studiok.StudioID
WHERE RendezoNev = 'Audrey Haines'

--11. Adjuk meg azon rendezőket, akik rendeztek horror filmet! (Rendezok.RendezoNev)
SELECT DISTINCT RendezoNev
FROM Filmek JOIN Rendezte ON Filmek.FilmID = Rendezte.FilmID
			JOIN Rendezok ON Rendezte.RendezoID = Rendezok.RendezoID
			JOIN Mufaja ON Filmek.FilmID = Mufaja.FilmID
			JOIN Mufajok ON Mufaja.MufajID = Mufajok.MufajID
WHERE MufajNev = 'horror'

--12. Adjuk meg a `Mayert-Luettgen` stúdióban gyártott filmek rendezőit, életkoruk szerint csökkenő sorrendben!
SELECT DISTINCT RendezoNev
FROM Filmek JOIN Studiok ON Filmek.StudioID = Studiok.StudioID
			JOIN Rendezte ON Filmek.FilmID = Rendezte.FilmID
			JOIN Rendezok ON Rendezte.RendezoID = Rendezok.RendezoID
WHERE StudioNev = 'Mayert-Luettgen'
ORDER BY SzulDatum DESC