<?php

	function conexion()
	{
		$con=mysqli_connect('mysql','usuario', '1234', 'db');
		return $con;
	}	
	
	

?>
