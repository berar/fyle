package org.fyle.validation.impl;

import org.fyle.localization.Localization;
import org.fyle.util.impl.Constants;
import org.fyle.validation.LRValidator;

/**
 *
 * @author Korisnik
 */
public class LRValidatorImpl implements LRValidator {

    private Localization localization;

    public LRValidatorImpl(Localization localization) {
        this.localization = localization;
    }

    @Override
    public ValidationResult isUsernameCorrect(String username) {
        if (!checkMin(username, 4)) {
            return new ValidationResult(false, localization.getUsernameTooShort());
        }
        if (!checkMax(username, 16)) {
            return new ValidationResult(false, localization.getUsernameTooLarge());
        }
        if (!checkFormat(username, Constants.RGX_VALID_USERNAME_FORMAT)) {
            return new ValidationResult(false, localization.getUsernameInvalidFormat());
        }
        return new ValidationResult(true);
    }

    @Override
    public ValidationResult isPasswordCorrect(String password) {
        if (!checkMin(password, 8)) {
            return new ValidationResult(false, localization.getPasswordTooShort());
        }
        if (!checkMax(password, 128)) {
            return new ValidationResult(false, localization.getPasswordTooLarge());
        }
        if (!checkFormat(password, Constants.RGX_VALID_PASSWORD_FORMAT)) {
            return new ValidationResult(false, localization.getPasswordInvalidFormat());
        }
        return new ValidationResult(true);
    }

    @Override
    public ValidationResult isEmailCorrect(String email) {
        if (!checkMin(email, 3)) {
            return new ValidationResult(false, localization.getEmailTooShort());
        }
        if (!checkMax(email, 254)) {
            return new ValidationResult(false, localization.getEmailTooLarge());
        }
        if (!checkFormat(email, Constants.RGX_VALID_EMAIL_FORMAT)) {
            return new ValidationResult(false, localization.getEmailInvalidFormat());
        }
        return new ValidationResult(true);
    }

    private boolean checkFormat(String target, String pattern) {
        return target.matches(pattern);
    }

    private boolean checkMin(String target, int min) {
        return (target.length() >= min);
    }

    private boolean checkMax(String target, int max) {
        return (target.length() <= max);
    }
}
