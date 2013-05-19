package org.fyle.view.component;

import javax.swing.Icon;

public interface Label {
	void setText(String text);
	void setIcon(Icon icon);
	void setLoadingIcon();
	void setOkIcon();
	void setErrorIcon();
	void setToolTipText(String toolTipText);
}
