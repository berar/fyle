
package org.fyle.view;

import org.fyle.model.WindowExitListener;

/**
 *
 * @author berar
 */
public interface LoginView {
    void show();
    void closeFrame();
    void enableAllForms(boolean enable);
    void subscribeOnWindowExit(WindowExitListener wl);
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
