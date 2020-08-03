package bytesmyth.games.edpg.actor.object;

import com.badlogic.gdx.scenes.scene2d.Stage;

public class PatchObject extends GameObject {
	
	public PatchObject(OBJECT_TYP type, float x, float y, float width, float height, String filename, int left, int right, int top, int bottom, Stage s, Stage c) {
		super(type, x, y, width, height, s, c);
		
		this.loadPatch(filename, left, right, top, bottom);
		this.setSize(width, height);
	}
	public PatchObject(float x, float y, float width, float height, String filename, int left, int right, int top, int bottom, Stage s, Stage c) {
		this(OBJECT_TYP.Basic, x, y, width, height, filename, left, right, top, bottom, s, c);
	}
	public PatchObject(float width, float height, String filename, int left, int right, int top, int bottom, Stage s, Stage c) {
		this(0f, 0f, width, height, filename, left, right, top, bottom, s, c);
	}
	public PatchObject(String filename, int left, int right, int top, int bottom, Stage s, Stage c) {
		this(50f, 50f, filename, left, right, top, bottom, s, c);
	}
	
}
