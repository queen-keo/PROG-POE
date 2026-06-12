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
    public void testMessageHashGenerateP2() {
        // Data from Test Case 1
        // For ID to start with '00' for test verification
        String hash = msg.createMessageHash();
        assertTrue(hash.startsWith("00:"), "Message hash should start with 00:");
        assertTrue(hash.contains("HI") && hash.contains("MIKE"), "Hash should contain first and last word");
    }
    
    @Test
    public void testMessageHashGenerate() {

        String hash = msg.createMessageHash();
        assertNotNull(hash);
        assertTrue(hash.contains(":"));
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
    public void testMessageHashP2() {
        // Using Test Data 1: "HI MIKE, can you join us for dinner tonight?"
        String hash = msg.createMessageHash();
        
        // This validates that the hash logic meets the requirement
        assertNotNull(hash);
        assertTrue(hash.contains(":"), "Hash must contain colons");
        assertTrue(hash.endsWith("HIMIKE"), "Hash should end with words in CAPS");
    }
    
    @Test
    public void testMessageHash() {

        String hash = msg.createMessageHash();
        assertNotNull(hash);
        assertTrue(hash.contains(":"));
        assertTrue(hash.endsWith("HIMIKE"));
}
    
    @Test
    public void testStoredMessagesArray() {

        Message msg1 = new Message(
            "+27838884567",
            "Where are you? You are late! I have asked you to be on time."
    );

        Message.addStoredMessage(msg1);
        String result =
            Message.searchMessagesByRecipient(
                    "+27838884567"
            );
        assertTrue(
            result.contains(
                    "Where are you? You are late! I have asked you to be on time."
            )
    );
}

    @Test
    public void testLongestMessage() {

        Message msg1 = new Message(
            "+27838884567",
            "Hi"
    );

        Message msg2 = new Message(
            "+27838884567",
            "Where are you? You are late! I have asked you to be on time."
    );

        Message.addStoredMessage(msg1);
        Message.addStoredMessage(msg2);
        assertEquals(
            "Where are you? You are late! I have asked you to be on time.",
            Message.displayLongestMessage()
    );
}

    @Test
    public void testSearchByMessageID() {

        Message msg = new Message(
            "+27838884567",
            "Testing message ID"
    );

        Message.addStoredMessage(msg);
        String result =
            Message.searchMessageByID(
                    msg.getMessageID()
            );
        assertTrue(
            result.contains(
                    "Testing message ID"
            )
    );
}

    @Test
    public void testDeleteMessageByHash() {

        Message msg = new Message(
            "+27838884567",
            "Delete me"
    );

        Message.addStoredMessage(msg);
        String result =
            Message.deleteMessageByHash(
                    msg.getMessageHash()
            );
        assertTrue(
            result.contains(
                    "successfully deleted"
            )
    );
}

    @Test
    public void testDisplayReport() {

        Message msg = new Message(
            "+27838884567",
            "Report Message"
    );

        Message.addStoredMessage(msg);
        assertDoesNotThrow(() -> {
        Message.displayReport();
    });
}
}
