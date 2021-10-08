package com.gudimov.players;

import javax.swing.*;

public enum XOEnum {
    X,O;
    public static String toString(XOEnum player) {
        return player == X ? "X" : "O";
    }
    public static ImageIcon getPlayer(XOEnum player) {
        return player == X ?
                new ImageIcon("src/Images/1x/X_1_black.png"):
                new ImageIcon("src/Images/1x/O_1_black.png");
    }
    public static ImageIcon getPlayerX2(XOEnum player) {
        return player == X ?
                new ImageIcon("src/Images/2x/X_2_black.png"):
                new ImageIcon("src/Images/2x/O_2_black.png");
    }
    public static ImageIcon getPlayer(boolean player) {
        return player == true ?
                new ImageIcon("src/Images/1x/X_1_black.png"):
                new ImageIcon("src/Images/1x/O_1_black.png");
    }
    public static ImageIcon getPlayerX2(boolean player) {
        return player == true ?
                new ImageIcon("src/Images/2x/X_2_black.png"):
                new ImageIcon("src/Images/2x/O_2_black.png");
    }
    public ImageIcon getIconX2 () {
        return getPlayerX2(this);
    }
    public static XOEnum getEnum(String contains) {
        return contains.contains("X_1") ? X : O;
    }

}
