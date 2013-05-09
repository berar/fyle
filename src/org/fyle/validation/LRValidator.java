
package org.fyle.validation;

import org.fyle.validation.impl.ValidationResult;

/**
 *
 * @author Korisnik
 */
public interface LRValidator {
    
    ValidationResult isUsernameCorrect(String username);
    ValidationResult isPasswordCorrect(String password);
    ValidationResult isEmailCorrect(String email);
    
}
