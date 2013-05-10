
package org.fyle.validation.impl;

/**
 * An instance of this object indicates result of login or register validator methods
 * (LRValidator.java in org.fyle.validation.impl).
 * 
 * @author Korisnik
 */
public class ValidationResult {
	
    private final boolean valid;
    private final String message;
    
    /**
     * This constructor is used when all of the conditions of validation 
     * in LRValidator are met.
     * 
     */
    public ValidationResult(){
    	this.valid = true;
    	this.message = null;
    }
    
    /**
     * This constructor is used when some condition of validation in LRValidator
     * isn't met.
     * 
     * @param message set proper Error message.
     */
    public ValidationResult(String message){
        this.valid = false;
        this.message = message;
    }
    
    /**
     * Indicates whether validation result is valid or not.
	 *
     * @return True if validation result is valid, otherwise false.
     */
    public boolean isValid() {
        return valid;
    }
    
    /**
     * 
     * @return Error message if validation result is invalid, otherwise it's null.
     */
    public String getMessage() {
        return message;
    }
}
