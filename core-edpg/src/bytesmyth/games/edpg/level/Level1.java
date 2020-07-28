package bytesmyth.games.edpg.level;

import bytesmyth.games.edpg.actor.GameActor;
import bytesmyth.games.edpg.actor.object.Door;
import bytesmyth.games.edpg.actor.object.Dopamine;
import bytesmyth.games.edpg.actor.object.FaxModulo;
import bytesmyth.games.edpg.actor.object.InfoBox;
import bytesmyth.games.edpg.actor.object.StaticObject;

public class Level1 extends LevelScreen {
	
	public Level1() { super("Level 1", "The Tower"); }

	@Override
	public void initialize() {
		(new GameActor(this.mainStage)).loadTexture("level1.png");
		
		new StaticObject(-5000f, -5000f, 10800f, 5000f, this.mainStage);
		new StaticObject(-5000f, 6000f, 10800f, 5000f, this.mainStage);
		new StaticObject(-5000f, 0f, 5000f, 6000f, this.mainStage);
		new StaticObject(800f, 0f, 5000f, 6000f, this.mainStage);
		new StaticObject(375f, 0f, 50f, 6000f, this.mainStage);
		new StaticObject(0f, 500f, 800f, 50f, this.mainStage);
		new StaticObject(0f, 1500f, 600f, 50f, this.mainStage);
		new StaticObject(200f, 3000f, 600f, 50f, this.mainStage);
		new StaticObject(400f, 4350f, 400f, 50f, this.mainStage);
		new StaticObject(0f, 5705f, 180f, 50f, this.mainStage);
		
		new Door(Level2.class, 10f, 5755f, this.mainStage);
		
		new Dopamine(100f, 800f, this.mainStage);
		
		new FaxModulo(100f, 2f, this.mainStage, this.uiStage);
		
		new InfoBox("SHIFT\nAIM\nLAUNCH", 190f, 20f, 20f, 175f, this.mainStage);
	}

	@Override
	public void update(float dt) { }

}
