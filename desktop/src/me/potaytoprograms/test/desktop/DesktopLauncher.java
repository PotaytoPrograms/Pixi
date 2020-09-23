package me.potaytoprograms.test.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import me.potaytoprograms.pixi.test.Test;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 800;
		config.height = 480;
		config.resizable = false;
		config.vSyncEnabled = true;
		new LwjglApplication(new Test(), config);
	}
}
