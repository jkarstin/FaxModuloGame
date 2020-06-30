package bytesmyth.games.edpg.level;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;

public abstract class LevelScreen implements Screen {
	
	public final Stage mainStage;
	public final Stage uiStage;
	
	public LevelScreen() {
		this.mainStage = new Stage();
		this.uiStage = new Stage();
		this.initialize();
	}
	
	public abstract void initialize();
	public abstract void update(float dt);
	
	@Override
	public void render(float dt) {
		this.mainStage.act(dt);
		this.uiStage.act(dt);
		
		this.update(dt);
		
		Gdx.gl20.glClearColor(0.5f, 0f, 0f, 1f);
		Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		this.mainStage.draw();
		this.uiStage.draw();
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
