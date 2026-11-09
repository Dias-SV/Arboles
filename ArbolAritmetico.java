import java.util.Stack;

public class ArbolAritmetico {
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

    public static StringBuilder infijaAPostfija(String expresion) {
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

    public static void main(String[] args) {
        System.out.println(infijaAPostfija("1+2-10"));     
        System.out.println(infijaAPostfija("(1+2)*3"));    
        System.out.println(infijaAPostfija("2^3^2"));      
    }
}