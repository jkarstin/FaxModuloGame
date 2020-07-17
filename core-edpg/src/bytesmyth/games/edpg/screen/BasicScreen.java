package bytesmyth.games.edpg.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;

public abstract class BasicScreen implements Screen {
	
	protected Stage stage;
	
	public BasicScreen() {
		this.stage = new Stage();
		Gdx.input.setInputProcessor(this.stage);
		
		this.initialize();
	}
	
	public abstract void initialize();
	public abstract void update(float dt);
	
	@Override
	public void render(float dt) {
		this.stage.act(dt);
		
		this.update(dt);
		
		Gdx.gl20.glClearColor(0f, 0f, 0f, 1f);
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
