import java.util.ArrayList;

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
	

}
