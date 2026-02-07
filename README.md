# springboot4security7labv1
Laboratorio de un api asegurada con Spring boot 4.1.0 y security 7.0.2
Software requerido:

1.-Java JDK preferentemente la version 21, puedes descargar java de la pagina de oracle.com,	https://download.oracle.com/java/21/latest/jdk-21_windows-x64_bin.msi (sha256)
y seguir las instrucciones de instalacion, no incluyo este paso pues asumo que eres un java developer y sabes como instalar y configurar java en tu sistema operativo ;-)

2.-Oracle Express 21c, nuevamente el sitio de oracle.com es un buen lugar de inicio, si tienes windows tal vez la liga que necesitas se parezca a esta: https://download.oracle.com/otn-pub/otn_software/db-express/OracleXE213_Win64.zip
Te recomiendo sigas esta guia de instalacion y desinstales previas versiones de oracle en caso de tener alguna, el instalador de la version anterior es un buen punto de partida, si no aplica sigue esta liga https://docs.oracle.com/en/database/oracle/oracle-database/21/xeinw/index.html para una instalacion de primera vez.
Si es la primera vez que instalas oracle in tu equipo, te recomiendo sigas un video de instalacion como este: https://www.youtube.com/watch?v=puItszKAWNE
en youtube tambien encontraras videos de como crear una base de datos(conjunto de tablespaces que contienen los datos), sus tablespaces(agrupaciones logicas de datafiles),datafiles(archivos fisicos donde se guardan los datos) y los usuarios, roles de tu base de datos)
No te preocupes si hasta este punto no conoces mucho de la administracion de una base de datos o de su estructura fisica o logica, aqui te dejo unos queries que pueden ayudarte a gestionar tu base de datos y los puedes ejecutar en sqlplus o bien en alguna herramienta como sqldeveloper en mi caso.

--para crea la base de datos busca el directorio dbhomeXE/bin o dbhomeORCL/bin segun como se llame tu base de datos global definida por la instalacion de oracle
--ejecuta el script dbca si estas en windows doble click en caso linux/unix ./dbca y asegurate que el usuario tiene permiso de ejecucion sobre ese archivo
-- se abre una ventana en entorno grafico
-- en el paso operacion de base de datos seleccion crear una base de datos y click en el boton siguiente
--en el paso modo de creacion llena los campos de password y nombre de la base de datos conectable, guarda muy bien esos datos pues te serviran para conectarte a la base de datos
--click en el boton aceptar y luego en el boton terminar
--revisar que el archivo tnsnames.ora en oracle\homes\OraDB21Home2\network\admin tiene una configuracion similar a esta:
/*
tu_nombre_de_base_de_datos =
  (DESCRIPTION =
    (ADDRESS = (PROTOCOL = TCP)(HOST = nombre_de_tu_computadora)(PORT = 1521))
    (CONNECT_DATA =
      (SERVER = DEDICATED)
      (SERVICE_NAME = tu_nombre_de_base_de_datos)
    )
  )
*/

--Para levantar tu instancia de base de datos y conectarte con sqldeveloper
/*
abre cmd si estas en windows y ejecuta las siguientes lineas
set oracle_sid=nombre de tu base de datos creada
sqlplus sys/tu_password_administrativo$@tu_base_de_datos as sysdba
sql>alter database tu_base_de_datos open;

abre sqldeveloper y crea una nueva conexion: vista conexiones click en icono +
llena los campos con:
nombre de la conexion >> cualquier nombre pero sugiero el de la base de datos que creaste
usuario >> SYSTEM
contraseña >> la contraseña que le pusiste a tu base de datos
nombre del host >> localhost si estas en el mismo equipo que la base de datos o bien el nombre del equipo donde esta la base de datos o su IP
nombre del servicio >> nombre de tu base de datos
los demas campos dejalos con su valor por defecto a menos que se haya cambiado la configuracion por defecto al crear la base de datos como pueden ser el puerto o el SID
*/

--cuales tablespaces hay en tu base de datos y sepas donde vas a crear tus tablespaces 
select * from dba_tablespaces;

--donde estan los archivos que contienen los datos
--donde puedes crear tus carpetas para tus archivos de datos(tablespace)
select * from dba_data_files;

--previo crea tu carpeta para tu tablespace
create tablespace mi_tbspace 
datafile 'ruta de tus archivo e.g. D:\oracle\mi_tbspace\tempo\my_file1.dbf' size 3M, --nombre y tamaño de tu archivo tu lo defines en este caso son 3 megabytes, pero bien pueden ser 3 Gigabytes
datafile 'ruta de tus archivo e.g. D:\oracle\mi_tbspace\tempo\my_file2.dbf' size 3M; -- o dos o mas archivos si quieres, nuevamente es opcional y tu defines que es mejor para tu base de datos

--agregar tablas a tu tablespace
create table mytable (id integer, mensaje char(100)) tablespace mi_tbspace

--te equivocaste en tu definicion del tablespace o quieres mejorarla?
--borrala y creala de nuevo
drop tablespace mi_tbspace including content and datafiles;

--se lleno el datafile?
alter database 
datafile D:\oracle\mi_tbspace\tempo\my_file2.dbf'
resize 10M;

-- Abrir la vista de DBA
-- Menu >> ver >> DBA
-- Abrir la vista DBA para tu base de datos
-- + >> Conexion >> tu base de datos 
-- Abrir vista de archivo de datos para revisar visualmente tus datafiles
--vista DBBA >> tu base de datos >> Almacenamiento >>Archivos de datos

--crear usuarios
create user myuser identified by mypassword;

--revisar tus usuarios
select * from dba_users;

--darles el rol de dba para que puedan conectarse a la base de datos
grant dba to myuser;

alter user myuser account lock;
alter user myuser account unlock;

alter user myuser password expire;
alter user myuser identified by mypassword;

--rol =usuario asociado a tablespace
create user myuser2 identified su_password
default tablespace my_tablespace;
--le doy permisos para ingresar al tablespace
grant dba to myuser2;

--si te conectas con el usuario que creaste relacionado al tablespace puedes crear tablas y ya no necesitas especificar su tablespace
--por defecto se crean en el tablespace del usuario.

3.-SQL Developer, te recomiendo lo busques en oracle.com, https://www.oracle.com/database/sqldeveloper/technologies/download/
busca la opcion segun tu sistema operativo y descomprimelo en alguna ruta que sea de tu conveniencia, ejecutalo e indicale si no autodetecta tu version de java(antes de arrancar te pregunta la ruta de tu instalacion de java si no detecto la variable de ambiente de java JAVA_HOME)

4.-Tu IDE favorito, en mi caso yo utilice IntelliJ Community Edition.
5.- cliente para probar servicios rest favorito, en mi caso postman.

