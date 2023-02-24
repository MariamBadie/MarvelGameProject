package views;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;

public class OptionsButton extends JButton {
    public OptionsButton() {
        this.setText("How to play");
        this.setBounds(660,400,200,50);
        this.setFont(new Font(Font.DIALOG, Font.BOLD, 15));
        this.setForeground(Color.white);
        this.setBackground(new Color(0x373F51));
        this.setFocusable(false);
    }
}
