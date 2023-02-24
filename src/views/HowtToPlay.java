package views;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;

public class HowtToPlay extends JFrame implements ActionListener {
        JButton backToMain = new JButton("Return");
    public HowtToPlay() {
//		JPanel container = new JPanel();
        backToMain.addActionListener(this);
        backToMain.setBounds(0,0,200,50);
        backToMain.setForeground(Color.white);
        backToMain.setBackground(new Color(0x373F51));
        backToMain.setFocusable(false);
        JTextArea text = new JTextArea("Marvel: Ultimate War is a 2 player battle game. Each player picks 3 champions to form his team\r\n"
                + "and fight the other player’s team. The players take turns to fight the other player’s champions.\r\n"
                + "The turns will keep going back and forth until a player is able to defeat all of the other player’s\r\n"
                + "champions which will make him the winner of the battle.\r\n"
                + "During the battle, each player will use his champions to attack the opponent champions either\r\n"
                + "by using normal attacks or using special attacks/abilities. The battle takes place on a 5x5 grid.\r\n"
                + "Each cell in the grid can either be empty, or contain a champion or obstacle/cover. At the\r\n"
                + "beginning of the battle, each team will stand at one of the sides/edges of the grid as a starting\r\n"
                + "position.\r\n"
                + "Champions\r\n"
                + "Champions are the fighters that each player will form his team from. Each champion will have\r\n"
                + "a certain type which influences how the champion deals damage to other types as well as how\r\n"
                + "much damage it will receive from them. The available types are:-\r\n"
                + "• Heroes: they deal extra damage when attacking villains.\r\n"
                + "• Villains: they deal extra damage when attacking heroes.\r\n"
                + "• Anti-Heroes: when being attacked or attacking a hero or villain, the antihero will always\r\n"
                + "act as the opposite type. If attacking an antihero, damage is calculated normally.\r\n"
                + "The available champions along with their corresponding type:-\r\n"
                + "Champion Type Champion Type Champion Type\r\n"
                + "Captain America Hero Deadpool Anti-Hero Dr Strange Hero\r\n"
                + "Electro Villain Ghost Rider Anti-Hero Hela Villain\r\n"
                + "Hulk Hero Iceman Hero Ironman Hero\r\n"
                + "Loki Villain Quicksilver Villain Spiderman Hero\r\n"
                + "Thor Hero Venom Anti-Hero Yellow Jacket Villain\r\n"
                + "Each champion has the following attributes and characteristics:-\r\n"
                + "• Health points: Represents the life of the champion. As long as the value of this attribute\r\n"
                + "is bigger than zero, the champion will remain alive and can act in the game. Once the\r\n"
                + "value of this attribute reaches zero, the champion is considered dead and hence, eliminated\r\n"
                + "from the fight.\r\n"
                + "• Mana: a resource that a champion uses to use his abilities. Each time a player uses an\r\n"
                + "ability, a certain amount of mana will be consumed. Once run out, the champion cannot\r\n"
                + "use any of his abilities.\r\n"
                + "• Normal attack damage: The amount of damage that the champion will inflict upon\r\n"
                + "the attacked champion while using a normal attack. This amount will be deducted from\r\n"
                + "the attacked champion’s health points.\r\n"
                + "• Normal attack range: The maximum number of cells that the attacker’s normal attack\r\n"
                + "can reach the attacked champion within. If the attacked champion is standing in distance\r\n"
                + "greater than this range, the attacker can not use a normal attack on him.\r\n"
                + "– Range is calculated by the Manhattan distance algorithm.\r\n"
                + "• Speed: Determines how fast the champion is. The faster the champion, the sooner he\r\n"
                + "can carry out his actions before other champions.\r\n"
                + "• Condition: Represents the current ability/inability of the champion to act. The champion can be active (can do some actions), inactive (can not do any actions until he is back\r\n"
                + "to active), or knocked out (defeated and can not do any action till the end of the game).\r\n"
                + "• Actions per turn: A number representing how many actions a player can do with the\r\n"
                + "champion during each of his turns. Each action will consume a certain amount of this\r\n"
                + "number. Once it reaches zero, no more actions can be done by this champion during this\r\n"
                + "turn. This attribute resets each time the turn goes to the champion.\r\n"
                + "Possible actions that can be done by a champion during his turn:\r\n"
                + "2\r\n"
                + "– Move to an empty cell.\r\n"
                + "– Do a normal attack.\r\n"
                + "– Cast an ability.\r\n"
                + "– Use Leader Ability (only if champion is the player’s chosen leader)\r\n"
                + "Abilities\r\n"
                + "These are special attacks that a champion can use. They are categorized under the following\r\n"
                + "categories:-\r\n"
                + "• Damaging abilities: Abilities that deal damage to the opponent champion(s) or covers.\r\n"
                + "• Healing abilities: Abilities that restore health points to friendly champion(s).\r\n"
                + "• Effect abilities: Abilities that can empower or weaken their targets by applying different\r\n"
                + "effects. These effects can last for multiple turns and will affect how the affected champion\r\n"
                + "interacts or reacts to abilities or attacks.\r\n"
                + "Example of some effects: stun, weaken, embrace, shield, silence, disarm.\r\n"
                + "Abilities have different targets and ranges. Some abilities are single target abilities which\r\n"
                + "affect only a single champion (or a cover in some cases) per use. Or can affect any champion\r\n"
                + "standing in a certain area (area of effect). These areas can be directional (Horizontal or\r\n"
                + "Vertical), or Circuilar (affect an area surrounding a central point). Finally, some abilities\r\n"
                + "can affect all friendly or opposing champions.\r\n"
                + "Each ability requires a certain amount of action points to be present in the champion\r\n"
                + "casting them as well as some mana. Also, each ability has a specific range of cells that\r\n"
                + "the target needs to be present in it in order for the ability to affect it.\r\n"
                + "Leader Abilities\r\n"
                + "At the beginning of the battle, each player promotes one of his champions to be the leader of\r\n"
                + "his team. The leader will then receive a special ability based on his type that can be used only\r\n"
                + "once per battle.\r\n"
                + "3\r\n"
                + "Gameplay Flow\r\n"
                + "Each player will select his three champions to form his team. The champions will take turns\r\n"
                + "based on their speed. The champion with the highest speed (from all selected champions) will\r\n"
                + "begin acting first followed by the champion with the second highest speed and so on. When\r\n"
                + "the turn goes to a champion, the player controlling the champion can use him to carry out any\r\n"
                + "action as long as the champion has enough action points needed for this action and also enough\r\n"
                + "mana in case of using any of his abilities. After that, the champion can end his turn and the\r\n"
                + "turn will go to the next champion.\r\n"
                + "The turns will keep passing over the living champions till a player is able to defeat all of the\r\n"
                + "three champions of the opponent player. In this case, the game ends and the player with the\r\n"
                + "living champion will be declared the winner.");
        JScrollPane s = new JScrollPane(text,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        text.setLineWrap(true);
//		text.setEditable(false);
        text.setFont(new Font(Font.DIALOG,  Font.BOLD, 30));
        text.setSize(new Dimension(1195,550));
//		container.add(text);
        JPanel container = new JPanel();
        this.setLayout(null);
        this.getContentPane().add(s);
        s.setBounds(0,50,1500,800);
        this.getContentPane().setBackground(Color.white);
        this.add(backToMain);
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setExtendedState(this.MAXIMIZED_BOTH);
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
