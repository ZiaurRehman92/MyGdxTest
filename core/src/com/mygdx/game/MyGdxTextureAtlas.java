package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.ScreenUtils;

public class MyGdxTextureAtlas extends ApplicationAdapter implements InputProcessor {
	SpriteBatch batch;
	Texture img;
	TextureAtlas textureAtlas;
	Sprite sprite;
	TextureRegion textureRegion;
	int currentFrame=0;
	int maxFrame=20;
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		textureAtlas =  new TextureAtlas(Gdx.files.internal("SpriteSheet/jet.atlas"));
		textureRegion= textureAtlas.findRegion("0002");
		sprite=new Sprite(textureRegion);
		sprite.setPosition(Gdx.graphics.getWidth()/2 - sprite.getWidth()/2,
				Gdx.graphics.getHeight()/2 - sprite.getHeight()/2);
		sprite.scale(3);
		Gdx.input.setInputProcessor(this);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0,0,0,1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		//batch.draw(img, 0, 0);
		sprite.draw(batch);

		startAnimation();
		batch.end();
	}

	private void startAnimation() {

	}

	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}

	@Override
	public boolean keyDown(int keycode) {

		if(keycode== Input.Keys.UP){
			currentFrame++;
			if(currentFrame>maxFrame){
				currentFrame=1;
			}

			sprite.setRegion(textureAtlas.findRegion(String.format("%04d",currentFrame)));

		}
		if(keycode== Input.Keys.DOWN){
			currentFrame--;
			if(currentFrame<maxFrame){
				currentFrame=1;
			}

			sprite.setRegion(textureAtlas.findRegion(String.format("%04d",currentFrame)));

		}
		return true;
	}

	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
	//	for(int i=0;i<maxFrame;i++) {
			currentFrame++;
			if (currentFrame > maxFrame) {
				currentFrame = 1;
			}

			sprite.setRegion(textureAtlas.findRegion(String.format("%04d", currentFrame)));

		//}
		return true;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {

		for(int i=0;i<maxFrame;i++) {
			currentFrame--;
			if (currentFrame < maxFrame) {
				currentFrame = 1;
			}

			sprite.setRegion(textureAtlas.findRegion(String.format("%04d", currentFrame)));
		}
		return true;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(float amountX, float amountY) {
		return false;
	}
}
