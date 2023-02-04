import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GameSnake extends JPanel implements ActionListener, KeyListener{

	public static final int SCREEN_WIDTH = 600;
	public static final int SCREEN_HEIGHT = 600;
	public static final int SIZE_BLOCK = 20;
	public static final int DEFAULT_NB = 5;
	public BlockSnake food;
	public ArrayList<BlockSnake> snake = new ArrayList<BlockSnake>();
	Timer t;
	DIRECTION direction;
	

	public GameSnake() {

		setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
		setBackground(Color.black);
		direction = DIRECTION.RIGHT;
		setFocusable(true);
		addKeyListener(this);
		food = BlockSnake.getFood(SCREEN_WIDTH/SIZE_BLOCK);

		t = new Timer(100, this);

		init();
		t.start();

	}

	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);

		g.setColor(Color.white);
		//for (int i = 0; i < SCREEN_WIDTH / SIZE_BLOCK; i++) {
		//	g.drawLine(i * SIZE_BLOCK, 0, i * SIZE_BLOCK, SCREEN_HEIGHT);
		//	g.drawLine(0, i * SIZE_BLOCK, SCREEN_WIDTH, i * SIZE_BLOCK);
		//}

		for(BlockSnake b: snake)
			b.draw(g);

		food.draw(g);
	}

	void init() {
		for (int i = DEFAULT_NB - 1; i >= 0; i--) {
			if (i == DEFAULT_NB - 1)
				snake.add(new BlockSnake(i, 0, TypeBlock.HEAD, SIZE_BLOCK));
			else
				snake.add(new BlockSnake(i, 0, TypeBlock.NORMAL, SIZE_BLOCK));
		}
	}

	public void deplace_snake() {

		for (int i = snake.size() - 1; i > 0; i--) {
			snake.get(i).setPosX(snake.get(i-1).getPosX());
			snake.get(i).setPosY(snake.get(i-1).getPosY());
		}

		switch (direction) {
		case RIGHT:
			snake.get(0).setPosX(snake.get(0).getPosX()+1);
			break;
		case LEFT:
			snake.get(0).setPosX(snake.get(0).getPosX()-1);
			break;
		case UP:
			snake.get(0).setPosY(snake.get(0).getPosY()-1);
			break;
		case DOWN:
			snake.get(0).setPosY(snake.get(0).getPosY()+1);
			break;
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		deplace_snake();
		EatFood();
		
		if(collision()) {
			t.stop();
			JOptionPane.showMessageDialog(null, "Perdu ...");
		}
		repaint();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
	
		switch (e.getKeyCode()) {
		case KeyEvent.VK_RIGHT:
			if(direction == DIRECTION.UP || direction == DIRECTION.DOWN)
				direction = DIRECTION.RIGHT;
			break;
		case KeyEvent.VK_LEFT:
			if(direction == DIRECTION.UP || direction == DIRECTION.DOWN)
				direction = DIRECTION.LEFT;
			break;
		case KeyEvent.VK_UP:
			if(direction == DIRECTION.RIGHT || direction == DIRECTION.LEFT)
				direction = DIRECTION.UP;
			break;
		case KeyEvent.VK_DOWN:
			if(direction == DIRECTION.RIGHT || direction == DIRECTION.LEFT)
				direction = DIRECTION.DOWN;
			break;
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	void EatFood() {
		
		if(snake.get(0).equals(food)) {
			snake.add(new BlockSnake(food.getPosX(), food.getPosY(), TypeBlock.NORMAL, SIZE_BLOCK));
			food = BlockSnake.getFood(SCREEN_WIDTH / SIZE_BLOCK);
		}
	}
	
	boolean collision() {
		if(snake.get(0).getPosX()<0)
			return true;
		
		if(snake.get(0).getPosY()<0)
			return true;
		
		if(snake.get(0).getPosX()>(SCREEN_WIDTH / SIZE_BLOCK))
			return true;
		
		if(snake.get(0).getPosY()>(SCREEN_HEIGHT / SIZE_BLOCK))
			return true;
		
		return false;
		
	}

}
