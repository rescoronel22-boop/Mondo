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

		s.apri();
		//s.guardaDentro();
		//s.evoca("spada di Serpeverde");
		s.riponi("spada di Serpeverde");
        s.chiudi();
		System.out.println("------------------------------------");
		
		//creazione oggetto Avventuriero
		Avventuriero eroe = new Avventuriero("Ser", "25", "Maschio", 75.5, "Guerriero" );
		
		// 3. Prova delle azioni dell'Avventuriero sulla Scatola
        eroe.esaminaScatola(s);
        System.out.println();
        
        // Tenta di prendere la spada mentre la scatola è chiusa (fallirà)
        eroe.prendiOggetto(s, "spada di Serpeverde");
        System.out.println();
        
        // Apriamo la scatola e riproviamo
        eroe.apriScatola(s);
        eroe.esaminaScatola(s);
        System.out.println();
        
        // Ora riuscirà a prendere la spada!
        eroe.prendiOggetto(s, "spada di Serpeverde");
        System.out.println();
        
        // Chiudiamo la scatola alla fine
        eroe.chiudiScatola(s);
	}
}
//per creare un Open World il server deve essere a loop infinito

//oggetto prototipo


//###coda lista di oggetti da creare###
//equipaggiamento
//porta
//mostro 

