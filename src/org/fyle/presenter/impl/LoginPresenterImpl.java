package org.fyle.presenter.impl;

import java.awt.event.KeyEvent;
import org.fyle.localization.Localization;
import org.fyle.localization.impl.EnglishLocalizationImpl;
import org.fyle.model.ActionListeners;
import org.fyle.presenter.LoginPresenter;
import org.fyle.util.authenticate.AuthenticityState;
import org.fyle.util.ErrorChecker;
import org.fyle.util.impl.Constants;
import org.fyle.util.impl.ErrorCheckerImpl;
import org.fyle.view.LoginView;
import org.fyle.view.impl.LoginViewImpl;

public class LoginPresenterImpl implements LoginPresenter {

    LoginView lView;
    Localization localization;
    ErrorChecker leci;
    AuthenticityState as;
    Constants.ErrorType errorType;

    public LoginPresenterImpl(LoginView lView, Localization localization) {
        this.lView = lView;
        this.localization = localization;
        init();
    }

    private void init() {
        lView.getLoginUsername().setToolTipText(localization.getUsernameToolTip());
        lView.getLoginPassword().setToolTipText(localization.getPasswordToolTip());
        lView.subscribeOnLoginButtonClick(new ActionListeners() {
            @Override
            public void actionPerformed() {
                checkLoginErrors();
            }

            @Override
            public void actionPerformed(KeyEvent e) {
            }
        });
        lView.subscribeOnLoginKeyPressed(new ActionListeners() {
            @Override
            public void actionPerformed() {
            }

            @Override
            public void actionPerformed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    checkLoginErrors();
                }
            }
        });
        lView.subscribeOnRegButtonClick(new ActionListeners() {
            @Override
            public void actionPerformed() {
                checkRegErrors();
            }

            @Override
            public void actionPerformed(KeyEvent e) {
            }
        });
        lView.subscribeOnRegKeyPressed(new ActionListeners() {
            @Override
            public void actionPerformed() {
            }

            @Override
            public void actionPerformed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    checkRegErrors();
                }
            }
        });
    }

    @Override
    public void start() {
        lView.show();
    }

    @Override
    public void checkLoginErrors() {
        lView.closeBalloonTip();
        if (lView.getLoginUsername().getText().length() > 0) {
            if (lView.getLoginPassword().getPassword().length > 0) {
                String logUsername = lView.getLoginUsername().getText();
                String logPassword = new String(lView.getLoginPassword().getPassword());
                leci = new ErrorCheckerImpl(logUsername, logPassword, this, localization);
                as = leci.checkErrors(Constants.ErrorType.LOGIN);
                as.execute();
            } else {
                lView.displayError(lView.getLoginPassword(), localization.getThisFieldIsEmpty());
            }
        } else {
            lView.displayError(lView.getLoginUsername(), localization.getThisFieldIsEmpty());
        }
    }

    @Override
    public void checkRegErrors() {
        lView.closeBalloonTip();
        if (lView.getRegUsername().getText().length() > 0) {
            if (lView.getRegPass().getPassword().length > 0) {
                if (lView.getRegEmail().getText().length() > 0) {
                    String regUsername = lView.getRegUsername().getText();
                    String regPassword = new String(lView.getRegPass().getPassword());
                    String regEmail = lView.getRegEmail().getText();
                    leci = new ErrorCheckerImpl(regUsername, regPassword, regEmail, this, localization);
                    as = leci.checkErrors(Constants.ErrorType.REGISTER);
                    as.execute();
                } else {
                    lView.displayError(lView.getRegEmail(), localization.getThisFieldIsEmpty());
                }
            } else {
                lView.displayError(lView.getRegPass(), localization.getThisFieldIsEmpty());
            }
        } else {
            lView.displayError(lView.getRegUsername(), localization.getThisFieldIsEmpty());
        }
    }

    @Override
    public void sendLoginUsernameError(String message) {
        lView.displayLoginUsernameError(message);
    }

    @Override
    public void sendLoginPasswordError(String message) {
        lView.displayLoginPasswordError(message);
    }

    public static void main(String[] args) {
        final LoginPresenter lp = new LoginPresenterImpl(new LoginViewImpl(), new EnglishLocalizationImpl());
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                lp.start();
            }
        });
    }

    @Override
    public void sendRegisterUsernameError(String message) {
        lView.displayRegisterUsernameError(message);
    }

    @Override
    public void sendRegisterPasswordError(String message) {
        lView.displayRegisterPasswordError(message);
    }

    @Override
    public void sendRegisterEmailError(String message) {
        lView.displayRegisterEmailError(message);
    }
}
