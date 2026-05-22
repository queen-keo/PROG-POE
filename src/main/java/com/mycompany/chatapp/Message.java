package com.mycompany.chatapp;

import java.util.Random;

public class Message {

    private static int messageCount = 0;

    private String messageID;
    private int messageNumber;
    private String recipient;
    private String messageText;
    private String messageHash;

    public Message() {

    }

    // Generate 10-digit ID
    public String generateMessageID() {

        Random random = new Random();

        long number = 1000000000L
                + (long) (random.nextDouble() * 9000000000L);

        return String.valueOf(number);
    }

    // Recipient validation
    public boolean checkRecipientCell(String number) {

        return number.matches("^\\+\\d{10,12}$");
    }

    // Message length validation
    public boolean checkMessageLength(String message) {

        return message.length() <= 250;
    }

    // Create message
    public void createMessage(String recipient,
                              String messageText) {

        messageCount++;

        this.messageNumber = messageCount;
        this.messageID = generateMessageID();
        this.recipient = recipient;
        this.messageText = messageText;

        createMessageHash();
    }

    // Message hash
    public void createMessageHash() {

        String[] words = messageText.split(" ");

        String firstWord = words[0];
        String lastWord = words[words.length - 1];

        String firstTwoDigits = messageID.substring(0, 2);

        messageHash = firstTwoDigits
                + ":"
                + messageNumber
                + ":"
                + firstWord
                + lastWord;

        messageHash = messageHash.toUpperCase();
    }

    // Display message
    public void printMessage() {

        System.out.println("\n===== MESSAGE DETAILS =====");
        System.out.println("Message ID: " + messageID);
        System.out.println("Message Hash: " + messageHash);
        System.out.println("Recipient: " + recipient);
        System.out.println("Message: " + messageText);
    }
}
