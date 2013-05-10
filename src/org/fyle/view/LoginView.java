
package org.fyle.view;

import org.fyle.model.KeyPressedListener;
import javax.swing.JComponent;
import org.fyle.model.ClickListener;

public interface LoginView {
    void show();
    void subscribeOnLoginButtonClick(ClickListener cl);
    void subscribeOnLoginKeyPressed(KeyPressedListener kl);
    void subscribeOnRegButtonClick(ClickListener cl);
    void subscribeOnRegKeyPressed (KeyPressedListener kl);
    void displayError(JComponent jcomp, String errorMessage);
    TextField getLoginUsername();
    TextField getLoginPassword();
    TextField getRegPass();
    TextField getRegRepPass();
    TextField getRegUsername();
    TextField getRegEmail();
}
