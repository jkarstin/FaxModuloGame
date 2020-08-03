package bytesmyth.games.edpg.actor;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;

import bytesmyth.games.edpg.util.Assets;
import bytesmyth.games.edpg.util.MetaData;

public class HUD extends GameActor {
	
	private int gears;
	private int jumps;
	
	private GameActor gearsActor;
	private GameActor jumpsActor;
	private Label infoLabel;
	
	private ArrayList<Animation<NinePatch>> gearsAnims;
	private ArrayList<Animation<NinePatch>> jumpsAnims;
	
	public HUD(float x, float y, Stage ui) {
		super(x, y, ui);
		
		this.gears = 3;
		this.jumps = 3;
		
		this.gearsActor = new GameActor(ui);
		this.jumpsActor = new GameActor(ui);
		
		this.infoLabel = new Label("", Assets.skin);
		this.infoLabel.setPosition(MetaData.VIRTUAL_WIDTH/2f, MetaData.VIRTUAL_HEIGHT/4f, Align.center);
		this.infoLabel.setVisible(false);
		ui.addActor(this.infoLabel);
		
		this.addActor(this.gearsActor);
		this.addActor(this.jumpsActor);
		
		this.gearsAnims = new ArrayList<Animation<NinePatch>>();
		this.gearsAnims.add(this.gearsActor.loadTexture("hud_gear_meter_0.png"));
		this.gearsAnims.add(this.gearsActor.loadTexture("hud_gear_meter_1.png"));
		this.gearsAnims.add(this.gearsActor.loadTexture("hud_gear_meter_2.png"));
		this.gearsAnims.add(this.gearsActor.loadTexture("hud_gear_meter_3.png"));
		this.jumpsAnims = new ArrayList<Animation<NinePatch>>();
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
	public void setInfoMessage(String message) {
		this.infoLabel.setText(message);
		this.infoLabel.pack();
		this.infoLabel.setPosition(MetaData.VIRTUAL_WIDTH/2f, MetaData.VIRTUAL_HEIGHT/4f, Align.center);
	}
	public void showMessage() {
		this.infoLabel.setVisible(true);
	}
	public void hideMessage() {
		this.infoLabel.setVisible(false);
	}
	
}
