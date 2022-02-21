<?php
    include 'conexionbd.php';
    session_start();
    if (isset($_GET['datosJugador'])) {
        echo($email);
        $email = $_GET['datosJugador'];

        $error = comprobar_sql_injection($email);

        if ($error == TRUE)
        {
            header("location:./index.php");
        }
        else
        {
            $con=conexion();
            $sql="select count(*) as total from USUARIOS where email='" . $email ."';";
            $resultado=mysqli_query($con, $sql);
            $datos=mysqli_fetch_assoc($resultado);
            $cantidad = $datos['total'];
            mysqli_close($con);

            if ($cantidad > 0)
            {
                $jug = obtener_datos_usuario($email);
                $_SESSION['nombre']=$jug['nombre'];
                $_SESSION['email']=$jug['email'];
                $_SESSION['apellido']=$jug['apellido'];
                $_SESSION['victorias']=$jug['IFNULL(ganadas,0)'];
                $_SESSION['perdidas']=$jug['IFNULL(perdidas,0)'];
                $_SESSION['jugando']=false;
                $_SESSION['juegoInterrumpido']=false;
                header("location:./testSesion.php");
            }
            else
            {
                header("location:./index.php");
            }            
        }

        
    }
    else
    {
        header("location:./index.php");
    }

                

    

    function obtener_datos_usuario($email)
	{
		$con=conexion();

		$sql="select email, nombre, fecha_nacimiento, apellido, IFNULL(ganadas,0), IFNULL(perdidas,0) from USUARIOS where email = '" . $email . "';";
		$resultado=mysqli_query($con, $sql);
        $datos=mysqli_fetch_assoc($resultado);
        
		mysqli_close($con);
		
		return $datos;
	}
    

    function comprobar_sql_injection($valor)
	{
		$error = FALSE;
		if (strpos($valor, "'") == TRUE) {
			$error = TRUE;
		}
		else if (strpos($valor, '"') == TRUE)
		{
			$error = TRUE;
		}
		else if (strpos($valor, ';') == TRUE)
		{
			$error = TRUE;
		}
		else if (strpos($valor, '<') == TRUE)
		{
			$error = TRUE;
		}
		else if (strpos($valor, '>') == TRUE)
		{
			$error = TRUE;
		}
		else if (strpos($valor, '/') == TRUE)
		{
			$error = TRUE;
		}
		else if (strpos($valor, '&') == TRUE)
		{
			$error = TRUE;
		}
		else if (strpos($valor, '--') == TRUE)
		{
			$error = TRUE;
		}
		else if (strpos($valor, '/*') == TRUE)
		{
			$error = TRUE;
		}
		else if (strpos($valor, '*/') == TRUE)
		{
			$error = TRUE;
		}
		return $error;
	}

?>
