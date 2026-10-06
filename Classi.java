public class Classi {

    // Codice ANSI per il giallo vivace/brillante e reset
    public static final String GIALLO_VIVACE = "\u001B[92m";
    public static final String RESET = "\u001B[0m";

    public static String getDescrizioneAttacco(String nomeArma, int danno) {
        if (nomeArma == null) return "⚔️ Attacco generico infliggendo " + GIALLO_VIVACE + danno + RESET + " danni!";
        
        // Rendiamo il numero del danno vivace
        String dannoVivace = GIALLO_VIVACE + danno + RESET;
        
        String arma = nomeArma.toLowerCase().trim();
        switch (arma) {
            case "spada di serpeverde":
                return "🐍 Ser affonda la lama con precisione: un lampo verdastro taglia l'aria, rilasciando un veleno letale che infligge " + dannoVivace + " danni!";
            case "cucciolo di drago berserk":
                return "🔥 Il piccolo drago spalanca le fauci e scatena una fiammata furiosa, arrostendo il nemico per " + dannoVivace + " danni!";
            case "dito di sauron":
                return "👁️ Un'aura mefitica e oscura si sprigiona dall'anello, scuotendo la mente del mostro e infliggendo " + dannoVivace + " danni!";
            case "pistola di legno":
                return "🔫 Punti la pistola giocattolo e premi il grilletto: un colpo innocuo rimbalza contro il mostro, infliggendo solo " + dannoVivace + " danni.";
            case "fionda indiana":
                return "🎯 Un tiro a distanza fulmineo! La pietra sfreccia nell'aria e colpisce il bersaglio, infliggendo " + dannoVivace + " danni!";
            case "martello di loki":
                return "🔨 Il pesante martello cala dall'alto con un tonfo sordo, stordendo il mostro e causando " + dannoVivace + " danni!";
            default:
                return "⚔️ Hai attaccato con [" + nomeArma + "] infliggendo " + dannoVivace + " danni!";
        }
    }

    public static String getDescrizioneSupporto(String nomeArma) {
        if (nomeArma == null) return "✨ Usato oggetto di supporto.";
        
        String arma = nomeArma.toLowerCase().trim();
        switch (arma) {
            case "pietra della guarigione":
                return "💚 Utilizzi la pietra mistica: un'ondata di calore rigenerante avvolge il tuo corpo, curando le ferite!";
            case "mantello dell'invincibilità":
                return "🧥 Agiti il mantello magico: la stoffa si fonde con le ombre, facendoti svanire per schivare l'attacco!";
            case "scudo di excalibur":
                return "🛡️ Sollevi lo scudo leggendario erigendo una barriera di luce pura: difesa assoluta attivata!";
            case "occhiali da sole":
                return "😎 Indossi gli occhiali da sole con nonchalance: stile ineguagliabile (+100) che spiazza il nemico!";
            default:
                return "✨ Hai usato [" + nomeArma + "]!";
        }
    }
}