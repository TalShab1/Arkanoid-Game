package game;


public class Counter {
    private int c;

    /**
     * Constructs a new Counter.
     */
    public Counter() {
        this.c = 0;
    }

    /**
     * Increases the counter.
     *
     * @param number number to add.
     */
    public void increase(int number) {
        this.c += number;
    }

    /**
     * Decreases the counter.
     *
     * @param number number to subtract.
     */
    public void decrease(int number) {
        this.c -= number;
    }

    /**
     * Returns value of the counter.
     *
     * @return current counter value
     */
    public int getC() {
        return this.c;
    }
}