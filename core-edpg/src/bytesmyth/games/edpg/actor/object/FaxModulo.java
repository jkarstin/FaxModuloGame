package bytesmyth.games.edpg.actor.object;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Align;

import bytesmyth.games.edpg.actor.GameActor;
import bytesmyth.games.edpg.actor.HUD;
import bytesmyth.games.edpg.actor.Neuron;
import bytesmyth.games.edpg.actor.Neuron.DIRECTION;

public class FaxModulo extends PhysObject {
	
	/*** Fields ***/
	
	private HUD hud;
	
	private float moveSpeed = 150f;
	private Animation<TextureRegion> idleAnim, walkAnim;
	
	private Neuron neuron;
	private boolean neuronMode;
	
	/*** Constructors ***/
	
	public FaxModulo(float x, float y) {
		super(0f, 0f);
		
		this.loadAnimations();
		this.setPosition(x, y);
		this.velocity.y = -GROUNDING_DISTANCE*15f;
		this.grounded = true;
		
		this.neuron = new Neuron();
		this.neuron.setVisible(false);
		this.neuronMode = false;
		
		this.hud = new HUD();
	}
	public FaxModulo() { this(0f, 0f); }

	public FaxModulo(float x, float y, Stage s, Stage ui) {
		super(0f, 0f, s);
		
		this.loadAnimations();
		this.setPosition(x, y);
		this.velocity.y = -GROUNDING_DISTANCE*15f;
		this.grounded = true;
		
		this.neuron = new Neuron(s);
		this.neuron.setVisible(false);
		this.neuronMode = false;
		
		this.hud = new HUD(ui);
	}
	public FaxModulo(Stage s, Stage ui) { this(0f, 0f, s, ui); }
	
	/*** Methods ***/
	
	//Mechanics
	
	public void collect(Dopamine dp) {
		this.hud.setJumps(this.hud.getJumps()+1);
	}
	
	//Player input
	
	private void neuronModeInputProcessing() {
		if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE) ||
			Gdx.input.isKeyJustPressed(Input.Keys.SHIFT_LEFT)) {
			this.neuronMode = false;
			this.neuron.setVisible(false);
			this.neuron.setDirection(DIRECTION.Center);
			this.animator.resume();
		}
		
		if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
			this.neuronMode = false;
			this.setPosition(this.neuron.getReturnPoint().x, this.neuron.getReturnPoint().y, Align.center);
			this.setVelocity(this.neuron.getDirectionVector().scl(500f));
			this.neuron.setVisible(false);
			this.neuron.setDirection(DIRECTION.Center);
			
			if (!this.grounded)
				this.hud.setJumps(this.hud.getJumps()-1);
			
			this.animator.resume();
		}
		
		if (Gdx.input.isKeyJustPressed(Input.Keys.D) ||
				Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)) {
			switch (this.neuron.getDirection()) {
			case Up:
			case UpLeft:
				this.neuron.setDirection(DIRECTION.UpRight);
				break;
			case Down:
			case DownLeft:
				this.neuron.setDirection(DIRECTION.DownRight);
				break;
			default:
				this.neuron.setDirection(DIRECTION.Right);
				break;
			}
		}
		if (Gdx.input.isKeyJustPressed(Input.Keys.A) ||
				Gdx.input.isKeyJustPressed(Input.Keys.LEFT)) {
			switch (this.neuron.getDirection()) {
			case Up:
			case UpRight:
				this.neuron.setDirection(DIRECTION.UpLeft);
				break;
			case Down:
			case DownRight:
				this.neuron.setDirection(DIRECTION.DownLeft);
				break;
			default:
				this.neuron.setDirection(DIRECTION.Left);
				break;
			}
		}
		if (Gdx.input.isKeyJustPressed(Input.Keys.W) ||
				Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
			switch (this.neuron.getDirection()) {
			case Right:
			case DownRight:
				this.neuron.setDirection(DIRECTION.UpRight);
				break;
			case Left:
			case DownLeft:
				this.neuron.setDirection(DIRECTION.UpLeft);
				break;
			default:
				this.neuron.setDirection(DIRECTION.Up);
				break;
			}
		}
		if (Gdx.input.isKeyJustPressed(Input.Keys.S) ||
				Gdx.input.isKeyJustPressed(Input.Keys.DOWN)) {
			switch (this.neuron.getDirection()) {
			case Right:
			case UpRight:
				this.neuron.setDirection(DIRECTION.DownRight);
				break;
			case Left:
			case UpLeft:
				this.neuron.setDirection(DIRECTION.DownLeft);
				break;
			default:
				this.neuron.setDirection(DIRECTION.Down);
				break;
			}
		}
	}
	
	private void movementInputProcessing(float dt) {
		Vector2 moveVector = new Vector2();
		
		if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
			Gdx.app.exit();
		}
		
		boolean walking = false;
		Vector2 tmp = new Vector2();
		
		if (Gdx.input.isKeyJustPressed(Input.Keys.SHIFT_LEFT) && this.hud.getJumps() > 0) {
			this.neuronMode = true;
			this.neuron.setPosition(this.getX(Align.center), this.getY(Align.center));
			this.neuron.setVisible(true);
			this.animator.pause();
			return;
		}
		
		if (Gdx.input.isKeyPressed(Input.Keys.D) ||
				Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
			moveVector.add(tmp.set(Vector2.X).scl(this.moveSpeed));
			walking = !walking;
		}
		if (Gdx.input.isKeyPressed(Input.Keys.A) ||
				Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
			moveVector.add(tmp.set(Vector2.X).scl(-this.moveSpeed));
			walking = !walking;
		}
		
		if (this.grounded && Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
			this.velocity.y = 350f;
		}
		
		tmp = null;
		
		if (walking) {
			if (moveVector.x < 0f) {
				this.setScale(-1f, 1f);
			}
			else if (moveVector.x > 0f) {
				this.setScale(1f, 1f);
			}
		}
		
		this.velocity.x = moveVector.x;
		
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
	
	//Utility
	
	private void loadAnimations() {
		this.idleAnim = this.loadTexture("jex_modem.png");
		this.walkAnim = this.loadAnimationFromSpritesheet("jex_modem_walk.png", 2, 4, 0.08f, true);
		this.animator.setAnimation(this.idleAnim);
		this.setSize(this.animator.getKeyFrame(0f).getRegionWidth(), this.animator.getKeyFrame(0f).getRegionHeight());
	}
	
	//Overridden (Inherited or Required)
	
	@Override
	public void act(float dt) {		
		if (this.neuronMode) {
			neuronModeInputProcessing();
		}
		else {
			super.act(dt);
			
			this.getStage().getCamera().position.x = this.getX(Align.center);
			this.getStage().getCamera().position.y = this.getY(Align.center);
			
			movementInputProcessing(dt);
			
			for (GameActor actor : GameActor.getList(this.getStage(), Trigger.class)) {
				Trigger trigger = (Trigger)actor;
				
				if (this.collider.overlaps(trigger.collider)) {
					trigger.activate(this);
				}
			}
		}
	}
	
	@Override
	protected void setGrounded() {
		super.setGrounded();
		
		this.hud.setJumps(3);
	}
	
	@Override
	public void setPosition(float x, float y) {
		super.setPosition(x-40f, y);
	}
	
}
