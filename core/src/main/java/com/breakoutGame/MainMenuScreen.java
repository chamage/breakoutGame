package com.breakoutGame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class MainMenuScreen implements Screen {

    final BreakoutGame game;
    SpriteBatch batch;
    FreeTypeFontGenerator generator;
    FreeTypeFontGenerator.FreeTypeFontParameter parameter;
    BitmapFont font;
    GlyphLayout layout;
    String title = "Breakout!";

    Stage stage;
    Skin skin;
    TextButton startButton;
    TextButton creditsButton;

    public MainMenuScreen(final BreakoutGame game){
        this.game = game;
        batch = new SpriteBatch();
        generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/PressStart2P-Regular.ttf"));
        parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 42;
        font = generator.generateFont(parameter);
        generator.dispose();
        layout = new GlyphLayout();
        layout.setText(font, title);
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        skin = new Skin(Gdx.files.internal("uiskin.json"));
        startButton = new TextButton("Start Game", skin);
        creditsButton = new TextButton("Credits", skin);

        startButton.setPosition(100, 200);
        creditsButton.setPosition(100, 160);

        stage.addActor(startButton);
        stage.addActor(creditsButton);

        startButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent changeEvent, Actor actor) {
                game.setScreen(new GameScreen(game));
                dispose();
            }
        });

        creditsButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent changeEvent, Actor actor) {
                game.setScreen(new CreditsScreen(game));
                dispose();
            }
        });
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.BLACK);
        float textWidth = layout.width;
        float screenWidth = Gdx.graphics.getWidth();
        float screenHeight = Gdx.graphics.getHeight();
        float x = (screenWidth - textWidth) / 2f;
        float y = screenHeight - 60;

        batch.begin();
        font.draw(batch, title, x, y);
        startButton.setSize(200, 40);
        startButton.setPosition((screenWidth-200)/2f, y-140);
        creditsButton.setSize(200, 40);
        creditsButton.setPosition((screenWidth-200)/2f, y-190);
        batch.end();

        stage.act(delta);
        stage.draw();

    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
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
        stage.dispose();
        skin.dispose();
        batch.dispose();
        font.dispose();
    }
}
