package org.nugs.clientapp.localization.impl;

import org.nugs.clientapp.localization.Localization;

/**
 *
 * @author Korisnik
 */
public class EnglishLocalizationImpl implements Localization{

    @Override
    public String getInvalidUsername() {
        return "Invalid name.";
    }
    
    @Override
    public String getAlreadyConnected(){
        return "You are already connected. Type /quit to disconnect.";
    }

    @Override
    public String getDisconnectedSuccess() {
        return "You are now disconnected successfully.";
    }

    @Override
    public String getNotConnected() {
        return "You are not connected to any server.";
    }

    @Override
    public String getYourNewName() {
        return "Your name is now: ";
    }

    @Override
    public String getInvalidOption() {
        return "Invalid option. Use /options to see all options.";
    }
}
