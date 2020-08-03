package bytesmyth.games.edpg.util;

import com.badlogic.gdx.Game;

import bytesmyth.games.edpg.screen.TestScreen;

public class TestGame extends Game {
	
	@Override
	public void create() {
		new Assets();
		
		this.setScreen(new TestScreen());
	}
	
}
