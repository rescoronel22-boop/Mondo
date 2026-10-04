import java.util.Scanner;
public class GeneratoreEroe{
	public static Avventuriero creaEroe(Scanner scanner){
		System.out.println("\n--- CREAZIONE EROE ---");
		
		//nome
		String nome = "";
		boolean nomeValido = false;
		while (!nomeValido) {
			System.out.print("Come ti chiami, viandante?\n> ");
			nome = scanner.nextLine().trim(); // Legge e rimuove spazi bianchi iniziali/finali
			
			if (nome.isEmpty()) {
				System.out.println("[Errore] Il nome non può essere vuoto.");
			} 
			else if (nome.matches(".*\\d.*")) { 
				System.out.println("[Errore] Il nome non può contenere numeri.");
			} 
			else if (!nome.matches("^[a-zA-ZÀ-ÿ\\s']+$")) {
				// Controlla che contenga solo lettere (anche accentate), spazi o apostrofi
				System.out.println("[Errore] Il nome contiene caratteri non validi.");
			} 
			else {
				nomeValido = true; // Se passa tutti i controlli, il nome è valido!
			}
		}
		
        //età
        int etaInt = 0;
        boolean etaValida = false;
        while (!etaValida) {
            System.out.print("Quanti inverni hai vissuto?\n> ");
            String inputEta = scanner.nextLine();
            try {
                etaInt = Integer.parseInt(inputEta);
                if (etaInt > 0 && etaInt < 50000) {
                    etaValida = true;
                } else {
                    System.out.println("[Errore] Inserisci un'età compresa tra 1 e 50000.");
                }
            } catch (NumberFormatException e) {
                System.out.println("[Errore] Devi inserire un numero valido.");
            }
        }
        String eta = String.valueOf(etaInt);
        //genere
        String genere = "";
		boolean genereValido = false;

		while (!genereValido) {
			System.out.print("Qual è il tuo genere? [M = Maschio, F = Femmina, A = Altro]\n> ");
			String inputGenere = scanner.nextLine().trim().toUpperCase();
			
			if (inputGenere.equals("M")) {
				genere = "maschio";
				genereValido = true;
			} 
			else if (inputGenere.equals("F")) {
				genere = "femmina";
				genereValido = true;
			} 
			else if (inputGenere.equals("A") || inputGenere.equals("ALTRO")) {
				System.out.print("Come desideri definirlo?\n> ");
				String customGenere = scanner.nextLine().trim();
				
				if (!customGenere.isEmpty()) {
					genere = customGenere;
					genereValido = true;
				} else {
					System.out.println("[Errore] La definizione non può essere vuota.");
				}
			} 
			else {
				System.out.println("[Errore] Scelta non valida. Inserisci M, F oppure A.");
			}
		}
		
		String classeEroe = "guerriero";
        System.out.println("Destino scelto: Un nuovo " + classeEroe + " muove i primi passi nel mondo.");
		
		// Creazione dell'eroe
        Avventuriero eroe = new Avventuriero(nome, eta, genere, 62.0, classeEroe);
        
        System.out.println("\nIl tuo viaggio ha inizio...");
        
        // Sfruttiamo il metodo che hai già per mostrare il profilo completo!
        eroe.mostraProfilo();
		
		return eroe;
	}
}