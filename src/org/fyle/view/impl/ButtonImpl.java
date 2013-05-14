package org.fyle.view.impl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import org.fyle.model.ClickListener;
import org.fyle.view.Button;

/**
 *
 * @author berar
 */
public class ButtonImpl extends JButton implements Button {
	
	private ClickListener cl;
	
	public ButtonImpl(String text){
		super(text);
	}
	
	@Override
	public void subscribeOnClick(final ClickListener cl) {
		this.cl = cl;
        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cl.actionPerformed();
            }
        });
		
	}

}
