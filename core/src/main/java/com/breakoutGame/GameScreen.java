package com.breakoutGame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.ArrayList;

public class GameScreen implements Screen {

    final BreakoutGame game;
    ShapeRenderer shape;
    Ball ball;
    Paddle paddle;
    ArrayList<Block> blocks = new ArrayList<>();

    public GameScreen(final BreakoutGame game){
        this.game = game;
    }

    @Override
    public void show() {
        shape = new ShapeRenderer();
        ball = new Ball(350, 50, 6, 9, 4);
        paddle = new Paddle(50, 40, 150, 20);
        createBlocks();
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.BLACK);
        ball.update();
        ball.outOfBoundsFix();
        paddle.update();
        ball.checkCollision(paddle);
        shape.begin(ShapeRenderer.ShapeType.Filled);
        for (Block block : blocks){
            block.draw(shape);
            ball.checkCollision(block);
        }
        for (int i = 0; i < blocks.size(); i++){
            Block block = blocks.get(i);
            if (block.destroyed){
                blocks.remove(block);
                i--;
            }
        }
        ball.draw(shape);
        paddle.draw(shape);
        shape.end();
        loseCondition();
        endGame();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        shape.dispose();
    }

    private void createBlocks(){
        Color[] colors = {Color.BLUE, Color.GREEN, Color.RED};
        int blockWidth = 60;
        int blockHeight = 20;
        int rowIndex = 0;
        for (int y = Gdx.graphics.getHeight()/2; y < Gdx.graphics.getHeight(); y += blockHeight + 10){
            for (int x = 4; x < Gdx.graphics.getWidth(); x += blockWidth + 7){
                Color color = colors[rowIndex % colors.length];
                blocks.add(new Block(x, y, blockWidth, blockHeight, color));
                rowIndex++;
            }
        }
    }

    private  void loseCondition(){
        if (ball.y < paddle.y){
            restart();
        }
    }

    private void restart(){
        for (Block block : blocks){
            block.destroyed = true;
        }
        blocks.clear();

        ball.y = paddle.y + paddle.height * 2 + 10;
        ball.ySpeed = Math.abs(ball.ySpeed);
        createBlocks();
    }

    private void endGame(){
        if (blocks.isEmpty()){
            Gdx.app.exit();
        }
    }
}
