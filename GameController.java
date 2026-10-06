import java.util.Scanner;
public class GameController{
	private Avventuriero eroe;
	private Scatola scatola;
	private Scanner scanner;
	private Mostro mostro;
	
	//Costruttore
	public GameController(Avventuriero eroe, Scatola scatola, Scanner scanner, Mostro mostro){
		this.eroe = eroe;
        this.scatola = scatola;
        this.scanner = scanner;
		this.mostro = mostro; 
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
		System.out.println();
		//System.out.println("Forze oscure si avvicinano al nostro mondo...");
		//scanner.nextLine();
		System.out.println("scegli 5 oggetti per il tuo equipaggiamento: ");
		System.out.println("Scegli 5 oggetti inserendo i numeri delle posizioni (senza ripeterli):");

		// Salviamo direttamente i NOMI degli oggetti scelti, non gli indici
		String[] nomiScelti = new String[5];
		int conteggio = 0;

		while (conteggio < 5) {
			System.out.print("Scelta " + (conteggio + 1) + " - Inserisci il numero dell'oggetto: ");
			String inputUtente = scanner.nextLine();
			int scelta;
			try {
				scelta = Integer.parseInt(inputUtente); // Proviamo a convertirla in numero
			} catch (NumberFormatException e) {
				System.out.println("Inserisci un numero valido, non lettere o caratteri speciali!");
				continue; // Salta il giro e richiede l'input senza bloccare il programma
			}

			int indiceReale = scelta - 1;

			// 1. Otteniamo il nome dell'oggetto in base all'indice attuale
			String nomeOggetto = scatola.getInventario().getNomeOggettoIndice(indiceReale);

			if (nomeOggetto != null) {
				// 2. Controlliamo se questo nome è già stato scelto
				boolean giaScelto = false;
				for (int j = 0; j < conteggio; j++) {
					if (nomiScelti[j] != null && nomiScelti[j].equals(nomeOggetto)) {
						giaScelto = true;
						break;
					}
				}

				if (giaScelto) {
					System.out.println("Hai già scelto questo oggetto! Scegline un altro.");
				} else {
					// Memorizziamo direttamente il nome dell'oggetto
					nomiScelti[conteggio] = nomeOggetto;
					conteggio++;
				}
			} else {
				System.out.println("Posizione non valida o oggetto non esistente! Riprova.");
			}
		}

		// Fase di prelievo finale usando i nomi salvati
		System.out.println("\nConferma e prelievo dei 5 oggetti...");
		for (int i = 0; i < 5; i++) {
			if (nomiScelti[i] != null) {
				eroe.prendiOggetto(scatola, nomiScelti[i]);
			}
		}
	}
	
	public void gestisciPreparazioneBattaglia() {
		boolean prontoPerBattaglia = false;
		
		while (!prontoPerBattaglia) {
			System.out.println("\n==============================================");
			System.out.println("          PREPARAZIONE ALLA BATTAGLIA        ");
			System.out.println("==============================================");
			System.out.println("1. Vedi equipaggiamento");
			System.out.println("2. Informazioni mostro");
			System.out.println("3. Inizia battaglia");
			System.out.println("0. Esci dal gioco");
			System.out.print("Scegli un'opzione -> ");
			
			String sceltaPrep = scanner.nextLine().trim();
			
			switch (sceltaPrep) {
				case "1":
					// Mostra l'equipaggiamento usando il catalogo oggetti
					// Assicurati di avere accesso al catalogo anche qui (o passalo come parametro)
					eroe.guardaZaino(Inventario.getCatalogoOggetti()); 
					System.out.println("\n[Premi INVIO per continuare...]");
					//scanner.nextLine();
					break;
					
				case "2":
					System.out.println("\n--- 👁️ ANALISI DEL NEMICO ---");
					System.out.println("Nome: " + mostro.getTipologia());
					System.out.println("HP massimi: " + mostro.getVita()); // Oppure mostro.getHp() se dinamico
					System.out.println("Attacco base: " + mostro.getPuntiAttacco());
					System.out.println("Corazza/Difesa: " + mostro.getPuntiDifesa());
					System.out.println("\n[Premi INVIO per continuare...]");
					//scanner.nextLine();
					break;
					
				case "3":
					System.out.println("\n--- INIZIO BATTAGLIA ---");
					prontoPerBattaglia = true; // Esce dal ciclo e avvia lo scontro
					break;
					
				case "0":
					System.out.println("Uscita dal gioco. Alla prossima!");
					System.exit(0);
					break;
					
				default:
					System.out.println("❌ Scelta non valida. Inserisci un numero tra 0 e 3.");
			}
		}
	}
	
	// Metodo di test rapido che sfrutta esattamente la logica originale dell'eroe
	public void assegnaEquipaggiamentoDefaultTest() {
		// Assicuriamoci che la scatola sia aperta per il test (se richiesto dal controllo isAperta())
		// scatola.apri(); // Scommenta se la scatola deve essere aperta forzatamente

		int conteggio = 0;
		
		// Cicliamo per prendere i primi 5 oggetti disponibili
		for (int i = 0; i < 5; i++) {
			// 1. Otteniamo il nome dell'oggetto tramite l'indice della scatola
			String nomeOggetto = scatola.getInventario().getNomeOggettoIndice(i);
			
			if (nomeOggetto != null) {
				// 2. Usiamo direttamente il metodo originale dell'eroe!
				eroe.prendiOggetto(scatola, nomeOggetto);
				conteggio++;
			}
		}
		
		System.out.println("✅ [TEST] Equipaggiamento di default assegnato con successo (" + conteggio + "/5)!");
}
}

