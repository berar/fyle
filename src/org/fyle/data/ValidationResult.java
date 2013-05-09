
package org.fyle.data;

/**
 *
 * @author Korisnik
 */
public class ValidationResult {
    
    private boolean valid;
    private String message;
    
    private ValidationResult(){}
    
    public ValidationResult(boolean valid, String message){
        this.valid = valid;
        this.message = message;
    }
    
    public ValidationResult(boolean valid){
        this.valid = valid;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
