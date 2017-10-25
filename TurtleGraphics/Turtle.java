package TurtleGraphics;

import java.awt.Color;

public class Turtle {

    private int x;
    private int y;
    private int angle;
    private Color penColour;
    private boolean penPosition;
    private int speed;

    // Window to draw on
    private final TurtleTank turtleTank;

    /**
     * Constant indicating a Turtle's pen is down.
     */
    public final static boolean PEN_DOWN = true;

    /**
     * Constant indicating that a Turtle's pen is up.
     */
    public final static boolean PEN_UP = false;

    /**
     * Constructs a Turtle object with a 500 X 500 pixel
     * Turtle Tank.
     *
     * The pen will be down with black ink and the turtle
     * will start in the middle of the Turtle Tank looking right.
     *
     */
    public Turtle() {
        this(500, 500);
    }

    /**
     * Constructs a Turtle object with a width X height pixel
     * Turtle Tank.
     *
     * The pen will be down with black ink and the turtle
     * will start in the middle of the Turtle Tank looking right.
     *
     * @param width  The width in pixels of the Turtle Tank.
     * @param height The height in pixels of the Turtle Tank.
     */
    public Turtle(final int width, final int height) {
        // Make the Turtle Tank
        this.turtleTank = TurtleTank.getTurtle(width, height);
        turtleTank.addTurtle();

        x = width / 2;
        y = height / 2;
        angle = 0;
        penColour = Color.black;
        penPosition = PEN_DOWN;
        speed = 1;

        // Sleep so that the starting position can be seen
        try {
            Thread.sleep((int) (1000 / speed));
        } catch (Exception e) {}
    }

    /**
     * Move this Turtle forward by the specified number of
     * screen pixels.
     *
     * @param pixels the number of screen pixes by which
     *               to move this Turtle forward.
     */
    public void moveForward(final int pixels) {
        move(pixels);
    }

    /**
     * Move this Turtle backward by the specified number of
     * screen pixels.
     *
     * @param pixels the number of screen pixes by which
     *               to move this Turtle backward.
     */
    public void moveBackward(final int pixels) {
        move((-1)*pixels);
    }

    /**
     * Rotate this Turtle left by the specified
     * number of degrees.
     *
     * @param degrees the number of degrees by which to rotate
     *                this Turtle.
     */
    public void turnLeft(final int degrees) {
        rotate(degrees);
    }

    /**
     * Rotate this Turtle left by the specified
     * number of degrees.
     *
     * @param degrees the number of degrees by which to rotate
     *                this Turtle.
     */
    public void turnRight(final int degrees) {
        rotate((-1)*degrees);
    }


    /**
     * Ask this Turtle if it's pen is up or down. The value
     * returned will be either PEN_UP or PEN_DOWN.
     *
     * @return PEN_DOWN if this Turtle's pen is up or
     *         PEN_UP if this Turtle's pen is up.
     */
    public boolean getPenPosition() {
        return penPosition;
    }

    /**
     * Put this Turtle's pen down. When this Turtle's pen is
     * down it will draw a line in its color as it moves
     * forward.
     */
    public void putPenDown() {
        penPosition = PEN_DOWN;
    }

    /**
     * Pick this Turtle's pen up. When this Turtle's pen is
     * up it will not draw a line as it moves forward.
     */
    public void pickPenUp() {
        penPosition = PEN_UP;
    }


    /**
     * Get the x coordinate of this Turtle.
     *
     * @return the x coordinate of this Turtle.
     */
    public int getX() {
        return x;
    }

    /**
     * Get the y coordinate of this Turtle.
     *
     * @return the y coordinate of this Turtle.
     */
    public int getY() {
        return y;
    }


    /**
     * Get the colour of this Turtle's pen. The color is
     * returned as a reference to a Color object.
     *
     * @return a reference to a Color object representing
     *         the Color of this Turtle's pen.
     */
    public Color getColour() {
        return penColour;
    }

    /**
     * Set the pen colour to be a random colour.
     */
    public void setRandomColour() {
       setColour(new Color( (float)Math.random(), (float)Math.random(), (float)Math.random()));
    }

    /**
     * Set the pen colour to be a given colour formed from RGB values.
     *
     * @param red  the amount of red from 0 to 255.
     * @param green the amount of green from 0 to 255.
     * @param blue the amount of blue from 0 to 255.
     */
    public void setColour(int red, int green, int blue) {
        setColour(new Color(red, green, blue));
    }

     /**
     * Set the pen colour to be a given colour.
     *
     * @param colour Color object to set as colour.
     */
    public void setColour(Color colour) {
        penColour = colour;
    }


     /**
     * Get the current speed of this Turtle.
     *
     * The speed should go from 1 (slowest) to 1000 (quickest).
     *
     * @return a reference to a Color object representing
     *         the Color of this Turtle's pen.
     */
    public int getSpeed() {
        return speed;
    }

     /**
     * Set the current speed of this Turtle.
     *
     * The speed should go from 1 (slowest) to 10 (quickest).
     *
     * @param newSpeed An int from 1 (slowest) to 10 (quickest).
     */
    public void setSpeed(int newSpeed) {
        if (newSpeed > 10) {
            newSpeed = 10;
        } else if (newSpeed < 1) {
            newSpeed = 1;
        }

        this.speed = newSpeed;
    }

    /**
     * Get the the angle to which this Turtle is turned.
     * The angle of the Turtle is measured counter-clockwise
     * from horizontal.
     *
     * @return the angle to which this Turtle is turned.
     */
    public int getAngle() {
        return angle;
    }

    private void move(final int pixels) {
        int oldX = x;
        int oldY = y;

        double radAngle = Math.toRadians(angle);
        x = x + (int)Math.round(Math.cos(radAngle) * pixels);
        y = y - (int)Math.round(Math.sin(radAngle) * pixels);

        if (penPosition) {
            TurtleTank.addNewLine(turtleTank, oldX, oldY, x, y, penColour);
            try {
                Thread.sleep((int)(1000 / speed));
            } catch (Exception e) {}
        }
    }

    /**
     * Rotate this Turtle counter-clockwise by the specified
     * number of degrees.
     *
     * @param degrees the number of degrees by which to rotate
     *                this Turtle.
     */
    private void rotate(int degrees) {
        int newAngle = angle + degrees;
        angle = newAngle % 360;
        double radAngle = Math.toRadians(angle);

        // Update Turtle image in the Turtle Tank.
        // Note we are rotating by degrees and not angle
        // since we are updating the image in turtle tank
        turtleTank.rotate(Math.toRadians(degrees));

        // Sleep so that the rotation can be seen
        try {
            Thread.sleep((int)(1000 / speed));
        } catch (Exception e) {}
    }
}
