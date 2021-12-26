package src;

public class Ganador {
    comandosSql comando= new comandosSql();
    private int id;
    private int idJugador;
    public Ganador(int id, int idJugador) {
        this.id=id;
        this.idJugador=idJugador;
    }
    public Ganador(int idJugador){
        this.idJugador=idJugador;
        comando.crarRegistro("INSERT INTO ganador (jugador )VALUES ("+ idJugador +");");


    }
    public Ganador(){

    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getIdJugador() {
        return idJugador;
    }
    public void setIdJugador(int idJugador) {
        this.idJugador = idJugador;
    }
    
    
}
