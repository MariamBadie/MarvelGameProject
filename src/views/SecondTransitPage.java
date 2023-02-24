package views;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import engine.Game;
import engine.Player;
import model.abilities.Ability;
import model.world.Champion;

public class SecondTransitPage extends JFrame implements ActionListener, ItemListener{
    ArrayList<Champion> ChoosenChampions = new ArrayList<Champion>();
    int i = 0;
    JCheckBox ironmanCheck;
    JCheckBox captainAmericaCheck;
    JCheckBox drStrangeCheck;
    JCheckBox deadpoolCheck;
    JCheckBox electroCheck;
    JCheckBox GhostRiderCheck;
    JCheckBox HelaCheck;
    JCheckBox HulkCheck;
    JCheckBox IcemanCheck;
    JCheckBox LokiCheck;
    JCheckBox QuickSilverCheck;
    JCheckBox SpidermanCheck;
    JCheckBox ThorCheck;
    JCheckBox VenomCheck;
    JCheckBox YellowJacketCheck;
    String secondPlayerName;
    Player firstPlayer;
    moreInfoButton deadpoolinfo;
    moreInfoButton ironmaninfo;
    moreInfoButton captainAmericainfo;
    moreInfoButton drStrangeinfo;
    moreInfoButton GhostRiderinfo;
    moreInfoButton electroinfo;
    moreInfoButton Helainfo;
    moreInfoButton Hulkinfo;
    moreInfoButton Icemaninfo;
    moreInfoButton Lokiinfo;
    moreInfoButton QuickSilverinfo;
    moreInfoButton Spidermaninfo;
    moreInfoButton Thorinfo;
    moreInfoButton Venominfo;
    moreInfoButton YellowJacketinfo;
    public SecondTransitPage(String secondPlayerName, Player firstPlayer) throws IOException {
        this.setLayout(null);
        this.secondPlayerName = secondPlayerName;
        this.firstPlayer = firstPlayer;
        ///////////////// Iron man start ////////////////////
        ironmanCheck = new JCheckBox("Iron man");
        ironmanCheck.setFocusable(false);
        ironmanCheck.addActionListener(this);
        ironmanCheck.addItemListener(this);
        ironmanCheck.setBackground(Color.white);
        if (checkChampionAvailable("Ironman"))
            ironmanCheck.setEnabled(false);
        JPanel ironmanP = new JPanel();
        ironmanP.setLayout(null);
        ironmanCheck.setBounds(5,0,100,30);
        ironmanP.setBackground(Color.white);
        ImageIcon ironmanPhoto = new ImageIcon("Resources/ironman.png");
        JLabel ironmanLabel = new JLabel(ironmanPhoto);
        ironmanLabel.setBounds(0,2,140,204);
        ironmanP.add(ironmanCheck);
        ironmanP.add(ironmanLabel);
        ironmanP.setBounds(100,100,140,254);
        ironmaninfo = new moreInfoButton();
        ironmanP.add(ironmaninfo);
        ironmaninfo.addActionListener(this);
        ///////////////// Iron man end //////////////////
        ///////////////// captain america start /////////////
        captainAmericaCheck = new JCheckBox("Captain America");
        captainAmericaCheck.setFocusable(false);
        captainAmericaCheck.addActionListener(this);
        captainAmericaCheck.addItemListener(this);
        captainAmericaCheck.setBackground(Color.white);
        if (checkChampionAvailable("Captain America"))
            captainAmericaCheck.setEnabled(false);
        JPanel captainAmericaP = new JPanel();
        captainAmericaP.setLayout(null);
        captainAmericaCheck.setBounds(5,0,150,30);
        captainAmericaP.setBackground(Color.white);
        ImageIcon captainAmericaPhoto = new ImageIcon("Resources/captainAmerica.png");
        JLabel captainAmericaLabel = new JLabel(captainAmericaPhoto);
        captainAmericaLabel.setBounds(0,2,140,204);
        captainAmericaLabel.setBackground(Color.white);
        captainAmericaP.add(captainAmericaCheck);
        captainAmericaP.add(captainAmericaLabel);
        captainAmericaP.setBounds(260,100,140,254);
        captainAmericainfo = new moreInfoButton();
        captainAmericaP.add(captainAmericainfo);
        captainAmericainfo.addActionListener(this);
        /////////////// captain america end //////////////
        ///////////////// Dr strange start /////////////
        drStrangeCheck = new JCheckBox("Dr Strange");
        drStrangeCheck.setFocusable(false);
        drStrangeCheck.addActionListener(this);
        drStrangeCheck.addItemListener(this);
        if (checkChampionAvailable("Dr Strange"))
            drStrangeCheck.setEnabled(false);
        drStrangeCheck.setBackground(Color.white);
        JPanel drStrangeP = new JPanel();
        drStrangeP.setLayout(null);
        drStrangeCheck.setBounds(5,0,150,30);
        drStrangeP.setBackground(Color.white);
        ImageIcon drStrangePhoto = new ImageIcon("Resources/drStrange.jpg");
        JLabel drStrangeLabel = new JLabel(drStrangePhoto);
        drStrangeLabel.setBounds(0,-10,140,204);
        drStrangeLabel.setBackground(Color.white);
        drStrangeP.add(drStrangeCheck);
        drStrangeP.add(drStrangeLabel);
        drStrangeP.setBounds(450,100,140,254);
        drStrangeinfo = new moreInfoButton();
        drStrangeP.add(drStrangeinfo);
        drStrangeinfo.addActionListener(this);
        /////////////// Dr strange end //////////////
        ///////////////// Deadpool start /////////////
        deadpoolCheck = new JCheckBox("Deadpool");
        deadpoolCheck.setFocusable(false);
        deadpoolCheck.addActionListener(this);
        deadpoolCheck.addItemListener(this);
        if (checkChampionAvailable("Deadpool"))
            deadpoolCheck.setEnabled(false);
        deadpoolCheck.setBackground(Color.white);
        JPanel deadpoolP = new JPanel();
        deadpoolP.setLayout(null);
        deadpoolCheck.setBounds(5,0,150,30);
        deadpoolP.setBackground(Color.white);
        ImageIcon deadpoolPhoto = new ImageIcon("Resources/deadpool.jpg");
        JLabel deadpoolLabel = new JLabel(deadpoolPhoto);
        deadpoolLabel.setBounds(0,8,140,204);
        deadpoolLabel.setBackground(Color.white);
        deadpoolP.add(deadpoolCheck);
        deadpoolP.add(deadpoolLabel);
        deadpoolP.setBounds(640,100,140,254);
        deadpoolinfo = new moreInfoButton();
        deadpoolP.add(deadpoolinfo);
        deadpoolinfo.addActionListener(this);
        /////////////// Deadpool end //////////////
        ///////////////// Electro start /////////////
        electroCheck = new JCheckBox("Electro");
        electroCheck.setFocusable(false);
        electroCheck.addActionListener(this);
        electroCheck.addItemListener(this);
        if (checkChampionAvailable("Electro"))
            electroCheck.setEnabled(false);
        electroCheck.setBackground(Color.white);
        JPanel electroP = new JPanel();
        electroP.setLayout(null);
        electroCheck.setBounds(5,0,150,30);
        electroP.setBackground(Color.white);
        ImageIcon electroPhoto = new ImageIcon("Resources/electro.jpg");
        JLabel electroLabel = new JLabel(electroPhoto);
        electroLabel.setBounds(0,5,140,204);
        electroLabel.setBackground(Color.white);
        electroP.add(electroCheck);
        electroP.add(electroLabel);
        electroP.setBounds(820,100,140,254);
        electroinfo = new moreInfoButton();
        electroP.add(electroinfo);
        electroinfo.addActionListener(this);
        /////////////// Electro end //////////////
        ///////////////// GhostRider start /////////////
        GhostRiderCheck = new JCheckBox("GhostRider");
        GhostRiderCheck.setFocusable(false);
        GhostRiderCheck.addActionListener(this);
        GhostRiderCheck.addItemListener(this);
        if (checkChampionAvailable("Ghost Rider"))
            GhostRiderCheck.setEnabled(false);
        GhostRiderCheck.setBackground(Color.white);
        JPanel GhostRiderP = new JPanel();
        GhostRiderP.setLayout(null);
        GhostRiderCheck.setBounds(5,0,150,30);
        GhostRiderP.setBackground(Color.white);
        ImageIcon GhostRiderPhoto = new ImageIcon("Resources/Ghost-Rider.png");
        JLabel GhostRiderLabel = new JLabel(GhostRiderPhoto);
        GhostRiderLabel.setBounds(15,5,140,204);
        GhostRiderLabel.setBackground(Color.white);
        GhostRiderP.add(GhostRiderCheck);
        GhostRiderP.add(GhostRiderLabel);
        GhostRiderP.setBounds(1000,100,140,254);
        GhostRiderinfo = new moreInfoButton();
        GhostRiderP.add(GhostRiderinfo);
        GhostRiderinfo.addActionListener(this);
        /////////////// GhostRider end //////////////
        ///////////////// Hela start /////////////
        HelaCheck = new JCheckBox("Hela");
        HelaCheck.setFocusable(false);
        HelaCheck.addActionListener(this);
        HelaCheck.addItemListener(this);
        if (checkChampionAvailable("Hela"))
            HelaCheck.setEnabled(false);
        HelaCheck.setBackground(Color.white);
        JPanel HelaP = new JPanel();
        HelaP.setLayout(null);
        HelaCheck.setBounds(5,0,150,30);
        HelaP.setBackground(Color.white);
        ImageIcon HelaPhoto = new ImageIcon("Resources/hela.jpg");
        JLabel HelaLabel = new JLabel(HelaPhoto);
        HelaLabel.setBounds(5,5,140,204);
        HelaLabel.setBackground(Color.white);
        HelaP.add(HelaCheck);
        HelaP.add(HelaLabel);
        HelaP.setBounds(1180,100,140,254);
        Helainfo = new moreInfoButton();
        HelaP.add(Helainfo);
        Helainfo.addActionListener(this);
        /////////////// Hela end //////////////
        ///////////////// Hulk start /////////////
        HulkCheck = new JCheckBox("Hulk");
        HulkCheck.setFocusable(false);
        HulkCheck.addActionListener(this);
        HulkCheck.addItemListener(this);
        if (checkChampionAvailable("Hulk"))
            HulkCheck.setEnabled(false);
        HulkCheck.setBackground(Color.white);
        JPanel HulkP = new JPanel();
        HulkP.setLayout(null);
        HulkCheck.setBounds(5,0,150,30);
        HulkP.setBackground(Color.white);
        ImageIcon HulkPhoto = new ImageIcon("Resources/hulk.png");
        JLabel HulkLabel = new JLabel(HulkPhoto);
        HulkLabel.setBounds(0,5,140,204);
        HulkLabel.setBackground(Color.white);
        HulkP.add(HulkCheck);
        HulkP.add(HulkLabel);
        HulkP.setBounds(1350,100,140,254);
        Hulkinfo = new moreInfoButton();
        HulkP.add(Hulkinfo);
        Hulkinfo.addActionListener(this);
        /////////////// Hulk end //////////////
        ///////////////// Iceman start /////////////
        IcemanCheck = new JCheckBox("Iceman");
        IcemanCheck.setFocusable(false);
        IcemanCheck.addActionListener(this);
        IcemanCheck.addItemListener(this);
        if (checkChampionAvailable("Iceman"))
            IcemanCheck.setEnabled(false);
        IcemanCheck.setBackground(Color.white);
        JPanel IcemanP = new JPanel();
        IcemanP.setLayout(null);
        IcemanCheck.setBounds(5,0,150,30);
        IcemanP.setBackground(Color.white);
        ImageIcon IcemanPhoto = new ImageIcon("Resources/iceman.png");
        JLabel IcemanLabel = new JLabel(IcemanPhoto);
        IcemanLabel.setBounds(0,5,140,204);
        IcemanLabel.setBackground(Color.white);
        IcemanP.add(IcemanCheck);
        IcemanP.add(IcemanLabel);
        IcemanP.setBounds(100,450,140,254);
        Icemaninfo = new moreInfoButton();
        IcemanP.add(Icemaninfo);
        Icemaninfo.addActionListener(this);
        /////////////// Iceman end //////////////
        ///////////////// Loki start /////////////
        LokiCheck = new JCheckBox("Loki");
        LokiCheck.setFocusable(false);
        LokiCheck.addActionListener(this);
        LokiCheck.addItemListener(this);
        if (checkChampionAvailable("Loki"))
            LokiCheck.setEnabled(false);
        LokiCheck.setBackground(Color.white);
        JPanel LokiP = new JPanel();
        LokiP.setLayout(null);
        LokiCheck.setBounds(5,0,150,30);
        LokiP.setBackground(Color.white);
        ImageIcon LokiPhoto = new ImageIcon("Resources/loki.png");
        JLabel LokiLabel = new JLabel(LokiPhoto);
        LokiLabel.setBounds(0,5,140,204);
        LokiLabel.setBackground(Color.white);
        LokiP.add(LokiCheck);
        LokiP.add(LokiLabel);
        LokiP.setBounds(280,450,140,254);
        Lokiinfo = new moreInfoButton();
        LokiP.add(Lokiinfo);
        Lokiinfo.addActionListener(this);
        /////////////// Loki end //////////////
        ///////////////// QuickSilver start /////////////
        QuickSilverCheck = new JCheckBox("QuickSilver");
        QuickSilverCheck.setFocusable(false);
        QuickSilverCheck.addActionListener(this);
        QuickSilverCheck.addItemListener(this);
        if (checkChampionAvailable("Quicksilver"))
            QuickSilverCheck.setEnabled(false);
        QuickSilverCheck.setBackground(Color.white);
        JPanel QuickSilverP = new JPanel();
        QuickSilverP.setLayout(null);
        QuickSilverCheck.setBounds(5,0,150,30);
        QuickSilverP.setBackground(Color.white);
        ImageIcon QuickSilverPhoto = new ImageIcon("Resources/quicksilver.png");
        JLabel QuickSilverLabel = new JLabel(QuickSilverPhoto);
        QuickSilverLabel.setBounds(0,5,140,204);
        QuickSilverLabel.setBackground(Color.white);
        QuickSilverP.add(QuickSilverCheck);
        QuickSilverP.add(QuickSilverLabel);
        QuickSilverP.setBounds(450,450,140,254);
        QuickSilverinfo = new moreInfoButton();
        QuickSilverP.add(QuickSilverinfo);
        QuickSilverinfo.addActionListener(this);
        /////////////// QuickSilver end //////////////
        ///////////////// Spiderman start /////////////
        SpidermanCheck = new JCheckBox("Spiderman");
        SpidermanCheck.setFocusable(false);
        SpidermanCheck.addActionListener(this);
        SpidermanCheck.addItemListener(this);
        if (checkChampionAvailable("Spiderman"))
            SpidermanCheck.setEnabled(false);
        SpidermanCheck.setBackground(Color.white);
        JPanel SpidermanP = new JPanel();
        SpidermanP.setLayout(null);
        SpidermanCheck.setBounds(5,0,150,30);
        SpidermanP.setBackground(Color.white);
        ImageIcon SpidermanPhoto = new ImageIcon("Resources/Spiderman.png");
        JLabel SpidermanLabel = new JLabel(SpidermanPhoto);
        SpidermanLabel.setBounds(0,5,140,204);
        SpidermanLabel.setBackground(Color.white);
        SpidermanP.add(SpidermanCheck);
        SpidermanP.add(SpidermanLabel);
        SpidermanP.setBounds(630,450,140,254);
        Spidermaninfo = new moreInfoButton();
        SpidermanP.add(Spidermaninfo);
        Spidermaninfo.addActionListener(this);
        /////////////// Spiderman end //////////////
        ///////////////// Thor start /////////////
        ThorCheck = new JCheckBox("Thor");
        ThorCheck.setFocusable(false);
        ThorCheck.addActionListener(this);
        ThorCheck.addItemListener(this);
        if (checkChampionAvailable("Thor"))
            ThorCheck.setEnabled(false);
        ThorCheck.setBackground(Color.white);
        JPanel ThorP = new JPanel();
        ThorP.setLayout(null);
        ThorCheck.setBounds(5,0,150,30);
        ThorP.setBackground(Color.white);
        ImageIcon ThorPhoto = new ImageIcon("Resources/Thor.jpg");
        JLabel ThorLabel = new JLabel(ThorPhoto);
        ThorLabel.setBounds(0,5,140,204);
        ThorLabel.setBackground(Color.white);
        ThorP.add(ThorCheck);
        ThorP.add(ThorLabel);
        ThorP.setBounds(810,450,140,254);
        Thorinfo = new moreInfoButton();
        ThorP.add(Thorinfo);
        Thorinfo.addActionListener(this);
        /////////////// Thor end //////////////
        ///////////////// Venom start /////////////
        VenomCheck = new JCheckBox("Venom");
        VenomCheck.setFocusable(false);
        VenomCheck.addActionListener(this);
        VenomCheck.addItemListener(this);
        if (checkChampionAvailable("Venom"))
            VenomCheck.setEnabled(false);
        VenomCheck.setBackground(Color.white);
        JPanel VenomP = new JPanel();
        VenomP.setLayout(null);
        VenomCheck.setBounds(5,0,150,30);
        VenomP.setBackground(Color.white);
        ImageIcon VenomPhoto = new ImageIcon("Resources/venom.png");
        JLabel VenomLabel = new JLabel(VenomPhoto);
        VenomLabel.setBounds(0,5,140,204);
        VenomLabel.setBackground(Color.white);
        VenomP.add(VenomCheck);
        VenomP.add(VenomLabel);
        VenomP.setBounds(1000,450,140,254);
        Venominfo = new moreInfoButton();
        VenomP.add(Venominfo);
        Venominfo.addActionListener(this);
        /////////////// Venom end //////////////
        ///////////////// Yellowjacket start /////////////
        YellowJacketCheck = new JCheckBox("YellowJacket");
        YellowJacketCheck.setFocusable(false);
        YellowJacketCheck.addActionListener(this);
        YellowJacketCheck.addItemListener(this);
        if (checkChampionAvailable("Yellow Jacket"))
            YellowJacketCheck.setEnabled(false);
        YellowJacketCheck.setBackground(Color.white);
        JPanel YellowJacketP = new JPanel();
        YellowJacketP.setLayout(null);
        YellowJacketCheck.setBounds(5,0,150,30);
        YellowJacketP.setBackground(Color.white);
        ImageIcon YellowJacketPhoto = new ImageIcon("Resources/YellowJacket.jpg");
        JLabel YellowJacketLabel = new JLabel(YellowJacketPhoto);
        YellowJacketLabel.setBounds(-8,10,140,204);
        YellowJacketLabel.setBackground(Color.white);
        YellowJacketP.add(YellowJacketCheck);
        YellowJacketP.add(YellowJacketLabel);
        YellowJacketP.setBounds(1190,450,140,254);
        YellowJacketinfo = new moreInfoButton();
        YellowJacketP.add(YellowJacketinfo);
        YellowJacketinfo.addActionListener(this);
        /////////////// YellowJacket end //////////////

        JLabel welcome = new JLabel("<html><p>Hello " + secondPlayerName + ", Please choose 3 champions to start with");
        welcome.setFont(new Font(Font.MONOSPACED, Font.BOLD, 30));
        welcome.setBounds(100,0,1500,100);
        int in = 0;
        while (Game.getAvailableAbilities().size() != 0)
            Game.getAvailableAbilities().remove(in);
        while (Game.getAvailableChampions().size() != 0)
            Game.getAvailableChampions().remove(in);
        Game.loadAbilities("Resources/Abilities.csv");
        Game.loadChampions("Resources/Champions.csv");
        this.getContentPane().setBackground(Color.white);
        this.add(welcome);
        this.add(ironmanP);
        this.add(captainAmericaP);
        this.add(drStrangeP);
        this.add(deadpoolP);
        this.add(GhostRiderP);
        this.add(electroP);
        this.add(HelaP);
        this.add(HulkP);
        this.add(IcemanP);
        this.add(LokiP);
        this.add(QuickSilverP);
        this.add(SpidermanP);
        this.add(ThorP);
        this.add(VenomP);
        this.add(YellowJacketP);
        this.setExtendedState(this.MAXIMIZED_BOTH);
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.setVisible(true);

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == deadpoolinfo) {
            int x = this.searchChampionByName("Deadpool");
            Champion c = Game.getAvailableChampions().get(x);
            String s = "Type : AntiHero \nName : " + c.getName()
                    + "\nMaxHP : " + c.getMaxHP() + "\nMax Mana : " +
                    c.getMana() + "\nMax Action Points : " + c.getMaxActionPointsPerTurn()
                    + "\nSpeed : " + c.getSpeed() + "\nAttack Range : " + c.getAttackRange()
                    + "\nAttack Damage : " + c.getAttackDamage()
                    + "\nFirst Ability : " + c.getAbilities().get(0).getName()
                    + "\nSecond Ability : " + c.getAbilities().get(1).getName()
                    + "\nThird Ability : " + c.getAbilities().get(2).getName();
            JOptionPane.showMessageDialog(null,s,"info",JOptionPane.PLAIN_MESSAGE);
        }
        if (e.getSource() == drStrangeinfo) {
            int x = this.searchChampionByName("Dr Strange");
            Champion c = Game.getAvailableChampions().get(x);
            String s = "Type : Hero \nName : " + c.getName()
                    + "\nMaxHP : " + c.getMaxHP() + "\nMax Mana : " +
                    c.getMana() + "\nMax Action Points : " + c.getMaxActionPointsPerTurn()
                    + "\nSpeed : " + c.getSpeed() + "\nAttack Range : " + c.getAttackRange()
                    + "\nAttack Damage : " + c.getAttackDamage()
                    + "\nFirst Ability : " + c.getAbilities().get(0).getName()
                    + "\nSecond Ability : " + c.getAbilities().get(1).getName()
                    + "\nThird Ability : " + c.getAbilities().get(2).getName();;
            JOptionPane.showMessageDialog(null,s,"info",JOptionPane.PLAIN_MESSAGE);
        }
        if (e.getSource() == captainAmericainfo) {
            int x = this.searchChampionByName("Captain America");
            Champion c = Game.getAvailableChampions().get(x);
            String s = "Type : Hero \nName : " + c.getName()
                    + "\nMaxHP : " + c.getMaxHP() + "\nMax Mana : " +
                    c.getMana() + "\nMax Action Points : " + c.getMaxActionPointsPerTurn()
                    + "\nSpeed : " + c.getSpeed() + "\nAttack Range : " + c.getAttackRange()
                    + "\nAttack Damage : " + c.getAttackDamage()
                    + "\nFirst Ability : " + c.getAbilities().get(0).getName()
                    + "\nSecond Ability : " + c.getAbilities().get(1).getName()
                    + "\nThird Ability : " + c.getAbilities().get(2).getName();;
            JOptionPane.showMessageDialog(null,s,"info",JOptionPane.PLAIN_MESSAGE);
        }
        if (e.getSource() == ironmaninfo) {
            int x = this.searchChampionByName("Ironman");
            Champion c = Game.getAvailableChampions().get(x);
            String s = "Type : Hero \nName : " + c.getName()
                    + "\nMaxHP : " + c.getMaxHP() + "\nMax Mana : " +
                    c.getMana() + "\nMax Action Points : " + c.getMaxActionPointsPerTurn()
                    + "\nSpeed : " + c.getSpeed() + "\nAttack Range : " + c.getAttackRange()
                    + "\nAttack Damage : " + c.getAttackDamage()
                    + "\nFirst Ability : " + c.getAbilities().get(0).getName()
                    + "\nSecond Ability : " + c.getAbilities().get(1).getName()
                    + "\nThird Ability : " + c.getAbilities().get(2).getName();;
            JOptionPane.showMessageDialog(null,s,"info",JOptionPane.PLAIN_MESSAGE);
        }
        if (e.getSource() == electroinfo) {
            int x = this.searchChampionByName("Electro");
            Champion c = Game.getAvailableChampions().get(x);
            String s = "Type : Villain \nName : " + c.getName()
                    + "\nMaxHP : " + c.getMaxHP() + "\nMax Mana : " +
                    c.getMana() + "\nMax Action Points : " + c.getMaxActionPointsPerTurn()
                    + "\nSpeed : " + c.getSpeed() + "\nAttack Range : " + c.getAttackRange()
                    + "\nAttack Damage : " + c.getAttackDamage()
                    + "\nFirst Ability : " + c.getAbilities().get(0).getName()
                    + "\nSecond Ability : " + c.getAbilities().get(1).getName()
                    + "\nThird Ability : " + c.getAbilities().get(2).getName();;
            JOptionPane.showMessageDialog(null,s,"info",JOptionPane.PLAIN_MESSAGE);
        }
        if (e.getSource() == GhostRiderinfo) {
            int x = this.searchChampionByName("Ghost Rider");
            Champion c = Game.getAvailableChampions().get(x);
            String s = "Type : AntiHero \nName : " + c.getName()
                    + "\nMaxHP : " + c.getMaxHP() + "\nMax Mana : " +
                    c.getMana() + "\nMax Action Points : " + c.getMaxActionPointsPerTurn()
                    + "\nSpeed : " + c.getSpeed() + "\nAttack Range : " + c.getAttackRange()
                    + "\nAttack Damage : " + c.getAttackDamage()
                    + "\nFirst Ability : " + c.getAbilities().get(0).getName()
                    + "\nSecond Ability : " + c.getAbilities().get(1).getName()
                    + "\nThird Ability : " + c.getAbilities().get(2).getName();;
            JOptionPane.showMessageDialog(null,s,"info",JOptionPane.PLAIN_MESSAGE);
        }
        if (e.getSource() == Helainfo) {
            int x = this.searchChampionByName("Hela");
            Champion c = Game.getAvailableChampions().get(x);
            String s = "Type : Villain \nName : " + c.getName()
                    + "\nMaxHP : " + c.getMaxHP() + "\nMax Mana : " +
                    c.getMana() + "\nMax Action Points : " + c.getMaxActionPointsPerTurn()
                    + "\nSpeed : " + c.getSpeed() + "\nAttack Range : " + c.getAttackRange()
                    + "\nAttack Damage : " + c.getAttackDamage()
                    + "\nFirst Ability : " + c.getAbilities().get(0).getName()
                    + "\nSecond Ability : " + c.getAbilities().get(1).getName()
                    + "\nThird Ability : " + c.getAbilities().get(2).getName();;
            JOptionPane.showMessageDialog(null,s,"info",JOptionPane.PLAIN_MESSAGE);
        }
        if (e.getSource() == Hulkinfo) {
            int x = this.searchChampionByName("Hulk");
            Champion c = Game.getAvailableChampions().get(x);
            String s = "Type : Hero \nName : " + c.getName()
                    + "\nMaxHP : " + c.getMaxHP() + "\nMax Mana : " +
                    c.getMana() + "\nMax Action Points : " + c.getMaxActionPointsPerTurn()
                    + "\nSpeed : " + c.getSpeed() + "\nAttack Range : " + c.getAttackRange()
                    + "\nAttack Damage : " + c.getAttackDamage()
                    + "\nFirst Ability : " + c.getAbilities().get(0).getName()
                    + "\nSecond Ability : " + c.getAbilities().get(1).getName()
                    + "\nThird Ability : " + c.getAbilities().get(2).getName();;
            JOptionPane.showMessageDialog(null,s,"info",JOptionPane.PLAIN_MESSAGE);
        }
        if (e.getSource() == Icemaninfo) {
            int x = this.searchChampionByName("Iceman");
            Champion c = Game.getAvailableChampions().get(x);
            String s = "Type : Hero \nName : " + c.getName()
                    + "\nMaxHP : " + c.getMaxHP() + "\nMax Mana : " +
                    c.getMana() + "\nMax Action Points : " + c.getMaxActionPointsPerTurn()
                    + "\nSpeed : " + c.getSpeed() + "\nAttack Range : " + c.getAttackRange()
                    + "\nAttack Damage : " + c.getAttackDamage()
                    + "\nFirst Ability : " + c.getAbilities().get(0).getName()
                    + "\nSecond Ability : " + c.getAbilities().get(1).getName()
                    + "\nThird Ability : " + c.getAbilities().get(2).getName();;
            JOptionPane.showMessageDialog(null,s,"info",JOptionPane.PLAIN_MESSAGE);
        }
        if (e.getSource() == Lokiinfo) {
            int x = this.searchChampionByName("Loki");
            Champion c = Game.getAvailableChampions().get(x);
            String s = "Type : Villain \nName : " + c.getName()
                    + "\nMaxHP : " + c.getMaxHP() + "\nMax Mana : " +
                    c.getMana() + "\nMax Action Points : " + c.getMaxActionPointsPerTurn()
                    + "\nSpeed : " + c.getSpeed() + "\nAttack Range : " + c.getAttackRange()
                    + "\nAttack Damage : " + c.getAttackDamage()
                    + "\nFirst Ability : " + c.getAbilities().get(0).getName()
                    + "\nSecond Ability : " + c.getAbilities().get(1).getName()
                    + "\nThird Ability : " + c.getAbilities().get(2).getName();;
            JOptionPane.showMessageDialog(null,s,"info",JOptionPane.PLAIN_MESSAGE);
        }
        if (e.getSource() == QuickSilverinfo) {
            int x = this.searchChampionByName("Quicksilver");
            Champion c = Game.getAvailableChampions().get(x);
            String s = "Type : Villain \nName : " + c.getName()
                    + "\nMaxHP : " + c.getMaxHP() + "\nMax Mana : " +
                    c.getMana() + "\nMax Action Points : " + c.getMaxActionPointsPerTurn()
                    + "\nSpeed : " + c.getSpeed() + "\nAttack Range : " + c.getAttackRange()
                    + "\nAttack Damage : " + c.getAttackDamage()
                    + "\nFirst Ability : " + c.getAbilities().get(0).getName()
                    + "\nSecond Ability : " + c.getAbilities().get(1).getName()
                    + "\nThird Ability : " + c.getAbilities().get(2).getName();;
            JOptionPane.showMessageDialog(null,s,"info",JOptionPane.PLAIN_MESSAGE);
        }
        if (e.getSource() == Spidermaninfo) {
            int x = this.searchChampionByName("Spiderman");
            Champion c = Game.getAvailableChampions().get(x);
            String s = "Type : Hero \nName : " + c.getName()
                    + "\nMaxHP : " + c.getMaxHP() + "\nMax Mana : " +
                    c.getMana() + "\nMax Action Points : " + c.getMaxActionPointsPerTurn()
                    + "\nSpeed : " + c.getSpeed() + "\nAttack Range : " + c.getAttackRange()
                    + "\nAttack Damage : " + c.getAttackDamage()
                    + "\nFirst Ability : " + c.getAbilities().get(0).getName()
                    + "\nSecond Ability : " + c.getAbilities().get(1).getName()
                    + "\nThird Ability : " + c.getAbilities().get(2).getName();;
            JOptionPane.showMessageDialog(null,s,"info",JOptionPane.PLAIN_MESSAGE);
        }
        if (e.getSource() == Thorinfo) {
            int x = this.searchChampionByName("Thor");
            Champion c = Game.getAvailableChampions().get(x);
            String s = "Type : Hero \nName : " + c.getName()
                    + "\nMaxHP : " + c.getMaxHP() + "\nMax Mana : " +
                    c.getMana() + "\nMax Action Points : " + c.getMaxActionPointsPerTurn()
                    + "\nSpeed : " + c.getSpeed() + "\nAttack Range : " + c.getAttackRange()
                    + "\nAttack Damage : " + c.getAttackDamage()
                    + "\nFirst Ability : " + c.getAbilities().get(0).getName()
                    + "\nSecond Ability : " + c.getAbilities().get(1).getName()
                    + "\nThird Ability : " + c.getAbilities().get(2).getName();;
            JOptionPane.showMessageDialog(null,s,"info",JOptionPane.PLAIN_MESSAGE);
        }
        if (e.getSource() == Venominfo) {
            int x = this.searchChampionByName("Venom");
            Champion c = Game.getAvailableChampions().get(x);
            String s = "Type : AntiHero \nName : " + c.getName()
                    + "\nMaxHP : " + c.getMaxHP() + "\nMax Mana : " +
                    c.getMana() + "\nMax Action Points : " + c.getMaxActionPointsPerTurn()
                    + "\nSpeed : " + c.getSpeed() + "\nAttack Range : " + c.getAttackRange()
                    + "\nAttack Damage : " + c.getAttackDamage()
                    + "\nFirst Ability : " + c.getAbilities().get(0).getName()
                    + "\nSecond Ability : " + c.getAbilities().get(1).getName()
                    + "\nThird Ability : " + c.getAbilities().get(2).getName();;
            JOptionPane.showMessageDialog(null,s,"info",JOptionPane.PLAIN_MESSAGE);
        }
        if (e.getSource() == YellowJacketinfo) {
            int x = this.searchChampionByName("Yellow Jacket");
            Champion c = Game.getAvailableChampions().get(x);
            String s = "Type : Villain \nName : " + c.getName()
                    + "\nMaxHP : " + c.getMaxHP() + "\nMax Mana : " +
                    c.getMana() + "\nMax Action Points : " + c.getMaxActionPointsPerTurn()
                    + "\nSpeed : " + c.getSpeed() + "\nAttack Range : " + c.getAttackRange()
                    + "\nAttack Damage : " + c.getAttackDamage()
                    + "\nFirst Ability : " + c.getAbilities().get(0).getName()
                    + "\nSecond Ability : " + c.getAbilities().get(1).getName()
                    + "\nThird Ability : " + c.getAbilities().get(2).getName();;
            JOptionPane.showMessageDialog(null,s,"info",JOptionPane.PLAIN_MESSAGE);
        }
    }
    public int searchChampionByName(String name) {
        for (int i = 0 ; i<Game.getAvailableChampions().size() ; i++)
            if (Game.getAvailableChampions().get(i).getName().equals(name))
                return i;
        return -1;
    }
    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getSource() == ironmanCheck) {
            int x = searchChampionByName("Ironman");
            Champion c = Game.getAvailableChampions().get(x);
            if (ironmanCheck.isSelected()) {
                i++;
                ChoosenChampions.add(c);
            }
            else {
                i--;
                ChoosenChampions.remove(c);
            }
        }
        if (e.getSource() == captainAmericaCheck) {
            int x = searchChampionByName("Captain America");
            Champion c = Game.getAvailableChampions().get(x);
            if (captainAmericaCheck.isSelected()) {
                i++;
                ChoosenChampions.add(c);
            }
            else {
                i--;
                ChoosenChampions.remove(c);
            }
        }
        if (e.getSource() == drStrangeCheck) {
            int x = searchChampionByName("Dr Strange");
            Champion c = Game.getAvailableChampions().get(x);
            if (drStrangeCheck.isSelected()) {
                i++;
                ChoosenChampions.add(c);
            }
            else {
                i--;
                ChoosenChampions.remove(c);
            }
        }
        if (e.getSource() == deadpoolCheck) {
            int x = searchChampionByName("Deadpool");
            Champion c = Game.getAvailableChampions().get(x);
            if (deadpoolCheck.isSelected()) {
                i++;
                ChoosenChampions.add(c);
            }
            else {
                i--;
                ChoosenChampions.remove(c);
            }
        }
        if (e.getSource() == electroCheck) {
            int x = searchChampionByName("Electro");
            Champion c = Game.getAvailableChampions().get(x);
            if (electroCheck.isSelected()) {
                i++;
                ChoosenChampions.add(c);
            }
            else {
                i--;
                ChoosenChampions.remove(c);
            }
        }
        if (e.getSource() == GhostRiderCheck) {
            int x = searchChampionByName("Ghost Rider");
            Champion c = Game.getAvailableChampions().get(x);
            if (GhostRiderCheck.isSelected()) {
                i++;
                ChoosenChampions.add(c);
            }
            else {
                i--;
                ChoosenChampions.remove(c);
            }
        }
        if (e.getSource() == HelaCheck) {
            int x = searchChampionByName("Hela");
            Champion c = Game.getAvailableChampions().get(x);
            if (HelaCheck.isSelected()) {
                i++;
                ChoosenChampions.add(c);
            }
            else {
                i--;
                ChoosenChampions.remove(c);
            }
        }
        if (e.getSource() == HulkCheck) {
            int x = searchChampionByName("Hulk");
            Champion c = Game.getAvailableChampions().get(x);
            if (HulkCheck.isSelected()) {
                i++;
                ChoosenChampions.add(c);
            }
            else {
                i--;
                ChoosenChampions.remove(c);
            }
        }
        if (e.getSource() == IcemanCheck) {
            int x = searchChampionByName("Iceman");
            Champion c = Game.getAvailableChampions().get(x);
            if (IcemanCheck.isSelected()) {
                i++;
                ChoosenChampions.add(c);
            }
            else {
                i--;
                ChoosenChampions.remove(c);
            }
        }
        if (e.getSource() == LokiCheck) {
            int x = searchChampionByName("Loki");
            Champion c = Game.getAvailableChampions().get(x);
            if (LokiCheck.isSelected()) {
                i++;
                ChoosenChampions.add(c);
            }
            else {
                i--;
                ChoosenChampions.remove(c);
            }
        }
        if (e.getSource() == QuickSilverCheck) {
            int x = searchChampionByName("Quicksilver");
            Champion c = Game.getAvailableChampions().get(x);
            if (QuickSilverCheck.isSelected()) {
                i++;
                ChoosenChampions.add(c);
            }
            else {
                i--;
                ChoosenChampions.remove(c);
            }
        }
        if (e.getSource() == SpidermanCheck) {
            int x = searchChampionByName("Spiderman");
            Champion c = Game.getAvailableChampions().get(x);
            if (SpidermanCheck.isSelected()) {
                i++;
                ChoosenChampions.add(c);
            }
            else {
                i--;
                ChoosenChampions.remove(c);
            }
        }
        if (e.getSource() == ThorCheck) {
            int x = searchChampionByName("Thor");
            Champion c = Game.getAvailableChampions().get(x);
            if (ThorCheck.isSelected()) {
                i++;
                ChoosenChampions.add(c);
            }
            else {
                i--;
                ChoosenChampions.remove(c);
            }
        }
        if (e.getSource() == VenomCheck) {
            int x = searchChampionByName("Venom");
            Champion c = Game.getAvailableChampions().get(x);
            if (VenomCheck.isSelected()) {
                i++;
                ChoosenChampions.add(c);
            }
            else {
                i--;
                ChoosenChampions.remove(c);
            }
        }
        if (e.getSource() == YellowJacketCheck) {
            int x = searchChampionByName("Yellow Jacket");
            Champion c = Game.getAvailableChampions().get(x);
            if (YellowJacketCheck.isSelected()) {
                i++;
                ChoosenChampions.add(c);
            }
            else {
                i--;
                ChoosenChampions.remove(c);
            }
        }
        if (i == 3) {
            Player second = new Player(this.secondPlayerName);
            String[] options = new String[3];
            int index = 0;
            for (Champion x : ChoosenChampions) {
                second.getTeam().add(x);
                options[index] = x.getName();
                index++;
            }
            int indexOfLeader = JOptionPane.showOptionDialog(null, "Please select your leader",
                    "Leader selection",
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE, null,
                    options, 0);
            second.setLeader(second.getTeam().get(indexOfLeader));
//			String secondPlayerName = JOptionPane.showInputDialog("Dear Player2, What should we call you with?");
            Game game = new Game(this.firstPlayer , second);
            StartedGame g = new StartedGame(game);
            this.dispose();
        }
    }
    public boolean checkChampionAvailable(String s) {
        for (Champion x : firstPlayer.getTeam())
            if (x.getName().equals(s))
                return true;
        return false;
    }
}
