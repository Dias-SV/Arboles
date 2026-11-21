public class NodoArbolAVL 
{
    int valor;
    int altura;
    int balanceo;
    NodoArbolAVL hizq;
    NodoArbolAVL hder;

    public NodoArbolAVL(int valor)
    {
        this.valor=valor;
        this.altura=1;
        this.balanceo=0;
        this.hizq=null;
        this.hder=null;
    }
}
