package bytesmyth.games.edpg.actor;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class RoundCollider extends Collider {
	
	/*** Fields ***/
	
	private int numSides;
	
	/*** Constructors ***/
	
	public RoundCollider(float x, float y, float width, float height) {
		super(SHAPE_TYP.Ellipse, x, y, width, height);
		
		this.setNumSides(8);
	}
	public RoundCollider() { this(0f, 0f, 50f, 50f); }

	public RoundCollider(float x, float y, float width, float height, Stage s) {
		super(SHAPE_TYP.Ellipse, x, y, width, height, s);
		
		this.setNumSides(8);
	}
	public RoundCollider(Stage s) { this(0f, 0f, 50f, 50f, s); }
	
	/*** Methods ***/
	
	//Getters
	
	public int getNumSides() {
		return this.numSides;
	}
	
	//Setters
	
	public void setNumSides(int n) {
		this.numSides = n;
		this.setBoundaryPolygon();
	}
	
	//Overridden (Inherited or Required)

	@Override
	public void setBoundaryPolygon(float width, float height) {
		float[] vertices = new float[2*this.numSides];
		
		for (int i=0; i < this.numSides; i++) {
			float angle = i * MathUtils.PI2 / this.numSides;
			//x-coordinate
			vertices[2*i] = width/2 * MathUtils.cos(angle) + width/2;
			//y-coordinate
			vertices[2*i+1] = height/2 * MathUtils.sin(angle) + height/2;
		}
		
		this.boundaryPolygon = new Polygon(vertices);
	}
	
	@Override
	public void setSize(float width, float height) {
		super.setWidth(width);
		super.setHeight(height);
		if (this.numSides < 3) this.numSides = 3;
		this.setBoundaryPolygon();
	}

}
