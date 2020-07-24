package bytesmyth.games.edpg.level;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;

import bytesmyth.games.edpg.actor.GameActor;
import bytesmyth.games.edpg.util.Assets;
import bytesmyth.games.edpg.util.MetaData;

public abstract class LevelScreen implements Screen {
	
	private String title;
	private String subtitle;
	private final float titleCardTime = 2f;
	private final float titleCardFade = 1f;
	private final float shadowTime = 1.5f;
	
	private boolean mainStagePaused;
	
	public final Stage mainStage;
	public final Stage uiStage;
	public final Stage titleStage;
	
	public LevelScreen(String title, String subtitle) {
		this.title = title;
		this.subtitle = subtitle;
		
		this.mainStagePaused = false;
		
		this.mainStage = new Stage();
		this.uiStage = new Stage();
		this.titleStage = new Stage();
		Gdx.input.setInputProcessor(this.uiStage);
		
		this.initialize();
		
		this.titleStage.addAction(Actions.sequence(
				Actions.delay(0.1f),
				Actions.run(()->{ this.mainStagePaused = true; }),
				Actions.delay(titleCardTime-0.1f),
				Actions.delay(titleCardFade/4f),
				Actions.run(()->{ this.mainStagePaused = false; })
				));
		
		GameActor blackout = new GameActor(this.titleStage);
		blackout.loadTexture("blackout.png");
		blackout.addAction(Actions.sequence(
				Actions.delay(titleCardTime),
				Actions.fadeOut(titleCardFade),
				Actions.run(()->{ blackout.setVisible(false); })
				));
		
		Label titleLabelW = new Label(this.title, Assets.skin, "title_white");
		titleLabelW.setPosition(MetaData.VIRTUAL_WIDTH/2f, MetaData.VIRTUAL_HEIGHT/2f, Align.bottom);
		titleLabelW.addAction(Actions.sequence(
				Actions.delay(titleCardTime-0.2f),
				Actions.color(Color.BLACK, titleCardFade+0.2f),
				Actions.delay(shadowTime, Actions.run(()->{ titleLabelW.setVisible(false); }))
				));
		this.titleStage.addActor(titleLabelW);
		
		Label subtitleLabelW = new Label(this.subtitle, Assets.skin, "subtitle_white");
		subtitleLabelW.setPosition(MetaData.VIRTUAL_WIDTH/2f, MetaData.VIRTUAL_HEIGHT/2f-5f, Align.top);
		subtitleLabelW.addAction(Actions.sequence(
				Actions.delay(titleCardTime-0.2f),
				Actions.color(Color.BLACK, titleCardFade+0.2f),
				Actions.delay(shadowTime, Actions.run(()->{ subtitleLabelW.setVisible(false); }))
				));
		this.titleStage.addActor(subtitleLabelW);
	}
	
	public void setMainStagePaused(boolean state) {
		this.mainStagePaused = state;
	}
	
	public boolean isMainStagePaused() {
		return this.mainStagePaused;
	}
	
	public abstract void initialize();
	public abstract void update(float dt);
	
	@Override
	public void render(float dt) {
		if (!this.mainStagePaused) this.mainStage.act(dt);
		this.uiStage.act(dt);
		this.titleStage.act(dt);
		
		if (!this.mainStagePaused) this.update(dt);
		
		Gdx.gl20.glClearColor(0.5f, 0f, 0f, 1f);
		Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		this.mainStage.draw();
		this.uiStage.draw();
		this.titleStage.draw();
	}
	
	@Override
	public void show() { }
	
	@Override
	public void resize(int width, int height) { }

	@Override
	public void pause() { }

	@Override
	public void resume() { }

	@Override
	public void hide() { }

	@Override
	public void dispose() { }

}
