package bytesmyth.games.edpg.actor.object.trigger;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.Align;

import bytesmyth.games.edpg.actor.GameActor;

public class WheelController extends Trigger {
	
	private GameActor valveWheel;
	
	public WheelController(float x, float y, float width, float height, Stage s, Stage c) {
		super(x, y, width, height, s, c);
		
		this.loadTexture("valve.png");
		this.setSize(this.animator.getKeyFrame(0f).getRegionWidth(), this.animator.getKeyFrame(0f).getRegionHeight());
		
		this.valveWheel = new GameActor(s);
		this.valveWheel.loadTexture("valve_wheel.png");
		this.addActor(this.valveWheel);
		this.valveWheel.setPosition(this.getWidth()/2f, this.getHeight()/2f, Align.center);
	}
	public WheelController(float x, float y, Stage s, Stage c) {
		this(x, y, 50f, 50f, s, c);
	}
	public WheelController(Stage s, Stage c) { this(0f, 0f, s, c); }
	
	@Override
	public boolean activate() {
		this.valveWheel.addAction(Actions.forever(Actions.rotateBy(90f, 0.5f)));
		return true;
	}

	@Override
	public boolean deactivate() {
		this.valveWheel.clearActions();
		return true;
	}

}
