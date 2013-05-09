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
import javax.swing.JTextField;
import net.java.balloontip.BalloonTip;
import org.fyle.model.ClickListener;
import org.fyle.model.KeyPressedListener;
import org.fyle.view.LoginView;
import org.fyle.view.TextField;

public class LoginViewImpl extends JFrame implements LoginView {

    private JButton loginButton;
    private JButton regButton;
    private TextFieldImpl loginUsername;
    private PasswordFieldImpl loginPassword;
    private PasswordFieldImpl regPass, regRepPass;
    private TextFieldImpl regUsername, regEmail;
    private JLabel logUserLabel, logPassLabel, regPassLabel, regRepPassLabel, regUserLabel, regEmailLabel;
    private JTextField[] loginFields;
    private JTextField[] regFields;
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
        loginUsername.setMaximumSize(loginUsername.getPreferredSize());
        loginButton.setMaximumSize(loginButton.getPreferredSize());
        loginPassword = new PasswordFieldImpl(12);
        loginPassword.setMaximumSize(loginPassword.getPreferredSize());
        regPass = new PasswordFieldImpl(12);
        regPass.setMaximumSize(regPass.getPreferredSize());
        regRepPass = new PasswordFieldImpl(12);
        regRepPass.setMaximumSize(regRepPass.getPreferredSize());
        regUsername = new TextFieldImpl(12);
        regUsername.setMaximumSize(regUsername.getPreferredSize());
        regEmail = new TextFieldImpl(12);
        regEmail.setMaximumSize(regEmail.getPreferredSize());
        logUserLabel = new JLabel("Username:");
        logPassLabel = new JLabel("Password:");
        regPassLabel = new JLabel("Password:");
        regRepPassLabel = new JLabel("Repeat password:");
        regEmailLabel = new JLabel("Email:");
        regUserLabel = new JLabel("Username:");
        Dimension buttonDim = new Dimension(loginUsername.getHeight(), loginUsername.getWidth() - 60);
        loginButton.setSize(buttonDim);
        loginFields = new JTextField[]{loginUsername, loginPassword};
        regFields = new JTextField[]{regUsername, regEmail, regPass, regRepPass};
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
        for (JTextField loginField : loginFields) {
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
        for (JTextField regField : regFields) {
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
