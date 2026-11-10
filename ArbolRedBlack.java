import java.util.LinkedList;
import java.util.Queue;

public class ArbolRedBlack {

    public NodoRB raiz;
    public final NodoRB nil; //usado para las hojas

    public ArbolRedBlack() {
        nil = new NodoRB(0);
        nil.rojo = false;
        nil.hDerecho = nil;
        nil.hIzquierdo = nil;
        nil.padre = nil;
        this.raiz = nil;
    }

    //Nodos auxiliares
    public NodoRB abuelo(NodoRB nodo) {
        if ((nodo != null) && (nodo.padre != null)) {
		    return nodo.padre.padre;
        } else {
		    return null;
        }
    }
    public NodoRB tio(NodoRB nodo) {
        NodoRB a = abuelo(nodo);
        if (nodo.padre == a.hIzquierdo) {
		    return a.hDerecho;
        } else {
		    return a.hIzquierdo;
        }
    }

    //Inserciones
    public void insertar(int valor) {
        NodoRB nuevo = new NodoRB(valor);
        nuevo.hIzquierdo = nil;
        nuevo.hDerecho = nil;
        nuevo.padre = nil;

        NodoRB padre = nil;
        NodoRB actual = raiz;

        while (actual != nil) {
            padre = actual;
            if (valor < actual.valor) {
                actual = actual.hIzquierdo;
            } else if (valor > actual.valor) {
                actual = actual.hDerecho;
            } else {
                return;
            }
        }

        nuevo.padre = padre;
        if (padre == nil) {
            raiz = nuevo;
        } else if (valor < padre.valor) {
            padre.hIzquierdo = nuevo;
        } else {
            padre.hDerecho = nuevo;
        }

        insercionCaso1(nuevo);
    }

    public void insercionCaso1(NodoRB nodo) {
        if (nodo.padre == nil) {
            nodo.rojo = false;
        } else {
            insercionCaso2(nodo);
        }
    }

    public void insercionCaso2(NodoRB nodo) {
        if (!nodo.padre.rojo) {
            return;
        } else {
            insercionCaso3(nodo);
        }
    }

    public void insercionCaso3(NodoRB nodo) {
        NodoRB tio = tio(nodo);
        if (tio.rojo) {
		    nodo.padre.rojo = false;
		    tio.rojo = false;
		    NodoRB abuelo = abuelo(nodo);
            abuelo.rojo = true;
		    insercionCaso1(abuelo);
	    } else {
    		insercionCaso4(nodo);
        }
    }

    public void insercionCaso4(NodoRB nodo) {
        NodoRB abuelo = abuelo(nodo);

	    if ((nodo == nodo.padre.hDerecho) && (nodo.padre == abuelo.hIzquierdo)) {
		    rotarIzquierda(nodo.padre);
		    nodo = nodo.hIzquierdo;
	    } else if ((nodo == nodo.padre.hIzquierdo) && (nodo.padre == abuelo.hDerecho)) {
		    rotarDerecha(nodo.padre);
		    nodo = nodo.hDerecho;
	    }
	    insercion_caso5(nodo);
    }

    public void insercionCaso5(NodoRB nodo) {
        NodoRB abuelo = abuelo(nodo);
        nodo.padre.rojo = false;
	    abuelo.rojo = true;
	    if ((nodo == nodo.padre.hIzquierdo) && (nodo.padre == abuelo.hIzquierdo)) {
		    rotarDerecha(abuelo);
	    } else {
    		rotarIzquierda(abuelo);
	    }   
    }


    //Rotaciones
    public void rotarIzquierda(NodoRB nodo) {
        NodoRB aux = nodo.hDerecho;
        nodo.hDerecho = aux.hIzquierdo;
        if (aux.hIzquierdo != nil) {
            aux.hIzquierdo.padre = nodo;
        }

        aux.padre = nodo.padre;
        if (nodo.padre == nil) {
            raiz = aux;
        } else if (nodo == nodo.padre.hIzquierdo) {
            nodo.padre.hIzquierdo = aux;
        } else {
            nodo.padre.hDerecho = aux;
        }
        aux.hIzquierdo = nodo;
        nodo.padre = aux;
    }
    
    public void rotarDerecha(NodoRB nodo) {
        NodoRB aux = nodo.hIzquierdo;
        nodo.hIzquierdo = aux.hDerecho;
        if (aux.hDerecho != nil) {
            aux.hDerecho.padre = nodo;
        }

        aux.padre = nodo.padre;
        if (nodo.padre == nil) {
            raiz = aux;
        } else if (nodo == nodo.padre.hDerecho) {
            nodo.padre.hDerecho = aux;
        } else {
            nodo.padre.hIzquierdo = aux;
        }
        aux.hDerecho = nodo;
        nodo.padre = aux;
    }

    public NodoRB buscar(int valor) {
        return buscarRec(raiz, valor);
    }

    private NodoRB buscarRec(NodoRB nodo, int valor) {
        if (nodo == null || nodo.valor == valor) {
            return nodo;
        }

        if (valor < nodo.valor) {
            return buscarRec(nodo.hIzquierdo, valor);
        } else {
            return buscarRec(nodo.hDerecho, valor);
        }
    }

    public void eliminar(int valor) {
        raiz = eliminarRec(raiz, valor);
    }

    private NodoRB eliminarRec(NodoRB nodo, int valor) {
        if (nodo == null) {
            return null;
        }

        if (valor < nodo.valor) {
            nodo.hIzquierdo = eliminarRec(nodo.hIzquierdo, valor);
        } else if (valor > nodo.valor) {
            nodo.hDerecho = eliminarRec(nodo.hDerecho, valor);
        } else {
            if (nodo.hIzquierdo == null) {
                return nodo.hDerecho;
            } else if (nodo.hDerecho == null) {
                return nodo.hIzquierdo;
            }

            nodo.valor = valorMinimo(nodo.hDerecho);
            nodo.hDerecho = eliminarRec(nodo.hDerecho, nodo.valor);
        }
        return nodo;
    }

    private int valorMinimo(NodoRB nodo) {
        while (nodo.hIzquierdo != null) {
            nodo = nodo.hIzquierdo;
        }
        return nodo.valor;
    }



    public void inorden(NodoRB nodo) {
        if (nodo == null) {
            return;
        }
        inorden(nodo.hIzquierdo);
        System.out.print(nodo.valor + " ");
        inorden(nodo.hDerecho);
    }



    public void preorden(NodoRB nodo) {
        if (nodo == null) {
            return;
        }
        System.out.print(nodo.valor + " ");
        preorden(nodo.hIzquierdo);
        preorden(nodo.hDerecho);
    }


    public void posorden(NodoRB nodo) {
        if (nodo == null) {
            return;
        }
        posorden(nodo.hIzquierdo);
        posorden(nodo.hDerecho);
        System.out.print(nodo.valor + " ");
    }

    public void imprimirArbol() {
        if (raiz == null) {
            System.out.println("El arbol esta vacio.");
            return;
        }

        Queue<NodoRB> queue = new LinkedList<>();
        queue.add(raiz);
        int nivel = 0;

        while (!queue.isEmpty()) {
            int nivelTamano = queue.size();
            System.out.println("Nivel " + nivel + ":");

            while (nivelTamano > 0) {
                NodoRB actual = queue.poll();
                System.out.print("[" + actual.valor + "] ");

                if (actual.hIzquierdo != null) {
                    queue.add(actual.hIzquierdo);
                    System.out.print("(Izq: " + actual.hIzquierdo.valor + ") ");
                }
                if (actual.hDerecho != null) {
                    queue.add(actual.hDerecho);
                    System.out.print("(Der: " + actual.hDerecho.valor + ") ");
                }

                System.out.print(" | ");
                nivelTamano--;
            }

            System.out.println("\n");
            nivel++;
        }
    }
}

