package bytesmyth.games.edpg.actor;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Intersector.MinimumTranslationVector;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;

public abstract class Collider extends GameActor {
	
	/*** Enumerations ***/
	
	public static enum SHAPE_TYP {
		Box,
		Ellipse
	}
	
	/*** Fields ***/
	
	private final SHAPE_TYP shapeType;
	protected Polygon boundaryPolygon;
	protected boolean physical;
	
	//Temporary hold-overs from previous design
	protected float collisionX, collisionY, collisionWidth, collisionHeight;
	
	/*** Constructors ***/
	
	public Collider(SHAPE_TYP type, float x, float y) {
		super(x, y);
		
		this.shapeType = type;
		this.physical = true;
		
		//Temporary hold-overs from previous design
		this.collisionX = 0f;
		this.collisionY = 0f;
		this.collisionWidth = 1f;
		this.collisionHeight = 1f;
	}
	public Collider(SHAPE_TYP type) { this(type, 0f, 0f); }
	
	public Collider(SHAPE_TYP type, float x, float y, Stage s) {
		super(x, y, s);
		
		this.shapeType = type;
		this.physical = true;
		
		//Temporary hold-overs from previous design
		this.collisionX = 0f;
		this.collisionY = 0f;
		this.collisionWidth = 1f;
		this.collisionHeight = 1f;
	}
	public Collider(SHAPE_TYP type, Stage s) { this(type, 0f, 0f, s); }
	
	/*** Methods ***/
	
	//Abstract
	
	//Dependent on shape of Collider
	public abstract void setBoundaryPolygon(float width, float height);
	
	//Getters
	
	public SHAPE_TYP getShapeType() {
		return this.shapeType;
	}
	
	public boolean isPhysical() {
		return this.physical;
	}
	
	public Polygon getBoundaryPolygon() {
		if (this.boundaryPolygon == null) return null;
		this.boundaryPolygon.setPosition(
			this.getX()+this.collisionX+(this.getWidth()-this.collisionWidth)/2f,
			this.getY()+this.collisionY-(this.collisionHeight)/2f
		);
		this.boundaryPolygon.setOrigin(this.getOriginX(), this.getOriginY());
		this.boundaryPolygon.setRotation(this.getRotation());
		this.boundaryPolygon.setScale(this.getScaleX(), this.getScaleY());
		return this.boundaryPolygon;
	}
	
	//Setters
	
	public void setPhysical(boolean state) {
		this.physical = state;
	}
	
	public void setBoundaryPolygon() {
		this.setBoundaryPolygon(this.collisionWidth, this.collisionHeight);
	}
	
	public void setCollisionSize(float w, float h) {
		this.collisionWidth  = w;
		this.collisionHeight = h;
		this.setBoundaryPolygon();
	}
	
	//Functional
	
	public boolean isWithinDistance(float distance, Collider other) {
		Polygon poly1 = this.getBoundaryPolygon();
		float scaleX = (this.getWidth() + 2 * distance) / this.getWidth();
		float scaleY = (this.getHeight() + 2 * distance) / this.getHeight();
		poly1.setScale(scaleX, scaleY);
		
		Polygon poly2 = other.getBoundaryPolygon();
		
		if (!poly1.getBoundingRectangle().overlaps(poly2.getBoundingRectangle())) {
			return false;
		}
		
		return Intersector.overlapConvexPolygons(poly1, poly2);
	}
	
	public boolean overlaps(Collider other) {
		Polygon poly1 = this.getBoundaryPolygon();
		Polygon poly2 = other.getBoundaryPolygon();
		
		//initial test to improve performance (MUCH more efficient collision detection algorithm)
		if (!poly1.getBoundingRectangle().overlaps(poly2.getBoundingRectangle())) {
			return false;
		}
		
		return Intersector.overlapConvexPolygons(poly1, poly2);
	}
	
	public Vector2 preventOverlap(Collider other) {
		if (this.isPhysical() && other.isPhysical()) {
			Polygon poly1 = this.getBoundaryPolygon();
			Polygon poly2 = other.getBoundaryPolygon();
			
			//initial test to improve performance
			if (!poly1.getBoundingRectangle().overlaps(poly2.getBoundingRectangle())) {
				return null;
			}
			
			MinimumTranslationVector mtv = new MinimumTranslationVector();
			boolean polygonOverlap = Intersector.overlapConvexPolygons(poly1, poly2, mtv);
			
			if (!polygonOverlap) {
				return null;
			}
			
//			this.moveBy(mtv.normal.x * mtv.depth, mtv.normal.y * mtv.depth);
			Vector2 tmp = new Vector2();
			return tmp.set(mtv.normal).scl(mtv.depth);
		}
		return Vector2.Zero;
	}
	
}
