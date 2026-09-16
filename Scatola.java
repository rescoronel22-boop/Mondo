//posso usare questa scatola in main per il principio di COMPOSIZIONE 
public class Scatola{
	//Stato
	String nome;
	String colore;
	String consistenza;
	
	//COMPOSIZIONE: inventario è proprietà della scatola	
	//solo la classe corrente può accedere e modificare ad Inventario -> 	INCAPSULAMENTO
	private Inventario inventario = new Inventario();
	
	//valori di default	//se non assegni valori java assegna valori default
	boolean integra = true;	//nasce sempre intatta
	boolean aperta = false;	//è chiusa inizialmente
	double pesoGr = 100.00;	//peso iniziale
	
	//Costruttore
	Scatola(String nome, String colore, String consistenza){
		this.nome = nome;
		this.colore = colore;
		this.consistenza = consistenza;	
	}
	
	//METODI DELLA CLASSE
	//metodi per gestire lo stato
	public void apri(){
		this.aperta = true;	//il this si riferisce al boolean aperta
		System.out.println("scatola aperta ");
	}
	
	public void chiudi(){
		this.aperta = false;
		System.out.println("scatola chiusa " );
	}
	
	//inserimento oggetti: delega Inventario
	public void riponi(String oggetto){
		if(!integra){
			System.out.println("La scatola è rotta! Non puoi riporre nulla.");
			return;
		}
		if(!aperta){
			System.out.println("Impossibile inserire '" + oggetto + "': la scatola è chiusa!");
			return;
		}
		
		//delegazione
		inventario.aggiungi(oggetto);	//si riferisce all'oggetto inventario creato in questa classe
	}
	
	//ispezione del contenuto
	public void guardaDentro(){
		if(!aperta){
			System.out.println("La scatola è chiusa. Non puoi guardare dentro!");
			return;
		}
		System.out.print("Contenuto: ");
		
		inventario.mostraContenuto(); // Delega all'inventario
	}
	
	//abilità Evocazione: permette di prendere oggetti dalla scatola	
	public String evoca(String oggetto){
		//per poter utilizzare questa abilità devono soddisfare due condizioni
		//la scatola è aperta ed è integra
		if (!integra) {
        System.out.println("La scatola è rotta! Il potere di evocazione non funziona.");
			return null;
		}
		if (!aperta) {
			System.out.println("La scatola è chiusa! Devi aprirla prima di evocare.");
			return null;
		}
		
		//DELEGAZIONE: l'inventario cerca e rimuove l'oggetto dalla lista
		//serve una variabile di tipo String per raccogliere un oggetto di tipo String
		//oggettoEvocato diventa l'oggetto che estraiamo dall'inventario
		String oggettoEvocato = inventario.estrai(oggetto);

		if (oggettoEvocato != null) {
			System.out.println("EVOCAZIONE RIUSCITA!: " + oggettoEvocato);
		} else {
			System.out.println("EVOCAZIONE FALLITA: '" + oggetto + "' non si trova nella scatola!");
		}
		return oggettoEvocato;
	}
}
