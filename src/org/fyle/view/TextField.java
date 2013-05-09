
package org.fyle.view;

import java.awt.Dimension;
import java.awt.event.KeyListener;

/**
 *
 * @author Korisnik
 */
public interface TextField {
    String getText();
    void setToolTipText(String toolTipText);
    void setBalloonTip(String message);
    
    Dimension getPreferredSize();
    void setMaximumSize(Dimension d);
    void setAlignmentX(float f);
    int getHeight();
    int getWidth();
    void addKeyListener(KeyListener kl);
}
