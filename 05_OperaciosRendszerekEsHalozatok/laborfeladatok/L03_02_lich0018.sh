#!/bin/bash
#A standard bemenetről beolvasott állományokról határozzuk meg, hogy állomány vagy katalógus. Az olvasást addig folytassuk amíg exit kulcsszót nem adunk meg. Program befejése előtt írjuk ki, hány darab bemenetet adott meg a felhasználó.

read bemenet
darab = 1
while [[ $bemenet != exit ]]
do
	if [[ -d $bemenet ]]
	then
		echo "$bemenet katalógus."
	elif [[ -f $bemenet ]]
	then
		echo "$bemenet állomány."
	else
		echo "$bemenet nem állomány, de nem is katalógus."
	fi
	read bemenet
	(( darab++ ))
done

echo "Összesen $darab bemenetet adott meg a felhasználó."
