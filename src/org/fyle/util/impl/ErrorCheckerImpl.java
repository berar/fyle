package org.fyle.util.impl;

import org.fyle.localization.Localization;
import org.fyle.util.ErrorChecker;

/**
 *
 * @author Korisnik
 */
public class ErrorCheckerImpl implements ErrorChecker {

    private String errorMessage;
    private Localization localization;

    public ErrorCheckerImpl(Localization localization) {
        this.localization = localization;
    }

    @Override
    public String getErrorMessage() {
        return errorMessage;
    }

    @Override
    public void setErrorMessage(String message) {
        this.errorMessage = message;
    }

    @Override
    public boolean isUsernameCorrect(String username) {
        if (!checkMin(username, 4)) {
            setErrorMessage(localization.getUsernameTooShort());
            return false;
        }
        if (!checkMax(username, 16)) {
            setErrorMessage(localization.getUsernameTooLarge());
            return false;
        }
        if (!checkFormat(username, Constants.RGX_VALID_USERNAME_FORMAT)) {
            setErrorMessage(localization.getUsernameInvalidFormat());
            return false;
        }
        return true;
    }

    @Override
    public boolean isPasswordCorrect(String password) {
        if (!checkMin(password, 8)) {
            setErrorMessage(localization.getPasswordTooShort());
            return false;
        }
        if (!checkMax(password, 128)) {
            setErrorMessage(localization.getPasswordTooLarge());
            return false;
        }
        if (!checkFormat(password, Constants.RGX_VALID_PASSWORD_FORMAT)) {
            setErrorMessage(localization.getPasswordInvalidFormat());
            return false;
        }
        return true;
    }

    @Override
    public boolean isEmailCorrect(String email) {
        if (!checkMin(email, 3)) {
            setErrorMessage(localization.getEmailTooShort());
            return false;
        }
        if (!checkMax(email, 254)) {
            setErrorMessage(localization.getEmailTooLarge());
            return false;
        }
        if (!checkFormat(email, Constants.RGX_VALID_EMAIL_FORMAT)) {
            setErrorMessage(localization.getEmailInvalidFormat());
            return false;
        }
        return true;
    }

    private boolean checkFormat(String target, String pattern) {
        if (target.matches(pattern)) {
            return true;
        } else {
            return false;
        }
    }

    private boolean checkMin(String target, int min) {
        if (target.length() >= min) {
            return true;
        } else {
            return false;
        }
    }

    private boolean checkMax(String target, int max) {
        if (target.length() <= max) {
            return true;
        } else {
            return false;
        }
    }
}
