package org.fyle.presenter.impl;

import java.awt.event.KeyEvent;
import org.fyle.localization.Localization;
import org.fyle.localization.impl.EnglishLocalizationImpl;
import org.fyle.model.ClickListener;
import org.fyle.model.KeyPressedListener;
import org.fyle.presenter.LoginPresenter;
import org.fyle.util.ErrorChecker;
import org.fyle.util.impl.Constants;
import org.fyle.util.impl.ErrorCheckerImpl;
import org.fyle.view.LoginView;
import org.fyle.view.impl.LoginViewImpl;

public class LoginPresenterImpl implements LoginPresenter {

    LoginView lView;
    Localization localization;
    Constants.ErrorType errorType;
    ErrorChecker eci;

    public LoginPresenterImpl(LoginView lView, Localization localization) {
        this.lView = lView;
        this.localization = localization;
        eci = new ErrorCheckerImpl(localization);
        init();
    }

    private void init() {
        lView.getLoginUsername().setToolTipText(localization.getUsernameToolTip());
        lView.getLoginPassword().setToolTipText(localization.getPasswordToolTip());
        lView.subscribeOnLoginButtonClick(new ClickListener() {
            @Override
            public void actionPerformed() {
                checkLoginErrors();
            }
        });
        lView.subscribeOnLoginKeyPressed(new KeyPressedListener() {
            @Override
            public void actionPerformed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    checkLoginErrors();
                }
            }
        });
        lView.subscribeOnRegButtonClick(new ClickListener() {
            @Override
            public void actionPerformed() {
                checkRegErrors();
            }
        });
        lView.subscribeOnRegKeyPressed(new KeyPressedListener() {

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

    private void checkLoginErrors() {
        if (lView.getLoginUsername().getText().length() == 0) {
	    lView.getLoginUsername().setBalloonTip(localization.getThisFieldIsEmpty());
	    return;
	}
	if (lView.getLoginPassword().getText().length() == 0) {
	    lView.getLoginPassword().setBalloonTip(localization.getThisFieldIsEmpty());
	    return;
	}
        String regUsername = lView.getLoginUsername().getText();
        String regPassword = lView.getLoginPassword().getText();
        if(!eci.isUsernameCorrect(regUsername)) {
	    lView.getLoginUsername().setBalloonTip(eci.getErrorMessage());
	    return;
	}
	if(!eci.isPasswordCorrect(regPassword)) {
	    lView.getLoginPassword().setBalloonTip(eci.getErrorMessage());
	    return;
	}
        System.out.println("Login is valid.");
    }

    private void checkRegErrors() {
        if (lView.getRegUsername().getText().length() == 0) {
	    lView.getRegUsername().setBalloonTip(localization.getThisFieldIsEmpty());
	    return;
	}
	if (lView.getRegPass().getText().length() == 0) {
	    lView.getRegPass().setBalloonTip(localization.getThisFieldIsEmpty());
	    return;
	}
	if (lView.getRegEmail().getText().length() == 0) {
	    lView.getRegEmail().setBalloonTip(localization.getThisFieldIsEmpty());
	    return;
	}
        String regUsername = lView.getRegUsername().getText();
        String regPassword = lView.getRegPass().getText();
        String regEmail = lView.getRegEmail().getText();
        if(!eci.isUsernameCorrect(regUsername)) {
	    lView.getRegUsername().setBalloonTip(eci.getErrorMessage());
	    return;
	}
	if(!eci.isPasswordCorrect(regPassword)) {
	    lView.getRegPass().setBalloonTip(eci.getErrorMessage());
	    return;
	}
	if(!eci.isEmailCorrect(regEmail)) {
	    lView.getRegEmail().setBalloonTip(eci.getErrorMessage());
	    return;
	}
        String regRepPass = lView.getRegRepPass().getText();
        if(!regPassword.equals(regRepPass)){
            lView.getRegRepPass().setBalloonTip(localization.getPasswordsDontMatch());
            return;
        }
        System.out.println("Registration is complete.");
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
}
