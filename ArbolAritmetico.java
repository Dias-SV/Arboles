
import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class ArbolAritmetico {

    private NodoAA raiz;

    public ArbolAritmetico() {
        this.raiz = null;
    }

    private static int prioridad(char simbolo) {
        switch (simbolo) {
            case '^':
                return 3;
            case '*':
            case '/':
                return 2;
            case '+':
            case '-':
                return 1;
            default:
                return 0; // '('
        }
    }

    private StringBuilder infijaAPostfija(String expresion) {
        Stack<Character> pila = new Stack<>();
        StringBuilder salida = new StringBuilder();

        for (int i = 0; i < expresion.length(); i++) {
            char termino = expresion.charAt(i);

            if (Character.isDigit(termino)) {
                while (i < expresion.length() && Character.isDigit(expresion.charAt(i))) {
                    salida.append(expresion.charAt(i));
                    i++;
                }
                i--;
                salida.append(' ');

            } else if (termino == '(') {
                pila.push(termino);

            } else if (termino == ')') {
                while (!pila.isEmpty() && pila.peek() != '(') {
                    salida.append(pila.pop()).append(' ');
                }
                pila.pop();

            } else if ("+-*/^".indexOf(termino) >= 0) {
                while (!pila.isEmpty() && pila.peek() != '(' && (prioridad(pila.peek()) > prioridad(termino)
                        || (prioridad(pila.peek()) == prioridad(termino) && termino != '^'))) {
                    salida.append(pila.pop()).append(' ');
                }
                pila.push(termino);
            }
        }

        while (!pila.isEmpty()) {
            char termino = pila.pop();
            salida.append(termino).append(' ');
        }
        return salida;
    }

    public void insercion(String expresion) {
        Stack<NodoAA> pila = new Stack<>();
        String operacion = infijaAPostfija(expresion).toString();
        NodoAA nuevo;

        for (int i = 0; i < operacion.length(); i++) {

            char termino = operacion.charAt(i);

            if (termino == ' ') {
                continue;
            }

            if (Character.isDigit(termino)) {
                StringBuilder conjunto = new StringBuilder();
                while (i < operacion.length() && Character.isDigit(operacion.charAt(i))) {
                    conjunto.append(operacion.charAt(i));
                    i++;
                }
                i--;

                nuevo = new NodoAA(conjunto.toString());
                pila.push(nuevo);
            } else if ("+-*/^".indexOf(termino) >= 0) {
                NodoAA derecho = pila.pop();
                NodoAA izquierdo = pila.pop();

                nuevo = new NodoAA(String.valueOf(termino));
                nuevo.setHIzquierdo(izquierdo);
                nuevo.setHDerecho(derecho);

                pila.push(nuevo);

            }
        }

        raiz = pila.pop();
    }

    private String postOrden(NodoAA nodo) {
        if (nodo == null) {
            return "";
        }
        String izquierdo = postOrden(nodo.getHIzquierdo());
        String derecho = postOrden(nodo.getHDerecho());
        return izquierdo + derecho + nodo.getElemento() + " ";
    }

    public String resolucion() {
        DecimalFormat formato = new DecimalFormat("#0.00");
        Stack<Double> pila = new Stack<>();
        String cadena = postOrden(raiz);

        for (int i = 0; i < cadena.length(); i++) {
            char termino = cadena.charAt(i);

            if (Character.isDigit(termino)) {
                StringBuilder conjunto = new StringBuilder();
                while (i < cadena.length() && Character.isDigit(cadena.charAt(i))) {
                    conjunto.append(cadena.charAt(i));
                    i++;
                }
                i--;
                String numero = conjunto.toString();
                pila.push(Double.parseDouble(numero));
            } else if ("+-*/^".indexOf(termino) >= 0) {
                if (!pila.isEmpty()) {
                    double numero2 = pila.pop();
                    double numero1 = pila.pop();

                    switch (termino) {
                        case '^':
                            pila.push(Math.pow(numero1, numero2));
                            break;
                        case '*':
                            pila.push(numero1 * numero2);
                            break;
                        case '/':
                            pila.push(numero1 / numero2);
                            break;
                        case '+':
                            pila.push(numero1 + numero2);
                            break;
                        case '-':
                            pila.push(numero1 - numero2);
                            break;
                    }
                }
            }
        }
        String resultado = formato.format(pila.pop());
        return resultado;
    }

    public void impresionArbol() {
        if (raiz == null) {
            System.out.println("El árbol está vacío");
            return;
        }

        Queue<NodoAA> cola = new LinkedList<>();
        cola.add(raiz);
        int nivel = 0;

        while (!cola.isEmpty()) {
            int nivelTamano = cola.size();

            while (nivelTamano > 0) {
                NodoAA actual = cola.poll();
                System.out.print("[" + actual.getElemento() + "] ");

                if (actual.getHIzquierdo() != null) {
                    cola.add(actual.getHIzquierdo());
                    System.out.print("(Izq: " + actual.getHIzquierdo().getElemento() + ") ");
                }
                if (actual.getHDerecho() != null) {
                    cola.add(actual.getHDerecho());
                    System.out.print("(Der: " + actual.getHDerecho().getElemento() + ") ");
                }

                System.out.print(" | ");
                nivelTamano--;
            }

            System.out.println("\n");
            nivel++;
        }
    }

}
