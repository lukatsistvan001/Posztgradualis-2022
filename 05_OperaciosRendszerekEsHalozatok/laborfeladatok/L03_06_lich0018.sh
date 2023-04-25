#!/bin/bash
#Írjon shell script-et, amely az első paraméterként megadott mélységig mindenkinek írásjogot ad a további paraméterekként megadott katalógusokban és azok alkatalógusaiban lévő közönséges állományokra.
#Minden egyes ilyen állomány esetén írjuk ki a következőket:
#"... katalógus ... állománya írható." (ahol a ...-ok helyére a megfelelő elérési út, illetve állománynév kerüljön)
#ha az állomány kevesebb, mint 5 soros, akkor írjuk ki a teljes állományt,
#ha több, mint 5 de kevesebb, mint 10 soros, akkor  az állomány első 5 sorát,
#különben az első kettő és az utolsó két sort, illetve középen egy sor "..." szerepeljen.
#Az olvashatóság kedvéért két állományra vonatkozó kiírás közé tegyünk egy elválasztó sort:
#pl. "---------------------------------------------"

if [[ $# -eq 0 ]] || [[ ! $1 =~ ^[0-9]+$ ]]
then
	echo "HIBA! Használat: $0 szám katalógus1 katalógus2 ..."
	exit 1
fi

for parameter in ${@:2}
do
	find $parameter -maxdepth $1 -type f >tmp
	while read file
	do
		echo "---------------------------------------------"
		chmod ugo+w $file
		echo "`dirname $file` katalógus `basename $file` állománya írható."

		lines=`wc -l $file | cut -d " " -f 1`
		if [[ $lines -le 5 ]]
		then
			cat $file
		elif [[ $lines -gt 5 ]] && [[ $lines -lt 10 ]]
		then
			head -n 5 $file
		else
			head -n 2 $file
			echo "..."
			(( mid=$lines/2 ))
			sed -n "$mid""p" $file
			echo "..."
			tail -n 2 $file
		fi
	done <tmp
	rm tmp
done
