package bytesmyth.games.edpg.level;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

import bytesmyth.games.edpg.actor.GameActor;
import bytesmyth.games.edpg.actor.object.MovingPlatform;
import bytesmyth.games.edpg.actor.object.StaticObject;
import bytesmyth.games.edpg.actor.object.trigger.Door;
import bytesmyth.games.edpg.actor.object.trigger.Dopamine;
import bytesmyth.games.edpg.actor.object.trigger.InfoBox;
import bytesmyth.games.edpg.actor.object.trigger.WheelController;

public class Level1 extends LevelScreen {
	
	public Level1() { super("Level 1", "The Tower", new Vector2(100f, 2f)); }

	@Override
	public void initialize() {
		(new GameActor(this.environment)).loadTexture("level1.png");
		
		new WheelController(200f, 550f, this.background, this.collisions);
		
		new StaticObject(-5000f, -5000f, 10800f, 5000f, this.mainStage, this.collisions);
		new StaticObject(-5000f, 6000f, 10800f, 5000f, this.mainStage, this.collisions);
		new StaticObject(-5000f, 0f, 5000f, 6000f, this.mainStage, this.collisions);
		new StaticObject(800f, 0f, 5000f, 6000f, this.mainStage, this.collisions);
		new StaticObject(375f, 0f, 50f, 6000f, this.mainStage, this.collisions);
		new StaticObject(0f, 500f, 800f, 50f, this.mainStage, this.collisions);
		new StaticObject(0f, 1500f, 600f, 50f, this.mainStage, this.collisions);
		new StaticObject(200f, 3000f, 600f, 50f, this.mainStage, this.collisions);
		new StaticObject(400f, 4350f, 400f, 50f, this.mainStage, this.collisions);
		new StaticObject(0f, 5705f, 180f, 50f, this.mainStage, this.collisions);
		
		(new MovingPlatform(200f, -50f, 200f, 50f, this.mainStage, this.collisions)).addAction(Actions.forever(Actions.sequence(
			Actions.moveBy(0f, 200f, 4f),
			Actions.moveBy(0f, -200f, 4f)
		)));
		
		new Door(this.getHUD(), Level2.class, 10f, 5755f, this.background, this.collisions);
		
		new Dopamine(100f, 800f, this.background, this.collisions);
		
		new InfoBox(this.getHUD(), "SHIFT\nAIM\nLAUNCH", 190f, 20f, 20f, 175f, this.background, this.collisions);
	}

	@Override
	public void update(float dt) { }

}
