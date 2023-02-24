package views;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;

public class ActionButtons extends JButton{
    public ActionButtons(String s) {
        this.setText(s);
        this.setBackground(Color.white);
        this.setForeground(Color.black);
        this.setFocusable(false);
        this.setFont(new Font(Font.DIALOG, Font.BOLD, 15));

    }
}
