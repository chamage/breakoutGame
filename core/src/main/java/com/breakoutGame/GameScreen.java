package com.breakoutGame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;

/** First screen of the application. Displayed after the application is created. */
public class GameScreen extends ApplicationAdapter {
    ShapeRenderer shape;
    Ball ball;
    Paddle paddle;

    @Override
    public void create() {
        shape = new ShapeRenderer();
        ball = new Ball(250, 250, 25, 12, 5);
        paddle = new Paddle(50, 20, 150, 20);

    }

    @Override
    public void render() {
        ScreenUtils.clear(Color.BLACK);
        //ball.update();
        paddle.update();
        ball.checkCollision(paddle);
        shape.begin(ShapeRenderer.ShapeType.Filled);
        ball.draw(shape);
        paddle.draw(shape);
        shape.end();
    }
}
