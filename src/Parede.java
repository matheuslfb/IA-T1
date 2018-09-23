import java.util.ArrayList;
import java.util.Random;

public class Parede {
	private int xStart;
	private int yStart;
	private int xEnd;
	private int yEnd;
	Random ran = new Random();

	public Parede(int xStart, int yStart, int xEnd, int yEnd) {
		this.xStart = xStart;
		this.yStart = yStart;
		this.xEnd = xEnd;
		this.yEnd = yEnd;
	}

	public ArrayList<Parede> geraParedeCB() {
		ArrayList<Parede>lista = new ArrayList<>();
		Parede p;
		int posX = 0;
		int posY = 0;
		int lado = 0; //0 vert 1 hor
		for (int i = 0; i <= 3; i++) {
			posX = ran.nextInt(10);
			posY = ran.nextInt(10);
			lado = ran.nextInt(1);
			
			if (posY == 0 || posY == 9) {
				yStart = 1;
			} else if (posY == 8 || posY == 7 || posY == 6 || posY == 5)
				yStart = 4;
			else {
				yStart = posY;
			}
			xStart = posX;
			xEnd = xStart+5;
			yEnd = yStart +5;
			p = new Parede(xStart,yStart,xEnd,yEnd);
			lista.add(p);
		}

		return lista;

	}

	public ArrayList<Parede> geraParedeED() {
		ArrayList<Parede>lista = new ArrayList<>();
		Parede p;
		int posX = 0;
		int posY = 0;
		for (int i = 0; i <= 3; i++) {
			posX = ran.nextInt(10);
			posY = ran.nextInt(10);
			if (posX == 0 || posX == 9) {
				xStart = 1;
			} else if (posX == 8 || posX == 7 || posX == 6 || posX == 5)
				xStart = 4;
			else {
				xStart = posX;
			}
			xStart = posX;
			xEnd = xStart+5;
			yEnd = yStart +5;
			p = new Parede(xStart,yStart,xEnd,yEnd);
			lista.add(p);
		}

		return lista;

	}

	public int getXStart() {
		return xStart;
	}

	public void setXStart(int xStart) {
		this.xStart = xStart;
	}

	public int getyStart() {
		return yStart;
	}

	public void setyStart(int yStart) {
		this.yStart = yStart;
	}

	public int getxEnd() {
		return xEnd;
	}

	public void setxEnd(int xEnd) {
		this.xEnd = xEnd;
	}

	public int getyEnd() {
		return yEnd;
	}

	public void setyEnd(int yEnd) {
		this.yEnd = yEnd;
	}

	public int colisoes(ArrayList<Parede> lista) {
		for (int i = 0; i < 4; i++) {
			//TODO
		}
		return 0;
	}

	public int extremos(Parede[] lista) {

		return 0;
	}
}
