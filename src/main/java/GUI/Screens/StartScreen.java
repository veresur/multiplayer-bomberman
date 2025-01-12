package GUI.Screens;

import GUI.Display;
import Utils.Coordinate;
import Utils.Props;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

/**
 * Start screen which shows options for the game before starting it
 */
public class StartScreen extends Screen {
    private final ArrayList<String> MAP_FILE_NAMES = new ArrayList<>(Arrays.asList("map1.png", "map2.png", "map3.png"));
    private JButton selectedButton;
    private int selectedMapIndex = -1;
    private final JComboBox<String> playerModeDropdown = new JComboBox<>(Props.PLAYER_MODES);
    private final JComboBox<String> roundSelectorDropdown = new JComboBox<>(Props.ROUND_OPTIONS);

    /**
     * @return size of the frame to be rendered
     */
    public Coordinate getFrameSize() {
        return new Coordinate(Props.MAP_SIZE * Props.RECT_SIZE + 200, Props.MAP_SIZE * Props.RECT_SIZE + 200);
    }

    /**
     * sets the UI up and renders options, dropdrowns for the user
     * @param display to reload screen when game is requested to be started
     */
    public StartScreen(Display display) {
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        c.gridheight = 1;

        playerModeDropdown.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        roundSelectorDropdown.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        for (String each : MAP_FILE_NAMES) {
            ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("maps/" + each)));
            JButton btn = createImageButton(icon, 600, 336);
            btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            btn.addActionListener(event -> {
                // +1 due to 0-based indexing but maps starting with 1
                this.selectedMapIndex = MAP_FILE_NAMES.indexOf(each) + 1;
                handleImageSelection(btn);
            });
            this.add(btn, c);

            if (c.gridx == 1) {
                c.gridx = 0;
                c.gridy++;
            } else {
                c.gridx++;
            }
        }

        // n player selector
        c.gridwidth = 2;
        c.gridx = 0;
        c.gridy++;
        this.add(playerModeDropdown, c);

        // n round selector
        c.gridwidth = 2;
        c.gridx = 0;
        c.gridy++;
        this.add(roundSelectorDropdown, c);

        JButton newGame = new JButton("Start game");
        newGame.addActionListener(e -> {
            if (selectedButton != null) {
                int playerCount = this.getLeadingNumberFromString(Objects.requireNonNull(playerModeDropdown.getSelectedItem()).toString());
                int roundCount = this.getLeadingNumberFromString(Objects.requireNonNull(roundSelectorDropdown.getSelectedItem()).toString());
                display.ReloadScreen(new GameScreen(display, playerCount, selectedMapIndex, roundCount));
            }
        });
        c.gridy++;
        this.add(newGame, c);

        this.setBorder(BorderFactory.createEmptyBorder(20, 40, 40, 40));
    }

    /**
     * @param icon to be rendered
     * @param maxWidth for render
     * @param maxHeight for render
     * @return JButton to make the image clickable / selectable
     */
    private JButton createImageButton(ImageIcon icon, int maxWidth, int maxHeight) {
        Image img = icon.getImage();

        double aspectRatio = (double) img.getWidth(null) / img.getHeight(null);

        int scaledWidth = Math.min(img.getWidth(null), maxWidth);
        int scaledHeight = (int) (scaledWidth / aspectRatio);

        if (scaledHeight > maxHeight) {
            scaledHeight = maxHeight;
            scaledWidth = (int) (scaledHeight * aspectRatio);
        }

        Image scaledImg = img.getScaledInstance(scaledWidth, scaledHeight, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImg);

        JButton button = new JButton(scaledIcon);
        button.setPreferredSize(new Dimension(scaledWidth, scaledHeight));
        button.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        return button;
    }


    /**
     * @param selectedButton renders the selected button with border
     * UI visualization of the select
     */
    private void handleImageSelection(JButton selectedButton) {
        if (this.selectedButton != null) {
            this.selectedButton.setBorder(BorderFactory.createEmptyBorder());
        }

        Border selectedBorder = BorderFactory.createLineBorder(Color.BLUE, 2);
        selectedButton.setBorder(selectedBorder);

        this.selectedButton = selectedButton;
    }

    /**
     * @param s dropdown value's string
     * @return the number parsed from the dropdown's string
     */
    private int getLeadingNumberFromString(String s) {
        return Integer.parseInt(s.split(" ")[0]);
    }
}
