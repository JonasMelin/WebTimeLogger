
mariadbpath=$(pwd)/mariadbdata

mkdir -p $mariadbpath


docker run --network=host -d --rm -v ${mariadbpath}:/var/lib/mysql   --env MARIADB_ALLOW_EMPTY_ROOT_PASSWORD=true  mariadb

#mariadb --host 127.0.0.1 --port 3306 --user root
