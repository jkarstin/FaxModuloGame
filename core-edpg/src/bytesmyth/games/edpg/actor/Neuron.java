package bytesmyth.games.edpg.actor;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Align;

import bytesmyth.games.edpg.util.MetaData;

public class Neuron extends GameActor {
	
	/*** Enumerations ***/
	
	public static enum DIRECTION {
		Center,
		Right,
		UpRight,
		Up,
		UpLeft,
		Left,
		DownLeft,
		Down,
		DownRight
	}
	
	/*** Fields ***/
	
	private int jumpLevel;
	private DIRECTION direction;
	private ArrayList<Animation<NinePatch>> jumpFrames;
	private GameActor neuronJump;
	private GameActor returnPoint;
	
	/*** Constructors ***/
	
	public Neuron(float x, float y, Stage s) {
		super(x, y, s);
		
		this.jumpLevel = 0;
		this.direction = DIRECTION.Center;
		this.jumpFrames = new ArrayList<Animation<NinePatch>>();
		
		this.neuronJump = new GameActor(s);
		this.jumpFrames.add(neuronJump.loadTexture("neuron_jump_0.png"));
		this.jumpFrames.add(neuronJump.loadTexture("neuron_jump_1.png"));
		this.jumpFrames.add(neuronJump.loadTexture("neuron_jump_2.png"));
		this.jumpFrames.add(neuronJump.loadTexture("neuron_idle.png"));
		this.neuronJump.animator.setAnimation(this.jumpFrames.get(3));
		this.neuronJump.updateSize();
		this.neuronJump.updateOrigin();
		this.addActor(neuronJump);
		
		this.neuronJump.setPosition(-48f, -48f);
		
		this.returnPoint = new GameActor(s);
		this.returnPoint.loadTexture("return_point.png");
		this.neuronJump.addActor(this.returnPoint);
		
		this.updateSize();
		this.updateOrigin();
		
		this.updateReturnPoint();
	}
	public Neuron(Stage s) { this(MetaData.VIRTUAL_WIDTH/2f, MetaData.VIRTUAL_HEIGHT/2f, s); }
	
	/*** Methods ***/
	
	//Getters
	
	public int getJumpLevel() {
		return this.jumpLevel;
	}
	
	public DIRECTION getDirection() {
		return this.direction;
	}
	
	public Vector2 getDirectionVector() {
		Vector2 tmp = new Vector2();
		
		switch (this.direction) {
		case Right:
			tmp.set(1f, 0f);
			break;
		case UpRight:
			tmp.set(1f, 1f).nor();
			break;
		case Up:
			tmp.set(0f, 1f);
			break;
		case UpLeft:
			tmp.set(-1f, 1f).nor();
			break;
		case Left:
			tmp.set(-1f, 0f);
			break;
		case DownLeft:
			tmp.set(-1f, -1f).nor();
			break;
		case Down:
			tmp.set(0f, -1f);
			break;
		case DownRight:
			tmp.set(1f, -1f).nor();
			break;
		case Center:
		default:
			return Vector2.Zero;
		}
		
		return tmp;
	}
	
	public Vector2 getReturnPoint() {
		Vector2 tmp = new Vector2();
		Vector2 rPoint = new Vector2();
		rPoint.set(this.returnPoint.getX(Align.center), 0f).add(tmp.set(this.neuronJump.getX(), 0f));
		rPoint.rotate(this.getRotation());
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
	
	public void setDirection(DIRECTION dir) {
		
		this.direction = dir;
		
		if (dir == DIRECTION.Center) {
			this.neuronJump.animator.setAnimation(this.jumpFrames.get(3));
			this.neuronJump.updateSize();
			this.neuronJump.updateOrigin();
			
			this.updateSize();
			this.updateOrigin();
		}
		else {
			this.neuronJump.animator.setAnimation(this.jumpFrames.get(this.jumpLevel));
			this.neuronJump.updateSize();
			this.neuronJump.updateOrigin();
			
			this.updateSize();
			this.updateOrigin();
		}
		
		this.updateRotation();
	}
	
	public void setDirection(Vector2 directionVect) {
		if (directionVect == null) return;
		
		if (directionVect.x > 0f) {
			if (directionVect.y > 0f) {
				this.setDirection(DIRECTION.UpRight);
			}
			else if (directionVect.y < 0f) {
				this.setDirection(DIRECTION.DownRight);
			}
			else {
				this.setDirection(DIRECTION.Right);
			}
		}
		else if (directionVect.x < 0f) {
			if (directionVect.y > 0f) {
				this.setDirection(DIRECTION.UpLeft);
			}
			else if (directionVect.y < 0f) {
				this.setDirection(DIRECTION.DownLeft);
			}
			else {
				this.setDirection(DIRECTION.Left);
			}
		}
		else {
			if (directionVect.y > 0f) {
				this.setDirection(DIRECTION.Up);
			}
			else if (directionVect.y < 0f) {
				this.setDirection(DIRECTION.Down);
			}
			else {
				this.setDirection(DIRECTION.Center);
			}
		}
	}
	
	//Utility
	
	public void updateRotation() {
		int dirCode = this.direction2int(this.direction);
		if (dirCode < 0) this.setRotation(0f);
		else this.setRotation(this.direction2int(this.direction) * 45f);
		
		this.updateReturnPoint();
	}
	
	private void updateReturnPoint() {
		if (this.direction == DIRECTION.Center) {
			this.returnPoint.setPosition(48f, 48f, Align.center);
			this.returnPoint.setVisible(false);
		}
		else {
			this.returnPoint.setVisible(true);
			this.returnPoint.setPosition(this.getWidth()-20f, 48f, Align.center);
			this.returnPoint.setRotation(-this.getRotation());	
		}
	}
	
	private int direction2int(DIRECTION dir) {
		switch (dir) {
		case Right:
			return 0;
		case UpRight:
			return 1;
		case Up:
			return 2;
		case UpLeft:
			return 3;
		case Left:
			return 4;
		case DownLeft:
			return 5;
		case Down:
			return 6;
		case DownRight:
			return 7;
		default:
			return -1;
		}
	}
	
	//Overridden (Inherited or Required)
	
	@Override
	public void updateSize() {
		NinePatch np = this.neuronJump.animator.getKeyFrame(0f);
		float w = np.getTotalWidth();
		float h = np.getTotalHeight();
		this.setSize(w, h);
	}
	
	@Override
	public void updateOrigin() { }
	
}
