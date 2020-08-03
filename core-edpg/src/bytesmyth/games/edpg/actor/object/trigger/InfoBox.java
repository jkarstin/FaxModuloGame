package bytesmyth.games.edpg.actor.object.trigger;

import com.badlogic.gdx.scenes.scene2d.Stage;

import bytesmyth.games.edpg.actor.HUD;

public class InfoBox extends Trigger {
	
	private final HUD hud;
	private String message;
	
	public InfoBox(HUD hud, String msg, float x, float y, float width, float height, Stage s, Stage c) {
		super(x, y, width, height, s, c);
		
		this.hud = hud;
		this.message = msg;
	}
	public InfoBox(HUD hud, String msg, float x, float y, Stage s, Stage c) {
		this(hud, msg, x, y, 50f, 50f, s, c);
	}
	public InfoBox(HUD hud, String msg, Stage s, Stage c) { this(hud, msg, 0f, 0f, s, c); }

	@Override
	public boolean activate() {
		this.hud.setInfoMessage(this.message);
		this.hud.showMessage();
		return true;
	}

	@Override
	public boolean deactivate() {
		this.hud.hideMessage();
		return true;
	}

}
