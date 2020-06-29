package bytesmyth.games.edpg.actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public class Animator {
	
	private Animation<TextureRegion> animation;
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
	
	public Animation<TextureRegion> animation() {
		return this.animation;
	}
	
	public TextureRegion getCurrentFrame() {
		return this.getKeyFrame(this.elapsedTime);
	}
	
	public TextureRegion getKeyFrame(float stateTime) {
		if (this.animation == null) return null;
		return this.animation.getKeyFrame(stateTime);
	}
	
	public Animation<TextureRegion> loadTexture(String filename) {
		return this.loadAnimationFromFiles(new String[] {filename}, 1f, true);
	}
	
	public Animation<TextureRegion> loadAnimationFromFiles(String[] filenames, float frameDuration, boolean loop) {
		Array<TextureRegion> textureArr = new Array<TextureRegion>();
		
		for (String filename : filenames) {
			Texture tex = new Texture(Gdx.files.internal(filename));
			tex.setFilter(TextureFilter.Linear, TextureFilter.Linear);
			textureArr.add(new TextureRegion(tex));
		}
		
		Animation<TextureRegion> anim = new Animation<TextureRegion>(frameDuration, textureArr);
		
		if (loop) anim.setPlayMode(PlayMode.LOOP);
		else	  anim.setPlayMode(PlayMode.NORMAL);
		
		if (this.animation == null) this.setAnimation(anim);
		
		return anim;
	}
	
	public Animation<TextureRegion> loadAnimationFromSpritesheet(String filename, int rows, int cols, float frameDuration, boolean loop) {
		Texture tex = new Texture(Gdx.files.internal(filename));
		tex.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		int frameWidth  = tex.getWidth()  / cols;
		int frameHeight = tex.getHeight() / rows;
		
		TextureRegion[][] temp = TextureRegion.split(tex, frameWidth, frameHeight);
		Array<TextureRegion> textureArr = new Array<TextureRegion>();
		
		for (TextureRegion[] trRow : temp) for (TextureRegion tr : trRow) textureArr.add(tr); 
		
		Animation<TextureRegion> anim = new Animation<TextureRegion>(frameDuration, textureArr);
		
		if (loop) anim.setPlayMode(PlayMode.LOOP);
		else	  anim.setPlayMode(PlayMode.NORMAL);
		
		if (this.animation == null) this.setAnimation(anim);
		
		return anim;
	}
	
	public void setAnimation(Animation<TextureRegion> anim) {
		this.animation = anim;
	}
	
}
