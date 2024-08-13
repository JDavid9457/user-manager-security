**USER MANAGER SECURITY**



![Java Version](https://img.shields.io/badge/Java-17(LTS)-red?logo=java)
![Spring Boot](https://img.shields.io/badge/Spring&nbsp;Boot-3.3.2-success?logo=springboot)
![Gradle](https://img.shields.io/badge/Gradle-8.2.1-success?logo=Gradle)

## Soluccion del problema
De acuerdo con el problema,se abordó la construcción un servicio de backen, utilizando la tecnología Java y spring boot, para ello se implementó una arquitectura hexagonal adaptada a las necesidades del problema, obteniendo una aplicación para creación de usuario



## H2 Console
Este proyecto cuenta con una base de datos en H2, la cual permite guardar información en memoria mientras esté
el aplicativo en ejecución.


para interactuar ingresamos  al siguiente enlace:


```sh
http://localhost:8080/h2-console/
```
una vez aquí configuramos el ingreso, colocando  la url, usuario y contraseña
```sh
JDBC URL: jdbc:h2:mem:usertdb
User Name: sa
Password: sa
```
colocando estos datos nos permitirá el ingreso.
## Swagger
Este proyecto  tiene configurado un Swagger, el cual permite la ejecución de los endpoints disponibles,
para acceder ingresamos a la  siguiente ruta:

```sh
http://localhost:8080/swagger-ui/index.html
```
aqui nos permite interactuar de forma grafica con los endpoints

## Rutas
Estos enlaces nos permite interactuar con el aplicativo:


```sh
ingresar usuario: nos pedira correo y contraseña
http://localhost:8080/api-user-manager/auth/login

```

```sh
guardar usuario:
http://localhost:8080/api-user-manager/register/save
```

```sh
Actualizar: para actulizar el usuario, debemos enviar un parametro,
el cual es id por ejemplo: e1698970-7f90-4d31-bf9a-ff608b8650e8, lo colcamos 
donde va id.
http://localhost:8080/api-user-manager/register/update/{id}
```


```sh
Listar: Lista todo los contactos que esten almacenados
http://localhost:8080/api-user-manager/register/list
```

```sh
buscar por id: devuelve los datos del usuario
http://localhost:8080/api-user-manager/register/user/{id}
```
```sh
elemiar por id: elimina el usuario en base de datos
http://localhost:8080/api-user-manager/register/delete/{id}
```

## Respuesta principales del servicio:
Se ingresa el objeto, para guardarlo
```JSON
{
    "username": "ricardo Rodriguez",
    "email": "ricardo@riguez.org",
    "password": "hunter2A*",
    "phones": [
        {
            "number": "1234567",
            "cityCode": "1",
            "contryCode": "57"
        }
    ]
}
```
Cuando la respuesta es exitosa devuelve lo siguiente:
```JSON
{
    "data": {
        "id": "86a900f8-5ed8-4ace-b48c-543beb5197ac",
        "createdAt": "2024-07-24T07:04:37.0391699",
        "modifiedAt": "2024-07-24T07:04:37.0391699",
        "lastLogin": "2024-07-24T07:04:37.0391699",
        "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJyaWNhcmRvIFJvZHJpZ3VleiIsImlhdCI6MTcyMTgyMjY3NywiZXhwIjoxNzIxODI0MTE3fQ.NZn0xIjbViXEsnyHiza9FHHBjvLn9A_JBYEWcFx3kyg",
        "isactive": true
    },
    "status": 200,
    "message": "Exitoso"
}
```
tiene validaciones de contraseña y correo

```JSON
{
  "message": "Clave no cumple con el formato valido."
}
```

```JSON
{
  "message": "Email no cumple con el formato adecuado (aaaaaaa@dominio.cl)"
}
```
## Respuesta de login
```JSON
{

  "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJyaWNhcmRvIFJvZHJpZ3VleiIsImlhdCI6MTcyMTg0NTM4MywiZXhwIjoxNzIxODQ2ODIzfQ.qLMTUb_ttQ3tHczpI-r1IA4I018ZNUQhQEarPpu-Ncg"
}
```
cuando la respuesta es exitosa, devuelve el token: