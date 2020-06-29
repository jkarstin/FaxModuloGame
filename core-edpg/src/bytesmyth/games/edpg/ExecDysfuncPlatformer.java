package bytesmyth.games.edpg;

import com.badlogic.gdx.Game;

import bytesmyth.games.edpg.level.Level0;

public class ExecDysfuncPlatformer extends Game {
	
	@Override
	public void create () {
		this.setScreen(new Level0());
	}
	
}
