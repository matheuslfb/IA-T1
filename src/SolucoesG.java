import java.util.ArrayList;

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


}
