# Proyecto Demo Hispana

Este es una demo de una api rest realizado en Spring Boot que gestiona informacion de clientes y sus vehiculos, incluyendo detalles de marcas y modelos. El proyecto tambien incluye testing

## Tecnologías Utilizadas

- **Java 21 o superior**
- **Spring Boot 3.3.2**
- **Spring Data JPA**
- **MySQL** como base de datos
- **Lombok** para reducir la cantidad de codigo repetido evitar escribir geters and seters
- **JUnit 5** para testing
- **Mockito** para testing con mocks
- **Swagger-ui2.6.0** para documentacion de la api

## Configuración del Proyecto
- **Pebe utilizar my sql como motor de base de datos al el script de la base de datos esta incluido se llama demohispana.sql**
- **En el archivo aplication.properties esta el nombre de la base a utilizar mas usuario y contraneña modificar valores de ser necesario**
- **Para provar el projecto debe acceder a la url: "http://localhost:8080/api/find?identificacion=1111" en postman**
- **Tambien puede accder a probar con swagger usando la siguiente ruta: "http://localhost:8080/swagger-ui/index.html#/cliente-controller/findClienteByIdentificacion" en el navegador**