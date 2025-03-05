package com.breakoutGame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class CreditsScreen implements Screen {

    final BreakoutGame game;
    private SpriteBatch batch;
    private BitmapFont font;

    private String creditName = "me";

    private String[] creditLines;

    private float creditsOffsetY;
    private float scrollSpeed = 50f;
    private float lineSpacing = 40f;

    public CreditsScreen(final BreakoutGame game) {
        this.game = game;
        batch = new SpriteBatch();
        font = new BitmapFont();

        creditLines = new String[] {
            "Extra credit to " + creditName,
            "Special thanks to " + creditName,
            "Desing by " + creditName,
            "Sound by " + creditName,
            "Music by " + creditName,
            "Art by " + creditName,
            "Programming by " + creditName,
            "Breakout! by " + creditName
        };

        creditsOffsetY = -Gdx.graphics.getHeight() / 2f;
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.BLACK);

        creditsOffsetY += scrollSpeed * delta;

        batch.begin();
        GlyphLayout layout = new GlyphLayout();
        for (int i = 0; i < creditLines.length; i++) {
            String line = creditLines[i];
            layout.setText(font, line);
            float textWidth = layout.width;
            float x = (Gdx.graphics.getWidth() - textWidth) / 2f;
            float y = creditsOffsetY + i * lineSpacing;
            font.draw(batch, line, x, y);
        }
        batch.end();

        if (Gdx.input.justTouched()) {
            game.setScreen(new MainMenuScreen(game));
            dispose();
        }
    }

    @Override
    public void show() {

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
        batch.dispose();
        font.dispose();
    }
}
