import java.util.ArrayList;
import java.util.Random;

public class Gerador {
	Random ran = new Random();

	public void GeraParedao(int[][] base) {
		int op = ran.nextInt(3);
		if (op == 0) {// preenche o topo
			for (int i = 0; i < base.length; i++) {
				base[0][i] = 1;
			}
			base[0][ran.nextInt(base.length)] = 6; // garante que a saida vai estar em no paredão
		}
		if (op == 1) {// preenche a direita
			for (int i = 0; i < base.length; i++) {
				base[i][9] = 1;
			}
			base[ran.nextInt(base.length)][9] = 6;
		}

		if (op == 2) {// preenche esquerda
			for (int i = 0; i < base.length; i++) {
				base[i][0] = 1;
			}
			base[ran.nextInt(base.length)][0] = 6;
		}

		if (op == 3) {// preenche em baixo
			for (int i = 0; i < base.length; i++) {
				base[9][i] = 1;
			}
			base[9][ran.nextInt(base.length)] = 6;
		}

	}

	public void GeraParede2(int[][] base) {
		int[][] amigo = new int[10][10];
		clone(base, amigo);
		ArrayList<Parede> lista = new ArrayList<>();
		Parede p = new Parede(0, 0, 0, 0);
		int count = 0;
		int pos = 0;
		if ((amigo[0][0] == 1 && amigo[0][9] == 1) || (amigo[9][0] == 1 && amigo[9][1] == 1)) {
			lista = p.geraParedeCB();
			while (count < 4) {
				if (lista.get(pos).getyStart() == lista.get(pos).getyEnd()) {
					amigo[lista.get(0).getXStart()][lista.get(pos).getyStart()] = 2;
					amigo[lista.get(1).getXStart() + 1][lista.get(pos).getyStart()] = 2;
					amigo[lista.get(2).getXStart() + 2][lista.get(pos).getyStart()] = 2;
					amigo[lista.get(3).getXStart() + 3][lista.get(pos).getyStart()] = 2;
					amigo[lista.get(4).getXStart() + 4][lista.get(pos).getyStart()] = 2;
				} else {
					amigo[lista.get(0).getXStart()][lista.get(pos).getyStart()] = 2;
					amigo[lista.get(1).getXStart()][lista.get(pos).getyStart() + 1] = 2;
					amigo[lista.get(pos).getXStart()][lista.get(pos).getyStart() + 2] = 2;
					amigo[lista.get(pos).getXStart()][lista.get(pos).getyStart() + 3] = 2;
					amigo[lista.get(pos).getXStart()][lista.get(pos).getyStart() + 4] = 2;
				}
				pos++;
				count++;
			}
		} else {
			lista = p.geraParedeED();
			count = 0;
			pos = 0;
			while (count < 4) {
				if (lista.get(pos).getyStart() == lista.get(pos).getyEnd()) {
					amigo[lista.get(pos).getXStart()][lista.get(pos).getyStart()] = 2;

					amigo[lista.get(pos).getXStart() + 1][lista.get(pos).getyStart()] = 2;
					amigo[lista.get(pos).getXStart() + 2][lista.get(pos).getyStart()] = 2;
					amigo[lista.get(pos).getXStart() + 3][lista.get(pos).getyStart()] = 2;
					amigo[lista.get(pos).getXStart() + 4][lista.get(pos).getyStart()] = 2;
				} else {
					amigo[lista.get(pos).getXStart()][lista.get(pos).getyStart()] = 2;
					amigo[lista.get(pos).getXStart()][lista.get(pos).getyStart() + 1] = 2;
					amigo[lista.get(pos).getXStart()][lista.get(pos).getyStart() + 2] = 2;
					amigo[lista.get(pos).getXStart()][lista.get(pos).getyStart() + 3] = 2;
					amigo[lista.get(pos).getXStart()][lista.get(pos).getyStart() + 4] = 2;
				}
				pos++;
				count++;
			}
		}
	}

	public void GeraPosSacoMoedas(int[][] base) {

		// espalha
		Random ran = new Random();
		int posX = 1;
		int posY = 1;
		for (int i = 0; i < 16; i++) {
			do {
				posX = ran.nextInt(10);
				posY = ran.nextInt(10);
			} while (base[posX][posY] != 0);
			// posicao
			// uma
			// vazia
			base[posX][posY] = 4;
		}

	}

	public void geraburacos(int[][] base) {
		// espalha
		Random ran = new Random();
		int posX = 1;
		int posY = 1;
		for (int i = 0; i < 5; i++) {
			do {
				posX = ran.nextInt(10);
				posY = ran.nextInt(10);
				if (posX > 8) {
					posX = 8;
				}
				if (posX < 2) {
					posX = 2;
				}
				if (posY > 8) {
					posY = 8;
				}
				if (posY < 2) {
					posY = 2;
				}
			} while (base[posX][posY] != 0 && (base[posX + 1][posY] == 3 || base[posX - 1][posY] == 3
					|| base[posX][posY + 1] == 3 || base[posX][posY - 1] == 3));
			// posicao
			// uma
			// vazia
			base[posX][posY] = 3;
		}

	}

	public void geraBaus(int[][] base) {
		// achar paredao
		// achar 4 lugares livres no paredao
		// colocar 4 baus
		Random ran = new Random();
		int posX = 1;
		int posY = 1;
		if (base[0][0] == 1 && base[0][9] == 1) {
			// cima
			for (int i = 0; i < 4; i++) {
				do {
					posX = 1;
					posY = ran.nextInt(10);
				} while (base[posX][posY] != 0);

				base[posX][posY] = 5;
			}
		} else if (base[9][0] == 1 && base[9][9] == 1) {
			// baixo
			for (int i = 0; i < 4; i++) {
				do {
					posX = 8;
					posY = ran.nextInt(10);
				} while (base[posX][posY] != 0);

				base[posX][posY] = 5;
			}
		} else if (base[0][0] == 1 && base[9][0] == 1) {
			// esquerda
			for (int i = 0; i < 4; i++) {
				do {
					posX = ran.nextInt(10);
					posY = 1;
				} while (base[posX][posY] != 0);

				base[posX][posY] = 5;
			}
		} else if (base[0][9] == 1 && base[9][9] == 1) {
			// direita
			for (int i = 0; i < 4; i++) {
				do {
					posX = ran.nextInt(10);
					posY = 8;
				} while (base[posX][posY] != 0);

				base[posX][posY] = 5;
			}
		}

	}

	public void geraSpawn(int base[][]) {
		Random ran = new Random();
		int posX = 1;
		int posY = 1;
		do {
			posX = ran.nextInt(10);
			posY = ran.nextInt(10);
		} while (base[posX][posY] != 0);

		base[posX][posY] = 7;
	}

	public void clone(int[][] base, int[][] amigo) {
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				amigo[i][j] = base[i][j];
			}
		}

	}
}
