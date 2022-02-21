<?php

	include 'conexionbd.php';

	$error = FALSE;
	$nombre = 'class="entrada_datos"';
	$apellidos = 'class="entrada_datos"';
	$email = 'class="entrada_datos"';
	$fecha = 'class="entrada_datos"';

	if (isset($_POST['comprobacion'])) {
    
		if (isset($_POST['nombre'])) {
			if (comprobar_introduccion_string($_POST['nombre']) == TRUE)
			{
				$error = TRUE;
				$nombre = 'class="entrada_datos error"';
			}
		}
		else
		{
			$error = TRUE;
		}

		if (isset($_POST['apellidos'])) {
			if (comprobar_introduccion_string_vacio($_POST['apellidos']) == TRUE)
			{
				$error = TRUE;
				$apellidos = 'class="entrada_datos error"';
			}
		}
		
		if (isset($_POST['fecha'])) {
			if (comprobar_introduccion_fecha($_POST['fecha']) == TRUE)
			{
				$error = TRUE;
				$fecha = 'class="entrada_datos error"';
			}
		}
		else
		{
			$error = TRUE;
		}


		if ($error == FALSE)
		{
			insertar_usuario($_POST['email'], $_POST['nombre'], $_POST['fecha'], $_POST['apellidos']);
			header("location:./index.php");
		}
	}

    function comprobar_introduccion_string($campo)
    {
		$error = comprobar_sql_injection($campo);
		if ($error == FALSE)
		{
			if (strlen($campo) > 100)
			{
				$error = TRUE;
			}
			else if ($campo == "")
			{
				$error = TRUE;
			}
		}
        return $error;
	}
	
	function comprobar_introduccion_string_vacio($campo)
    {
		$error = comprobar_sql_injection($campo);
		if ($error == FALSE)
		{
			if (strlen($campo) > 100)
			{
				$error = TRUE;
			}
		}
        return $error;
    }

    
    function comprobar_introduccion_fecha($campo)
    {
		$error = comprobar_sql_injection($campo);
		if ($error == FALSE)
		{
			if ($campo == "")
			{
				$error = TRUE;
			}
			else
			{
				$segundos_fecha=strtotime($campo);
				$segundos_fecha_actual=time();
				if ($segundos_fecha_actual - $segundos_fecha <= 0)
				{
					echo("error en la fecha");
					$error = TRUE;
				}
			}
		}
        return $error;
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

	function mostrar_campo($nombre)
	{
		global $error;
		if ($error == TRUE)
		{
			echo('"' . $_POST[$nombre] . '"');
		}
		else
		{	
			echo('""');
		}
	}
	
	
	function insertar_usuario($email, $nombre, $fecha, $apellido)
	{
		$con=conexion();
		$sql='INSERT INTO USUARIOS VALUES ("' . $email . '", "' . $nombre . '", "' . $fecha . '", "' . $apellido . '", NULL, NULL);';
		$resultado=mysqli_multi_query($con, $sql);
	}

?>

<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="./css/alta.css" type="text/css"/>
        <link href="https://fonts.googleapis.com/css?family=Bebas+Neue&display=swap" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Solway&display=swap" rel="stylesheet">
        <title>Alta</title>
    </head>

    <body>
        <form action="./alta.php" method="post">
			<div>
				<fieldset id="formulario_alta">
					<legend id="hola">Nuevo jugador</legend>
					<table>
						<tr>
							<td>
								<p>Nombre</p>
								<input id="nombre" type="text" name="nombre" <?php echo($nombre); ?> value=<?php mostrar_campo('nombre'); ?>/>
							</td>
							<td>
								<p>Apellidos</p>
								<input id="apellidos" type="text" name="apellidos" <?php echo($apellidos); ?> value=<?php mostrar_campo('apellidos'); ?>/>
							</td>
						</tr>
						<tr>
							<td>
								<p>Email</p>
								<input id="email" type="text" name="email" <?php echo($email); ?> value=<?php mostrar_campo('email'); ?>/>
							</td>
							<td>
								<p>Fecha de Nacimiento</p>
								<input id="fecha" type="date" name="fecha" <?php echo($fecha); ?> value=<?php mostrar_campo('fecha'); ?>/>
							</td>
						</tr>
					</table>
					<div id="caja_boton">
						<input id="enviar" type="submit" value="Crear">
					</div>
				</fieldset>
			</div>
			<input id="comprobacion" type="hidden" name="comprobacion" value="ok" />
        </form>
    </body>

</html>
