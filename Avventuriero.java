import java.util.Map;
import java.util.HashMap;
public class Avventuriero{
	//Stato privato(INCAPSULAMENTO)
	String nome;
	String eta;
	String sesso;
	int peso;
	String classe; //principiante, verano, esperto
	private int hp; 
	private Equipaggiamento equipaggiamento;
	
	//Costruttore
	public Avventuriero(String nome, String eta, String sesso, int peso, String classe, int hp){
		this.nome = nome;
		this.eta = eta;
		this.sesso = sesso;
		this.peso = peso;
		this.classe = classe;
		this.hp = hp;
		this.equipaggiamento = new Equipaggiamento();
	}
	
	//metodi getter e setter
	public int getHp() {
		return this.hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}
	
	public Equipaggiamento getEquipaggiamento() { //serve per utilizzare equipaggiamento
        return this.equipaggiamento;
    }

    public String getNome() {
        return this.nome;
    }
		
	//METODI DI AVVENTURIERO
	public void apriScatola(Scatola scatola){
		System.out.println(nome + " tenta di aprire la scatola...");
		scatola.apri();	//chiama il metodo della Scatola
	}
	
	public void chiudiScatola(Scatola scatola){
		System.out.println(nome + " tenta di chiudere la scatola...");
		scatola.chiudi();
	}
	
	public void esaminaScatola(Scatola scatola){
		System.out.println("esamina la scatola: ");
		if(scatola.isAperta()){
			System.out.println("è aperta!");
			scatola.guardaDentro();
		}else
			System.out.println("è chiusa");
	}
	
	public void prendiOggetto(Scatola scatola, String nomeOggetto){
		System.out.println(nome + " prova a prendere " + nomeOggetto + "...");
		if(scatola.isAperta()){
			// Evoca l'oggetto per stampare il messaggio di successo/fallimento
			scatola.evoca(nomeOggetto);
			
			// Aggiunge la stringa del nome all'equipaggiamento
			this.equipaggiamento.aggiungiOggetto(nomeOggetto);
		} else {
			System.out.println("Non può farlo: la scatola è chiusa...");
		}
	}	
	
	public void scambia(Scatola scatola, String oggettoDaPrendere, String oggettoDaDepositare) {
		System.out.println(this.nome + " tenta uno scambio con la scatola...");
		
		// 1. Controllo di sicurezza sulla scatola
		if (!scatola.isAperta()) {
			System.out.println("Scambio fallito: la scatola è chiusa!");
			return;
		}
		
		// 2. Verifichiamo se l'eroe possiede effettivamente l'oggetto che vuole dare in cambio
		// (Presupponendo che l'eroe abbia un metodo o un inventario personale per verificare cosa possiede)
		// Se l'eroe per ora non ha un inventario personale e vuoi solo simulare lo scambio con la scatola:
		
		System.out.println("-> Vuoi prendere: " + oggettoDaPrendere);
		System.out.println("-> Vuoi lasciare in cambio: " + oggettoDaDepositare);
		
		// 3. Eseguiamo l'azione: preleviamo dalla scatola
		OggettoStats statsOttenute = scatola.evoca(oggettoDaPrendere, false);
		
		if (statsOttenute != null) {
			// 3. AGGIUNGIAMO L'OGGETTO ALL'EQUIPAGGIAMENTO DELL'EROE!
			boolean aggiunto = this.equipaggiamento.aggiungiOggetto(oggettoDaPrendere);
			
			if (aggiunto) {
				// 4. Se lo zaino aveva spazio, mettiamo l'oggetto dell'eroe dentro la scatola
				scatola.getInventario().aggiungi(oggettoDaDepositare);
				
				// (Opzionale: qui potresti anche rimuovere l'oggetto depositato dall'equipaggiamento dell'eroe, 
				// se avevi già inserito la spada prima nello zaino)
				
				System.out.println("SCAMBIO COMPLETATO CON SUCCESSO!");
				System.out.println("Hai scambiato il tuo '" + oggettoDaDepositare + "' con '" + oggettoDaPrendere + "'.");
				} else {
					// Se lo zaino era pieno, restituiamo l'oggetto alla scatola per sicurezza
					// (o gestisci il blocco prima di evocare)
					System.out.println("Scambio fallito: il tuo equipaggiamento è pieno (Max 5 oggetti)!");
				}
		} else {
			System.out.println("Scambio fallito: l'oggetto desiderato non è disponibile nella scatola.");
		}
	}
	
	// Metodo per far vedere l'equipaggiamento 
    public void guardaZaino(Map<String, OggettoStats> catalogo) {
		equipaggiamento.mostraEquipaggiamento(catalogo);
	}
	
	public void mostraProfilo() {
		System.out.println("\n┌────────────────────────────────────────────────────────┐");
		System.out.println("│               PROFILO DELL'AVVENTURIERO                │");
		System.out.println("├────────────────────────────────────────────────────────┤");
		System.out.println("│ Nome:    " + String.format("%-46s", this.nome) + "│");
		System.out.println("│ Età:     " + String.format("%-46s", this.eta) + "│");
		System.out.println("│ Genere:  " + String.format("%-46s", this.sesso) + "│");
		System.out.println("│ Peso:    " + String.format("%-46s", this.peso + " kg") + "│");
		System.out.println("│ Classe:  " + String.format("%-46s", this.classe) + "│");
		System.out.println("└────────────────────────────────────────────────────────┘");
	}
}
