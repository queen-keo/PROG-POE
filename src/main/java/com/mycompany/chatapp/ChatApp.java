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

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

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
            System.out.print("Enter South African Cell Phone Number (+27...): ");
            phoneNumber = input.next();

            if (temp.checkCellPhoneNumber(phoneNumber)) {
                System.out.println("Cell phone number successfully added");
                break;
            } else {
                System.out.println("Cell phone number incorrectly formatted or does not contain international code.");
            }
        }

        // Creating a final account for Login
        Login account = new Login(firstName, surname, username, password, phoneNumber);

        // Register
        boolean isRegistered = account.registerUser();

        // Login if only the user was able to register successfully
        if (isRegistered) {

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

            System.out.println(account.returnLoginStatus(true));
        }

        input.close();
    }
}