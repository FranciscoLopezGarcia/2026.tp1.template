package com.bibliotech.model;

public record Estudiante (
        int dni,
        String nameComplete,
        String eMail
) implements Socio{
    @Override
    public int limiteLibros(){return 3;}
}
