public class NodoAA {
    private String elemento;
    private NodoAA hIzquierdo;
    private NodoAA hDerecho;

    public NodoAA(String elemento) {
        this.elemento = elemento;
        this.hIzquierdo = null;
        this.hDerecho = null;
    }

    public NodoAA getHIzquierdo(){
        return hIzquierdo;
    }

    public void setHIzquierdo(NodoAA hIzquierdo) {
        this.hIzquierdo = hIzquierdo;
    }

    public NodoAA getHDerecho(){
        return hDerecho;
    }

    public void setHDerecho(NodoAA hDerecho) {
        this.hDerecho = hDerecho;
    }

    public String getElemento() {
        return elemento;
    }

    public void setElemento(String elemento) {
        this.elemento = elemento;
    }
}
