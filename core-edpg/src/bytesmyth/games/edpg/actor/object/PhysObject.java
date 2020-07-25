package bytesmyth.games.edpg.actor.object;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;

import bytesmyth.games.edpg.actor.BoxCollider;
import bytesmyth.games.edpg.actor.Collider;
import bytesmyth.games.edpg.actor.GameActor;

public class PhysObject extends GameObject {
	
	/*** Fields ***/
	
	public static final Vector2 GRAVITY = new Vector2(0f, -500f);
	protected static final float GROUNDING_DISTANCE = 8f;
	private static final float GROUNDING_LATERAL_PADDING = 2f;
	
	protected Vector2 velocity;
	protected Vector2 acceleration;
	protected float frictionCoef;
	protected float mass;
	
	protected boolean applyGravity;
	protected boolean grounded;
	protected Collider groundDetector;
	
	/*** Constructors ***/
	
	public PhysObject(float x, float y, float width, float height, Stage s) {
		super(OBJECT_TYP.Physics, x, y, width, height, s);
		
		this.velocity = new Vector2();
		this.acceleration = new Vector2();
		this.frictionCoef = 0f;
		this.mass = 1f;
		
		this.applyGravity = true;
		this.grounded = false;
		this.groundDetector = new BoxCollider(
				x+GROUNDING_LATERAL_PADDING,
				y-GROUNDING_DISTANCE,
				50f-(2*GROUNDING_LATERAL_PADDING),
				GROUNDING_DISTANCE,
				s
				);
		this.groundDetector.setPhysical(false);
	}
	public PhysObject(float x, float y, Stage s) {
		this(x, y, 50f, 50f, s);
	}
	public PhysObject(Stage s) { this(0f, 0f, s); }
	
	/*** Methods ***/
	
	//Getters
	
	public boolean affectedByGravity() {
		return this.applyGravity;
	}
	
	//Setters
	
	public void setFriction(float coef) {
		if (coef < 0f) coef = 0f;
		else if (coef > 1f) coef = 1f;
		this.frictionCoef = coef;
	}
	
	public void setMass(float mass) {
		if (mass < 0f) mass = 0f;
		this.mass = mass;
	}
	
	public void setAffectedByGravity(boolean state) {
		this.applyGravity = state;
	}
	
	//Physics methods
	
	public void applyForce(Vector2 force) {
		Vector2 accel = new Vector2();
		accel.set(force).scl(1f/this.mass);
		this.addVelocity(accel);
	}
	
	public void accelerate(Vector2 accel) {
		this.acceleration.add(accel);
	}
	
	public void setAcceleration(Vector2 accel) {
		this.acceleration.set(accel);
	}
	
	public void addVelocity(Vector2 vel) {
		this.velocity.add(vel);
	}
	
	public void setVelocity(Vector2 vel) {
		this.velocity.set(vel);
	}
	
	public boolean isFalling() {
		return this.applyGravity && !this.grounded;
	}
	
	protected void setGrounded() {
		this.grounded = true;
		this.setVelocity(new Vector2(0f, -GROUNDING_DISTANCE*15f));
	}
	
	//Overridden (Inherited or Required)
	
	@Override
	public void act(float dt) {
		super.act(dt);
		
		Vector2 tmp = new Vector2();
		
		this.addVelocity(tmp.set(this.acceleration).scl(dt));
		
		if (this.frictionCoef > 0f) {
			this.addVelocity(tmp.set(this.velocity).scl(-this.frictionCoef));
		}
		
		if (this.isFalling()) {
			this.addVelocity(tmp.set(GRAVITY).scl(dt));
		}
		
		tmp.set(this.velocity).scl(dt);
		
		this.moveBy(tmp.x, tmp.y);
		
		boolean groundCheck = false;
		
		//TODO: Sum up friction of PhysObject and friction of StaticObject to determine friction affect
		for (GameActor actor : GameActor.getList(this.getStage(), GameObject.class)) {
			//Skip over reference to current PhysObject
			if (actor.equals(this)) continue;
			
			GameObject obj = (GameObject)actor;
			
			Vector2 adjustment = this.collider.preventOverlap(obj.collider);
			this.moveBy(adjustment.x, adjustment.y);
			
			switch (obj.getObjectType()) {
			case Physics:
//				PhysObject pobj = (PhysObject)obj;
				
				//TODO: What happens upon physical interactions between PhysObjects?
				
				break;
				
			case Basic:
			case Static:
				if (adjustment.x > 0f || adjustment.x < 0f) {
					this.acceleration.x = 0f;
					this.velocity.x = 0f;
				}
				if (adjustment.y > 0f || adjustment.y < 0f) {
					this.acceleration.y = 0f;
					this.velocity.y = 0f;
				}
				
				if (!groundCheck && this.groundDetector.overlaps(obj.collider)) groundCheck = true;
				
				if (this.isFalling() && this.groundDetector.overlaps(obj.collider)) {
					this.setGrounded();
				}
				
				break;
				
			case Trigger:
			default:
				break;
			}
		}
		
		if (!groundCheck) this.grounded = false;
	}
	
	@Override
	public void moveBy(float x, float y) {
		super.moveBy(x, y);
		if (this.groundDetector != null) this.groundDetector.moveBy(x, y);
	}
	
	@Override
	public void setPosition(float x, float y) {
		super.setPosition(x, y);
		if (this.groundDetector != null) this.groundDetector.setPosition(x+GROUNDING_LATERAL_PADDING, y-GROUNDING_DISTANCE);
	}
	
	@Override
	public void setPosition(float x, float y, int alignment) {
		super.setPosition(x, y, alignment);
		if (this.groundDetector != null) this.groundDetector.setPosition(this.getX()+GROUNDING_LATERAL_PADDING, this.getY()-GROUNDING_DISTANCE);
	}
	
	@Override
	public void setSize(float width, float height) {
		super.setSize(width, height);
		if (this.groundDetector != null) this.groundDetector.setWidth(width-(2*GROUNDING_LATERAL_PADDING));
	}
	
}
