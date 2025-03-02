package com.breakoutGame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

public class Paddle {
    int x;
    int y;
    int width;
    int height;
    Color color = Color.YELLOW;
    Rectangle colBox;

    public Paddle(int x, int y, int width, int height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        colBox = new Rectangle();
    }

    public void update(){
        x = Gdx.input.getX()-(width/2);
        //y = Gdx.graphics.getHeight()-Gdx.input.getY();
    }

    public void draw(ShapeRenderer shape){
        shape.setColor(color);
        shape.rect(x, y, width, height);
        //colBox.set(x, y, width, height);
    }
}
