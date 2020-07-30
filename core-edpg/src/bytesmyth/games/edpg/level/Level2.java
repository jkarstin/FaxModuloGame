package bytesmyth.games.edpg.level;

import com.badlogic.gdx.math.Vector2;

import bytesmyth.games.edpg.actor.GameActor;
import bytesmyth.games.edpg.actor.object.StaticObject;
import bytesmyth.games.edpg.actor.object.trigger.DamageArea;
import bytesmyth.games.edpg.actor.object.trigger.Portal;
import bytesmyth.games.edpg.screen.WinScreen;

public class Level2 extends LevelScreen {

	public Level2() { super("Level 2", "Lateral Lava", new Vector2(100f, 202f)); }

	@Override
	public void initialize() {
		(new GameActor(this.environment)).loadTexture("level2.png");
		
		new StaticObject(-5000f, -5000f, 16000f, 5000f, this.mainStage, this.collisions);
		new StaticObject(-5000f, 800f, 16000f, 5000f, this.mainStage, this.collisions);
		new StaticObject(-5000f, 0f, 5000f, 800f, this.mainStage, this.collisions);
		new StaticObject(6000f, 0f, 5000f, 800f, this.mainStage, this.collisions);
		
		new StaticObject(0f, 0f, 450f, 200f, this.mainStage, this.collisions);
		new StaticObject(1000f, 300f, 50f, 500f, this.mainStage, this.collisions);
		new StaticObject(1700f, 0f, 200f, 200f, this.mainStage, this.collisions);
		new StaticObject(2700f, 300f, 50f, 500f, this.mainStage, this.collisions);
		new DamageArea(2750f, 300f, 250f, 500f, this.foreground, this.collisions);
		new StaticObject(3400f, 0f, 50f, 300f, this.mainStage, this.collisions);
		
		new DamageArea(0f, 0f, 6000f, 15f, this.foreground, this.collisions);
		
		new Portal(WinScreen.class, 5900f, 300f, this.background, this.collisions);
	}

	@Override
	public void update(float dt) { }

}
