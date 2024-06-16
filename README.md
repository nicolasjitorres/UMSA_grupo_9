# Proyecto de Obra Social - API con Java + Quarkus

Somos el grupo 9 de la academia Java + React 2024 de UMSA con Softtek y hemos creado este proyecto modelando una obra social. Esta obra social tiene pacientes, especialistas y turnos y recetas medicas, permitiendo que un paciente pueda sacar un turno relacionado a un especialista específico. La API está documentada utilizando Swagger y se proporciona una colección de Postman para facilitar las pruebas. Esta es la documentación correspondiente al backend; pronto incorporaremos el frontend con su apartado correspondiente.


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

### Dependencias utilizadas
- **Swagger**: Para la documentación de cada método implementado en la API.
- **Postman**: Para pruebas de la API mediante una URL y body con los datos correspondientes.
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

## Prerrequisitos

- **Java 17 o superior**
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

![Inicio Swagger](https://imgur.com/E9L4GOZ)

Cada entidad del sistema cuenta con su respectiva documentación de cada método y de como usarse.

![Metodos Swagger](https://imgur.com/a/hZ4ugnq)

Dentro de Swagger, podemos probar cada método de la API. Dentro de cada método, encontrarás información relevante que debes tener en cuenta. Esto puede incluir detalles sobre los parámetros de entrada, el formato de los datos esperados, ejemplos de solicitudes y respuestas, y cualquier otra información útil para interactuar con la API de manera efectiva.


![Status](https://imgur.com/4J3R4TG)

Cada método devuelve un código de estado HTTP diferente según sea necesario. En nuestro caso, optamos por trabajar con los siguientes códigos:

- **200**: Se ha traído o utilizado este objeto correctamente.
- **204**: Para cuando hacemos un GET pero no hay datos en el sistema.
- **400**: Error debido a alguna validación.
- **404**: Se ha proporcionado una ID de alguna entidad que no existe en el sistema.

## ¡Terminamos!

Cualquier duda o recomendación que quieran a hacer pueden hacerla y responderemos a la brevedad.

¡Gracias por visitar nuestra API de Obra Social, proximamente llega el frontend!
