
package org.fyle.util.authenticate.impl;

import org.fyle.presenter.LoginPresenter;
import org.fyle.util.authenticate.AuthenticityState;

public class AuthenticateRegisterUsernameImpl implements AuthenticityState {
    
    LoginPresenter lp;
    String message;
    
    private AuthenticateRegisterUsernameImpl(){}
    
    public AuthenticateRegisterUsernameImpl(LoginPresenter lp, String message){
        this.lp=lp;
        this.message=message;
    }
    
    @Override
    public void execute() {
        lp.sendRegisterUsernameError(message);
    }
    
}
