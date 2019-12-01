package Controlador;

import Jugador.Jugador;
import Tablero.Casillero;
import Tablero.Coordenada;
import Unidades.SoldadoDeInfanteria;
import Unidades.Unidad;
import javafx.event.EventHandler;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class HandlerCasilleroJugar implements EventHandler<MouseEvent> {
    private Jugador jugador1;
    private Jugador jugador2;
    private ControladorFlujoJuego controlador;
    private Casillero casillero;
    private Coordenada coordenada;

    public HandlerCasilleroJugar(Jugador jugador1, Jugador jugador2, ControladorFlujoJuego controlador, Casillero casillero, Coordenada coordenada){
        this.jugador1 = jugador1;
        this.jugador2 = jugador2;
        this.controlador = controlador;
        this.casillero = casillero;
        this.coordenada = coordenada;
    }

    public void handleJugar(MouseEvent mouseEvent){if(jugador1.esTurno()) {
        if(mouseEvent.getButton().equals(MouseButton.SECONDARY)){
            if(casillero.obtenerUnidad() instanceof SoldadoDeInfanteria && jugador1.obtenerListaUnidades().contains(casillero.obtenerUnidad())){
                controlador.guardarABatallon(casillero.obtenerUnidad());
                return;
            }
            else if(controlador.obtenerListaBatallon().size() == 3){
                controlador.moverBatallon(coordenada);
                jugador1.asignarTurno(false);
                jugador2.asignarTurno(true);
                return;
            }
        }
        else{
            if (controlador.obtenerSeleccionada() == null && jugador1.obtenerListaUnidades().contains(casillero.obtenerUnidad())) {
                controlador.seleccionarUnidad(casillero.obtenerUnidad());
            }
            else if(jugador1.obtenerListaUnidades().contains(controlador.obtenerSeleccionada())){
                controlador.accionUnidad(coordenada, jugador1);
                jugador1.asignarTurno(false);
                jugador2.asignarTurno(true);
            }
        }
    }
    else if(jugador2.esTurno()) {
        if(mouseEvent.getButton().equals(MouseButton.SECONDARY)){
            if(casillero.obtenerUnidad() instanceof SoldadoDeInfanteria && jugador2.obtenerListaUnidades().contains(casillero.obtenerUnidad())){
                controlador.guardarABatallon(casillero.obtenerUnidad());
                return;
            }
            else if(controlador.obtenerListaBatallon().size() == 3){
                controlador.moverBatallon(coordenada);
                jugador1.asignarTurno(true);
                jugador2.asignarTurno(false);
                return;
            }
        }
        if (controlador.obtenerSeleccionada() == null && jugador2.obtenerListaUnidades().contains(casillero.obtenerUnidad())) {
            controlador.seleccionarUnidad(casillero.obtenerUnidad());
        }
        else if(jugador2.obtenerListaUnidades().contains(controlador.obtenerSeleccionada())){
            controlador.accionUnidad(coordenada, jugador2);
            jugador1.asignarTurno(true);
            jugador2.asignarTurno(false);
        }
    }
    }

    public void handleUbicar(Jugador jugador1, Jugador jugador2){
        Unidad ultimaComprada = controlador.obtenerUltimaUnidadComprada();
        if (jugador1.esTurno()){
            if (jugador1.ubicarUnidad(ultimaComprada, coordenada)) {
                controlador.cambiarUltimaUnidadComprada(null);
                if (jugador2.obtenerPuntos() > 0) {
                    jugador1.asignarTurno(false);
                    jugador2.asignarTurno(true);
                    controlador.habilitarBotonesUnidadDeJugador(jugador2);
                    return;
                }
                else controlador.habilitarBotonesUnidadDeJugador(jugador1);
            }
        }
        else {
            if (jugador2.ubicarUnidad(ultimaComprada, coordenada)) {
                controlador.cambiarUltimaUnidadComprada(null);
                if (jugador1.obtenerPuntos() > 0) {
                    jugador1.asignarTurno(true);
                    jugador2.asignarTurno(false);
                    controlador.habilitarBotonesUnidadDeJugador(jugador1);
                    return;
                }
                controlador.habilitarBotonesUnidadDeJugador(jugador2);
            }
        }
        if (jugador1.obtenerPuntos() == 0 && jugador2.obtenerPuntos() == 0) {
            controlador.cambiarAPantallaDeJuego();
        }
    }

    @Override
    public void handle(MouseEvent mouseEvent) {
        if(controlador.obtenerEstadoJuego()){ //Si todavia no estamos jugando, estamos en la seleccion de unidades
            handleJugar(mouseEvent);
        }
        else{
            handleUbicar(jugador1, jugador2);
        }
    }
}