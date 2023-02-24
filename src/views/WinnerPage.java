package views;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import engine.Player;
public class WinnerPage extends JFrame implements ActionListener {
    CreditsButton b;
    public WinnerPage(Player winner) {
        String s = winner.getName();
        JLabel image = new JLabel(new ImageIcon("Resources/congrats.png"));
        image.setBounds(400,-50,700,500);
        JLabel congrats = new JLabel("Congrats " + s + " YOU WON!!!");
        this.setLayout(null);
        this.getContentPane().setBackground(Color.white);
        congrats.setBounds(550,300,500,500);
        congrats.setFont(new Font(Font.DIALOG, Font.BOLD, 30));
        b = new CreditsButton();
        b.setText("EXIT GAME");
        b.setBounds(650,660,200,50);
        b.addActionListener(this);
        this.add(b);
        this.add(image);
        this.add(congrats);
        this.setExtendedState(this.MAXIMIZED_BOTH);
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
//		this.setUndecorated(true);
        this.setVisible(true);
        this.setResizable(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b)
            this.dispose();
    }
    public static void main(String[] args) {
        Player b = new Player("Kareem");
        new WinnerPage(b);
    }
}
