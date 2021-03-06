package me.potaytoprograms.test.desktop;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import me.potaytoprograms.pixi.test.Test;

public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setWindowedMode(800, 480);
		config.setResizable(false);
		config.useVsync(true);
		new Lwjgl3Application(new Test(), config);
	}
}
