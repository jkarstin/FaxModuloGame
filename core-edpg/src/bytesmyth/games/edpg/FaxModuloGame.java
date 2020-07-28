package bytesmyth.games.edpg;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;

import bytesmyth.games.edpg.screen.StartScreen;
import bytesmyth.games.edpg.util.Assets;

public class FaxModuloGame extends Game {
	
	public static FaxModuloGame EDP;
	
	public static void setActiveScreen(Screen screen) {
		FaxModuloGame.EDP.setScreen(screen);
	}
	
	public static void restartScreen() {
		try {
			FaxModuloGame.EDP.setScreen(FaxModuloGame.EDP.getScreen().getClass().newInstance());
		} catch (Exception e) {}
	}
	
	public FaxModuloGame() {
		FaxModuloGame.EDP = this;
	}
	
	@Override
	public void create () {
		new Assets();
		
		FaxModuloGame.setActiveScreen(new StartScreen());
	}
	
}
