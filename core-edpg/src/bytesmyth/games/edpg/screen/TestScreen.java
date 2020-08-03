package bytesmyth.games.edpg.screen;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

import bytesmyth.games.edpg.actor.GameActor;

public class TestScreen extends BasicScreen {
	
	GameActor ga;
	
	@Override
	public void initialize() {
		this.setBackgroundColor(new Color(0.1f, 0.1f, 0.1f, 1f));
		
		(ga = new GameActor(this.stage)).loadPatch("platform.png", 3, 3, 3, 3);
		ga.setSize(100f, 200f);
		ga.addAction(Actions.forever(Actions.parallel(
			Actions.sequence(
				Actions.sizeBy(200f, 0f, 2f),
				Actions.sizeBy(-200f, 0f, 2f)
			),
			Actions.sequence(
				Actions.sizeBy(0f, 100f, 1f),
				Actions.sizeBy(0f, -200f, 2f),
				Actions.sizeBy(0f, 100f, 1f)
			)
		)));
	}

	@Override
	public void update(float dt) { }

}
