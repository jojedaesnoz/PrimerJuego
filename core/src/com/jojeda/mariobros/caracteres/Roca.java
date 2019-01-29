package com.jojeda.mariobros.caracteres;

import com.badlogic.gdx.graphics.Texture;

public class Roca extends Caracter {

    public enum Tipo {
        GRANDE, PEQUENA, MEDIANA
    }

    public Tipo tipo;
    public int vidas;

    public Roca(Texture imagen) {
        super(imagen);
        tipo = Tipo.MEDIANA;
    }

    public Roca(Texture imagen, Tipo tipo) {
        super(imagen);
        this.tipo = tipo;
    }
}
