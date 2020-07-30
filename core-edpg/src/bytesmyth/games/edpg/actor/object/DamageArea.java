package bytesmyth.games.edpg.actor.object;

import com.badlogic.gdx.scenes.scene2d.Stage;

public class DamageArea extends Trigger {

	public DamageArea(float x, float y, float width, float height, Stage s, Stage c) {
		super(x, y, width, height, s, c);
	}
	public DamageArea(Stage s, Stage c) { this(0f, 0f, 50f, 50f, s, c); }

	@Override
	public boolean activate() { return true; }

	@Override
	public boolean deactivate() { return true; }

}
