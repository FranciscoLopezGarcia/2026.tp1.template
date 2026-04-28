package com.bibliotech.model;

import java.time.LocalDate;

public record Prestamo(
    Recurso libro,
    Socio socio,
    LocalDate fechaInicio,
    LocalDate fechaDevolucion
){}