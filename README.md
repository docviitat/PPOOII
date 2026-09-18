SCHEMA BASES DE DATOS PPOOII

sudo mysql -u root

CREATE DATABASE IF NOT EXISTS ppooii;
ALTER USER 'root'@'localhost' IDENTIFIED VIA mariadb_native_password USING PASSWORD('root');
GRANT ALL PRIVILEGES ON ppooii.* TO 'root'@'localhost';
FLUSH PRIVILEGES;

mysql -u root -proot ppooii / root