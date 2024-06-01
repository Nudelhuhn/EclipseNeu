package uebung12;

import java.io.IOException;
import java.io.Reader;

/**
 * Ein Decorator für Reader, der Zeichen zwischen "a" und "z" bzw. "A" und "Z" 
 * um jeweils 13 Zeichen verschiebt (Cäsar-Chiffre mit Versatz 13).
 */
public class Rot13Reader extends Reader {
    private static final int CAESAR_OFFSET = 13;
    private static final int NUM_LETTERS = 26;
    
    private Reader delegate;
    
    public Rot13Reader(Reader delegate) {
        this.delegate = delegate;
    }

    @Override
    public void close() throws IOException {
        delegate.close();
    }

    @Override
    public int read(char[] buf, int off, int len) throws IOException {
        int numRead = delegate.read(buf, off, len);
        rot13(buf, off, numRead);
        return numRead;
    }

    /**
     * Verschiebt lateinische Buchstaben im Puffer um 13 Zeichen.
     * 
     * @param buf Puffer
     * @param off Startposition
     * @param len Anzahl Zeichen
     */
    private void rot13(char[] buf, int off, int len) {
        for (int pos = off; pos < off + len; pos++) {
            buf[pos] = rot13(buf[pos]);
        }
    }

    /**
     * @param c Zeichen
     * @return Zeichen um 13 rotiert, wenn es ein lateinischer Buchstabe ist, sonst das Eingabezeichen
     */
    private char rot13(char c) {
        if ('a' <= c && c <= 'z') {
            return (char) ('a' + (((c - 'a') + CAESAR_OFFSET) % NUM_LETTERS));
            
        }
        if ('A' <= c && c <= 'Z') {
            return (char) ('A' + (((c - 'A') + CAESAR_OFFSET) % NUM_LETTERS));            
        }
        return c;
    }
}
