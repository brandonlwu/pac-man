import javafx.scene.image.Image;

public class BlueGhost extends Ghost {
	public String pic = getClass().getClassLoader().getResource("resources/blueG.png").toString();
	public BlueGhost() {
		this.setImage(new Image(pic));
	}
	public void setImage() {
		this.setImage(new Image(pic));
	}
}
