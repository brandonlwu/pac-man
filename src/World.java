import java.util.ArrayList;

import javafx.animation.AnimationTimer;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

public abstract class World extends Pane {
	
	private AnimationTimer timer;
	
	public World() {
		timer = new AnimationTimer() {
			@Override
			public void handle(long arg) {
				act(arg);
				start();
				try {
				for(Node a : getChildren()) {
					if(a instanceof Actor) {
						((Actor)a).act();
					}
				}
				} catch (java.util.ConcurrentModificationException err) {
			
				}
			}
		};
	}

	public abstract void act(long now);

	public void start() {
		timer.start();
	}

	public void stop() {
		timer.stop();
	}

	public void add(Actor actor) {
		getChildren().add(actor);
	}

	public void remove(Actor actor) {
			getChildren().remove(actor);
	}

	public <A extends Actor> java.util.List<A> getObjects(java.lang.Class<A> cls){
		ArrayList<A> list = new ArrayList<A>();
		for(Node n : getChildren()) {
			if(cls.isInstance(n)) {
				list.add(cls.cast(n));
			}
		}
		return list;
	}
}
