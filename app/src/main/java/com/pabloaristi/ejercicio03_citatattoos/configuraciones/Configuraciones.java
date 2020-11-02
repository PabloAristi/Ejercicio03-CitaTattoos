package com.pabloaristi.ejercicio03_citatattoos.configuraciones;

import java.text.SimpleDateFormat;

public class Configuraciones {
    public static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

    //Las dos sirven pero esta es mas elegante
    static {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
    }
}
