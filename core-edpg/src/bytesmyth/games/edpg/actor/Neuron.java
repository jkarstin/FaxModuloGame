package bytesmyth.games.edpg.actor;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Align;

public class Neuron extends GameActor {
	
	/*** Fields ***/
	
	private int jumpLevel;
	private int direction;
	private ArrayList<Animation<TextureRegion>> jumpFrames;
	private GameActor neuronJump;
	private GameActor returnPoint;
	
	/*** Constructors ***/
	
	public Neuron(float x, float y) {
		super(x, y);
		
		this.jumpLevel = 0;
		this.direction = 0;
		this.jumpFrames = new ArrayList<Animation<TextureRegion>>();
		
		this.neuronJump = new GameActor();
		this.jumpFrames.add(neuronJump.loadTexture("neuron_jump_0.png"));
		this.jumpFrames.add(neuronJump.loadTexture("neuron_jump_1.png"));
		this.jumpFrames.add(neuronJump.loadTexture("neuron_jump_2.png"));
		this.neuronJump.animator.setAnimation(this.jumpFrames.get(0));
		
		this.returnPoint = new GameActor();
		this.returnPoint.loadTexture("return_point.png");
		this.addActor(this.returnPoint);
		
		this.updateSize();
		this.updateOrigin();
		
		this.updateReturnPoint();
	}
	public Neuron() { this(0f, 0f); }
	
	public Neuron(float x, float y, Stage s) {
		super(x, y, s);
		
		this.jumpLevel = 0;
		this.direction = 0;
		this.jumpFrames = new ArrayList<Animation<TextureRegion>>();
		
		this.neuronJump = new GameActor(s);
		this.jumpFrames.add(neuronJump.loadTexture("neuron_jump_0.png"));
		this.jumpFrames.add(neuronJump.loadTexture("neuron_jump_1.png"));
		this.jumpFrames.add(neuronJump.loadTexture("neuron_jump_2.png"));
		this.neuronJump.animator.setAnimation(this.jumpFrames.get(0));
		this.addActor(neuronJump);
		
		this.neuronJump.setPosition(-48f, -48f);
		
		this.returnPoint = new GameActor(s);
		this.returnPoint.loadTexture("return_point.png");
		this.neuronJump.addActor(this.returnPoint);
		
		this.updateSize();
		this.updateOrigin();
		
		this.updateReturnPoint();
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
	
	public Vector2 getReturnPoint() {
		Vector2 tmp = new Vector2();
		Vector2 rPoint = new Vector2();
		rPoint.set(this.returnPoint.getX(Align.center), 0f).add(tmp.set(this.neuronJump.getX(), 0f));
		rPoint.rotate(this.getRotation());
		rPoint.add(tmp.set(this.getX(), this.getY()));
		return rPoint;
	}
	
	//Setters
	
	public void setJumpLevel(int level) {
		if (level > 2) level = 2;
		else if (level < 0) level = 0;
		this.jumpLevel = level;
		
		this.neuronJump.animator.setAnimation(this.jumpFrames.get(this.jumpLevel));
		this.neuronJump.updateSize();
		this.neuronJump.updateOrigin();
		
		this.updateSize();
		this.updateOrigin();
		
		this.updateReturnPoint();
	}
	
	public void setDirection(int direction) {
		this.direction = direction%8;
		
		this.updateRotation();
	}
	
	public void setDirection(Vector2 directionVect) {
		if (directionVect == null) return;
		
		if (directionVect.x > 0f) {
			if (directionVect.y > 0f) {
				this.setDirection(1);
			}
			else if (directionVect.y < 0f) {
				this.setDirection(7);
			}
			else {
				this.setDirection(0);
			}
		}
		else if (directionVect.x < 0f) {
			if (directionVect.y > 0f) {
				this.setDirection(3);
			}
			else if (directionVect.y < 0f) {
				this.setDirection(5);
			}
			else {
				this.setDirection(4);
			}
		}
		else {
			if (directionVect.y > 0f) {
				this.setDirection(2);
			}
			else if (directionVect.y < 0f) {
				this.setDirection(6);
			}
		}
	}
	
	@Override
	public void updateSize() {
		TextureRegion tr = this.jumpFrames.get(this.jumpLevel).getKeyFrame(0f);
		float w = tr.getRegionWidth();
		float h = tr.getRegionHeight();
		this.setSize(w, h);
	}
	
	@Override
	public void updateOrigin() { }
	
	public void updateRotation() {
		this.setRotation(this.direction * 45f);
		
		this.updateReturnPoint();
	}
	
	private void updateReturnPoint() {
		this.returnPoint.setPosition(this.getWidth()-20f, 48f, Align.center);
		this.returnPoint.setRotation(-this.getRotation());
	}
	
}
