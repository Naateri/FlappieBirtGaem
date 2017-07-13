package com.fbdpb.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.Input.TextInputListener;
import com.fbdpb.game.FlappieBirtMovile;

/**
 * Created by Alejandro on 13/06/2017.
 */

public class MenuState extends State implements TextInputListener{
    private Texture background;
    private Texture playBtn;
    private String name = text;
    //public String text;
    public MenuState(GameStateManager gsm) {
        super(gsm);
        cam.setToOrtho(false, FlappieBirtMovile.WIDTH / 2, FlappieBirtMovile.HEIGHT / 2);
        background = new Texture("space_background.jpg");
        playBtn = new Texture("playbutton.png");
    }


    @Override
    public void handleInput() {
        if(Gdx.input.justTouched()) {
            Gdx.input.getTextInput(this, "Enter your name", name, "ayy");
        }
    }

    @Override
    public void update(float dt) {
        handleInput(); Gdx.app.log("text", text);
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        //sb.draw(background, 0, 0, FlappieBirtMovile.WIDTH, FlappieBirtMovile.HEIGHT);
        //sb.draw(playBtn, (FlappieBirtMovile.WIDTH / 2) - (playBtn.getWidth() / 2), FlappieBirtMovile.HEIGHT / 3);
        sb.draw(background, 0, 0);
        sb.draw(playBtn, cam.position.x - playBtn.getWidth()/2, cam.position.y);
        sb.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        playBtn.dispose();
        //System.out.println("Menu State Disposed LOL");
    }

    @Override
    public void input(String text) {
        this.text = text;
        gsm.set(new PlayState(gsm));
    }

    @Override
    public void canceled() {

    }
}
