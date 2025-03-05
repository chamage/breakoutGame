package com.breakoutGame;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

public class Block {
    float x;
    float y;
    float width;
    float height;
    boolean destroyed;
    //Color color;
    Rectangle colBox;
    Texture texture;

    public Block(float x, float y, float width, float height, Texture texture) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        //this.color = color;
        this.texture = texture;
        destroyed = false;
        colBox = new Rectangle();
    }

    public void draw(ShapeRenderer shape){
        //shape.setColor(color);
        shape.rect(x, y, width, height);
        colBox.set(x, y, width, height);
    }

    public void draw(SpriteBatch batch){
        batch.draw(texture, x, y, width, height);
        colBox.set(x, y, width, height);
    }
}
