
<html>
	<head>
		<meta charset="UTF-8"/>
		<link rel="stylesheet" href="./css/lista.css">
		<title>Lista</title>
	</head>
	<body>
		<main id="main">
			<?php

				
				include 'consultasTablaUsuarios.php';

				$jugadores=obtenerTodosUsuarios();

			
				foreach($jugadores as $jugador)
				{
					$segundosEdadUsuario=strtotime($jugador['fecha_nacimiento']);
					$segundosFechaActual=time();
					$edad=floor(($segundosFechaActual-$segundosEdadUsuario)/31536000);
					$envioJugador = $jugador['email'];
					
					
					echo '<article>';
					echo '<a class="enlace" href="iniciarSesion.php?datosJugador=' .$envioJugador. '">';
					echo '<table><tr><td class="td_imagen"><figure><img class="imagen" src="./imagenes/jugador.png"></figure></td></tr><tr><td><h1 class="titulo">';
					echo $jugador['nombre'] . ' ' . $jugador['apellido'] . ' ' . $edad . ' años';
					echo '</h1></td></tr></table></a></article>';
				
				}
					
				
				
					
			?>
		</main>
		<script>			
			function filtrar() {
				var input, filter, ul, h1, a, i, txtValue;
				input = document.getElementById("busqueda");
				filter = input.value.toUpperCase();
				a = document.getElementsByClassName("enlace");
				for (i = 0; i < a.length; i++) {
					h1 = a[i].getElementsByTagName("h1")[0];
					txtValue = h1.textContent || h1.innerText;
					if (txtValue.toUpperCase().indexOf(filter) > -1) {
						a[i].style.display = "";
					} else {
						a[i].style.display = "none";
					}
				}
			}
		</script>
	</body>
</html> 
