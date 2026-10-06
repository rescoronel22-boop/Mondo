import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;
import extra.Art;
import extra.Art4;
import  extra.Art5;
import  extra.Art6;
//questo mondo conterrà oggetti casuali su cui fare sperimenti
public class Mondo{
	public static void main(String[] args){
		System.out.println("Benvenuto in Mondo");
		//scanner
		Scanner scanner = new Scanner(System.in);
		
		//creazione oggetti
		Scatola s = new Scatola("eva", "bianco", "legno");
		Mostro mostro = new Mostro("Mimikyu", 200, 25, 10);
		Map<String, OggettoStats> catalogoOggetti = Inventario.getCatalogoOggetti();
		GameController controller = new GameController(eroe, s, scanner, mostro);
		Avventuriero eroe = new Avventuriero("Ser", "22", "maschio", 62, "guerriero", 100);
		
		AudioPlayer.riproduciSuono("Star Wars.wav", 0.0f);

		scanner.nextLine();
		Art4.stampaLogo();
		scanner.nextLine();
		Art5.stampaLogo();
		scanner.nextLine();
		Art.stampaLogo();
		
		scanner.nextLine();
		System.out.println("Tanto tempo fa in una galassia lontana...");
		scanner.nextLine();
		System.out.println("Molto molto lontana...");
		scanner.nextLine();
		System.out.println("Un eroe muoveva i passi al proprio destino...");
		scanner.nextLine();
		
		//Avventuriero eroe = GeneratoreEroe.creaEroe(scanner);
		
		//s.apri();
		//controller.assegnaEquipaggiamentoDefaultTest();
		
        // Avvii il gioco chiamando il metodo sul controller!
        controller.avviaGioco();
		s.guardaDentro();
		
		controller.prendiOggettoIndice();
		scanner.nextLine();
		System.out.print("\n[Premi INVIO per continuare...] ");
        //scanner.nextLine(); // Legge la pressione del tasto Invio
		
		controller.gestisciPreparazioneBattaglia();
		
		//eroe.scambia(s, "cucciolo di drago Berserk", "spada di Serpeverde");
		//eroe.guardaZaino(Inventario.getCatalogoOggetti());
		//s.guardaDentro(); //contenuto scatola
		//inizio battaglia
		String risultato = Combattimento.eseguiBattaglia(eroe, mostro, catalogoOggetti);

		// 3. Gestisci la fine del gioco in base a come è andata
		if (risultato.equals("VITTORIA")) {
			System.out.println("Il viaggio continua!");
		} else if (risultato.equals("SCONFITTA")) {
			System.out.println("Game Over.");
		} else {
			System.out.println("Sei tornato sui tuoi passi.");
		}
		System.out.println("Fine dell'avventura");
		//AudioPlayer.riproduciSuono("Star Wars.wav", 0.0f);
		scanner.nextLine();
		// Chiude lo scanner per evitare leak di risorse
        scanner.close();
	}
}
/*
	###coda lista di oggetti da creare###
*/






