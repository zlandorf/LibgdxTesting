package fr.zlandorf.wildwest;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import fr.zlandorf.wildwest.bob.Bob;

public class WildWestGame extends ApplicationAdapter {
    SpriteBatch batch;
    Texture img;

    Bob bob;
    
    @Override
    public void create () {
        batch = new SpriteBatch();
        img = new Texture("badlogic.jpg");

        bob = new Bob();
    }

    @Override
    public void render () {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(img, 0, 0);
        batch.end();
        bob.update();
    }
    
    @Override
    public void dispose () {
        batch.dispose();
        img.dispose();
    }
}