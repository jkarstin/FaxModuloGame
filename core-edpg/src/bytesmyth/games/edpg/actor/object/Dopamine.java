package bytesmyth.games.edpg.actor.object;

import com.badlogic.gdx.scenes.scene2d.Stage;

public class Dopamine extends Trigger {

	public Dopamine(float x, float y) {
		super(x, y);
		
		this.loadTexture("dopamine.png");
		this.setSize(this.animator.getKeyFrame(0f).getRegionWidth(), this.animator.getKeyFrame(0f).getRegionHeight());
	}
	public Dopamine() { this(0f, 0f); }
	
	public Dopamine(float x, float y, Stage s) {
		super(x, y, s);
		
		this.loadTexture("dopamine.png");
		this.setSize(this.animator.getKeyFrame(0f).getRegionWidth(), this.animator.getKeyFrame(0f).getRegionHeight());
	}
	public Dopamine(Stage s) { this(0f, 0f, s); }

	@Override
	public boolean activate(FaxModulo fm) {
		if (fm == null) return false;
		
		fm.collect(this);
		this.collider.remove();
		this.remove();
		
		return false;
	}

}
