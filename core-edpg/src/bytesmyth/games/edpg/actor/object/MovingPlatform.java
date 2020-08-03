package bytesmyth.games.edpg.actor.object;

import com.badlogic.gdx.scenes.scene2d.Stage;

public class MovingPlatform extends PatchObject {

	public MovingPlatform(float x, float y, float width, float height, Stage s, Stage c) {
		super(OBJECT_TYP.Moving, x, y, width, height, "platform.png", 3, 3, 3, 3, s, c);
	}
	public MovingPlatform(float x, float y, Stage s, Stage c) {
		this(x, y, 150f, 50f, s, c);
	}
	public MovingPlatform(Stage s, Stage c) { this(0f, 0f, s, c); }
	
}
