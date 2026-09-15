import java.util.ArrayList;

//questo mondo conterrà oggetti casuali su cui fare sperimenti
public class Mondo{
	public static void main(String[] args){
		System.out.println("Benvenuto in Mondo!");
		System.out.println();
		
		//creazione oggetto scatola
		Scatola s = new Scatola("eva", "bianco", "legno");
		
		//verifica gli stati
		System.out.println("scatola: " + s.nome);
		System.out.println("colore: " + s.colore);
		System.out.println("consistenza: " + s.consistenza);
		System.out.println("integra?: " + s.integra);
		System.out.println("aperta?: " + s.aperta);
		System.out.println("peso: " + s.pesoGr  + "g");
	}
}
//per creare un Open World il server deve essere a loop infinito

//oggetto prototipo
class Equipaggiamento{
	
}

//coda lista di oggetti da creare
//equipaggiamento
//avventuriero

