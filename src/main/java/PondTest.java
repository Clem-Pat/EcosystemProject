import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;


class PondTest {
    @org.junit.jupiter.api.Test
    void testMain() {
        // Sauvegarde la sortie normale pour la restaurer après le test
        PrintStream originalOut = System.out;
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));


        FrogPond.main(null);

        // Restaure la sortie normale
        System.setOut(originalOut);

        // Vérifie la sortie attendue
        String expectedOutput = "Un étang avec la grenouille Noe la grenouille Benj la mouche Baptiste\n" +
                "the fly Baptiste is eaten by Noe\n";
        assertEquals(expectedOutput, outputStreamCaptor.toString());
    }
}
