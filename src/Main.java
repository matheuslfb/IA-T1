public class Main {
	public static void main(String[] args) {

		Ambiente a = new Ambiente();
		Gerador g = new Gerador();

		g.GeraParedao(a.base);
		// g.GeraParede(a.base);
	//	g.GeraParede2(a.base);
		g.GeraPosSacoMoedas(a.base);
		g.geraburacos(a.base);
		g.geraBaus(a.base);
		g.geraSpawn(a.base);
		a.printAmbiente(a.base);
	}
}
