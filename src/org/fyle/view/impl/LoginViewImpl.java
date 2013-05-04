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
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import net.java.balloontip.BalloonTip;
import org.fyle.model.ActionListeners;
import org.fyle.view.LoginView;

public class LoginViewImpl extends JFrame implements LoginView {

    private JButton loginButton;
    private JButton regButton;
    private JTextField loginUsername;
    private JPasswordField loginPassword, regPass, regRepPass;
    private JTextField regUsername, regEmail;
    private JLabel logUserLabel, logPassLabel, regPassLabel, regRepPassLabel, regUserLabel, regEmailLabel;
    private ActionListeners al;
    private JTextField[] loginFields;
    private JTextField[] regFields;
    private static BalloonTip errorTip;

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
        loginUsername = new JTextField(12);
        getLoginUsername().setMaximumSize(getLoginUsername().getPreferredSize());
        loginPassword = new JPasswordField(12);
        getLoginPassword().setMaximumSize(getLoginPassword().getPreferredSize());
        regPass = new JPasswordField(12);
        getRegPass().setMaximumSize(getRegPass().getPreferredSize());
        regRepPass = new JPasswordField(12);
        getRegRepPass().setMaximumSize(getRegRepPass().getPreferredSize());
        regUsername = new JTextField(12);
        getRegUsername().setMaximumSize(getRegUsername().getPreferredSize());
        regEmail = new JTextField(12);
        getRegEmail().setMaximumSize(getRegEmail().getPreferredSize());
        logUserLabel = new JLabel("Username:");
        logPassLabel = new JLabel("Password:");
        regPassLabel = new JLabel("Password:");
        regRepPassLabel = new JLabel("Repeat password:");
        regEmailLabel = new JLabel("Email:");
        regUserLabel = new JLabel("Username:");
        Dimension buttonDim = new Dimension(getLoginUsername().getHeight(), getLoginUsername().getWidth() - 60);
        loginButton.setSize(buttonDim);
        loginFields = new JTextField[]{getLoginUsername(), getLoginPassword()};
        regFields = new JTextField[]{getRegUsername(), getRegEmail(), getRegPass(), getRegRepPass()};

        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(new BoxLayout(loginPanel, BoxLayout.Y_AXIS));
        loginPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        logUserLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        loginPanel.add(logUserLabel);
        getLoginUsername().setAlignmentX(Component.LEFT_ALIGNMENT);
        loginPanel.add(getLoginUsername());
        logPassLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        loginPanel.add(logPassLabel);
        getLoginPassword().setAlignmentX(Component.LEFT_ALIGNMENT);
        loginPanel.add(getLoginPassword());
        loginPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        loginButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        loginPanel.add(loginButton);

        JPanel regPanel = new JPanel();
        regPanel.setLayout(new BoxLayout(regPanel, BoxLayout.Y_AXIS));
        regPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        regUserLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        regPanel.add(regUserLabel);
        getRegUsername().setAlignmentX(Component.LEFT_ALIGNMENT);
        regPanel.add(getRegUsername());
        regEmailLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        regPanel.add(regEmailLabel);
        getRegEmail().setAlignmentX(Component.LEFT_ALIGNMENT);
        regPanel.add(getRegEmail());
        regPassLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        regPanel.add(regPassLabel);
        getRegPass().setAlignmentX(Component.LEFT_ALIGNMENT);
        regPanel.add(getRegPass());
        regRepPassLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        regPanel.add(regRepPassLabel);
        getRegRepPass().setAlignmentX(Component.LEFT_ALIGNMENT);
        regPanel.add(getRegRepPass());
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
    public void subscribeOnLoginButtonClick(final ActionListeners al) {
        this.al = al;
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                al.actionPerformed();
            }
        });
    }

    @Override
    public void subscribeOnRegButtonClick(final ActionListeners al) {
        this.al = al;
        regButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                al.actionPerformed();
            }
        });
    }

    @Override
    public void subscribeOnLoginKeyPressed(final ActionListeners al) {
        this.al = al;
        for (JTextField loginField : loginFields) {
            loginField.addKeyListener(new KeyListener() {
                @Override
                public void keyTyped(KeyEvent e) {
                }

                @Override
                public void keyPressed(KeyEvent e) {
                    //if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    al.actionPerformed(e);

                }

                @Override
                public void keyReleased(KeyEvent e) {
                }
            });
        }
    }

    @Override
    public void subscribeOnRegKeyPressed(final ActionListeners al) {
        this.al = al;
        for (JTextField regField : regFields) {
            regField.addKeyListener(new KeyListener() {
                @Override
                public void keyTyped(KeyEvent e) {
                }

                @Override
                public void keyPressed(KeyEvent e) {
                    al.actionPerformed(e);

                }

                @Override
                public void keyReleased(KeyEvent e) {
                }
            });
        }
    }

    @Override
    public void displayError(JComponent jcomp, String errorMessage) {
        if (errorTip != null) {
            errorTip.closeBalloon();
        }
        errorTip = new BalloonTip(jcomp, errorMessage);

    }

    @Override
    public void displayLoginUsernameError(String errorMessage) {
        if (errorTip != null) {
            errorTip.closeBalloon();
        }
        errorTip = new BalloonTip(loginUsername, errorMessage);
    }

    @Override
    public void displayLoginPasswordError(String errorMessage) {
        if (errorTip != null) {
            errorTip.closeBalloon();
        }
        errorTip = new BalloonTip(loginPassword, errorMessage);
    }

    @Override
    public JTextField getLoginUsername() {
        return loginUsername;
    }

    @Override
    public JPasswordField getLoginPassword() {
        return loginPassword;
    }

    @Override
    public JPasswordField getRegPass() {
        return regPass;
    }

    @Override
    public JPasswordField getRegRepPass() {
        return regRepPass;
    }

    @Override
    public JTextField getRegUsername() {
        return regUsername;
    }

    @Override
    public JTextField getRegEmail() {
        return regEmail;
    }

    @Override
    public void closeBalloonTip() {
        if (errorTip != null) {
            errorTip.closeBalloon();
        }
    }

    @Override
    public void displayRegisterPasswordError(String errorMessage) {
        if (errorTip != null) {
            errorTip.closeBalloon();
        }
        errorTip = new BalloonTip(regPass, errorMessage);
    }

    @Override
    public void displayRegisterUsernameError(String errorMessage) {
        if (errorTip != null) {
            errorTip.closeBalloon();
        }
        errorTip = new BalloonTip(regUsername, errorMessage);
    }

    @Override
    public void displayRegisterEmailError(String errorMessage) {
        if(errorTip!=null)
            errorTip.closeBalloon();
        errorTip = new BalloonTip(regEmail, errorMessage);
    }
}
