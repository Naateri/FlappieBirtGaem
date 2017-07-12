package com.fbdpb.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fbdpb.game.FlappieBirtMovile;

/**
 * Created by Alejandro on 13/06/2017.
 */

public class MenuState extends State {
    private Texture background;
    private Texture playBtn;
    public MenuState(GameStateManager gsm) {
        super(gsm);
        cam.setToOrtho(false, FlappieBirtMovile.WIDTH / 2, FlappieBirtMovile.HEIGHT / 2);
        background = new Texture("space_background.jpg");
        playBtn = new Texture("playbutton.png");
    }

    @Override
    public void handleInput() {
        if(Gdx.input.justTouched()){
            gsm.set(new PlayState(gsm));
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
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
}
