package bytesmyth.games.edpg.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class Assets {
	
	/*** Static Fields ***/
	
	public static Skin skin;
	private static final String SKIN_FILE = "uiskin";
	
	/*** Static Methods ***/
	
	public static void dispose() { Assets.skin.dispose(); }
	
	/*** Constructors ***/
	
	public Assets() {
		Assets.skin = new Skin();
		FileHandle fileHandle = Gdx.files.internal("gui/" + SKIN_FILE + ".json");
		FileHandle atlasFile = fileHandle.sibling(SKIN_FILE + ".atlas");
		if (atlasFile.exists()) Assets.skin.addRegions(new TextureAtlas(atlasFile));
		Assets.skin.load(fileHandle);
	}
	
}
