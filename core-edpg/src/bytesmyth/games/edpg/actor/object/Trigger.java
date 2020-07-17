package bytesmyth.games.edpg.actor.object;

import com.badlogic.gdx.scenes.scene2d.Stage;

import bytesmyth.games.edpg.actor.RoundCollider;

public abstract class Trigger extends GameObject {

	public Trigger(float x, float y, float width, float height) {
		super(OBJECT_TYP.Trigger, x, y, width, height);
		
		this.setCollider(new RoundCollider(x, y, width, height));
		this.collider.setPhysical(false);
	}
	public Trigger(float x, float y) {
		this(x, y, 50f, 50f);
	}
	public Trigger() { this(0f, 0f); }

	public Trigger(float x, float y, float width, float height, Stage s) {
		super(OBJECT_TYP.Trigger, x, y, width, height, s);
		
		this.setCollider(new RoundCollider(x, y, width, height, s));
		this.collider.setPhysical(false);
	}
	public Trigger(float x, float y, Stage s) {
		this(x, y, 50f, 50f, s);
	}
	public Trigger(Stage s) { this(0f, 0f, s); }
	
	public abstract boolean activate(FaxModulo fm);
	
}
