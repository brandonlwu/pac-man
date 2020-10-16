import javafx.scene.image.Image;

public class Pellet extends Actor {

	private Image pellet = new Image(getClass().getClassLoader().getResource("resources/swuare.png").toString());

	
	public Pellet() {
		setImage(pellet);
	}
	
	@Override
	public void act() {
		// TODO Auto-generated method stub

	}

}
