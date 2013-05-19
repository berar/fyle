
package org.fyle.view;

import org.fyle.model.WindowExitListener;
import org.fyle.view.component.Button;
import org.fyle.view.component.Label;
import org.fyle.view.component.TextField;

/**
 *
 * @author berar
 */
public interface LoginView {
	void setVisible(boolean visible);
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
