public class NodoRB {
    public int valor;
    public boolean rojo;
    public NodoRB hIzquierdo;
    public NodoRB hDerecho;
    public NodoRB padre;

    public NodoRB(int valor) {
        this.valor = valor;
        this.hIzquierdo = null;
        this.hDerecho = null;
        this.padre = null;
        this.rojo = true;
    }
}
