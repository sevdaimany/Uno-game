import java.util.LinkedList;

/**
 * Player class represent a player in a game 
 * it holds a player cards
 * 
 * @author sevda imany
 * @version 0.0
 */
public class Player {
    // a list of player cards
    private LinkedList<Card> cards = new LinkedList<Card>();

    /**
     * get a list of player's cards
     * @return LinkedList<Card> player's cards
     */
    public LinkedList<Card> getCards() {
        return cards;
    }

}