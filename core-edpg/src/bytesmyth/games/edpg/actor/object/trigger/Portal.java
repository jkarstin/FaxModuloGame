package bytesmyth.games.edpg.actor.object.trigger;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;

import bytesmyth.games.edpg.FaxModuloGame;
import bytesmyth.games.edpg.actor.RoundCollider;

public class Portal extends Trigger {
	
	private Class<? extends Screen> nextScreen;
	
	public Portal(Class<? extends Screen> screen, float x, float y, Stage s, Stage c) {
		super(x, y, s, c);
		
		this.setCollider(new RoundCollider(this, x, y, 50f, 50f, c));
		
		this.loadTexture("portal.png");
		this.setSize(this.animator.getKeyFrame(0f).getRegionWidth(), this.animator.getKeyFrame(0f).getRegionHeight());
		
		this.nextScreen = screen;
	}
	public Portal(Class<? extends Screen> screen, Stage s, Stage c) { this(screen, 0f, 0f, s, c); }
	
	@Override
	public boolean activate() {
		try {
			FaxModuloGame.setActiveScreen(this.nextScreen.newInstance());
			return true;
		} catch (Exception e) {}
		return false;
	}
	
	@Override
	public boolean deactivate() { return true; }
	
}
