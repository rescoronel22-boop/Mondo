import java.util.Scanner;

public class Art2 {
    private static volatile boolean inEsecuzione = true;

    public static void main(String[] args) {
		//AudioPlayer.riproduciSuono("Star Wars.wav");
        // Sequenze di colore ANSI
        final String YELLOW = "\u001B[33m";
        final String CYAN = "\u001B[36m";
        final String BLUE = "\u001B[34m";
        final String BOLD = "\u001B[1m";
        final String RESET = "\u001B[0m";

        // Messaggio di benvenuto in testo normale
        String titolo = YELLOW + BOLD + """
        ===================================================================
											BENVENUTI IN MONDO 
        ===================================================================
        """ + RESET;

        // FRAME 1: Stelle in posizione A
        String frame1 = CYAN + """
                   .                           +
           +                                             .
                                ___        .
         .                        _.--"~~ __"-.
                            ,-"     .-~  ~"-\\             .
                  .        .^       /        ( )      .
                +    {_.---._ /         ~
                    /    .  Y                             .
                   /      \\_j                     +
    .             Y     ( --l__
                  |            "-.                    .
                  |      (___     \\
          .       |        .)~-.__/             .            .
                  l        _)
 .                 \\      "l
     +              \\        \\
                     \\        ^.
         .            ^.       "-.            -Row         .
                        "-._      ~-.___,
                  .          "--.._____.^
    .                                             .
                        ->Moon<-
        """ + RESET;

        // FRAME 2: Stelle in posizione B (scintillio)
        String frame2 = BLUE + """
                   +                           .
           .                                             +
                                ___        *
         +                        _.--"~~ __"-.
                            ,-"     .-~  ~"-\\             +
                  +        .^       /        ( )      +
                .    {_.---._ /         ~
                    /    +  Y                             +
                   /      \\_j                     .
    +             Y     ( --l__
                  |            "-.                    +
                  |      (___     \\
          +       |        +)~-.__/             +            +
                  l        _)
 +                 \\      "l
     .              \\        \\
                     \\        ^.
         +            ^.       "-.            -Row         +
                        "-._      ~-.___,
                  +          "--.._____.^
    +                                             +
                        ->Moon<-
        """ + RESET;

        String[] frames = {frame1, frame2};

        // Thread per l'animazione delle stelle
        Thread threadAnimazione = new Thread(() -> {
            int indiceFrame = 0;

            while (inEsecuzione) {
                // Posiziona il cursore in alto a sinistra per sovrascrivere senza sfarfallii
                System.out.print("\u001B[H");

                // Stampa titolo e frame corrente
                System.out.print(titolo);
                System.out.print(frames[indiceFrame]);
                System.out.println(YELLOW + "\n[ Premere INVIO per continuare con Art1 ]" + RESET);
                System.out.flush();

                indiceFrame = (indiceFrame + 1) % frames.length;

                try {
                    Thread.sleep(450);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        });

        // Pulisce lo schermo prima di iniziare
        System.out.print("\u001B[2J\u001B[H");

        threadAnimazione.start();

        // Attesa del tasto INVIO nel thread principale
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();

        // Arresto dell'animazione
        inEsecuzione = false;
        try {
            threadAnimazione.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Pulisce lo schermo ed esegue Art1
        System.out.print("\u001B[2J\u001B[H");
        System.out.flush();

        Art.main(args);
    }
}