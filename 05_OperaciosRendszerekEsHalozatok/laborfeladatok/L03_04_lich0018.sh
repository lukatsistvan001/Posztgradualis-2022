#!/bin/bash
#A paraméterként megadott szöveges állomány sorairól állapítsuk meg, hogy e-mail címet tartalmaz. A kimenetre írjuk ki az eredményt a következő formátumban:
#<sor száma>:<sor tartalma>:<e-mail cím vagy nem e-mail cím>

if [[ $# -eq 0 ]]
then
	echo "HIBA! Használat: $0 állománynév"
	exit 1
fi

if [[ ! -f $1 ]] || [[ ! -r $1 ]] || [[ ! $(file $1 | egrep "text") ]]
then
	echo "HIBA! A $1 nem szöveges állomány vagy nem olvasható."
	exit 2
fi

sorszam=0
while read sor
do
	(( sorszam++ ))
	tartalmazE=1
	for szo in $sor
	do
		if [[ $szo =~ .+@[^@]+\.[^@]+ ]] || [[ $szo =~ .+@\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\] ]]
		then
			tartalmazE=0
		fi
	done
	if [[ $tartalmazE -eq 0 ]]
	then
		echo "$sorszam:$sor:e-mail cím"
	else
		echo "$sorszam:$sor:nem e-mail cím"
	fi
done < $1
