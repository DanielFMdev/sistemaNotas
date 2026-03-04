package app;

import java.util.Scanner;
import model.Login;
import model.Registro;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while(true){
            System.out.println(("=".repeat(47)));
            System.out.println("Sistema de service.Notas por Usuario");
            System.out.println(("=".repeat(47)));
            System.out.println("1. Registrarse");
            System.out.println("2. Iniciar sesión");
            System.out.println("0. Salir");
            System.out.println(("=".repeat(47)));

            System.out.print("Seleccione una opción: ");
            int opcion = sc.nextInt();

            switch (opcion) {
                case 1:
                    Registro.registro();
                    break;
                case 2:
                    Login.login();
                    break;
                case 0:
                    System.out.println("Saliendo del programa...");
                    sc.close();
                    return;
                default:
                    System.out.println("Opción no válida. Por favor, intente de nuevo.");
            }
        }
    }
}

