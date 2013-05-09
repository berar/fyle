
package org.fyle.util.authenticate.impl;

import org.fyle.presenter.LoginPresenter;
import org.fyle.util.authenticate.AuthenticityState;

public class AuthenticateRegisterEmailImpl implements AuthenticityState {
    
    LoginPresenter lp;
    String message;
    
    private AuthenticateRegisterEmailImpl(){}
    
    public AuthenticateRegisterEmailImpl(LoginPresenter lp, String message){
        this.lp=lp;
        this.message=message;
    }
    
    @Override
    public void execute() {
        lp.sendRegisterEmailError(message);
    }
    
}
