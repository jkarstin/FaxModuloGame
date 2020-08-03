package bytesmyth.games.edpg;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;

import bytesmyth.games.edpg.screen.StartScreen;
import bytesmyth.games.edpg.util.Assets;

public class FaxModuloGame extends Game {
	
	private static FaxModuloGame FMG;
	
	public static void setActiveScreen(Screen screen) {
		FaxModuloGame.FMG.setScreen(screen);
	}
	
	public static void restartScreen() {
		try {
			FaxModuloGame.FMG.setScreen(FaxModuloGame.FMG.getScreen().getClass().newInstance());
		} catch (Exception e) {}
	}
	
	public FaxModuloGame() {
		FaxModuloGame.FMG = this;
	}
	
	@Override
	public void create () {
		new Assets();
		
		FaxModuloGame.setActiveScreen(new StartScreen());
	}
	
}
