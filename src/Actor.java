import javafx.scene.image.ImageView;
import java.util.ArrayList;

public abstract class Actor extends ImageView{
	public int dir = 1;
	// 1 = up
	// 2 = right
	// 3 = down
	// 4 = left
	public Actor() {
		
	}
	public abstract void act();
	
	public World getWorld() {
		return (World) this.getParent();
	}
	
	public double getHeight() {
		return this.getImage().getHeight();
	}
	
	public double getWidth() {
		return this.getImage().getWidth();
	}
	
	public void move(double dx, double dy) {
		this.setX(this.getX() + dx);
		this.setY(this.getY() + dy);
	}

	public <A extends Actor> java.util.List<A> getIntersectingObjects(java.lang.Class<A> cls) {
		ArrayList<A> returnArr = new ArrayList<>();
		ArrayList<A> arr = (ArrayList<A>)getWorld().getObjects(cls);
		for (A a: arr) {
			if (this.intersects(a.getX(), a.getY(), a.getWidth(), a.getHeight())) {
				returnArr.add(a);
			}
		}
		return returnArr;
	}
	public <A extends Actor> A getOneIntersectingObject(java.lang.Class<A> cls) {
		ArrayList<A> rArr = (ArrayList<A>)getIntersectingObjects(cls);
		if (rArr.size() > 0) {
			return rArr.get(0);
		} else {
			return null;
		}
	}
}
