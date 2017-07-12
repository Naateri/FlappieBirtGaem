package com.fbdpb.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.fbdpb.game.FlappieBirtMovile;
import com.fbdpb.game.sprites.Bird;
import com.fbdpb.game.sprites.Tube;

/**
 * Created by Alejandro on 13/06/2017.
 */

public class PlayState extends State {
    /////

    private String scoreName;
    BitmapFont testing;
    /////
    private static int bestScore = 0;
    private String bestScoreStr;
    BitmapFont pbFont;

    private static final int TUBE_SPACING = 125;
    private static final int TUBE_COUNT = 4;
    private static final int GROUND_Y_OFFSET = -50;
    private Bird bird;
    private Texture background;
    private Texture ground;
    private Vector2 groundPos1, groundPos2;
    private Array<Tube> tubes;
    public PlayState(GameStateManager gsm) {
        super(gsm);
        bird = new Bird(50,300);
        cam.setToOrtho(false, FlappieBirtMovile.WIDTH / 2, FlappieBirtMovile.HEIGHT / 2);
        background = new Texture("city_background.gif");
        ground = new Texture("groundi.png");
        groundPos1 = new Vector2(cam.position.x - cam.viewportWidth / 2, GROUND_Y_OFFSET);
        groundPos2 = new Vector2((cam.position.x - cam.viewportWidth / 2) + ground.getWidth(), GROUND_Y_OFFSET);
        tubes = new Array<Tube>();
        for(int i = 1; i <= TUBE_COUNT; i++){
            tubes.add(new Tube(i * (TUBE_SPACING + Tube.TUBE_WIDTH)));
        }
        score = 0;
        scoreName = "score: 0";
        bestScoreStr = "personal best: " + bestScore;
        testing = new BitmapFont();
        pbFont = new BitmapFont();
    }

    @Override
    protected void handleInput() {
        if(Gdx.input.justTouched()){
            bird.jump();
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
        updateGround();
        bird.update(dt);
        cam.position.x = bird.getPosition().x + 80;
        for(int i = 0; i < tubes.size; i++){
            Tube tube = tubes.get(i);
            if(cam.position.x - (cam.viewportWidth/2) > tube.getPosTopTube().x + tube.getTopTube().getWidth()){
                tube.reposition(tube.getPosTopTube().x + ((Tube.TUBE_WIDTH + TUBE_SPACING) * TUBE_COUNT));
                score++;
                scoreName = "score: " + score;
            }
        }
        if (bird.getPosition().y <= ground.getHeight() + GROUND_Y_OFFSET){ //si choca con el suelo
            if (score > bestScore)
                bestScore = score;
            gsm.set(new MenuState(gsm)); //Menu
        }
        cam.update();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(background, cam.position.x - (cam.viewportWidth / 2), 0);
        sb.draw(bird.getTexture(), bird.getPosition().x, bird.getPosition().y);
        for(Tube tube : tubes){
            sb.draw(tube.getTopTube(), tube.getPosTopTube().x, tube.getPosTopTube().y);
            sb.draw(tube.getBottomTube(), tube.getPosBotTube().x, tube.getPosBotTube().y);
            if (tube.collides(bird.getBounds())){ //si choca con un tubito
                if (score > bestScore)
                    bestScore = score;
                gsm.set(new MenuState(gsm));
            }
        }
        sb.draw(ground, groundPos1.x, groundPos1.y);
        sb.draw(ground, groundPos2.x, groundPos2.y);
        testing.setColor(1.0f, 1.0f, 1.0f, 1.0f);
        testing.draw(sb, scoreName, bird.getPosition().x - 20, bird.getPosition().y);
        pbFont.setColor(1.0f, 1.0f, 1.0f, 1.0f);
        pbFont.draw(sb, bestScoreStr, bird.getPosition().x+96, 390);
        sb.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        //bird.dispose();
        /*for (Tube tube : tubes){
            tube.dispose();
        }*/
        //System.out.println("Play State Disposed LOL");
    }

    private void updateGround(){
        if(cam.position.x - (cam.viewportWidth / 2) > groundPos1.x + ground.getWidth()){
            groundPos1.add(ground.getWidth() * 2, 0);
        }
        if(cam.position.x - (cam.viewportWidth / 2) > groundPos2.x + ground.getWidth()){
            groundPos2.add(ground.getWidth() * 2, 0);
        }
    }
}
