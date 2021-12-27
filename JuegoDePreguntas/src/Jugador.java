package src;

import java.util.ArrayList;

public class Jugador {
    private int id;
    private String nombre;
    private String apellido;
    private double premio;
    private ArrayList<Jugador> listaDeJugadores= new ArrayList();
    comandosSql comando = new comandosSql();

    public Jugador(String nombre, String apellido) {
        this.nombre = nombre;
        this.apellido = apellido;
      comando.crarRegistro( "INSERT INTO jugador (nombre,apellido) VALUES ("+"'"+ nombre+"'" + "," + "'" + apellido + "'"  +");");
    }
    public Jugador(){
       comando.crarRegistro("INSERT INTO jugador (nombre,apellido) VALUES ("+"'"+ null+"'" + "," + "'" + null + "'"  +");");
    }
    public Jugador(int id){
        this.id=id;
    }
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre,int id) {
        this.nombre = nombre;
        comando.actualizar( "UPDATE jugador  SET Nombre = '" + nombre + "'WHERE idJugador = " + id +" ;");
    }
    public String getApellido() {
        return apellido;
    }
    public void setApellido(String apellido, int id) {
        this.apellido = apellido;
        comando.actualizar( "UPDATE jugador  SET apellido = '" + apellido + "'WHERE idJugador = " + id + ";");

    }
    public double getPremio() {
        return premio;
    }
    public void setPremio(double premio,int idJugador) {
        this.premio = premio;
        comando.actualizar( "UPDATE jugador  SET premio = '" + premio + "'WHERE idJugador = " + idJugador + ";");

    }
    //Este meotodo llama a la clase comandoSQL para hacer una lista de todos los jugadores de la base de datos
    public void listarJugadores(){
        listaDeJugadores= comando.listarJugadores();
    }
    //Este metodo devuelve el ultimo jugador que se creó en la base de datos ya que en la clase Juego primero se crea el jugador y
    // luego el juego y se nececita el id del jugador actual(Que seria el ultimo creado)
    public int obtenerUltimoJugador(){
        listarJugadores();
        int idUltimoJugador = listaDeJugadores.get(listaDeJugadores.size()-1).getId();
                    return idUltimoJugador;
    }
    

    
}
