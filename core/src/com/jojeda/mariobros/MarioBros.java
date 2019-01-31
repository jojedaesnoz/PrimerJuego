package com.jojeda.mariobros;


import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.Timer;
import com.jojeda.mariobros.caracteres.Caracter;
import com.jojeda.mariobros.caracteres.Cubo;
import com.jojeda.mariobros.caracteres.Gota;
import com.jojeda.mariobros.caracteres.Roca;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class MarioBros extends ApplicationAdapter {
    SpriteBatch batch;
    Cubo cubo;
    ArrayList<Gota> gotas;
    ArrayList<Roca> rocas;
    Array<Roca> rocasArray;
    Random random;
    long tiempoEntreGotas = 500;
    long tiempoUltimaGota;

    @Override
    public void create() {
        batch = new SpriteBatch();
        random = new Random();
        gotas = new ArrayList<>();
        rocas = new ArrayList<>();
        rocasArray = new Array<>();

        // Poner la musica de fondo
        Music music = Gdx.audio.newMusic(new FileHandle("sounds/undertreeinrain.mp3"));
        music.setLooping(true);
        music.play();

        cubo = new Cubo(new Texture("sprites/bucket.png"));
        cubo.posicion = new Vector2((float) Gdx.graphics.getWidth() / 2, 0);
        cubo.velocidad = new Vector2(10, 0);
    }

    private void generarLluviaConTimer() {
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                generarLluvia();
            }
        }, 2, 1);
    }

    private void comprobarTeclado() {
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT))
            cubo.mover(new Vector2(cubo.velocidad.x, cubo.velocidad.y));
        else if (Gdx.input.isKeyPressed(Input.Keys.LEFT))
            cubo.mover(new Vector2(- cubo.velocidad.x, cubo.velocidad.y));
    }

    private void comprobarBordes() {
        if (cubo.posicion.x < 0)
            cubo.posicion.x = 0;
        else if (cubo.posicion.x > Gdx.graphics.getWidth() - cubo.tamano.x)
            cubo.posicion.x = Gdx.graphics.getWidth() - cubo.tamano.x;
    }

    private void moverNPCS(Iterable<? extends Caracter> caracteres) {
        Iterator<? extends Caracter> iterator = caracteres.iterator();
        while (iterator.hasNext()) {
            Caracter npc = iterator.next();
            npc.mover();

            if (npc.rect.overlaps(cubo.rect)) {
                // Desaparece si choca con el cubo
                iterator.remove();
                Sound sound = Gdx.audio.newSound(new FileHandle("sounds/waterdrop.wav"));
                sound.play();

            } else if ((npc.posicion.y + npc.tamano.y) < 0) {
                // Desaparece si llega al suelo
                iterator.remove();
            }
        }
    }

    private void pintarCaracteres(Iterable<? extends Caracter> caracteres, Batch batch) {
        for (Caracter caracter : caracteres) {
            caracter.pintar(batch);
        }
    }

    @Override
    public void render() {
        // Teclado
        comprobarTeclado();

        // Logica
        comprobarBordes();
        comprobarColisiones();
//		tirarGotas();
        generarLluviaConTimeUtils();
        moverNPCS(gotas);
        moverNPCS(rocas);

        // Pintar
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        pintarCaracteres(gotas, batch);
        pintarCaracteres(rocas, batch);
        cubo.pintar(batch);
        batch.end();
    }

    private void comprobarColisiones() {

    }

    private void generarLluviaConTimeUtils() {
		if (TimeUtils.timeSinceMillis(tiempoUltimaGota) > tiempoEntreGotas) {
		    generarLluvia();
		    tiempoUltimaGota = TimeUtils.millis();
        }
    }

    private void generarLluvia() {
        // 10% de posibilidades de tirar roca
        Caracter caracter;
        if (MathUtils.random(1, 10) == 1) {
            caracter = new Roca(new Texture("sprites/rock.png"));
            rocas.add((Roca) caracter);
        } else {
            caracter = new Gota(new Texture("sprites/droplet.png"));
            gotas.add((Gota) caracter);
        }

        int posicionX = (int) MathUtils.random(0, Gdx.graphics.getWidth() - caracter.tamano.x);
        caracter.posicion = new Vector2(posicionX, Gdx.graphics.getHeight());
        caracter.velocidad = new Vector2(0, -5);

//        int posicionX = MathUtils.random(0, Gdx.graphics.getWidth() - Gota.ANCHURA);
//        Gota gota = new Gota(new Texture("sprites/droplet.png"));
//        gota.posicion = new Vector2(posicionX, Gdx.graphics.getHeight());
//        gota.velocidad = new Vector2(0, -5);
//        gotas.add(gota);
    }

    @Override
    public void dispose() {
        batch.dispose();
        cubo.dispose();
    }
}
