import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;

public class Pacman extends Actor {

	public final Image PACMAN_RIGHT = new Image(getClass().getClassLoader().getResource("resources/pacman_right.png").toString());
	public final Image PACMAN_LEFT = new Image(getClass().getClassLoader().getResource("resources/pacman_left.png").toString());
	public final Image PACMAN_UP = new Image(getClass().getClassLoader().getResource("resources/pacman_up.png").toString());
	public final Image PACMAN_DOWN = new Image(getClass().getClassLoader().getResource("resources/pacman_down.png").toString());
	public final Image PACMAN_CLOSED = new Image(getClass().getClassLoader().getResource("resources/pacman_closed.png").toString());

	public String direction;
	public AnimationTimer imageSetter;

	public Pacman() {
		setImage(PACMAN_RIGHT);
		direction = "NULL";
		imageSetter = new AnimationTimer() {
			long previousTime = System.currentTimeMillis();

			@Override
			public void handle(long now) {
				if (System.currentTimeMillis() - previousTime >= 100) {
					if (!getImage().equals(PACMAN_CLOSED) && direction != "NULL") {
						setImage(PACMAN_CLOSED);
					} else {
						switch (direction) {
						case "RIGHT":
							setImage(PACMAN_RIGHT);
							break;
						case "LEFT":
							setImage(PACMAN_LEFT);
							break;
						case "DOWN":
							setImage(PACMAN_DOWN);
							break;
						case "UP":
							setImage(PACMAN_UP);
							break;
						case "NULL":
							break;
						}
					}
					previousTime = System.currentTimeMillis();
				}
			}
		};
		imageSetter.start();
	}

	@Override
	public void act() {
		if(getOneIntersectingObject(Ghost.class) != null) {
			if(getOneIntersectingObject(Ghost.class).edible) {
				Ghost g = getOneIntersectingObject(Ghost.class);
				g.edible = false;
				g.setImage();
				g.setX(200);
				g.setY(240);
				g.count = 0;
				g.nextDirection();
			} else {
				this.setImage(new Image(getClass().getClassLoader().getResource("resources/gOver.png").toString()));
				this.setX(85);
				this.setY(150);
				imageSetter.stop();
				getWorld().stop();
			}
		}
		if(getWorld().getObjects(Pellet.class).size() == 0 ) {
			this.setImage(new Image(getClass().getClassLoader().getResource("resources/win.jpg").toString()));
			this.setX(85);
			this.setY(150);
//			imageSetter.stop();
//			getWorld().stop();
			if(((PacmanWorld)getWorld()).getLevel().getValue() == 1) {
				((PacmanWorld)getWorld()).incrementLevel();
			}
			if(((PacmanWorld)getWorld()).getLevel().getValue() == 2) {
				imageSetter.stop();
				getWorld().stop();
			}
		}
		boolean left = true;
		boolean right = true;
		boolean up = true;
		boolean down = true;
		FakePacman p = new FakePacman();
		getWorld().add(p);
		p.setImage(new Image(getClass().getClassLoader().getResource("resources/PacManSymbol.png").toString()));
		p.setY(this.getY());
		p.setX(this.getX() + 1);
		if(p.getOneIntersectingObject(Brick.class) != null) {
			right = false;
		}
		p.setX(this.getX() - 1);
		if(p.getOneIntersectingObject(Brick.class) != null) {
			left = false;
		}
		p.setX(this.getX());
		p.setY(this.getY() + 1);
		if(p.getOneIntersectingObject(Brick.class) != null) {
			down = false;
		}
		p.setX(this.getX());
		p.setY(this.getY() - 1);
		if(p.getOneIntersectingObject(Brick.class) != null) {
			up = false;
		}
		getWorld().remove(p);
		this.getWorld().remove(p);
		if(direction.equals("RIGHT") && right) {
			move(1,0);
		}
		if(direction.equals("LEFT") && left) {
			move(-1,0);
		}
		if(direction.equals("UP") && up) {
			move(0,-1);
		}
		if(direction.equals("DOWN") && down) {
			move(0,1);
		}
//		Class<Pellet> cls = Pellet.class;
//		Pellet p = this.getOneIntersectingObject(cls);
//		if (p != null) {
//			getWorld().remove(p);
//		}
		if(getOneIntersectingObject(Pellet.class) != null) {
			getWorld().remove(getOneIntersectingObject(Pellet.class));
		} 
		if(getOneIntersectingObject(PowerPellet.class) != null) {
			getWorld().remove(getOneIntersectingObject(PowerPellet.class));
			for(Ghost g : getWorld().getObjects(Ghost.class)) {
				g.edible = true;
			}
		}
		if(getOneIntersectingObject(Mushroom.class) != null) {
			getWorld().remove(getOneIntersectingObject(Mushroom.class));
			for(Ghost g : getWorld().getObjects(Ghost.class)) {
				g.mushrooms = true;
			}
		}
	}

	@Override
	public void move(double dx, double dy) {
		if (dx > 0) {
			setDirection("RIGHT");
			if (this.getX() > this.getScene().getWidth()) {
				this.setX(0);
			}
		} if (dx < 0) {
			setDirection("LEFT");
			if (this.getX() < 0) {
				this.setX(this.getScene().getWidth());
			}
		} else if (dy > 0) {
			setDirection("DOWN");
			if (this.getY() > this.getScene().getHeight()) {
				this.setY(0);
			}
		} else if (dy < 0) {
			setDirection("UP");
			if (this.getY() < 0) {
				this.setY(this.getScene().getHeight());
			}
		}

		this.setX(this.getX() + dx);
		this.setY(this.getY() + dy);
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

}
