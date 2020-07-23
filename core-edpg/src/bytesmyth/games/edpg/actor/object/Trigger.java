package bytesmyth.games.edpg.actor.object;

import com.badlogic.gdx.scenes.scene2d.Stage;

import bytesmyth.games.edpg.actor.Collider;
import bytesmyth.games.edpg.actor.GameActor;

public abstract class Trigger extends GameObject {
	
	private boolean activated;
	
	public Trigger(float x, float y, float width, float height, Stage s) {
		super(OBJECT_TYP.Trigger, x, y, width, height, s);
		this.collider.setPhysical(false);
		
		this.activated = false;
	}
	public Trigger(float x, float y, Stage s) {
		this(x, y, 50f, 50f, s);
	}
	public Trigger(Stage s) { this(0f, 0f, s); }
	
	public abstract boolean activate();
	public abstract boolean deactivate();
	
	public boolean isActivated() {
		return this.activated;
	}
	
	@Override
	public void setCollider(Collider collider) {
		super.setCollider(collider);
		this.collider.setPhysical(false);
	}
	
	@Override
	public void act(float dt) {
		for (GameActor actor : GameActor.getList(this.getStage(), FaxModulo.class)) {
			FaxModulo fm = (FaxModulo)actor;
			if (!this.activated && this.collider.overlaps(fm.getCollider())) {
				this.activated = true;
				fm.justActivated(this);
				this.activate();
				return;
			}
			else if (this.activated && !this.collider.overlaps(fm.getCollider())) {
				this.activated = false;
				fm.justDeactivated(this);
				this.deactivate();
				return;
			}
		}
	}
	
}
