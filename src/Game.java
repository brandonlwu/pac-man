import java.io.File;
import java.util.Scanner;
import java.io.IOException;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Game extends Application {
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Pacman");
		primaryStage.setResizable(false);
		BorderPane screen = new BorderPane();
		PacmanWorld world = new PacmanWorld();
		world.setPrefHeight(500);
		world.setPrefWidth(460);
		screen.setCenter(world);
		Scene scene = new Scene(screen);
		
		File f = new File(getClass().getClassLoader().getResource("resources/pacman.txt").getFile());
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(f);
		
		int y = 0;
		while (scan.hasNextLine()) {
			String str = scan.nextLine();
			for (int x = 0; x < str.length(); x+=2) {
				if (str.charAt(x) == 'X') {
					Brick brick = new Brick();
					world.add(brick);
					brick.setX((x/2)*20);
					brick.setY(y*20);
				} else if (str.charAt(x) == 'S') {
					PowerPellet p = new PowerPellet();
					world.add(p);
					p.setY(y*20);
					p.setX((x/2)*20);
				} else if (str.charAt(x) == ' ') {
					Pellet p = new Pellet();
					world.add(p);
					p.setY(7.5 + y*20);
					p.setX(7.5 + (x/2)*20);
				}
			}
			y++;
		}
		
		
		Ghost rG = new RedGhost();
		world.add(rG);
		rG.setX(220);
		rG.setY(240);
		Ghost pG = new PinkGhost();
		world.add(pG);
		pG.setX(220);
		pG.setY(240);
		Ghost oG = new OrangeGhost();
		world.add(oG);
		oG.setX(220);
		oG.setY(240);
		Ghost bG = new BlueGhost();
		world.add(bG);
		bG.setX(220);
		bG.setY(240);
		Mushroom m = new Mushroom();
		world.add(m);
		m.setX(220);
		m.setY(240);
		
		Pacman pacman = new Pacman();
		world.add(pacman);

		
		
		world.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				switch (event.getCode()) {
				case RIGHT:
					pacman.setDirection("RIGHT");
					break;
				case LEFT:
					pacman.setDirection("LEFT");
					break;
				case UP:
					pacman.setDirection("UP");
					break;
				case DOWN:
					pacman.setDirection("DOWN");
					break;
				default:
					break;
				}
			}
		});

		pacman.setX(100);
		pacman.setY(100);
		world.start();
		primaryStage.setScene(scene);
		primaryStage.show();
		world.requestFocus();
		
		world.getLevel().addListener(new ChangeListener<Object>() {

			@Override
			public void changed(ObservableValue<?> arg0, Object arg1, Object arg2) {
				PacmanWorld world2 = new PacmanWorld();
				world2.setPrefHeight(500);
				world2.setPrefWidth(460);
				screen.setCenter(world2);
				Scanner scan2;
				try {
					File f2 = new File(getClass().getClassLoader().getResource("resources/pacman2.txt").getFile());
					scan2 = new Scanner(f2);
					int y = 0;
					while (scan2.hasNextLine()) {
						String str = scan2.nextLine();
						for (int x = 0; x < str.length(); x+=2) {
							if (str.charAt(x) == 'X') {
								Brick brick = new Brick();
								world2.add(brick);
								brick.setX((x/2)*20);
								brick.setY(y*20);
							} else if (str.charAt(x) == 'S') {
								PowerPellet p = new PowerPellet();
								world2.add(p);
								p.setY(y*20);
								p.setX((x/2)*20);
							} else if (str.charAt(x) == ' ') {
								Pellet p = new Pellet();
								world2.add(p);
								p.setY(7.5 + y*20);
								p.setX(7.5 + (x/2)*20);
							}
						}
						y++;
					}
				} catch (IOException err) {
					
				}
				
				
				
				Ghost rG = new RedGhost();
				world2.add(rG);
				rG.setX(220);
				rG.setY(240);
				Ghost pG = new PinkGhost();
				world2.add(pG);
				pG.setX(220);
				pG.setY(240);
				Ghost oG = new OrangeGhost();
				world2.add(oG);
				oG.setX(220);
				oG.setY(240);
				Ghost bG = new BlueGhost();
				world2.add(bG);
				bG.setX(220);
				bG.setY(240);
				Mushroom m = new Mushroom();
				world2.add(m);
				m.setX(220);
				m.setY(240);
				
				Pacman pacman = new Pacman();
				world2.add(pacman);
				
				world2.setOnKeyPressed(new EventHandler<KeyEvent>() {

					@Override
					public void handle(KeyEvent event) {
						switch (event.getCode()) {
						case RIGHT:
							pacman.setDirection("RIGHT");
							break;
						case LEFT:
							pacman.setDirection("LEFT");
							break;
						case UP:
							pacman.setDirection("UP");
							break;
						case DOWN:
							pacman.setDirection("DOWN");
							break;
						default:
							break;
						}
					}
				});

				pacman.setX(102);
				pacman.setY(102);
				world2.start();
				world2.requestFocus();
			}});
	}

}
