package com.breakoutGame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;

public class GameScreen extends ApplicationAdapter {
    ShapeRenderer shape;
    Ball ball;
    Paddle paddle;

    @Override
    public void create() {
        shape = new ShapeRenderer();
        ball = new Ball(250, 250, 25, 9, 4);
        paddle = new Paddle(50, 40, 150, 20);

    }

    @Override
    public void render() {
        ScreenUtils.clear(Color.BLACK);
        ball.update();
        paddle.update();
        ball.checkCollision(paddle);
        shape.begin(ShapeRenderer.ShapeType.Filled);
        ball.draw(shape);
        paddle.draw(shape);
        shape.end();
    }
}
