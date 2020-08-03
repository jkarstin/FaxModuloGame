package bytesmyth.games.edpg.actor.object.trigger;

import com.badlogic.gdx.scenes.scene2d.Stage;

import bytesmyth.games.edpg.FaxModuloGame;
import bytesmyth.games.edpg.actor.HUD;
import bytesmyth.games.edpg.level.LevelScreen;

public class Door extends InfoBox {
	
	private Class<? extends LevelScreen> nextLevel;
	
	public Door(HUD hud, Class<? extends LevelScreen> level, float x, float y, Stage s, Stage c) {
		super(hud, "UP", x, y, 50f, 50f, s, c);
		
		this.loadTexture("door.png");
		this.setSize(this.animator.getKeyFrame(0f).getTotalWidth(), this.animator.getKeyFrame(0f).getTotalHeight());
		
		this.nextLevel = level;
	}
	public Door(HUD hud, Class<? extends LevelScreen> level, Stage s, Stage c) {
		this(hud, level, 0f, 0f, s, c);
	}
	
	public void use() {
		if (this.nextLevel == null) return;
		
		try {
			FaxModuloGame.setActiveScreen(this.nextLevel.newInstance());
		} catch (Exception e) {}
	}

}
