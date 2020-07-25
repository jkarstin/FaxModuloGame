package bytesmyth.games.edpg.level;

import bytesmyth.games.edpg.actor.GameActor;
import bytesmyth.games.edpg.actor.object.DamageArea;
import bytesmyth.games.edpg.actor.object.FaxModulo;
import bytesmyth.games.edpg.actor.object.Portal;
import bytesmyth.games.edpg.actor.object.StaticObject;
import bytesmyth.games.edpg.screen.WinScreen;

public class Level2 extends LevelScreen {

	public Level2() { super("Level 2", "Lateral Mobility"); }

	@Override
	public void initialize() {
		(new GameActor(this.mainStage)).loadTexture("level2.png");
		
		new StaticObject(-5000f, -5000f, 16000f, 5000f, this.mainStage);
		new StaticObject(-5000f, 800f, 16000f, 5000f, this.mainStage);
		new StaticObject(-5000f, 0f, 5000f, 800f, this.mainStage);
		new StaticObject(6000f, 0f, 5000f, 800f, this.mainStage);
		
		new StaticObject(0f, 0f, 450f, 200f, this.mainStage);
		new StaticObject(1000f, 300f, 50f, 500f, this.mainStage);
		new StaticObject(1700f, 0f, 200f, 200f, this.mainStage);
		new StaticObject(2700f, 300f, 50f, 500f, this.mainStage);
		new DamageArea(2750f, 300f, 250f, 500f, this.mainStage);
		new StaticObject(3400f, 0f, 50f, 300f, this.mainStage);
		
		new DamageArea(0f, 0f, 6000f, 15f, this.mainStage);
		
		new Portal(WinScreen.class, 5900f, 300f, this.mainStage);
		
		new FaxModulo(100f, 202f, this.mainStage, this.uiStage);
	}

	@Override
	public void update(float dt) { }

}
