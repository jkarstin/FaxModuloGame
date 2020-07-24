package bytesmyth.games.edpg.level;

import bytesmyth.games.edpg.actor.GameActor;
import bytesmyth.games.edpg.actor.object.FaxModulo;
import bytesmyth.games.edpg.actor.object.Portal;
import bytesmyth.games.edpg.actor.object.StaticObject;
import bytesmyth.games.edpg.screen.WinScreen;

public class Level2 extends LevelScreen {

	public Level2() { super("Level 2", "Lateral Mobility"); }

	@Override
	public void initialize() {
		(new GameActor(this.mainStage)).loadTexture("level2.png");
		
		new StaticObject(-5000f, -5000f, 12000f, 5000f, this.mainStage);
		new StaticObject(-5000f, 400f, 12000f, 5000f, this.mainStage);
		new StaticObject(-5000f, 0f, 5000f, 400f, this.mainStage);
		new StaticObject(2000f, 0f, 5000f, 400f, this.mainStage);
		
		new Portal(WinScreen.class, 1900f, 300f, this.mainStage);
		
		new FaxModulo(100f, 2f, this.mainStage, this.uiStage);
	}

	@Override
	public void update(float dt) { }

}
