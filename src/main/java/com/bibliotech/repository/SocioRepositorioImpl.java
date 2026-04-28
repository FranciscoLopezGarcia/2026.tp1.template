package com.bibliotech.repository;

import com.bibliotech.model.Socio;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SocioRepositorioImpl implements Repository<Socio, Integer>{
    private List<Socio> socios =  new ArrayList<>();

    @Override
    public void guardar(Socio socio) {
        socios.add(socio);
    }
    @Override
    public Optional<Socio> buscarPorId(Integer id) {
        for (Socio socio : socios) {
            if (socio.dni() == id) {
                return Optional.of(socio);
            }
        }
        return Optional.empty();
    }
    @Override
    public List<Socio> buscarTodos() {
        return socios;
    }
}