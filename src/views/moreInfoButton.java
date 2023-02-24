package views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;

public class moreInfoButton extends JButton {
    public moreInfoButton() {
        this.setText("more Info");
        this.setBounds(10,204,209,50);
        this.setSize(new Dimension(110,30));
        this.setFont(new Font(Font.DIALOG, Font.BOLD, 15));
        this.setForeground(Color.white);
        this.setBackground(Color.black);
        this.setFocusable(false);
    }
}
