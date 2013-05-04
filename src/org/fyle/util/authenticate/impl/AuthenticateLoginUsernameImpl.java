
package org.fyle.util.authenticate.impl;

import org.fyle.presenter.LoginPresenter;
import org.fyle.util.authenticate.AuthenticityState;

public class AuthenticateLoginUsernameImpl implements AuthenticityState {
    
    LoginPresenter lp;
    String message;
    
    private AuthenticateLoginUsernameImpl(){}
    
    public AuthenticateLoginUsernameImpl(LoginPresenter lp, String message){
        this.lp=lp;
        this.message=message;
    }
    
    @Override
    public void execute() {
        lp.sendLoginUsernameError(message);
    }
    
}
