package bytesmyth.games.edpg.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;

import bytesmyth.games.edpg.level.BasicStage;

public abstract class BasicScreen implements Screen {
	
	public static final Color DEFAULT_BACKGROUND_COLOR = Color.BLACK;
	
	protected BasicStage stage;
	private Color backgroundColor;
	
	public BasicScreen(Color bgc) {
		this.stage = new BasicStage();
		Gdx.input.setInputProcessor(this.stage);
		
		if (bgc == null) this.backgroundColor = BasicScreen.DEFAULT_BACKGROUND_COLOR;
		else this.backgroundColor = bgc;
		
		this.initialize();
	}
	public BasicScreen() { this(BasicScreen.DEFAULT_BACKGROUND_COLOR); }
	
	public abstract void initialize();
	public abstract void update(float dt);
	
	public void setBackgroundColor(Color bgc) {
		if (bgc == null) return;
		
		this.backgroundColor = bgc;
	}
	
	@Override
	public void render(float dt) {
		this.stage.act(dt);
		
		this.update(dt);
		
		Gdx.gl20.glClearColor(
			this.backgroundColor.r,
			this.backgroundColor.g,
			this.backgroundColor.b,
			this.backgroundColor.a
		);
		Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		this.stage.draw();
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
