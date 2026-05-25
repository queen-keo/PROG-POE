/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chatapp;

/**
 *
 * @author Keorapetse
 */


import java.util.Random;
import java.io.FileWriter;
import java.io.IOException;

public class Message {
    private static int totalMessagesSent = 0;
    private final String messageID;
    private final String recipient;
    private final String message;
    private final String messageHash;

    public Message(String recipient, String message) {
        this.messageID = generateID();
        this.recipient = recipient;
        this.message = message;
        totalMessagesSent++;
        this.messageHash = createMessageHash();
    }

    private String generateID() {
        Random rand = new Random();
        return String.format("%010d", rand.nextInt(1000000000));
    }

    public boolean checkMessageID(String id) {
        return id != null && id.length() <= 10;
    }

    public String checkRecipientCell(String cell) {
        // Validation: Must start with +27 followed by 9 digits
        if (cell.matches("^\\+27\\d{9}$")) {
            return "Cell phone number successfully captured.";
        } else {
            return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
        }
    }

    public String checkMessageLength(String msg) {
        if (msg.length() <= 250) {
            return "Message ready to send.";
        } else {
            return "Message exceeds 250 characters by " + (msg.length() - 250) + "; please reduce the size.";
        }
    }

    public String createMessageHash() {
        String[] words = message.split(" ");
        String first = words[0].toUpperCase();
        String last = words[words.length - 1].toUpperCase();
        return messageID.substring(0, 2) + ":" + (totalMessagesSent - 1) + ":" + first + last;
    }

    public void storeMessage() {
    String json = String.format("{\"ID\":\"%s\", \"Hash\":\"%s\", \"Recipient\":\"%s\"}%n", 
                                messageID, messageHash, recipient);
    
    try (FileWriter fw = new FileWriter("messages.json", true)) {
        fw.write(json);
        System.out.println("DEBUG: JSON write successful!"); // Add this to see if it triggers
    } catch (IOException e) {
        System.out.println("Error storing message: " + e.getMessage());
    }
}

    public String printMessages() {
        return "\nMessage ID: " + messageID + "\nMessage Hash: " + messageHash + 
               "\nRecipient: " + recipient + "\nMessage: " + message;
    }

    public static int getTotalMessages() {
        return totalMessagesSent;
    }
}
