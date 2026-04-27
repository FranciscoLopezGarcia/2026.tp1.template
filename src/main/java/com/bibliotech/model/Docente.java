package com.bibliotech.model;

public record Docente (
        int dni,
        String nameComplete,
        String eMail
) implements Socio{
    @Override
    public int limiteLibros(){return 5;}
}
