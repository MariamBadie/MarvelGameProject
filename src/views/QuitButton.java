package views;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;

public class QuitButton extends JButton {
    public QuitButton() {
        this.setText("Quit");
        this.setBounds(660,600,200,50);
        this.setFont(new Font(Font.DIALOG, Font.BOLD, 15));
        this.setForeground(Color.white);
        this.setBackground(new Color(0x373F51));
        this.setFocusable(false);
    }
}
