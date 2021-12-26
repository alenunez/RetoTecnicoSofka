package src;
public class Categoria {
    private String nombre;
    private int nivel;
    comandosSql comando = new comandosSql();
    public Categoria(String nombre, int nivel) {
        this.nombre = nombre;
        this.nivel = nivel;
        comando.crarRegistro("INSERT INTO categoria (nombre,nivel) VALUES ("+ "'"+nombre+ "'"+"," +  nivel  +");");
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public int getNivel() {
        return nivel;
    }
    public void setNivel(int nivel) {
        this.nivel = nivel;
    }
    
}
