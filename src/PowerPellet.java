import javafx.scene.image.Image;

public class PowerPellet extends Actor {

	private Image pellet = new Image(getClass().getClassLoader().getResource("resources/power pellet.png").toString());
	
	public PowerPellet() {
		setImage(pellet);
		this.setFitHeight(20);
		this.setFitWidth(20);
	}
	
	
	@Override
	public void act() {
		// TODO Auto-generated method stub

	}

}
