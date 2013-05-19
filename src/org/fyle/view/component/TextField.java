
package org.fyle.view.component;

import org.fyle.model.KeyPressedListener;

/**
 *
 * @author berar
 */
public interface TextField {
    String getText();
    void setToolTipText(String toolTipText);
    void setBalloonTip(String message);
    void setEnabled(boolean editable);
    void subscribeOnKeyPressed(KeyPressedListener kpl);
}
