# Proyecto de Obra Social - API con Java + Quarkus

Somos el grupo 9 y este es el trabajo práctico integrador de la academia Java + React 2024 de UMSA con Softtek y hemos creado este proyecto modelando una obra social. Esta obra social tiene pacientes, especialistas y turnos y recetas medicas, permitiendo que un paciente pueda sacar un turno relacionado a un especialista específico. La API está documentada utilizando Swagger y se proporciona una colección de Postman para facilitar las pruebas. Esta es la documentación correspondiente al backend; pronto incorporaremos el frontend con su apartado correspondiente.

## Integrantes del Proyecto

- **Javier Kuznik**
- **Nicolas Torres**
- **Joaquin Muñoz**


## Entidades creadas

- **Pacientes**
- **Especialistas**
- **Turnos**
- **Receta medica**
- **Ubicaciones del especialista**
- **Horarios**

## Tecnologías

- **Java 17** como lenguaje de programación.
- **Quarkus**: Framework para la creación y ejecución del proyecto.
- **Maven**: Para instalar las dependencias del proyecto.
- **Postman**: Para pruebas de la API mediante una URL y body con los datos correspondientes.

### Dependencias utilizadas
- **Swagger**: Para la documentación de cada método implementado en la API.
- **RESTEasy**: Para construir la API REST.
- **Lombok**: Para reducir el código repetitivo y mejorar la legibilidad.
- **Validation**: Para validaciones de datos de forma más eficiente.
- **H2**: Base de datos en memoria para una mayor velocidad a la hora de probar y correr el proyecto.

## Estructura del Proyecto

El proyecto sigue el patrón de diseño MVC (Modelo-Vista-Controlador) y está dividido en paquetes según su funcionalidad:

- **controller**: Controladores REST.
- **service**: Servicios con interfaces para especificar su funcionalidad y mantener un orden.
- **repository**: Repositorios para acceso a datos.
- **model**: Modelos de datos.
- **dto**: Objetos de Transferencia de Datos (DTOs).

## Requisitos

- **Java 17 o superior**
- **Tener Maven instalado**
- **Terminal para correr el proyecto**
- **Postman o navegador para probar la API**

## Instrucciones para Ejecutar el Proyecto

1. **Primero hay que descargar el repositorio, para ello usamos el comando con la url del proyecto.**

   ```bash
   git clone https://github.com/nicolasjitorres/UMSA_grupo_9/tree/main
   ```
2. **Usar el comando en la linea de comandos para iniciar el proyecto, en este caso para powershell es**

    ```bash
    ./mvn compile quarkus:dev
    ```
3. **¡Listo para probar!**

- **Opción 1:**
  Abre el [archivo de la colección](https://github.com/nicolasjitorres/UMSA_grupo_9/blob/develop/BackendSofftek/obrasocial.postman_collection.json) que se encuentra subido en el repositorio para hacer las pruebas desde la herramienta POSTMAN.

- **Opción 2 (recomendada):**
  Ve a la dirección [http://localhost:8080/swagger-ui/index.html#/](http://localhost:8080/swagger-ui/index.html#/) y prueba desde la interfaz de Swagger, que está documentada con cada endpoint respectivo (declarando las posibles respuestas que puede dar).

## Implementación del Swagger

Al correr el proyecto podemos ingresar a esta web de swagger de forma local, la cual consta de la documentación de cada metodo e información de los integrantes del proyecto.

![Inicio Swagger](https://private-user-images.githubusercontent.com/92613299/340129084-1583973f-49f3-40d2-ab3a-5e2cc02cd35a.png?jwt=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJnaXRodWIuY29tIiwiYXVkIjoicmF3LmdpdGh1YnVzZXJjb250ZW50LmNvbSIsImtleSI6ImtleTUiLCJleHAiOjE3MTg1NjUyMDUsIm5iZiI6MTcxODU2NDkwNSwicGF0aCI6Ii85MjYxMzI5OS8zNDAxMjkwODQtMTU4Mzk3M2YtNDlmMy00MGQyLWFiM2EtNWUyY2MwMmNkMzVhLnBuZz9YLUFtei1BbGdvcml0aG09QVdTNC1ITUFDLVNIQTI1NiZYLUFtei1DcmVkZW50aWFsPUFLSUFWQ09EWUxTQTUzUFFLNFpBJTJGMjAyNDA2MTYlMkZ1cy1lYXN0LTElMkZzMyUyRmF3czRfcmVxdWVzdCZYLUFtei1EYXRlPTIwMjQwNjE2VDE5MDgyNVomWC1BbXotRXhwaXJlcz0zMDAmWC1BbXotU2lnbmF0dXJlPTQwNjY2MWY3NmVjMjAwODY5YTBkNTlmZTQwNWFlOTdhMDEzOTZmNWZlODcyNjAxOTBjMzkzN2RhY2NlNTRjYzUmWC1BbXotU2lnbmVkSGVhZGVycz1ob3N0JmFjdG9yX2lkPTAma2V5X2lkPTAmcmVwb19pZD0wIn0.CtxZDsd4j2y0kvL9_-L4_wI0n9I03PsQfTUBE8G0Fbw)

Cada entidad del sistema cuenta con su respectiva documentación de cada método y de como usarse.

![Metodos Swagger](https://private-user-images.githubusercontent.com/92613299/340129080-cc1a417c-e4ae-4dc8-b589-061fa6442839.png?jwt=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJnaXRodWIuY29tIiwiYXVkIjoicmF3LmdpdGh1YnVzZXJjb250ZW50LmNvbSIsImtleSI6ImtleTUiLCJleHAiOjE3MTg1NjUyMDUsIm5iZiI6MTcxODU2NDkwNSwicGF0aCI6Ii85MjYxMzI5OS8zNDAxMjkwODAtY2MxYTQxN2MtZTRhZS00ZGM4LWI1ODktMDYxZmE2NDQyODM5LnBuZz9YLUFtei1BbGdvcml0aG09QVdTNC1ITUFDLVNIQTI1NiZYLUFtei1DcmVkZW50aWFsPUFLSUFWQ09EWUxTQTUzUFFLNFpBJTJGMjAyNDA2MTYlMkZ1cy1lYXN0LTElMkZzMyUyRmF3czRfcmVxdWVzdCZYLUFtei1EYXRlPTIwMjQwNjE2VDE5MDgyNVomWC1BbXotRXhwaXJlcz0zMDAmWC1BbXotU2lnbmF0dXJlPTFmMzM2ODU0YmZjMjQ0Yjg1Y2M0YzIzNDIzNDlkOTUwNDJmNzk4OGVmYzQ3NmU0Yzk5YzFjNDUyZWY4ZGZlODcmWC1BbXotU2lnbmVkSGVhZGVycz1ob3N0JmFjdG9yX2lkPTAma2V5X2lkPTAmcmVwb19pZD0wIn0.DIBNc1oVOvMcrdTOG0eKpO9MsobZKwPs6niDC4z9l7A)

Dentro de Swagger, podemos probar cada método de la API. Dentro de cada método, encontrarás información relevante que debes tener en cuenta. Esto puede incluir detalles sobre los parámetros de entrada, el formato de los datos esperados, ejemplos de solicitudes y respuestas, y cualquier otra información útil para interactuar con la API de manera efectiva.


![Status](https://private-user-images.githubusercontent.com/92613299/340129082-c1d2ce60-6b2b-4291-84c6-8e24ad01d00c.png?jwt=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJnaXRodWIuY29tIiwiYXVkIjoicmF3LmdpdGh1YnVzZXJjb250ZW50LmNvbSIsImtleSI6ImtleTUiLCJleHAiOjE3MTg1NjUyMDUsIm5iZiI6MTcxODU2NDkwNSwicGF0aCI6Ii85MjYxMzI5OS8zNDAxMjkwODItYzFkMmNlNjAtNmIyYi00MjkxLTg0YzYtOGUyNGFkMDFkMDBjLnBuZz9YLUFtei1BbGdvcml0aG09QVdTNC1ITUFDLVNIQTI1NiZYLUFtei1DcmVkZW50aWFsPUFLSUFWQ09EWUxTQTUzUFFLNFpBJTJGMjAyNDA2MTYlMkZ1cy1lYXN0LTElMkZzMyUyRmF3czRfcmVxdWVzdCZYLUFtei1EYXRlPTIwMjQwNjE2VDE5MDgyNVomWC1BbXotRXhwaXJlcz0zMDAmWC1BbXotU2lnbmF0dXJlPTkyYTA5Yjc2MDU0YzZmMzA5NmM2OTgzMDM1NjdjMTIxNzZkMGRlZDgyN2FiYjQ1Y2I3NjRjNWE0NTgzNTBkMjYmWC1BbXotU2lnbmVkSGVhZGVycz1ob3N0JmFjdG9yX2lkPTAma2V5X2lkPTAmcmVwb19pZD0wIn0.SxvMmFAV2rBi9y9dy8URggIb3hR2q84rZlPiFEZ84cI)

Cada método devuelve un código de estado HTTP diferente según sea necesario. En nuestro caso, optamos por trabajar con los siguientes códigos:

- **200**: Se ha traído o utilizado este objeto correctamente.
- **204**: Para cuando hacemos un GET pero no hay datos en el sistema.
- **400**: Error debido a alguna validación.
- **404**: Se ha proporcionado una ID de alguna entidad que no existe en el sistema.

## ¡Terminamos!

Cualquier duda o recomendación que quieran a hacer pueden hacerla y responderemos a la brevedad.

¡Gracias por visitar nuestra API de Obra Social, proximamente llega el frontend!

![LogoSofttekUmsa](https://private-user-images.githubusercontent.com/92613299/340140653-3f4c0edc-26ec-41dc-b4f5-26c594dccca7.png?jwt=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJnaXRodWIuY29tIiwiYXVkIjoicmF3LmdpdGh1YnVzZXJjb250ZW50LmNvbSIsImtleSI6ImtleTUiLCJleHAiOjE3MTg1NzgyMjIsIm5iZiI6MTcxODU3NzkyMiwicGF0aCI6Ii85MjYxMzI5OS8zNDAxNDA2NTMtM2Y0YzBlZGMtMjZlYy00MWRjLWI0ZjUtMjZjNTk0ZGNjY2E3LnBuZz9YLUFtei1BbGdvcml0aG09QVdTNC1ITUFDLVNIQTI1NiZYLUFtei1DcmVkZW50aWFsPUFLSUFWQ09EWUxTQTUzUFFLNFpBJTJGMjAyNDA2MTYlMkZ1cy1lYXN0LTElMkZzMyUyRmF3czRfcmVxdWVzdCZYLUFtei1EYXRlPTIwMjQwNjE2VDIyNDUyMlomWC1BbXotRXhwaXJlcz0zMDAmWC1BbXotU2lnbmF0dXJlPWU3YWEwMmJmMzYwNzM3MzA0ODQ1YTZjMmUwMGZmYWMwMmQ3MDRmZGM3NDhlYWRjOWE5ZjRlZjIyMjZiMzFiZWEmWC1BbXotU2lnbmVkSGVhZGVycz1ob3N0JmFjdG9yX2lkPTAma2V5X2lkPTAmcmVwb19pZD0wIn0.9IsoJLS3grazf485IeWRcB6KIYhP0Whr1XbfqLYi7fk)