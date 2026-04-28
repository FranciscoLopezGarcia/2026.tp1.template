package com.bibliotech.repository;

import com.bibliotech.model.Prestamo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PrestamoRepositorioImpl implements Repository<Prestamo, String> {
    private List<Prestamo> prestamos =  new ArrayList<>();

    @Override
    public void guardar(Prestamo prestamo) { prestamos.add(prestamo); }
    @Override
    public Optional<Prestamo> buscarPorId(String id){
        for (Prestamo prestamo: prestamos){
            if (prestamo.libro().isbn().equals(id)){
                return Optional.of(prestamo);
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Prestamo> buscarTodos() {
        return prestamos;
    }
}

