//posso usare questa scatola in main per il principio di composizione 
public class Scatola{
	//Stato
	String nome;
	String colore;
	String consistenza;
	
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
}