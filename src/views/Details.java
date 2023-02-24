package views;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;

public class Details extends JButton {
    public Details() {
        this.setText("Details about current champion");
        this.setBounds(660,500,200,50);
        this.setFont(new Font(Font.DIALOG, Font.BOLD, 15));
        this.setForeground(Color.white);
        this.setBackground(new Color(0x373F51));
        this.setFocusable(false);
    }
}
