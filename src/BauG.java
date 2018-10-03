import java.util.ArrayList;
import java.util.Random;

public class BauG {
	ArrayList<SacoMoeda> Saco = new ArrayList<>();

	public void addSaco(SacoMoeda sacoM) {
		Saco.add(sacoM);
	}

	public void removeSaco(SacoMoeda sacoM) {
		for (int i = 0; i < Saco.size(); i++) {
			if (sacoM == Saco.get(i)) {
				Saco.remove(sacoM);
			}
		}
	}

	public void limpaBau() {
		for (int i = 0; i < Saco.size(); i++) {
			{
				Saco.remove(i);
			}
		}
	}

	public int valorBau() {
		int max = 0;
		for (int i = 0; i < Saco.size(); i++) {
			max += Saco.get(i).getQuantidadeMoeda();
		}
		return max;
	}

	public int qntSacos() {
		int tam = 0;

		for (int i = 0; i < Saco.size(); i++) {
			tam++;
		}

		return tam;
	}

	public int pegaSacoMetade() {
		int pos = 0;
		Random r = new Random();
		boolean okRandom = false;
		//MUDAR PRA PEGAR O TAM TOTAL E DIVIDIR NA METADE
		//AI TEM Q COMPARAR UM BAU COM OUTRO
		//SIPA MUDA ISSO PRA CLASSE SOLUCOES
		// pega um random pra pos random do genentico
		do {
			int aleatorio = r.nextInt(Saco.size());
			okRandom = true;
			pos = aleatorio;
		} while (okRandom == true);

		return pos;
	}

}
