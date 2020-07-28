package bytesmyth.games.edpg.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;

import bytesmyth.games.edpg.FaxModuloGame;
import bytesmyth.games.edpg.actor.GameActor;
import bytesmyth.games.edpg.level.Level1;
import bytesmyth.games.edpg.util.Assets;
import bytesmyth.games.edpg.util.MetaData;

public class StartScreen extends BasicScreen {
	
	private GameActor titleW;
	private GameActor titleB;
	
	private final float titleYOffset = 100f;
	private final float titleShadowOffset = 7f;
	private final float buttonWidth = 120f;
	private final float buttonSpread = 50f;
	
	private TextButton startButton;
	private TextButton controlsButton;
	private TextButton quitButton;
	
	@Override
	public void initialize() {
		this.titleW = new GameActor(this.stage);
		this.titleW.loadTexture("fax_modulo_title_white.png");
		this.titleW.setPosition(MetaData.VIRTUAL_WIDTH/2f, MetaData.VIRTUAL_HEIGHT/2f+titleYOffset, Align.center);
		
		this.titleB = new GameActor(this.stage);
		this.titleB.loadTexture("fax_modulo_title_black.png");
		
		this.startButton = new TextButton("Start", Assets.skin);
		this.startButton.setWidth(buttonWidth);
		this.startButton.setPosition(MetaData.VIRTUAL_WIDTH/2f, MetaData.VIRTUAL_HEIGHT/2f-buttonSpread, Align.center);
		this.startButton.addListener(new ClickListener() {
			
			@Override
			public void clicked(InputEvent event, float x, float y) {
				FaxModuloGame.setActiveScreen(new Level1());
			}
			
		});
		this.stage.addActor(this.startButton);
		
		this.controlsButton = new TextButton("Controls", Assets.skin);
		this.controlsButton.setWidth(buttonWidth);
		this.controlsButton.setPosition(MetaData.VIRTUAL_WIDTH/2f, MetaData.VIRTUAL_HEIGHT/2f-buttonSpread*2f, Align.center);
		this.controlsButton.addListener(new ClickListener() {
			
			@Override
			public void clicked(InputEvent event, float x, float y) {
				FaxModuloGame.setActiveScreen(new ControlsScreen());
			}
			
		});
		this.stage.addActor(this.controlsButton);
		
		this.quitButton = new TextButton("Quit", Assets.skin);
		this.quitButton.setWidth(buttonWidth);
		this.quitButton.setPosition(MetaData.VIRTUAL_WIDTH/2f, MetaData.VIRTUAL_HEIGHT/2f-buttonSpread*3f, Align.center);
		this.quitButton.addListener(new ClickListener() {
			
			@Override
			public void clicked(InputEvent event, float x, float y) {
				Gdx.app.exit();
			}
			
		});
		this.stage.addActor(this.quitButton);
	}
	
	@Override
	public void update(float dt) {
		Vector2 tmp = new Vector2();
		tmp.set(
				Gdx.input.getX()-MetaData.VIRTUAL_WIDTH/2f,
				MetaData.VIRTUAL_HEIGHT/2f-Gdx.input.getY()-titleYOffset
				).nor().scl(-titleShadowOffset);
		this.titleB.setPosition(this.titleW.getX(Align.center)+tmp.x, this.titleW.getY(Align.center)+tmp.y, Align.center);
		
		if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
			Gdx.app.exit();
		}
	}
	
}
