
package org.fyle.util;

import org.fyle.data.ValidationResult;

/**
 *
 * @author Korisnik
 */
public interface ErrorChecker {
    
    ValidationResult isUsernameCorrect(String username);
    ValidationResult isPasswordCorrect(String password);
    ValidationResult isEmailCorrect(String email);
    
}
