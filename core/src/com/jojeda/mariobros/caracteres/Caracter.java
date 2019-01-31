package com.jojeda.mariobros.caracteres;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Disposable;

public abstract class Caracter implements Disposable {
    public Vector2 posicion;
    public Vector2 velocidad;
    public Vector2 tamano;
    public Texture imagen;
    public Rectangle rect;

    public Caracter(Texture imagen) {
        this.imagen = imagen;
        posicion = new Vector2(0, 0);
        velocidad = new Vector2(0, 0);
        tamano = new Vector2(imagen.getWidth(), imagen.getHeight());
        rect = new Rectangle(posicion.x, posicion.y, tamano.x, tamano.y);
    }

    public void pintar(Batch batch) {
        batch.draw(imagen, posicion.x, posicion.y);
    }

    public void mover(Vector2 movimiento) {
        posicion.add(movimiento);
        rect.x = posicion.x;
        rect.y = posicion.y;
    }

    public void mover() {
        mover(velocidad);
    }

    @Override
    public void dispose() {
        imagen.dispose();
    }
}
