package BrickBreaker;

import javax.swing.*;

import com.sun.jdi.PrimitiveType;

import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.awt.*;
import java.awt.Color;
import java.awt.event.*;

class Game extends JPanel implements KeyListener, ActionListener  {
	private boolean play = false;
	private boolean playStart = false;
	private int score;
	private int totalBricks = 21;
	private Timer timer;
	private int delay;
	private int xBar = 300;
	private int ballposX = 340;
	private int ballposY = 500;
	private int ballXdir = -1;
	private int ballYdir = -2;
	private Layout level;

	public Game() {
		level = new Layout(3, 7);
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		timer = new Timer(delay, this);
		timer.start();
	}

	public void paint(Graphics brick) {
		
		brick.setColor(Color.PINK);
		brick.fillRect(1, 1, 750, 800);
		
		level.createBrick((Graphics2D)brick);
		
		brick.fillRect(0, 0, 3, 592);
		brick.fillRect(0, 0, 692, 3);
		brick.fillRect(691, 0, 3, 592);
		
		brick.setColor(Color.blue);
		brick.fillRect(xBar, 550, 100, 12);
		
		brick.setColor(Color.white);  
		brick.fillOval(ballposX, ballposY, 20, 20);
	
		
		brick.setColor(Color.white);
		brick.setFont(new Font("Arial", Font.BOLD, 25));
		brick.drawString("Score: " + score, 520, 30);
		


		if(playStart == false){
			brick.setColor(Color.white);
			brick.setFont(new Font("Arial", Font.BOLD, 30));
			brick.drawString("Press Enter to Begin!", 200, 300);
			brick.drawString("Press Esc to Exit", 228, 350);
		}
	
		if (totalBricks == 0) { 
			play = false;
			ballXdir = 0;
			ballYdir = 0;
			brick.setColor(Color.WHITE);
			brick.setFont(new Font("Arial", Font.BOLD, 30));
			brick.drawString("You Won, Score: " + score, 215, 300);
			
			brick.setFont(new Font("Arial", Font.BOLD, 20));
			brick.drawString("Press 1 to Move to Level 2.", 230, 350);
			
		}
		
		if(ballposY > 570) {  
			play = false;
			ballXdir = 0;
			ballYdir = 0;
			brick.setColor(Color.white);
			brick.setFont(new Font("Arial", Font.BOLD, 30));
			brick.drawString("YOU LOSE! Score: " + score, 185, 300);
			
			brick.setFont(new Font("Arial", Font.BOLD, 20));
			brick.drawString("Press Space to Restart from level 1", 165, 330);
			brick.drawString("Press 1 to Move to level 2", 210, 355);
			brick.drawString("Press ESC to Exit", 230, 380);
		} 
		brick.dispose();
	

	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		timer.start();
		if(play) {
			if(new Rectangle(ballposX, ballposY, 20, 20).intersects(new Rectangle(xBar, 550, 100, 8))) {
				ballYdir = - (ballYdir - (1/2) * ballYdir);
			}
			else if(new Rectangle(ballposX, ballposY, 20, 20).intersects(new Rectangle(xBar, 550, 100, 8))) {
					ballYdir = - (ballYdir + (1/2) * ballYdir);
			}
			else if(new Rectangle(ballposX, ballposY, 20, 20).intersects(new Rectangle(xBar, 550, 100, 8))) {
						ballYdir = - (ballYdir + (1/2) * ballYdir);
			}
			else if(new Rectangle(ballposX, ballposY, 20, 20).intersects(new Rectangle(xBar, 550, 100, 8))) {
							ballYdir = - (ballYdir - (1/2) * ballYdir);
			}
			for(int i = 0; i<level.level.length; i++) { 
				for(int j = 0; j < level.level[0].length; j++) {  
					if(level.level[i][j] > 0) {
						int brickX = j * level.brickW + 80;
						int brickY = i * level.brickH + 30;
						int brickW= level.brickW;
						int brickH = level.brickH;
						
						Rectangle rect = new Rectangle(brickX, brickY, brickW, brickH);
						Rectangle ballRect = new Rectangle(ballposX, ballposY, 15,15);
						Rectangle brickRect = rect;
						
						if(ballRect.intersects(brickRect) ) {
							level.setBrickValue(0, i, j);
							totalBricks--;
							score += 2;
							
							if(ballposX + 19 <= brickRect.x || ballposX +1 >= brickRect.x + brickRect.width)
								ballXdir = -ballXdir;
							 else {
								ballYdir = -ballYdir;
							}
						}
						
					}
					
				}
			}
			
			ballposX += ballXdir;
			ballposY += ballYdir;
			if(ballposX < 0) {
				ballXdir = -ballXdir;
			}
			if(ballposY < 0) {  
				ballYdir = -ballYdir;
			}
			if(ballposX > 670) { 
				ballXdir = -ballXdir;  
			
			}
			
		}
		
		repaint();

}

	@Override
	public void keyTyped(KeyEvent arg0) {
		
	}
	
	@Override
	public void keyPressed(KeyEvent arg0) {
		if(arg0.getKeyCode() == KeyEvent.VK_RIGHT && play == true) { 
			
			if(xBar >= 600) {
				xBar = 600;
			} else {
				moveRight();
					
			}
		}
		if(arg0.getKeyCode() == KeyEvent.VK_LEFT && play == true) { 

			if(xBar < 10) {
				xBar = 10;
			} else {
				moveLeft();
			}	
		}
	

		if(arg0.getKeyCode() == KeyEvent.VK_SPACE) { 
			if(!play) {
				play = true;
				ballposX = 120;
				ballposY = 350;
				ballXdir = -1;
				ballYdir = -2;
				score = 0;
				totalBricks = 7;
				level = new Layout(1,7);
				repaint();
			}
		}
		if(arg0.getKeyCode() == KeyEvent.VK_1) { 
			if(!play) {
				play = true;
				ballposX = 340;
				ballposY = 500;
				ballXdir = -1;
				ballYdir = -2;
				score = 0;
				totalBricks = 24;
				level = new Layout(3,8);
				repaint();
			}
			
		}
		if(arg0.getKeyCode() == KeyEvent.VK_2) { 
			if(!play) {
				play = true;
				ballposX = 340;
				ballposY = 500;
				ballXdir = -1;
				ballYdir = -1;
				score = 0;
				totalBricks = 40;
				level = new Layout(4,10);				
				repaint();
			}
		}
	
		if(arg0.getKeyCode() == KeyEvent.VK_ESCAPE){
				System.exit(ABORT);
			}	
		if(arg0.getKeyCode() == KeyEvent.VK_ENTER && play == false){
			if(!play){
				play = true;
				playStart = true; 
				delay = 3;
			} else {
				play = false;
				ballposX = 340;
				ballposY = 500;
			}
			repaint();
		}

	}	
		public void moveRight() { 
			play = true;
			xBar += 25;
		}
		public void moveLeft() { 
			play = true;
			xBar -= 25;
		}
		
	

	@Override
	public void keyReleased(KeyEvent arg0) {
		
	}


}

class Level1 {
	public static void main(String[] args) {
		JFrame obj = new JFrame();
		Game game = new Game();
		obj.setBounds(10, 10, 700, 600);
		obj.setTitle("Brick Breaker");
		obj.setResizable(true);
		obj.setVisible(true);
		obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		obj.add(game);
		
	}

}
