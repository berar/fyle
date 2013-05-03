
package org.nugs.clientapp.user.impl;

/**
 *
 * @author Korisnik
 */
public class Message {
    
    private static Message INSTANCE = new Message();
    private String username;
    private String message;
    
    private Message(){}
    
    public static Message getInstance(){
        return INSTANCE;
    }
    
    @Override
    public String toString(){
        return username+": "+message+"\n";
    }
    
    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }
    
    
    
}
