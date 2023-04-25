#!/bin/bash
#Írassa ki a paraméterként megadott állományban szereplő hostokról, hogy elérhetőek-e vagy sem (használjuk a ping parancsot, hogy eldönthessük a host elérhető-e vagy sem).

if [[ $# -eq 0 ]]
then
	echo "HIBA! Használat: $0 állománynév"
	exit 1
fi

while read sor
do
	for host in $sor
	do
		ping -q -c 5 $host
		if [[ $? -eq 0 ]]
		then
			echo "--- A $host elérhető. ---"
		else
			echo "--- A $host nem elérhető. ---"
		fi
	done
done <$1
