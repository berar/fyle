package org.fyle.util.impl;

import org.fyle.util.authenticate.impl.*;
import org.fyle.localization.Localization;
import org.fyle.presenter.LoginPresenter;
import org.fyle.util.authenticate.AuthenticityState;
import org.fyle.util.ErrorChecker;

public class ErrorCheckerImpl implements ErrorChecker {

    private String username;
    private String password;
    private LoginPresenter lp;
    private Localization localization;
    private String errorMessage;
    private String email;
    private static String[] usernameErrorMessages;
    private static String[] passwordErrorMessages;
    private static String[] emailErrorMessages;

    private ErrorCheckerImpl() {
    }
    
    public ErrorCheckerImpl(String username, String password, String email, LoginPresenter lp, Localization localization) {
        this.username = username;
        this.password = password;
        this.lp = lp;
        this.localization = localization;
        this.email = email;
        ErrorCheckerImpl.usernameErrorMessages = new String[]{localization.getUsernameTooShort(),
            localization.getUsernameTooLarge(), localization.getUsernameInvalidFormat()};
        ErrorCheckerImpl.passwordErrorMessages = new String[]{localization.getPasswordTooShort(),
            localization.getPasswordTooLarge(), localization.getPasswordInvalidFormat()};
        ErrorCheckerImpl.emailErrorMessages = new String[]{localization.getEmailTooShort(),
        localization.getEmailTooLarge(), localization.getEmailInvalidFormat()};
    }
    
    public ErrorCheckerImpl(String username, String password, LoginPresenter lp, Localization localization) {
        this.username = username;
        this.password = password;
        this.lp = lp;
        this.localization = localization;
        ErrorCheckerImpl.usernameErrorMessages = new String[]{localization.getUsernameTooShort(),
            localization.getUsernameTooLarge(), localization.getUsernameInvalidFormat()};
        ErrorCheckerImpl.passwordErrorMessages = new String[]{localization.getPasswordTooShort(),
            localization.getPasswordTooLarge(), localization.getPasswordInvalidFormat()};
    }

    @Override
    public AuthenticityState checkErrors(Constants.ErrorType errorType) {
        boolean valid = check(username, 4, 16, Constants.RGX_VALID_USERNAME_FORMAT, usernameErrorMessages);
        if (valid) {
            valid = check(password, 8, 128, Constants.RGX_VALID_PASSWORD_FORMAT, passwordErrorMessages);
            if (valid) {
                if(errorType == Constants.ErrorType.REGISTER){
                    valid = check(email, 3, 254, Constants.RGX_VALID_EMAIL_FORMAT, emailErrorMessages);
                    if(valid){
                        return new AuthenticateNullImpl();
                    } else {
                        return new AuthenticateRegisterEmailImpl(lp, errorMessage);
                    }
                } else if (errorType == Constants.ErrorType.LOGIN){
                    return new AuthenticateNullImpl();
                }else {
                    throw new UnsupportedOperationException();
                }
            } else {
                if (errorType == Constants.ErrorType.LOGIN) {
                    return new AuthenticateLoginPasswordImpl(lp, errorMessage);
                } else if (errorType == Constants.ErrorType.REGISTER) {
                    return new AuthenticateRegisterPasswordImpl(lp, errorMessage);
                } else {
                    throw new UnsupportedOperationException();
                }
            }
        } else {
            if (errorType == Constants.ErrorType.LOGIN) {
                return new AuthenticateLoginUsernameImpl(lp, errorMessage);
            } else if (errorType == Constants.ErrorType.REGISTER) {
                return new AuthenticateRegisterUsernameImpl(lp, errorMessage);
            } else {
                throw new UnsupportedOperationException();
            }
        }
    }

    @Override
    public boolean check(String target, int min, int max, String pattern, String[] messages) {
        boolean valid = checkMin(target, min);
        if (valid) {
            valid = checkMax(target, max);
            if (valid) {
                valid = checkFormat(target, pattern);
                if (valid) {
                    return true;
                } else {
                    errorMessage = messages[2];
                    return false;
                }
            } else {
                errorMessage = messages[1];
                return false;
            }
        } else {
            errorMessage = messages[0];
            return false;
        }

    }

    @Override
    public boolean checkFormat(String target, String pattern) {
        if (target.matches(pattern)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean checkMin(String target, int min) {
        if (target.length() >= min) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean checkMax(String target, int max) {
        if (target.length() <= max) {
            return true;
        } else {
            return false;
        }
    }
}
