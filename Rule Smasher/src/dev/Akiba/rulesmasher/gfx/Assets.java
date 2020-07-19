package dev.Akiba.rulesmasher.gfx;

import java.awt.image.BufferedImage;

public class Assets {

    private static final int width = 160, height = 160;

    public static BufferedImage[] p1Static, p1Left, p1Right;
    public static BufferedImage[] p2Static, p2Left, p2Right;
    public static BufferedImage redGloveRight, redGloveLeft, 
                                blueGloveRight, blueGloveLeft;
    public static BufferedImage cloud, grass, wood, sky, dirt, brick;

    public static void init() {
        SpriteSheet otherSheet = new SpriteSheet(ImageLoader.loadImage("/textures/OtherSprites.png"));
        SpriteSheet blueSheet = new SpriteSheet(ImageLoader.loadImage("/textures/BlueSprites.png"));
        SpriteSheet redSheet = new SpriteSheet(ImageLoader.loadImage("/textures/RedSprites.png"));
        SpriteSheet gloveSprites = new SpriteSheet(ImageLoader.loadImage("/textures/GloveSprites.png"));

        p1Left = new BufferedImage[2];
        p1Right = new BufferedImage[2];
        p1Static = new BufferedImage[2];
        p1Left[0] = blueSheet.crop(0, 0, width, height);
        p1Left[1] = blueSheet.crop(width, 0, width, height);
        p1Right[0] = blueSheet.crop(width * 2, 0, width, height);
        p1Right[1] = blueSheet.crop(width * 3, 0, width, height);
        p1Static[0] = blueSheet.crop(width * 4, 0, width, height);
        p1Static[1] = blueSheet.crop(width * 5, 0, width, height);

        p2Left = new BufferedImage[2];
        p2Right = new BufferedImage[2];
        p2Static = new BufferedImage[2];
        p2Left[0] = redSheet.crop(0, 0, width, height);
        p2Left[1] = redSheet.crop(width, 0, width, height);
        p2Right[0] = redSheet.crop(width * 2, 0, width, height);
        p2Right[1] = redSheet.crop(width * 3, 0, width, height);
        p2Static[0] = redSheet.crop(width * 4, 0, width, height);
        p2Static[1] = redSheet.crop(width * 5, 0, width, height);

        blueGloveLeft = gloveSprites.crop(0, 0, width, height);
        blueGloveRight = gloveSprites.crop(width, 0, width, height);
        redGloveLeft = gloveSprites.crop(width * 2, 0, width, height);
        redGloveRight = gloveSprites.crop(width * 3, 0, width, height);
        
        cloud = otherSheet.crop(0, 0, width, height);
        dirt = otherSheet.crop(width * 1, 0, width, height);
        grass = otherSheet.crop(width * 2, 0, width, height);
        sky = otherSheet.crop(width * 3, 0, width, height);
        wood = otherSheet.crop(width * 4, 0, width, height);
        brick = otherSheet.crop(width * 5, 0, width, height);
    }

    public static int getWidth() {
        return width;
    }

    public static int getHeight() {
        return height;
    }
}
