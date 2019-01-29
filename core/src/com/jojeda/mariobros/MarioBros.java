package com.jojeda.mariobros;


import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.jojeda.mariobros.caracteres.Cubo;
import com.jojeda.mariobros.caracteres.Gota;

import java.util.ArrayList;
import java.util.Random;

public class MarioBros extends ApplicationAdapter {
	SpriteBatch batch;
	Cubo cubo;
	ArrayList<Gota> gotas;
	Random random;

	@Override
	public void create () {
		batch = new SpriteBatch();
		random = new Random();
		gotas = new ArrayList<>();
		cubo = new Cubo(new Texture("sprites/bucket.png"));
		cubo.posicion = new Vector2(0, 0);
		cubo.velocidad = new Vector2(10, 0);
	}

	private void comprobarEntrada() {
		if (Gdx.input.isKeyPressed(Input.Keys.RIGHT))
			cubo.posicion.add(cubo.velocidad);
		else if (Gdx.input.isKeyPressed(Input.Keys.LEFT))
			cubo.posicion.sub(cubo.velocidad);
	}

	private void comprobarBordes() {
		if (cubo.posicion.x < 0)
			cubo.posicion.x = 0;
		else if (cubo.posicion.x > Gdx.graphics.getWidth() - cubo.tamano.x)
			cubo.posicion.x = Gdx.graphics.getWidth() - cubo.tamano.x;
	}

	private void generarLluvia() {
		int posicionX = MathUtils.random(0, Gdx.graphics.getWidth() - Gota.ANCHURA);
		Gota gota = new Gota(new Texture("sprites/droplet.png"));
		gota.posicion = new Vector2(posicionX, 0);
		gota.velocidad = new Vector2(0, 5);
		gotas.add(gota);
	}

	private void caerGotas() {
		for (Gota gota : gotas) {
			gota.posicion.add(gota.velocidad);
		}
	}

	@Override
	public void render () {
		// Teclado
		comprobarEntrada();

		// Logica
		comprobarBordes();

		// Pintar
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		for (Gota gota : gotas) {
		    gota.pintar(batch);
        }
		cubo.pintar(batch);
		batch.end();
	}

	@Override
	public void dispose () {
		batch.dispose();
		cubo.dispose();
	}
}
