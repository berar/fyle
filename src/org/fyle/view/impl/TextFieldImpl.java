
package org.fyle.view.impl;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JComponent;
import javax.swing.JTextField;
import net.java.balloontip.BalloonTip;
import net.java.balloontip.utils.TimingUtils;

import org.fyle.model.KeyPressedListener;
import org.fyle.view.TextField;

/**
 *
 * @author berar
 */
public class TextFieldImpl extends JTextField implements TextField {
    
    private BalloonTip tip;
    
    public TextFieldImpl(int i) {
        super(i);
    }

    @Override
    public void setBalloonTip(String message) {
        if(tip!=null){
            tip.closeBalloon();
        }
        tip = new BalloonTip((JComponent)this, message);
        TimingUtils.showTimedBalloon(tip, 3000);
    }

	@Override
	public void subscribeOnKeyPressed(final KeyPressedListener kpl) {
        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                kpl.actionPerformed(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
	}
}
