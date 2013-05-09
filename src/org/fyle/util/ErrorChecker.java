
package org.fyle.util;

/**
 *
 * @author Korisnik
 */
public interface ErrorChecker {
    
    void setErrorMessage(String message);
    String getErrorMessage();
    boolean isUsernameCorrect(String username);
    boolean isPasswordCorrect(String password);
    boolean isEmailCorrect(String email);
    
}
