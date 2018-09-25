import java.util.ArrayList;
import java.util.Random;

public class Agente {
	// observar -->guarda o mapa conforme vai revelando
	// faz sempre q anda

	// INFORMAR AO AMBIENTE
	// andar**RANDOM
	// pegar dinheiro**SETIVER EM CIMA
	// deposita no bau**** GENETICO
	// ANDAR ATE BAUS***** ESTRELA
	// PULAR **SE buraco
	static ArrayList<SacoMoeda> dindin = new ArrayList<>();
	int dinheiro = 0;
	boolean vivo = true;
	static int[][] mapa = new int[10][10]; // usa pra andar
	static int[][] mapaAux = new int[10][10]; // usa pra baus e sacos de dinheiro
	static boolean acabou = false;
	static Ambiente amb = new Ambiente();
	static int contAcoes = 0;
	static int contBaus = 0;
	static int contSacos = 0;
	static boolean achouPorta = false;
	static Random r = new Random();
	// DECIDIR BASEADO NO QUE TEM EM VOLTA E MAPA

	public static void main(String[] args) {
		amb.GeraAmb();
		amb.spawn();
		idle();

		System.out.println("----------------------------------------------------------------------------");

		/*
		 * receberInfo();
		 * 
		 * analisar();
		 * 
		 * printVisaoAgente(mapa); receberInfo(); analisar();
		 * 
		 * printVisaoAgente(mapa);
		 */
	}

	public static void idle() {
		// recebe o mapa
		// analisa o mapa
		// decide o que fazer
		// ORDEM PRIORIDADE
		// PEGAR DINHEIRO
		// Pular buraco
		// andar random
		// se pegou todos os sacos
		int cont = 0;
		
		do {
			receberInfo();
			printVisaoAgente(mapa);
			analisar();
			printMovimento(mapa);
			cont++;
			System.out.println("----------------------------------------------------------------------------");
			/*
			 * if (contSacos == 16) { acabou = true; } } while (!acabou);
			 */
		} while (cont < 20);

	}

	public Agente(int dinheiro, boolean vivo, int[][] mapa) {

		this.dinheiro = dinheiro;
		this.vivo = vivo;
		this.mapa = mapa;
	}

	public static void receberInfo() {
		// manda q quer receber o mapa
		amb.observar(mapa);

		// printVisaoAgente(mapa);

		// recebe
		// marca no mapa
	}

	private static void analisar() {// anda para sacos din din, marca baus, pula buracos quando tem dinherio atras
		boolean nada = false;

		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if (mapa[i][j] == 8) {
					try {
						if (mapa[i][j + 1] == 5) {// conta bau, remove da matriz normal, usa a aux pra analisar
							contBaus++;
							mapa[i][j + 1] = 0;
						}
						if (mapa[i][j + 1] == 4) {
							andar("d");
							pegaSaco();
							break;
						} // ve se tem buraco N agora
					} catch (Exception e) {

					}
					try {
						// valodr direita 2
						if (mapa[i][j + 2] == 4) {
							if (mapa[i][j + 2] == 5) {// conta bau, remove da matriz normal, usa a aux pra analisar
								contBaus++;
								mapa[i][j + 2] = 0;
							}
							if (mapa[i][j + 1] == 3) {// ve se te, buraco
								pular("d");
								pegaSaco();

							} else {
								andar("d");
							}

							break;

						}
					} catch (Exception e) {

					}
					try {
						// valodr esquerda 1
						if (mapa[i][j - 1] == 5) {// conta bau, remove da matriz normal, usa a aux pra analisar
							contBaus++;
							mapa[i][j - 1] = 0;
						}
						if (mapa[i][j - 1] == 4) {
							andar("e");
							pegaSaco();
							break;
						}
					} catch (Exception e) {

					}
					try {
						// valodr esquerda 2
						if (mapa[i][j - 2] == 5) {// conta bau, remove da matriz normal, usa a aux pra analisar
							contBaus++;
							mapa[i][j - 2] = 0;
						}
						if (mapa[i][j - 2] == 4) {
							if (mapa[i][j - 1] == 3) {// ve se te, buraco
								pular("e");
								pegaSaco();
							} else {
								andar("e");
							}
							break;
						}
					} catch (Exception e) {

					}
					try {
						if (mapa[i - 1][j] == 5) {// conta bau, remove da matriz normal, usa a aux pra analisar
							contBaus++;
							mapa[i - 1][j] = 0;
						}
						// valodr cima 1
						if (mapa[i - 1][j] == 4) {
							andar("c");
							pegaSaco();
							break;
						}
					} catch (Exception e) {

					}
					try {
						if (mapa[i - 2][j] == 5) {// conta bau, remove da matriz normal, usa a aux pra analisar
							contBaus++;
							mapa[i - 2][j] = 0;
						}
						// valodr cima 2
						if (mapa[i - 2][j] == 4) {
							if (mapa[i - 1][j] == 3) {// ve se te, buraco
								pular("c");
								pegaSaco();
							} else {
								andar("c");
							}
							break;
						}
					} catch (Exception e) {

					}
					try {
						if (mapa[i + 1][j] == 5) {// conta bau, remove da matriz normal, usa a aux pra analisar
							contBaus++;
							mapa[i + 1][j] = 0;
						}
						// valodr baixo 1
						if (mapa[i + 1][j] == 4) {
							andar("b");
							pegaSaco();
							break;
						}
					} catch (Exception e) {

					}
					try {
						if (mapa[i + 2][j] == 5) {// conta bau, remove da matriz normal, usa a aux pra analisar
							contBaus++;
							mapa[i + 2][j] = 0;

						}
						// valodr baixo 2
						if (mapa[i + 2][j] == 4) {
							if (mapa[i + 1][j] == 3) {// ve se te, buraco
								pular("b");
								pegaSaco();
							} else {
								andar("b");
							}
							break;
						}
					} catch (Exception e) {

					}
					nada = true;
					// random anda
					andaRandom();
					break;
					// anda memoria

				}
				if (nada) {
					break;
				}
			}
			if (nada) {
				break;
			}

		}

	}

	private static void andaRandom() {
		// todos os lados sao espacos vazios ou sem saco de dinheiro
		// andar pra onde n andou ainda
		String op = "";
		boolean c = false;
		boolean b = false;
		boolean e = false;
		boolean d = false;
		boolean c9 = false;
		boolean b9 = false;
		boolean e9 = false;
		boolean d9 = false;
		System.out.println("ANDOU RANDOM");
		int val = 0;
		boolean achou = false;

		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if (mapa[i][j] == 8) {
					// 2 casos
					// tem 0 ou 9
					// se for 0 vai random Prioridade
					// se for 9 vai random se n tiver 0 em volta
					try {
						if (mapa[i][j + 1] == 0) {
							d = true;
						} else if (mapa[i][j + 1] == 9) {
							d9 = true;
						}
					} catch (Exception x) {
						d = false;
						d9 = false;
					}
					try {
						if (mapa[i][j - 1] == 0) {
							e = true;
						} else if (mapa[i][j - 1] == 9) {
							e9 = true;

						}
					} catch (Exception x) {
						e = false;
						e9 = false;
					}
					try {
						if (mapa[i + 1][j] == 0) {
							b = true;
						} else if (mapa[i + 1][j] == 9) {
							b9 = true;
						}
					} catch (Exception x) {
						b = false;
						b9 = false;
					}
					try {
						if (mapa[i - 1][j] == 0) {
							c = true;
						} else if (mapa[i - 1][j] == 9) {
							c9 = true;

						}
					} catch (Exception x) {
						c = false;
						c9 = false;
					}
					/////////////////////////////////////////////////

					if (c9 == true && b9 == true && e9 == true && d9 == true) {
						// se n tem caminho
						val = r.nextInt(3);
						if (val == 0) {
							op = "c";
						} else if (val == 1) {
							op = "b";
						} else if (val == 2) {
							op = "d";
						} else {
							op = "e";
						}
						amb.anda(op, mapa);
						System.out.println("Caminho random que ja foi usado");
						break;
					} else if (c == true || b == true || e == true || d == true) {
						// caminho random novo
						achou = false;

						do {
							val = r.nextInt(3);
							if (val == 0 && c == true) {
								op = "c";
								achou = true;
								break;
							} else if (val == 1 && b == true) {
								op = "b";
								achou = true;
								break;
							} else if (val == 2 && d == true) {
								op = "d";
								achou = true;
								break;
							} else {
								op = "e";
								achou = true;
								break;
							}

						} while (!achou);
						amb.anda(op, mapa);
						break;
					}

					achou = false;
					do {
						val = r.nextInt(3);
						if (val == 0 && (c == true || c9 == true)) {
							op = "c";
							achou = true;
							break;
						} else if (val == 1 && (b == true || b9 == true)) {
							op = "b";
							achou = true;
							break;
						} else if (val == 2 && (d == true || d9 == true)) {
							op = "d";
							achou = true;
							break;
						} else if (e == true || e9 == true) {
							op = "e";
							achou = true;
							break;
						}

					} while (!achou);
					if (achou == true) {
						amb.anda(op, mapa);
					} else {
						System.out.println("N ERA PRA TA AQUI");
					}
					// so random no q tiver caso esteja numa ponta e tudo for 9
					break;
				} // if
			} // FOR
		} // FOR
	}

	public static void pegaSaco() {
		amb.pegaSaco(mapaAux, dindin);
		contSacos++;
	}

	public static void andar(String op) {
		amb.anda(op, mapa);
	}

	public static void pular(String op) {
		amb.pular(op, mapa);
	}

	public static void theAlgoritmoGen() {
		// GENETICO == decide quanto dividir em cada bau
		// se conhece os 4 baus e a saida
	}

	public static int qntDindin() {
		int val = 0;
		for (int i = 0; i < dindin.size(); i++) {
			val = val + dindin.get(i).getQuantidadeMoeda();
		}
		return val;
	}

	public static void printVisaoAgente(int base[][]) {

		contAcoes++;
		System.out.println("Visao Agente NRO: " + contAcoes);
		System.out.println("Quantidade de baus encontrados: " + contBaus);
		System.out.println("Achou a porta: " + achouPorta);
		System.out.println("Quantidade de dinheiro: " + qntDindin());
		System.out.println("Quantidade de sacos: " + contSacos);
		// System.out.println("Quantidade de pontos: "+ );
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
					System.out.print("  +" + "\t");

				}

				else {
					System.out.print(base[i][j] + "\t");
				}
				// mudar pra letras pra facilitar a leitura
			}
			System.out.println("");
		}
	}
	public static void printMovimento(int base[][]) {

		
		
		System.out.println("Movimento" );
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
					System.out.print("  +" + "\t");

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
