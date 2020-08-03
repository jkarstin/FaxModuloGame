package bytesmyth.games.edpg.actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public class Animator {
	
	private Animation<NinePatch> animation;
	private float elapsedTime;
	private boolean paused;
	
	public Animator() {
		this.animation = null;
		this.restart();
	}
	
	public void restart() {
		this.elapsedTime = 0f;
		this.resume();
	}
	
	public void resume() {
		this.paused = false;
	}
	
	public void pause() {
		this.paused = true;
	}
	
	public void step(float dt) {
		if (!this.paused) this.elapsedTime += dt;
	}
	
	public Animation<NinePatch> animation() {
		return this.animation;
	}
	
	public NinePatch getCurrentFrame() {
		return this.getKeyFrame(this.elapsedTime);
	}
	
	public NinePatch getKeyFrame(float stateTime) {
		if (this.animation == null) return null;
		return this.animation.getKeyFrame(stateTime);
	}
	
	public Animation<NinePatch> loadPatch(String filename, int left, int right, int top, int bottom) {
		return this.loadAnimationFromPatches(new String[] {filename}, left, right, top, bottom, 1f, true);
	}
	
	public Animation<NinePatch> loadAnimationFromPatches(String[] filenames, int left, int right, int top, int bottom, float frameDuration, boolean loop) {
		Array<NinePatch> patchArray = new Array<NinePatch>();
		
		for (String filename : filenames) {
			Texture tex = new Texture(Gdx.files.internal(filename));
			tex.setFilter(TextureFilter.Linear, TextureFilter.Linear);
			patchArray.add(new NinePatch(tex, left, right, top, bottom));
		}
		
		Animation<NinePatch> anim = new Animation<NinePatch>(frameDuration, patchArray);
		
		if (loop) anim.setPlayMode(PlayMode.LOOP);
		else	  anim.setPlayMode(PlayMode.NORMAL);
		
		if (this.animation == null) this.setAnimation(anim);
		
		return anim;
	}
	
	public Animation<NinePatch> loadTexture(String filename) {
		return this.loadPatch(filename, 0, 0, 0, 0);
	}
	
	public Animation<NinePatch> loadAnimationFromFiles(String[] filenames, float frameDuration, boolean loop) {
		return this.loadAnimationFromPatches(filenames, 0, 0, 0, 0, frameDuration, loop);
	}
	
	public Animation<NinePatch> loadAnimationFromSpritesheet(String filename, int rows, int cols, float frameDuration, boolean loop) {
		Texture tex = new Texture(Gdx.files.internal(filename));
		tex.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		int frameWidth  = tex.getWidth()  / cols;
		int frameHeight = tex.getHeight() / rows;
		
		TextureRegion[][] temp = TextureRegion.split(tex, frameWidth, frameHeight);
		Array<NinePatch> textureArr = new Array<NinePatch>();
		
		for (TextureRegion[] trRow : temp) for (TextureRegion tr : trRow) textureArr.add(new NinePatch(tr, 0, 0, 0, 0)); 
		
		Animation<NinePatch> anim = new Animation<NinePatch>(frameDuration, textureArr);
		
		if (loop) anim.setPlayMode(PlayMode.LOOP);
		else	  anim.setPlayMode(PlayMode.NORMAL);
		
		if (this.animation == null) this.setAnimation(anim);
		
		return anim;
	}
	
	public void setAnimation(Animation<NinePatch> anim) {
		this.animation = anim;
	}
	
}
