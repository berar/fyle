
package org.fyle.util.authenticate.impl;

import org.fyle.presenter.LoginPresenter;
import org.fyle.util.authenticate.AuthenticityState;

public class AuthenticateRegisterPasswordImpl implements AuthenticityState {
    
    LoginPresenter lp;
    String message;
    
    private AuthenticateRegisterPasswordImpl(){}
    
    public AuthenticateRegisterPasswordImpl(LoginPresenter lp, String message){
        this.lp=lp;
        this.message=message;
    }
    
    @Override
    public void execute() {
        lp.sendRegisterPasswordError(message);
    }
    
}
