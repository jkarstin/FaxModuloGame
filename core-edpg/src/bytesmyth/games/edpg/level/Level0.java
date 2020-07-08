package bytesmyth.games.edpg.level;

import bytesmyth.games.edpg.actor.object.JexModem;
import bytesmyth.games.edpg.actor.object.Portal;
import bytesmyth.games.edpg.actor.object.StaticObject;
import bytesmyth.games.edpg.util.MetaData;

public class Level0 extends LevelScreen {
	
	JexModem jexModem;
	
	@Override
	public void initialize() {
		new StaticObject(0f, -100f, 800f, 120f, this.mainStage);
		
		jexModem = new JexModem(MetaData.VIRTUAL_WIDTH/2f, 22f, this.mainStage);
		
		new StaticObject(710f, 50f, 50f, 50f, this.mainStage);
		
		new Portal(Level0.class, 400f, 300f, this.mainStage);
	}

	@Override
	public void update(float dt) { }

}
