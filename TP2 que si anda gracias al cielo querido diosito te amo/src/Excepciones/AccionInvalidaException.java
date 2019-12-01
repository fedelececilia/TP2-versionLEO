package Excepciones;

public class AccionInvalidaException extends Exception {
    private static final String mensaje = "La acción es inválida. Pierde el turno.";

    public AccionInvalidaException(){
        super();
    };

    /*public void getMensaje(){
        System.out.println(mensaje);
    }*/
    public String getMensaje() { return this.mensaje; }
}
