
package org.fyle.util.authenticate.impl;

import org.fyle.presenter.LoginPresenter;
import org.fyle.util.authenticate.AuthenticityState;

public class AuthenticateLoginPasswordImpl implements AuthenticityState {
    
    LoginPresenter lp;
    String message;
    
    private AuthenticateLoginPasswordImpl(){}
    
    public AuthenticateLoginPasswordImpl(LoginPresenter lp, String message){
        this.lp=lp;
        this.message=message;
    }
    
    @Override
    public void execute() {
        lp.sendLoginPasswordError(message);
    }
}
