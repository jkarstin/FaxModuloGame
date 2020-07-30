package bytesmyth.games.edpg.actor.object;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;

import bytesmyth.games.edpg.util.Assets;

public class InfoBox extends Trigger {
	
	private Label infoLabel;
	
	public InfoBox(String msg, float x, float y, float width, float height, Stage s, Stage c) {
		super(x, y, width, height, s, c);

		this.infoLabel = new Label(msg, Assets.skin);
		this.addActor(this.infoLabel);
		this.infoLabel.setPosition(this.getWidth()/2f, this.getHeight(), Align.top);
		this.infoLabel.setVisible(false);
	}
	public InfoBox(String msg, float x, float y, Stage s, Stage c) {
		this(msg, x, y, 50f, 50f, s, c);
	}
	public InfoBox(String msg, Stage s, Stage c) { this(msg, 0f, 0f, s, c); }

	@Override
	public boolean activate() {
		if (this.infoLabel == null) return false;
		
		this.infoLabel.setVisible(true);
		return true;
	}

	@Override
	public boolean deactivate() {
		if (this.infoLabel == null) return false;
		
		this.infoLabel.setVisible(false);
		return true;
	}

}
