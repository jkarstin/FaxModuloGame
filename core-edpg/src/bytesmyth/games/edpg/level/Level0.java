package bytesmyth.games.edpg.level;

import bytesmyth.games.edpg.actor.GameActor;
import bytesmyth.games.edpg.actor.Neuron;

public class Level0 extends LevelScreen {
	
	GameActor jexModem;
	Neuron neuron;
	float elapsedTime;
	
	@Override
	public void initialize() {
		jexModem = new GameActor(this.mainStage);
		jexModem.loadAnimationFromSpritesheet("jex_modem_walk.png", 2, 4, 0.08f, true);
		
		neuron = new Neuron(16f, 32f, this.mainStage);
		elapsedTime = 0f;
	}

	@Override
	public void update(float dt) {
		elapsedTime += dt;
		jexModem.moveBy(96f*dt, 0f);
		neuron.setDirection((int)elapsedTime);
	}

}
