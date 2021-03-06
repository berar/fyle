package org.fyle.view.impl;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import org.fyle.model.WindowExitListener;
import org.fyle.view.LoginView;
import org.fyle.view.component.Button;
import org.fyle.view.component.Label;
import org.fyle.view.component.TextField;
import org.fyle.view.component.impl.ButtonImpl;
import org.fyle.view.component.impl.PasswordFieldImpl;
import org.fyle.view.component.impl.StatusLabelImpl;
import org.fyle.view.component.impl.TextFieldImpl;

/**
 *
 * @author berar
 */
public class LoginViewImpl extends JFrame implements LoginView {

    private ButtonImpl loginButton;
    private ButtonImpl regButton;
    private TextFieldImpl loginUsername;
    private PasswordFieldImpl loginPassword;
    private PasswordFieldImpl regPass, regRepPass;
    private TextFieldImpl regUsername, regEmail;
    private JLabel logUserLabel, logPassLabel, regPassLabel, regRepPassLabel, regUserLabel, regEmailLabel;
    private JLabel statustxtLabel;
    private StatusLabelImpl statusLabel;

    public LoginViewImpl() {
        initUI();
    }

    private void initUI() {
        setResizable(false);
        setTitle("Fyle - Login");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        loginButton = new ButtonImpl("Login");
        regButton = new ButtonImpl("Register");
        loginUsername = new TextFieldImpl(12);
        loginUsername.setDocument(new TextFieldLimit(16));
        loginUsername.setMaximumSize(loginUsername.getPreferredSize());
        loginButton.setMaximumSize(loginButton.getPreferredSize());
        loginPassword = new PasswordFieldImpl(12);
        loginPassword.setDocument(new TextFieldLimit(128));
        loginPassword.setMaximumSize(loginPassword.getPreferredSize());
        regPass = new PasswordFieldImpl(12);
        regPass.setDocument(new TextFieldLimit(128));
        regPass.setMaximumSize(regPass.getPreferredSize());
        regRepPass = new PasswordFieldImpl(12);
        regRepPass.setDocument(new TextFieldLimit(128));
        regRepPass.setMaximumSize(regRepPass.getPreferredSize());
        regUsername = new TextFieldImpl(12);
        regUsername.setDocument(new TextFieldLimit(16));
        regUsername.setMaximumSize(regUsername.getPreferredSize());
        regEmail = new TextFieldImpl(12);
        regEmail.setDocument(new TextFieldLimit(254));
        regEmail.setMaximumSize(regEmail.getPreferredSize());
        logUserLabel = new JLabel("Username:");
        logPassLabel = new JLabel("Password:");
        regPassLabel = new JLabel("Password:");
        regRepPassLabel = new JLabel("Repeat password:");
        regEmailLabel = new JLabel("Email:");
        regUserLabel = new JLabel("Username:");
        Dimension buttonDim = new Dimension(loginUsername.getHeight(), loginUsername.getWidth() - 60);
        loginButton.setSize(buttonDim);
        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(new BoxLayout(loginPanel, BoxLayout.Y_AXIS));
        loginPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        logUserLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        loginPanel.add(logUserLabel);
        loginUsername.setAlignmentX(Component.LEFT_ALIGNMENT);
        loginPanel.add((Component) getLoginUsername());
        logPassLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        loginPanel.add(logPassLabel);
        loginPassword.setAlignmentX(Component.LEFT_ALIGNMENT);
        loginPanel.add((Component) getLoginPassword());
        loginPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        loginButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        loginPanel.add(loginButton);

        JPanel regPanel = new JPanel();
        regPanel.setLayout(new BoxLayout(regPanel, BoxLayout.Y_AXIS));
        regPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        regUserLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        regPanel.add(regUserLabel);
        regUsername.setAlignmentX(Component.LEFT_ALIGNMENT);
        regPanel.add((Component) getRegUsername());
        regEmailLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        regPanel.add(regEmailLabel);
        regEmail.setAlignmentX(Component.LEFT_ALIGNMENT);
        regPanel.add((Component) getRegEmail());
        regPassLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        regPanel.add(regPassLabel);
        regPass.setAlignmentX(Component.LEFT_ALIGNMENT);
        regPanel.add((Component) getRegPass());
        regRepPassLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        regPanel.add(regRepPassLabel);
        regRepPass.setAlignmentX(Component.LEFT_ALIGNMENT);
        regPanel.add((Component) getRegRepPass());
        regPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        regButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        regPanel.add(regButton);
        
        JPanel formPanel = new JPanel();
        formPanel.setLayout(
                new BoxLayout(formPanel, BoxLayout.X_AXIS));
        loginPanel.setAlignmentY(JLabel.TOP_ALIGNMENT);
        formPanel.add(loginPanel);
        JSeparator js = new JSeparator(JSeparator.VERTICAL);
        js.setPreferredSize(new Dimension(1, 50));
        js.setAlignmentX(Component.TOP_ALIGNMENT);
        formPanel.add(js);
        regPanel.setAlignmentY(JLabel.TOP_ALIGNMENT);
        formPanel.add(regPanel);
        
        statustxtLabel = new JLabel("Status: ");
        statusLabel = new StatusLabelImpl("");
        JPanel statusPanel = new JPanel();
        statusPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        statusPanel.add(statustxtLabel);
        statusPanel.add(statusLabel);
        
        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        add(formPanel);
        add(statusPanel);
        pack();
    }
    
    @Override
	public void subscribeOnWindowExit(final WindowExitListener wl) {
    	this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                wl.exit();
            }
        });
	}

    @Override
    public TextField getLoginUsername() {
        return loginUsername;
    }

    @Override
    public TextField getLoginPassword() {
        return loginPassword;
    }

    @Override
    public TextField getRegPass() {
        return regPass;
    }

    @Override
    public TextField getRegRepPass() {
        return regRepPass;
    }

    @Override
    public TextField getRegUsername() {
        return regUsername;
    }

    @Override
    public TextField getRegEmail() {
        return regEmail;
    }
    
    @Override
    public void closeFrame() {
        WindowEvent wev = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
        Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(wev);
        setVisible(false);
        dispose();
    }

	@Override
	public Button getLoginButton() {
		return loginButton;
	}

	@Override
	public Button getRegisterButton() {
		return regButton;
	}
	
	@Override
	public Label getStatusLabel(){
		return statusLabel;
	}
	
	@Override
	public void enableAllForms(boolean enable){
		loginButton.setEnabled(enable);
		loginUsername.setEnabled(enable);
		loginPassword.setEnabled(enable);
		regButton.setEnabled(enable);
		regUsername.setEnabled(enable);
		regPass.setEnabled(enable);
		regRepPass.setEnabled(enable);
		regEmail.setEnabled(enable);
	}
}
