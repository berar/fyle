package org.fyle.localization;

/**
 *
 * @author berar
 */
public interface Localization {
    String getInvalidUsername();
    String getAlreadyConnected();
    String getDisconnectedSuccess();
    String getNotConnected();
    String getYourNewName();
    String getInvalidOption();
    //login view tooltips
    String getUsernameToolTip();
    String getPasswordToolTip();
    //login view errors
    String getThisFieldIsEmpty();
    String getUsernameTooShort();
    String getUsernameTooLarge();
    String getUsernameInvalidFormat();
    String getPasswordTooShort();
    String getPasswordTooLarge();
    String getPasswordInvalidFormat();
    String getPasswordsDontMatch();
    String getEmailTooShort();
    String getEmailTooLarge();
    String getEmailInvalidFormat();
    //login status label
    String getLRStatusLabelNotConnected();
    String getLRStatusLabelConnecting();
    String getLRStatusLabelConnectExcRefused();
    String getLRStatusLabelConnectExcTimeout();
    String getLRStatusLabelConnectExcOtherIO();
    String getLRStatusLabelConnected();
    
    String getSendFileButtonTooltip();
    String getSendDirButtonTooltip();
}
