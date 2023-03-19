# Írjon shell script-et, amely a paraméterként megadott katalógusokra és
# azok alkatalógusainak minden szöveges állományából (nem a txt kiterjesztés 
# jelenti a szöveges állományt, lásd file parancs) kitörli az utolsó sort,
# amennyiben a felhasználó ezt engedélyezi (tehát minden kiválasztott állományra
# előbb kérdezze meg a script, hogy törölhető-e az utolsó sor).
# Törlés esetén írjuk ki, hogy: "... állomány végéről kitöröltük az alábbi sort:
# ..."
# (ahol a "..."-ok helyére a megfelelő állománynév illetve sor kerül)
# Megj.: a billentyűzetről a "read" parancs segítségével lehet beolvasni.

if [ "$#" -eq 0 ]
then
  echo Hibas parameter lista
  echo "Hasznalat: $0 <directory>"
  exit 1
fi

for dir in $*
do
  if [ -d $dir ]
  then
    for allomany in `find $dir -type f`
    do
      if `file -b $allomany | grep -Eq text`
      then
        echo Talalt file: $allomany
        echo Torolheto-e az utolso sor? [y/n]
        read valasz
        if [ "$valasz" = "y" ]
        then
          echo $allomany állomány végéről kitöröltük az alábbi sort: `tail -1 $allomany`
          temp='allomany.tmp'
	  head -n -1 $allomany >$temp
          mv $temp $allomany
        fi
      fi
    done
  fi
done

