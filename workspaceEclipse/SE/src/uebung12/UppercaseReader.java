package uebung12;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

/**
 * Ein Decorator fÜR Reader, der aus allen gelesenen Zeichen Großbuchstaben
 * macht (auf die Weise, wie die Klasse "Character" Großbuchstaben versteht
 * sollte also im Deutschen z. B. auch für Umlaute funktionieren).
 */
public class UppercaseReader extends Reader {
    private Reader delegate;

    public static void main(String[] args) throws IOException {
        // Decorator lassen sich beliebig kombinieren; BufferedReader
        // ist nicht nur ein Decorator, sondern hat eine erweiterte
        // Schnittstelle gegenüber Reader: er kann ganze Zeilen lesen,
        // nicht nur einzelne Zeichen.
        try (BufferedReader r = new BufferedReader(
                new Rot13Reader(// Ceasar-Chiffre anwenden
                    new UppercaseReader(// Buchstaben zu Großbuchstaben umwandeln
                        new Rot13Reader(// Ceasar-Chiffre anwenden
                            new FileReader(// Zeichen aus Textdatei einlesen
                            		"C:\\Users\\user\\OneDrive\\workspaceEclipse\\SE\\src\\uebung12\\testfile.txt")))))) {

            String line = r.readLine();
            System.out.println(line);
        }
    }

    public UppercaseReader(Reader delegate) {
        this.delegate = delegate;
    }

    @Override
    public void close() throws IOException {
        delegate.close();
    }

    /**
     * @param buf der Lesepuffer
     * @param off Startposition
     * @param len maximale Anzahl zu lesender Zeichen
     * @return tatsächliche Anzahl gelesener Zeichen
     */
    public int read(char[] buf, int off, int len) throws IOException {
        int numRead = delegate.read(buf, off, len);
        if (numRead >= 0) {
            // Fallunterscheidung hier nicht wirklich notwendig, da makeUppercase
            // auch mit numRead == -1 funktionieren würde; soll hier nur verdeutlichen, dass
            // es diesen Sonderfall gibt, wenn z. B. am Dateiende gelesen werden soll.
            makeUppercase(buf, off, numRead);
        }
        return numRead;
    }

    /**
     * Wandelt Zeichen im Puffer in Großbuchstaben um.
     * 
     * @param buf der Puffer
     * @param off Startposition
     * @param len Anzahl umzuwandelnder Zeichen
     */
    private void makeUppercase(char[] buf, int off, int len) {
        for (int pos = off; pos < off + len; pos++) {
            buf[pos] = Character.toUpperCase(buf[pos]);
        }
    }
}
