package com.breakoutGame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Ball {
    int x;
    int y;
    int size;
    int xSpeed;
    int ySpeed;
    Color color = Color.WHITE;

    public Ball(int x, int y, int size, int xSpeed, int ySpeed){
        this.x = x;
        this.y = y;
        this.size = size;
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
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
    }

    public void checkCollision(Paddle paddle){
        if (collidesWith(paddle)){
            color = Color.GREEN;
        }
        else {
            color = Color.WHITE;
        }
    }

    private boolean collidesWith(Paddle paddle){
        //ballX + ballWidth > paddleX && ballX < paddleX+paddleWidth && ballY + ballHeight > paddleY
        if (x + size >paddle.x && x < paddle.x + paddle.width && y + size > paddle.y && y < paddle.y + paddle.height) return true;

        return false;
    }

}
