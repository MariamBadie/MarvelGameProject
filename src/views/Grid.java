package views;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Grid extends JPanel {
    JButton [][] board = new JButton[5][5];
    public Grid() {
        // use array to access buttons
        this.setLayout(new GridLayout(5,5,5,5));
        this.setBackground(new Color(0x2C2B3C));
        JButton b1 = new JButton();
        b1.setBackground(Color.white);
        b1.setLayout(null);
        this.add(b1);
        JButton b2 = new JButton();
        b2.setBackground(Color.white);
        b2.setLayout(null);
        this.add(b2);
        JButton b3 = new JButton();
        b3.setBackground(Color.white);
        b3.setLayout(null);
        this.add(b3);
        JButton b4 = new JButton();
        b4.setBackground(Color.white);
        b4.setLayout(null);
        this.add(b4);
        JButton b5 = new JButton();
        b5.setBackground(Color.white);
        b5.setLayout(null);
        this.add(b5);
        JButton b6 = new JButton();
        this.add(b6);
        b6.setBackground(Color.white);
        b6.setLayout(null);
        JButton b7 = new JButton();
        this.add(b7);
        b7.setBackground(Color.white);
        b7.setLayout(null);
        JButton b8 = new JButton();
        this.add(b8);
        b8.setBackground(Color.white);
        b8.setLayout(null);
        JButton b9 = new JButton();
        this.add(b9);
        b9.setBackground(Color.white);
        b9.setLayout(null);
        JButton b10 = new JButton();
        this.add(b10);
        b10.setBackground(Color.white);
        b10.setLayout(null);
        JButton b11 = new JButton();
        this.add(b11);
        b11.setBackground(Color.white);
        b11.setLayout(null);
        JButton b12 = new JButton();
        this.add(b12);
        b12.setBackground(Color.white);
        b12.setLayout(null);
        JButton b13 = new JButton();
        this.add(b13);
        b13.setBackground(Color.white);
        b13.setLayout(null);
        JButton b14 = new JButton();
        this.add(b14);
        b14.setBackground(Color.white);
        b14.setLayout(null);
        JButton b15 = new JButton();
        this.add(b15);
        b15.setBackground(Color.white);
        b15.setLayout(null);
        JButton b16 = new JButton();
        this.add(b16);
        b16.setBackground(Color.white);
        b16.setLayout(null);
        JButton b17 = new JButton();
        this.add(b17);
        b17.setBackground(Color.white);
        b17.setLayout(null);
        JButton b18 = new JButton();
        this.add(b18);
        b18.setBackground(Color.white);
        b18.setLayout(null);
        JButton b19 = new JButton();
        this.add(b19);
        b19.setBackground(Color.white);
        b19.setLayout(null);
        JButton b20 = new JButton();
        this.add(b20);
        b20.setBackground(Color.white);
        b20.setLayout(null);
        JButton b21 = new JButton();
        this.add(b21);
        b21.setBackground(Color.white);
        b21.setLayout(null);
        JButton b22 = new JButton();
        this.add(b22);
        b22.setBackground(Color.white);
        b22.setLayout(null);
        JButton b23 = new JButton();
        this.add(b23);
        b23.setBackground(Color.white);
        b23.setLayout(null);
        JButton b24 = new JButton();
        this.add(b24);
        b24.setBackground(Color.white);
        b24.setLayout(null);
        JButton b25 = new JButton();
        this.add(b25);
        b25.setBackground(Color.white);
        b25.setLayout(null);
        board = new JButton[5][5];
        board[0][0] = b21;
        board[0][1] = b22;
        board[0][2] = b23;
        board[0][3] = b24;
        board[0][4] = b25;
        board[1][0] = b16;
        board[1][1] = b17;
        board[1][2] = b18;
        board[1][3] = b19;
        board[1][4] = b20;
        board[2][0] = b11;
        board[2][1] = b12;
        board[2][2] = b13;
        board[2][3] = b14;
        board[2][4] = b15;
        board[3][0] = b6;
        board[3][1] = b7;
        board[3][2] = b8;
        board[3][3] = b9;
        board[3][4] = b10;
        board[4][0] = b1;
        board[4][1] = b2;
        board[4][2] = b3;
        board[4][3] = b4;
        board[4][4] = b5;
    }
    public JButton[][] getBoard() {
        return board;
    }
    public static void main(String[] args) {
        JFrame x = new JFrame();
        Grid g = new Grid();
        x.add(g);
        x.setExtendedState(x.MAXIMIZED_BOTH);
        x.setDefaultCloseOperation(x.EXIT_ON_CLOSE);
        x.setVisible(true);
    }
}
