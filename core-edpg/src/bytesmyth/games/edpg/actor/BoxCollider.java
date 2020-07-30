package bytesmyth.games.edpg.actor;

import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class BoxCollider extends Collider {
	
	/*** Constructors ***/
	
	public BoxCollider(GameActor attached, float x, float y, float width, float height, Stage c) {
		super(attached, SHAPE_TYP.Box, x, y, width, height, c);
	}
	public BoxCollider(float x, float y, float width, float height, Stage c) {
		this(null, x, y, width, height, c);
	}
	public BoxCollider(GameActor attached, Stage c) {
		this(null, 0f, 0f, 50f, 50f, c);
	}
	public BoxCollider(Stage c) { this(null, c); }
	
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
