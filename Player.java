import java.util.LinkedList;

public class Player {
    private int mainPlayer;
    private LinkedList<Card> cards = new LinkedList<Card>();

    public LinkedList<Card> getCards() {
        return cards;
    }

    public void setCards(LinkedList<Card> cards) {
        this.cards = cards;
    }
    
    public void addCard(Card card){
        cards.add(card);
    }

    public int getMainPlayer() {
        return mainPlayer;
    }

    public void setMainPlayer(int mainPlayer) {
        this.mainPlayer = mainPlayer;
    }
    
}