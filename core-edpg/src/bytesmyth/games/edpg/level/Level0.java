package bytesmyth.games.edpg.level;

import bytesmyth.games.edpg.actor.GameActor;
import bytesmyth.games.edpg.actor.object.Door;
import bytesmyth.games.edpg.actor.object.Dopamine;
import bytesmyth.games.edpg.actor.object.FaxModulo;
import bytesmyth.games.edpg.actor.object.InfoBox;
import bytesmyth.games.edpg.actor.object.Portal;
import bytesmyth.games.edpg.actor.object.StaticObject;
import bytesmyth.games.edpg.screen.WinScreen;

public class Level0 extends LevelScreen {
	
	@Override
	public void initialize() {
		(new GameActor(this.mainStage)).loadTexture("Level0.png");
		
		new StaticObject(-5000f, -5000f, 10800f, 5000f, this.mainStage);
		new StaticObject(-5000f, 6000f, 10800f, 5000f, this.mainStage);
		new StaticObject(-5000f, 0f, 5000f, 6000f, this.mainStage);
		new StaticObject(800f, 0f, 5000f, 6000f, this.mainStage);
		new StaticObject(375f, 0f, 50f, 6000f, this.mainStage);
		new StaticObject(0f, 500f, 800f, 50f, this.mainStage);
		new StaticObject(0f, 1500f, 600f, 50f, this.mainStage);
		new StaticObject(200f, 3000f, 600f, 50f, this.mainStage);
		
		new Door(Level0.class, 600f, 0f, this.mainStage);
		
		new Portal(WinScreen.class, 600f, 4000f, this.mainStage);
		
		new Dopamine(100f, 800f, this.mainStage);
		
		new FaxModulo(100f, 2f, this.mainStage, this.uiStage);
		
		new InfoBox("SHIFT", 300f, 0f, 20f, 175f, this.mainStage);
	}

	@Override
	public void update(float dt) { }

}
