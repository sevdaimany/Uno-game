
public class Card {
    private final int digital;
    private  final String color;
    private final int skip;
    private final int takeTwo;
    private final int reverse;
    private final int blackActive;
    private final int blackActive4;

    public Card(int digital, String color, int skip, int takeTwo, int reverse, int blackActive, int blackActive4) {
        this.digital = digital;
        this.color = color;
        this.skip = skip;
        this.takeTwo = takeTwo;
        this.reverse = reverse;
        this.blackActive = blackActive;
        this.blackActive4 = blackActive4;
    }

    public int getDigital() {
        return digital;
    }

    public String getColor() {
        return color;
    }

    public int getSkip() {
        return skip;
    }

    public int getTakeTwo() {
        return takeTwo;
    }

    public int getReverse() {
        return reverse;
    }

    public int getBlackActive() {
        return blackActive;
    }

    public int getBlackActive4() {
        return blackActive4;
    }

}