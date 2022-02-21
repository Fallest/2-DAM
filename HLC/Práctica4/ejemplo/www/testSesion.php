<?php
	//Iniciar una nueva sesión o reanudar la existente    
	session_start();

    if($_SESSION['sesionJuego']==true)
    {
        $_SESSION['palabraOriginal']="Casa1";
        $_SESSION['palabraAdivinar']="Casa2";
        $_SESSION['fallos']=0;
		$_SESSION['masControl']=true;
		header("location:./verDatos.php");
    }
    else{
        $_SESSION['sesionJuego']=false;
	    header("location:./index.php");
    }
?>
