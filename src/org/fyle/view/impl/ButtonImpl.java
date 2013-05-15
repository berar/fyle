package org.fyle.view.impl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;

import net.java.balloontip.BalloonTip;
import net.java.balloontip.utils.TimingUtils;

import org.fyle.model.ClickListener;
import org.fyle.view.Button;

/**
 *
 * @author berar
 */
public class ButtonImpl extends JButton implements Button {
	
	private ClickListener cl;
	private BalloonTip tip;
	
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
	
	@Override
    public void setBalloonTip(String message) {
        if(tip!=null){
            tip.closeBalloon();
        }
        tip = new BalloonTip((JComponent)this, message);
        TimingUtils.showTimedBalloon(tip, 3000);
    }

}
