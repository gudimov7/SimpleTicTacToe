package com.gudimov.gui;

import com.gudimov.players.XOEnum;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Frame extends JFrame implements ActionListener {
    private final JPanel gamePanel;
    private final JButton[] buttons;
    private final GridLayout gridLayout;
    private static int turns = 0;
    public Frame() {
        gridLayout = new GridLayout(3,3,15,15);
        this.setTitle("Tic-Tac-Toe");
        gamePanel = new JPanel();
        buttons = new JButton[9];
        gamePanel.setBackground(Color.BLACK);
        gamePanel.setLayout(gridLayout);
        for(int i = 0; i < 9; i ++){
            buttons[i] = new JButton();
            buttons[i].setFocusable(false);
            buttons[i].addActionListener(this);
            gamePanel.add(buttons[i]);
        }
        this.add(gamePanel);
        this.setSize(300,300);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        turns++;
        setPlayerIcon(e);
        getWinner(e);

    }
    public int getTurns(){
        return turns;
    }
    public void setPlayerIcon (ActionEvent e) {
        if(getTurns() % 2 == 0)
            ((JButton)e.getSource()).setIcon(setPlayer(false));
        else
            ((JButton)e.getSource()).setIcon(setPlayer(true));
    }
    public ImageIcon setPlayer (boolean player) {
        return XOEnum.getPlayer(player);
    }
    public void getWinner(ActionEvent e) {
        /*
         *      WINNER COMBINATIONS
         *  -0-1-2-  |   -0-3-6- |   -0-4-8-
         *  -3-4-5-  |   -1-4-7- |   -2-4-6-
         *  -6-7-8-  |   -2-5-8- |
         *
         *      DRAW COMBINATIONS
         *  -0-2-7-  |  -2-8-3-
         *  -0-6-5-  |  -1-6-8-
         *
         */
        /*  WIN COMBINATIONS   */
            if(isCombination(buttons[0],buttons[1],buttons[2])) {
                viewWinner(getEnumValue(buttons[0]));
                return;
            }
            else if(isCombination(buttons[3],buttons[4],buttons[5])) {
                viewWinner(getEnumValue(buttons[3]));
                return;
            }
            else if(isCombination(buttons[6],buttons[7],buttons[8])) {
                viewWinner(getEnumValue(buttons[6]));
                return;
            }
            else if(isCombination(buttons[0],buttons[3],buttons[6])) {
                viewWinner(getEnumValue(buttons[0]));
                return;
            }
            else if(isCombination(buttons[1],buttons[4],buttons[7])) {
                viewWinner(getEnumValue(buttons[1]));
                return;
            }
            else if(isCombination(buttons[2],buttons[5],buttons[8])) {
                viewWinner(getEnumValue(buttons[2]));
                return;
            }
            else if(isCombination(buttons[0],buttons[4],buttons[8])) {
                viewWinner(getEnumValue(buttons[0]));
                return;
            }
            else if(isCombination(buttons[2],buttons[4],buttons[6])) {
                viewWinner(getEnumValue(buttons[2]));
                return;
            }

            /*  DRAW COMBINATIONS   */
            else if(isCombination(buttons[0],buttons[2],buttons[7])) {
                viewWinner(getEnumValue(new JButton()));
                return;
            }
            else if(isCombination(buttons[0],buttons[6],buttons[5])) {
                viewWinner(getEnumValue(new JButton()));
                return;
            }
            else if(isCombination(buttons[2],buttons[8],buttons[3])) {
                viewWinner(getEnumValue(new JButton()));
                return;
            }
            else if(isCombination(buttons[1],buttons[6],buttons[8])) {
                viewWinner(getEnumValue(new JButton()));
                return;
            }
            else
                return;
    }
    public boolean isCombination (JButton a,JButton b,JButton c) {
        if(getEnumValue(a) != null)
            return getEnumValue(a) == getEnumValue(b) && getEnumValue(b) == getEnumValue(c);
        return false;

    }
    public boolean btnContains(JButton b) {
        return b.getIcon() != null;
    }
    public XOEnum getEnumValue(JButton b){
        if(btnContains(b)) {
            return XOEnum.getEnum(b.getIcon().toString());
        }
        return null;
    }
    public void viewWinner(XOEnum winner) {
        JPanel winnerPan = new JPanel();
        JLabel winnerLabel = new JLabel();
        if (winner == null) {
            winnerLabel.setText("DRAW");
        }
        else {
            ImageIcon winnerIcon = winner.getIconX2();
            winnerLabel.setText("WINNER");
            winnerLabel.setIcon(winnerIcon);
        }
        winnerLabel.setFont(new Font("Sans",Font.BOLD,50));
        winnerLabel.setHorizontalTextPosition(JLabel.CENTER);
        winnerLabel.setVerticalTextPosition(JLabel.BOTTOM);
        winnerPan.setBackground(Color.RED);
        winnerPan.add(winnerLabel);
        this.remove(gamePanel);
        this.add(winnerPan);
    }

}
