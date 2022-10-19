
#mariadbpath=$(pwd)/mariadbdata
mariadbpath=/media/jonas/USBMAIN2TB/diverse/mariadb

mkdir -p $mariadbpath


docker run --network=host -d --rm -v ${mariadbpath}:/var/lib/mysql   --env MARIADB_ALLOW_EMPTY_ROOT_PASSWORD=true  mariadb

