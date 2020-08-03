package bytesmyth.games.edpg.actor;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Intersector.MinimumTranslationVector;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;

public abstract class Collider extends GameActor {
	
	/*** Enumerations ***/
	
	public static enum SHAPE_TYP {
		Box,
		Ellipse
	}
	
	/*** Fields ***/
	
	private Actor attached;
	private final SHAPE_TYP shapeType;
	protected Polygon boundaryPolygon;
	protected boolean physical;
	
	/*** Constructors ***/
	
	public Collider(Actor attached, SHAPE_TYP type, float x, float y, float width, float height, Stage c) {
		super(x, y, c);
		
		this.attached = attached;
		
		this.shapeType = type;
		this.physical = true;
		
		this.loadAnimations();
		
		this.setSize(width, height);
	}
	public Collider(SHAPE_TYP type, float x, float y, float width, float height, Stage c) { this(null, type, x, y, width, height, c); }
	public Collider(Actor attached, SHAPE_TYP type, Stage c) { this(attached, type, 0f, 0f, 50f, 50f, c); }
	public Collider(SHAPE_TYP type, Stage c) { this(null, type, c); }
	
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
	
	public Actor getAttached() {
		return this.attached;
	}
	
	public Polygon getBoundaryPolygon() {
		if (this.boundaryPolygon == null) return null;
		this.boundaryPolygon.setPosition(this.getX(), this.getY());
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
		this.setBoundaryPolygon(this.getWidth(), this.getHeight());
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
		if (other != null && this.isPhysical() && other.isPhysical()) {
			Polygon poly1 = this.getBoundaryPolygon();
			Polygon poly2 = other.getBoundaryPolygon();
			
			//initial test to improve performance
			if (!poly1.getBoundingRectangle().overlaps(poly2.getBoundingRectangle())) {
				return Vector2.Zero;
			}
			
			MinimumTranslationVector mtv = new MinimumTranslationVector();
			boolean polygonOverlap = Intersector.overlapConvexPolygons(poly1, poly2, mtv);
			
			if (!polygonOverlap) {
				return Vector2.Zero;
			}
			
//			this.moveBy(mtv.normal.x * mtv.depth, mtv.normal.y * mtv.depth);
			Vector2 tmp = new Vector2();
			return tmp.set(mtv.normal).scl(mtv.depth);
		}		
		return Vector2.Zero;
	}
	
	//Utility
	
	private void loadAnimations() {
		switch (this.shapeType) {
		case Box:
			this.loadPatch("gui/collision_wireframe_rect.png", 2, 2, 2, 2);
			break;
		case Ellipse:
			this.loadTexture("gui/collision_wireframe_round.png");
			break;
		default:
			break;
		}
	}
	
	//Overridden (Inherited or Required)
	
	@Override
	public void setSize(float width, float height) {
		super.setWidth(width);
		super.setHeight(height);
		this.setBoundaryPolygon();
	}
	
}
