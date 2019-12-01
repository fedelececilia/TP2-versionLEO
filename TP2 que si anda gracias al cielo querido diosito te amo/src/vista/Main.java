package vista;


import Controlador.ControladorPrincipal;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    Stage stage;

    public static void main(String[] args){
        launch(args);
    }


    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        ControladorPrincipal controladorPrincipal = new ControladorPrincipal(this.stage);
        stage.setTitle("AlgoChess");
        stage.setMinWidth(1024);
        stage.setMinHeight(720);
        stage.setFullScreen(true);
        stage.setMaximized(true);
        stage.setScene(controladorPrincipal.MenuInicio());
        stage.show();
    }
}



/*package vista;


import Jugador.Jugador;
import Controlador.ControladorFlujoJuego;
import Tablero.Tablero;
import Tablero.Coordenada;
import Unidades.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class Main extends Application {
    Stage stage;
    Jugador jugador1;
    Jugador jugador2;
    Tablero tablero;
    MediaPlayer mediaPlayer;
    //TableroVista tableroVista;                            SE CREA EN MenuSeleccionDeUnidades

    public static void main(String[] args){
        launch(args);
    }


    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        Tablero tablero = new Tablero();
        Jugador jugador1 = new Jugador("leo", tablero, 1);

        Jinete jinete = new Jinete();
        SoldadoDeInfanteria soldado = new SoldadoDeInfanteria();
        Catapulta catapulta = new Catapulta();
        Curandero curandero = new Curandero();

        SoldadoDeInfanteria soldado2 = new SoldadoDeInfanteria();
        SoldadoDeInfanteria soldado3 = new SoldadoDeInfanteria();
        SoldadoDeInfanteria soldado4 = new SoldadoDeInfanteria();
        Jinete jinete2 = new Jinete();
        Jinete jinete3 = new Jinete();


        Coordenada coord = new Coordenada(5,6);
        Coordenada coord2 = new Coordenada(6,9);
        Coordenada coord3 = new Coordenada(1,3);
        Coordenada coord4 = new Coordenada(4,5);
        Coordenada coord5 = new Coordenada(11,1);
        Coordenada coord6 = new Coordenada(11,2);
        Coordenada coord7 = new Coordenada(11,3);
        Coordenada coord8 = new Coordenada(11,4);
        Coordenada coord9 = new Coordenada(11,5);

        Jugador jugador2 = new Jugador("oel", tablero, 2 );
        ControladorFlujoJuego controlador = new ControladorFlujoJuego(jugador1, jugador2, tablero);
        TableroVista tableroVista = new TableroVista(tablero, jugador1, jugador2, controlador);

        jugador1.comprar(jinete);
        jugador1.comprar(catapulta);
        jugador1.comprar(curandero);
        jugador1.comprar(soldado);
        jugador1.ubicarUnidad(jinete, coord);
        jugador1.ubicarUnidad(catapulta, coord2);
        jugador1.ubicarUnidad(curandero, coord3);
        jugador1.ubicarUnidad(soldado, coord4);
        jugador1.asignarTurno(true);

        jugador2.comprar(jinete2);
        jugador2.comprar(jinete3);
        jugador2.comprar(soldado2);
        jugador2.comprar(soldado3);
        jugador2.comprar(soldado4);
        jugador2.ubicarUnidad(jinete2,coord5);
        jugador2.ubicarUnidad(jinete3,coord6);
        jugador2.ubicarUnidad(soldado2,coord7);
        jugador2.ubicarUnidad(soldado3,coord8);
        jugador2.ubicarUnidad(soldado4,coord9);

        Scene scene = new Scene(tableroVista);
        stage.setTitle("Jugador");
        stage.setScene(scene);
        stage.setMinWidth(950);
        stage.setMinHeight(800);
        //reproducirMusicaDeFondo();
        stage.show();
    }
}*/

