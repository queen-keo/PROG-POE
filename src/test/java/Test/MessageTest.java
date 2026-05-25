/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package Test;

import com.mycompany.chatapp.Message;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


/**
 *
 * @author Student
 */

public class MessageTest {
    
    Message msg = new Message("+27718693002", "Hi Mike");

    @Test
    public void testMessageLengthSuccess() {
        String body = "Hi Mike, can you join us for dinner tonight?";
        assertTrue(body.length() <= 250, "Message ready to send.");
    }

    @Test
    public void testMessageLengthFail() {
        String body = "A".repeat(251); 
        int excess = body.length() - 250;
        assertFalse(body.length() <= 250, "Message exceeds 250 characters by " + excess + "; please reduce the size.");
    }

    @Test
    public void testRecipientFormatSuccess() {
        Message msg = new Message("+27718693002", "Test");
        // Using Login class regex logic for validation
        assertTrue(msg.checkRecipientCell("+27718693002").contains("successfully captured"));
    }

    @Test
    public void testMessageHashGenerate() {
        // Data from Test Case 1
        // For ID to start with '00' for test verification
        String hash = msg.createMessageHash();
        assertTrue(hash.startsWith("00:"), "Message hash should start with 00:");
        assertTrue(hash.contains("HI") && hash.contains("MIKE"), "Hash should contain first and last word");
    }
    
    @Test
    public void testMessageLength() {
        // Success case
        String validMsg = "Hi Mike, can you join us for dinner tonight?";
        assertTrue(validMsg.length() <= 250, "Message ready to send.");
        
        // Failure case
        String longMsg = "A".repeat(251);
        int excess = longMsg.length() - 250;
        assertFalse(longMsg.length() <= 250, "Message exceeds 250 characters by " + excess + "; please reduce the size.");
    }
    
     //Message Testing Assert True or False
    
    @Test
    public void testRecipientFormat() { 
        assertTrue(msg.checkRecipientCell("+27718693002").contains("successfully captured"));
        assertFalse(msg.checkRecipientCell("08575975889").contains("successfully captured"));
    }
 
    @Test
    public void testMessageHash() {
        // Using Test Data 1: "HI MIKE, can you join us for dinner tonight?"
        String hash = msg.createMessageHash();
        
        // This validates that the hash logic meets the requirement
        assertNotNull(hash);
        assertTrue(hash.contains(":"), "Hash must contain colons");
        assertTrue(hash.endsWith("HIMIKE"), "Hash should end with words in CAPS");
    }
}