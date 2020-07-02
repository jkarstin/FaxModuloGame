package bytesmyth.games.edpg.actor;

import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class BoxCollider extends Collider {
	
	/*** Constructors ***/
	
	public BoxCollider(float x, float y) {
		super(SHAPE_TYP.Box, x, y);
	}
	public BoxCollider() { this(0f, 0f); }

	public BoxCollider(float x, float y, Stage s) {
		super(SHAPE_TYP.Box, x, y, s);
	}
	public BoxCollider(Stage s) { this(0f, 0f, s); }
	
	/*** Methods ***/
	
	//Overridden (Inherited or Required)

	@Override
	public void setBoundaryPolygon(float width, float height) {
		float[] vertices = {
				   0f,     0f,
				width,     0f,
				width, height,
				   0f, height
				};
		this.boundaryPolygon = new Polygon(vertices);
	}

}
