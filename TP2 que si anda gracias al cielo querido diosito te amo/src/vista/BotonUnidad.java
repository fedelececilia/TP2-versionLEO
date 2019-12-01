package vista;

import Excepciones.PuntosInsuficientesException;
import Jugador.Jugador;
import Unidades.Unidad;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class BotonUnidad extends Button{

    Image imagen;
    Unidad unidad;

    public BotonUnidad(String imagenRuta, Unidad unidad, Jugador jugadorDeTurno, Jugador jugadorSiguiente, SeleccionDeUnidades seleccionDeUnidades){
        super();
        this.unidad = unidad;
        this.setPrefSize(100,100);
        imagen = new Image(getClass().getResourceAsStream(imagenRuta), 100, 100, false, false);
        ImageView imageView = new ImageView(imagen);
        this.setGraphic(imageView);
        this.setAlignment(Pos.CENTER);
        this.setOnAction(MouseEvent -> {
            try {
                jugadorDeTurno.comprar(unidad);
                System.out.println("Compró la unidad");
                seleccionDeUnidades.cambiarUltimaUnidadComprada(unidad);
                seleccionDeUnidades.cambiarLabelEstadoDeJuego("Ubique la unidad " + unidad.getClass().getSimpleName());
                seleccionDeUnidades.cambiarLabelPuntajeJugador(jugadorDeTurno);
                seleccionDeUnidades.deshabilitarBotonesUnidadDeJugador(jugadorDeTurno);
            } catch (PuntosInsuficientesException e) {
                // LA IDEA ES QUE NUNCA LLEGUE ACÁ PORQUE SE BLOQUEAN LOS BOTONES
                e.printStackTrace();
            }
        });
    }

    public Unidad obtenerUnidad() {
        return this.unidad;
    }
}
