package GameWindow.GamePanels;

import GameWindow.PreparationGameWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChoosePanel extends JPanel {
    private PreparationGameWindow gameWindow;
    private JPanel panelRadio;
    private JPanel panelPlacement;
    private JButton startButton;
    private JRadioButton oneDeck;
    private JRadioButton twoDeck;
    private JRadioButton threeDeck;
    private JRadioButton fourDeck;
    private JRadioButton vertical;
    private JRadioButton horizontal;
    private JButton clearField;
    private ButtonGroup groupDeck;
    private ButtonGroup groupPlacement;

    public ChoosePanel(PreparationGameWindow gameWindow) {
        this.gameWindow = gameWindow;
        setLayout(null);
        this.setPreferredSize(new Dimension(255, 400));
        panelRadio = new JPanel();
        panelRadio.setLayout(new BoxLayout(panelRadio, BoxLayout.Y_AXIS));
        panelRadio.setBounds(13, 190, 230, 130);
        panelPlacement = new JPanel();
        panelPlacement.setLayout(new BoxLayout(panelPlacement, BoxLayout.Y_AXIS));
        panelPlacement.setBounds(13, 330, 230, 80);
        clearField = new JButton("Убрать все корабли");
        clearField.setBounds(13, 410, 230, 30);
        clearField.addActionListener(new ActionClearField());
        panelRadio.setBorder(BorderFactory.createTitledBorder("Расставьте корабли:"));
        panelPlacement.setBorder(BorderFactory.createTitledBorder("Ориентация корабля"));

        oneDeck = new JRadioButton();
        setNameOneDeck(4);
        twoDeck = new JRadioButton();
        setNameTwoDeck(3);
        threeDeck = new JRadioButton();
        setNameThreeDeck(2);
        fourDeck = new JRadioButton();
        setNameFourDeck(1);

        vertical = new JRadioButton("Вертикальная");
        horizontal = new JRadioButton("Горизонтальная");
        groupDeck = new ButtonGroup();
        groupPlacement = new ButtonGroup();

        panelRadio.add(oneDeck);
        panelRadio.add(twoDeck);
        panelRadio.add(threeDeck);
        panelRadio.add(fourDeck);

        panelPlacement.add(vertical);
        panelPlacement.add(horizontal);
        add(panelRadio);
        add(panelPlacement);
        add(clearField);

        groupDeck.add(oneDeck);
        groupDeck.add(twoDeck);
        groupDeck.add(threeDeck);
        groupDeck.add(fourDeck);

        groupPlacement.add(vertical);
        groupPlacement.add(horizontal);

        startButton = new JButton("В БОЙ!");
        startButton.setBounds(13, 100, 230, 50);
        startButton.addActionListener(new startGame());
        add(startButton);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

    public void setNameOneDeck(int count) {
        String text = "Однопалубный, осталось - " + count;
        oneDeck.setText(text);
    }

    public void setNameTwoDeck(int count) {
        String text = "Двухпалубный, осталось - " + count;
        twoDeck.setText(text);
    }

    public void setNameThreeDeck(int count) {
        String text = "Трехпалубный, осталось - " + count;
        threeDeck.setText(text);
    }

    public void setNameFourDeck(int count) {
        String text = "Четырехпалубный, осталось - " + count;
        fourDeck.setText(text);
    }

    public int getCountDeck() {
        if (oneDeck.isSelected()) return 1;
        else if (twoDeck.isSelected()) return 2;
        else if (threeDeck.isSelected()) return 3;
        else if (fourDeck.isSelected()) return 4;
        else return 0;
    }

    public int getPlacement() {
        if (vertical.isSelected()) return 1;
        else if (horizontal.isSelected()) return 2;
        else return 0;
    }

    private class ActionClearField implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            gameWindow.loadEmptyMyField();
        }
    }

    private class startGame implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (gameWindow.isGameIsReady()){
                gameWindow.loadBattleShipWindow();
            }
        }
    }
}
