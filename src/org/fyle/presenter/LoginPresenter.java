
package org.fyle.presenter;

public interface LoginPresenter {
    
    void checkLoginErrors();
    void checkRegErrors();
    void start();
    void sendLoginUsernameError(String message);
    void sendLoginPasswordError(String message);
    void sendRegisterUsernameError(String message);
    void sendRegisterPasswordError(String message);
    void sendRegisterEmailError(String message);
}
