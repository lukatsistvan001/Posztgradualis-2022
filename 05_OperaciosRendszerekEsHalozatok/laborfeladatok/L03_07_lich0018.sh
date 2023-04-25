#!/bin/bash

#Írjon shell script-et, amely kiírja a 2., 3., ... stb. paraméterként megadott katalógusok tartalmát az első paraméter függvényében:
#ha ez 1, akkor az állományok neve szerint rendezve,
#ha ez 2, akkor az utolsó módosítás dátuma szerint rendezve
#ha ez 3, akkor a file-ok mérete szerint rendezve.
#Minden alkalommal írjuk ki azt is, hogy az egyes katalógusok hány szöveges állományt tartalmaznak (lásd file parancs kimenete), és ezek nevét. Legvégül pedig határozzuk meg a legtöbb szöveges állományt tartalmazó katalógus nevét.

#paraméterek ellenörzése
#paraméterek számának ellenörzése: legalább két paraméter megadása
#első paraméter ellenörzése: kizárólag 1-3 közötti szám
if [[ $# -lt 2 ]] || ! [[ $1 =~ [1-3] ]]
then
	echo "HIBA! Helyes használat $0 szám katalógusnév1 katalógusnév2 ..."
	exit 1
fi

#ha a megadott paraméter nem létező katalógusok, akkor nem vesszük figyelembe
max=0
keresett_kat=""
for parameter
do
	if ! [[ -d $parameter ]]
	then
		continue
	fi
	echo
	echo "A(z) $1 -es opció szerint a $parameter katalógus tartalma:"
	case $1 in
		1)
			ls -AXl $parameter;;
		2)
			ls -Atl $parameter;;
		3)
			ls -ASl $parameter;;
	esac

	#echo "A $parameter katalógus szöveges állományai:"
	find $parameter -maxdepth 1 -type f >ossz_all
	darab=0
	while read allomany
	do
		if [[ `file $allomany | egrep ".+ text.*"` ]]
		then
			#allomany=$(eval echo $allomany | egrep -o "[^/]*$")
			echo $allomany | egrep -o "[^/]*$" >>szoveges_all
			#echo $allomany
			darab=$((darab+=1))
		fi
	done <ossz_all

	echo
	echo "A(z) $parameter katalógus $darab szöveges állományt tartalmaz:"
	while read allomany
	do
		echo $allomany
	done <szoveges_all

	if [[ $darab -gt $max  ]]
	then
		max=$darab
		keresett_kat=$parameter
	fi
	rm ossz_all
	rm szoveges_all
done

echo
echo "A legtöbb szöveges állományt tartalmazó katalógus:"
echo $keresett_kat
echo "Szöveges állományok száma: $max."
