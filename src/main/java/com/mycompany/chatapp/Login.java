/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chatapp;

/*
This class is responsible for handling:
  1. User registration validation
  2. Login authentication
  3. Returning appropriate system messages
*/

/**
 *
 * @author Keorapetse
 */

public class Login {

    private String firstName;
    private String surname;
    private String username;
    private String password;
    private String phoneNumber;

    // Constructor
    public Login(String firstName, String surname, String username, String password, String phoneNumber) {
        this.firstName = firstName;
        this.surname = surname;
        this.username = username;
        this.password = password;
        this.phoneNumber = phoneNumber;
    }

/*
The following codes includes:
  1. Username validation must contain '_' and be <= 5 characters
  2. Password complexity check at least eight characters which includes uppercase, number, special character)
  3. South African phone number validation should begin with +27 format plus nine digits
  4. Login verification verify is username and password matches with the one one the registration   
*/
    // Validating username
    public boolean checkUserName(String username) {
        return username.contains("_") && username.length() <= 5;
    }

    // Validating password complexity
    public boolean checkPasswordComplexity(String password) {

        if (password.length() < 8) {
            return false;
        }

        boolean hasUpperCase = false;
        boolean hasNumber = false;
        boolean hasSpecial = false;

        for (int i = 0; i < password.length(); i++) {
            char ch = password.charAt(i);

            if (Character.isUpperCase(ch)) {
                hasUpperCase = true;
            } else if (Character.isDigit(ch)) {
                hasNumber = true;
            } else if (!Character.isLetterOrDigit(ch)) {
                hasSpecial = true;
            }
        }

        return hasUpperCase && hasNumber && hasSpecial;
    }

    // Validating South African phone number
  /*
  The phone number validation uses a regular expression (regex) to make sure the number follows the correct South African format
    1. ^ makes sure the number starts at the beginning of the string
    2. \\+27 makes sure that the number starts with the South African international code (+27)
    3. \\d{9} makes sure 9 digits follow the country code
    4. $ makes sure the string ends after the 9 digits
  This ensures that the phone number is correctly formatted as a South African number for example +27831234567.
  */
    public boolean checkCellPhoneNumber(String phoneNumber) {
        return phoneNumber.matches("^\\+27\\d{9}$");
    }

    // Registration (returns boolean now)
    public boolean registerUser() {

        if (!checkUserName(this.username)) {
            System.out.println("Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.");
            return false;
        }

        if (!checkPasswordComplexity(this.password)) {
            System.out.println("Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.");
            return false;
        }

        if (!checkCellPhoneNumber(this.phoneNumber)) {
            System.out.println("Cell phone number incorrectly formatted or does not contain international code.");
            return false;
        }

        System.out.println("Account has been registered successfully.");
        return true;
    }

    // Login check
    public boolean loginUser(String username, String password) {
        return username.equals(this.username) && password.equals(this.password);
    }

    // Login message
    public String returnLoginStatus(boolean success) {
        if (success) {
            return "Welcome " + firstName + " " + surname + ", it is great to see you again.";
        } else {
            return "Username or password incorrect, please try again.";
        }
    }
}
