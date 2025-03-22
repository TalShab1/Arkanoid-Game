package game;

import geometry.Point;
import geometry.Rectangle;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import biuoop.Sleeper;
import listeners.BallRemover;
import listeners.BlockRemover;
import listeners.ScoreIndicator;
import listeners.ScoreTrackingListener;
import sprites.Collidable;
import sprites.Sprite;
import sprites.Frame;
import sprites.SpriteCollection;
import sprites.Ball;
import sprites.Block;
import sprites.Paddle;


import java.awt.Color;


public class Game {
    public static final int HEIGHT = 600;
    public static final int WIDTH = 800;

    private GUI gui;
    private Sleeper sleeper;
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private Counter remainingBlocks;
    private Counter remainingBalls;
    private Counter score;
    private Frame frame;

    /**
     * Adds a collidable object to the game environment.
     *
     * @param c the collidable object to add
     */


    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * removes a collidable object to the game environment.
     *
     * @param c the collidable object to remove.
     */

    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }

    /**
     * Adds a sprite object to the game.
     *
     * @param s the sprite object to add.
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * removes a sprite object to the game.
     *
     * @param s the sprite object to remove.
     */

    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }

    /**
     * Initialize a new game.
     */
    public void initialize() {
        this.gui = new GUI("game", WIDTH, HEIGHT);
        this.frame = new Frame(new Point(0, 0), new Point(WIDTH, HEIGHT));
        this.sleeper = new Sleeper();

        this.environment = new GameEnvironment();
        this.sprites = new SpriteCollection();
        this.remainingBlocks = new Counter();
        this.remainingBalls = new Counter();
        this.score = new Counter();

        ScoreTrackingListener scoreListener = new ScoreTrackingListener(score);

        ScoreIndicator scoreIndicator = new ScoreIndicator(score);
        scoreIndicator.addToGame(this);

        Ball ball1 = new Ball(new Point(30, 15), 5, Color.RED, this.environment, frame);
        Ball ball2 = new Ball(new Point(30, 15), 5, Color.MAGENTA, this.environment, frame);
        Ball ball3 = new Ball(new Point(30, 15), 5, Color.WHITE, this.environment, frame);

        remainingBalls.increase(3);

        Block deathRegion = new Block(new Rectangle(new Point(0, HEIGHT - 10), WIDTH, 10));

        BallRemover ballRemover = new BallRemover(this, remainingBalls);
        deathRegion.addHitListener(ballRemover);
        deathRegion.addToGame(this);

        KeyboardSensor keyboard = gui.getKeyboardSensor();
        Rectangle paddleRectangle = new Rectangle(new Point((double) (WIDTH - 100) / 2, HEIGHT - 30), 100, 20);
        Paddle paddle = new Paddle(paddleRectangle, keyboard, Color.YELLOW);
        paddle.addToGame(this);

        for (int i = 1; i < 7; i++) {
            Color color;
            if (i == 1) {
                color = Color.GRAY;
            } else if (i == 2) {
                color = Color.RED;
            } else if (i == 3) {
                color = Color.YELLOW;
            } else if (i == 4) {
                color = Color.BLUE;
            } else if (i == 5) {
                color = Color.PINK;
            } else {
                color = Color.GREEN;
            }
            for (int j = 0; j < 13 - i; j++) {
                Block block = new Block(new Rectangle(new Point(750 - j * 40, 100 + i * 25), 40, 25));
                block.setColor(color);
                block.addToGame(this);
                block.addHitListener(new BlockRemover(this, remainingBlocks));
                block.addHitListener(scoreListener);
                remainingBlocks.increase(1);
            }
        }

        Block bL = new Block(new Rectangle(new Point(0, 0), 10, HEIGHT));
        Block bT = new Block(new Rectangle(new Point(0, 0), WIDTH, 10));
        Block bR = new Block(new Rectangle(new Point(WIDTH - 10, 0), 10, HEIGHT));
        Block bB = new Block(new Rectangle(new Point(0, HEIGHT - 10), WIDTH, 10));

        bL.setColor(Color.BLACK);
        bT.setColor(Color.BLACK);
        bR.setColor(Color.BLACK);
        bB.setColor(Color.BLACK);

        bL.addToGame(this);
        bT.addToGame(this);
        bR.addToGame(this);
        bB.addToGame(this);

        ball1.addToGame(this);
        ball2.addToGame(this);
        ball3.addToGame(this);
        ball1.setVelocity(3, 5);
        ball2.setVelocity(3, 4);
        ball3.setVelocity(3, 3);
    }

    /**
     * Runs the main loop of the game.
     */

    public void run() {
        int framesPerSecond = 60;
        int millisecondsPerFrame = 1000 / framesPerSecond;
        while (this.remainingBlocks.getC() > 0 && this.remainingBalls.getC() > 0) {
            long startTime = System.currentTimeMillis();

            DrawSurface d = this.gui.getDrawSurface();
            this.sprites.drawAllOn(d);
            gui.show(d);
            this.sprites.notifyAllTimePassed();
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
        if (remainingBlocks.getC() == 0) {
            score.increase(100);
        }
        this.gui.close();
    }


}