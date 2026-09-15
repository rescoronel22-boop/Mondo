// Struttura concettuale di un server Open World
public class ServerMondo {
    public static void main(String[] args) {
        inizializzaMondo();
        
        while (serverInEsecuzione) {
            ascoltaNuoveConnessioni();  // Gestisce login/logout
            aggiornaStatoMondo();       // Muove oggetti, applica fisica
            inviaAggiornamentiAUtenti(); // Invia i dati ai giocatori connessi
        }
    }
}