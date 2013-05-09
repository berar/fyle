package org.fyle.view.impl;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import net.java.balloontip.BalloonTip;
import org.fyle.model.ClickListener;
import org.fyle.model.KeyPressedListener;
import org.fyle.view.LoginView;
import org.fyle.view.TextField;

public class LoginViewImpl extends JFrame implements LoginView {

    private JButton loginButton;
    private JButton regButton;
    private TextField loginUsername;
    private TextField loginPassword;
    private TextField regPass, regRepPass;
    private TextField regUsername, regEmail;
    private JLabel logUserLabel, logPassLabel, regPassLabel, regRepPassLabel, regUserLabel, regEmailLabel;
    private TextField[] loginFields;
    private TextField[] regFields;
    private static BalloonTip errorTip;
    private ClickListener cl;
    private KeyPressedListener kl;

    public LoginViewImpl() {
        initUI();
    }

    private void initUI() {
        setResizable(false);
        setTitle("Fyle - Login");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginButton = new JButton("Login");
        regButton = new JButton("Register");
        loginUsername = new TextFieldImpl(12);
        getLoginUsername().setMaximumSize(getLoginUsername().getPreferredSize());
        loginPassword = new PasswordFieldImpl(12);
        getLoginPassword().setMaximumSize(getLoginPassword().getPreferredSize());
        regPass = new PasswordFieldImpl(12);
        getRegPass().setMaximumSize(getRegPass().getPreferredSize());
        regRepPass = new PasswordFieldImpl(12);
        getRegRepPass().setMaximumSize(getRegRepPass().getPreferredSize());
        regUsername = new TextFieldImpl(12);
        getRegUsername().setMaximumSize(getRegUsername().getPreferredSize());
        regEmail = new TextFieldImpl(12);
        getRegEmail().setMaximumSize(getRegEmail().getPreferredSize());
        logUserLabel = new JLabel("Username:");
        logPassLabel = new JLabel("Password:");
        regPassLabel = new JLabel("Password:");
        regRepPassLabel = new JLabel("Repeat password:");
        regEmailLabel = new JLabel("Email:");
        regUserLabel = new JLabel("Username:");
        Dimension buttonDim = new Dimension(getLoginUsername().getHeight(), getLoginUsername().getWidth() - 60);
        loginButton.setSize(buttonDim);
        loginFields = new TextField[]{getLoginUsername(), getLoginPassword()};
        regFields = new TextField[]{getRegUsername(), getRegEmail(), getRegPass(), getRegRepPass()};
        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(new BoxLayout(loginPanel, BoxLayout.Y_AXIS));
        loginPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        logUserLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        loginPanel.add(logUserLabel);
        getLoginUsername().setAlignmentX(Component.LEFT_ALIGNMENT);
        loginPanel.add((Component) getLoginUsername());
        logPassLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        loginPanel.add(logPassLabel);
        getLoginPassword().setAlignmentX(Component.LEFT_ALIGNMENT);
        loginPanel.add((Component) getLoginPassword());
        loginPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        loginButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        loginPanel.add(loginButton);

        JPanel regPanel = new JPanel();
        regPanel.setLayout(new BoxLayout(regPanel, BoxLayout.Y_AXIS));
        regPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        regUserLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        regPanel.add(regUserLabel);
        getRegUsername().setAlignmentX(Component.LEFT_ALIGNMENT);
        regPanel.add((Component) getRegUsername());
        regEmailLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        regPanel.add(regEmailLabel);
        getRegEmail().setAlignmentX(Component.LEFT_ALIGNMENT);
        regPanel.add((Component) getRegEmail());
        regPassLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        regPanel.add(regPassLabel);
        getRegPass().setAlignmentX(Component.LEFT_ALIGNMENT);
        regPanel.add((Component) getRegPass());
        regRepPassLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        regPanel.add(regRepPassLabel);
        getRegRepPass().setAlignmentX(Component.LEFT_ALIGNMENT);
        regPanel.add((Component) getRegRepPass());
        regPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        regButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        regPanel.add(regButton);

        getContentPane().setLayout(
                new BoxLayout(getContentPane(), BoxLayout.X_AXIS));
        loginPanel.setAlignmentY(JLabel.TOP_ALIGNMENT);
        add(loginPanel);
        JSeparator js = new JSeparator(JSeparator.VERTICAL);
        js.setPreferredSize(new Dimension(1, 50));
        js.setAlignmentX(Component.TOP_ALIGNMENT);
        add(js);
        regPanel.setAlignmentY(JLabel.TOP_ALIGNMENT);
        add(regPanel);
        pack();
    }

    @Override
    public void displayError(JComponent jcomp, String errorMessage) {
        if (errorTip != null) {
            errorTip.closeBalloon();
        }
        errorTip = new BalloonTip(jcomp, errorMessage);
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
    public void subscribeOnLoginButtonClick(final ClickListener cl) {
        this.cl = cl;
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cl.actionPerformed();
            }
        });
    }

    @Override
    public void subscribeOnLoginKeyPressed(final KeyPressedListener kl) {
        this.kl = kl;
        for (TextField loginField : loginFields) {
            loginField.addKeyListener(new KeyListener() {
                @Override
                public void keyTyped(KeyEvent e) {
                }

                @Override
                public void keyPressed(KeyEvent e) {
                    kl.actionPerformed(e);
                }

                @Override
                public void keyReleased(KeyEvent e) {
                }
            });
        }
    }

    @Override
    public void subscribeOnRegButtonClick(final ClickListener cl) {
        this.cl = cl;
        regButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cl.actionPerformed();
            }
        });
    }

    @Override
    public void subscribeOnRegKeyPressed(final KeyPressedListener kl) {
        this.kl = kl;
        for (TextField regField : regFields) {
            regField.addKeyListener(new KeyListener() {
                @Override
                public void keyTyped(KeyEvent e) {
                }

                @Override
                public void keyPressed(KeyEvent e) {
                    kl.actionPerformed(e);
                }

                @Override
                public void keyReleased(KeyEvent e) {
                }
            });
        }
    }
}
