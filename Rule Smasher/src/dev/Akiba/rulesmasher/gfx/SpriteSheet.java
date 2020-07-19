package dev.Akiba.rulesmasher.gfx;

import java.awt.image.BufferedImage;

public class SpriteSheet {

    private BufferedImage sheet;

    public SpriteSheet(BufferedImage sheet) {
        this.sheet = sheet;
    }

    public BufferedImage crop(int x, int y, int width, int height) {
        // return a certain part of the spreadsheet given parameters
        return sheet.getSubimage(x, y, width, height);
    }
}
