package bytesmyth.games.edpg.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import bytesmyth.games.edpg.util.MetaData;
import bytesmyth.games.edpg.util.TestGame;

public class TestLauncher {
	
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = (int)MetaData.VIRTUAL_WIDTH;
		config.height = (int)MetaData.VIRTUAL_HEIGHT;
		config.resizable = MetaData.RESIZEABLE;
		config.title = MetaData.TITLE;
		new LwjglApplication(new TestGame(), config);
	}
	
}
