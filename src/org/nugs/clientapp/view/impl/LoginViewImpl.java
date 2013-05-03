/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.nugs.clientapp.view.impl;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import org.nugs.clientapp.model.PressOrClick;
import org.nugs.clientapp.view.ClientView;

/**
 *
 * @author Korisnik
 */
public class LoginViewImpl extends JFrame implements ClientView {

    private JButton loginButton;
    private JButton regButton;
    private JTextField loginUsername;
    private JPasswordField loginPassword, regPass, regRepPass;
    private JTextField regUsername, regEmail;
    private JLabel logUserLabel, logPassLabel, regPassLabel, regRepPassLabel, regUserLabel, regEmailLabel;

    public LoginViewImpl() {
        initUI();
    }

    private void initUI() {
        setResizable(false);
        setTitle("Fyle - Login");
        //setSize(600,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        loginButton = new JButton("Login");
        regButton = new JButton("Register");
        loginUsername = new JTextField(12);
        loginPassword = new JPasswordField(12);
        regPass = new JPasswordField(12);
        regRepPass = new JPasswordField(12);
        regUsername = new JTextField(12);
        regEmail = new JTextField(12);
        logUserLabel = new JLabel("Username:");
        logPassLabel = new JLabel("Password:");
        regPassLabel = new JLabel("Password:");
        regRepPassLabel = new JLabel("Repeat password:");
        regEmailLabel = new JLabel ("Email:");
        regUserLabel = new JLabel ("Username:");
        
        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(new GridLayout(5,1));
        loginPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        loginPanel.add(logUserLabel);
        loginPanel.add(loginUsername);
        loginPanel.add(logPassLabel);
        loginPanel.add(loginPassword);
        loginPanel.add(loginButton);
        
        JPanel regPanel = new JPanel();
        regPanel.setLayout(new GridLayout(9,1));
        regPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        regPanel.add(regUserLabel);
        regPanel.add(regUsername);
        regPanel.add(regEmailLabel);
        regPanel.add(regEmail);
        regPanel.add(regPassLabel);
        regPanel.add(regPass);
        regPanel.add(regRepPassLabel);
        regPanel.add(regRepPass);
        regPanel.add(regButton);
        
        setLayout(new FlowLayout());
        add(loginPanel);
        JSeparator js = new JSeparator(JSeparator.VERTICAL);
        js.setPreferredSize(new Dimension(1,100));
        add(js);
        add(regPanel);
        pack();
    }
    
    public static void main(String[] args) {
        new LoginViewImpl().show();
    }
    
    @Override
    public String getInputText() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setOutputText(String output, boolean ln) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void subscribeOnPressOrClick(PressOrClick pressOrClick) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void clearTextArea() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void clearChatArea() {
        throw new AssertionError();
    }
}
