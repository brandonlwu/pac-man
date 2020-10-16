import javafx.scene.image.Image;

public class OrangeGhost extends Ghost {
	public String pic = getClass().getClassLoader().getResource("resources/orangeG.png").toString();;
	public OrangeGhost() {
		this.setImage(new Image(pic));
	}
	public void setImage() {
		this.setImage(new Image(pic));
	}
}
