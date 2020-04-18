import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * this class represent a playground in the game
 * 
 * @author sevda imany
 * @version 0.0
 */
public class Playground {
    
    /** 
     * this method print a card
     * @param card
     * @param n 0 if the card is player card and 1 if is the draw pile card
     * @param numCard
     */
    public void printCard(Card card, int n, int numCard) {

        String space = "";
        if (n == 0) {
            space = "\t\t\t\t\t  ";
        } else if (n == 1) {
            space = "\t\t\t\t\t\t\t\t\t\t  ";
        }
        if (n == 1) {
            System.out.println(
                    ConsoleColors.YELLOW_BOLD_BRIGHT + "\t\t\t\t\t\t\t\t\t\t draw pile cards:\n" + ConsoleColors.RESET);
            if (card.getBlackActive() == 1 || card.getBlackActive4() == 1) {
                System.out.println("\t\t\t\t\t\t\t\t\t\t color is " + card.getColor() + "\n");
            }
        }
        if (n == 0) {
            System.out.println("\t\t\t\t     " + ConsoleColors.WHITE_BOLD_BRIGHT + numCard + ")" + ConsoleColors.RESET);
        }
        if (card.getDigital() >= 0) {

            changeColor(card.getColor());
            System.out.println(space + "               " + ConsoleColors.RESET);
            changeColor(card.getColor());
            System.out.println(space + "       " + card.getDigital() + "       " + ConsoleColors.RESET);
            changeColor(card.getColor());
            System.out.println(space + "               " + ConsoleColors.RESET);

        } else if (card.getReverse() == 1) {
            changeColor(card.getColor());
            System.out.println(space + "               " + ConsoleColors.RESET);
            changeColor(card.getColor());
            System.out.println(space + "    " + "reverse" + "    " + ConsoleColors.RESET);
            changeColor(card.getColor());
            System.out.println(space + "               " + ConsoleColors.RESET);
        }

        else if (card.getSkip() == 1) {

            changeColor(card.getColor());
            System.out.println(space + "               " + ConsoleColors.RESET);
            changeColor(card.getColor());
            System.out.println(space + "     " + "skip" + "      " + ConsoleColors.RESET);
            changeColor(card.getColor());
            System.out.println(space + "               " + ConsoleColors.RESET);
        }

        else if (card.getTakeTwo() == 1) {

            changeColor(card.getColor());
            System.out.println(space + "               " + ConsoleColors.RESET);
            changeColor(card.getColor());
            System.out.println(space + "      " + "+2" + "       " + ConsoleColors.RESET);
            changeColor(card.getColor());
            System.out.println(space + "               " + ConsoleColors.RESET);
        }

        else if (card.getBlackActive() == 1) {

            changeColor("black");
            System.out.println(space + "               " + ConsoleColors.RESET);
            changeColor("black");
            System.out.println(space + " " + "change color" + "  " + ConsoleColors.RESET);
            changeColor("black");
            System.out.println(space + "               " + ConsoleColors.RESET);
        }

        else if (card.getBlackActive4() == 1) {

            changeColor("black");
            System.out.println(space + "               " + ConsoleColors.RESET);
            changeColor("black");
            System.out.println(space + "      " + "+4" + "       " + ConsoleColors.RESET);
            changeColor("black");
            System.out.println(space + "               " + ConsoleColors.RESET);
        }
    }

    
    /** 
     * this method change console's color
     * @param color
     */
    public void changeColor(String color) {

        if (color.equals("red"))
            System.out.print(ConsoleColors.RED_BACKGROUND_BRIGHT);

        else if (color.equals("yellow"))
            System.out.print(ConsoleColors.YELLOW_BACKGROUND_BRIGHT);

        else if (color.equals("green"))
            System.out.print(ConsoleColors.GREEN_BACKGROUND_BRIGHT);

        else if (color.equals("blue"))
            System.out.print(ConsoleColors.BLUE_BACKGROUND_BRIGHT);

        else if (color.equals("black"))
            System.out.print(ConsoleColors.BLACK_BACKGROUND_BRIGHT);

    }

    
    /** 
     * this method print a playground
     * @param players a list of all players
     * @param cards list of draw pile cards
     * @param turn  0 if game is clockwise  and 1 if anticlockwise
     * @param playerTurn
     * @param choose 1 if play online and 2 if game is play with freinds
     */
    public void printPlayground(ArrayList<Player> players, LinkedList<Card> cards, int turn, int playerTurn,
            int choose) {
        Iterator<Card> it;
        if (choose == 1)
            it = players.get(0).getCards().iterator();
        else
            it = players.get(playerTurn).getCards().iterator();

        Iterator<Player> itPlayer = players.iterator();

        System.out.println("\n");
        if (choose == 1)
            System.out.println(
                    ConsoleColors.WHITE_BOLD_BRIGHT + "\t\t\t\t\t Player1 cards: \n" + ConsoleColors.RED_BOLD_BRIGHT);
        else
            System.out.println(ConsoleColors.WHITE_BOLD_BRIGHT + "\t\t\t\t\t Player" + (playerTurn + 1) + " cards: \n"
                    + ConsoleColors.RED_BOLD_BRIGHT);

        int n = 1;
        while (it.hasNext()) {
            printCard(it.next(), 0, n);
            System.out.println("");
            n++;
        }

        printCard(cards.get(0), 1, -1);
        System.out.println("\n");
        if (choose == 1) {
            int k = 2;
            itPlayer.next();
            System.out.print("\t\t\t\t\t ");
            while (itPlayer.hasNext()) {
                System.out.print(ConsoleColors.GREEN_BOLD_BRIGHT + "player" + k + " cards: "
                        + itPlayer.next().getCards().size() + ConsoleColors.RESET);
                k++;
                System.out.print("\t\t\t");
            }
        } else {
            int k = 1;
            System.out.print("\t\t\t\t\t ");
            while (itPlayer.hasNext()) {
                if (k == playerTurn + 1) {
                    itPlayer.next();
                    k++;
                    continue;
                } else {
                    itPlayer.next();
                    System.out.print(ConsoleColors.GREEN_BOLD_BRIGHT + "player" + k + " cards: "
                            + players.get(k - 1).getCards().size() + ConsoleColors.RESET);
                    k++;
                    System.out.print("\t\t\t");
                }
            }
        }
        System.out.println("\n");
        if (turn == 0)
            System.out.println(
                    "\t\t\t\t\t\t\t\t\t\t " + ConsoleColors.BLUE_BOLD_BRIGHT + "clockwise" + ConsoleColors.RESET);
        else if (turn == 1)
            System.out.println(
                    "\t\t\t\t\t\t\t\t\t\t " + ConsoleColors.BLUE_BOLD_BRIGHT + "anticlockwise" + ConsoleColors.RESET);

        System.out.println("\n\t\t\t\t\t\t\t\t\t\t " + ConsoleColors.YELLOW_BOLD_BRIGHT + "player" + (playerTurn + 1)
                + " turn: " + ConsoleColors.RESET);

    }

}