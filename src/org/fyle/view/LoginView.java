
package org.fyle.view;

import javax.swing.JComponent;

/**
 *
 * @author berar
 */
public interface LoginView {
    void show();
    void displayError(JComponent jcomp, String errorMessage);
    void closeFrame();
    void enableAllForms(boolean enable);
    Label getStatusLabel();
    Button getLoginButton();
    Button getRegisterButton();
    TextField getLoginUsername();
    TextField getLoginPassword();
    TextField getRegPass();
    TextField getRegRepPass();
    TextField getRegUsername();
    TextField getRegEmail();
}
