
docker build -t app .

Para iniciar el aplicativo, corra el siguiente comando de docker para tener la base de datos:
docker run -d -p 8090:3306 -e MYSQL_ROOT_PASSWORD=cualquiera -e MYSQL_DATABASE=yms -e MYSQL_USER=yms_user -e MYSQL_PASSWORD=yms_clave mysql