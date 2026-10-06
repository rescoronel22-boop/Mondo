import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;
public class Combattimento{
	//metodo principale per il combattimento
	public static String eseguiBattaglia(Avventuriero eroe, Mostro mostro, Map<String, OggettoStats> catalogoOggetti){
		Scanner scanner = new Scanner(System.in);
		// 🛑 1. FERMA LA MUSICA PRECEDENTE
		// (Usa il nome del metodo che hai nella tua classe AudioPlayer, es. fermaAudio() o stopMusica())
		AudioPlayer.fermaAudio();
		
			
		// --- STEP 1: Prima scritta e attesa INVIO ---
		System.out.print("\033[H\033[2J");
		System.out.flush();
		System.out.println("==============================================================");
		System.out.println("              ⚠️ UNA PRESENZA MINACCIOSA SI AVVICINA... ⚠️       ");
		System.out.println("==============================================================");
		System.out.println();
		System.out.println(" Senti un brivido gelido per la schiena...");
		System.out.println();
		//System.out.println(" [Premi INVIO per procedere...] ");
		scanner.nextLine(); // Il gioco si blocca qui finché l'utente non preme Invio
		
		// 🎵 1. CAMBIO MUSICA: Avvia la traccia dinamica di battaglia all'ingresso nell'arena
		// AudioPlayer.fermaSuono(); // Se hai il metodo per fermare l'intro
		AudioPlayer.riproduciSuono("Bad Apple!!.wav", -25.0f);
		
		// ✨ 2. EFFETTO VISIVO DI INIZIO SCONTRO 
		try {
			System.out.print("\033[H\033[2J");
			System.out.flush();
			
			// Ciclo di lampeggio fluido con la tecnica del cursore in alto
			for (int i = 0; i < 12; i++) {
				System.out.print("\033[H");
				System.out.println("==============================================================");
				System.out.println("                 👻 APPARIZIONE IMPROVVISA 👻               ");
				System.out.println("==============================================================");
				System.out.println();

				int varianteColore = (i % 3) + 1; // Ruota tra i colori 1, 2 e 3
				System.out.println(Grafica.getMostroColorato(varianteColore));
				
				System.out.println();
				System.out.println("           Mimikyu: \"Kyu... Vuoi giocare con me...?\"     ");
				System.out.println("==============================================================");
				System.out.flush();
				
				Thread.sleep(180); // Velocità del lampo
			}
			Thread.sleep(400);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	
		// --- STEP 3: Seconda scritta d'avvertimento ---
		System.out.print("\033[H\033[2J");
		System.out.flush();
		System.out.println("==============================================================");
		System.out.println("                 ⚔️ LA TRAPPOLA È SCATTATA! ⚔️                ");
		System.out.println("==============================================================");
		System.out.println();
		System.out.println(" Il nemico blocca ogni via di fuga. La battaglia ha inizio!");
		System.out.println();
		System.out.println(" [Premi INVIO per scendere nell'arena...] ");
		scanner.nextLine(); // Secondo stop facoltativo per dare il via definitivo
		
		// --- STEP 4: Inizia la battaglia vera e propria ---
		System.out.print("\033[H\033[2J");
		System.out.flush();

// Da qui in poi parte il tuo codice normale con la battaglia affiancata e i turni!
		
		
		int turno = 1;
		int limiteTurni = 20;
		boolean immuneProssimoTurno = false; // 👈 Aggiungilo qui
		
		// Salviamo la difesa dell'oggetto usato in questo turno per calcolare il contrattacco
        int difesaOggettoCorrente = 0;
		//System.out.println();
		System.out.println("\n---INIZIO BATTAGLIA: " + eroe.getNome() + " vs " + mostro.getTipologia() + " ---");
		System.out.println("hai a disposizione " + limiteTurni + " turni per vincere!");
		while(turno <= limiteTurni && eroe.getHp() > 0 && mostro.getVita() > 0){
			// All'interno del while del combattimento, quando stampi la schermata del turno:

			int numRigheTotali = 0; // Lo calcoliamo in base all'altezza delle immagini //<<<<----DA QUA! versione alternativa
			
			System.out.println("\n==================================================================================");
            System.out.println("TURNO " + turno + " / " + limiteTurni);
            System.out.println("==================================================================================");

            // 1. INTESTAZIONE CON I NOMI DEI DUE SFIDANTI
            System.out.println(" [ EROE: " + eroe.getNome() + " ]                                         [ MOSTRO: " + mostro.getTipologia() + " ]");
            System.out.println("----------------------------------------------------------------------------------");

            // 2. STAMPA LE DUE IMMAGINI GRANDI AFFIANCATE (tramite il metodo che abbiamo appena fatto)
            Grafica.stampaBattagliaAffiancata(1);
			
		
			// Nota: puoi sostituire 100 e 200 con i valori massimi reali dei tuoi HP/Vita se li hai memorizzati in una variabile
			System.out.println(" HP:  " + generaBarraHp(eroe.getHp(), 100) + "   HP:  " + generaBarraHp(mostro.getVita(), 200));
			
			System.out.println("======================================================================");
			System.out.println("TURNO " + turno + " / " + limiteTurni);
			System.out.println("----------------------------------------------------------------------");
			
			
			//TURNO DEL GIOCATORE
			// 1. TURNO DEL GIOCATORE (Con ciclo di ripetizione in caso di input errato)
			String scelta = "";
			boolean azioneEseguita = false;
			// Variabile da dichiarare all'inizio del turno (prima della scelta del giocatore)
			boolean bloccoAttaccoMostro = false;			////<<<<<---- QUA!
			boolean bloccoOcchiali = false;
			boolean occhialiUsati = false;
			//lampeggiaEroe = false; 
			
			while (!azioneEseguita) {
				
				System.out.println("Scegli un'azione:");
				System.out.println("1. Attacca (Usa arma equipaggiata)");
				System.out.println("0. Fuggi");
				System.out.print("Scelta -> ");
				
				scelta = scanner.nextLine();
				
				
				if (scelta.equals("1")) {
					// Mostra l'equipaggiamento usando il tuo metodo e il catalogo
					eroe.getEquipaggiamento().mostraEquipaggiamento(catalogoOggetti);
					System.out.print("Scegli il numero dello slot (da 1 a 5) da usare: ");
					
					int slotScelto = -1;
					try {
						int sceltaUtente = Integer.parseInt(scanner.nextLine());
						slotScelto = sceltaUtente - 1; // Conversione da 1-5 a indice array 0-4
					} catch (NumberFormatException e) {
						System.out.println("Input non valido! Riprova.");
						continue; // Torna a chiedere l'azione del menu
					}

					// Controllo validità slot (anche qui, se sbaglia lo slot, può riprovare senza perdere il turno)
					if (slotScelto < 0 || slotScelto > 4) {
						System.out.println("Slot non valido! Scegli un numero tra 1 e 5. Riprova.");
						continue; // Torna a chiedere l'azione del menu
					}

					// Prende il nome dell'oggetto dall'array dell'equipaggiamento
					String nomeOggettoAttivo = eroe.getEquipaggiamento().getOggettoInSlot(slotScelto);

					if (nomeOggettoAttivo == null) {
						System.out.println("Slot vuoto! Non hai usato nulla, ma puoi scegliere un altro slot o azione.");
						continue; // Ritorna alla scelta se lo slot è vuoto
					} else {
						// Prende le statistiche dal catalogo globale
						OggettoStats stats = catalogoOggetti.get(nomeOggettoAttivo); 			// <<<<---------	QUA!
						
						// 🛑 CONTROLLO SPECIALE: Se usa la pistola di legno
						if (nomeOggettoAttivo.equalsIgnoreCase("pistola di legno")) {
							// La pistola non fa danni diretti agli HP, ma riduce la corazza del mostro
							mostro.riduciCorazza(5); // Assicurati di avere questo metodo nel mostro
							System.out.println("Hai usato [pistola di legno]: non fa danni diretti, ma ha intaccato la corazza del mostro!");
						}
						
						// 🕶️ CONTROLLO SPECIALE: (Easter Egg)
						if (nomeOggettoAttivo.equalsIgnoreCase("occhiali da sole")) {
							// Controllo del turno: utilizzabili solo dopo il 3° turno (quindi turno >= 4)
							if (turno <= 3) {
								System.out.println("🕶️ È troppo presto per indossarli! Puoi usarli solo dopo il 3° turno.");
								continue; // Torna a scegliere l'azione senza sprecare il turno
							}
							// 👈 CONTROLLO USATI: Verifichiamo se sono già stati attivati in questa battaglia
							if (occhialiUsati) {
								System.out.println("❌ Gli occhiali da sole sono già stati usati!");
								continue;
							}
							// Segnamo che gli occhiali sono stati usati così non si possono riattivare
							occhialiUsati = true;
							// Effetto: porta la vita del mostro al 10% (su 200 HP massimi, imposta 20)
							int hpDieciPercento = (int) (200 * 0.10); 
							mostro.setVita(hpDieciPercento); // Assicurati di usare il metodo corretto per aggiornare la vita del mostro
							
							System.out.println("🕶️️ Indossi gli occhiali da sole con estremo stile... La luce accecante abbatte Mimikyu, riducendo la sua vita al 10%!");
							
							// 👈 ATTIVA L'IMMUNITÀ PER IL TURNO SUCCESSIVO DEL MOSTRO
							bloccoOcchiali = true;
							
							// Segna l'azione come eseguita per chiudere il turno del giocatore
							azioneEseguita = true; 
						}
						//ATTACCO PER TUTTE LE ALTRE ARMI
						else if (stats != null) {
							// Salviamo la difesa dell'oggetto per il turno del mostro
							difesaOggettoCorrente = stats.getDifesa();
							
							// 🛑 CONTROLLO DELLA DURABILITÀ
							// Saltiamo il controllo se l'oggetto ha 0 utilizzi massimi 
							if (stats.getUtilizziMassimi() > 0 && !stats.puoEssereUsato()) {
								System.out.println("❌ L'oggetto [" + nomeOggettoAttivo + "] è esaurito o rotto! Scegline un altro.");
								continue; // Riporta il giocatore all'inizio della scelta senza far avanzare il mostro
							}
							// Se l'oggetto ha ancora utilizzi, lo consumiamo subito:
							stats.consumaUtilizzo();
							
							// 1. 💚 SE L'OGGETTO CURA GLI HP
							if (stats.getCuraHp() > 0) {
								int hpCurati = stats.getCuraHp();
								int hpAttuali = eroe.getHp();
								int hpMax = 100; // Il limite massimo di HP dell'eroe
								
								int nuoviHp = Math.min(hpMax, hpAttuali + hpCurati);
								int hpEffettivamenteCurati = nuoviHp - hpAttuali; 
								
								eroe.setHp(nuoviHp);// 👈 Qui l'eroe arriva a 100 HP
								System.out.println("✨ Hai usato [" + nomeOggettoAttivo + "]! Ti rigeneri e attivi una barriera protettiva.");
								
								// 🛡️ ATTIVA LA CONDIZIONE: In questo turno il mostro non ti farà danni!
								bloccoAttaccoMostro = true; 
							}
							// 2. ⚔️ SE L'OGGETTO FA DANNO
							else if (stats.getAttacco() > 0) {
								int danno = stats.getAttacco() - mostro.getPuntiDifesa();
								if (danno < 1) danno = 1; // Danno minimo garantito
								
								mostro.setVita(mostro.getVita() - danno);
								//System.out.println("Hai attaccato con [" + nomeOggettoAttivo + "] infliggendo " + danno + " danni!"); <<<<<----ATTACCO DESCRIZIONE!
								System.out.println();
								System.out.println(Classi.getDescrizioneAttacco(nomeOggettoAttivo, danno));
							} 
							// 3. 🛡️ PER GLI OGGETTI PURAMENTE DIFENSIVI
							else {
								System.out.println("Hai usato [" + nomeOggettoAttivo + "] (Nessun attacco diretto, ma ti difendi).");
							}
						} else {
							System.out.println("Errore: Statistiche oggetto non trovate!");
							continue;
						}
					}
					
					azioneEseguita = true; // Azione completata con successo, usciamo dal ciclo del menu

				} else if (scelta.equals("0")) {
					System.out.println("Sei fuggito dalla battaglia con successo!");
					return "FUGA";
				} else {
					System.out.println("Scelta non valida! Inserisci 1 o 0.");
					// Non impostiamo azioneEseguita a true, quindi il ciclo si ripete chiedendo di nuovo la scelta!
				}
			}
			
			//CONTROLLO SE IL MOSTRO E' MORTO
			if (mostro.getVita() <= 0) {
				//scanner.nextLine();
                System.out.println("\n🎉 Complimenti! Hai sconfitto il " + mostro.getTipologia() + "!");
				//System.out.println(Grafica.artVittoria());
				//AudioPlayer.fermaAudio();
                return "VITTORIA!";
            }
			
			
			//TURNO DEL MOSTRO
			if (bloccoAttaccoMostro) {
				System.out.println("🛡️ Grazie alla pietra della cura, l'eroe è protetto e concentrato: il mostro non può infliggere danni in questo turno!");
			}else if (bloccoOcchiali) {
				System.out.println("🛡️ Mimikyu è accecato dallo stile degli occhiali da sole e barcolla stordito, saltando il turno!");
				bloccoOcchiali = false; // Resetta il flag degli occhiali
			}else{
				//danno base
				int dannoMostro = mostro.getPuntiAttacco();
				
				//attacco speciale 20%
				boolean attaccoSpeciale = Math.random() < 0.20;
				if (attaccoSpeciale) {
					dannoMostro *= 2;
					System.out.println("⚠️ ATTACCO SPECIALE! Il mostro scatena un colpo devastante!");
				}
				
				//difesaOggettoCorrente = stats.getDifesa(); //punti difesa in OggettoStats
				dannoMostro = mostro.getPuntiAttacco() - difesaOggettoCorrente;
				if (dannoMostro < 1) dannoMostro = 1;

				eroe.setHp(eroe.getHp() - dannoMostro);
				//System.out.println("Il " + mostro.getTipologia() + " ti attacca e ti infligge " + dannoMostro + " danni!");
				System.out.println("Il " + mostro.getTipologia() + " attacca e ti infligge " + Classi.GIALLO_VIVACE + dannoMostro + Classi.RESET + " danni!");
				
				//CONTROLLO SE L'EROE E' MORTO
				if (eroe.getHp() <= 0) {
					System.out.println("\n💀 Sei stato sconfitto dal " + mostro.getTipologia() + "...");
					//AudioPlayer.fermaAudio();
					return "SCONFITTA";
				}
			}
			turno++;
		}
		
		// Controllo limite dei 20 turni
        if (turno > limiteTurni) {
            System.out.println("\nTempo scaduto! 20 turni esauriti. Sei costretto a ritirarti!");
			//AudioPlayer.fermaAudio();
            return "RITIRATA";
        }

        return "FINE";
	}
	
	// --- METODO DI UTILITÀ PER LA BARRA HP ---
    public static String generaBarraHp(int hpAttuali, int hpMassimi) {
        int lunghezzaTotale = 20; // Lunghezza della barra nel terminale
        int riempite = (int) Math.round(((double) hpAttuali / hpMassimi) * lunghezzaTotale);
        
        if (riempite < 0) riempite = 0;
        if (riempite > lunghezzaTotale) riempite = lunghezzaTotale;
        
        StringBuilder barra = new StringBuilder("[");
        for (int i = 0; i < lunghezzaTotale; i++) {
            if (i < riempite) {
                barra.append("█");
            } else {
                barra.append("░");
            }
        }
        barra.append("] ").append(hpAttuali).append("/").append(hpMassimi);
        return barra.toString();
    }
	
	
	
}

//parte iniziale alternativa
/*
			// Fai un piccolo ciclo di animazione (ad esempio 4 fotogrammi di lampeggio)
			for (int frame = 1; frame <= 4; frame++) {
				// Pulisce lo schermo e riporta il cursore in alto a sinistra per ogni fotogramma
				System.out.print("\u001B[H");
				System.out.flush();

				// 1. Intestazione
				System.out.println("==================================================================================");
				System.out.println(" [ EROE: " + eroe.getNome() + " ]                                         [ MOSTRO: " + mostro.getTipologia() + " ]");
				System.out.println("--------------------------------------------------------------------------------==");

				// 2. Grafica affiancata completa
				String[] righeEroe = Grafica.getEroe().split("\n");
				String[] righeMostro = Grafica.getMostroColorato(frame).split("\n");

				int maxRighe = Math.max(righeEroe.length, righeMostro.length);
				for (int i = 0; i < maxRighe; i++) {
					String rigaE = (i < righeEroe.length) ? righeEroe[i] : "";
					String rigaM = (i < righeMostro.length) ? righeMostro[i] : "";
					// Nota: ho tolto il "vs" in mezzo alle righe per lasciare respirare le immagini grandi
					System.out.printf("%-45s   %s%n", rigaE, rigaM);
				}

				// 3. Barre HP in basso
				System.out.println("--------------------------------------------------------------------------------==");
				System.out.println(" HP:  " + generaBarraHp(eroe.getHp(), 100) + "   HP:  " + generaBarraHp(mostro.getVita(), 200));
				System.out.println("==================================================================================");

				// Pausa per l'effetto lampeggio
				try {
					Thread.sleep(250);
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
				}
			}*/
			
			// --- INCOLLA QUI LA GRAFICA DELLA BATTAGLIA E LE BARRE HP ---
			/*System.out.println("\n======================================================================");
			System.out.println(" [ EROE: " + eroe.getNome() + " ]                     [ MOSTRO: " + mostro.getTipologia() + " ]");
			System.out.println("   O                                     \\._./");
			System.out.println("  /|\\                                    (o_o)");
			System.out.println("  / \\                                    /(_)\\");
			System.out.println("----------------------------------------------------------------------");*/