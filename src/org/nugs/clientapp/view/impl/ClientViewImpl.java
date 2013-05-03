package org.nugs.clientapp.view.impl;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.KeyEvent;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import org.nugs.clientapp.model.PressOrClick;
import org.nugs.clientapp.view.ClientView;

/**
 *
 * @author Korisnik
 */
public class ClientViewImpl extends JFrame implements ClientView {

    private JButton submit;
    private JTextArea chatArea;
    private JTextArea textArea;
    private PressOrClick pressOrClick;

    public ClientViewImpl() {
        initUI();
    }

    private void initUI() {
        setTitle("Chat Client");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        submit = new JButton("Submit");
        textArea = new JTextArea(3, 100);
        JScrollPane textAreaScroll = new JScrollPane(textArea);
        textAreaScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        textAreaScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        textAreaScroll.setPreferredSize(new Dimension(300, 40));
        textArea.setEditable(true);
        chatArea = new JTextArea(10, 50);
        JScrollPane chatAreaScroll = new JScrollPane(chatArea);
        chatAreaScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        chatAreaScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        chatAreaScroll.setPreferredSize(new Dimension(100, 300));
        chatArea.setEditable(false);

        JPanel flowPanel = new JPanel();
        flowPanel.setLayout(new FlowLayout());
        flowPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        flowPanel.add(textAreaScroll);
        flowPanel.add(submit);

        setLayout(new BorderLayout());
        add(chatAreaScroll, BorderLayout.CENTER);
        add(flowPanel, BorderLayout.SOUTH);
        pack();
    }

    @Override
    public String getInputText() {
        return textArea.getText();
    }

    @Override
    public void setOutputText(String output, boolean ln) {

        if (ln) {
            chatArea.append(output + "\n");
        } else {
            chatArea.append(output);
        }
    }

    @Override
    public void subscribeOnPressOrClick(final PressOrClick pressOrClick) {
        this.pressOrClick = pressOrClick;
        PressOrClickListeners.addClickListeners(pressOrClick, submit);
        PressOrClickListeners.addKeyEvents(KeyEvent.VK_ENTER, pressOrClick, textArea);
    }

    @Override
    public void clearTextArea() {
        textArea.setText(null);
    }

    @Override
    public void clearChatArea() {
        chatArea.setText(null);
    }
}
