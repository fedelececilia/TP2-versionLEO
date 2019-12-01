package vista;

import Controlador.ControladorFlujoJuego;
import Controlador.ControladorPrincipal;
import Jugador.Jugador;
import Unidades.Unidad;
import javafx.scene.control.Label;

import java.util.ArrayList;

public class SeleccionDeUnidades {
    Jugador jugador1;
    Jugador jugador2;
    ControladorPrincipal controladorPrincipal;
    ControladorFlujoJuego controladorFlujoJuego;
    Label puntajeJugador1;
    Label puntajeJugador2;

    BotonUnidad gatoSoldado;
    BotonUnidad gatoJinete;
    BotonUnidad gatoCurandero;
    BotonUnidad gatoCatapulta;
    BotonUnidad perroSoldado;
    BotonUnidad perroJinete;
    BotonUnidad perroCurandero;
    BotonUnidad perroCatapulta;

    public SeleccionDeUnidades(Jugador jugador1, Jugador jugador2, ControladorPrincipal controladorPrincipal, Label puntajeJugador1, Label puntajeJugador2, ControladorFlujoJuego controladorFlujoJuego) {
        this.jugador1 = jugador1;
        this.jugador2 = jugador2;
        this.controladorPrincipal = controladorPrincipal;
        this.controladorFlujoJuego = controladorFlujoJuego;
        this.puntajeJugador1 = puntajeJugador1;
        this.puntajeJugador2 = puntajeJugador2;
        jugador1.asignarTurno(true);
        jugador2.asignarTurno(false);
        this.gatoSoldado = new BotonUnidad("imagenes/gato_soldado_precio.png", "soldado", jugador1, jugador2, this);
        this.gatoJinete = new BotonUnidad("imagenes/gato_jinete_precio.png", "jinete", jugador1, jugador2, this);
        this.gatoCurandero = new BotonUnidad("imagenes/gato_curandero_precio.png", "curandero", jugador1, jugador2, this);
        this.gatoCatapulta = new BotonUnidad("imagenes/gato_catapulta_precio.png", "catapulta", jugador1, jugador2, this);
        this.perroSoldado = new BotonUnidad("imagenes/perro_soldado_precio.png", "soldado", jugador2, jugador1, this);
        this.perroJinete = new BotonUnidad("imagenes/perro_jinete_precio.png", "jinete", jugador2, jugador1, this);
        this.perroCurandero = new BotonUnidad("imagenes/perro_curandero_precio.png", "curandero", jugador2, jugador1, this);
        this.perroCatapulta = new BotonUnidad("imagenes/perro_catapulta_precio.png", "catapulta", jugador2, jugador1, this);
    }

    public ArrayList<BotonUnidad> unidadesPosiblesJugador1() { //VER SI ESTOY INSTANCIANDO UNIDAD O EN ALGÚN LUGAR TIENE QUE APARECER new();
        ArrayList<BotonUnidad> listaBotones1 = new ArrayList<>();
        listaBotones1.add(gatoSoldado);
        listaBotones1.add(gatoCurandero);
        listaBotones1.add(gatoJinete);
        listaBotones1.add(gatoCatapulta);
        return listaBotones1;
    }

    public ArrayList<BotonUnidad> unidadesPosiblesJugador2() {
        ArrayList<BotonUnidad> listaBotones2 = new ArrayList<>();
        listaBotones2.add(perroSoldado);
        listaBotones2.add(perroCurandero);
        listaBotones2.add(perroJinete);
        listaBotones2.add(perroCatapulta);
        return listaBotones2;
    }

    public void cambiarUltimaUnidadComprada(Unidad ultimaUnidadComprada) {
        this.controladorFlujoJuego.cambiarUltimaUnidadComprada(ultimaUnidadComprada);
    }

    public void cambiarLabelEstadoDeJuego(String mensaje) {
        this.controladorPrincipal.cambiarLabelEstadoDeJuego(mensaje);
    }

    public void cambiarLabelPuntajeJugador(Jugador jugador) {
        if (jugador == jugador1) {
            this.puntajeJugador1.setText("Puntaje " + jugador.obtenerNombre() + ": " + jugador.obtenerPuntos());
        } else {
            this.puntajeJugador2.setText("Puntaje " + jugador.obtenerNombre() + ": " + jugador.obtenerPuntos());
        }
    }

    public void deshabilitarBotonesUnidadDeJugador(Jugador jugador) {
        if (jugador.obtenerNumeroJugador() == 1) {
            for (BotonUnidad boton : unidadesPosiblesJugador1()){
                boton.setDisable(true);
            }
        } else {
            for (BotonUnidad boton : unidadesPosiblesJugador2()){
                boton.setDisable(true);
            }
        }
    }

    public void habilitarBotonesUnidadDeJugador(Jugador jugador) {
        if (jugador.obtenerNumeroJugador() == 1) {
            int puntosJugador1 = jugador1.obtenerPuntos();
            for (BotonUnidad boton : unidadesPosiblesJugador1()){
                if (boton.obtenerUnidad().obtenerCosto() <= puntosJugador1) {
                    boton.setDisable(false);
                } else {
                    boton.setDisable(true);
                }
            }
        } else {
            int puntosJugador2 = jugador2.obtenerPuntos();
            for (BotonUnidad boton : unidadesPosiblesJugador2()){
                if (boton.obtenerUnidad().obtenerCosto() <= puntosJugador2) {
                    boton.setDisable(false);
                } else {
                    boton.setDisable(true);
                }
            }
        }
    }

}


/*
public void turnoJugador(Jugador jugador) {  //se llama para cada jugador en el handle de botonJugar

        if (jugador == this.jugador1) {
            desbloquearUnidadesJugador1();
            bloquearUnidadesJugador2();
        } else {
            desbloquearUnidadesJugador2();
            bloquearUnidadesJugador1();
        }

        while (jugador.obtenerPuntos() > 0) {

            if (jugador.obtenerPuntos() < 5 ) {
                perroCatapulta.setDisable(true);
                gatoCatapulta.setDisable(true);
            }
            if (jugador.obtenerPuntos() < 3 ) {
                perroJinete.setDisable(true);
                gatoJinete.setDisable(true);
            }
            if (jugador.obtenerPuntos() < 2 ) {
                perroCurandero.setDisable(true);
                gatoCurandero.setDisable(true);
            }

            if (gatoSoldado.isPressed() || gatoJinete.isPressed() || gatoCurandero.isPressed() || gatoCatapulta.isPressed() || perroSoldado.isPressed() || perroJinete.isPressed() || perroCurandero.isPressed() || perroCatapulta.isPressed()) {
                continue;
            }  //feo feo dudoso
        }
    }

    public void bloquearUnidadesJugador2(){
        perroSoldado.setDisable(true);
        perroJinete.setDisable(true);
        perroCurandero.setDisable(true);
        perroCatapulta.setDisable(true);
    }

    public void desbloquearUnidadesJugador2(){
        perroSoldado.setDisable(false);
        perroJinete.setDisable(false);
        perroCurandero.setDisable(false);
        perroCatapulta.setDisable(false);
    }

    public void bloquearUnidadesJugador1(){
        gatoSoldado.setDisable(true);
        gatoJinete.setDisable(true);
        gatoCurandero.setDisable(true);
        gatoCatapulta.setDisable(true);
    }

    public void desbloquearUnidadesJugador1(){
        gatoSoldado.setDisable(false);
        gatoJinete.setDisable(false);
        gatoCurandero.setDisable(false);
        gatoCatapulta.setDisable(false);
    }

    public void estadoBotones(Jugador jugador){
        if (jugador.obtenerPuntos() < 5 ) {
           if(jugador == this.jugador1) perroCatapulta.setDisable(true);
           else gatoCatapulta.setDisable(true);
        }
        if (jugador.obtenerPuntos() < 3 ) {
            if(jugador == this.jugador1) perroJinete.setDisable(true);
            else gatoJinete.setDisable(true);
        }
        if (jugador.obtenerPuntos() < 2 ) {
            if(jugador == this.jugador1) perroCurandero.setDisable(true);
           else gatoCurandero.setDisable(true);
        }
        if (jugador.obtenerPuntos() < 1 ) {
            if(jugador == this.jugador1) perroSoldado.setDisable(true);
            else gatoSoldado.setDisable(true);
        }
    }
 */
