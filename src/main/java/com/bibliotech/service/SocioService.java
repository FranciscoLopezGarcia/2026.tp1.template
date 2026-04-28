package com.bibliotech.service;

import com.bibliotech.exception.BibliotecaException;
import com.bibliotech.exception.SocioInvalidoException;
import com.bibliotech.model.Recurso;
import com.bibliotech.model.Socio;
import com.bibliotech.repository.Repository;
import com.bibliotech.repository.SocioRepositorioImpl;

import java.util.ArrayList;
import java.util.List;

public class SocioService {
    private final Repository<Socio, Integer> repositorio;

    public SocioService(Repository<Socio, Integer> repositorio) {
        this.repositorio = repositorio;
    }

    public void registrar(Socio socio) throws BibliotecaException {
        if (repositorio.buscarPorId(socio.dni()).isPresent()){
            throw new SocioInvalidoException("Socio ya existe");
        }
        if (!socio.eMail().contains("@")){
            throw new SocioInvalidoException("Socio email formato invalido");
        }
        repositorio.guardar(socio);
    }

}