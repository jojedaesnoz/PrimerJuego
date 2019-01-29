package com.jojeda.mariobros.caracteres;

import com.badlogic.gdx.graphics.Texture;


public class Gota extends Caracter {

    public Gota(Texture texture) {
        super(texture);
        tipo = Tipo.NORMAL;
    }

    public enum Tipo {
        NORMAL, MUNICION
    }
    public Tipo tipo;
    public static final int ANCHURA = 64;

    public Gota(Texture imagen, Tipo tipo) {
        super(imagen);
        this.tipo = tipo;
    }

    public Gota(Texture imagen, int posicionY) {
        super(imagen);
        posicion.y = posicionY;
        tipo = Tipo.NORMAL;
    }
}
