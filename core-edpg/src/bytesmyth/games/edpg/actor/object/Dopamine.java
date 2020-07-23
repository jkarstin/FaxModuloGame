package bytesmyth.games.edpg.actor.object;

import com.badlogic.gdx.scenes.scene2d.Stage;

import bytesmyth.games.edpg.actor.RoundCollider;

public class Dopamine extends Trigger {

	public Dopamine(float x, float y, Stage s) {
		super(x, y, s);
		
		this.setCollider(new RoundCollider(x, y, 50f, 50f, s));
		
		this.loadTexture("dopamine.png");
		this.setSize(this.animator.getKeyFrame(0f).getRegionWidth(), this.animator.getKeyFrame(0f).getRegionHeight());
	}
	public Dopamine(Stage s) { this(0f, 0f, s); }

	@Override
	public boolean activate() {
		this.collider.remove();
		this.remove();
		return true;		
	}
	
	@Override
	public boolean deactivate() { return true; }

}
