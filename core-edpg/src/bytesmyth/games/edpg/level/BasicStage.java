package bytesmyth.games.edpg.level;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;

public class BasicStage extends Stage {
	
	public BasicStage() { }
	public BasicStage(Viewport viewport) { super(viewport); }
	public BasicStage(Viewport viewport, Batch batch) { super(viewport, batch); }
	
	public ArrayList<Actor> getList(Class<? extends Actor> actorClass) {
		ArrayList<Actor> actors = new ArrayList<Actor>();
		
		for (Actor a : this.getActors()) {
			if (actorClass.isInstance(a))
				actors.add(a);
		}
		
		return actors;
	}
	
}
