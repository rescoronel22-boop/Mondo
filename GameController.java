import java.util.Scanner;
public class GameController{
	private Avventuriero eroe;
	private Scatola scatola;
	private Scanner scanner;
	
	//Costruttore
	public GameController(Avventuriero eroe, Scatola scatola, Scanner scanner){
		this.eroe = eroe;
        this.scatola = scatola;
        this.scanner = scanner;
	}
	
	public void avviaGioco(){
		boolean inGioco = true;
		while (inGioco) {
            System.out.println("\n--- MENU PRINCIPALE ---");
            System.out.println("Hai una scatola davanti a te.");
            System.out.println("1. Apri la scatola");
			System.out.println("0. Esci dal gioco");
			System.out.print("Scegli un'azione\n> ");
			//System.out.println("2. Mostra Profilo Eroe"); // Nuova opzione
            
            String scelta = scanner.nextLine().trim();
            
            switch (scelta) {
                case "1":
				scatola.apri();
				//System.out.println("scatola aperta"); // Oppure il messaggio che preferisci
				System.out.println("Hai aperto la scatola con successo!");
				
				// Interrompiamo il ciclo perché la fase iniziale è completata
				inGioco = false; 
				break;
				
				/*
				case "2":
                // Mostra i dati dell'eroe già creato, senza ricrearlo
                eroe.mostraProfilo();
                
				// Mette in pausa il gioco aspettando che l'utente prema Invio
                System.out.println("\nPremi INVIO per continuare...");
                scanner.nextLine(); 
                break;
				*/
				
                    
                case "0":
                    // Quando preme 0, invece di uscire subito, chiediamo cosa vuole fare
                    System.out.println("\nHai scelto di uscire (tasto 0).");
                    System.out.print("Vuoi davvero uscire? (Digita 'C' per continuare, 'U' per uscire): ");
                    String rispostaUscita = scanner.nextLine().trim().toUpperCase();
                    
                    if (rispostaUscita.equals("C")) {
                        System.out.println("Perfetto, continuiamo a giocare!");
                        // Il ciclo while continua e torna al menu
                    } else if (rispostaUscita.equals("U")) {
                        inGioco = false; // Ferma il ciclo e chiude il gioco
                        System.out.println("\nIl tuo viaggio si interrompe qui. Arrivederci, viandante!");
						System.exit(0);
                    } else {
                        System.out.println("[Errore] Scelta non riconosciuta. Ritorno al menu principale.");
                    }
                    break;
                    
                default:
                    System.out.println("[Errore] Scelta non valida. Inserisci 1 o 0.");
            }
        }
    }
	
	
	//metodi
	public void prendiOggettoIndice(int sceltaUtente) {
		int indiceReale = sceltaUtente - 1;
		String nomeOggetto = scatola.getInventario().getNomeOggettoIndice(indiceReale);

		if (nomeOggetto != null) {
			// Passi direttamente la scatola e il nome all'eroe, che farà tutto il lavoro!
			eroe.prendiOggetto(scatola, nomeOggetto);
		} else {
			System.out.println("Posizione non valida.");
		}
	}
	
	
	public void prendiOggettoIndice() {
		System.out.println("Scegli 5 oggetti inserendo i numeri delle posizioni (senza ripetizioni):");

		int[] scelteUtente = new int[5];
		int conteggio = 0;

		while (conteggio < 5) {
			System.out.print("Scelta " + (conteggio + 1) + " - Inserisci il numero dell'oggetto: ");
			int scelta = scanner.nextInt();
			scanner.nextLine(); // Pulisce il buffer

			int indiceReale = scelta - 1;

			// 1. Verifichiamo se l'indice esiste nella scatola
			String nomeOggetto = scatola.getInventario().getNomeOggettoIndice(indiceReale);

			if (nomeOggetto != null) {
				// 2. Controlliamo se il numero è già stato inserito in precedenza
				boolean giaScelto = false;
				for (int j = 0; j < conteggio; j++) {
					if (scelteUtente[j] == indiceReale) {
						giaScelto = true;
						break;
					}
				}

				if (giaScelto) {
					System.out.println("Hai già scelto questo oggetto! Scegline un altro.");
				} else {
					scelteUtente[conteggio] = indiceReale;
					conteggio++;
				}
			} else {
				System.out.println("Posizione non valida o oggetto non esistente! Riprova.");
			}
		}

		// Fase di prelievo finale in blocco
		System.out.println("\nConferma e prelievo dei 5 oggetti...");
		for (int i = 0; i < 5; i++) {
			String nomeOggetto = scatola.getInventario().getNomeOggettoIndice(scelteUtente[i]);
			if (nomeOggetto != null) {
				eroe.prendiOggetto(scatola, nomeOggetto);
			}
		}
	}
	
}