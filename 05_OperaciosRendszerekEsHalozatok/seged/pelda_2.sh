# Írjon shell script-et, amely paraméterként egy állománynévből
# és egy k számból álló párokat kap. Minden ilyen párra írja ki az állomány 
# nevét, a k számot és
#   -  amennyiben az állománynak több, mint k sora van, az állomány
#      első k sorát
#   -  amennyiben az állománynak kevesebb, mint k sora van, akkor
#      minden sorból az első k karaktert írjuk ki
# Minden állomány listázása esetén írjuk ki előbb, hogy mit csinálunk,
# és a rendszeridőt is ("első <k> sor listázása (<óra:perc:mp>)" vagy 
# "soronként az első <k> karakter listázása (óra:perc:mp)")

if [ "$#" -lt 2 ]
then
  echo "Parameterek megadas kotelezo"
  echo "$0 <allomany> <szam>"
  exit 1
fi

if [ `expr $# % 2` -eq 1 ]
then
  echo "Parameterek szama paros kell legyen"
  echo "$0 <allomany> <szam>"
  exit 2
fi

while [ "$#" -gt 0 ]
do
  allomany=$1
  k=$2
  idopont=`date +"%H%t%M%n%S"`
  if [ `cat $allomany | wc -l;` -gt "$k" ]
  then
    echo "elso $k sor listazasa ($idopont)"
    head -n $k $allomany
  else
    echo "soronkent az elso $k karakter listazasa ($idopont)"
    cut -c1-$k $allomany  
  fi
  echo
  shift
  shift
done

exit 0