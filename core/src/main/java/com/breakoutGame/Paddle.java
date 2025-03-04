package com.breakoutGame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

public class Paddle {
    float x;
    float y;
    float width;
    float height;
    Color color = Color.YELLOW;
    Rectangle colBox;

    public Paddle(float x, float y, float width, float height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        colBox = new Rectangle();
    }

    public void update(){
        if (Gdx.input.getX() > width/2 && Gdx.input.getX() < Gdx.graphics.getWidth() - width/2) {
            x = Gdx.input.getX()-(width/2);
        }
        //y = Gdx.graphics.getHeight()-Gdx.input.getY();
    }

    public void draw(ShapeRenderer shape){
        shape.setColor(color);
        shape.rect(x, y, width, height);
        colBox.set(x, y, width, height);
    }
}
