# Proyecto Demo Hispana

Este proyecto es una aplicación de Spring Boot que gestiona información de clientes y sus vehículos, incluyendo detalles de marcas y modelos. La aplicación expone una API REST que permite realizar operaciones CRUD sobre los clientes y sus vehículos.

## Tecnologías Utilizadas

- **Java 21 o superior**
- **Spring Boot 3.3.2**
- **Spring Data JPA**
- **MySQL** como base de datos
- **Lombok** para reducir la cantidad de codigo repetido evitar escribir geters and seters
- **JUnit 5** para testing
- **Mockito** para testing con mocks

## Configuración del Proyecto
- **Pebe utilizar my sql como motor de base de datos al el script de la base de datos esta incluido se llama demohispana.sql**
- **En el archivo aplication.properties esta el nombre de la base a utilizar mas usuario y contraneña modificar valores de ser necesario**
- **Para provar el projecto debe acceder a la url: "http://localhost:8080/api/find?identificacion=1111" en postman ya que no tiene swagger**

### Prerrequisitos