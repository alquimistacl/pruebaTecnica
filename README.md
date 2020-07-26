# pruebaTecnica


### Tecnologias y librerias utilizadas ###

Para esta implementacion, se utilizo: 
* java 8
* maven
* junit
* mockito
* H2 db
* swagger

### Detalles de compilaci�n y ejecuci�n ###

Para compilar esta aplicacion, es necesario ejecutar en el directorio raiz de la misma, la
siguiente instruccion:

```.\mvnw.cmd clean compile package```

Para ejecutar la aplicacion, es necesario ejecutar en el directorio raiz de la misma, la siguiente instruccion

```.\mvnw.cmd spring-boot:run```

### Credenciales ###

Credenciales para generar un json web token(jwt), con el que se podrá acceder al resto de los endpoints
```
{
  "name": "usuario",
  "pass": "palabrasecreta"
}
```



### Rutas ###
Para acceder a la documentacion swagger, una vez que este corriendo la aplicacion, en un navegador abrir la siguiente ruta:
```http://localhost:8080/swagger```

Para acceder a la consola H2
```http://localhost:8080/h2-console```

