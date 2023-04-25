#!/bin/bash
#Írjon shell scriptet, mely tetszőleges számú paramétert kap. Minden egyes paraméter esetén:
#    - amennyiben az egy katalógus, írjuk ki, hogy az illető katalógusban hány bejegyzés található, és mekkora a mérete
#    - szöveges állomány esetén írjuk ki, hogy hány soros az illető állomány
#    - különben írjuk ki, hogy "<parameter> hibás paraméter" és folytassuk a feldolgozást
#Az állományokra vonatkozó információ (<állománynév>:<sorok száma>) ne csak a képernyőn jelenjen meg, hanem írjuk bele egy "allomanyok.txt" nevű állományba is, és rendezzük azt a sorok számának csökkenő sorrendjében.

for parameter
do
	if [[ -d $parameter ]]
	then
		darab=`du -ah $parameter |  wc -l`
		(( darab-- ))
		echo "A $parameter katalógusban $darab bejegyzés van. Mérete: `du -sh $parameter | cut -f 1`"
	elif [[ `file $parameter | egrep ".+ text.*"` ]]
	then
		sorokSzama=`wc -l $parameter | cut -d " " -f 1`
		echo "Az $parameter állomány $sorokSzama sort tartalmaz."
		echo "$parameter:$sorokSzama" >>allomanyok.txt
	else
		echo "$parameter hibás paraméter"
		continue
	fi
done
sort -t ":" -k 2 -nro allomanyok.txt allomanyok.txt
