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
	static ArrayList<SacoMoeda> dindinTemp = new ArrayList<>(); // pro genetico brincar
	int dinheiro = 0;
	boolean vivo = true;
	static int[][] mapa = new int[10][10]; // usa pra andar
	static int[][] mapaAux = new int[10][10]; // usa pra baus e buracos pro estrela
	static boolean endgame = false;
	static boolean earlygame = false;
	static Ambiente amb = new Ambiente();
	static int contAcoes = 0;
	static int contBaus = 0;
	static int contSacos = 0;
	static boolean achouPorta = false;
	static Random r = new Random();
	static int geneticoVer = 0; // print do genetico de cada vez
	static int geneticoTot = 0; // print do genetico total
	static ArrayList<BauG> listaBaus = new ArrayList<>();// genetico
	static ArrayList<SolucoesG> solucoes = new ArrayList<>();// genentico

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
		// criar outro do while aqui com ACABOU
		/*
		 * do { receberInfo(); printVisaoAgente(mapa); analisar(); printMovimento(mapa);
		 * cont++; System.out.println(
		 * "----------------------------------------------------------------------------"
		 * );
		 * 
		 * if (contSacos == 16 && contBaus == 4 && achouPorta == true) { earlygame =
		 * true; } } while (!earlygame);
		 */
		System.out.println("Achou todos os sacos");
		amb.idkfa(dindin);
		for (int i = 0; i < dindin.size(); i++) {
			System.out.println(dindin.get(i).getQuantidadeMoeda());
		}

		theAlgoritmoGen();

	}

	public static void theAlgoritmoGen() {
		// GENETICO == decide quanto dividir em cada bau
		// se conhece os 4 baus e a saida
		geneticoVer = 0;
		System.out.println("Genetico x: " + geneticoVer);

		// ###inicia populacao
		// base

		for (int i = 0; i < 5; i++) {
			popular2();
		}
		// editar o pop1 e fazer q nem o 2

		for (int i = 0; i < solucoes.size(); i++) {
			System.out.println("Solucao :" + i);
			solucoes.get(i).printSolucao();
			System.out.println("------------");
		}
		// ###Populacao
		// DO
		System.out.println("APTIDAO");
		
		int resp = adptar(); //pos 0 - 1 - 2 - 3 - 4
		if(resp == -1 || resp !=0) {
			//
		}

		/*
		 * if solucao encontrada /fim = true /sai do loop
		 * 
		 * else { selecao(); Reproducao(); Mutacao();
		 * 
		 * }
		 * 
		 * 
		 */

	}

	public static int adptar() {
		// verifica a valor de cada bau
		// diferenca mais proxima de 0
		// entre todos os baus eh o melhor
		System.out.println("calcula");
		for (int i = 0; i < solucoes.size(); i++) {
			solucoes.get(i).printDif();
		}
		int pos = -1;
		int aux = solucoes.get(0).calculaDiferenca();
		for (int i = 0; i < solucoes.size(); i++) {
			if (solucoes.get(i).calculaDiferenca() < aux) {
				pos = i;
				aux = solucoes.get(i).calculaDiferenca();
			}
		}
		return pos;
	}

	public static void cloneDindin(ArrayList<SacoMoeda> din1, ArrayList<SacoMoeda> din2) {
		din2.clear();
		for (int i = 0; i < din1.size(); i++) {
			din2.add(din1.get(i));
		}
	}

	// #####genetico
	public static void popular2() {

		cloneDindin(dindin, dindinTemp);

		SolucoesG sol1 = new SolucoesG();
		int totDiv4 = dindin.size() / 4;
		BauG bau1 = new BauG();
		BauG bau2 = new BauG();
		BauG bau3 = new BauG();
		BauG bau4 = new BauG();
		sol1.baus.add(bau1);
		sol1.baus.add(bau2);
		sol1.baus.add(bau3);
		sol1.baus.add(bau4);

		// sol 1
		for (int i = 0; i < dindin.size(); i++) {
			if (totDiv4 == 1) {
				for (int j = 0; j < 4; j++) {
					int val = r.nextInt(dindin.size());
					sol1.baus.get(j).Saco.add(dindin.get(val));
					dindin.remove(dindin.get(val));

				}

			} else if (totDiv4 == 2) {
				for (int j = 0; j < 4; j++) {
					int val = r.nextInt(dindin.size());
					sol1.baus.get(j).Saco.add(dindin.get(val));
					dindin.remove(dindin.get(val));

					val = r.nextInt(dindin.size());
					sol1.baus.get(j).Saco.add(dindin.get(val));
					dindin.remove(dindin.get(val));
				}
			} else if (totDiv4 == 3) {
				for (int j = 0; j < 4; j++) {
					int val = r.nextInt(dindin.size());
					sol1.baus.get(j).Saco.add(dindin.get(val));
					dindin.remove(dindin.get(val));

					val = r.nextInt(dindin.size());
					sol1.baus.get(j).Saco.add(dindin.get(val));
					dindin.remove(dindin.get(val));

					val = r.nextInt(dindin.size());
					sol1.baus.get(j).Saco.add(dindin.get(val));
					dindin.remove(dindin.get(val));

				}
			} else if (totDiv4 == 4) {
				for (int j = 0; j < 4; j++) {
					int val = r.nextInt(dindin.size());
					sol1.baus.get(j).Saco.add(dindin.get(val));
					dindin.remove(dindin.get(val));

					val = r.nextInt(dindin.size());
					sol1.baus.get(j).Saco.add(dindin.get(val));
					dindin.remove(dindin.get(val));

					val = r.nextInt(dindin.size());
					sol1.baus.get(j).Saco.add(dindin.get(val));
					dindin.remove(dindin.get(val));

					val = r.nextInt(dindin.size());
					sol1.baus.get(j).Saco.add(dindin.get(val));
					dindin.remove(dindin.get(val));

				}
			}

		} // if sol 1
		cloneDindin(dindinTemp, dindin);

		solucoes.add(sol1);
	}

	public static void popular() {
		/*
		 * for (int i = 0; i < dindin.size(); i++) { dindinTemp.add(dindin.get(i)); }
		 */
		int tam = 5;
		SolucoesG sol1 = new SolucoesG();
		SolucoesG sol2 = new SolucoesG();
		SolucoesG sol3 = new SolucoesG();
		SolucoesG sol4 = new SolucoesG();
		SolucoesG sol5 = new SolucoesG();
		int totDiv4 = dindin.size() / 4;

		// pra cada solucao
		// pegapega dindinTemp
		// pega total de itens /4
		// coloca totDiv4 de itens em cada bau
		BauG bau1 = new BauG();
		BauG bau2 = new BauG();
		BauG bau3 = new BauG();
		BauG bau4 = new BauG();
		sol1.baus.add(bau1);
		sol1.baus.add(bau2);
		sol1.baus.add(bau3);
		sol1.baus.add(bau4);

		// sol 1
		amb.idkfa(dindin);
		for (int i = 0; i < dindin.size(); i++) {
			if (totDiv4 == 1) {
				for (int j = 0; j < 4; j++) {
					int val = r.nextInt(dindin.size());
					sol1.baus.get(j).Saco.add(dindin.get(val));
					dindin.remove(dindin.get(val));

				}

			} else if (totDiv4 == 2) {
				for (int j = 0; j < 4; j++) {
					int val = r.nextInt(dindin.size());
					sol1.baus.get(j).Saco.add(dindin.get(val));
					dindin.remove(dindin.get(val));

					val = r.nextInt(dindin.size());
					sol1.baus.get(j).Saco.add(dindin.get(val));
					dindin.remove(dindin.get(val));
				}
			} else if (totDiv4 == 3) {
				for (int j = 0; j < 4; j++) {
					int val = r.nextInt(dindin.size());
					sol1.baus.get(j).Saco.add(dindin.get(val));
					dindin.remove(dindin.get(val));

					val = r.nextInt(dindin.size());
					sol1.baus.get(j).Saco.add(dindin.get(val));
					dindin.remove(dindin.get(val));

					val = r.nextInt(dindin.size());
					sol1.baus.get(j).Saco.add(dindin.get(val));
					dindin.remove(dindin.get(val));

				}
			} else if (totDiv4 == 4) {
				for (int j = 0; j < 4; j++) {
					int val = r.nextInt(dindin.size());
					sol1.baus.get(j).Saco.add(dindin.get(val));
					dindin.remove(dindin.get(val));

					val = r.nextInt(dindin.size());
					sol1.baus.get(j).Saco.add(dindin.get(val));
					dindin.remove(dindin.get(val));

					val = r.nextInt(dindin.size());
					sol1.baus.get(j).Saco.add(dindin.get(val));
					dindin.remove(dindin.get(val));

					val = r.nextInt(dindin.size());
					sol1.baus.get(j).Saco.add(dindin.get(val));
					dindin.remove(dindin.get(val));

				}
			}

		} // if sol 1
		amb.idkfa(dindin);

		// sol 2
		for (int i = 0; i < dindin.size(); i++) {
			if (totDiv4 == 1) {
				for (int j = 0; j < 4; j++) {
					int val = r.nextInt(dindin.size());
					sol2.baus.get(j).Saco.add(dindin.get(val));
					dindin.remove(dindin.get(val));

				}

			} else if (totDiv4 == 2) {
				for (int j = 0; j < 4; j++) {
					int val = r.nextInt(dindin.size());
					sol2.baus.get(j).Saco.add(dindin.get(val));
					dindin.remove(dindin.get(val));

					val = r.nextInt(dindin.size());
					sol2.baus.get(j).Saco.add(dindin.get(val));
					dindin.remove(dindin.get(val));
				}
			} else if (totDiv4 == 3) {
				for (int j = 0; j < 4; j++) {
					int val = r.nextInt(dindin.size());
					sol2.baus.get(j).Saco.add(dindin.get(val));
					dindin.remove(dindin.get(val));

					val = r.nextInt(dindin.size());
					sol2.baus.get(j).Saco.add(dindin.get(val));
					dindin.remove(dindin.get(val));

					val = r.nextInt(dindin.size());
					sol2.baus.get(j).Saco.add(dindin.get(val));
					dindin.remove(dindin.get(val));

				}
			} else if (totDiv4 == 4) {
				for (int j = 0; j < 4; j++) {
					int val = r.nextInt(dindin.size());
					sol2.baus.get(j).Saco.add(dindin.get(val));
					dindin.remove(dindin.get(val));

					val = r.nextInt(dindin.size());
					sol2.baus.get(j).Saco.add(dindin.get(val));
					dindin.remove(dindin.get(val));

					val = r.nextInt(dindin.size());
					sol2.baus.get(j).Saco.add(dindin.get(val));
					dindin.remove(dindin.get(val));

					val = r.nextInt(dindin.size());
					sol2.baus.get(j).Saco.add(dindin.get(val));
					dindin.remove(dindin.get(val));

				}
			}

		} // if sol2
		amb.idkfa(dindin);

		// sol 3
		for (int i = 0; i < dindin.size(); i++) {
			if (totDiv4 == 1) {
				for (int j = 0; j < 4; j++) {
					int val = r.nextInt(dindin.size());
					sol3.baus.get(j).Saco.add(dindin.get(val));
					dindin.remove(dindin.get(val));

				}

			} else if (totDiv4 == 2) {
				for (int j = 0; j < 4; j++) {
					int val = r.nextInt(dindin.size());
					sol3.baus.get(j).Saco.add(dindin.get(val));
					dindin.remove(dindin.get(val));

					val = r.nextInt(dindin.size());
					sol3.baus.get(j).Saco.add(dindin.get(val));
					dindin.remove(dindin.get(val));
				}
			} else if (totDiv4 == 3) {
				for (int j = 0; j < 4; j++) {
					int val = r.nextInt(dindin.size());
					sol3.baus.get(j).Saco.add(dindin.get(val));
					dindin.remove(dindin.get(val));

					val = r.nextInt(dindin.size());
					sol3.baus.get(j).Saco.add(dindin.get(val));
					dindin.remove(dindin.get(val));

					val = r.nextInt(dindin.size());
					sol3.baus.get(j).Saco.add(dindin.get(val));
					dindin.remove(dindin.get(val));

				}
			} else if (totDiv4 == 4) {
				for (int j = 0; j < 4; j++) {
					int val = r.nextInt(dindin.size());
					sol3.baus.get(j).Saco.add(dindin.get(val));
					dindin.remove(dindin.get(val));

					val = r.nextInt(dindin.size());
					sol3.baus.get(j).Saco.add(dindin.get(val));
					dindin.remove(dindin.get(val));

					val = r.nextInt(dindin.size());
					sol3.baus.get(j).Saco.add(dindin.get(val));
					dindin.remove(dindin.get(val));

					val = r.nextInt(dindin.size());
					sol3.baus.get(j).Saco.add(dindin.get(val));
					dindin.remove(dindin.get(val));

				}
			}

		} // if sol 3
		amb.idkfa(dindin);

		// sol 4
		for (int i = 0; i < dindin.size(); i++) {
			if (totDiv4 == 1) {
				for (int j = 0; j < 4; j++) {
					int val = r.nextInt(dindin.size());
					sol4.baus.get(j).Saco.add(dindin.get(val));
					dindin.remove(dindin.get(val));

				}

			} else if (totDiv4 == 2) {
				for (int j = 0; j < 4; j++) {
					int val = r.nextInt(dindin.size());
					sol4.baus.get(j).Saco.add(dindin.get(val));
					dindin.remove(dindin.get(val));

					val = r.nextInt(dindin.size());
					sol4.baus.get(j).Saco.add(dindin.get(val));
					dindin.remove(dindin.get(val));
				}
			} else if (totDiv4 == 3) {
				for (int j = 0; j < 4; j++) {
					int val = r.nextInt(dindin.size());
					sol4.baus.get(j).Saco.add(dindin.get(val));
					dindin.remove(dindin.get(val));

					val = r.nextInt(dindin.size());
					sol4.baus.get(j).Saco.add(dindin.get(val));
					dindin.remove(dindin.get(val));

					val = r.nextInt(dindin.size());
					sol4.baus.get(j).Saco.add(dindin.get(val));
					dindin.remove(dindin.get(val));

				}
			} else if (totDiv4 == 4) {
				for (int j = 0; j < 4; j++) {
					int val = r.nextInt(dindin.size());
					sol4.baus.get(j).Saco.add(dindin.get(val));
					dindin.remove(dindin.get(val));

					val = r.nextInt(dindin.size());
					sol4.baus.get(j).Saco.add(dindin.get(val));
					dindin.remove(dindin.get(val));

					val = r.nextInt(dindin.size());
					sol4.baus.get(j).Saco.add(dindin.get(val));
					dindin.remove(dindin.get(val));

					val = r.nextInt(dindin.size());
					sol4.baus.get(j).Saco.add(dindin.get(val));
					dindin.remove(dindin.get(val));

				}
			}

		} // if sol 4
		amb.idkfa(dindin);

		// sol 5
		for (int i = 0; i < dindin.size(); i++) {
			if (totDiv4 == 1) {
				for (int j = 0; j < 4; j++) {
					int val = r.nextInt(dindin.size());
					sol5.baus.get(j).Saco.add(dindin.get(val));
					dindin.remove(dindin.get(val));

				}

			} else if (totDiv4 == 2) {
				for (int j = 0; j < 4; j++) {
					int val = r.nextInt(dindin.size());
					sol5.baus.get(j).Saco.add(dindin.get(val));
					dindin.remove(dindin.get(val));

					val = r.nextInt(dindin.size());
					sol5.baus.get(j).Saco.add(dindin.get(val));
					dindin.remove(dindin.get(val));
				}
			} else if (totDiv4 == 3) {
				for (int j = 0; j < 4; j++) {
					int val = r.nextInt(dindin.size());
					sol5.baus.get(j).Saco.add(dindin.get(val));
					dindin.remove(dindin.get(val));

					val = r.nextInt(dindin.size());
					sol5.baus.get(j).Saco.add(dindin.get(val));
					dindin.remove(dindin.get(val));

					val = r.nextInt(dindin.size());
					sol5.baus.get(j).Saco.add(dindin.get(val));
					dindin.remove(dindin.get(val));

				}
			} else if (totDiv4 == 4) {
				for (int j = 0; j < 4; j++) {
					int val = r.nextInt(dindin.size());
					sol5.baus.get(j).Saco.add(dindin.get(val));
					dindin.remove(dindin.get(val));

					val = r.nextInt(dindin.size());
					sol5.baus.get(j).Saco.add(dindin.get(val));
					dindin.remove(dindin.get(val));

					val = r.nextInt(dindin.size());
					sol5.baus.get(j).Saco.add(dindin.get(val));
					dindin.remove(dindin.get(val));

					val = r.nextInt(dindin.size());
					sol5.baus.get(j).Saco.add(dindin.get(val));
					dindin.remove(dindinTemp.get(val));

				}
			}

		} // if sol 5

		// supostamente gerou 5 solucoes
		solucoes.add(sol1);
		solucoes.add(sol2);
		solucoes.add(sol3);
		solucoes.add(sol4);
		solucoes.add(sol5);

	}

	// #####genetico
	public Agente(int dinheiro, boolean vivo, int[][] mapa) {

		this.dinheiro = dinheiro;
		this.vivo = vivo;
		this.mapa = mapa;
	}

	public static void receberInfo() {
		// manda q quer receber o mapa
		amb.observar(mapa);
		infoMap();
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
							mapaAux[i][j + 1] = 5;
							mapa[i][j + 1] = 0;
						}
						if (mapa[i][j + 1] == 4) {
							andar("d");
							pegaSaco();
							return;
						} // ve se tem buraco N agora
					} catch (Exception e) {

					}
					try {
						// valodr direita 2
						if (mapa[i][j + 2] == 4) {
							if (mapa[i][j + 2] == 5) {// conta bau, remove da matriz normal, usa a aux pra analisar
								contBaus++;
								mapaAux[i][j + 2] = 5;
								mapa[i][j + 2] = 0;
							}
							if (mapa[i][j + 1] == 3) {// ve se te, buraco
								pular("d");
								pegaSaco();

							} else {
								andar("d");
							}

							return;

						}
					} catch (Exception e) {

					}
					try {
						// valodr esquerda 1
						if (mapa[i][j - 1] == 5) {// conta bau, remove da matriz normal, usa a aux pra analisar
							contBaus++;
							mapaAux[i][j - 1] = 5;
							mapa[i][j - 1] = 0;
						}
						if (mapa[i][j - 1] == 4) {
							andar("e");
							pegaSaco();
							return;
						}
					} catch (Exception e) {

					}
					try {
						// valodr esquerda 2
						if (mapa[i][j - 2] == 5) {// conta bau, remove da matriz normal, usa a aux pra analisar
							contBaus++;
							mapaAux[i][j - 2] = 5;
							mapa[i][j - 2] = 0;
						}
						if (mapa[i][j - 2] == 4) {
							if (mapa[i][j - 1] == 3) {// ve se te, buraco
								pular("e");
								pegaSaco();
							} else {
								andar("e");
							}
							return;
						}
					} catch (Exception e) {

					}
					try {
						if (mapa[i - 1][j] == 5) {// conta bau, remove da matriz normal, usa a aux pra analisar
							contBaus++;
							mapaAux[i - 1][j] = 5;
							mapa[i - 1][j] = 0;
						}
						// valodr cima 1
						if (mapa[i - 1][j] == 4) {
							andar("c");
							pegaSaco();
							return;
						}
					} catch (Exception e) {

					}
					try {
						if (mapa[i - 2][j] == 5) {// conta bau, remove da matriz normal, usa a aux pra analisar
							contBaus++;
							mapaAux[i - 2][j] = 5;
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
							return;
						}
					} catch (Exception e) {

					}

					try {
						if (mapa[i + 2][j] == 5) {// conta bau, remove da matriz normal, usa a aux pra analisar
							contBaus++;
							mapaAux[i + 2][j] = 5;
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
							return;
						}
					} catch (Exception e) {

					}
					try {
						if (mapa[i + 1][j] == 5) {// conta bau, remove da matriz normal, usa a aux pra analisar
							contBaus++;
							mapaAux[i + 1][j] = 5;
							mapa[i + 1][j] = 0;
						}
						// valodr baixo 1
						if (mapa[i + 1][j] == 4) {
							andar("b");
							pegaSaco();
							return;
						}
					} catch (Exception e) {
						andaRandom();
						return;
					}
					nada = true;
					// anda memoria

					// random anda
					// andaRandom2();
					andaRandom();
					return;
				}
				if (nada) {
					return;
				}
			}

			if (nada) {
				return;
			}

		}

	}

	private static int geraRandom() {

		boolean okRandom = false;
		int vari = 0;

		// pega um random pra ver q lado vai
		do {
			int aleatorio = r.nextInt(4);
			if (aleatorio == 0) {
				vari = 0;
			} else if (aleatorio == 1) {
				vari = 1;
			} else if (aleatorio == 2) {
				vari = 2;
			} else if (aleatorio == 3) {
				vari = 3;
			}

		} while (okRandom == true);

		return vari;

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
		// int val = 0;
		boolean achou = false;

		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if (mapa[i][j] == 8) {
					// 2 casos
					// tem 0 ou 9
					// se for 0 vai random Prioridade
					// se for 9 vai random se n tiver 0 em volta

					// analisa em volta
					// se nao andou lado = true
					// se ja andou lado9 = true
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
						// se n tem caminho em todas as voltas novo
						int val = geraRandom();
						if (val == 0) {
							op = "c";
						} else if (val == 1) {
							op = "b";
						} else if (val == 2) {
							op = "d";
						} else if (val == 3) {
							op = "e";
						}
						amb.anda(op, mapa);
						System.out.println("Caminho random que ja foi usado");
						break;
					} else if (c == true || b == true || e == true || d == true) {
						// caminho random novo
						achou = false;

						do {
							int val = geraRandom();
							if (val == 0 && c == true) {
								op = "c";
								achou = true;

							} else if (val == 1 && b == true) {
								op = "b";
								achou = true;

							} else if (val == 2 && d == true) {
								op = "d";
								achou = true;

							} else if (val == 3 && e == true) {
								op = "e";
								achou = true;

							}

						} while (!achou);
						if (achou == true) {
							System.out.println("Caminho Random novo");
							amb.anda(op, mapa);
							return;
						} else {
							System.out.println("vai pro extra");
						}
					}

					achou = false;
					do {
						int val = geraRandom();
						if (val == 0 && (c9 == true)) {
							op = "c";
							achou = true;

						} else if (val == 1 && (b9 == true)) {
							op = "b";
							achou = true;

						} else if (val == 2 && (d9 == true)) {
							op = "d";
							achou = true;

						} else if (val == 3 && (e9 == true)) {
							op = "e";
							achou = true;

						}

					} while (!achou);
					if (achou == true) {
						System.out.println("Caminho random extra");
						amb.anda(op, mapa);
						return;
					} else {
						System.out.println("N ERA PRA TA AQUI");
					}

					// so random no q tiver caso esteja numa ponta e tudo for 9
					return;
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

	public static void infoMap() {
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if (mapa[i][j] == 5) {
					mapaAux[i][j] = 5;
				}
				if (mapa[i][j] == 3) {
					mapaAux[i][j] = 3;
				}
				if (mapa[i][j] == 1) {
					mapaAux[i][j] = 1;
				}
				if (mapa[i][j] == 2) {
					mapaAux[i][j] = 2;
				}
				if (mapa[i][j] == 6) {
					mapaAux[i][j] = 6;
				}
			}
		}
	}

	public static void printMovimento(int base[][]) {

		System.out.println("Movimento");
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
