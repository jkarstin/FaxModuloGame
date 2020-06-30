package bytesmyth.games.edpg.level;

import bytesmyth.games.edpg.actor.GameActor;
import bytesmyth.games.edpg.actor.Neuron;

public class Level0 extends LevelScreen {
	
	GameActor badlogic;
	Neuron neuron;
	
	@Override
	public void initialize() {
		badlogic = new GameActor(this.mainStage);
		badlogic.loadTexture("badlogic.jpg");
		
		neuron = new Neuron(16f, 32f, this.mainStage);
	}

	@Override
	public void update(float dt) {
		badlogic.moveBy(1f, 1f);
		neuron.rotateBy(1);
	}

}
