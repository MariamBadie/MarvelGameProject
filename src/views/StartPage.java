package views;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import engine.Game;
import engine.Player;


public class StartPage extends JFrame implements ActionListener{
    StartButton startButton;
    OptionsButton optionButton;
    JButton creditsButton;
    QuitButton quitButton;
    Clip clip;
    public StartPage() throws LineUnavailableException, IOException, UnsupportedAudioFileException {
        File file = new File("Resources/testsound.wav");
        AudioInputStream as = AudioSystem.getAudioInputStream(file);
        clip = AudioSystem.getClip();
        clip.open(as);
        clip.start();
        clip.loop(clip.LOOP_CONTINUOUSLY);
        JLabel marvelLogo = new JLabel(new ImageIcon("Resources/logo.png"));
        marvelLogo.setBounds(400 , 30 , 750 , 200);
        this.add(marvelLogo);
        startButton = new StartButton();
        startButton.addActionListener(this);
        this.add(startButton);
        optionButton = new OptionsButton();
        optionButton.addActionListener(this);
        this.add(optionButton);
        creditsButton = new CreditsButton();
        this.add(creditsButton);
        creditsButton.addActionListener(this);
        quitButton = new QuitButton();
        quitButton.addActionListener(this);
        this.add(quitButton);
        this.getContentPane().setBackground(Color.white);
        this.setSize(500, 500);
        this.setLayout(null);
        this.setExtendedState(this.MAXIMIZED_BOTH);
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
//		this.setUndecorated(true);
        this.setVisible(true);
        this.setResizable(false);
    }
    public static void main(String[] args) throws LineUnavailableException, IOException, UnsupportedAudioFileException {
        StartPage s = new StartPage();
    }
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == creditsButton) {
            this.dispose();
            clip.stop();
            CreditsPage c = new CreditsPage();
        }
        if (e.getSource() == quitButton) {
            int n = JOptionPane.showConfirmDialog(null, "Are you sure you want to quit ?",
                    "Confirmation message", JOptionPane.YES_NO_OPTION);
            if (n==0) {
                clip.stop();
                this.dispose();
            }
        }
        if (e.getSource() == startButton) {
            String firstPlayerName = JOptionPane.showInputDialog("Dear Player1, What should we call you with?");
            this.dispose();
            clip.stop();
//			StartedGame s = new StartedGame();
            try {
                TransitPage t = new TransitPage(firstPlayerName);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        if (e.getSource() == optionButton) {
            this.dispose();
            new HowtToPlay();
        }
    }
}
