
package org.fyle.view.impl;

import javax.swing.JComponent;
import javax.swing.JTextField;
import net.java.balloontip.BalloonTip;
import net.java.balloontip.utils.TimingUtils;
import org.fyle.view.TextField;

/**
 *
 * @author Korisnik
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
    
}
