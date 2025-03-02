package com.breakoutGame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;

public class Ball {
    int x;
    int y;
    int size;
    int xSpeed;
    int ySpeed;
    Color color = Color.WHITE;
    //Rectangle colBox;

    public Ball(int x, int y, int size, int xSpeed, int ySpeed){
        this.x = x;
        this.y = y;
        this.size = size;
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
        //colBox = new Rectangle();
    }

    public void update(){
        x += xSpeed;
        y += ySpeed;

        if (x > Gdx.graphics.getWidth() - size || x < size){
            xSpeed = -xSpeed;
        }
        if (y > Gdx.graphics.getHeight() - size || y < size){
            ySpeed = -ySpeed;
        }
    }

    public void draw(ShapeRenderer shape){
        shape.setColor(color);
        shape.circle(x, y, size);
        //colBox.set(x-size, y-size, size*2, size*2);
    }

    public void checkCollision(Paddle paddle){
        if (collidesWith(paddle)){
            ySpeed = -ySpeed;
        }
    }

    public void checkCollision(Block block){
        if (collidesWith(block)){
            ySpeed = -ySpeed;
            block.destroyed = true;
        }
    }

    private boolean collidesWith(Paddle paddle){
        //collision with the paddle
        if (x + size > paddle.x && x - size < paddle.x + paddle.width && y + size > paddle.y && y - size < paddle.y + paddle.height) return true;
        //if (paddle.colBox.overlaps(colBox)) return true;
        return false;

    }

    private boolean collidesWith(Block block){
        //collision with the whole block
        if (x + size > block.x && x - size < block.x + block.width && y + size > block.y && y - size < block.y + block.height) return true;

        return false;
    }

}
