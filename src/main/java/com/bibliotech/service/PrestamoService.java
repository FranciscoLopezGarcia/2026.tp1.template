package com.bibliotech.service;

import com.bibliotech.exception.BibliotecaException;
import com.bibliotech.exception.LibroNoDisponibleException;
import com.bibliotech.exception.LimiteSuperadoException;
import com.bibliotech.exception.SocioInvalidoException;
import com.bibliotech.model.Prestamo;
import com.bibliotech.model.Recurso;
import com.bibliotech.model.Socio;
import com.bibliotech.repository.Repository;

import java.time.LocalDate;

public class PrestamoService {
    private final Repository<Recurso, String> libroRepository;
    private final Repository<Socio, Integer> socioRepository;
    private final Repository<Prestamo, String> prestamoRepository;

    public PrestamoService(Repository<Recurso, String> libroRepository, Repository<Socio, Integer> socioRepository, Repository<Prestamo, String> prestamoRepository) {
        this.libroRepository = libroRepository;
        this.socioRepository = socioRepository;
        this.prestamoRepository = prestamoRepository;
    }

    public void solicitar(String isbn, int dni) throws BibliotecaException {
        Recurso libro = libroRepository.buscarPorId(isbn).orElseThrow(() -> {
            return new LibroNoDisponibleException("Libro no encontrado");
        });
        Socio socio = socioRepository.buscarPorId(dni).orElseThrow(() -> {
            return new SocioInvalidoException("Socio no encontrado");
        });

        long prestamosActivos = 0;
        for (Prestamo p : prestamoRepository.buscarTodos()) {
            if (p.socio().dni() == dni) {
                prestamosActivos++;
            }
        }
        if (prestamosActivos >= socio.limiteLibros()) {
            throw new LimiteSuperadoException("El socio alcanzo su limite de libros");
        }
        Prestamo prestamo = new Prestamo(libro, socio, LocalDate.now(), LocalDate.now().plusDays(14));
        prestamoRepository.guardar(prestamo);
    }

    public long registrarDevolucion(String isbn) throws BibliotecaException{
        Prestamo prestamo = prestamoRepository.buscarPorId(isbn)
                .orElseThrow(() -> new  LibroNoDisponibleException("Prestamo no encontrado"));
        return calcularRetraso(prestamo);
        }
    public long calcularRetraso(Prestamo prestamo){
        long dias = LocalDate.now().toEpochDay() - prestamo.fechaDevolucion().toEpochDay();
        return dias > 0 ? dias : 0;
        }
}