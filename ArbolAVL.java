import java.util.LinkedList;
import java.util.Queue;

public class ArbolAVL 
{
    public NodoArbolAVL raiz;

    public ArbolAVL()
    {
        this.raiz=null;
    }

    //BUSCAR
    public NodoArbolAVL buscar(int valor) 
    {
        return buscarRec(raiz, valor);
    }

    private NodoArbolAVL buscarRec(NodoArbolAVL nodo, int valor) 
    {
        if (nodo == null || nodo.valor == valor) {
            return nodo;
        }

        if (valor < nodo.valor) {
            return buscarRec(nodo.hizq, valor);
        } else {
            return buscarRec(nodo.hder, valor);
        }
    }

    //IMPRIMIR ARBOL

    public void imprimirArbol() 
    {
        if (this.raiz == null) 
        {
            System.out.println("El arbol esta vacio.");
            return;
        }

        Queue<NodoArbolAVL> queue = new LinkedList<>();
        queue.add(raiz);
        int nivel = 0;

        while (!queue.isEmpty()) 
        {
            int nivelTamaño = queue.size();
            System.out.println("Nivel " + nivel + ":");

            while (nivelTamaño > 0) 
            {
                NodoArbolAVL actual = queue.poll();
                System.out.print("[" + actual.valor + "] ");

                if (actual.hizq != null) 
                {
                    queue.add(actual.hizq);
                    System.out.print("(Izq: " + actual.hizq.valor + ") ");
                }
                if (actual.hder != null) 
                {
                    queue.add(actual.hder);
                    System.out.print("(Der: " + actual.hder.valor + ") ");
                }

                System.out.print(" | ");
                nivelTamaño--;
            }

            System.out.println("\n");
            nivel++;
        }
    }

    //INSERTAR

    public void insertar(int valor) 
    {
        raiz = insertarRec(raiz, valor);
    }

    private NodoArbolAVL insertarRec(NodoArbolAVL nodo, int valor) 
    {
        if (nodo == null) 
        {
            return new NodoArbolAVL(valor);
        }

        if (valor < nodo.valor) 
        {
            nodo.hizq = insertarRec(nodo.hizq, valor);
        } 
        else if (valor > nodo.valor) 
        {
            nodo.hder = insertarRec(nodo.hder, valor);
        } 

        return balanceo(nodo);
    }

    //ELIMINAR

    public void eliminar(int valor) 
    {
        raiz = eliminarRec(raiz, valor);
    }

    private NodoArbolAVL eliminarRec (NodoArbolAVL nodo, int valor) 
    {
        if (nodo == null) 
        {
            return null;
        }

        if (valor < nodo.valor) 
        {
            nodo.hizq = eliminarRec(nodo.hizq, valor);
        } 
        else if (valor > nodo.valor) 
        {
            nodo.hder = eliminarRec(nodo.hder, valor);
        } 
        else 
            {
            if (nodo.hizq == null) 
            {
                return nodo.hder;
            } 
            else if (nodo.hder == null) 
            {
                return nodo.hizq;
            }
            nodo.valor = valorMinimo(nodo.hder);
            nodo.hder = eliminarRec(nodo.hder, nodo.valor);
        }
        return balanceo(nodo);
    }

    private int valorMinimo(NodoArbolAVL nodo) 
    {
        while (nodo.hizq != null) 
        {
            nodo = nodo.hizq;
        }
        return nodo.valor;
    }

    //METODOS ROTACION

    public NodoArbolAVL RotSI(NodoArbolAVL padre)
    {
        NodoArbolAVL newraiz = padre.hder;
        NodoArbolAVL newsubizq = newraiz.hizq;

        newraiz.hizq=padre;
        padre.hder=newsubizq;

        nuevosdatos(padre);
        nuevosdatos(newraiz);
       
        return newraiz;
    }

    public NodoArbolAVL RotSD(NodoArbolAVL padre)
    {
        NodoArbolAVL newraiz = padre.hizq;
        NodoArbolAVL newsubder = newraiz.hder;
        
        newraiz.hder = padre;
        padre.hizq = newsubder;

        nuevosdatos(padre);
        nuevosdatos(newraiz);

        return newraiz;
    }

    public NodoArbolAVL balanceo(NodoArbolAVL nodo)
    {
        nuevosdatos(nodo);

        if(nodo.balanceo > 1)
        {
            if(nodo.hder.balanceo < 0)
            {
                nodo.hder=RotSD(nodo.hder);
            }
            return RotSI(nodo);
        }
        
        if(nodo.balanceo < -1)
        {
            if(nodo.hizq.balanceo > 0)
            {
                nodo.hizq=RotSI(nodo.hizq);
            }
            return RotSD(nodo);
        }
        
        return nodo;
    }


    //Para OBTENER su altura y refrescar balanceo

    private int getAltura(NodoArbolAVL nodo)
    {
        if(nodo == null)
        {
            return 0;
        }
        return nodo.altura;
    }


    public void nuevosdatos(NodoArbolAVL nodo)
    {
        if(nodo!=null)
        {
            int newalt= 1 + getAltura(nodo.hizq);
            if(getAltura(nodo.hder)>getAltura(nodo.hizq))
            {
                newalt= 1 + getAltura(nodo.hder);
            }
            nodo.altura=newalt;
            nodo.balanceo= getAltura(nodo.hder) - getAltura(nodo.hizq);
        }   
    }
}
