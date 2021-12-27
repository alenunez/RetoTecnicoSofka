package src;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Juego {
    ArrayList<Juego> listaDeJuegos = new ArrayList<>();

    int id;
    Scanner entrada = new Scanner(System.in);
    comandosSql comando = new comandosSql();
    Pregunta pregunta = new Pregunta();
    Opcion opcion = new Opcion();
    Premio premio = new Premio();
    private int idjugador;

    public Juego(int id, int jugador) {
        this.id = id;
        this.idjugador = jugador;
    }

    public Juego(int jugador) {
        this.idjugador = idjugador;
        comando.crarRegistro("INSERT INTO juego (jugador) VALUES (" + jugador + ");");
    }

    public Juego() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdjugador() {
        return idjugador;
    }

    public void setIdjugador(int idjugador) {
        this.idjugador = idjugador;
    }
    //Este metodo crea el juego
    public void iniciarJuego() {
        int idPremio = 0;
        boolean control = true;
        int contador = 1;
        Jugador jugador = new Jugador();
        boolean verRespuesta = false;
        jugar(idPremio, control, contador, jugador, verRespuesta);
    }
    //Este metodo recibe como parametros el id del premio, una variable booleana control para controlar el ciclo
    //while, el contador inicializado en uno para también controlar el ciclo, recibe un objeto de la clase Jugador, 
    //Y otra variable booleana verRespuesta que controla si la respuesta es correcta o no. 
    //Este metodo es mas largo ya que controla todo el juego,sin embargo llama a otros metodos para las funcionalidades
    // del juego. El contador representa cada nivel del juego o ronda.
    public void jugar(int idPremio, boolean control, int contador, Jugador jugador, boolean verRespuesta) {
        while (control && contador <= 5) {
            if (contador == 1) {
                primeraRonda(jugador); //Si estamos en el nivel 1 se llama el metodo primeraRonda
            }
            int pre = pregunta.mostrarPregunta(contador); //Guarda en la variable pre el id de la pregunta que selecciono al azar y adicionalmente muesta en pantalla el enunciado de la pregunta
            verRespuesta = verificarRespuesta(contador, idPremio, pre);//Llama el metod verificarRespuesta para mostrat las opciones y obtener true si es verdadero o false si no lo es
            if (verRespuesta == true) {
                if (contador == 5) {//Si la respuesta es correcta pero el contador(nivel) es el 5 llama al metodo respuesta correcta final
                    respuestaCorrectaFinal(jugador, contador);
                    control = false;
                    contador++;
                } else if (contador != 5) { //Si la respuesta es correcta pero no es el ultimo nivel
                    respuestaCorrecta(jugador, contador);
                    contador++;
                }
            } else { //Si la respuesta es incorrecta
                System.out.println("Juego finalizado. Perdiste todo tu dinero acumulado");
                jugador.setPremio(0, jugador.obtenerUltimoJugador());//Deja en cero al jugador.
                control = false;
            }
            if ((contador > 1 && contador <= 5) && control == true) { //Da la opcion de abandonar el juego o continuar
                control = salirOContinuar(control, jugador, contador);
            }
            Ronda ronda = new Ronda(pre, idPremio, obtenerUltimoJuego(), contador - 1, opcion.getIdOpcion());
        }
    }
    //Este metodo permite listar todos los juegos en el ArrayList listaDeJuegos.
    public void listarJuegos() {
        listaDeJuegos = comando.listarJuegos();
    }
    //Este metodo devuelve el id del ultimo juego creado.
    public int obtenerUltimoJuego() {
        listarJuegos();
        int idUltimoJuego = listaDeJuegos.get(listaDeJuegos.size() - 1).getId();
        return idUltimoJuego;
    }
    //Este metodo solo se ejecuta en la primera ronda o categoria(nivel) y guarda en la 
    //base de datos los datos del jugador y crea el juego con el jugador correspondiente.
    public void primeraRonda(Jugador jugador) {
        System.out.println("Ingrese el nombre del jugador");
        String nombre = entrada.nextLine();
        System.out.println("Ingrese el apellido del jugdor");
        String apellido = entrada.nextLine();
        jugador.setNombre(nombre, jugador.obtenerUltimoJugador());
        jugador.setApellido(apellido, jugador.obtenerUltimoJugador());
        Juego juego = new Juego(jugador.obtenerUltimoJugador());
    }
    //Este metodo se ejecuta si el jugador respondio correctamente la ultima pregunta y 
    //agrega al jugador a la tabla de ganadores en la base de datos
    public void respuestaCorrectaFinal(Jugador jugador, int contador) {
        jugador.setPremio(premio.getPremioDinero(contador) + jugador.getPremio(), jugador.obtenerUltimoJugador());
        System.out.println("Felicitaciones has ganado " + jugador.getPremio());
        Ganador ganador = new Ganador(jugador.obtenerUltimoJugador());
    }
    //Este metodo se ejecuta si el jugador responde correctamente cualquier pregunta que no sea la ultima y 
    //agrega el jugador el dinero ganado en esa ronda
    public void respuestaCorrecta(Jugador jugador, int contador) {
        System.out.println("Has pasado de ronda");
        jugador.setPremio(premio.getPremioDinero(contador) + jugador.getPremio(),
                jugador.obtenerUltimoJugador());
    }

    //Este metodo se ejecuta al seleccionar correctamente una respuesta que no sea a la ultima pregunta y
    //le da la oportunidad al jugador de decidir si sale del juego con el dinero ganado o continua.
    //Se utiliza un try catch para controlar que las entradas solo sean números enteros y se llama 
    //al metodo controlSalirContinual para verificar que solo selecciones 1 o 2.
    public boolean salirOContinuar(boolean control, Jugador jugador, int contador) {
        boolean controlEntero = true;
        int elegir=0;
        while (controlEntero ) {
            try {
                System.out.println("Su dinero acumulado es: " + jugador.getPremio() + " Pesos. ");
                System.out.println("¿Desea seguir el juego para ganar " + premio.getPremioDinero(contador)
                        + " Pesos. O abandonar con lo que ha ganado?\n1.-Continuar\n2.-Abandonar");
                elegir = entrada.nextInt();
                controlEntero = false;
            } catch (InputMismatchException e) {
                System.out.println("Solo puede ingresar enteros");
                entrada.next();                       
            }
        }
        elegir = controlSalirOContinuar(elegir);
        if (elegir == 2) {
            control = false;
            System.out.println("Juego finalizado. Te llevas " + jugador.getPremio() + " Pesos");
        }        
        return control;
    }
    //Este metod permite muestra las opciones por una pregunta y permite verificar si la respuesta
    // seleccionada por el jugador es correcta y devuelve true si es correcta o false si no lo es.
    // Recibimos como parametro el contador que nos sirve para saber en que nivel o categoria estamos, 
    //el id del premio, y el id de la pregunta. Se utiliza un try catch para controlar que la entrada 
    //solo sea un entero y luego se llama al metodo controlRespuesta que controlas que la entrada esté entre 1 y 4
    public boolean verificarRespuesta(int contador, int idPremio, int pre) {
        System.out.println("Nivel N°" + contador);
        boolean control = true;
        int res = 0;
      //  idPremio = comando.getPremio(contador);
        while (control) {
            try {
                opcion.seleccionarPorPregunta(pre);
                res = entrada.nextInt();
                control = false;
            } catch (InputMismatchException e) {
                opcion.limpiarListaDeOpciones();
                System.out.println("Solo puede ingresar enteros");
                entrada.next();
            }
        }
        res = controlRespuesta(res, pre);
        boolean verRespuesta = opcion.verificarRespuesta(res);
        return verRespuesta;
    }
    //Este metodo controla que la entrada esté entre 1 y 2 para el metodo salirOContinuar
    //Se utiliza un try para controlar que solo se ingresen enteros
    public int controlSalirOContinuar(int elegir) {
        boolean control = false;
        while (control == false) {
            if (elegir < 1 || elegir > 2) {
               boolean controlEntero = true;
               while (controlEntero ) {
                   try {
                       System.out.println("Solo puede ingresar números entre 1 y 2\n1.-Continuar\n2.-Abandonar");
                       elegir = entrada.nextInt();
                       controlEntero = false;
                   } catch (InputMismatchException e) {
                       System.out.println("Solo puede ingresar enteros");
                       entrada.next();                       
                   }
               }
            } else {
                control = true;
            }
        }
        return elegir;
    }
    //Este metodo controla que la entrada esté entre 1 y 5 para el metodo verificarRespuesta
    //Se utiliza un try para controlar que solo se ingresen enteros
    public int controlRespuesta(int res, int pre) {
        Opcion opcion = new Opcion();
        boolean control = false;
        while (control == false) {
            if ((res < 1 || res > 4)) {
                boolean controlEntero = true;
                while (controlEntero ) {
                    try {
                        System.out.println("Solo puede ingresar números entre 1 y 4");
                        opcion.seleccionarPorPregunta(pre);
                        res = entrada.nextInt();
                        controlEntero = false;
                    } catch (InputMismatchException e) {
                        opcion.limpiarListaDeOpciones();
                        System.out.println("Solo puede ingresar enteros");
                        entrada.next();                       
                    }
                    opcion.limpiarListaDeOpciones();
                }
            } else {
                control = true;
            }
        }
        return res;
    }

}
