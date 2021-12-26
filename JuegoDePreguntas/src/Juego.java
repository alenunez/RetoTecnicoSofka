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

    public void iniciarJuego() {
        int idPremio = 0;
        boolean control = true;
        int contador = 1;
        Jugador jugador = new Jugador();
        boolean verRespuesta = false;
        jugar(idPremio, control, contador, jugador, verRespuesta);
    }

    public void jugar(int idPremio, boolean control, int contador, Jugador jugador, boolean verRespuesta) {
        while (control && contador <= 5) {
            if (contador == 1) {
                primeraRonda(jugador);
            }
            int pre = pregunta.mostrarPregunta(contador);
            verRespuesta = verificarRespuesta(contador, idPremio, pre);
            if (verRespuesta == true) {
                if (contador == 5) {
                    respuestaCorrectaFinal(jugador, contador);
                    control = false;
                    contador++;
                } else if (contador != 5) {
                    respuestaCorrecta(jugador, contador);
                    contador++;
                }
            } else {
                System.out.println("Juego finalizado. Perdiste todo tu dinero acumulado");
                control = false;
            }
            if ((contador > 1 && contador <= 5) && control == true) {
                control = salirOContinuar(control, jugador, contador);
            } else if (contador == 5 && control == true) {
                System.out.println("Su dinero acumulado es: " + jugador.getPremio()
                        + "Pesos. La siguiente pregunta es por " + premio.getPremioDinero(contador) + " Pesos");
            }
            Ronda ronda = new Ronda(pre, idPremio, obtenerUltimoJuego(), contador - 1, opcion.getIdOpcion());
        }
    }

    public void listarJuegos() {
        listaDeJuegos = comando.listarJuegos();
    }

    public int obtenerUltimoJuego() {
        listarJuegos();
        int idUltimoJuego = listaDeJuegos.get(listaDeJuegos.size() - 1).getId();
        return idUltimoJuego;
    }

    public void primeraRonda(Jugador jugador) {
        System.out.println("Ingrese el nombre del jugador");
        String nombre = entrada.nextLine();
        System.out.println("Ingrese el apellido del jugdor");
        String apellido = entrada.nextLine();
        jugador.setNombre(nombre, jugador.obtenerUltimoJugador());
        jugador.setApellido(apellido, jugador.obtenerUltimoJugador());
        Juego juego = new Juego(jugador.obtenerUltimoJugador());
    }

    public void respuestaCorrectaFinal(Jugador jugador, int contador) {
        jugador.setPremio(premio.getPremioDinero(contador) + jugador.getPremio(), jugador.obtenerUltimoJugador());
        System.out.println("Felicitaciones has ganado " + jugador.getPremio());
        Ganador ganador = new Ganador(jugador.obtenerUltimoJugador());
    }

    public void respuestaCorrecta(Jugador jugador, int contador) {
        System.out.println("Has pasado de ronda");
        jugador.setPremio(premio.getPremioDinero(contador) + jugador.getPremio(),
                jugador.obtenerUltimoJugador());
    }

    public boolean salirOContinuar(boolean control, Jugador jugador, int contador) {
        System.out.println("Su dinero acumulado es: " + jugador.getPremio() + " Pesos. ");
        System.out.println("¿Desea seguir el juego para ganar " + premio.getPremioDinero(contador)
                + " Pesos. O abandonar con lo que ha ganado?\n1.-Continuar\n2.-Abandonar");
        int elegir = entrada.nextInt();
        elegir = controlSalirOContinuar(elegir);
        if (elegir == 2) {
            control = false;
            System.out.println("Juego finalizado. Te llevas " + jugador.getPremio() + " Pesos");
        }
        return control;
    }

    public boolean verificarRespuesta(int contador, int idPremio, int pre) {
        System.out.println("Nivel N°" + contador);
        boolean control = true;
        int res = 0;
        idPremio = comando.getPremio(contador);
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

    public int controlSalirOContinuar(int elegir) {
        boolean control = false;
        while (control == false) {
            if (elegir < 1 || elegir > 2) {
             //   System.out.println("Solo puede ingresar valores entre 1 y 2 \n1.-Continuar\n2.-Abandonar");
               // elegir = entrada.nextInt();
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
