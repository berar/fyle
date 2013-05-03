/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.nugs.clientapp.view.impl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.AbstractButton;
import javax.swing.JComponent;
import org.nugs.clientapp.model.PressOrClick;

/**
 *
 * @author Korisnik
 */
public class PressOrClickListeners {

    public static void addClickListeners(final PressOrClick poc, AbstractButton... buttons) {
        for (AbstractButton button : buttons) {
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    poc.pressOrClickPerformed();
                }
            });
        }
    }

    public static void addKeyEvents(final int key, final PressOrClick poc, JComponent... jcomps) {
        for (JComponent jcomp : jcomps) {
            jcomp.addKeyListener(new KeyListener() {
                @Override
                public void keyTyped(KeyEvent e) {
                }
                @Override
                public void keyPressed(KeyEvent e) {
                    if (e.getKeyCode() == key) {
                        poc.pressOrClickPerformed();
                    }
                }
                @Override
                public void keyReleased(KeyEvent e) {
                }
            });
        }
    }
}
