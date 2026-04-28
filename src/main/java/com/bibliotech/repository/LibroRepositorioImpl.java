package com.bibliotech.repository;

import com.bibliotech.model.Recurso;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LibroRepositorioImpl implements Repository<Recurso, String>{
    private List<Recurso> libros =  new ArrayList<>();

    @Override
    public void guardar(Recurso recurso) {
        libros.add(recurso);
    }
    @Override
    public Optional<Recurso> buscarPorId(String id) {
        for (Recurso recurso : libros) {
            if (recurso.isbn().equals(id)) {
                return Optional.of(recurso);
            }
        }
        return Optional.empty();
    }
    @Override
    public List<Recurso> buscarTodos() {
        return libros;
    }
}