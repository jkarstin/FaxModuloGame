package bytesmyth.games.edpg;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;

import bytesmyth.games.edpg.level.Level0;

public class ExecDysfuncPlatformer extends Game {
	
	public static ExecDysfuncPlatformer EDP;
	
	public static void setActiveScreen(Screen screen) {
		ExecDysfuncPlatformer.EDP.setScreen(screen);
	}
	
	public ExecDysfuncPlatformer() {
		ExecDysfuncPlatformer.EDP = this;
	}
	
	@Override
	public void create () {
		ExecDysfuncPlatformer.setActiveScreen(new Level0());
	}
	
}
