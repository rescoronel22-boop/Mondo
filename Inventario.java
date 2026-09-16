import java.util.ArrayList;

public class Inventario{
	//qui saranno creati gli oggetti che conterrà la scatola
	//lista dinamica a espansione infinita
	ArrayList<String> oggetti = new ArrayList<>();	
	
	//costruttore 1: //oggetti di partenza
	public Inventario(){	//costruttore vuoto
		this.aggiungi("spada di Serpeverde");
		this.aggiungi("pietra della guarigione");
		this.aggiungi("cucciolo di drago Berserk");
		this.aggiungi("dito di Sauron");
		this.aggiungi("mantello dell'invicibilità" );
	}	
		
	//costruttore 2: 
	public Inventario(String oggettoPartenza){
		this.aggiungi(oggettoPartenza);
	}
		
	//inserimento oggetto senza restrizioni
	void aggiungi(String oggetto){
		oggetti.add(oggetto);
		//System.out.println("-> [INVENTARIO] Inserito: " + oggetto);
	}
	
	void mostraContenuto(){
		if(oggetti.isEmpty()){
			System.out.println("L'inventario è vuoto");
			return;
		}
		//System.out.println("Contenuto(" + oggetti.size() + "elementi): ");
		System.out.println();
		
		// Ciclo for-each: scorre ogni elemento dell'ArrayList e lo stampa su una nuova riga
		for (String elemento : oggetti) {
			System.out.println(" -> " + elemento);
		}
	}
	
	public String estrai(String oggettoCercato) {
        // Scorriamo la lista per cercare l'oggetto
        for (int i = 0; i < oggetti.size(); i++) {
			if (oggetti.get(i).equalsIgnoreCase(oggettoCercato)) {	//cerca senza distinguere tra maiuscole e minscole
				return oggetti.remove(i); // remove(index) rimuove e restituisce l'elemento in un colpo solo
			}
		}
		return null;
    }
}
