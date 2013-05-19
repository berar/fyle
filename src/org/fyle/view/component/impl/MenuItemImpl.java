package org.fyle.view.component.impl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenuItem;
import org.fyle.model.ClickListener;
import org.fyle.view.component.MenuItem;

public class MenuItemImpl extends JMenuItem implements MenuItem {
	
	public MenuItemImpl(String text){
		super(text);
	}
	
	@Override
	public void subscribeOnClick(final ClickListener cl) {
        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cl.actionPerformed();
            }
        });
	}
}
