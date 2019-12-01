package Controlador;

import Jugador.Jugador;
import Excepciones.BatallonInvalidoException;
import Tablero.Coordenada;
import Tablero.Direccion;
import Tablero.Tablero;
import Unidades.Batallon;
import Unidades.Catapulta;
import Unidades.Movible;
import Unidades.Unidad;

import java.util.ArrayList;

public class ControladorFlujoJuego {
    private boolean jugando;
    private Unidad seleccionada;
    private Jugador jugador1;
    private Jugador jugador2;
    private Tablero tablero;
    private ArrayList<Movible> batallon;

    public ControladorFlujoJuego(Jugador jugador1, Jugador jugador2, Tablero tablero){
        seleccionada = null;
        jugando = false;
        this.jugador1 = jugador1;
        this.jugador2 = jugador2;
        this.tablero = tablero;
        batallon = new ArrayList<>();
    }

    public void seleccionarUnidad(Unidad unidad){
        this.seleccionada = unidad;
    }

    public Unidad obtenerSeleccionada(){
        return seleccionada;
    }

    public void accionUnidad(Coordenada coordenada, Jugador jugador){
        if(jugador.esTurno()) {
            if (tablero.obtenerCasillero(coordenada).estaOcupado()) {
                System.out.println("Se esta realizando una accion");
                jugador.realizarAccionDeUnidad(seleccionada, tablero.obtenerCasillero(coordenada).obtenerUnidad());
                jugador1.checkearUnidadesMuertas();
                jugador2.checkearUnidadesMuertas();
                jugador1.actualizarEstadoJugador();
                jugador2.actualizarEstadoJugador();
                updateEstadoDeJuego(jugador1, jugador2);
                seleccionarUnidad(null);
                return;
            }
            if(jugador.obtenerListaUnidades().contains(seleccionada)) {
                if (coordenada.compararCoordenada(seleccionada.obtenerCoordenada())) {
                    System.out.println("Movete a otro lugar!");
                    seleccionarUnidad(null);
                    return;
                }
                if (seleccionada instanceof Catapulta) {
                    System.out.println("No podes mover una catapulta!");
                    seleccionarUnidad(null);
                    return;
                }

                jugador.mover((Movible) seleccionada, Direccion.obtenerDireccionSegunCoordenadas(seleccionada.obtenerCoordenada(), coordenada));
            }
            else{
                System.out.println("No podes mover una unidad enemiga");
            }
            seleccionarUnidad(null);
            return;
        }
    }

    public void guardarABatallon(Unidad soldado){
        batallon.add(((Movible) soldado));
    }

    public ArrayList<Movible> obtenerListaBatallon(){
        return batallon;
    }

    public void moverBatallon(Coordenada coordenada) {
        Batallon nuevo = null;
        try {
            nuevo = new Batallon(batallon.get(0),batallon.get(1), batallon.get(2));
            nuevo.mover(tablero,Direccion.obtenerDireccionSegunCoordenadas(batallon.get(1).obtenerCoordenada(), coordenada));
        } catch (BatallonInvalidoException e) {
            e.getMessage();
        }
        batallon.clear();
    }

    public void updateEstadoDeJuego(Jugador jugador1, Jugador jugador2){
        /*
          Negativity is my passion
         */
        if(jugador1.obtenerEstado() == "PERDEDOR"){
            System.out.println("Perdio el jugador 1");
            System.exit(0);
        }
        if(jugador2.obtenerEstado() == "PERDEDOR"){
            System.out.println("Perdio el jugador 2");
            System.exit(0);
        }
    }
}
