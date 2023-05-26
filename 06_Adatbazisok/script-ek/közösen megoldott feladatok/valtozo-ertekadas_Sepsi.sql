use Konyvesuzlet_Sepsi
go

--1. lehetoseg: 
DECLARE @konyvszam INT = (select COUNT(*) FROM Konyvek)
PRINT @konyvszam
GO

--2.lehetoseg:
DECLARE @konyvszam INT, @atlagar INT
SET @konyvszam = (select COUNT(*) FROM Konyvek)
SET @atlagar= (select avg(eladasiar) FROM Konyvek)
PRINT @konyvszam
PRINT @atlagar
GO
--3.lehetoseg:
DECLARE @konyvszam INT, @atlagar INT
select @konyvszam=COUNT(*) FROM Konyvek
select @atlagar=avg(eladasiar) FROM Konyvek
PRINT @konyvszam
PRINT @atlagar
GO
--4.lehetoseg:
DECLARE @konyvszam INT, @atlagar INT
select @konyvszam=COUNT(*), @atlagar=avg(eladasiar) FROM Konyvek
PRINT @konyvszam
PRINT @atlagar