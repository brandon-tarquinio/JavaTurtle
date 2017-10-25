package TurtleGraphics;

/**
 * Created by tarquinio on 9/2/17.
 */
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.util.LinkedList;

import javax.imageio.ImageIO;
import javax.swing.JComponent;
        import javax.swing.JFrame;

class TurtleTank extends JComponent{


    private static class Line{
        final int x1;
        final int y1;
        final int x2;
        final int y2;
        final Color color;

        public Line(int x1, int y1, int x2, int y2, Color color) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
            this.color = color;
        }
    }

    private final LinkedList<Line> lines = new LinkedList<>();

    int turtleX;
    int turtleY;
    BufferedImage turtleImg;
    TurtleTank() {
        super();
        try {
            turtleImg = ImageIO.read(getClass().getClassLoader().getResourceAsStream("TurtleGraphics.jpeg"));
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    void addLine(int x1, int x2, int x3, int x4, Color color) {
        lines.add(new Line(x1,x2,x3,x4, color));

        // Subtract 5 to center the turtle over the line
        turtleX = x3 - 5;
        turtleY = x4 - 5;

        repaint();
    }

    void clearLines() {
        lines.clear();
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.dispose();
        super.paintComponent(g);
        for (Line line : lines) {
            g.setColor(line.color);
            g.drawLine(line.x1, line.y1, line.x2, line.y2);
        }

        // draw the beast
        g.drawImage(turtleImg.getScaledInstance(10, 10, Image.SCALE_SMOOTH), turtleX, turtleY, null);
    }

    // Rotate the beast
    void rotate(double radians) {
        AffineTransform tx = new AffineTransform();
        tx.rotate(-radians, turtleImg.getWidth(null) / 2, turtleImg.getHeight(null) / 2);

        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);

        BufferedImage copyImg = op.createCompatibleDestImage(turtleImg, null);
        op.filter(turtleImg, copyImg);
        turtleImg = copyImg;

        repaint();
    }

    void addTurtle() {
        // Set turtle starting point to middle of image
        turtleX = this.getWidth() / 2;
        turtleY = this.getHeight() / 2;

        repaint();
    }

    static void addNewLine(TurtleTank turtleTank, int x1, int y1, int x2, int y2, Color color) {
                turtleTank.addLine(x1, y1, x2, y2, color);
    }

    static TurtleTank getTurtle(int width, int height) {
        JFrame testFrame = new JFrame();
        testFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        final TurtleTank turtleTank = new TurtleTank();
        turtleTank.setPreferredSize(new Dimension(width, height));
        testFrame.getContentPane().add(turtleTank, BorderLayout.CENTER);
        testFrame.pack();
        testFrame.setVisible(true);

        return turtleTank;
    }
}