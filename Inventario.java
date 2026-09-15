public class Inventario{
	//qui saranno creati gli oggetti che conterrà la scatola
	//lista dinamica a espansione infinita
	ArrayList<String> oggetti = new ArrayList<>();	
	
	//costruttore 1
	public Inventario();	//costruttore vuoto
	
	//costruttore 2: specifica gli oggetti iniziali
	public Inventario(String oggettoPartenza)
		this.aggiungi(oggettoPartenza);
		
	//inserimento oggetto senza restrizioni
	void aggiungi(String oggetto){
		oggetti.add(oggetto);
		System.out.println("-> [INVENTARIO] Inserito: " + oggetto);
	}
	
	void mostraContenuto(){
		System.out.println("Contenuto(" + oggetti.size() + "elementi): " + oggetti);
	}
}