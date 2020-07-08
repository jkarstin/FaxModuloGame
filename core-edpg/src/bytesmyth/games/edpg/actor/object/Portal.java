package bytesmyth.games.edpg.actor.object;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;

import bytesmyth.games.edpg.ExecDysfuncPlatformer;

public class Portal extends Trigger {
	
	private Class<? extends Screen> nextScreen;
	
	public Portal(Class<? extends Screen> screen, float x, float y) {
		super(x, y);
		
		this.loadTexture("portal.png");
		this.setSize(this.animator.getKeyFrame(0f).getRegionWidth(), this.animator.getKeyFrame(0f).getRegionHeight());
		
		this.nextScreen = screen;
	}
	public Portal(Class<? extends Screen> screen) { this(screen, 0f, 0f); }
	
	public Portal(Class<? extends Screen> screen, float x, float y, Stage s) {
		super(x, y, s);
		
		this.loadTexture("portal.png");
		this.setSize(this.animator.getKeyFrame(0f).getRegionWidth(), this.animator.getKeyFrame(0f).getRegionHeight());
		
		this.nextScreen = screen;
	}
	public Portal(Class<? extends Screen> screen, Stage s) { this(screen, 0f, 0f, s); }
	
	public boolean activate() {
		try {
			ExecDysfuncPlatformer.setActiveScreen(this.nextScreen.newInstance());
			return true;
		} catch (Exception e) {}
		return false;
	}
	
}
