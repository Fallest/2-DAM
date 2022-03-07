<?php  
include 'conexionbd.php';
session_start();
if (isset($_SESSION['juegoInterrumpido']))
{
	if($_SESSION['juegoInterrumpido']==true)
	{
		$_SESSION['juegoInterrumpido']=false;
		echo "<script>
				var peticion=confirm('Quieres comenzar otro juego? Habrás perdido');
				console.log(peticion);
				if(peticion){
					console.log(peticion);
					document.location='reiniciarJuego.php'   }     
			
			</script>";
	}
	$con=conexion();
	$sql="UPDATE USUARIOS SET GANADAS=" . $_SESSION['victorias'] .", PERDIDAS=" . $_SESSION['perdidas'] . " WHERE EMAIL='" . $_SESSION['email'] . "';";
	$resultado=mysqli_query($con, $sql);
	mysqli_close($con);
}
else
{
	session_destroy();
	header("location:./index.php");
}


?>
<html>
	<head>
		<meta charset="UTF-8"/>
		<meta name="viewport" content="width=device-width, initial-scale=1.0"/>
		<link href="https://fonts.googleapis.com/css?family=Bebas+Neue&display=swap" rel="stylesheet">
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <title>Inicio</title>
	</head>
	<body>
		<div class="caja_usuario">
			<div class="datos_usuario">
				<h1><?php echo ' ' .$_SESSION['nombre'] . ' ' . $_SESSION['apellido'];?></h1> 
			</div>
			<div class="puntuacion_usuario">
				<p class="victorias">Victorias: <?php echo $_SESSION['victorias'];?> </p>
				<br/>
				<p class="derrotas">Derrotas: <?php echo $_SESSION['perdidas'];?> </p>
			</div>
		</div>					
	</body>
</html> 
