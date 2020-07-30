package bytesmyth.games.edpg.level;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Align;

import bytesmyth.games.edpg.actor.GameActor;

public class LevelCamera {
	
	private final Stage env, bg, col, main, fg;
	private GameActor target;
	
	public LevelCamera(GameActor target, Stage env, Stage bg, Stage col, Stage main, Stage fg) {
		this.target = target;
		this.env = env;
		this.bg = bg;
		this.col = col;
		this.main = main;
		this.fg = fg;
	}
	public LevelCamera(Stage env, Stage bg, Stage col, Stage main, Stage fg) {
		this(null, env, bg, col, main, fg);
	}
	
	public void setFocus(GameActor target) {
		this.target = target;
	}
	
	public void update(float dt) {
		if (this.target == null) return;
		
		if (this.env != null) {
			this.env.getCamera().position.x = this.target.getX(Align.center);
			this.env.getCamera().position.y = this.target.getY(Align.center);
		}
		if (this.bg != null) {
			this.bg.getCamera().position.x = this.target.getX(Align.center);
			this.bg.getCamera().position.y = this.target.getY(Align.center);
		}
		if (this.col != null) {
			this.col.getCamera().position.x = this.target.getX(Align.center);
			this.col.getCamera().position.y = this.target.getY(Align.center);
		}
		if (this.main != null) {
			this.main.getCamera().position.x = this.target.getX(Align.center);
			this.main.getCamera().position.y = this.target.getY(Align.center);
		}
		if (this.fg != null) {
			this.fg.getCamera().position.x = this.target.getX(Align.center);
			this.fg.getCamera().position.y = this.target.getY(Align.center);
		}
	}

}
