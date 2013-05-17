package org.fyle.view;

import org.fyle.model.ClickListener;

/**
 *
 * @author berar
 */
public interface Button {
	void setEnabled(boolean enabled);
	void subscribeOnClick(ClickListener cl);
	void setBalloonTip(String message);
}
