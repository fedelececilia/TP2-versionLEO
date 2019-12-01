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

    public BotonUnidad(String imagenRuta, Unidad unidad, Jugador jugador, SeleccionDeUnidades seleccionDeUnidades){
        super();
        this.setPrefSize(100,100);
        imagen = new Image(getClass().getResourceAsStream(imagenRuta), 100, 100, false, false);
        ImageView imageView = new ImageView(imagen);
        this.setGraphic(imageView);
        this.setAlignment(Pos.CENTER);
        this.setOnAction(MouseEvent -> {
            try {
                jugador.comprar(unidad);
                System.out.println("Compró la unidad");
                seleccionDeUnidades.cambiarUltimaUnidadComprada(unidad);
                seleccionDeUnidades.cambiarLabelEstadoDeJuego("Ubique la unidad " + unidad.getClass().getSimpleName());
                seleccionDeUnidades.cambiarLabelPuntajeJugador(jugador);
                // BLOQUEAR LOS BOTONES DE ESTE JUGADOR LUEGO DE COMPRAR
            } catch (PuntosInsuficientesException e) {
                // LA IDEA ES QUE NUNCA LLEGUE ACÁ PORQUE SE BLOQUEAN LOS BOTONES
                e.printStackTrace();
            }
        });
    }
}
