package src;
import java.util.ArrayList;
import java.util.Random;
public class Pregunta {
    private int categoria;
    private String enunciado;
    private int id;
    ArrayList <Pregunta> listaPreguntas = new ArrayList<>();
    ArrayList <Pregunta> listaPreguntasPorCategoria = new ArrayList<>();



    comandosSql comando = new comandosSql();


    public Pregunta(int categoria, String enunciado) {
        this.categoria = categoria;
        this.enunciado = enunciado;
        comando.crarRegistro("INSERT INTO pregunta (categoria,enunciado) VALUES ("+ categoria+ "," + "'" + enunciado + "'"  +");");
    }
    public Pregunta(int id, int categoria,String enunciado){
        this.id = id;
        this.categoria=categoria;
        this.enunciado=enunciado;
    }
    public Pregunta(){

    }
    

    public int getId(int numero) {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getCategoria() {
        return categoria;
    }

    public void setCategoria(int categoria) {
        this.categoria = categoria;
    }

    public String getEnunciado() {
        return enunciado;
    }

    public void setEnunciado(String enunciado) {
        this.enunciado = enunciado;
    }
    public int generarAleatorio(){
        Random aleatorio = new Random();
        int idPregunta = aleatorio.nextInt(listaPreguntasPorCategoria.size());
        return idPregunta;
    }
    public int seleccionarPregunta(){
        int aleatorio = generarAleatorio();
        int idPregunta=0;
        for(int i=0;i< listaPreguntasPorCategoria.size();i++){
            if(i==aleatorio){
               idPregunta= listaPreguntasPorCategoria.get(i).getId(i);
            }
        }
        return idPregunta;
    }

    public int mostrarPregunta(int categoria){
        listarPreguntasPorCategoria(categoria);
        int idPregunta = seleccionarPregunta();
        comando.mostrarPreguntaId(idPregunta);
        listaPreguntasPorCategoria.clear();
        return idPregunta;     
    }
    

    public void listarPreguntasPorCategoria(int categoria){
        listarPreguntas();
        for(int i = 0;i<listaPreguntas.size();i++){
            if(listaPreguntas.get(i).getCategoria()==categoria){
                listaPreguntasPorCategoria.add(listaPreguntas.get(i));
            }
        }

        
    }
    public void listarPreguntas(){
        listaPreguntas = comando.preguntas();
    }

}
