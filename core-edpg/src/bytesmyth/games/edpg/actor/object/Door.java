package bytesmyth.games.edpg.actor.object;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;

import bytesmyth.games.edpg.FaxModuloGame;
import bytesmyth.games.edpg.level.LevelScreen;
import bytesmyth.games.edpg.util.Assets;

public class Door extends Trigger {
	
	private Class<? extends LevelScreen> nextLevel;
	private Label promptLabel;
	
	public Door(Class<? extends LevelScreen> level, float x, float y, Stage s, Stage c) {
		super(x, y, s, c);
		
		this.loadTexture("door.png");
		this.setSize(this.animator.getKeyFrame(0f).getRegionWidth(), this.animator.getKeyFrame(0f).getRegionHeight());
		
		this.nextLevel = level;
		this.promptLabel = new Label("UP", Assets.skin);
		this.addActor(this.promptLabel);
		this.promptLabel.setPosition(this.getWidth()/2f, this.getHeight()+10f, Align.bottom);
		this.promptLabel.setVisible(false);
	}
	public Door(Class<? extends LevelScreen> level, Stage s, Stage c) {
		this(level, 0f, 0f, s, c);
	}
	
	public void use() {
		if (this.nextLevel == null) return;
		
		try {
			FaxModuloGame.setActiveScreen(this.nextLevel.newInstance());
		} catch (Exception e) {}
	}
	
	@Override
	public boolean activate() {
		if (this.promptLabel == null) return false;
		
		this.promptLabel.setVisible(true);
		return true;
	}

	@Override
	public boolean deactivate() {
		if (this.promptLabel == null) return false;
		
		this.promptLabel.setVisible(false);
		return true;
	}

}
