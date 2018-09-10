import java.util.Random;

public class Gerador {
	Random ran = new Random();

	public void GeraParedao(int[][] base) {
		int op = ran.nextInt(3);
		if (op == 0) {// preenche o topo
			for (int i = 0; i < base.length; i++) {
				base[0][i] = 1;
			}
		}
		if (op == 1) {// preenche a direita
			for (int i = 0; i < base.length; i++) {
				base[i][9] = 1;
			}
		}

		if (op == 2) {// preenche esquerda
			for (int i = 0; i < base.length; i++) {
				base[i][0] = 1;
			}
		}

		if (op == 3) {// preenche em baixo
			for (int i = 0; i < base.length; i++) {
				base[9][i] = 1;
			}
		}

	}

	public void GeraParede(int[][] base) {

		int[][] amigo = new int[10][10];
		clone(amigo, base);
		int verhor = ran.nextInt(1);
		int xAmigo = ran.nextInt(10);
		int yAmigo = ran.nextInt(10);
		// 0 = vertical
		// 1 = horizontal
		// Verificar qual variacao do paredao
		// em cima
		if (amigo[0][0] == 1 && amigo[0][9] == 1) {
			// vertical
			if (verhor == 0) {
				// 0-4 6-9
				if (xAmigo <= 4) {

				} else if (xAmigo >= 6) {

				}
				// CASO 5 faz pra cima
				// horizontal
			} else {
				// 0-3 6-9
				if (yAmigo <= 3) {

				} else if (yAmigo >= 6) {

				}
				// CASO 4 ou 5 faz pra Direita pq eh sucesso
			}
		}
		// direita
		if (amigo[0][9] == 1 && amigo[1][9] == 1) {
			if (verhor == 0) {
				// 0-3 6-9
				if (xAmigo <= 3) {

				} else if (xAmigo >= 6) {

				}
				// CASO 4 ou 5 faz pra cima
				// horizontal
			} else {
				// 0-3 5-9
				if (yAmigo <= 3) {

				} else if (yAmigo >= 5) {

				}
				// CASO 4  faz pra Direita pq eh sucesso
			}

		}
		// esquerda
		if (amigo[0][0] == 1 && amigo[1][0] == 1) {
			if (verhor == 0) {
				// 0-3 6-9
				if (xAmigo <= 3) {

				} else if (xAmigo >= 6) {

				}
				// CASO 4 ou 5 faz pra cima
			} else {
				// 0-4 6-9
				if (yAmigo <= 4) {

				} else if (yAmigo >= 6) {

				}
				// CASO 4 ou 5 faz pra Direita pq eh sucesso
			}
		}
		// baixo
		if (amigo[9][0] == 1 && amigo[9][1] == 1) {
			if (verhor == 0) {
				// 0-3 5-9
				if (xAmigo <= 3) {

				} else if (xAmigo >= 5) {

				}
				// CASO 4 ou 5 faz pra cima
			} else {
				// 0-3 6-9
				if (yAmigo <= 3) {

				} else if (yAmigo >= 6) {

				}
				// CASO 4 ou 5 faz pra Direita pq eh sucesso
			}
		}

	}

	public void clone(int[][] base, int[][] amigo) {
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				amigo[i][j] = base[i][j];
			}
		}

	}
}