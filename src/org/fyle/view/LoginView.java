
package org.fyle.view;

import javax.swing.JComponent;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import org.fyle.model.ActionListeners;

public interface LoginView {
    void show();
    void subscribeOnLoginButtonClick(ActionListeners al);
    void subscribeOnRegButtonClick(ActionListeners al);
    void subscribeOnLoginKeyPressed (ActionListeners al);
    void subscribeOnRegKeyPressed (ActionListeners al);
    void displayError(JComponent jcomp, String errorMessage);
    JTextField getLoginUsername();
    JPasswordField getLoginPassword();
    JPasswordField getRegPass();
    JPasswordField getRegRepPass();
    JTextField getRegUsername();
    JTextField getRegEmail();
    void displayLoginUsernameError(String errorMessage);
    void displayLoginPasswordError(String errorMessage);
    void closeBalloonTip();
    void displayRegisterPasswordError(String errorMessage);
    void displayRegisterUsernameError(String errorMessage);
    void displayRegisterEmailError(String errorMessage);
}
