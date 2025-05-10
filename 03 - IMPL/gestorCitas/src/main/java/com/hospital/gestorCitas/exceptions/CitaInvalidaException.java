package com.hospital.gestorCitas.exceptions;

public class CitaInvalidaException extends RuntimeException {
    private final int codigo;

    public CitaInvalidaException(String mensaje, int codigo) {
        super(mensaje);
        this.codigo = codigo;
    }

    public int getCodigo() {
        return codigo;
    }
}