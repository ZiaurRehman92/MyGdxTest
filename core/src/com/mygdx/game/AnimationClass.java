package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.Animation;

public class AnimationClass {
    private Texture texture;
    private TextureRegion[] walkFrames;
    private Animation walkAnimation;
    private float stateTime = 0f;
    private TextureRegion currentFrame;
    int rows = 5;
    int cols = 6;
    public AnimationClass(Texture texture) {
        this.texture = texture;
        TextureRegion[][] tmp = TextureRegion.split(texture, texture.getWidth() / cols, texture.getHeight() / rows);  // split the sprite sheet up into its 30 different frames
        walkFrames = new TextureRegion[rows * cols];
        int index = 0;
        for(int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                walkFrames[index++] = tmp[i][j];    // Put the 30 frames into a 1D array from the 2d array
            }
        }
        walkAnimation = new Animation(0.06f, walkFrames);  // initialize the animation class
        stateTime = 0f;
    }

    public void draw(Batch batch) {
        stateTime += Gdx.graphics.getDeltaTime();   // stateTime is used to determine which frame to show
        if(stateTime > walkAnimation.getAnimationDuration()) stateTime -= walkAnimation.getAnimationDuration();  // when we reach the end of the animation, reset the stateTime
        currentFrame = (TextureRegion) walkAnimation.getKeyFrame(stateTime);    // Get the current Frame

        batch.draw(currentFrame,(Gdx.graphics.getWidth()/2),
                Gdx.graphics.getHeight()/2 ); // draw the frame
    }
}
