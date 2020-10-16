import java.util.ArrayList;
import java.util.Random;

import javafx.scene.image.Image;

public class Ghost extends Actor {
	// true = eatable
	//false = not eatable
	public boolean edible = false;
	public boolean mushrooms = false;
	public String vulnerablePic = getClass().getClassLoader().getResource("resources/ghostVulnerable.png").toString();
	
	public Ghost() {
		dir = 2;
	}
	
	//When the pacman is activated, and is touching, this method is called for the ghost
	//to change the sprite (to eyes) and go back to its "home"
	public void eaten() {
		
	}
	
	// 1 = up
	// 2 = right
	// 3 = left
	// 4 = down
	@SuppressWarnings("unchecked")
	public void nextDirection(){
		boolean left = true;
		boolean right = true;
		boolean up = true;
		boolean down = true;
		World w = this.getWorld();
		@SuppressWarnings("rawtypes")
		ArrayList<Actor> arr =  (ArrayList)(w.getObjects(Brick.class));
		double x = this.getX();
		double y = this.getY();
		for(Actor a : arr) {
			double x2 = a.getX();
			double y2 = a.getY();
			if(x-20 == x2 && y == y2) {
				left = false;
			}
			if(x+20 == x2 && y == y2) {
				right = false;
			}
			if(x==x2 && y+20 == y2) {
				up = false;
			}
			if(x==x2 && y-20 == y2) {
				down = false;
			}
		}
		ArrayList<Integer> ar= new ArrayList<Integer>();
		if(left && dir != 2) ar.add(3);
		if(right && dir != 3) ar.add(2);
		if(up && dir != 4) ar.add(1);
		if(down && dir != 1) ar.add(4);
		Random r = new Random();
		if(ar.size() == 0) {
			if(dir == 1) ar.add(4);
			if(dir == 4) ar.add(1);
			if(dir == 2) ar.add(3);
			if(dir == 3) ar.add(2);
		}
		dir = ar.get(r.nextInt(ar.size()));
	}
	//true if within a radius of 15 of a pacman
//	public abstract boolean isClose() {
//		
//	}
	
	//true if its touching a ghost, change direction
	public boolean touchingGhost() {
		if(getOneIntersectingObject(Ghost.class) != null) {
			return true;
		}
		return false;
	}
	
	public int count = 0;
	public int count2 = 0;
	public int count3 = 0;
	@Override
	public void act() {
		// TODO Auto-generated method stub
		if(count == 20) {
			nextDirection();
			count=0;
		}
		if(dir == 1) { move(0,1); count++;}
		else if(dir == 2) { move(1,0); count++;}
		else if(dir == 3) { move(-1,0); count++;}
		else if(dir==4) {move(0,-1); count++;}
		if(edible) {
			this.setImage(new Image (vulnerablePic));
			count2++;
		}
		if(count2 == 650) {
			count2 = 0;
			setImage();
			edible = false;
		}
		if(mushrooms) {
			count3++;
			if(getOneIntersectingObject(Pellet.class) != null) {
				getWorld().remove(getOneIntersectingObject(Pellet.class));
			}
		}
		if(count3 == 800) {
			count3 = 0;
			mushrooms = false;
		}
		if(this.getX() == 0) {
			this.setX(getWorld().getWidth() - 20);
		} else if(this.getX() == getWorld().getWidth()) {
			this.setX(20);
		}
		if(this.getY() == 0) {
			this.setY(getWorld().getHeight() - 20);
		} else if(this.getY() == getWorld().getHeight()) {
			this.setY(20);
		}
	}

	public void setImage() {
		// TODO Auto-generated method stub
	}
	
}
