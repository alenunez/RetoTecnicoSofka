package src;
public class Ronda {    
    private int pregunta;
    private int premio;
    private int juego;
    private int categoria;
    private int respuesta;
    comandosSql comando = new comandosSql();

    public Ronda(int pregunta, int premio, int juego, int categoria,int respuesta) {
        this.pregunta = pregunta;
        this.premio = premio;
        this.juego = juego;
        this.categoria = categoria;
        this.respuesta=respuesta;
       comando.crarRegistro("INSERT INTO ronda (pregunta,premio,juego,categoria,respuesta) VALUES ("+pregunta+","+premio+","+juego+","+categoria+","+respuesta+");");

    }
    public Ronda(int juego,int categoria){
        this.juego = juego;
        this.categoria = categoria;

    }
    public Ronda(int juego){
        this.juego = juego;
        comando.crarRegistro("INSERT INTO ronda (juego) VALUES ("+juego  +");");

    }

    public int getPregunta() {
        return pregunta;
    }
    public void setPregunta(int pregunta) {
        this.pregunta = pregunta;
    }
    public int getPremio() {
        return premio;
    }
    public void setPremio(int premio) {
        this.premio = premio;
    }
    public int getJuego() {
        return juego;
    }
    public void setJuego(int juego) {
        this.juego = juego;
    }
    public int getCategoria() {
        return categoria;
    }
    public void setCategoria(int categoria) {
        this.categoria = categoria;
    }
    public int getRespuesta() {
        return respuesta;
    }
    public void setRespuesta(int respuesta) {
        this.respuesta = respuesta;
    }
    
    
}
