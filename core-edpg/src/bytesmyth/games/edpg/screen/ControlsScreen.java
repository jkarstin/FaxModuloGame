package bytesmyth.games.edpg.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;

import bytesmyth.games.edpg.FaxModuloGame;
import bytesmyth.games.edpg.actor.GameActor;
import bytesmyth.games.edpg.util.Assets;
import bytesmyth.games.edpg.util.MetaData;

public class ControlsScreen extends BasicScreen {
	
	private TextButton backButton;
	
	@Override
	public void initialize() {
		(new GameActor(this.stage)).loadTexture("controls.png");
		
		this.backButton = new TextButton("Back", Assets.skin);
		this.backButton.setWidth(80f);
		this.backButton.setPosition(MetaData.VIRTUAL_WIDTH-40f, 40f, Align.bottomRight);
		this.backButton.addListener(new ClickListener() {
			
			@Override
			public void clicked(InputEvent event, float x, float y) {
				FaxModuloGame.setActiveScreen(new StartScreen());
			}
			
		});
		this.backButton.setVisible(false);
		this.stage.addActor(this.backButton);
		
		this.stage.addAction(Actions.sequence(Actions.delay(3f), Actions.run(()->{
			this.backButton.setVisible(true);
		})));
	}

	@Override
	public void update(float dt) {
		if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
			if (this.backButton.isVisible())
				FaxModuloGame.setActiveScreen(new StartScreen());
			else {
				this.backButton.clearActions();
				this.backButton.setVisible(true);
			}
		}
	}

}
