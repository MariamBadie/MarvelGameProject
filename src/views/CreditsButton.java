package views;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class CreditsButton extends JButton {
    public CreditsButton() {
        this.setText("Credits");
        this.setBounds(660,500,200,50);
        this.setFont(new Font(Font.DIALOG, Font.BOLD, 15));
        this.setForeground(Color.white);
        this.setBackground(new Color(0x373F51));
        this.setFocusable(false);
    }
}
