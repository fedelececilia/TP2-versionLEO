package Controlador;

import Jugador.Jugador;
import Tablero.Tablero;
import Unidades.Unidad;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import vista.*;

public class ControladorPrincipal {

    private Stage stage;
    private Jugador jugador1;
    private Jugador jugador2;
    private Unidad ultimaUnidadComprada;
    private Label labelTurno;
    private Label labelEstadoDeJuego;

    public ControladorPrincipal(Stage stage){
        this.stage = stage;
    }

    public StackPane principal(){
        StackPane stackPane = new StackPane();
        stackPane.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        stackPane.setMinWidth(1024);
        stackPane.setMinHeight(720);
        return stackPane;
    }

    public Scene MenuInicio(){
        StackPane stackPane = principal();
        VBox canvas = new VBox();
        HBox contenedorBotonSalir = new HBox();
        BotonSalir salir = new BotonSalir();
        contenedorBotonSalir.getChildren().add(salir);
        HBox contenedorPrincipal = new HBox();
        contenedorPrincipal.setMinHeight(700);

        BotonComenzar comenzar = new BotonComenzar(contenedorPrincipal);
        contenedorPrincipal.setAlignment(Pos.CENTER);
        contenedorPrincipal.getChildren().add(comenzar);
        comenzar.setOnAction(actionEvent -> {
            stage.setScene(CreacionDeUsuarios());
            stage.setFullScreen(true);});
        canvas.getChildren().add(contenedorBotonSalir);
        canvas.getChildren().add(contenedorPrincipal);


        stackPane.getChildren().add(canvas);
        return new Scene(stackPane);
    }

    public Scene CreacionDeUsuarios(){
        StackPane stackPane = principal();
        VBox canvas = new VBox();
        HBox contenedorBotonSalir = new HBox();
        BotonSalir salir = new BotonSalir();
        contenedorBotonSalir.getChildren().add(salir);
        HBox contenedorPrincipal = new HBox();
        contenedorPrincipal.setMinHeight(700);
        contenedorPrincipal.setAlignment(Pos.CENTER);
        TextField nombreJugador1 = new TextField("Ingrese nombre jugador 1");
        TextField nombreJugador2 = new TextField("Ingrese nombre jugador 2");
        nombreJugador1.textProperty().addListener((observableValue, s, t1) ->{});
        nombreJugador2.textProperty().addListener((observableValue, s, t1) ->{});
        nombreJugador1.setMaxWidth(220);
        nombreJugador2.setMaxWidth(220);
        VBox panelNombreJugadores = new VBox(40);
        panelNombreJugadores.setAlignment(Pos.CENTER);
        canvas.getChildren().add(contenedorBotonSalir);
        BotonJugar jugar = new BotonJugar(contenedorPrincipal, nombreJugador1, nombreJugador2);
        jugar.setOnAction(actionEvent -> {
            stage.setScene(MenuSeleccionDeUnidades(nombreJugador1.getText(), nombreJugador2.getText()));
            stage.setFullScreen(true);
        });
        panelNombreJugadores.getChildren().addAll(nombreJugador1, nombreJugador2, jugar);
        contenedorPrincipal.getChildren().add(panelNombreJugadores);
        canvas.getChildren().add(contenedorPrincipal);
        stackPane.getChildren().add(canvas);
        return new Scene(stackPane);
    }

    public Scene MenuSeleccionDeUnidades(String nombreJugador1, String nombreJugador2) {
        Tablero tablero = new Tablero();
        this.jugador1 = new Jugador(nombreJugador1, tablero, 1);
        this.jugador2 = new Jugador(nombreJugador2, tablero, 2);

        BotonSalir botonSalir = new BotonSalir();
        this.labelTurno = new Label();
        cambiarLabelTurno(jugador1);
        labelTurno.setStyle("-fx-text-fill:WHITE;");
        this.labelEstadoDeJuego = new Label();
        cambiarLabelEstadoDeJuego("Seleccione una unidad.");
        labelEstadoDeJuego.setStyle("-fx-text-fill:WHITE;");

        HBox contenedorSuperior = new HBox(20);
        contenedorSuperior.setAlignment(Pos.CENTER_LEFT);
        contenedorSuperior.getChildren().addAll(botonSalir, labelTurno, labelEstadoDeJuego);

        Label puntajeJugador1 = new Label("Puntaje " + jugador1.obtenerNombre() + ": " + jugador1.obtenerPuntos());
        puntajeJugador1.setStyle("-fx-text-fill:WHITE;");
        Label puntajeJugador2 = new Label("Puntaje " + jugador2.obtenerNombre() + ": " + jugador2.obtenerPuntos());
        puntajeJugador2.setStyle("-fx-text-fill:WHITE;");
        SeleccionDeUnidades seleccionDeUnidades = new SeleccionDeUnidades(jugador1, jugador2, this, puntajeJugador1, puntajeJugador2);

        VBox contenedorUnidadesPosibles1 = new VBox(20);
        contenedorUnidadesPosibles1.setAlignment(Pos.CENTER);
        contenedorUnidadesPosibles1.getChildren().add(puntajeJugador1);
        for (BotonUnidad boton : seleccionDeUnidades.unidadesPosiblesJugador1()) {
            contenedorUnidadesPosibles1.getChildren().add(boton);
        }

        ControladorFlujoJuego controladorFlujoJuego = new ControladorFlujoJuego(jugador1, jugador2, tablero);
        TableroVista tableroVista = new TableroVista(tablero, jugador1, jugador2, controladorFlujoJuego);

        VBox contenedorUnidadesPosibles2 = new VBox(20);
        contenedorUnidadesPosibles2.setAlignment(Pos.CENTER);
        contenedorUnidadesPosibles2.getChildren().add(puntajeJugador2);
        for (BotonUnidad boton : seleccionDeUnidades.unidadesPosiblesJugador2()) {
            contenedorUnidadesPosibles2.getChildren().add(boton);
        }

        HBox contenedorPrincipal = new HBox(30);
        contenedorPrincipal.setMinHeight(700);
        contenedorPrincipal.setAlignment(Pos.CENTER);
        contenedorPrincipal.getChildren().addAll(contenedorUnidadesPosibles1, tableroVista, contenedorUnidadesPosibles2);

        VBox canvas = new VBox();
        canvas.getChildren().addAll(contenedorSuperior, contenedorPrincipal);

        StackPane stackPane = principal();
        stackPane.getChildren().add(canvas);

        return new Scene(stackPane);
    }

    public void cambiarUltimaUnidadComprada(Unidad ultimaUnidadComprada) {
        this.ultimaUnidadComprada = ultimaUnidadComprada;
    }

    public void cambiarLabelTurno(Jugador jugador) {
        this.labelTurno.setText("Turno de: " + jugador.obtenerNombre());
    }

    public void cambiarLabelEstadoDeJuego(String mensaje) {
        this.labelEstadoDeJuego.setText(mensaje);
    }
}
