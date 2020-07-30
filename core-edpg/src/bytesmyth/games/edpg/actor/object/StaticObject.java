package bytesmyth.games.edpg.actor.object;

import com.badlogic.gdx.scenes.scene2d.Stage;

public class StaticObject extends GameObject {
	
	public StaticObject(float x, float y, float width, float height, Stage s, Stage c) {
		super(OBJECT_TYP.Static, x, y, width, height, s, c);
	}
	public StaticObject(float x, float y, Stage s, Stage c) {
		this(x, y, 50f, 50f, s, c);
	}
	public StaticObject(Stage s, Stage c) { this(0f, 0f, s, c); }
	
	/*** Methods ***/
	
	//Overridden (Inherited or Required)
	
	@Override
	public void setPosition(float x, float y) { }
	
	@Override
	public void setPosition(float x, float y, int alignment) { }
	
	@Override
	public void setX(float x) { }
	
	@Override
	public void setX(float x, int alignment) { }
	
	@Override
	public void setY(float y) { }
	
	@Override
	public void setY(float y, int alignment) { }
	
	@Override
	public void setRotation(float degrees) { }
	
	@Override
	public void setScale(float scaleX, float scaleY) { }
	
	@Override
	public void setScale(float scaleXY) { }
	
	@Override
	public void setScaleX(float scaleX) { }
	
	@Override
	public void setScaleY(float scaleY) { }
	
	@Override
	public void setSize(float width, float height) { }
	
	@Override
	public void setHeight(float height) { }
	
	@Override
	public void setWidth(float width) { }
	
	@Override
	public void moveBy(float x, float y) { }
	
	@Override
	public void rotateBy(float amountInDegrees) { }
	
	@Override
	public void scaleBy(float scale) { }
	
	@Override
	public void scaleBy(float scaleX, float scaleY) { }
	
	@Override
	public void sizeBy(float size) { }
	
	@Override
	public void sizeBy(float width, float height) { }
	
	@Override
	public void updateSize() { }
	
}