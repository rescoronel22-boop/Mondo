//package extra;
import javax.sound.sampled.*;
import java.io.File;

public class AudioPlayer {

    // Memorizziamo la clip attiva in tutto il programma
    private static Clip clipAttiva = null;

   public static void riproduciSuono(String percorsoFile, float volumeDecibel) {
		try {
			fermaAudio();

			File fileAudio = new File(percorsoFile);
			AudioInputStream streamAudio = AudioSystem.getAudioInputStream(fileAudio);
			
			clipAttiva = AudioSystem.getClip();
			clipAttiva.open(streamAudio);
			
			// Controllo del volume
			try {
				FloatControl gainControl = (FloatControl) clipAttiva.getControl(FloatControl.Type.MASTER_GAIN);
				gainControl.setValue(volumeDecibel); // Ora riconosce correttamente la variabile!
			} catch (Exception e) {
				System.out.println("Controllo volume non supportato per questo file.");
			}

			clipAttiva.start();
			
		} catch (Exception e) {
			System.out.println("Errore durante la riproduzione dell'audio: " + e.getMessage());
		}
	}

    // --- NUOVO METODO PER FERMARE L'AUDIO ---
    public static void fermaAudio() {
        if (clipAttiva != null) {
            if (clipAttiva.isRunning()) {
                clipAttiva.stop();
            }
            clipAttiva.close();
            clipAttiva = null;
        }
    }

    public static void main(String[] args) {
        System.out.println("Riproduzione audio in corso...");
        riproduciSuono("Star Wars.wav", 0.0f);
    }
}