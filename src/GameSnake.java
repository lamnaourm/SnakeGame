import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

public class GameSnake extends JPanel {

	public static final int SCREEN_WIDTH = 600;
	public static final int SCREEN_HEIGHT = 600;
	public static final int SIZE_BLOCK = 20;
	public static final int DEFAULT_NB = 5;
	public ArrayList<BlockSnake> snake = new ArrayList<BlockSnake>();
	
	public GameSnake() {

		setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
		setBackground(Color.black);
		
		init();
	
	}
	
	
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		
		g.setColor(Color.white);
		/*for(int i=0;i<SCREEN_WIDTH/SIZE_BLOCK; i++) {
			g.drawLine(i*SIZE_BLOCK, 0, i*SIZE_BLOCK, SCREEN_HEIGHT);
			g.drawLine(0,i*SIZE_BLOCK, SCREEN_WIDTH, i*SIZE_BLOCK);
		}*/
	
		
		for(BlockSnake b : snake)
			b.draw(g);
		
		
		BlockSnake.getFood(SCREEN_WIDTH/SIZE_BLOCK).draw(g);
	}
	
	
	void init() {
		for(int i=0; i<DEFAULT_NB; i++) {
			if(i==DEFAULT_NB-1)
				snake.add(new BlockSnake(i,0, TypeBlock.HEAD, SIZE_BLOCK));
			else 
				snake.add(new BlockSnake(i,0, TypeBlock.NORMAL, SIZE_BLOCK));
		}
	}
	
	
	
	

}
