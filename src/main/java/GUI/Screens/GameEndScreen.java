package GUI.Screens;

import javax.swing.*;
import java.awt.*;
import GUI.Display;
import Utils.Coordinate;
import Utils.Props;

/**
 * Screen displayed upon game ending
 */
public class GameEndScreen extends Screen {
    /**
     * @return frame size for render
     */
    public Coordinate getFrameSize() {
        return new Coordinate(Props.MAP_SIZE * Props.RECT_SIZE, Props.MAP_SIZE * Props.RECT_SIZE);
    }

    /**
     * @param display window used to display
     * @param winner -1 for losing the game, otherwise the id of the player
     */
    public GameEndScreen(Display display, int winner) {
        this.setLayout(new GridBagLayout());

        JLabel winnerText = new JLabel();
        String winOrLoseText = winner != -1 ? "Congratulations! You beat the game! The winner is: player " + winner : "Game Over";
        winnerText.setText(winOrLoseText);
        winnerText.setHorizontalAlignment(SwingConstants.CENTER);
        winnerText.setFont(new Font("Serig", Font.PLAIN, 15));
        winnerText.setForeground(this.getPlayerColor(winner));

        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        c.ipady = 15;
        c.ipadx = 15;
        c.gridwidth = 3;
        this.add(winnerText, c);

        JButton continueBtn = new JButton("Continue");
        continueBtn.addActionListener(e -> display.ReloadScreen(new HomeScreen(display)));

        JButton exit = new JButton("Exit");
        exit.addActionListener(e -> System.exit(0));

        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 1;
        c.weightx = 0.5;
        this.add(continueBtn, c);

        c.gridx = 2;
        c.gridwidth = 1;
        c.weightx = 0.5;
        this.add(exit, c);
    }

    /**
     * @param id of the winner player
     * @return Color associated to the player
     */
    private Color getPlayerColor(int id) {
        switch (id) {
            case 1:
                return Color.RED;
            case 2:
                return Color.BLUE;
            case 3:
                return Color.YELLOW;
            default:
                return UIManager.getColor("Label.foreground");
        }
    }
}
