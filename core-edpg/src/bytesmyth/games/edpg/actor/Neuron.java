package bytesmyth.games.edpg.actor;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class Neuron extends GameActor {
	
	/*** Fields ***/
	
	private int jumpLevel;
	private int direction;
	private ArrayList<TextureRegion> jumpFrames;
	
	/*** Constructors ***/
	
	public Neuron(float x, float y) {
		super(x, y);
		
		this.jumpLevel = 0;
		this.direction = 0;
		this.jumpFrames = new ArrayList<TextureRegion>();
		
		GameActor tmp = new GameActor();
		this.jumpFrames.add(tmp.loadTexture("neuron_jump_0.png").getKeyFrame(0));
		this.jumpFrames.add(tmp.loadTexture("neuron_jump_1.png").getKeyFrame(0));
		this.jumpFrames.add(tmp.loadTexture("neuron_jump_2.png").getKeyFrame(0));
		tmp = null;
		
		this.updateSize();
		this.updateOrigin();
	}
	public Neuron() { this(0f, 0f); }
	
	public Neuron(float x, float y, Stage s) {
		super(x, y, s);
		
		this.jumpLevel = 0;
		this.direction = 0;
		this.jumpFrames = new ArrayList<TextureRegion>();
		
		GameActor tmp = new GameActor();
		this.jumpFrames.add(tmp.loadTexture("neuron_jump_0.png").getKeyFrame(0));
		this.jumpFrames.add(tmp.loadTexture("neuron_jump_1.png").getKeyFrame(0));
		this.jumpFrames.add(tmp.loadTexture("neuron_jump_2.png").getKeyFrame(0));
		tmp = null;
		
		this.updateSize();
		this.updateOrigin();
	}
	public Neuron(Stage s) { this(0f, 0f, s); }
	
	/*** Methods ***/
	
	//Getters
	
	public int getJumpLevel() {
		return this.jumpLevel;
	}
	
	public int getDirection() {
		return this.direction;
	}
	
	//Setters
	
	public void setJumpLevel(int level) {
		if (level > 2) level = 2;
		else if (level < 0) level = 0;
		this.jumpLevel = level;
		
		this.updateSize();
		this.updateOrigin();
	}
	
	public void setDirection(int direction) {
		this.direction = direction;
	}
	
	@Override
	public void updateSize() {
		TextureRegion tr = this.jumpFrames.get(this.jumpLevel);
		float w = tr.getRegionWidth();
		float h = tr.getRegionHeight();
		this.setSize(w, h);
	}
	
	@Override
	public void updateOrigin() {
		this.setOrigin(48f, 48f);
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		
		Color c = this.getColor();
		batch.setColor(c);
		
		if (this.isVisible()) {
			batch.draw(
				this.jumpFrames.get(this.jumpLevel),
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
	}
	
}
