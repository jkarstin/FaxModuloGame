package bytesmyth.games.edpg.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

import bytesmyth.games.edpg.FaxModuloGame;
import bytesmyth.games.edpg.actor.GameActor;

public class WinScreen extends BasicScreen {
	
	@Override
	public void initialize() {
		(new GameActor(this.stage)).loadTexture("fax_modulo_title_white.png");
		
		this.stage.addAction(
				Actions.sequence(
						Actions.delay(10f),
						Actions.run(()->{
							FaxModuloGame.setActiveScreen(new StartScreen());
							})
						)
				);
	}
	
	@Override
	public void update(float dt) {
		if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
			FaxModuloGame.setActiveScreen(new StartScreen());
		}
	}
	
}
