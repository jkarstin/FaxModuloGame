package bytesmyth.games.edpg.level;

import bytesmyth.games.edpg.actor.JexModem;
import bytesmyth.games.edpg.actor.StaticObject;
import bytesmyth.games.edpg.util.MetaData;

public class Level0 extends LevelScreen {
	
	JexModem jexModem;
	
	@Override
	public void initialize() {
		new StaticObject(0f, 0f, 800f, 20f, this.mainStage);
		
		jexModem = new JexModem(MetaData.VIRTUAL_WIDTH/2f, 22f, this.mainStage);
		
		new StaticObject(710f, 50f, 50f, 50f, this.mainStage);
	}

	@Override
	public void update(float dt) { }

}
