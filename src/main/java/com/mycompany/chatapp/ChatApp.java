/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.chatapp;

import java.util.Scanner;

/*
This class contains the main method and a foundation for controls which are:
 1. User input
 2. Validation loops for valid input
 3. Account creation
 4. Login process - Allows login only after successful registration
 */

/**
 *
 * @author Keorapetse
 */

public class ChatApp {

    static Scanner input = new Scanner(System.in);
    static Login account = null; 
    static boolean exitProgram = false; 

    public static void main(String[] args) {
        int choice;

/*
The menu keeps repeating and it stops only when:
	When the user chooses to exit or
	Login was successful
*/
        do {
            System.out.println("\n********** HELLO!, WELCOME TO QUICK APP **********");
            System.out.println("\n********** USER ACCOUNT MENU **********");
            System.out.println("1. Create A New Account");
            System.out.println("2. Login to your Account");
            System.out.println("3. Quit Application");
            System.out.print("Enter your choice: ");

            choice = input.nextInt();
            input.nextLine();

/*
Switch statement to control users choice
 */

            switch (choice) {
                case 1:
                    registerUser(); // This calls registration method
                    break;
                case 2:
                    loginUser(); // This calls login method
                    break;
                case 3:
                    System.out.println("Exiting..."); // Stops the program
                    exitProgram = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }

        } while (!exitProgram); 

        input.close();
    }

/*
This method handles the users registration
And it collects users details and validates them.
 */

    static void registerUser() {
        System.out.println("\n===== CREATE AN ACCOUNT =====");

// User details
        System.out.print("Enter First Name: ");
        String firstName = input.next();

        System.out.print("Enter Surname: ");
        String surname = input.next();

// A Temporary object for validation
        Login temp = new Login(firstName, surname, "", "", "");

/*
 * Created a Username Validation Loop
 * Repeats until user enters a valid username
 */
        String username;
        while (true) {
            System.out.print("Enter Username: ");
            username = input.next();

            if (temp.checkUserName(username)) {
                System.out.println("Username successfully captured");
                break;
            } else {
                System.out.println("Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.");
            }
        }

/*
 * Created a Password Validation Loop
 * Repeats until user enters a valid password that meets the requirement
 */
        String password;
        while (true) {
            System.out.print("Enter Password: ");
            password = input.next();

            if (temp.checkPasswordComplexity(password)) {
                System.out.println("Password successfully captured");
                break;
            } else {
                System.out.println("Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.");
            }
        }

 // Phone number Loop
        String phoneNumber;
        while (true) {
            System.out.print("Enter Cell Number (+27...): ");
            phoneNumber = input.next();

            if (temp.checkCellPhoneNumber(phoneNumber)) {
                System.out.println("Cell phone number successfully added");
                break;
            } else {
                System.out.println("Cell phone number incorrectly formatted or does not contain international code.");
            }
        }

 // Creating a final account for Login
        account = new Login(firstName, surname, username, password, phoneNumber);
        account.registerUser();
        
    }

// Login if only the user was able to register successfully
    static void loginUser() {
        if (account == null) {
            System.out.println("No account registered yet. Please register first.");
            return; 
        }

        System.out.println("\n===== LOGIN =====");
        boolean success = false;

        while (!success) {
            System.out.print("Enter Username: ");
            String loginUsername = input.next();

            System.out.print("Enter Password: ");
            String loginPassword = input.next();

            success = account.loginUser(loginUsername, loginPassword);

            if (!success) {
                System.out.println("Username or password incorrect, please try again.");
            }
        }

        System.out.println(account.returnLoginStatus(success));
        
         boolean quickChat = true;
        while (quickChat) {
            System.out.println("\n--- Welcome to QuickChat ---");
            System.out.println("1) Send Messages");
            System.out.println("2) Show recently sent messages");
            System.out.println("3) Quit");
            System.out.print("Enter your choice: ");
            int menuChoice = input.nextInt();
            input.nextLine();

            switch (menuChoice) {
                case 1: startQuickChat(); break;
                case 2: System.out.println("Coming Soon."); break;
                case 3: quickChat = false; exitProgram = true; break;
            }
        }
        
    }

    static void startQuickChat() {
        System.out.print("\nHow many messages do you wish to enter? ");
        int numMessages = input.nextInt();
        input.nextLine();

        for (int i = 0; i < numMessages; i++) {
            System.out.print("Enter recipient cell: ");
            String recipient = input.nextLine();
            System.out.print("Enter message (max 250 chars): ");
            String body = input.nextLine();

            Message msg = new Message(recipient, body);
            
            System.out.println(msg.checkMessageLength(body));

            System.out.println("\nChoose an option:");
            System.out.println("1. Send Message");
            System.out.println("2. Disregard Message (Press 0 to delete)");
            System.out.println("3. Store Message");
            System.out.print("Enter choice: ");
            int choice = input.nextInt();
            input.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("Message successfully sent.");
                    System.out.println(msg.printMessages());
                    break;
                case 2:
                    System.out.println("Press 0 to delete the message.");
                    break;
                case 3:
                    msg.storeMessage();
                    System.out.println("Message successfully stored.");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
        System.out.println("Total messages sent: " + Message.getTotalMessages());
        exitProgram = true; // This will exit the program completely after its done
    }
}  
