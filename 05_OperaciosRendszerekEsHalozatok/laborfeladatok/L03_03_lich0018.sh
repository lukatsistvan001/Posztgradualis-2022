#!/bin/bash

#A standard bemenetről beolvasott állományban szereplő felhasználókról határozzuk meg, hogy van-e weblapja. (Egy felhasználónak van saját weblapja, ha a saját alapkatalógusában van egy mások által is elérhető public_html katalógus). A balint.kelemen felhasználónak van weblapja, és megtekinthető ezen a linken.

#PARAMÉTERELLENÖRZÉS
#van megadott paraméter
if [[ $# -eq 0 ]]
then
	echo "HIBA! Használat: $0 állománynév."
	exit 1
fi

#létező és olvasható állomány
if [[ ! -f $1 ]] || [[ ! -r $1 ]]
then
	echo "HIBA! A $1 állomány nem létezik vagy nincs engedélyezett hozzáférés."
	exit 1
fi

#felhasználók feldolgozása
while read sor
do
	for felhasznalo in $sor
	do
		keresett_katalogus=$(eval echo ~$felhasznalo/public_html)
		if [[ -d $keresett_katalogus ]] && [[ -x $keresett_katalogus ]]
		then
			echo "$felhasznalo felhasználónak van weblapja."
		else
			echo "$felhasznalo felhasználónak nincs weblapja."
		fi
	done
done < "$1"

