package es.uma.isia;

public class DimensionesIncompatibles extends Exception {
    public DimensionesIncompatibles(){
        super();
    }
    public DimensionesIncompatibles(String texto){
        super(texto);
    }
}
