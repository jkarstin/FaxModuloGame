package bytesmyth.games.edpg.actor;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Align;

import bytesmyth.games.edpg.util.MetaData;

public class HUD extends GameActor {
	
	private int gears;
	private int jumps;
	
	private GameActor gearsActor;
	private GameActor jumpsActor;
	
	private ArrayList<Animation<TextureRegion>> gearsAnims;
	private ArrayList<Animation<TextureRegion>> jumpsAnims;
	
	public HUD(float x, float y, Stage s) {
		super(x, y, s);
		
		this.gears = 3;
		this.jumps = 3;
		
		this.gearsActor = new GameActor(s);
		this.jumpsActor = new GameActor(s);
		
		this.addActor(this.gearsActor);
		this.addActor(this.jumpsActor);
		
		this.gearsAnims = new ArrayList<Animation<TextureRegion>>();
		this.gearsAnims.add(this.gearsActor.loadTexture("hud_gear_meter_0.png"));
		this.gearsAnims.add(this.gearsActor.loadTexture("hud_gear_meter_1.png"));
		this.gearsAnims.add(this.gearsActor.loadTexture("hud_gear_meter_2.png"));
		this.gearsAnims.add(this.gearsActor.loadTexture("hud_gear_meter_3.png"));
		this.jumpsAnims = new ArrayList<Animation<TextureRegion>>();
		this.jumpsAnims.add(this.jumpsActor.loadTexture("hud_jump_meter_0.png"));
		this.jumpsAnims.add(this.jumpsActor.loadTexture("hud_jump_meter_1.png"));
		this.jumpsAnims.add(this.jumpsActor.loadTexture("hud_jump_meter_2.png"));
		this.jumpsAnims.add(this.jumpsActor.loadTexture("hud_jump_meter_3.png"));
		this.jumpsAnims.add(this.jumpsActor.loadTexture("hud_jump_meter_4.png"));
		this.jumpsAnims.add(this.jumpsActor.loadTexture("hud_jump_meter_5.png"));
		
		this.updateHUD();
		
		this.setSize(this.jumpsActor.getWidth(), this.jumpsActor.getHeight());
		this.setPosition(x, y, Align.topLeft);
	}
	public HUD(Stage s) { this(0f, MetaData.VIRTUAL_HEIGHT, s); }
	
	/*** Methods ***/
	
	//Setters
	
	public void setGears(int gears) {
		this.gears = gears;
		this.updateHUD();
	}
	
	public void setJumps(int jumps) {
		this.jumps = jumps;
		this.updateHUD();
	}
	
	//Getters
	
	public int getGears() {
		return this.gears;
	}
	
	public int getJumps() {
		return this.jumps;
	}
	
	//Utility
	
	public void updateHUD() {
		this.gearsActor.animator.setAnimation(this.gearsAnims.get(this.gears));
		this.jumpsActor.animator.setAnimation(this.jumpsAnims.get(this.jumps));
	}
	
}
