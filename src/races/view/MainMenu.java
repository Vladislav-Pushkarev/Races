package races.view;

import races.Commons;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

class MainMenu extends JPanel implements Commons {
    private static final String ERROR_MESSAGE = "Only integer greater than zero allowed.";
    private static final String LABEL_MESSAGE = "Enter the number of racers:";
    private final JButton button = new JButton();
    private final JTextField textField = new JTextField();
    private final Game game;

    private void initUI() {
        setLayout(null);
        setPreferredSize(new Dimension(MENU_WINDOW_WIDTH, WINDOW_HEIGHT));
        setFocusable(true);

        Action action = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actionOnClick();
            }
        };

        Font font = new Font("Arial Black", Font.BOLD, 17);
        JLabel label = new JLabel(LABEL_MESSAGE);
        label.setFont(font);
        label.setBounds(5, 25, 290, 30);

        textField.setBounds(295, 25, 35, 30);
        textField.addActionListener(action);
        button.addActionListener(action);
        button.setText("OK");
        button.setBounds(335, 25, 60, 30);

        add(label);
        add(textField);
        add(button);
    }

    MainMenu(Game game) {
        this.game = game;
        initUI();
    }

    private static boolean isValid(String input) {
        if (input.matches("\\d+(\\.\\d+)?")) {
            try {
                return Integer.parseInt(input) > 0;
            } catch (NumberFormatException e) {
                return false;
            }
        }

        return false;
    }

    private void actionOnClick() {
        String input = textField.getText();
        if (!isValid(input)) {
            JOptionPane.showMessageDialog(null, ERROR_MESSAGE,
                    "Illegal value: ", JOptionPane.INFORMATION_MESSAGE);

            return;
        }

        game.openGamePanel(Integer.parseInt(input));
    }
}
