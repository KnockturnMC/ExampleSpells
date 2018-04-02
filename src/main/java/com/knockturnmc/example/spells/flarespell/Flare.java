package com.knockturnmc.example.spells.flarespell;

import org.bukkit.Color;

public enum Flare {

    BLORIUM("white", Color.WHITE),
    VIOLUM("purple", Color.PURPLE),
    NORIUM("black", Color.BLACK),
    ACORIUM("yellow", Color.YELLOW),
    AERILLIOUS("blue", Color.BLUE),
    VERDIMILLIOUS("green", Color.GREEN),
    VERMILLIOUS("orange", Color.ORANGE),
    PERICULUM("red", Color.RED);

    private String colorString;
    private Color color;

    Flare(String colorString, Color color) {
        this.colorString = colorString;
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public String getColorString() {
        return colorString;
    }
}