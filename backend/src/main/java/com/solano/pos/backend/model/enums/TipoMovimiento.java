package com.solano.pos.backend.model.enums;

public enum TipoMovimiento {
    // ENTRADAS (Suman Stock)
    ENTRADA_COMPRA(true),
    ENTRADA_AJUSTE(true),
    ENTRADA_DEVOLUCION(true), // Cliente devuelve producto (entra al stock)

    // SALIDAS (Restan Stock)
    SALIDA_VENTA(false),
    SALIDA_MERMA(false),
    SALIDA_CONSUMO_INTERNO(false),
    SALIDA_AJUSTE(false);

    private final boolean esEntrada;

    TipoMovimiento(boolean esEntrada) {
        this.esEntrada = esEntrada;
    }
}
