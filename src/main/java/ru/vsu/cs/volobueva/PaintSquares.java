package ru.vsu.cs.volobueva;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class PaintSquares {
    private static void paintSmallerSquaresUpperLarger(Graphics2D g2d, double x, double y, double sideSize, int levelCounter) {
        if (levelCounter > 0) {
            g2d.setColor(Color.WHITE);
            g2d.fill(new Rectangle2D.Double(x - sideSize/2, y - sideSize/2, sideSize, sideSize));

            g2d.setColor(Color.BLACK);
            g2d.draw(new Rectangle2D.Double(x - sideSize/2, y - sideSize/2, sideSize, sideSize));

            paintSmallerSquaresUpperLarger(g2d, x + sideSize / 2, y + sideSize / 2, sideSize / 2, levelCounter - 1);
            paintSmallerSquaresUpperLarger(g2d, x - sideSize / 2, y + sideSize / 2, sideSize / 2, levelCounter - 1);
            paintSmallerSquaresUpperLarger(g2d, x + sideSize / 2, y - sideSize / 2, sideSize / 2, levelCounter - 1);
            paintSmallerSquaresUpperLarger(g2d, x - sideSize / 2, y - sideSize / 2, sideSize / 2, levelCounter - 1);
        }
    }

    private static void paintSmallerSquaresBelowLarger(Graphics2D g2d, double x, double y, double sideSize, int levelCounter) {
        if (levelCounter > 0) {
            paintSmallerSquaresBelowLarger(g2d, x + sideSize / 2, y + sideSize / 2, sideSize / 2, levelCounter - 1);
            paintSmallerSquaresBelowLarger(g2d, x - sideSize / 2, y + sideSize / 2, sideSize / 2, levelCounter - 1);
            paintSmallerSquaresBelowLarger(g2d, x + sideSize / 2, y - sideSize / 2, sideSize / 2, levelCounter - 1);
            paintSmallerSquaresBelowLarger(g2d, x - sideSize / 2, y - sideSize / 2, sideSize / 2, levelCounter - 1);

            g2d.setColor(Color.WHITE);
            g2d.fill(new Rectangle2D.Double(x - sideSize/2, y - sideSize/2, sideSize, sideSize));

            g2d.setColor(Color.BLACK);
            g2d.draw(new Rectangle2D.Double(x - sideSize/2, y - sideSize/2, sideSize, sideSize));
        }
    }

    public static void paintSquares(Graphics2D g2d, int width, int height, int levelCounter, boolean flagAtUpper) {
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Color color = g2d.getColor();
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, width, height);
        g2d.setColor(color);

        if (flagAtUpper) {
            paintSmallerSquaresUpperLarger(g2d, width/2, height/2, width/2, levelCounter);
        } else {
            paintSmallerSquaresBelowLarger(g2d, width/2, height/2, width/2, levelCounter);
        }
    }

    public static void saveDrawing(String fileName, BufferedImage drawing) throws IOException {
        File file = new File(fileName);
        ImageIO.write(drawing, "png", file);
    }
}
