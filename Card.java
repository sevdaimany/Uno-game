/**
 * the Card class represent a card in a game
 *
 * @author sevda imany
 * @version 0.0
 */
public class Card {
    // if card doesnt have digit is -1
    private final int digital;
    // card's color , for wild card is black at first
    private  String color;
    //if card is skip card is 1 , otherwise 0
    private final int skip;
    //if card is +2 card is 1 , otherwise 0
    private final int takeTwo;
    //if card is reverse card is 1 , otherwise 0
    private final int reverse;
    //if card is blackActive card is 1 , otherwise 0
    private final int blackActive;
    //if card is +4 card is 1 , otherwise 0
    private final int blackActive4;

    /**
     * create a new card with given information
     * @param digital
     * @param color
     * @param skip
     * @param takeTwo
     * @param reverse
     * @param blackActive
     * @param blackActive4
     */
    public Card(int digital, String color, int skip, int takeTwo, int reverse, int blackActive, int blackActive4) {
        this.digital = digital;
        this.color = color;
        this.skip = skip;
        this.takeTwo = takeTwo;
        this.reverse = reverse;
        this.blackActive = blackActive;
        this.blackActive4 = blackActive4;
    }

    
    /** 
     * get card's digit
     * @return card's digit
     */
    public int getDigital() {
        return digital;
    }

    
    /** 
     * get card's color
     * @return card's color
     */
    public String getColor() {
        return color;
    }

    
    /** 
     * @return 1 if the card is skip otherwise 0
     */
    public int getSkip() {
        return skip;
    }

    
    /** 
     * @return 1 if the card is +2 otherwise 0
     */
    public int getTakeTwo() {
        return takeTwo;
    }

    
    /** 
     * @return 1 if the card is reverse otherwise 0
     */
    public int getReverse() {
        return reverse;
    }

    
    /** 
     * @return 1 if the card is blackActive otherwise 0
     */
    public int getBlackActive() {
        return blackActive;
    }

    
    /** 
     * @return 1 if the card is +4 otherwise 0
     */
    public int getBlackActive4() {
        return blackActive4;
    }

    
    /** 
     * set the card's color
     * @param color
     */
    public void setColor(String color) {
        this.color = color;
    }

}