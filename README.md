# Proyecto de Obra Social - API con Quarkus

Somos el grupo 9 de la academia Java + React 2024 de UMSA + Softtek y hemos creado este proyecto modelando una obra social. Esta obra social tiene pacientes, especialistas y turnos, permitiendo que un paciente pueda sacar un turno relacionado a un especialista específico. La API está documentada utilizando Swagger y se proporciona una colección de Postman para facilitar las pruebas. Esta es la documentación correspondiente al backend; pronto incorporaremos el frontend con su apartado correspondiente.


## Entidades creadas

- **Pacientes**
- **Especialistas**
- **Turnos**
- **Horarios**
- **Ubicaciones del especialista**
- **Receta medica**

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
    mvn quarkus:dev
    ```
3. **¡Listo para probar!**

- **Opción 1:**
  Abre el archivo que se encuentra en `UMSA_grupo_9/BackendSofftek/obrasocial.postman_collection` y prueba desde la herramienta POSTMAN.

- **Opción 2 (recomendada):**
  Ve a la dirección [http://localhost:8080/q/swagger-ui/](http://localhost:8080/q/swagger-ui/) y prueba desde la interfaz de Swagger, que está documentada con cada endpoint respectivo (declarando las posibles respuestas que puede dar).

## FOTOS DE SWAGGER


## ¡Terminamos!

Cualquier duda o recomendación que quieran a hacer pueden hacerla y responderemos a la brevedad.

¡Gracias por visitar nuestra API de Obra Social, proximamente llega el frontend!