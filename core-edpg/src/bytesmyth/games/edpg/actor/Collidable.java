package bytesmyth.games.edpg.actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Intersector.MinimumTranslationVector;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;

import bytesmyth.games.edpg.util.MetaData;

public class Collidable extends GameActor {
	
	public static final int COLLISION_SQUARE = 0;
	public static final int COLLISION_ROUND  = 1;
	
	protected Polygon boundaryPolygon;
	protected Array<TextureRegion> collisionWireframes;
	
	protected float collisionWidth;
	protected float collisionHeight;
	protected float collisionX;
	protected float collisionY;
	protected int   collisionShape;
	
	protected boolean physicalCollision;
	
	public Collidable(float x, float y, Stage s) {
		super(x, y, s);
		
		//Collision field initialization
		this.collisionWireframes = new Array<TextureRegion>();
		Texture texture = new Texture(Gdx.files.internal("gui/collision_wireframe_rect.png"));
		texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		this.collisionWireframes.add(new TextureRegion(texture));
		texture = new Texture(Gdx.files.internal("gui/collision_wireframe_round.png"));
		texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		this.collisionWireframes.add(new TextureRegion(texture));
		
		this.collisionWidth  = 0f;
		this.collisionHeight = 0f;
		this.collisionX      = 0f;
		this.collisionY      = 0f;
		
		this.collisionShape = COLLISION_ROUND;
		
		this.physicalCollision = true;
	}
	
	public Collidable(float x, float y, String textureFileName, Stage s) {
		this(x, y, s);
		this.animator.loadTexture(textureFileName);
		this.setCollisionSize(this.getWidth(), this.getWidth()/2f);
		this.setCollisionLocation(0f, this.collisionHeight/3f);
	}
	
	private void setCollisionLocation(float x, float y) {
		this.collisionX = x;
		this.collisionY = y;
	}

//	@Override
//	public void setAnimation(Animation<TextureRegion> animation) {
//		super.setAnimation(animation);
//		
//		//Since size was just set, set boundary rectangle
//		if (this.boundaryPolygon == null) {
//			this.setBoundaryRectangle();
//		}
//	}
	
	/*** Collisions methods ***/
	
	public void setPhysicalCollisions(boolean state) {
		this.physicalCollision = state;
	}
	
	public boolean hasPhysicalCollisions() {
		return this.physicalCollision;
	}
	
	public void setBoundaryRectangle() {
		this.setBoundaryRectangle(this.getWidth(), this.getHeight());
	}
	
	public void setBoundaryRectangle(float w, float h) {
		float[] vertices = {
				0f, 0f,
				 w, 0f,
				 w,  h,
				0f,  h
				};
		this.boundaryPolygon = new Polygon(vertices);
	}
	
	public void setBoundaryPolygon(int numSides) {
		this.setBoundaryPolygon(this.getWidth(), this.getHeight(), numSides);
	}
	
	public void setBoundaryPolygon(float w, float h, int numSides) {
		float[] vertices = new float[2*numSides];
		
		for (int i=0; i < numSides; i++) {
			float angle = i * MathUtils.PI2 / numSides;
			//x-coordinate
			vertices[2*i] = w/2 * MathUtils.cos(angle) + w/2;
			//y-coordinate
			vertices[2*i+1] = h/2 * MathUtils.sin(angle) + h/2;
		}
		
		this.boundaryPolygon = new Polygon(vertices);
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
	
	public boolean overlaps(Collidable other) {
		Polygon poly1 = this.getBoundaryPolygon();
		Polygon poly2 = other.getBoundaryPolygon();
		
		//initial test to improve performance (MUCH more efficient collision detection algorithm)
		if (!poly1.getBoundingRectangle().overlaps(poly2.getBoundingRectangle())) {
			return false;
		}
		
		return Intersector.overlapConvexPolygons(poly1, poly2);
	}
	
	public boolean isWithinDistance(float distance, Collidable other) {
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
	
	public Vector2 preventOverlap(Collidable other) {
		if (this.hasPhysicalCollisions() && other.hasPhysicalCollisions()) {
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
			
			this.moveBy(mtv.normal.x * mtv.depth, mtv.normal.y * mtv.depth);
			return mtv.normal;
		}
		return Vector2.Zero;
	}
	
	public void setCollisionShape(int shape) {
		this.collisionShape = shape;
	}
	
	public void setCollisionSize(float w, float h) {
		this.collisionWidth  = w;
		this.collisionHeight = h;
		switch (this.collisionShape) {
		case COLLISION_SQUARE:
			this.setBoundaryRectangle(this.collisionWidth, this.collisionHeight);
			break;
		default:
		case COLLISION_ROUND:
			this.setBoundaryPolygon(this.collisionWidth, this.collisionHeight, 8);
			break;
		};
	}
	
	public float getCollisionWidth() {
		return this.collisionWidth;
	}
	
	public float getCollisionHeight() {
		return this.collisionHeight;
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		
		if (this.boundaryPolygon != null && MetaData.SHOW_WIREFRAMES) {
			Polygon bpoly = this.getBoundaryPolygon();
			batch.draw(
				this.collisionWireframes.get(this.collisionShape),
				bpoly.getX(),
				bpoly.getY(),
				bpoly.getOriginX(),
				bpoly.getOriginY(),
				this.collisionWidth,
				this.collisionHeight,
				bpoly.getScaleX(),
				bpoly.getScaleY(),
				bpoly.getRotation()
			);
		}
	}
	
}