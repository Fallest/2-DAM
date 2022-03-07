<?php
    session_start();

    
    
     //si esta jugando e inicia otro juego
    if($_SESSION['jugando']==true)
    {
        //interrumpimos juegos redirigimos y preguntamos
        $_SESSION['juegoInterrumpido']=true;
        header("location:./index.php");
       
    }
    else{
        $_SESSION['palabraOriginal']="Casa1";
        $_SESSION['palabraAdivinar']="Casa2";
        $_SESSION['fallos']=0;
        $_SESSION['jugando']=true;
    
        

        header("location:./verDatos.php");
    }
?>
