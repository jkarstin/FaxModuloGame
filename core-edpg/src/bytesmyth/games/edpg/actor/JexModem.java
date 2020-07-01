package bytesmyth.games.edpg.actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Align;

public class JexModem extends GameActor {
	
	/*** Fields ***/
	
	private Vector2 moveVector;
	private float moveSpeed = 144f;
	private Animation<TextureRegion> idleAnim, walkAnim;
	
	private Neuron neuron;
	private boolean neuronMode;
	private Vector2 tmp;
	
	/*** Constructors ***/
	
	public JexModem(float x, float y) {
		super(x, y);
		
		this.moveVector = new Vector2();
		this.loadAnimations();
		
		this.neuron = new Neuron();
		this.neuron.setVisible(false);
		this.neuronMode = false;
	}
	public JexModem() { this(0f, 0f); }

	public JexModem(float x, float y, Stage s) {
		super(x, y, s);
		
		this.moveVector = new Vector2();
		this.loadAnimations();
		
		this.neuron = new Neuron(s);
		this.neuron.setVisible(false);
		this.neuronMode = false;
	}
	public JexModem(Stage s) { this(0f, 0f, s); }
	
	/*** Methods ***/
	
	private void loadAnimations() {
		this.idleAnim = this.loadTexture("jex_modem.png");
		this.walkAnim = this.loadAnimationFromSpritesheet("jex_modem_walk.png", 2, 4, 0.08f, true);
		this.animator.setAnimation(this.idleAnim);
	}
	
	private void neuronModeInputProcessing() {
		if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
			this.neuronMode = false;
			this.setPosition(this.neuron.getReturnPoint().x, this.neuron.getReturnPoint().y, Align.center);
			this.neuron.setVisible(false);
			this.animator.resume();
		}
		
		if (Gdx.input.isKeyJustPressed(Input.Keys.D)) {
			this.tmp.x = 1f;
		}
		if (Gdx.input.isKeyJustPressed(Input.Keys.A)) {
			this.tmp.x = -1f;
		}
		if (Gdx.input.isKeyJustPressed(Input.Keys.W)) {
			this.tmp.y = 1f;
		}
		if (Gdx.input.isKeyJustPressed(Input.Keys.S)) {
			this.tmp.y = -1f;
		}
		
		this.neuron.setDirection(this.tmp);
	}
	
	private void movementInputProcessing(float dt) {
		boolean walking = false;
		this.tmp = new Vector2();
		
		if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
			this.neuronMode = true;
			this.neuron.setPosition(this.getX(Align.center), this.getY(Align.center));
			this.neuron.setVisible(true);
			this.animator.pause();
			return;
		}
		
		if (Gdx.input.isKeyPressed(Input.Keys.D)) {
			this.moveVector.add(this.tmp.set(Vector2.X).scl(this.moveSpeed));
			walking = !walking;
		}
		if (Gdx.input.isKeyPressed(Input.Keys.A)) {
			this.moveVector.add(this.tmp.set(Vector2.X).scl(-this.moveSpeed));
			walking = !walking;
		}
		
		this.tmp = null;
		
		this.moveVector.scl(dt);
		
		if (walking) {
			if (this.moveVector.x < 0f) {
				this.setScale(-1f, 1f);
			}
			else if (this.moveVector.x > 0f) {
				this.setScale(1f, 1f);
			}
		}
		
		this.moveBy(moveVector.x, moveVector.y);
		
		if (this.animator.animation().equals(this.idleAnim)) {
			if (walking) {
				this.animator.setAnimation(this.walkAnim);
				this.animator.restart();
			}
		}
		else if (this.animator.animation().equals(this.walkAnim)) {
			if (!walking) {
				this.animator.setAnimation(this.idleAnim);
			}
		}
	}
	
	@Override
	public void act(float dt) {
		super.act(dt);
		
		if (this.neuronMode) neuronModeInputProcessing();
		else movementInputProcessing(dt);
	}
	
	@Override
	public void setPosition(float x, float y) {
		super.setPosition(x-40f, y);
	}
	
}
