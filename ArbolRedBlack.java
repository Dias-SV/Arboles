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
    public NodoRB hermano(NodoRB nodo) {
        if (nodo == nodo.padre.hIzquierdo) {
            return nodo.padre.hDerecho;
        } else {
            return nodo.padre.hIzquierdo;
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

    //Busquedas (quedo igual)
    public NodoRB buscar(int valor) {
        return buscarRec(raiz, valor);
    }

    private NodoRB buscarRec(NodoRB nodo, int valor) {
        if (nodo == nil || nodo.valor == valor) {
            return nodo;
        }

        if (valor < nodo.valor) {
            return buscarRec(nodo.hIzquierdo, valor);
        } else {
            return buscarRec(nodo.hDerecho, valor);
        }
    }

    //Eliminacion

    public void eliminar(int valor) { //alch se lo pedi a claude, despues lo checo
        NodoRB z = buscar(valor);
        if (z == nil) {
            return;
        }

        NodoRB y = z;
        if (z.hIzquierdo != nil && z.hDerecho != nil) {
            y = z.hDerecho;
            while (y.hIzquierdo != nil) {
                y = y.hIzquierdo;
            }
            z.valor = y.valor;
        }

        NodoRB hijo = (y.hIzquierdo != nil) ? y.hIzquierdo : y.hDerecho;

        hijo.padre = y.padre;
        if (y.padre == nil) {
            raiz = hijo;
        } else if (y == y.padre.hIzquierdo) {
            y.padre.hIzquierdo = hijo;
        } else {
            y.padre.hDerecho = hijo;
        }

        if (!y.rojo) {
            if (hijo.rojo) {
                hijo.rojo = false;
            } else {
                eliminarCaso1(hijo);
            }
        }

        nil.padre = nil;
    }

    public void eliminarCaso1(NodoRB nodo) {
        if (nodo.padre != nil) {
            eliminarCaso2(nodo);
        }
    }

    public void eliminarCaso2(NodoRB nodo) {
        NodoRB hermano = hermano(nodo);
        if (hermano.rojo) {
            nodo.padre.rojo = true;
            hermano.rojo = false;
            if (nodo == nodo.padre.hIzquierdo) {
                rotarIzquierda(nodo.padre);
            } else {
                rotarDerecha(nodo.padre);
            }
        }
        eliminarCaso3(nodo);
    }

    public void eliminarCaso3(NodoRB nodo) {
        NodoRB hermano = hermano(nodo);
        if (!nodo.padre.rojo && !hermano.rojo && !hermano.hIzquierdo.rojo && !hermano.hDerecho.rojo) {
            hermano.rojo = true;
            eliminarCaso1(nodo.padre);
        } else {
            eliminarCaso4(nodo);
        }
    }

    public void eliminarCaso4(NodoRB nodo) {
        NodoRB hermano = hermano(nodo);
        if (nodo.padre.rojo && !hermano.rojo && !hermano.hIzquierdo.rojo && !hermano.hDerecho.rojo) {
            hermano.rojo = true;
            nodo.padre.rojo = false;
        } else {
            eliminarCaso5(nodo);
        }
    }

    public void eliminarCaso5(NodoRB nodo) {
        NodoRB hermano = hermano(nodo);
        if (nodo == nodo.padre.hIzquierdo && !hermano.rojo && hermano.hIzquierdo.rojo && !hermano.hDerecho.rojo) {
            hermano.rojo = true;
            hermano.hIzquierdo.rojo = false;
            rotarDerecha(hermano);
        } else if (nodo == nodo.padre.hDerecho && !hermano.rojo && !hermano.hIzquierdo.rojo && hermano.hDerecho.rojo) {
            hermano.rojo = true;
            hermano.hDerecho.rojo = false;
            rotarIzquierda(hermano);
        }
        eliminarCaso6(nodo);
    }

    public void eliminarCaso6(NodoRB nodo) {
        NodoRB hermano = hermano(nodo);
        hermano.rojo = nodo.padre.rojo;
        nodo.padre.rojo = false;
        if (nodo == nodo.padre.hIzquierdo) {
            hermano.hDerecho.rojo = false;
            rotarIzquierda(nodo.padre);
        } else {
            hermano.hIzquierdo.rojo = false;
            rotarDerecha(nodo.padre);
        }
    }

   
    private int valorMinimo(NodoRB nodo) {
        while (nodo.hIzquierdo != nil) {
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
        if (nodo == nil) {
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
        if (raiz == nil) {
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

                if (actual.hIzquierdo != nil) {
                    queue.add(actual.hIzquierdo);
                    System.out.print("(Izq: " + actual.hIzquierdo.valor + ") ");
                }
                if (actual.hDerecho != nil) {
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

