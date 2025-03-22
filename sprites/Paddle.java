package sprites;

import game.Game;
import geometry.Point;
import geometry.Rectangle;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;

public class Paddle implements Sprite, Collidable {
    private static final int SPEED = 5;

    private biuoop.KeyboardSensor keyboard;
    private Rectangle pad;
    private Frame f;
    private Color color;

    /**
     * Constructs a new Paddle with the given rectangle, keyboard sensor, and color.
     *
     * @param paddleRectangle The rectangle shape of the paddle.
     * @param keyboard        The keyboard sensor used to control the paddle.
     * @param c               The color of the paddle.
     */
    public Paddle(Rectangle paddleRectangle, KeyboardSensor keyboard, Color c) {
        this.pad = paddleRectangle;
        this.keyboard = keyboard;
        this.color = c;
    }


    /**
     * Moves the paddle left by 5 pixels, if possible.
     */
    public void moveLeft() {
        double y = pad.getUpperLeft().getY();
        double x = pad.getUpperLeft().getX() - SPEED;
        if (x + pad.getWidth() < 0) {
            x = 800;
        }
        pad.setUpperLeft(new Point(x, y));
    }

    /**
     * Moves the paddle right by 5 pixels, if possible.
     */
    public void moveRight() {
        double y = pad.getUpperLeft().getY();
        double x = pad.getUpperLeft().getX() + SPEED;
        if (pad.getUpperLeft().getX() + SPEED > 800) {
            x = -pad.getWidth();
        }
        pad.setUpperLeft(new Point(x, y));
    }

    @Override
    /**
     * Handles the time passed
     * Checks keyboard input to move the paddle.
     */
    public void timePassed() {
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft();
        }
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight();
        }
    }

    /**
     * Draws the paddle.
     *
     * @param d DrawSurface on which to draw.
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.fillRectangle((int) this.pad.getUpperLeft().getX(),
                (int) this.pad.getUpperLeft().getY(),
                (int) this.pad.getWidth(),
                (int) this.pad.getHeight());
        d.setColor(Color.BLACK);
        d.drawRectangle((int) this.pad.getUpperLeft().getX(),
                (int) this.pad.getUpperLeft().getY(),
                (int) this.pad.getWidth(),
                (int) this.pad.getHeight());
    }

    /**
     * Returns the rectangle of the paddle.
     *
     * @return The rectangle of the paddle.
     */
    public Rectangle getCollisionRectangle() {
        return this.pad;
    }

    /**
     * Determines the new velocity.
     * Calculates the angle based on the collision point
     * Returns a new velocity
     *
     * @param b               The ball
     * @param collisionPoint  The point
     * @param currentVelocity The current velocity
     * @return The new velocity of the ball
     */
    @Override
    public Velocity hit(Ball b, Point collisionPoint, Velocity currentVelocity) {
        double calc = (collisionPoint.getX() - pad.getUpperLeft().getX()) / (pad.getWidth() / 5);
        int segment = (int) calc;
        double ang;

        switch (segment) {
            case 0:
                ang = 300;
                break;
            case 1:
                ang = 330;
                break;
            case 2:
                return new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
            case 3:
                ang = 30;
                break;
            case 4:
            default:
                ang = 60;
                break;
        }
        return Velocity.fromAngleAndSpeed(ang, currentVelocity.speed());
    }

    /**
     * Adds the paddle to the game by registering.
     *
     * @param g The game to which the paddle is added.
     */
    public void addToGame(Game g) {
        g.addCollidable(this);
        g.addSprite(this);
    }


}