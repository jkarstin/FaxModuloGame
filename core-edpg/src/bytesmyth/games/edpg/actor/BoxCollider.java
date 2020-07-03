package bytesmyth.games.edpg.actor;

import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class BoxCollider extends Collider {
	
	/*** Constructors ***/
	
	public BoxCollider(float x, float y, float width, float height) {
		super(SHAPE_TYP.Box, x, y, width, height);
	}
	public BoxCollider() { this(0f, 0f, 50f, 50f); }

	public BoxCollider(float x, float y, float width, float height, Stage s) {
		super(SHAPE_TYP.Box, x, y, width, height, s);
	}
	public BoxCollider(Stage s) { this(0f, 0f, 50f, 50f, s); }
	
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
