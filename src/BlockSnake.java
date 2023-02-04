import java.awt.Color;
import java.awt.Graphics;
import java.util.Objects;
import java.util.Random;

public class BlockSnake {

	private int posX;
	private int posY;
	private TypeBlock type;
	private static int taille;

	public BlockSnake() {

	}


	public BlockSnake(int posX, int posY, TypeBlock type, int taille) {
		super();
		this.posX = posX;
		this.posY = posY;
		this.type = type;
		this.taille = taille;
	}


	public TypeBlock getType() {
		return type;
	}


	public void setType(TypeBlock type) {
		this.type = type;
	}


	public int getTaille() {
		return taille;
	}


	public void setTaille(int taille) {
		this.taille = taille;
	}


	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}
	
	public void draw(Graphics g) {
		
		switch (type) {
		case NORMAL:
			g.setColor(Color.white);
			g.fillOval(posX*taille, posY*taille, taille, taille);
			break;
		case HEAD:
			g.setColor(Color.YELLOW);
			g.fillOval(posX*taille, posY*taille, taille, taille);
			break;
		case FOOD:
			g.setColor(Color.red);
			g.fillOval(posX*taille, posY*taille, taille, taille);
			break;
		}
	}
	
	
	public static BlockSnake getFood(int val) {
		Random r = new Random(); 
		int x = r.nextInt(val);
		int y = r.nextInt(val);
		
		return new BlockSnake(x,y, TypeBlock.FOOD, taille);
	}


	@Override
	public int hashCode() {
		return Objects.hash(posX, posY);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BlockSnake other = (BlockSnake) obj;
		return posX == other.posX && posY == other.posY;
	}
	
	
	

}
