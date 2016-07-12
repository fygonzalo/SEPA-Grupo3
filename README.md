# SEPA - Grupo 3

El sistema SEPA es una aplicacion web con un backend en Java y frontend en HTML5, CSS3 y JS, con posible expansion a dispositivos moviles mediante aplicaciones nativas, que permitira a los ciudadanos argentinos visualizar los precios de los supermercados, los cuales seran informados por los mismos diariamente. En caso de que estos precios no sean respetados los ciudadanos podran realizar denuncias a través del sistema, las cuales serán informadas al administrador general del sistema.

### Integrantes
- Bernaola, Martin
- Fernandez Yaique, Gonzalo
- Perez, Pablo
- Tolosa, Lucas


## Proceso de configuracion
El servidor de aplicaciones utilizado durante el desarrollo fue Payara Server 4.1.1.162 (Glassfish). Para implementar JPA se uso Hibernate 4.3.11. La BD sobre Mysql 5.1.
Para utilizar la aplicacion sobre Payara es necesario:
### Conexion a la BD
1. Crear un pool de conexion a la BD:
Primero se debe copiar el conector JDBC, en este caso de Mysql, a la carpeta 			payara41\glassfish\domains\domain1\lib.
2. Ingresar a la consola de administracion de Payara e ir a Resources/JDBC/JDBC Connection Pools y agregar un nuevo pool con los datos de conexion a la BD.
3. Crear un JDBC Resource bajo el nombre de jdbc/SEPA que utilice el pool recien creado.

### Definicion de los parametros de seguridad
1. En la consola de Payara ir a Configurations/server-config/Security y activar el checkbox "Default Principal To Role Mapping"
2. Despues entrar a Realms y crear uno nuevo con el JAAS Context: "jdbcRealm", JNDI: "jdbc/SEPA", user table: "usuario", user name column: "username", password column: "password", group table: "rolusuario", group name column: "rol", password encryption algorithm: "none" (o algun algoritmo hash), y definir los datos de conexion a la BD en database user y database password.

### Configuracion de JavaMail Sessions
1. En la consola ir a Resources/JavaMail Sessions y crear una nueva bajo el nombre de mail/SEPAEmail.
2. Para utilizar el correo creado para el desarrollo configurar el mail host: "smtp.gmail.com", default user: "sepagrupo3@gmail.com", default sender address: "sepagrupo3@gmail.com" y agregar 4 parametros adicionales, mail.smtp.ssl.enable: "true", mail.smtp.port: "465", mail.smtp.auth: "true", mail.smtp.password: "sepatrabajo3"

### Importante
Se debe crear una cuenta para el administrador de forma manual.  
> USE [Database]  
INSERT INTO usuario (username, password, email) VALUES ("[Usuario]", "[Contraseña]", "[Correo]")  
INSERT INTO rolusuario(username, rol) VALUES ("[Usuario]", "ADMINISTRADOR");

