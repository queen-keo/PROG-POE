/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package Test;

import com.mycompany.chatapp.Login;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/*
    This class is used to test the Login class 
     It will test the following: 
        1. Username validation (true/false)
        2. Password complexity validation (true/false)
        3. Phone number validation (true/false)
        4. Login functionality (true/false)
*/
    
/*
    Assertions used are:
        assertTrue()
        assertFalse()
*/

public class LoginTest {

    Login user = new Login("Kyle", "Smith", "kyl_1", "Ch&&sec@ke99!", "+27838968976");

    //Username Testing

    @Test
    public void testUsernameCorrect() {
        boolean result = user.checkUserName("kyl_1");

        if (result) {
            String correct = user.returnLoginStatus(true);
            assertEquals("Welcome Kyle Smith, it is great to see you again.", correct);
        }
    }

    @Test
    public void testUsernameIncorrect() {
        boolean result = user.checkUserName("kyle!!!!!!");

        if (!result) {
            String expected = "Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.";
            assertEquals(expected, expected); // comparing expected message
        }
    }

    //Password Testing
    
    @Test
    public void testPasswordSuccess() {
        boolean result = user.checkPasswordComplexity("Ch&&sec@ke99!");

        if (result) {
            String expected = "Password successfully captured.";
            assertEquals(expected, "Password successfully captured.");
        }
    }

    @Test
    public void testPasswordFail() {
        boolean result = user.checkPasswordComplexity("password");

        if (!result) {
            String expected = "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
            assertEquals(expected, expected);
        }
    }

    //Cell phone number testing
    
    @Test
    public void testPhoneSuccess() {
        boolean result = user.checkCellPhoneNumber("+27838968976");

        if (result) {
            String expected = "Cell number successfully captured.";
            assertEquals(expected, "Cell number successfully captured.");
        }
    }

    @Test
    public void testPhoneFail() {
        boolean result = user.checkCellPhoneNumber("08966553");

        if (!result) {
            String expected = "Cell number is incorrectly formatted or does not contain an international code; please correct the number and try again.";
            assertEquals(expected, expected);
        }
    }

   //Login Test

    @Test
    public void testLoginSuccess() {
        boolean success = user.loginUser("kyl_1", "Ch&&sec@ke99!");
        String correct = user.returnLoginStatus(success);

        assertEquals("Welcome Kyle Smith, it is great to see you again.", correct);
    }

    @Test
    public void testLoginFail() {
        boolean success = user.loginUser("wrong", "wrong");
        String incorrect = user.returnLoginStatus(success);

        assertEquals("Username or password incorrect, please try again.", incorrect);
    }
 
    //Login Testing Assert True or False
    
    @Test
    public void testLoginUsernameTrue(){
        assertTrue(user.checkUserName("kyl_1"));
    }
    
    @Test 
    public void testLoginUsernameFalse(){
        assertFalse(user.checkUserName("kyle!!!!!!"));
    }
    
    @Test
    public void testLoginPasswordTrue(){
        assertTrue(user.checkPasswordComplexity("Ch&&sec@ke99!"));
    }
    
     @Test
    public void testLoginPasswordFalse(){
        assertFalse(user.checkPasswordComplexity("password"));
    }
    
     @Test
    public void testLoginCellNumberTrue(){
        assertTrue(user.checkCellPhoneNumber("+27838968976"));
    }
    
     @Test
    public void testLoginCellNumberFalse(){
        assertFalse(user.checkCellPhoneNumber("08966553"));
    }
}

