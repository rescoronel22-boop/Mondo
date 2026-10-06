import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Inventario{
	//qui saranno creati gli oggetti che conterrà la scatola
	//lista dinamica a espansione infinita
	// Mappa: Chiave = Nome (String), Valore = Statistiche (OggettoStats)
	//private OggettoStats[] listaOggetti;
	
	//Catalogo
	private static final Map<String, OggettoStats> catalogoOggetti = new HashMap<>();
	//oggetti posseduti
	private List<String> oggettiPosseduti;
	
	//costruttore 1: //oggetti di partenza
	public Inventario(){	
		this.oggettiPosseduti = new ArrayList<>();
	
		//public StatisticheOggetto(int attacco, int difesa, int curaHP, String descrizioneAbilita);
		catalogoOggetti.put("spada di Serpeverde", new OggettoStats(30, 5, 0, "veleno letale", 8));
        catalogoOggetti.put("pietra della guarigione", new OggettoStats(0, 0, 50, "cura 50 hp", 2));
        catalogoOggetti.put("cucciolo di drago Berserk", new OggettoStats(45, 20, 0, "sputa fuoco", 3));
        catalogoOggetti.put("dito di Sauron", new OggettoStats(25, 10, 0, "influenza oscura", 8));
        catalogoOggetti.put("mantello dell'invicibilità", new OggettoStats(0, 50, 0, "schiva gli attacchi", 2));
        catalogoOggetti.put("pistola di legno", new OggettoStats(5, 0, 0, "danno minimo"));
        catalogoOggetti.put("fionda indiana", new OggettoStats(18, 2, 0, "attacco a distanza", 10));
        catalogoOggetti.put("martello di Loki", new OggettoStats(35, 15, 0, "stordimento", 4));
        catalogoOggetti.put("scudo di Excalibur", new OggettoStats(10, 40, 0, "difesa assoluta", 4));
        catalogoOggetti.put("occhiali da sole", new OggettoStats(0, 1, 0, "stile +100", 1)); // easter egg
		
		 
        this.aggiungi("spada di Serpeverde");
        this.aggiungi("pietra della guarigione");
		this.aggiungi("cucciolo di drago Berserk");
		this.aggiungi("dito di Sauron");
		this.aggiungi("mantello dell'invicibilità");
		this.aggiungi("pistola di legno");
		this.aggiungi("fionda indiana");
		this.aggiungi("martello di Loki");
		this.aggiungi("scudo di Excalibur");
		this.aggiungi("occhiali da sole");
	}	
	// Ora puoi aggiungere gli oggetti iniziali scrivendo solo il nome!
		
	// --- COSTRUTTORE 2 (opzionale, se vuoi inizializzarlo con un singolo oggetto e un nome) ---
    public Inventario(String nome, OggettoStats oggettoPartenza){
        catalogoOggetti.put(nome, oggettoPartenza);
    }
	
	//getter
	public static Map<String, OggettoStats> getCatalogoOggetti() {
		return catalogoOggetti;
	}
	
	public String getOggettoIndice(int indice) {
		return oggettiPosseduti.get(indice);
	}
        
    // --- INSERIMENTO ---
   public void aggiungi(String nomeOggetto) {
    if (catalogoOggetti.containsKey(nomeOggetto)) {	//containsKey, nativo di Java
			oggettiPosseduti.add(nomeOggetto);
			// System.out.println("-> [INVENTARIO] Inserito: " + nomeOggetto);
		} else {
			System.out.println("Errore: L'oggetto '" + nomeOggetto + "' non esiste nel gioco.");
		}
	}
    
    // --- MOSTRA CONTENUTO (Adattato per HashMap) ---
	public void mostraInventario() {
		System.out.println("╔══════════════════════════════════════════════════════════════╗");
		System.out.println("║                === CONTENUTO DELLA SCATOLA ===               ║");
		System.out.println("╚══════════════════════════════════════════════════════════════╝");
		
		if (oggettiPosseduti.isEmpty()) {
			System.out.println("La scatola è vuota.");
			return;
		}

		int indice = 1;
		for (String nomeOggetto : oggettiPosseduti) {
			OggettoStats stats = catalogoOggetti.get(nomeOggetto);
			
			// Stampiamo a capo separando il numero/nome dalle statistiche
			System.out.println(indice + ". " + nomeOggetto);
			System.out.println("   └─ Stats: " + stats);
			System.out.println("----------------------------------------------------------------");
			indice++;
		}
	}
    
    // --- ESTRAI (Adattato per HashMap) ---
	public OggettoStats estrai(String oggettoCercato) {
		// Scorriamo la lista degli oggetti posseduti tramite indice per rimuovere in sicurezza
		for (int i = 0; i < oggettiPosseduti.size(); i++) {
			String nome = oggettiPosseduti.get(i);
			
			// Se trova l'oggetto ignorando maiuscole/minuscole
			if (nome.equalsIgnoreCase(oggettoCercato)) {
				// 1. Rimuove l'oggetto dalla lista dell'inventario
				oggettiPosseduti.remove(i);
				
				// 2. Prende le statistiche dal catalogo globale e le restituisce
				return catalogoOggetti.get(nome);
			}
		}
		
		// Se l'oggetto non è stato trovato nella scatola
		System.out.println("L'oggetto '" + oggettoCercato + "' non è presente nella scatola.");
		return null;
	}
	
	public String getNomeOggettoIndice(int indice) {//metodo di supporto per ottenere oggetto tramite indice
		if (indice >= 0 && indice < oggettiPosseduti.size()) {
			return oggettiPosseduti.get(indice);
		}
		return null; // Indice non valido
	}
}
/*
		catalogoOggetti.put("🗡️ spada di Serpeverde", new OggettoStats(30, 5, 0, "veleno letale", 8));
        catalogoOggetti.put("💎 pietra della guarigione", new OggettoStats(0, 0, 50, "cura 50 hp", 2));
        catalogoOggetti.put("🐉 cucciolo di drago Berserk", new OggettoStats(45, 20, 0, "sputa fuoco", 3));
        catalogoOggetti.put("💍 dito di Sauron", new OggettoStats(25, 10, 0, "influenza oscura", 8));
        catalogoOggetti.put("🧥 mantello dell'invicibilità", new OggettoStats(0, 50, 0, "schiva gli attacchi", 2));
        catalogoOggetti.put("🪵 pistola di legno", new OggettoStats(5, 0, 0, "danno minimo"));
        catalogoOggetti.put("🏹 fionda indiana", new OggettoStats(18, 2, 0, "attacco a distanza", 10));
        catalogoOggetti.put("🔨 martello di Loki", new OggettoStats(35, 15, 0, "stordimento", 4));
        catalogoOggetti.put("🛡️ scudo di Excalibur", new OggettoStats(10, 40, 0, "difesa assoluta", 4));
        catalogoOggetti.put("🕶️ occhiali da sole", new OggettoStats(0, 1, 0, "stile +100", 1)); // easter egg
		
		 
        this.aggiungi("🗡️ spada di Serpeverde");
        this.aggiungi("💎 pietra della guarigione");
		this.aggiungi("🐉 cucciolo di drago Berserk");
		this.aggiungi("💍 dito di Sauron");
		this.aggiungi("🧥 mantello dell'invicibilità");
		this.aggiungi("🪵 pistola di legno");
		this.aggiungi("🏹 fionda indiana");
		this.aggiungi("🔨 martello di Loki");
		this.aggiungi("🛡️ scudo di Excalibur");
		this.aggiungi("🕶️ occhiali da sole");*/
