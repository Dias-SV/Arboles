import java.util.Scanner;

public class Principal {
    static Scanner entrada = new Scanner(System.in);
    public static void main(String[] args) {
        int opcion;
        int valor;

        do {
            System.out.println("\nElige el tipo de árbol");
            System.out.println("1. Árbol AVL");
            System.out.println("2. Árbol Red Black");
            System.out.println("3. Árbol de Expresión Aritmética");
            System.out.println("0. Salir");
            System.out.print("Opcion: ");
            opcion = validacion();

            switch (opcion) {
                case 1:
                    arbolAVL();
                    break;

                case 2:
                    arbolRB();
                    break;

                case 3:
                    arbolArit();
                    break;

                case 0:
                    System.out.println("Cerrando programa...");
                    break;
            
                default:
                    System.out.println("Opción inválida");
                    break;
            }

        } while (opcion != 0);
    }


    public static void arbolAVL() {
        int opcion;
        int valor;
        ArbolAVL arbol = null;

        do {
            System.out.println("\n------ÁRBOL AVL------");
            System.out.println("1. Agregar clave");
            System.out.println("2. Buscar un valor");
            System.out.println("3. Eliminar clave");
            System.out.println("4. Mostrar árbol");
            System.out.println("0. Salir");
            System.out.print("Opción: ");
            opcion = validacion();

            switch (opcion) {
                case 1:
                    if (arbol == null) 
                    {
                       arbol = new ArbolAVL();
                    }
                    System.out.println("Inserte el valor a agregar:");
                    valor = entrada.nextInt();
                    arbol.insertar(valor);
                    break;

                case 2:
                    if (arbol == null) 
                    {
                        System.out.println("El árbol aún no ha sido creado, agrega una clave primero");
                    } 
                    else 
                    {
                        System.out.println("Inserte el valor a buscar:");
                        valor = entrada.nextInt();
                        NodoArbolAVL encontrado = arbol.buscar(valor);
                        if (encontrado != null) 
                        {
                            System.out.println("Valor " + encontrado.valor + " encontrado en el árbol");
                        } 
                        else 
                        {
                            System.out.println("El valor " + valor + " no se encuentra en el árbol");
                        }
                    }
                    break;

                case 3:
                    if (arbol == null) 
                    {
                        System.out.println("El árbol aún no ha sido creado, agrega una clave primero");
                    } 
                    else 
                    {
                        System.out.println("Inserte el valor a eliminar:");
                        valor = entrada.nextInt();
                        arbol.eliminar(valor);
                    }
                    break;

                case 4:
                    if (arbol == null) 
                    {
                        System.out.println("El árbol aún no ha sido creado, agrega una clave primero");
                    } 
                    else 
                    {
                        System.out.println("Estado actual del árbol:");
                        arbol.imprimirArbol();
                    }
                    break;

                case 0:
                    System.out.println("Cerrando programa...");
                    break;

                default:
                    System.out.println("Opcion invalida");
                    break;
            }

        } while (opcion != 0);
    }

    public static void arbolRB(){
        int opcion;
        int valor;
        ArbolRedBlack arbol = null;

        do {
            System.out.println("\n------ÁRBOL RED-BLACK------");
            System.out.println("1. Agregar clave");
            System.out.println("2. Eliminar clave");
            System.out.println("3. Mostrar árbol");
            System.out.println("0. Salir");
            System.out.print("Opción: ");
            opcion = validacion();

            switch (opcion) {
                case 1:
                    if(arbol == null) {
                        arbol = new ArbolRedBlack();
                    }
                    System.out.println("Inserte el valor a agregar");
                    valor = entrada.nextInt();
                    arbol.insertar(valor);
                    break;

                case 2:
                    if(arbol == null)
                    {
                        System.out.println("El árbol aún no ha sido creado. Es necesario que inserte una clave");
                        break;
                    }
                    System.out.println("Inserte el valor a eliminar");
                    valor = entrada.nextInt();
                    arbol.eliminar(valor);
                    break;

                case 3:
                    if(arbol == null) {
                        System.out.println("El árbol aún no ha sido creado. Es necesario que inserte una clave");
                        break;
                    }
                    System.out.println("Estado actual del árbol");
                    arbol.imprimirArbol();
                    break;

                case 0:
                    System.out.println("Cerrando programa...");
                    break;
            
                default:
                    System.out.println("Opción inválida");
                    break;
            }

        } while (opcion != 0);
    }

    public static void arbolArit(){
        int opcion;
        int valor;
        ArbolAritmetico arbol = null;
        String expresion = "";

        do {
            System.out.println("\n------ÁRBOL ARITMÉTICO------");
            System.out.println("1. Ingresar expresión");
            System.out.println("2. Mostrar árbol");
            System.out.println("3. Resolver");
            System.out.println("0. Salir");
            System.out.print("Opción: ");
            opcion = entrada.nextInt();
            entrada.nextLine();

            switch (opcion) {
                case 1:
                    System.out.println("Ingrese la expresión matemática");
                    expresion = entrada.nextLine();
                    arbol = new ArbolAritmetico();
                    arbol.insercion(expresion);
                    break;

                case 2:
                    if(arbol == null) {
                        System.out.println("Es necesario que ingrese primero la expresión aritmética");
                        break;
                    }
                    arbol.impresionArbol();
                    break;

                case 3:
                    if(arbol == null) {
                        System.out.println("Es necesario que ingrese primero la expresión aritmética");
                        break;
                    }
                    String solucion = arbol.resolucion();
                    System.out.println("La solución de la expresión "+expresion+" es: "+solucion);
                    break;

                case 0:
                    System.out.println("Cerrando programa...");
                    break;
            
                default:
                    System.out.println("Opción inválida");
                    break;
            }

        } while (opcion != 0);
    }

     public static int validacion() {
        try {
            int numero = Integer.parseInt(entrada.nextLine());
            if (numero < 0) {
                return -1;
            }
            return numero;
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}