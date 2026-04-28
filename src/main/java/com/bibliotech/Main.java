package com.bibliotech;

import com.bibliotech.exception.BibliotecaException;
import com.bibliotech.model.Docente;
import com.bibliotech.model.EBook;
import com.bibliotech.model.Estudiante;
import com.bibliotech.model.LibroFisico;
import com.bibliotech.repository.LibroRepositorioImpl;
import com.bibliotech.repository.PrestamoRepositorioImpl;
import com.bibliotech.repository.SocioRepositorioImpl;
import com.bibliotech.service.LibroService;
import com.bibliotech.service.PrestamoService;
import com.bibliotech.service.SocioService;

public class Main {
    public static void main(String[] args) {
        LibroRepositorioImpl libroRepo = new LibroRepositorioImpl();
        SocioRepositorioImpl socioRepo = new SocioRepositorioImpl();
        PrestamoRepositorioImpl prestamoRepo = new PrestamoRepositorioImpl();

        LibroService libroService = new LibroService(libroRepo);
        SocioService socioService = new SocioService(socioRepo);
        PrestamoService prestamoService = new PrestamoService(libroRepo, socioRepo, prestamoRepo);

        try {
            libroService.registrar(new LibroFisico("123-1", "Harry Potter", "JK Rowling", 1997, "Fantasia"));
            libroService.registrar(new EBook("321-2", "El alquimista", "Paulo Coelho", 1988, "Novela", "https://ejemplo.com", "PDF"));
            System.out.println("Libros registrados: " + libroRepo.buscarTodos().size());

            socioService.registrar(new Estudiante(12345678, "Juan Perez", "juan@gmail.com"));
            socioService.registrar(new Docente(87654321, "Maria Lopez", "maria@gmail.com"));
            System.out.println("Socios registrados: " + socioRepo.buscarTodos().size());

            prestamoService.solicitar("123-1", 12345678);
            System.out.println("Prestamo solicitado correctamente");

            long diasRetraso = prestamoService.registrarDevolucion("123-1");
            System.out.println("Devolucion registrada. Dias de retraso: " + diasRetraso);

            System.out.println("Libros de JK Rowling: " + libroService.buscarPorAutor("JK Rowling").size());

        } catch (BibliotecaException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
