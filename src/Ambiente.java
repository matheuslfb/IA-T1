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

	public int tamSacoMoedas() {
		int tam = 0;
		for (int i = 0; i < dindin.size(); i++) {
			tam++;
		}
		return tam;
	}

	public int numeroRandom() {
		r = new Random();
		int val = 0;
		int tam = tamSacoMoedas();

		val = r.nextInt(tam);

		return val;
	}

	// cheat pra pegar todos os sacos (pra testar o genetico e o estrela)
	public void idkfa(ArrayList<SacoMoeda> dindinAux) {
		for (int i = 0; i < dindin.size(); i++) {
			dindinAux.add(dindin.get(i));
		}
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

		// int qnt = dindin.size();
		int pos = 0;
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if (base[i][j] == 8) {
					if (baseAux[i][j] == 4) {
						// val = r.nextInt(qnt); // pega pos random da lista de sacos
						pos = numeroRandom();
						dindinAux.add(dindin.get(pos));
						dindin.remove(pos);
						viAge[i][j] = 9;
						baseAux[i][j] = 9;// retira da aux que tem um saco ali
						System.out.println("Pegou saco de dinheiro");
						return;
					}
				}
			}
		}

	}

	public void anda(String op, int[][] viAge) {
		boolean verificador = false;
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if (base[i][j] == 8) {
					try {
						if (op.equals("d")) {
							if (baseAux[i][j] == 5) {// se passou por cima de um bau ele continua sendo bau qnd sai
								base[i][j] = 9;
							} else {
								base[i][j] = 9;
								viAge[i][j] = 9;
							}
							base[i][j + 1] = 8;

							viAge[i][j + 1] = 8;
							System.out.println("Agente andou para Direita");

							return;
						}
						verificador = true;
					} catch (Exception e) {

						return;

					}
					try {
						if (op.equals("e")) {

							if (baseAux[i][j] == 5) {// se passou por cima de um bau ele continua sendo bau qnd sai
								base[i][j] = 9;
							} else {
								base[i][j] = 9;
								viAge[i][j] = 9;
							}

							base[i][j - 1] = 8;
							viAge[i][j - 1] = 8;
							System.out.println("Agente andou para Esquerda");

							return;
						}

					} catch (Exception x) {

						return;

					}
					try {
						if (op.equals("c")) {

							if (baseAux[i][j] == 5) {// se passou por cima de um bau ele continua sendo bau qnd
														// sai
								base[i][j] = 9;
							} else {
								base[i][j] = 9;
								viAge[i][j] = 9;
							}
							base[i - 1][j] = 8;
							viAge[i - 1][j] = 8;
							System.out.println("Agente andou para Cima");

							return;
						}
						verificador = true;
					} catch (Exception y) {

						return;

					}
					if (op.equals("b"))

						try {
							if (baseAux[i][j] == 5) {// se passou por cima de um bau ele continua sendo bau qnd
														// sai
								base[i][j] = 9;
							} else {
								base[i][j] = 9;
								viAge[i][j] = 9;
							}
							base[i + 1][j] = 8;
							viAge[i + 1][j] = 8;
							System.out.println("Agente andou para Baixo");
							verificador = true;
							return;

						} catch (Exception z) {

							return;

						}
				}
				if (verificador == true) {

					return;
				}
			}

			if (verificador == true) {

				return;
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
						return;
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
						return;
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
						return;
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
						return;
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
		SacoMoeda saco1 = new SacoMoeda(90);
		SacoMoeda saco2 = new SacoMoeda(3);
		SacoMoeda saco3 = new SacoMoeda(96);
		SacoMoeda saco4 = new SacoMoeda(92);
		SacoMoeda saco5 = new SacoMoeda(2);
		SacoMoeda saco6 = new SacoMoeda(90);
		SacoMoeda saco7 = new SacoMoeda(50);
		SacoMoeda saco8 = new SacoMoeda(50);
		SacoMoeda saco9 = new SacoMoeda(33);
		SacoMoeda saco10 = new SacoMoeda(11);
		SacoMoeda saco11 = new SacoMoeda(51);
		SacoMoeda saco13 = new SacoMoeda(84);
		SacoMoeda saco14 = new SacoMoeda(96);
		SacoMoeda saco15 = new SacoMoeda(96);
		SacoMoeda saco16 = new SacoMoeda(20);

		dindin.add(saco);
		// saco.setQuantidadeMoeda(90);
		dindin.add(saco1);
		// saco.setQuantidadeMoeda(3);
		dindin.add(saco2);
		// saco.setQuantidadeMoeda(96);
		dindin.add(saco3);
		// saco.setQuantidadeMoeda(92);
		dindin.add(saco4);
		// saco.setQuantidadeMoeda(2);
		dindin.add(saco5);
		// saco.setQuantidadeMoeda(90);
		dindin.add(saco6);
		// saco.setQuantidadeMoeda(50);
		dindin.add(saco7);
		// saco.setQuantidadeMoeda(50);
		dindin.add(saco8);
		// saco.setQuantidadeMoeda(33);
		dindin.add(saco9);
		// saco.setQuantidadeMoeda(11);

		dindin.add(saco10);
		// saco.setQuantidadeMoeda(51);
		dindin.add(saco11);
		// saco.setQuantidadeMoeda(84);

		// saco.setQuantidadeMoeda(96);
		dindin.add(saco13);
		// saco.setQuantidadeMoeda(96);
		dindin.add(saco14);
		// saco.setQuantidadeMoeda(20);
		dindin.add(saco15);
		dindin.add(saco16);
	}

	public void printAmbiente(int base[][]) {

		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				System.out.print("| ");
				if (base[i][j] == 0) {
					System.out.print(" " + "\t");
				} else if (base[i][j] == 1) {
					System.out.print("=====" + "\t");

				} else if (base[i][j] == 3) {
					System.out.print("  O" + "\t");

				} else if (base[i][j] == 4) {
					System.out.print("  $" + "\t");

				} else if (base[i][j] == 5) {
					System.out.print(" BAU" + "\t");

				} else if (base[i][j] == 6) {
					System.out.print("PORTA" + "\t");

				} else if (base[i][j] == 7) {
					System.out.print("SPAWN" + "\t");

				} else if (base[i][j] == 8) {
					System.out.print("AGENT" + "\t");

				} else if (base[i][j] == 9) {
					System.out.print("+" + "\t");

				}

				else {
					System.out.print(base[i][j] + "\t");
				}
				// mudar pra letras pra facilitar a leitura
			}
			System.out.println("");
		}
	}

}
