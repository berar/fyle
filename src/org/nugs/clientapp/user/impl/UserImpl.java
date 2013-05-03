package org.nugs.clientapp.user.impl;

import org.nugs.clientapp.user.User;
import org.nugs.clientapp.util.Constants;

/**
 *
 * @author Korisnik
 */
public class UserImpl implements User {
    
    private static UserImpl INSTANCE = new UserImpl();
    private String name;
    private int hostPort;
    private String hostIp;
    private boolean isConnected;
    
    private UserImpl(){
        this.name = "Guest"+Constants.RANDOM.nextInt();
        this.isConnected=false;
    }
    
    public static UserImpl getInstance(){
        return INSTANCE;
    }
    
    public String getName(){
        return INSTANCE.name;
    }
    
    public void setName(String name){
        INSTANCE.name=name;
    }

    /**
     * @return the hostPort
     */
    public int getHostPort() {
        return hostPort;
    }

    /**
     * @param hostPort the hostPort to set
     */
    public void setHostPort(int hostPort) {
        this.hostPort = hostPort;
    }

    /**
     * @return the hostIp
     */
    public String getHostIp() {
        return hostIp;
    }

    /**
     * @param hostIp the hostIp to set
     */
    public void setHostIp(String hostIp) {
        this.hostIp = hostIp;
    }

    /**
     * @return the isConnected
     */
    public boolean isIsConnected() {
        return isConnected;
    }

    /**
     * @param isConnected the isConnected to set
     */
    public void setIsConnected(boolean isConnected) {
        this.isConnected = isConnected;
    }
    
    
}
