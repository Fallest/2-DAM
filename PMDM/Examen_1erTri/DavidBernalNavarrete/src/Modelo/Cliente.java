package Modelo;

/**
 * Clase para los clientes.
 */
public class Cliente {
    /*
        codigo INTEGER,
	nombre VARCHAR(30),
	contrasena VARCHAR(30),
	ultimoAcceso TIMESTAMP,
        foto VARCHAR(20),
	PRIMARY KEY (codigo)
    */
    // Atributos
    private int cod;
    private String nombre;
    private String pwd;
    private String foto;
    
    // Constructor
    public Cliente(int c, String n, String p, String f) {
        setCod(c);
        setNombre(n);
        setPwd(p);
        setFoto(f);
    }
    
    // Getters
    public int getCod() {
        return this.cod;
    }

    public String getNombre() {
        return this.nombre;
    }

    public String getPwd() {
        return this.pwd;
    }

    public String getFoto() {
        return this.foto;
    }
    
    
    // Setters
    public void setCod(int c) {
        this.cod = c;
    }

    public void setNombre(String n) {
        this.nombre = n;
    }

    public void setPwd(String p) {
        this.pwd = p;
    }

    public void setFoto(String f) {
        this.foto = f;
    }
    // ToString

    @Override
    public String toString() {
        return  this.cod + ";" + 
                this.nombre + ";" + 
                this.pwd + ";" + 
                this.foto; 
    }
}
