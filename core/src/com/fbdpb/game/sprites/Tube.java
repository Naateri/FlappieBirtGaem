package com.fbdpb.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g3d.particles.influencers.ColorInfluencer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

/**
 * Created by Alejandro on 13/06/2017.
 */

public class Tube {
    public static final int TUBE_WIDTH = 52;
    private static final int SEPARATION = 130;
    private static final int GAP = 100;
    private static final int LOWEST_OPENING = 120;
    private Texture topTube, bottomTube;
    private Rectangle boundsTop, boundsBot;
    private Vector2 posTopTube, posBotTube;

    private Random rand;
    public Tube(float x){
        topTube = new Texture("building_bot.png");
        bottomTube = new Texture("building_top.png");
        rand = new Random();
        posTopTube = new Vector2(x, rand.nextInt(SEPARATION) + GAP + LOWEST_OPENING);
        posBotTube = new Vector2(x, posTopTube.y - GAP - bottomTube.getHeight());
        boundsTop = new Rectangle(posTopTube.x, posTopTube.y, topTube.getWidth(), topTube.getHeight());
        boundsBot = new Rectangle(posBotTube.x, posBotTube.y, bottomTube.getWidth(), bottomTube.getHeight());
    }
    public Texture getTopTube() {
        return topTube;
    }

    public Texture getBottomTube() {
        return bottomTube;
    }

    public Vector2 getPosTopTube() {
        return posTopTube;
    }

    public Vector2 getPosBotTube() {
        return posBotTube;
    }
    public void reposition(float x){
        posTopTube.set(x,rand.nextInt(SEPARATION) + GAP + LOWEST_OPENING);
        posBotTube.set(x, posTopTube.y - GAP - bottomTube.getHeight());
        boundsTop.setPosition(posTopTube.x, posTopTube.y);
        boundsBot.setPosition(posBotTube.x, posBotTube.y);
    }
    public boolean collides(Rectangle player){
        return player.overlaps(boundsTop) || player.overlaps(boundsBot);
    }
    public void dispose(){
        topTube.dispose();
        bottomTube.dispose();
    }
}
