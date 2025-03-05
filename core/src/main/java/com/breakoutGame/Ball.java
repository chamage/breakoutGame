package com.breakoutGame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;

public class Ball {
    float x;
    float y;
    float size;
    float xSpeed;
    float ySpeed;
    Color color = Color.WHITE;
    Rectangle colBox;
    Circle colCir;

    public Ball(float x, float y, float size, float xSpeed, float ySpeed){
        this.x = x;
        this.y = y;
        this.size = size;
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
        colBox = new Rectangle();
        colCir = new Circle();
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
        colBox.set(x-size, y-size, size*2, size*2);
        colCir.set(x, y, size);
    }

    public void checkCollision(Paddle paddle){

        if (collidesWith(paddle)) {
            float hitPoint = (x - (paddle.x + paddle.width / 2)) / (paddle.width / 2);
            hitPoint = MathUtils.clamp(hitPoint, -1f, 1f);

            float speed = (float) Math.sqrt(xSpeed * xSpeed + ySpeed * ySpeed);
            float angle = hitPoint * 40f;

            xSpeed = speed * MathUtils.sinDeg(angle);
            ySpeed = speed * MathUtils.cosDeg(angle);

            ySpeed = Math.abs(ySpeed);
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
        //return x + size > paddle.x && x - size < paddle.x + paddle.width && y + size > paddle.y && y - size < paddle.y + paddle.height;
        return Intersector.overlaps(colCir, paddle.colBox);
    }

    private boolean collidesWith(Block block){
        //collision with the whole block
        //return x + size > block.x && x - size < block.x + block.width && y + size > block.y && y - size < block.y + block.height;
        return Intersector.overlaps(colCir, block.colBox);
    }

    public void outOfBoundsFix(){
        if (x - size < 0) {
            x = size; // reposition inside
            xSpeed = Math.abs(xSpeed); // bounce right
        } else if (x + size > Gdx.graphics.getWidth()) {
            x = Gdx.graphics.getWidth() - size; // reposition inside
            xSpeed = -Math.abs(xSpeed); // bounce left
        }
    }

}
