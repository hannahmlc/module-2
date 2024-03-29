package ss.week6.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ss.week6.dictionaryattack.DictionaryAttack;

/**
 * Test program for DictionaryAttack.
 * Lab Exercise SoftwareSystems
 * @author Jip Spel, Wim Kamerman
 * @version $Revision: 1.0 $
 */
public class DictionaryAttackTest {

    /** Test variable for a <tt>DictionaryAttack</tt> object. */
    private DictionaryAttack dictionaryAttack;

    /** Path to the text file */
    private static final String PATH = "./src/ss/week6/test/"; // Path to the test folder

    @BeforeEach
    public void setUp() {
        dictionaryAttack = new DictionaryAttack();
        try {
            dictionaryAttack.readPasswords(PATH + "LeakedPasswords.txt");
        } catch (IOException e) {
            System.err.println("Unable to read password file.");
            e.printStackTrace();
        }
    }

    /**
     * Test for <tt>getPasswordHash</tt>
     */
    @Test
    public void testGetPasswordHash() {
        assertEquals("5f4dcc3b5aa765d61d8327deb882cf99", dictionaryAttack.getPasswordHash("password"));
    }

    /**
     * Test for <tt>checkPassword</tt>
     */
    @Test
    public void testCheckPassword() {
        assertTrue(dictionaryAttack.checkPassword("katrine", "spongebob"));
    }

}
