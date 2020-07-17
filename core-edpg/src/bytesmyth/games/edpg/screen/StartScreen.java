package bytesmyth.games.edpg.screen;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;

import bytesmyth.games.edpg.ExecDysfuncPlatformer;
import bytesmyth.games.edpg.level.Level0;
import bytesmyth.games.edpg.util.Assets;
import bytesmyth.games.edpg.util.MetaData;

public class StartScreen extends BasicScreen {
	
	private TextButton startButton;
	
	@Override
	public void initialize() {
		this.startButton = new TextButton("Start", Assets.skin);
		this.startButton.setPosition(MetaData.VIRTUAL_WIDTH/2f, 100f, Align.center);
		this.startButton.addListener(new ClickListener() {
			
			@Override
			public void clicked(InputEvent event, float x, float y) {
				ExecDysfuncPlatformer.setActiveScreen(new Level0());
			}
			
		});
		this.stage.addActor(this.startButton);
	}
	
	@Override
	public void update(float dt) {
		
	}
	
}
