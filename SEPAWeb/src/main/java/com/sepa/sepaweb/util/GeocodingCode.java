package com.sepa.sepaweb.util;

public enum GeocodingCode {
    NULL_PROVINCIA(0, "La provincia no puede ser nula"),
    NULL_CIUDAD(1, "La ciudad no puede ser nula"),
    NULL_DIRECCION(5, "La direccion no puede ser nula"),
    NO_RESULT(2, "No se ha encontrado ningun resultado para esta direccion"),
    MANY_RESULTS(3, "Se han encontrado varios resultados, se mas especifico"),
    PARTIAL_MATCH(4, "Se ha encontrado un resultado pero no es preciso, intenta de otra forma");

    private final int number;
    private final String description;

    GeocodingCode(int number, String description) {
        this.number = number;
        this.description = description;
    }

    public int getNumber() {
        return number;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return String.valueOf(number) + ": " + description;
    }
}
