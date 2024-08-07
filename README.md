# Proyecto de Obra Social - Academia Java + React + Quarkus


## Índice

1. [Introducción](#introducción)
2. [Integrantes del Proyecto](#integrantes-del-proyecto)
3. [Entidades creadas](#entidades-creadas)
4. [Backend](#backend)
    - [Requisitos](#requisitos)
    - [Tecnologias](#tecnologías)
    - [Dependencias utilizadas](#dependencias-utilizadas)
    - [Estructura del Proyecto](#estructura-del-proyecto)
    - [Base de datos H2](#base-de-datos)
    - [Implementación de los servicios](#implementación-de-los-servicios)
    - [Instrucciones para Ejecutar el Proyecto](#instrucciones-para-ejecutar-el-proyecto)
    - [Implementación del Swagger](#implementación-del-swagger)

5. [Frontend](#frontend)
    - [Tecnologías](#tecnologías-1)
    - [Dependencias Utilizadas](#dependencias-utilizadas-1)
    - [Estructura del Proyecto](#estructura-del-proyecto-1)
    - [Instalación y Ejecución del Proyecto](#instalación-y-ejecución-del-proyecto)
    - [Funcionalidades](#funcionalidades)
6. [Terminamos](#terminamos)

## Introducción
Somos el grupo 9 y este es el trabajo práctico integrador de la academia Java + React 2024 de UMSA con Softtek y hemos creado este proyecto modelando una obra social. Esta obra social tiene afiliados, especialistas, turnos y recetas medicas, permitiendo que un afiliado pueda sacar un turno relacionado a un especialista específico. Se ha creado una API la cual está documentada utilizando Swagger y se proporciona una colección de Postman para facilitar las pruebas. También se proporciona el correspondiente frontend para la utilización de la misma.


## Integrantes del Proyecto

- **Javier Kuznik**
- **Nicolas Torres**
- **Joaquin Muñoz**

## Entidades creadas

- **Afiliados**
- **Especialistas**
- **Turnos**
- **Receta medica**
- **Ubicaciones del especialista**
- **Horarios**

![Entidades](docs/Diagrama.png)

# Backend

## Requisitos

- **Java 17 o superior**
- **Tener Maven instalado**
- **Terminal para correr el proyecto**
- **Postman o navegador para probar la API**

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

## Base de datos

Estamos usando la base de datos H2 e importamos los datos a través de un archivo llamado *import.sql*. La elección de esta base de datos fue el hecho de poder lanzar el proyecto sin la necesidad de tener mas programas instalados.

![import](docs/importSQL.png)

## Implementación de los servicios

Los servicios están diseñados con interfaces e implementados para permitir una mayor flexibilidad y modularidad.

![Estructura services](docs/estructuraServices.png)




## Instrucciones para Ejecutar el Proyecto

1. **Primero hay que descargar el repositorio, para ello usamos el comando con la url del proyecto.**

   ```bash
   git clone https://github.com/nicolasjitorres/UMSA_grupo_9/tree/main
   ```
2. **Usar el comando en la linea de comandos para iniciar el proyecto, en este caso para powershell es**

    ```bash
    ./mvn compile quarkus:dev
    ```
3. **¡Listo para probar, no se necesita nada más!**

- **Opción 1:**
  Abre el [archivo de la colección](https://github.com/nicolasjitorres/UMSA_grupo_9/blob/develop/BackendSofftek/obrasocial.postman_collection.json) que se encuentra subido en el repositorio para hacer las pruebas desde la herramienta POSTMAN.

- **Opción 2:**
  Ve a la dirección [http://localhost:8080/swagger-ui/index.html#/](http://localhost:8080/swagger-ui/index.html#/) y prueba desde la interfaz de Swagger, que está documentada con cada endpoint respectivo (declarando las posibles respuestas que puede dar).

## Implementación del Swagger

Al correr el proyecto podemos ingresar a esta web de swagger de forma local, la cual consta de la documentación de cada metodo e información de los integrantes del proyecto.

![Inicio Swagger](docs/Swagger%20inicio.png)

Cada entidad del sistema cuenta con su respectiva documentación de cada método y de como usarse.

![Metodos Swagger](docs/Swagger%20metodos.png)


Dentro de Swagger, podemos probar cada método de la API. Dentro de cada método, encontrarás información relevante que debes tener en cuenta. Esto puede incluir detalles sobre los parámetros de entrada, el formato de los datos esperados, ejemplos de solicitudes y respuestas, y cualquier otra información útil para interactuar con la API de manera efectiva.


![Status](docs/Swagger%20status.png)

Cada método devuelve un código de estado HTTP diferente según sea necesario. En nuestro caso, optamos por trabajar con los siguientes códigos:

- **200**: Se ha traído o utilizado este objeto correctamente.
- **204**: Para cuando hacemos un GET pero no hay datos en el sistema.
- **400**: Error debido a alguna validación.
- **404**: Se ha proporcionado una ID de alguna entidad que no existe en el sistema.

# Frontend

El frontend de este proyecto ha sido desarrollado utilizando React y TypeScript, proporcionando una interfaz de usuario moderna y fácil de usar para interactuar con la API de la obra social.

### Tecnologías

- **React**: Biblioteca de JavaScript para construir interfaces de usuario.
- **TypeScript**: Lenguaje de programación que se basa en JavaScript y añade tipos estáticos.
- **Material-UI**: Biblioteca de componentes de interfaz de usuario para React.
- **Redux**: Biblioteca para el manejo del estado global de la aplicación.
- **Vite**: Herramienta de construcción rápida para proyectos de frontend.
- **Axios**: Para hacer peticiones HTTP a la API.


### Dependencias Utilizadas

- **redux-toolkit**: Herramientas y utilidades para simplificar el uso de Redux.
- **date-fns**: Librería moderna para manejo de fechas en JavaScript. Ofrece funciones para el parseo, manipulación y formateo de fechas, así como para trabajar con zonas horarias.
- **jspdf**: Biblioteca JavaScript para generar documentos PDF de manera dinámica en el navegador. Permite la creación de documentos PDF con texto, imágenes y gráficos generados desde datos en formato JSON.
- **sweetalert2**: Biblioteca para mostrar ventanas modales de alerta, confirmación y entrada de forma elegante y personalizable en aplicaciones web. Ofrece una experiencia de usuario mejorada en comparación con los diálogos estándar del navegador.
- **yup**: Para validación de formularios.
- **material-ui/icons**: Iconos para la interfaz de usuario.

### Estructura del Proyecto

El proyecto sigue una estructura organizada por componentes y características, facilitando su mantenimiento y escalabilidad:

- **components**: Componentes reutilizables en la aplicación.
- **pages**: Páginas principales de la aplicación.
- **redux**: Configuración y slices de Redux.
- **routes**: Definición de rutas de la aplicación.
- **services**: Servicios para interactuar con la API.
- **hooks**: Custom hooks para lógica reutilizable.

### Instalación y Ejecución del Proyecto

Para instalar y ejecutar el frontend del proyecto, sigue estos pasos:


1. **Navegar al directorio del frontend:**

    ```bash
    cd frontend
    ```

2. **Instalar las dependencias:**

    ```bash
    npm install
    ```

3. **Iniciar la aplicación:**

    ```bash
    npm run dev
    ```

### Funcionalidades

- **Gestión de Afiliados**: Permite la visualización, creación, edición y eliminación de afiliados. En el listado de afiliados se muestra información relevante como nombre, apellido, DNI y correo electrónico.

- **Gestión de Especialistas**: Permite la visualización, creación, edición y eliminación de especialistas.


- **Gestión de Horarios**: Cada especialista tiene un horario de atención, el cual se puede gestionar en este apartado, indicando el día y el horario de trabajo.


- **Gestión de Turnos**: Permite la creación, modificación, eliminación y visualización de turnos, junto a la posibilidad de gestionar la respectiva receta del mismo. En caso de que el turno no tenga receta, se podrá agregar una nueva. Si ya tiene una receta, se puede gestionar o descargar.

  Al seleccionar el botón para editar el turno, se podrá cambiar al especialista, el dia y el horario según la disponibilidad.


- **Gestión de Recetas Médicas**: Permite la creación, modificación, eliminación y visualización de recetas médicas junto a su posible descarga.

- **Inicio de sesión**: Permite a los usuarios autenticarse en el sistema. Momentáneamente, el login solo está hecho visualmente sin lógica, para el uso de un administrador pero la idea es dejarlo preparado para incorporar usuarios y permisos (JWT de fondo).

  ![Login](docs/login.png)

  *Pantalla de Inicio de Sesión*

- **Pantalla principal (Home)**: Después de iniciar sesión, los usuarios son redirigidos al Home, donde se muestra información básica de la web.

  ![home](docs/home.png)

  *Home*

- **Modales informativos**: La página principal incluye dos modales con información adicional.

  ![servicios](docs/servicios.png)

  *Servicios ofrecidos por la Obra Social*

  ![contacto](docs/contacto.png)

  *Contacto via mail de la obra social*

  Si la web no tiene datos cargados se mostrará de la siguiente forma, informando que no hay datos en sistema.

  ![tablas](docs/tablasVacias.png)

  *Tablas de datos*

  En caso de haber datos, se mostrará la web de la siguiente manera, en este caso tenemos la base de datos cargada por lo cual al iniciar el backend, se mostrará de la siguiente manera:

  ![tablas](docs/tablas.png)

  *Tablas de datos cargada*

**Filtros**

Todas las tablas tienen filtros para una busqueda más efectiva de los datos


**Validaciones y mensajes de error**

  Los campos tienen las validaciones correspondientes, y en caso de errores, como intentar ingresar campos vacíos, se muestra una notificación adecuada.

  
  ![agregar-afiliado](docs/agregar-afiliado.png)

  No se podrá borrar un afiliado en caso de tener un turno asociado, para ello primero se deberá de borrar el turno.

  ![mensaje-error](docs/alertaBorrar.png)

**Receta**

Para la receta de un turno se da la posibilidad de cambiar sus datos, borrar y descargar la misma en formato PDF.

![receta](docs/receta.png)





## Terminamos

Cualquier duda o recomendación que quieran a hacer pueden hacerla y responderemos a la brevedad.

¡Gracias por visitar nuestro proyecto FullStack de la Obra Social Almedin!


![LogoSofttekUmsa](docs/logoUMSA.png)

