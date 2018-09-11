import java.util.Random;

public class Ambiente {
	Random r = new Random();
	int [][] base = new int [10][10];


	
	
	//0 = espaco vazio
	//1 = paredao
	//2 = parede
	//3 = buraco
	//4 = saco dinheiro
	//5 = bau 
	//6 = saida
	//7 = spawn
	public void GeraAmb(int [][]amb){
	}

	public void printAmbiente(int base[][]){

		for (int i=0; i<10; i++) {
			for (int j=0; j<10; j++) {
				System.out.print("| ");
				System.out.print(base[i][j] + "\t");


			}
			System.out.println("");
		}
	}
	
}
