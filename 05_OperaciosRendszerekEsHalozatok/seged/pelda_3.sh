# Írjon shell script-et, amely kiírja a rendszerbe bejelentkezett felhasználók
# teljes nevét és a munkaállomást, amelyről bejelentkeztek.
# Amennyiben a scripnek a "-v"-t is megadtuk paraméterként, írjuk ki azt is az
# egyes felhasználókra, hogy van-e vagy nincs honlapjuk (hozzáférhető-e mindenki
# számára az alapkatalógusukban egy public_html nevű katalógus).

users_ip=`who | sed -r 's/^([a-zA-Z0-9]+\b).*\((.*)\)$/\1 \2/'`
users=`echo "$users_ip" | sed -r 's/^([a-zA-Z0-9]+\b).*/\1/' | sort | uniq`

#echo "$users"

for user in $users
do
  fullname=`finger $user | head -1 | sed -r 's/.*: (.*)$/\1/'`
  echo "$users_ip" | grep $user | sed -r "s/^($user)\b/$fullname/"
  if [ "$1" = "-v" ]
  then
    home=`cat /etc/passwd | grep $user | sed -r 's/.*:(\/[a-zA-Z0-9\/]+):.*/\1/'`
    if [ -x "$home/public_html" ]
    then
      echo "$user: Van honlapja"
    else
      echo "$user: Nincs honlapja"
    fi
  fi
done