import javafx.beans.property.SimpleIntegerProperty;

public class PacmanWorld extends World {

	private SimpleIntegerProperty level;
	
	public PacmanWorld() {
		level = new SimpleIntegerProperty(1);
	}
	
	@Override
	public void act(long now) {
		// TODO Auto-generated method stub

	}
	
	public SimpleIntegerProperty getLevel() {
		return level;
	}

	public void incrementLevel() {
		level.set(2);
	}
}
