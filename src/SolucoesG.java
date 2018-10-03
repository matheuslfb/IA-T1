import java.util.ArrayList;
import java.util.Random;

public class SolucoesG {
	ArrayList<BauG> baus = new ArrayList<>();

	public void printSolucao() {
		for (int i = 0; i < baus.size(); i++) {
			System.out.println("");
			System.out.println("Bau: " + i + " valor : " + baus.get(i).valorBau());

			for (int j = 0; j < baus.get(i).Saco.size(); j++) {
				System.out.println("Saco: " + j + " valor: " + baus.get(i).Saco.get(j).getQuantidadeMoeda());
			}
			System.out.println("");

		}
	}

	public void printSolucaoAPT() {
		for (int i = 0; i < baus.size(); i++) {
			System.out.println("Bau: " + i + " valor : " + baus.get(i).valorBau());
		}
	}

	public void printDif() {
		System.out.println("Dif: " + calculaDiferenca());
	}

	public int calculaDiferenca() {
		int dif = 0;
		int val = baus.get(0).valorBau();
		for (int i = 1; i < baus.size(); i++) {
			if (val != baus.get(i).valorBau()) {
				dif++;
			}
		}
		val = baus.get(1).valorBau();
		for (int i = 2; i < baus.size(); i++) {
			if (val != baus.get(i).valorBau()) {
				dif++;
			}
		}
		val = baus.get(2).valorBau();
		for (int i = 3; i < baus.size(); i++) {
			if (val != baus.get(i).valorBau()) {
				dif++;
			}
		}
		return dif;
	}

	public int maxTam() {
		int tam = 0;
		for (int i = 0; i < baus.size(); i++) {
			if (baus.get(i).qntSacos() > tam) {
				tam = baus.get(i).qntSacos();
			}
		}
		return tam;
	}

	public void gen2() {
		for (int i = 0; i < baus.size(); i++) {
			System.out.println("for2");
			if (i == 0 || i == 1) {
				SacoMoeda y = new SacoMoeda(baus.get(i).pegaSacoRandom());
				baus.get(trocabau(i)).addSaco(y);
				baus.get(i).removeSaco(y);
			}
			if (i == 2 || i == 3 && (baus.get(i).qntSacos() >= 2)) {
				SacoMoeda y = new SacoMoeda(baus.get(i).pegaSacoRandom());
				baus.get(trocabau(i)).addSaco(y);
				baus.get(i).removeSaco(y);
			}
		}
	}

	public void gen3() {
		for (int i = 0; i < baus.size(); i++) {
			System.out.println("for3");
			if (i == 0 || i == 1) {
				SacoMoeda y = new SacoMoeda(baus.get(i).pegaSacoRandom());
				baus.get(trocabau(i)).addSaco(y);
				baus.get(i).removeSaco(y);
			} else if (i == 2 || i == 3 && (baus.get(i).qntSacos() >= 3)) {
				SacoMoeda y = new SacoMoeda(baus.get(i).pegaSacoRandom());
				baus.get(trocabau(i)).addSaco(y);
				baus.get(i).removeSaco(y);
			} else {
				gen2();
			}
		}
	}

	public void gen4() {
		SacoMoeda y;
		SacoMoeda x;
		for (int i = 0; i < baus.size(); i++) {
			System.out.println("for4");
			if (i == 0 || i == 1) {
				System.out.println("if01");
				y = new SacoMoeda(baus.get(i).pegaSacoRandom());
				x = new SacoMoeda(baus.get(i).pegaSacoRandom());
				baus.get(trocabau(i)).addSaco(y);
				baus.get(i).removeSaco(y);
				baus.get(trocabau(i)).addSaco(x);
				baus.get(i).removeSaco(x);
			} else if (i == 2 || i == 3) {
				System.out.println("if23");
				y = new SacoMoeda(baus.get(i).pegaSacoRandom());
				x = new SacoMoeda(baus.get(i).pegaSacoRandom());
				baus.get(trocabau(i)).addSaco(y);
				baus.get(i).removeSaco(y);
				baus.get(trocabau(i)).addSaco(x);
				baus.get(i).removeSaco(x);
			} else {
				System.out.println("else");
				gen3();
			}
		}
	}

	public int trocabau(int b) {
		int pos = 0;

		if (b == 0) {
			pos = 2;
		}
		if (b == 1) {
			pos = 3;
		}
		if (b == 2) {
			pos = 0;
		}
		if (b == 3) {
			pos = 1;
		}

		return pos;
	}

}
