package model;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.StandardOpenOption;
import java.util.Scanner;
import java.nio.file.Files;
import java.nio.file.Path;

public class Registro {

    public static void registro() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Introduce tu email: ");
        String username = sc.nextLine();
        System.out.print("Introduce tu contraseña: ");
        String password = sc.nextLine();

        if (username.isBlank() || password.isBlank()) {
            System.out.println("El email y la contraseña no pueden estar vacíos. Por favor, inténtalo de nuevo.");
            return;
        } else if (!username.contains("@") && !username.contains(".")) {
            System.out.println("El email no es válido. Por favor, introduce un email correcto.");
            return;
        } else if (password.length() < 6) {
            System.out.println("La contraseña debe tener al menos 6 caracteres. Por favor, inténtalo de nuevo.");
            return;
        } else {
            Path carpeta = Path.of("usuarios");
            Path fichero = carpeta.resolve("usuarios.txt");

            try {
                Files.createDirectories(carpeta);
                System.out.println("Carpeta creada " + carpeta.toAbsolutePath());
            } catch (IOException e) {
                System.out.println("Error al crear la carpeta: " + e.getMessage());
                return;
            }

            try {
                if (Files.exists(fichero)) {
                    System.out.println("El fichero ya existe. Se añadirá el nuevo usuario.");
                } else {
                    Files.createFile(fichero);
                    System.out.println("Fichero creado " + fichero.toAbsolutePath());
                }

                System.out.println(" . Ruta relativa: " + fichero);
                System.out.println(" . Ruta absoluta: " + fichero.toAbsolutePath());

            } catch (IOException e) {
                System.out.println("Error al crear el fichero: " + e.getMessage());
                return;
            }

            try (BufferedWriter writer = Files.newBufferedWriter(fichero, StandardOpenOption.APPEND)) {
                writer.write(username.toLowerCase().trim() + " : " + password.trim());
                writer.newLine();
                System.out.println("Usuario registrado: " + username);

            } catch (IOException e) {
                System.out.println("Error al escribir en el fichero: " + e.getMessage());
            }
        }
    }
}
