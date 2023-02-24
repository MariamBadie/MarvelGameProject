package views;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class CreditsPage extends JFrame implements ActionListener{
    JButton backToMain = new JButton("Return");
    public CreditsPage() {
        this.setLayout(null);
        this.getContentPane().setBackground(Color.white);
        JLabel info = new JLabel("<html><p>We are a group of MET students in the GUC, we implemented this game as team project for csen401 Course, we really hope you like it</p></html>");
        info.setFont(new Font(Font.MONOSPACED, Font.BOLD, 40));
        info.setBounds(200, 0, 900, 400);
        this.add(info);
        JLabel names = new JLabel("<html><p> This project was done by : <br>"
                + "Kareem Ahmed Eladl <br> "
                + "Maryam Mohamed Fawzy <br>"
                + "Mahmoud Tamer Youssef </p></html>");
        names.setFont(new Font(Font.MONOSPACED, Font.BOLD, 40));
        names.setBounds(200, 400 , 680 , 400);
        this.add(names);
        this.add(backToMain);
        backToMain.addActionListener(this);
        backToMain.setBounds(0,0,200,50);
        backToMain.setForeground(Color.white);
        backToMain.setBackground(new Color(0x373F51));
        backToMain.setFocusable(false);
//		this.pack();
        this.setExtendedState(this.MAXIMIZED_BOTH);
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backToMain) {
            this.dispose();
            try {
                StartPage s = new StartPage();
            } catch (LineUnavailableException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            } catch (UnsupportedAudioFileException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }

    }
}
