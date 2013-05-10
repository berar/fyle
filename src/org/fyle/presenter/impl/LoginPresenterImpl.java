package org.fyle.presenter.impl;

import java.awt.event.KeyEvent;
import org.fyle.localization.Localization;
import org.fyle.localization.impl.EnglishLocalizationImpl;
import org.fyle.model.ClickListener;
import org.fyle.model.KeyPressedListener;
import org.fyle.validation.LRValidator;
import org.fyle.validation.impl.LRValidatorImpl;
import org.fyle.validation.impl.ValidationResult;
import org.fyle.view.LoginView;
import org.fyle.view.impl.LoginViewImpl;

public class LoginPresenterImpl {

    LoginView lView;
    Localization localization;
    LRValidator eci;

    public LoginPresenterImpl(LoginView lView, Localization localization, LRValidator eci) {
        this.lView = lView;
        this.localization = localization;
        this.eci = eci;
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
        ValidationResult vr;
        vr = eci.isUsernameCorrect(regUsername);
        if (!vr.isValid()) {
            lView.getLoginUsername().setBalloonTip(vr.getMessage());
            return;
        }
        vr = eci.isPasswordCorrect(regPassword);
        if (!vr.isValid()) {
            lView.getLoginPassword().setBalloonTip(vr.getMessage());
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
        ValidationResult vr;
        vr = eci.isUsernameCorrect(regUsername);
        if (!vr.isValid()) {
            lView.getRegUsername().setBalloonTip(vr.getMessage());
            return;
        }
        vr = eci.isPasswordCorrect(regPassword);
        if (!vr.isValid()) {
            lView.getRegPass().setBalloonTip(vr.getMessage());
            return;
        }
        vr = eci.isEmailCorrect(regEmail);
        if (!vr.isValid()) {
            lView.getRegEmail().setBalloonTip(vr.getMessage());
            return;
        }
        String regRepPass = lView.getRegRepPass().getText();
        if (!regPassword.equals(regRepPass)) {
            lView.getRegRepPass().setBalloonTip(localization.getPasswordsDontMatch());
            return;
        }
        System.out.println("Registration is complete.");
    }

    
}
