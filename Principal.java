import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        int opcion;
        int valor;
        ArbolRedBlack arbol = null;
        Scanner entrada = new Scanner(System.in);

        do {
            System.out.println("Elige el tipo de arbol");
            System.out.println("1. Arbol AVL");
            System.out.println("2. Arbol Red Black");
            System.out.println("3. Arbol de Expresion Aritmetica");
            System.out.println("0. Salir");
            System.out.print("Opcion:");
            opcion = entrada.nextInt();
            entrada.nextLine();

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
                    System.out.println("Opcion invalida");
                    break;
            }

        } while (opcion != 0);
    }


    public static void arbolAVL(){
        int opcion;
        int valor;
        ArbolAVL arbol = null;
        Scanner entrada = new Scanner(System.in);

        do {
            System.out.println("1. Agregar clave");
            System.out.println("2. Buscar un valor");
            System.out.println("3. Eliminar clave");
            System.out.println("4. Mostrar arbol");
            System.out.println("0. Salir");
            System.out.print("Opcion:");
            opcion = entrada.nextInt();
            entrada.nextLine();

            switch (opcion) {
                case 1:
                    System.out.println("Inserte el valor a agregar");
                    valor = entrada.nextInt();
                    break;

                case 2:
                    System.out.println("Inserte el valor a buscar");
                    valor = entrada.nextInt();
                    break;

                case 3:
                    System.out.println("Inserte el valor a eliminar");
                    valor = entrada.nextInt();
                    break;

                case 4:
                    System.out.println("Estado actual del arbol");
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
        ArbolRedBlack arbol = new ArbolRedBlack();
        Scanner entrada = new Scanner(System.in);

        do {
            System.out.println("1. Agregar clave");
            System.out.println("2. Eliminar clave");
            System.out.println("3. Mostrar arbol");
            System.out.println("0. Salir");
            System.out.print("Opcion:");
            opcion = entrada.nextInt();
            entrada.nextLine();

            switch (opcion) {
                case 1:
                    System.out.println("Inserte el valor a agregar");
                    valor = entrada.nextInt();
                    arbol.insertar(valor);
                    break;

                case 2:
                    System.out.println("Inserte el valor a eliminar");
                    valor = entrada.nextInt();
                    arbol.eliminar(valor);
                    break;

                case 3:
                    System.out.println("Estado actual del arbol");
                    arbol.imprimirArbol();
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

    public static void arbolArit(){
        int opcion;
        int valor;
        ArbolAritmetico arbol = null;
        Scanner entrada = new Scanner(System.in);

        do {
            System.out.println("1. Ingresar expresion");
            System.out.println("2. Mostrar arbol");
            System.out.println("3. Resolver");
            System.out.println("0. Salir");
            System.out.print("Opcion:");
            opcion = entrada.nextInt();
            entrada.nextLine();

            switch (opcion) {
                case 1:
                    System.out.println("Ingrese la expresion matematic (no poner espacios)");
                    break;

                case 2:
                    break;

                case 3:
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
}