package src;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import src.connection.Connect;
import java.util.ArrayList;

//En esta clase estan todos los metodos que permiten hacer el crud con la base de datos.


public class comandosSql {

    public comandosSql() {
    }

    //Este metodo recibe un comando sql para crear un registro en cualquier tabla de la base de datos
    public void crarRegistro(String query) {
        Connect objConexion = new Connect();
        try (Connection conn = objConexion.connect(); Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            String alerta = e.getMessage();
        }
    }

    //Este metodo devuelve el id de un juego recibiendo como parametros el nombre y el apellido del jugador
    public int buscarJuegoId(String nombre, String apellido){
        int idJuego = 0;
        Connect objConexion = new Connect();
        String query = "SELECT p.idJuego from jugador j, juego p" + " WHERE j.idJugador = p.jugador and j.nombre = "+"'" + nombre + "'"+" and j.apellido = "+"'"+apellido+"'"+";";
        try (Connection conn = objConexion.connect(); Statement stmt = conn.createStatement()) {
            ResultSet resultSet = stmt.executeQuery(query); // En este objeto resulSet se guardan los resultados de
            idJuego = resultSet.getInt("idJuego");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return idJuego;     
    }
    //Este metodo devuelve el id de un jugador recibiendo como parametros el nombre y el apellido del jugador
    public int buscarJugadorId(String nombre, String apellido){
        int idJugador = 0;
        Connect objConexion = new Connect();
        String query = "SELECT idJugador from jugador" + " WHERE nombre = "+"'" + nombre + "'"+" and apellido = "+"'"+apellido+"'"+";";
        try (Connection conn = objConexion.connect(); Statement stmt = conn.createStatement()) {
            ResultSet resultSet = stmt.executeQuery(query); // En este objeto resulSet se guardan los resultados de
            idJugador = resultSet.getInt("idJugador");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return idJugador;
    }

    //Este metodo devuelve el enunciado de la pregunta con el id ingresado.
    public void mostrarPreguntaId(int idPregunta){
        String enunciado = "";
        Connect objConexion = new Connect();
        String query = "SELECT * from pregunta " + " WHERE idPregunta = " + idPregunta + ";";
        try (Connection conn = objConexion.connect(); Statement stmt = conn.createStatement()) {
            ResultSet resultSet = stmt.executeQuery(query); // En este objeto resulSet se guardan los resultados de                                                          
            enunciado = resultSet.getString("enunciado");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("La pregunta es: "+ enunciado);
    }
    //Este metodo devuelve un ArrayList con todas las preguntas de la base de datos
    public ArrayList preguntas(){
        ArrayList<Pregunta>arreglo = new ArrayList<>();
        Connect objConexion = new Connect();
        String query ="SELECT * from pregunta ;";
        try (Connection conn = objConexion.connect(); Statement stmt = conn.createStatement()) {
            ResultSet resultSet = stmt.executeQuery(query);
            ResultSetMetaData rsmd = resultSet.getMetaData();
            while (resultSet.next()) {
                int id = resultSet.getInt("idPregunta");
                int categoria = resultSet.getInt("categoria");
                String enunciado = resultSet.getString("enunciado");
                Pregunta pregunta = new Pregunta(id,categoria,enunciado);
                    arreglo.add(pregunta);                
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return arreglo;
    }   
    //Este metodo devuelve un arrayList con todas las opciones de respuestas
    public ArrayList opciones(){
        ArrayList<Opcion>arreglo = new ArrayList<>();
        Connect objConexion = new Connect();
        String query ="SELECT * from opciones ;";
        try (Connection conn = objConexion.connect(); Statement stmt = conn.createStatement()) {
            ResultSet resultSet = stmt.executeQuery(query);
            ResultSetMetaData rsmd = resultSet.getMetaData();
            while (resultSet.next()) {
                int id = resultSet.getInt("idOpcion");
                String enunciado = resultSet.getString("enunciado");
                int correcto = resultSet.getInt("opcionCorrecta");
                int pregunta = resultSet.getInt("pregunta");
                Opcion opcion = new  Opcion(id,enunciado, correcto, pregunta);
                    arreglo.add(opcion);                
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return arreglo;
    }  
    //Este metodo devuelve un arrayList con todos los juegos creados.
    public ArrayList listarJuegos(){
        ArrayList<Juego>arreglo = new ArrayList<>();
        Connect objConexion = new Connect();
        String query ="SELECT * from juego ;";
        try (Connection conn = objConexion.connect(); Statement stmt = conn.createStatement()) {
            ResultSet resultSet = stmt.executeQuery(query);
            ResultSetMetaData rsmd = resultSet.getMetaData();
            while (resultSet.next()) {
                int id = resultSet.getInt("idJuego");
                int jugador = resultSet.getInt("jugador");
                Juego juego = new  Juego(id,jugador);
                    arreglo.add(juego);                
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return arreglo;
    }
    //Este metodo devuelve el premio correspondiente a la categoria(nivel)ingresado como parametro.
    public int getPremio(int categoria){
        int idPremio = 0;
        Connect objConexion = new Connect();
        String query = "SELECT * from premio " + " WHERE categoria = " +categoria + ";";
        try (Connection conn = objConexion.connect(); Statement stmt = conn.createStatement()) {
            ResultSet resultSet = stmt.executeQuery(query); // En este objeto resulSet se guardan los resultados de                                                          
            idPremio = resultSet.getInt("idPremio");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return idPremio;
    }
    //Este metodo permite actualizar cualquier registro de la base de datos recibiendo como parámetro el comando sql
    public void actualizar(String sql){
        Connect objConexion = new Connect();
        try (Connection conn = Connect.connect(); Statement stmt = conn.createStatement()) {
            int rs = stmt.executeUpdate(sql);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
    //Este metodo devuelve un ArrayList con todos los jugadores creados.
    public ArrayList listarJugadores(){
        ArrayList<Jugador>arreglo = new ArrayList<>();
        Connect objConexion = new Connect();
        String query ="SELECT * from jugador ;";
        try (Connection conn = objConexion.connect(); Statement stmt = conn.createStatement()) {
            ResultSet resultSet = stmt.executeQuery(query);
            ResultSetMetaData rsmd = resultSet.getMetaData();
            while (resultSet.next()) {
                int id = resultSet.getInt("idJugador");
                Jugador jugador = new  Jugador(id);
                    arreglo.add(jugador);                
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return arreglo;
    }
    //Este metodo devuelve un ArrayList con todos los premios.
    public ArrayList listarPremios(){
        ArrayList<Premio>arreglo = new ArrayList<>();
        Connect objConexion = new Connect();
        String query ="SELECT * from premio ;";
        try (Connection conn = objConexion.connect(); Statement stmt = conn.createStatement()) {
            ResultSet resultSet = stmt.executeQuery(query);
            ResultSetMetaData rsmd = resultSet.getMetaData();
            while (resultSet.next()) {
                int idPremio= resultSet.getInt("idPremio");                
               double dinero = resultSet.getDouble("dinero");
               int categoria = resultSet.getInt("categoria");
                Premio premio = new  Premio(idPremio,dinero,categoria);
                    arreglo.add(premio);                
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return arreglo;
    }





}
