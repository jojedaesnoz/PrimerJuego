package com.jojeda.mariobros.caracteres;

import com.badlogic.gdx.graphics.Texture;


public class Gota extends Caracter {

    public enum Tipo {
        NORMAL, MUNICION
    }
    public Tipo tipo;

    public Gota(Texture imagen, Tipo tipo) {
        super(imagen);
        this.tipo = tipo;
    }

    public Gota(Texture imagen) {
        super(imagen);
        tipo = Tipo.NORMAL;
    }
}
