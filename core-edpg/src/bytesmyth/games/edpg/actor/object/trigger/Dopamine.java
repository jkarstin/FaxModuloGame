package bytesmyth.games.edpg.actor.object.trigger;

import com.badlogic.gdx.scenes.scene2d.Stage;

import bytesmyth.games.edpg.actor.RoundCollider;

public class Dopamine extends Trigger {

	public Dopamine(float x, float y, Stage s, Stage c) {
		super(x, y, s, c);
		
		this.setCollider(new RoundCollider(this, x, y, 50f, 50f, c));
		
		this.loadTexture("dopamine.png");
		this.setSize(this.animator.getKeyFrame(0f).getRegionWidth(), this.animator.getKeyFrame(0f).getRegionHeight());
	}
	public Dopamine(Stage s, Stage c) { this(0f, 0f, s, c); }

	@Override
	public boolean activate() {
		this.collider.remove();
		this.remove();
		return true;		
	}
	
	@Override
	public boolean deactivate() { return true; }

}
