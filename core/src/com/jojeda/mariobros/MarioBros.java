package com.jojeda.mariobros;


import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.jojeda.mariobros.caracteres.Cubo;
import com.jojeda.mariobros.caracteres.Gota;

import java.util.ArrayList;
import java.util.Random;

public class MarioBros extends ApplicationAdapter {
	SpriteBatch batch;
	Cubo cubo;
	//	ArrayList<Gota> gotas;
	Random random;

	@Override
	public void create () {
		inicializar();
		batch = new SpriteBatch();
	}

	private void inicializar() {
		random = new Random();
		cubo = new Cubo(new Texture("sprites/bucket.png"));
		cubo.posicion.x = 0;
		cubo.posicion.y = 0;
		cubo.velocidad.x = 10;
		cubo.velocidad.y = 0;
	}

	private void comprobarEntrada() {
		if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
			cubo.posicion.add(cubo.velocidad);

			System.out.println(cubo.velocidad);
		} else if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
			cubo.posicion.sub(cubo.velocidad);

			System.out.println(cubo.velocidad);
		}
	}

	private void comprobarBordes() {
		if (cubo.posicion.x < 0) {
			cubo.posicion.x = 0;
		} else if (cubo.posicion.x > Gdx.graphics.getWidth() - cubo.tamano.x) {
			cubo.posicion.x = Gdx.graphics.getWidth() - cubo.tamano.x;
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
		cubo.pintar(batch);
		batch.end();
	}

	@Override
	public void dispose () {
		batch.dispose();
		cubo.dispose();
	}
}
