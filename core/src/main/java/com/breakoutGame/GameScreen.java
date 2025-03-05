package com.breakoutGame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.ArrayList;

public class GameScreen implements Screen {

    final BreakoutGame game;
    ShapeRenderer shape;
    SpriteBatch batch;
    Ball ball;
    Paddle paddle;
    ArrayList<Block> blocks = new ArrayList<>();

    Texture[] blockTextures;

    public GameScreen(final BreakoutGame game){
        this.game = game;
    }

    @Override
    public void show() {
        shape = new ShapeRenderer();
        batch = new SpriteBatch();
        ball = new Ball(350, 50, 6, 9, 4);
        paddle = new Paddle(50, 40, 150, 20);

        blockTextures = new Texture[3];
        blockTextures[0] = new Texture(Gdx.files.internal("textures/blueBlock.png"));
        blockTextures[1] = new Texture(Gdx.files.internal("textures/greenBlock.png"));
        blockTextures[2] = new Texture(Gdx.files.internal("textures/redBlock.png"));

        createBlocks();
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.BLACK);

        ball.update();
        ball.outOfBoundsFix();
        paddle.update();
        ball.checkCollision(paddle);

        batch.begin();
        for (Block block : blocks){
            block.draw(batch);
            ball.checkCollision(block);
        }
        batch.end();

        shape.begin(ShapeRenderer.ShapeType.Filled);
        ball.draw(shape);
        paddle.draw(shape);
        shape.end();

        for (int i = 0; i < blocks.size(); i++){
            Block block = blocks.get(i);
            if (block.destroyed){
                blocks.remove(block);
                i--;
            }
        }

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
        batch.dispose();
        for(Texture txt : blockTextures){
            txt.dispose();
        }
    }

    private void createBlocks(){
        //Color[] colors = {Color.BLUE, Color.GREEN, Color.RED};
        int blockWidth = 60;
        int blockHeight = 20;
        int rowIndex = 0;
        for (int y = Gdx.graphics.getHeight()/2; y < Gdx.graphics.getHeight(); y += blockHeight + 7){
            for (int x = 4; x < Gdx.graphics.getWidth(); x += blockWidth + 7){
                //Color color = colors[rowIndex % colors.length];
                Texture texture = blockTextures[rowIndex % blockTextures.length];
                blocks.add(new Block(x, y, blockWidth, blockHeight, texture));
                rowIndex++;
            }
        }
    }

    private  void loseCondition(){
        if (ball.y < paddle.y){
            //restart();
            game.setScreen(new GameLost(game));
            dispose();
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
            //Gdx.app.exit();
            game.setScreen(new GameWon(game));
            dispose();
        }
    }
}
