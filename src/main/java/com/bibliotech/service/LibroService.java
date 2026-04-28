package com.bibliotech.service;

import com.bibliotech.model.Recurso;
import com.bibliotech.repository.LibroRepositorioImpl;

import java.util.ArrayList;
import java.util.List;

public class LibroService {
    private LibroRepositorioImpl repositorio = new LibroRepositorioImpl();

    public void registrar(Recurso libro) {
        repositorio.guardar(libro);
    }

    public List<Recurso> buscarPorAutor(String autor) {
        List<Recurso> resultado = new ArrayList<>();
        for (Recurso recurso : repositorio.buscarTodos()) {
            if (recurso.autor().equals(autor)) {
                resultado.add(recurso);
            }
        }
        return resultado;
    }

    public List<Recurso> buscarPorTitulo(String titulo) {
        List<Recurso> resultado = new ArrayList<>();
        for (Recurso recurso : repositorio.buscarTodos()) {
            if (recurso.titulo().equals(titulo)) {
                resultado.add(recurso);
            }
        }
        return resultado;
    }

    public List<Recurso> buscarPorCategoria(String categoria) {
        List<Recurso> resultado = new ArrayList<>();
        for (Recurso recurso : repositorio.buscarTodos()) {
            if (recurso.categoria().equals(categoria)) {
                resultado.add(recurso);
            }
        }
        return resultado;
    }
}