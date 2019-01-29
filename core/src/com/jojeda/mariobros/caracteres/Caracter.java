package com.jojeda.mariobros.caracteres;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Disposable;

public abstract class Caracter implements Disposable {
    public Vector2 posicion;
    public Vector2 velocidad;
    public Vector2 tamano;
    public Texture imagen;

    public Caracter(Texture imagen) {
        this.imagen = imagen;
        posicion = Vector2.Zero;
        velocidad = Vector2.Zero;
        tamano = new Vector2(imagen.getWidth(), imagen.getHeight());
    }

    public void pintar(Batch batch) {
        batch.draw(imagen, posicion.x, posicion.y);
    }

    @Override
    public void dispose() {
        imagen.dispose();
    }
}
