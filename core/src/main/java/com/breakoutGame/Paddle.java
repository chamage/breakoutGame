package com.breakoutGame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Paddle {
    int x;
    int y;
    int width;
    int height;

    public Paddle(int x, int y, int width, int height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void update(){
        x = Gdx.input.getX()-(width/2);
        //y = Gdx.graphics.getHeight()-Gdx.input.getY();
    }

    public void draw(ShapeRenderer shape){
        shape.rect(x, y, width, height);
    }
}
