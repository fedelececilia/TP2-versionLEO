package Jugador;

import Excepciones.*;
import Tablero.*;
import Unidades.*;
import java.util.ArrayList;
import java.util.Iterator;

public class Jugador {
    private String nombre;
    private ArrayList<Unidad> unidades;
    private Tablero tablero;
    private int puntos;
    private int nroJugador; // Valdra 1 o 2, segun el numero del jugador
    private String estado;
    private boolean esTurno;

    public Jugador(String nombre, Tablero tablero, int nroJugador) {
        this.nombre = nombre;
        this.unidades = new ArrayList<>();
        this.tablero = tablero;
        this.puntos = 20;
        this.nroJugador = nroJugador;
        this.estado = "JUGANDO";
    }

    public void agregarUnidadAJugador(Unidad unidad) {
        this.unidades.add(unidad);
    }

    public void eliminarUnidadDelJugador(Unidad unidad) {
        this.unidades.remove(unidad);
        if (unidades.size() == 0) this.estado = "PERDEDOR"; //FIN DEL JUEGO
    }

    public int obtenerCantidadUnidades() {
        return this.unidades.size();
    }

    public String obtenerNombre() {
        return nombre;
    }

    public int obtenerPuntos() {
        return this.puntos;
    }

    public boolean tieneSuficientesPuntos(Unidad unidad) { return (unidad.obtenerCosto() <= obtenerPuntos()); }

    public void comprar(Unidad unidad) throws PuntosInsuficientesException {
        if (tieneSuficientesPuntos(unidad)) {
            agregarUnidadAJugador(unidad);
            this.puntos -= unidad.obtenerCosto();
        }
        else {
            throw new PuntosInsuficientesException();
        }
    }

    public boolean ubicarUnidad(Unidad unidad, Coordenada coordenada) {
        try {
            if (!estaEnLadoDelTableroCorrespondiente(coordenada)) {
                System.out.println("Estás tratando de ubicar en una celda del lado contario.");
                return false;
            }
            tablero.ubicarUnidad(unidad, coordenada);
        }
        catch (CasilleroOcupadoException e) {
            e.getMensaje();
            return false;
        }
        catch (CasilleroInvalidoException e) {
            e.getMensaje();
            return false;
        }
        return true;
    }

    public void mover(Movible unidadMovible, Direccion direccion) { tablero.mover(unidadMovible, direccion); }

    public boolean estaEnLadoDelTableroCorrespondiente(Coordenada coordenada) {
        if (this.nroJugador == 1) {
            if (coordenada.obtenerHorizontal() <= 10) return true;
            return false;
        }
        if (coordenada.obtenerHorizontal() > 10) return true;
        return false;
    }

    public ArrayList<Unidad> obtenerListaUnidades() {
        return this.unidades;
    }

    public String obtenerEstado() { return this.estado; }

    public void realizarAccionDeUnidad(Unidad unaUnidad, Unidad otraUnidad) {
        try {
            accionValida(unaUnidad, otraUnidad);
            unaUnidad.realizarAccion(otraUnidad, tablero, unidades);
        } catch (AccionInvalidaException e) {
            e.getMensaje();
        }
    }

    public boolean accionValida (Unidad unidad1, Unidad unidad2) throws AccionInvalidaException{
        if (this.unidades.contains(unidad2) && !esCurandero(unidad1)) return false;  //trató de atacar A una unidad aliada
        if (!this.unidades.contains(unidad1) && !esCurandero(unidad2)) return false; //trató de atacar CON una unidad enemiga
        if (!this.unidades.contains(unidad2) && esCurandero(unidad1)) return false; //trató de curar A unidad enemiga
        if (!this.unidades.contains(unidad1) && esCurandero(unidad2)) return false; // trato de curar CON un curandero enemigo
        return true;
    }

    public boolean esCurandero(Unidad unidad) { return (unidad instanceof Curandero); }

    public void asignarTurno(boolean turno){
        this.esTurno = turno;
    }

    public boolean esTurno(){
        return this.esTurno;
    }

    public Unidad obtenerUltimaUnidad() {return this.unidades.get(this.unidades.size() -1); }

    public void checkearUnidadesMuertas() {
        for(Iterator<Unidad> iterador = unidades.iterator(); iterador.hasNext();){
            Unidad actual = iterador.next();
            if(actual.obtenerVida() <= 0){
                tablero.obtenerCasillero(actual.obtenerCoordenada()).vaciarCasillero();
                iterador.remove();
            }
        }
    }

    public void actualizarEstadoJugador(){
        if (unidades.size() == 0) this.estado = "PERDEDOR";
    }

    public int obtenerNumeroJugador() {
        return nroJugador;
    }
}

