import java.util.ArrayList;
import java.util.Random;

public class Ambiente {
	Random r = new Random();
	int[][] base = new int[10][10];
	int[][] visaoAgente = new int[10][10];
	int[][] baseAux = new int[10][10];
	private int pontos = 0;
	ArrayList<SacoMoeda> dindin = new ArrayList<>();
	boolean jogando = false;
	Agente ag = new Agente(0, true, visaoAgente);
	Gerador g = new Gerador();

	// add pontos
	// get pontos
	// recebe acao agente
	// informa ao agente o mapa em volta

	// 0 = espaco vazio
	// 1 = paredao
	// 2 = parede
	// 3 = buraco
	// 4 = saco dinheiro
	// 5 = bau
	// 6 = saida
	// 7 = spawn
	// 8 = posicao do jogador
	// 9 = caminho do jogador
	public void GeraAmb() {
		g.GeraParedao(base);
		// g.GeraParede2(base);
		g.GeraPosSacoMoedas(base);
		g.geraburacos(base);
		g.geraBaus(base);
		g.geraSpawn(base);
		populaSacos();
		jogando = true;
		pontos = 0;
		g.clone(base, baseAux); // base usa pra andar baseAux usa para interagir
		System.out.println("Ambiente Inicial");
		printAmbiente(base);
	}

	public void pegaSaco(int[][] viAge, ArrayList<SacoMoeda> dindinAux) {
		// olha a posicao do amigo
		// ve se tem saco na aux
		// pega um saco aleatorio do array
		// coloca no array do agente
		// remove do mapaAux
		// remove do mapaAux do agente
		// printa q pegou saco mostra quantidade
		// segue a vida
		int qnt = dindin.size();
		int val = 0;
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if (base[i][j] == 8) {
					if (baseAux[i][j] == 4) {
						val = r.nextInt(qnt); // pega pos random da lista de sacos
						dindinAux.add(dindin.get(val));
						dindin.remove(val);
						viAge[i][j] = 9;
						baseAux[i][j] = 9;// retira da aux que tem um saco ali
						System.out.println("Pegou saco de dinheiro");
						break;
					}
				}
			}
		}

	}

	public void anda(String op, int[][] viAge) {
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if (base[i][j] == 8) {
					if (op.equals("d")) {
						if (baseAux[i][j] == 5) {// se passou por cima de um bau ele continua sendo bau qnd sai
							base[i][j] = 5;
						} else {
							base[i][j] = 9;
						}
						base[i][j + 1] = 8;
						viAge[i][j + 1] = 8;
						System.out.println("Agente andou para Direita");
						break;
					} else if (op.equals("e")) {

						if (baseAux[i][j] == 5) {// se passou por cima de um bau ele continua sendo bau qnd sai
							base[i][j] = 5;
						} else {
							base[i][j] = 9;
						}

						base[i][j - 1] = 8;
						viAge[i][j - 1] = 8;
						System.out.println("Agente andou para Esquerda");
						break;
					} else if (op.equals("c")) {

						if (baseAux[i][j] == 5) {// se passou por cima de um bau ele continua sendo bau qnd sai
							base[i][j] = 5;
						} else {
							base[i][j] = 9;
						}
						base[i - 1][j] = 8;
						viAge[i - 1][j] = 8;
						System.out.println("Agente andou para Cima");
						break;
					} else if (op.equals("b")) {

						if (baseAux[i][j] == 5) {// se passou por cima de um bau ele continua sendo bau qnd sai
							base[i][j] = 5;
						} else {
							base[i][j] = 9;
						}
						base[i + 1][j] = 8;
						viAge[i + 1][j] = 8;
						System.out.println("Agente andou para Baixo");
						break;

					}
					break;
				}

			}
		}
	}

	public void deposita() {

	}

	public void observar(int[][] viAge) {
		// acha posicao
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if (base[i][j] == 8) {
					// achou
					// analisa em volta e manda
					viAge[i][j] = base[i][j];
					// valodr direita 1
					try {
						viAge[i][j + 1] = base[i][j + 1];
					} catch (Exception e) {

					}
					try {
						// valodr direita 2
						viAge[i][j + 2] = base[i][j + 2];
					} catch (Exception e) {

					}
					try {
						// valodr esquerda 1
						viAge[i][j - 1] = base[i][j - 1];
					} catch (Exception e) {

					}
					try {
						// valodr esquerda 2
						viAge[i][j - 2] = base[i][j - 2];
					} catch (Exception e) {

					}
					try {
						// valodr cima 1
						viAge[i - 1][j] = base[i - 1][j];
					} catch (Exception e) {

					}
					try {
						// valodr cima 2
						viAge[i - 2][j] = base[i - 2][j];
					} catch (Exception e) {

					}
					try {
						// valodr baixo 1
						viAge[i + 1][j] = base[i + 1][j];
					} catch (Exception e) {

					}
					try {
						// valodr baixo 2
						viAge[i + 2][j] = base[i + 2][j];
					} catch (Exception e) {

					}

				}

			}
		}
		// pega pos
		// diz 2 range
	}

	public void pular(String op, int viAge[][]) {
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if (base[i][j] == 8) {
					if (op.equals("d")) {
						if (baseAux[i][j] == 5) {// se passou por cima de um bau ele continua sendo bau qnd sai
							base[i][j] = 5;
						} else {
							base[i][j] = 9;
						}
						base[i][j + 2] = 8;
						viAge[i][j + 2] = 8;
						System.out.println("Agente pulou para Direita");
						break;
					}
					if (op.equals("e")) {

						if (baseAux[i][j] == 5) {// se passou por cima de um bau ele continua sendo bau qnd sai
							base[i][j] = 5;
						} else {
							base[i][j] = 9;
						}

						base[i][j - 2] = 8;
						viAge[i][j - 2] = 8;
						System.out.println("Agente pulou para Esquerda");
						break;
					}
					if (op.equals("c")) {

						if (baseAux[i][j] == 5) {// se passou por cima de um bau ele continua sendo bau qnd sai
							base[i][j] = 5;
						} else {
							base[i][j] = 9;
						}
						base[i - 2][j] = 8;
						viAge[i - 2][j] = 8;
						System.out.println("Agente pulou para Cima");
						break;
					}
					if (op.equals("b")) {

						if (baseAux[i][j] == 5) {// se passou por cima de um bau ele continua sendo bau qnd sai
							base[i][j] = 5;
						} else {
							base[i][j] = 9;
						}
						base[i + 2][j] = 8;
						viAge[i + 2][j] = 8;
						System.out.println("Agente pulou para Baixo");
						break;
					}
				}

			}
		}
	}

	public void spawn() {
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if (base[i][j] == 7) {
					base[i][j] = 8;
				}
			}
		}
	}

	public void populaSacos() {
		// guardar essa info
		SacoMoeda saco = new SacoMoeda(10);

		dindin.add(saco);
		saco.setQuantidadeMoeda(90);
		dindin.add(saco);
		saco.setQuantidadeMoeda(3);
		dindin.add(saco);
		saco.setQuantidadeMoeda(96);
		dindin.add(saco);
		saco.setQuantidadeMoeda(92);
		dindin.add(saco);
		saco.setQuantidadeMoeda(2);
		dindin.add(saco);
		saco.setQuantidadeMoeda(90);
		dindin.add(saco);
		saco.setQuantidadeMoeda(50);
		dindin.add(saco);
		saco.setQuantidadeMoeda(50);
		dindin.add(saco);
		saco.setQuantidadeMoeda(33);
		dindin.add(saco);
		saco.setQuantidadeMoeda(20);
		dindin.add(saco);
		saco.setQuantidadeMoeda(51);
		dindin.add(saco);
		saco.setQuantidadeMoeda(84);
		dindin.add(saco);
		saco.setQuantidadeMoeda(96);
		dindin.add(saco);
		saco.setQuantidadeMoeda(96);
		dindin.add(saco);
		saco.setQuantidadeMoeda(11);
		dindin.add(saco);
	}

	public void theAlgoritmoStar() {
		// ESTRELA == MOV to bau mais proximo

	}

	public void printAmbiente(int base[][]) {

		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				System.out.print("| ");
				System.out.print(base[i][j] + "\t");

				// mudar pra letras pra facilitar a leitura
			}
			System.out.println("");
		}
	}

}
