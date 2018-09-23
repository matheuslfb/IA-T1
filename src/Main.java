public class Main {
	public static void main(String[] args) {

		Ambiente a = new Ambiente();
		Gerador g = new Gerador();

		g.GeraParedao(a.base);
		// g.GeraParede(a.base);
		g.GeraParede2(a.base);

		a.printAmbiente(a.base);
	}
}
