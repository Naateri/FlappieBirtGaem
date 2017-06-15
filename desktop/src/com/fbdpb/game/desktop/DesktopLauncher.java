package com.fbdpb.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.fbdpb.game.FlappieBirtMovile;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = FlappieBirtMovile.WIDTH;
		config.height = FlappieBirtMovile.HEIGHT;
		config.title = FlappieBirtMovile.TTTLE;
		new LwjglApplication(new FlappieBirtMovile(), config);
	}
}
