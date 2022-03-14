<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>DaviiExpress</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <!-- Título e introducción -->
        <div>
            <h1>DaviiExpress</h1>
            <p>
                Esta es una página web para que los clientes de una empresa de 
                envíos de paquetes puedan consultar datos sobre sus envíos.
            </p>
            <p>Autor: David Bernal Navarrete</p>
        </div>
        <!-- Inicio de sesión -->
        <form action="servlet1" method="POST">
            <h3>Inicio de sesión</h3>
            Usuario:<input type="text" name="userName"/><br/>
            Contraseña:<input type="password" name="pwd"/><br/>
            <input type="submit" value="go"/>
        </form>
        
    </body>
</html>

