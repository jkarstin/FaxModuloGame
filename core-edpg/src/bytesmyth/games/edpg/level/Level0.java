package bytesmyth.games.edpg.level;

import bytesmyth.games.edpg.actor.JexModem;
import bytesmyth.games.edpg.util.MetaData;

public class Level0 extends LevelScreen {
	
	JexModem jexModem;
	
	@Override
	public void initialize() {
		jexModem = new JexModem(MetaData.VIRTUAL_WIDTH/2f, 40f, this.mainStage);
	}

	@Override
	public void update(float dt) { }

}
