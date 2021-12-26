package src;

import java.util.ArrayList;

public class Opcion {
    int idOpcion;
    private String enunciado;
    private int opcionCorrecta;
    private int pregunta;
    ArrayList<Opcion> listaOpciones = new ArrayList<>();
    ArrayList<Opcion> listaOpcionesPorPregunta = new ArrayList<>();

    comandosSql comando = new comandosSql();

    public Opcion(String enunciado, int opcionCorrecta, int pregunta) {
        this.enunciado = enunciado;
        this.opcionCorrecta = opcionCorrecta;
        this.pregunta = pregunta;
    }

    public Opcion(int id, String enunciado, int opcionCorrecta, int pregunta) {
        this.idOpcion = id;
        this.enunciado = enunciado;
        this.opcionCorrecta = opcionCorrecta;
        this.pregunta = pregunta;
    }

    public Opcion() {

    }    

    public String getEnunciado() {
        return enunciado;
    }

    public void setEnunciado(String enunciado) {
        this.enunciado = enunciado;
    }


    public int getOpcionCorrecta() {
        return opcionCorrecta;
    }

    public void setOpcionCorrecta(int opcionCorrecta) {
        this.opcionCorrecta = opcionCorrecta;
    }

    public int getPregunta() {
        return pregunta;
    }

    public void setPregunta(int pregunta) {
        this.pregunta = pregunta;
    }

    public int getIdOpcion() {
        return idOpcion;
    }

    public void setIdOpcion(int idOpcion) {
        this.idOpcion = idOpcion;
    }

    public void listarOpciones() {
        listaOpciones = comando.opciones();
    }

    public void seleccionarPorPregunta(int pregunta) {
        listarOpciones();
        for (int i = 0; i < listaOpciones.size(); i++) {
            if (listaOpciones.get(i).getPregunta() == pregunta) {
                listaOpcionesPorPregunta.add(listaOpciones.get(i));
            }
        }
        mostrarOpciones();
    }

    public void mostrarOpciones() {
        for (int i = 0; i < listaOpcionesPorPregunta.size(); i++) {
            System.out.println("Opcion N°" + (i + 1) + " "+listaOpcionesPorPregunta.get(i).getEnunciado());
        }
    }

    public boolean verificarRespuesta(int respuesta) {
        boolean opcion=false;
        for (int i = 0; i < listaOpcionesPorPregunta.size(); i++) {
            if (i+1 == respuesta) {
                idOpcion= listaOpcionesPorPregunta.get(i).getIdOpcion();
                if(listaOpcionesPorPregunta.get(i).getOpcionCorrecta()==1){
                    opcion=true;
                }
            }
        }
        listaOpcionesPorPregunta.clear();
        return opcion;
    }
    public void limpiarListaDeOpciones(){
        listaOpcionesPorPregunta.clear();
    }


}
