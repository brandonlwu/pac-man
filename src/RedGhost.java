import javafx.scene.image.Image;

public class RedGhost extends Ghost {
	public String pic = getClass().getClassLoader().getResource("resources/redG.png").toString();
	public RedGhost() {
		this.setImage(new Image(pic));
	}
	public void setImage() {
		this.setImage(new Image(pic));
	}
}
