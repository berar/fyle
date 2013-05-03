package org.nugs.clientapp.view;

import org.nugs.clientapp.model.PressOrClick;

/**
 *
 * @author Korisnik
 */
public interface ClientView {
    void show();
    String getInputText();
    void setOutputText(String output, boolean ln);
    void subscribeOnPressOrClick(PressOrClick pressOrClick);
    void clearTextArea();
    void clearChatArea();
}
