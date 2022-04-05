package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;


public class MyGdxCustomAnimation extends ApplicationAdapter {
    SpriteBatch batch;
    Texture img;
    AnimationClass animationClass;
    TextureAtlas textureAtlas;
    Animation animation;
    float timePassed=0;
    @Override
    public void create () {

        batch = new SpriteBatch();
        textureAtlas =  new TextureAtlas(Gdx.files.internal("SpriteSheet/walkingboy.atlas"));
        animation=new Animation(1/10f,textureAtlas.getRegions());
        //animationClass = new AnimationClass(new Texture(Gdx.files.internal("walkingman.png")));

    }

    @Override
    public void render () {

        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        timePassed+=Gdx.graphics.getDeltaTime();
        batch.draw((TextureRegion) animation.getKeyFrame(timePassed,true),500,500);
      //  animationClass.draw(batch);
        batch.end();
    }

    @Override
    public void dispose () {
        batch.dispose();
        img.dispose();
    }
}

