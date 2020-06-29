package bytesmyth.games.edpg.level;

import bytesmyth.games.edpg.actor.GameActor;

public class Level0 extends LevelScreen {
	
	GameActor badlogic;
	
	@Override
	public void initialize() {
		badlogic = new GameActor(this.mainStage);
		badlogic.loadTexture("badlogic.jpg");
	}

	@Override
	public void update(float dt) {
		badlogic.moveBy(1f, 1f);
	}

}
