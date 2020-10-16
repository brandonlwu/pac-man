import javafx.scene.image.Image;

public class Mushroom extends Actor {
	public Image img = new Image(getClass().getClassLoader().getResource("resources/gMushroom.png").toString());
	public Mushroom() {
		this.setImage(img);
	}
	@Override
	public void act() {
		// TODO Auto-generated method stub
	}
	public void setImage() {
		this.setImage(img);
	}
}
