import javafx.scene.image.Image;

public class PinkGhost extends Ghost {
	public String pic = getClass().getClassLoader().getResource("resources/pinkG.png").toString();
	public PinkGhost() {
		this.setImage(new Image(pic));
	}
	public void setImage() {
		this.setImage(new Image(pic));
	}
}
