package service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Scanner;

public class Notas {
    static Scanner sc = new Scanner(System.in);


    public static void crearNotas(String username) {
        System.out.println(username);

        System.out.print("Introduce tu nota: ");
        String nota = sc.nextLine();

        Path carpetaNotas = Path.of("notas", username);
        Path ficheroNotas = carpetaNotas.resolve("service.Notas.txt");

        try {
            Files.createDirectories(carpetaNotas);
            System.out.println("Carpeta creada " + carpetaNotas.toAbsolutePath());
        } catch (IOException e) {
            System.out.println("Error al crear la carpeta: " + e.getMessage());
            return;
        }

        try {
            if (Files.exists(ficheroNotas)) {
                System.out.println("El fichero ya existe. Se añadirá el nueva nota.");
            } else {
                Files.createFile(ficheroNotas);
                System.out.println("Fichero creado " + ficheroNotas.toAbsolutePath());
            }

            System.out.println(" . Ruta relativa: " + ficheroNotas);
            System.out.println(" . Ruta absoluta: " + ficheroNotas.toAbsolutePath());

        } catch (IOException e) {
            System.out.println("Error al crear el fichero: " + e.getMessage());
            return;
        }

        int id;
        try (BufferedWriter writer = Files.newBufferedWriter(ficheroNotas, StandardOpenOption.APPEND)) {
            id = (int) Files.lines(ficheroNotas).count() + 1;
            writer.write(id + " " + nota);
            writer.newLine();
            System.out.println("ID: " + (id) + " -> " + "Nota registrada: " + nota);

        } catch (IOException e) {
            System.out.println("Error al escribir en el fichero: " + e.getMessage());
        }
    }

    public static void listarNota(String username) {
        Path carpetaNotas = Path.of("notas", username);
        Path ficheroNotas = carpetaNotas.resolve("service.Notas.txt");

        try (BufferedReader reader = Files.newBufferedReader(ficheroNotas)) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                System.out.println(linea);
            }
        } catch (IOException e) {
            System.out.println("Error al leer el fichero: " + e.getMessage());
        }
    }

    public static void verNotaPorNumero(String username) {
        System.out.print("Introduce el id de la nota que quieres ver: ");
        int idNota = sc.nextInt();

        Path carpetaNotas = Path.of("notas", username);
        Path ficheroNotas = carpetaNotas.resolve("service.Notas.txt");

        try (BufferedReader reader = Files.newBufferedReader(ficheroNotas)) {
            String idLinea;
            boolean encontrado = false;

            while ((idLinea = reader.readLine()) != null) {
                if (idLinea.startsWith(idNota + " ")) {
                    System.out.println("La nota es: " + idLinea);
                    encontrado = true;
                    break;
                }
            }

            if (!encontrado) {
                System.out.println("No se encontró una nota con el ID: " + idNota);
            }
        } catch (IOException e) {
            System.out.println("Error al leer el fichero: " + e.getMessage());
        }
    }

    public static void eliminarNotaPorNumero(String username) {
        System.out.print("Introduce el id de la nota que quieres eliminar: ");
        int idNota = sc.nextInt();

        Path carpetaNotas = Path.of("notas", username);
        Path ficheroNotas = carpetaNotas.resolve("service.Notas.txt");

        StringBuilder nuevaLinea = new StringBuilder(); // Almacena las líneas que no se eliminarán

        try (BufferedReader reader = Files.newBufferedReader(ficheroNotas)) {
            String idLinea;
            boolean encontrado = false;

            while ((idLinea = reader.readLine()) != null) {
                if (idLinea.startsWith(idNota + " ")) {
                    System.out.println("La nota ha sido eliminada: " + idLinea);
                    encontrado = true;
                    continue;
                }
                nuevaLinea.append(idLinea).append(System.lineSeparator());
            }

            if (!encontrado) {
                System.out.println("No se encontró una nota con el ID: " + idNota);
            }

            Files.writeString(ficheroNotas, nuevaLinea.toString()); // Sobrescribe el fichero con las notas restantes

        } catch (IOException e) {
            System.out.println("Error al leer el fichero: " + e.getMessage());
        }
    }
}
