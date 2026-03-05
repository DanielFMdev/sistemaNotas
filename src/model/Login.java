package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;
import service.Notas;

public class Login {

    public static void login() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Introduce tu email: ");
        String username = sc.nextLine();
        System.out.print("Introduce tu contraseña: ");
        String password = sc.nextLine();

        Path carpeta = Path.of("usuarios");
        Path fichero = carpeta.resolve("usuarios.txt");

        try (BufferedReader reader = Files.newBufferedReader(fichero)) {
            String linea;
            boolean encontrado = false;

            while ((linea = reader.readLine()) != null) {
                if (linea.equals(username.toLowerCase().trim() + " : " + password.trim())) {
                    encontrado = true;
                    break;
                }
            }

            if (encontrado) {
                System.out.println("Inicio de sesión exitoso. ¡Bienvenido, " + username + "!");

                while (true) {
                    System.out.println(("=".repeat(47)));
                    System.out.println("Menú de Usuario");
                    System.out.println(("=".repeat(47)));
                    System.out.println("1. Crear nota");
                    System.out.println("2. Listar nota");
                    System.out.println("3. Ver nota por número");
                    System.out.println("4. Eliminar nota por número");
                    System.out.println("0. Cerrar sesión");
                    System.out.println(("=".repeat(47)));

                    System.out.print("Seleccione una opción: ");
                    int opcion = sc.nextInt();
                    sc.nextLine();

                    switch (opcion) {
                        case 1:
                            Notas.crearNotas(username);
                            break;
                        case 2:
                            Notas.listarNota(username);
                            break;
                        case 3:
                            Notas.verNotaPorNumero(username);
                            break;
                        case 4:
                            Notas.eliminarNotaPorNumero(username);
                            break;
                        case 0:
                            System.out.println("Cerrando sesión. ¡Hasta luego, " + username + "!");
                            return;
                        default:
                            System.out.println("Opción no válida. Por favor, intente de nuevo.");
                    }
                }
            } else {
                System.out.println("Nombre de usuario o contraseña incorrectos. Por favor, inténtalo de nuevo.");
            }
        } catch (IOException e) {
            System.out.println("Error al leer el fichero: " + e.getMessage());
        }
    }
}
