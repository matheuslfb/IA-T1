import java.util.ArrayList;
import java.util.Random;

import javax.swing.plaf.synth.SynthSeparatorUI;

public class Gerador {
	Random ran = new Random();

	public void GeraParedao(int[][] base) {
		int op = ran.nextInt(3);
		if (op == 0) {// preenche o topo
			for (int i = 0; i < base.length; i++) {
				base[0][i] = 1;
			}
			base[0][ran.nextInt(base.length)] = 6; // garante que a saida vai estar em no paredÃƒÂ£o
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
			while(count<4) {
				if (lista.get(pos).getyStart() == lista.get(pos).getyEnd()) {
					amigo[lista.get(pos).getXStart()][lista.get(pos).getyStart()] = 2;
					amigo[lista.get(pos).getXStart()+1][lista.get(pos).getyStart()] = 2;
					amigo[lista.get(pos).getXStart()+2][lista.get(pos).getyStart()] = 2;
					amigo[lista.get(pos).getXStart()+3][lista.get(pos).getyStart()] = 2;
					amigo[lista.get(pos).getXStart()+4][lista.get(pos).getyStart()] = 2;
				} else {
					amigo[lista.get(pos).getXStart()][lista.get(pos).getyStart()] = 2;
					amigo[lista.get(pos).getXStart()][lista.get(pos).getyStart()+1] = 2;
					amigo[lista.get(pos).getXStart()][lista.get(pos).getyStart()+2] = 2;
					amigo[lista.get(pos).getXStart()][lista.get(pos).getyStart()+3] = 2;
					amigo[lista.get(pos).getXStart()][lista.get(pos).getyStart()+4] = 2;
				}
				pos++;
				count++;
			}
		} else {
			lista = p.geraParedeED();
			count = 0;
			pos = 0;
			while(count<4) {
				if (lista.get(pos).getyStart() == lista.get(pos).getyEnd()) {
					amigo[lista.get(pos).getXStart()][lista.get(pos).getyStart()] = 2;
					amigo[lista.get(pos).getXStart()+1][lista.get(pos).getyStart()] = 2;
					amigo[lista.get(pos).getXStart()+2][lista.get(pos).getyStart()] = 2;
					amigo[lista.get(pos).getXStart()+3][lista.get(pos).getyStart()] = 2;
					amigo[lista.get(pos).getXStart()+4][lista.get(pos).getyStart()] = 2;
				} else {
					amigo[lista.get(pos).getXStart()][lista.get(pos).getyStart()] = 2;
					amigo[lista.get(pos).getXStart()][lista.get(pos).getyStart()+1] = 2;
					amigo[lista.get(pos).getXStart()][lista.get(pos).getyStart()+2] = 2;
					amigo[lista.get(pos).getXStart()][lista.get(pos).getyStart()+3] = 2;
					amigo[lista.get(pos).getXStart()][lista.get(pos).getyStart()+4] = 2;
				}
				pos++;
				count++;
			}
		}

		clone(amigo, base);
	}

	public int colisoes(int[][] base) {

		return 0;

	}

	public void clone(int[][] base, int[][] amigo) {
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				amigo[i][j] = base[i][j];
			}
		}

	}
}
