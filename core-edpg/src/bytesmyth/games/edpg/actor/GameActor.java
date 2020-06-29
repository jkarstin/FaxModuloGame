package bytesmyth.games.edpg.actor;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class GameActor extends Group {
	
	/*** Static Methods ***/
	
	//Asset management
	
	/* getList(stage, actorClass):
	 *  Parameters: Stage stage to get objects from, Class extending BaseActor actorClass determining Class type to search for
	 *  Return:     ArrayList of all objects that are instances of actorClass in stage
	 */
	public static ArrayList<GameActor> getList(Stage stage, Class<? extends GameActor> actorClass) {
		ArrayList<GameActor> list = new ArrayList<GameActor>();
		
		for (Actor a : stage.getActors()) {
			if (actorClass.isInstance(a)) {
				list.add((GameActor)a);
			}
		}
		
		return list;
	}
	
	/* count(stage, actorClass):
	 *  Parameters: Stage stage to get objects from, Class extending BaseActor actorClass determining Class type to search for
	 *  Return:     Number of objects that are instances of actorClass in stage
	 */
	public static int count(Stage stage, Class<? extends GameActor> actorClass) {
		return GameActor.getList(stage, actorClass).size();
	}
	
	/*** Fields ***/
	
	protected Animator animator; 
	
	/*** Constructors ***/
	
	public GameActor(float x, float y) {
		this.setPosition(x, y);
		this.animator = new Animator();
	}
	public GameActor() { this(0f, 0f); }
	public GameActor(float x, float y, Stage s) {
		this(x, y);
		s.addActor(this);
	}
	public GameActor(Stage s) { this(0f, 0f, s); }
	
	/*** Methods ***/
	
	public Animation<TextureRegion> loadTexture(String filename) {
		Animation<TextureRegion> anim = this.animator.loadTexture(filename);
		
		this.updateSize();
		this.updateOrigin();
		
		return anim;
	}
	
	public Animation<TextureRegion> loadAnimationFromFiles(String[] filenames, float frameDuration, boolean loop) {
		Animation<TextureRegion> anim = this.animator.loadAnimationFromFiles(filenames, frameDuration, loop);
		
		this.updateSize();
		this.updateOrigin();
		
		return anim;
	}
	
	public Animation<TextureRegion> loadAnimationFromSpritesheet(String filename, int rows, int cols, float frameDuration, boolean loop) {
		Animation<TextureRegion> anim = this.animator.loadAnimationFromSpritesheet(filename, rows, cols, frameDuration, loop);
		
		this.updateSize();
		this.updateOrigin();
		
		return anim;
	}
	
	public void updateSize() {
		TextureRegion tr = this.animator.getKeyFrame(0f);
		float w = tr.getRegionWidth();
		float h = tr.getRegionHeight();
		this.setSize(w, h);
	}
	
	public void updateOrigin() {
		TextureRegion tr = this.animator.getKeyFrame(0f);
		float w = tr.getRegionWidth();
		float h = tr.getRegionHeight();
		this.setOrigin(w/2, h/2);
	}
	
	public void setOpacity(float opacity) {
		this.getColor().a = opacity;
	}
	
	//Overridden (Inherited or Required)
	
	@Override
	public void act(float dt) {
		super.act(dt);
		this.animator.step(dt);
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		Color c = this.getColor();
		batch.setColor(c);
		
		if (this.isVisible() && this.animator != null && this.animator.animation() != null) {
			batch.draw(
				this.animator.getCurrentFrame(),
				this.getX(),
				this.getY(),
				this.getOriginX(),
				this.getOriginY(),
				this.getWidth(),
				this.getHeight(),
				this.getScaleX(),
				this.getScaleY(),
				this.getRotation()
			);
		}
		
		super.draw(batch, parentAlpha);
	}
	
}
