import javafx.scene.image.Image;

public class Brick extends Actor{

	private Image brick = new Image(getClass().getClassLoader().getResource("resources/wall.png").toString());
	
	public Brick() {
		setImage(brick);
	}

	@Override
	public void act() {
		// TODO Auto-generated method stub
		//
	}
	
}
