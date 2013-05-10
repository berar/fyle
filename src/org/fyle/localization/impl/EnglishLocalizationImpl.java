package org.fyle.localization.impl;

import org.fyle.localization.Localization;

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

    @Override
    public String getUsernameToolTip() {
        return "Min. 4, Max. 16, a-z, A-Z, 0-9";
    }

    @Override
    public String getPasswordToolTip() {
        return "Must have at least one lower, upper case chars and number";
    }

    @Override
    public String getThisFieldIsEmpty() {
        return "This field is empty.";
    }

    @Override
    public String getUsernameTooShort() {
        return "Username min. length is 4.";
    }

    @Override
    public String getUsernameTooLarge() {
        return "Username max. length is 16";
    }

    @Override
    public String getUsernameInvalidFormat() {
        return "Username can only a-z, A-Z, 0-9, dashes or dot.";
    }

    @Override
    public String getPasswordTooShort() {
        return "Password min. length is 8.";
    }

    @Override
    public String getPasswordTooLarge() {
        return "Password max. length is 128.";
    }

    @Override
    public String getPasswordInvalidFormat() {
        return "At least one A-Z, a-z, 0-9 and no spaces.";
    }

    @Override
    public String getEmailTooShort() {
        return "Email is too short.";
    }

    @Override
    public String getEmailTooLarge() {
        return "Email is too large.";
    }

    @Override
    public String getEmailInvalidFormat() {
        return "Invalid email format.";
    }

    @Override
    public String getPasswordsDontMatch() {
        return "Passwords don't match.";
    }
}
