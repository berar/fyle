
package org.fyle.validation.impl;

/**
 *
 * @author Korisnik
 */
public class ValidationResult {
    
    private final boolean valid;
    private final String message;
    
    private ValidationResult(){
    	throw new AssertionError();
    }
    
    public ValidationResult(boolean valid, String message){
        this.valid = valid;
        this.message = message;
    }
    
    public ValidationResult(boolean valid){
        this.valid = valid;
        this.message = "default";
    }

    public boolean isValid() {
        return valid;
    }

    public String getMessage() {
        return message;
    }
}
