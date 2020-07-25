package bytesmyth.games.edpg.actor.object;

import com.badlogic.gdx.scenes.scene2d.Stage;

public class DamageArea extends Trigger {

	public DamageArea(float x, float y, float width, float height, Stage s) {
		super(x, y, width, height, s);
	}
	public DamageArea(Stage s) { this(0f, 0f, 50f, 50f, s); }

	@Override
	public boolean activate() { return true; }

	@Override
	public boolean deactivate() { return true; }

}
