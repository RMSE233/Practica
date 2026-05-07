package com.biblioteca.ui;

import com.biblioteca.models.*;
import com.biblioteca.services.BibliotecaService;
import java.util.Scanner;

/**
 * Clase principal que proporciona la interfaz de usuario del sistema.
 * Implementa un menú interactivo para gestionar la biblioteca.
 */
public class SistemaGestionBiblioteca {
    private BibliotecaService biblioteca;
    private Scanner scanner;

    /**
     * Constructor que inicializa el sistema y el scanner.
     */
    public SistemaGestionBiblioteca() {
        this.biblioteca = new BibliotecaService();
        this.scanner = new Scanner(System.in);
    }

    /**
     * Inicia el programa y muestra el menú principal.
     */
    public void iniciar() {
        System.out.println("\n╔════════════════════════════════════════════╗");
        System.out.println("║  BIENVENIDO AL SISTEMA DE GESTIÓN DE       ║");
        System.out.println("║           BIBLIOTECA UNIVERSITARIA         ║");
        System.out.println("╚════════════════════════════════════════════╝\n");

        boolean continuar = true;
        while (continuar) {
            mostrarMenuPrincipal();
            int opcion = obtenerOpcion();
            continuar = procesarOpcion(opcion);
        }

        System.out.println("\n╔════════════════════════════════════════════╗");
        System.out.println("║    ¡Gracias por usar nuestro sistema!      ║");
        System.out.println("╚════════════════════════════════════════════╝\n");
        scanner.close();
    }

    /**
     * Muestra el menú principal del sistema.
     */
    private void mostrarMenuPrincipal() {
        System.out.println("\n╔════════════════════════════════════════════╗");
        System.out.println("║           MENÚ PRINCIPAL                   ║");
        System.out.println("╠════════════════════════════════════════════╣");
        System.out.println("║ 1. Registrar nuevo material                ║");
        System.out.println("║ 2. Ver todos los materiales                ║");
        System.out.println("║ 3. Ver materiales disponibles              ║");
        System.out.println("║ 4. Ver materiales prestados                ║");
        System.out.println("║ 5. Buscar material por código              ║");
        System.out.println("║ 6. Buscar material por título              ║");
        System.out.println("║ 7. Prestar material                        ║");
        System.out.println("║ 8. Devolver material                       ║");
        System.out.println("║ 9. Salir                                   ║");
        System.out.println("╚════════════════════════════════════════════╝");
        System.out.print("Seleccione una opción: ");
    }

    /**
     * Procesa la opción seleccionada del menú.
     */
    private boolean procesarOpcion(int opcion) {
        switch (opcion) {
            case 1:
                registrarMaterial();
                return true;
            case 2:
                biblioteca.mostrarTodosMateriales();
                return true;
            case 3:
                biblioteca.mostrarMaterialesDisponibles();
                return true;
            case 4:
                biblioteca.mostrarMaterialesPrestados();
                return true;
            case 5:
                buscarPorCodigo();
                return true;
            case 6:
                buscarPorTitulo();
                return true;
            case 7:
                prestarMaterial();
                return true;
            case 8:
                devolverMaterial();
                return true;
            case 9:
                return false;
            default:
                System.out.println("\n✗ Opción no válida. Intente nuevamente.");
                return true;
        }
    }

    /**
     * Obtiene una opción válida del usuario.
     */
    private int obtenerOpcion() {
        try {
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer
            return opcion;
        } catch (Exception e) {
            scanner.nextLine(); // Limpiar buffer
            System.out.println("\n✗ Entrada no válida. Ingrese un número.");
            return -1;
        }
    }

    /**
     * Permite al usuario registrar un nuevo material.
     */
    private void registrarMaterial() {
        System.out.println("\n╔════════════════════════════════════════════╗");
        System.out.println("║       SELECCIONE TIPO DE MATERIAL          ║");
        System.out.println("╠════════════════════════════════════════════╣");
        System.out.println("║ 1. Libro                                   ║");
        System.out.println("║ 2. Revista                                 ║");
        System.out.println("║ 3. Tesis                                   ║");
        System.out.println("╚════════════════════════════════════════════╝");
        System.out.print("Seleccione el tipo: ");

        int tipo = obtenerOpcion();

        System.out.print("\nIngrese código: ");
        int codigo = obtenerOpcion();

        System.out.print("Ingrese título: ");
        String titulo = scanner.nextLine().trim();

        System.out.print("Ingrese año de publicación (1900-2026): ");
        int anio = obtenerOpcion();

        Material material = null;

        switch (tipo) {
            case 1:
                System.out.print("Ingrese autor: ");
                String autor = scanner.nextLine().trim();
                material = new Libro(codigo, titulo, anio, autor);
                break;
            case 2:
                System.out.print("Ingrese número de edición: ");
                int numeroEdicion = obtenerOpcion();
                material = new Revista(codigo, titulo, anio, numeroEdicion);
                break;
            case 3:
                System.out.print("Ingrese universidad: ");
                String universidad = scanner.nextLine().trim();
                material = new Tesis(codigo, titulo, anio, universidad);
                break;
            default:
                System.out.println("\n✗ Tipo de material no válido.");
                return;
        }

        biblioteca.registrarMaterial(material);
    }

    /**
     * Permite buscar un material por código.
     */
    private void buscarPorCodigo() {
        System.out.print("\nIngrese código a buscar: ");
        int codigo = obtenerOpcion();

        Material material = biblioteca.buscarMaterial(codigo);
        if (material != null) {
            material.mostrarInformacion();
        } else {
            System.out.println("\n✗ No se encontró material con código: " + codigo);
        }
    }

    /**
     * Permite buscar un material por título.
     */
    private void buscarPorTitulo() {
        System.out.print("\nIngrese título a buscar: ");
        String titulo = scanner.nextLine().trim();

        Material material = biblioteca.buscarMaterial(titulo);
        if (material != null) {
            material.mostrarInformacion();
        } else {
            System.out.println("\n✗ No se encontró material con título: " + titulo);
        }
    }

    /**
     * Permite prestar un material.
     */
    private void prestarMaterial() {
        System.out.print("\nIngrese código del material a prestar: ");
        int codigo = obtenerOpcion();
        biblioteca.prestarMaterial(codigo);
    }

    /**
     * Permite devolver un material.
     */
    private void devolverMaterial() {
        System.out.print("\nIngrese código del material a devolver: ");
        int codigo = obtenerOpcion();
        biblioteca.devolverMaterial(codigo);
    }

    /**
     * Método main - Punto de entrada del programa.
     */
    public static void main(String[] args) {
        SistemaGestionBiblioteca sistema = new SistemaGestionBiblioteca();
        sistema.iniciar();
    }
}
