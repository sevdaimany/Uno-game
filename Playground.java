import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class Playground {
    public void printCard(Card card, int n, int numCard) {

        String space = "";
        if (n == 0) {
            space = "\t\t\t\t\t  ";
        } else if (n == 1) {
            space = "\t\t\t\t\t\t\t\t\t\t  ";
        }
        if (n == 1) {
            System.out.println("\t\t\t\t\t\t\t\t\t\t draw pile cards:\n");
            if(card.getBlackActive()==1 || card.getBlackActive4() == 1){
            System.out.println("\t\t\t\t\t\t\t\t\t\t color is red\n");
            }
        }
        if (n == 0) {
            System.out.println("\t\t\t\t     "+ numCard + ")\n");
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

    public void printPlayground(ArrayList<Player> players, LinkedList<Card> cards, int turn, int playerTurn) {
        Iterator<Card> it = players.get(0).getCards().iterator();
        Iterator<Player> itPlayer = players.iterator();

        System.out.println("\n");
        System.out.println("\t\t\t\t\t Player1 cards: \n");
        int n = 1;
        while (it.hasNext()) {
            printCard(it.next(), 0, n);
            System.out.println("");
            n++;
        }

        printCard(cards.get(0), 1, -1);
        System.out.println("\n");

        int k = 2;
        itPlayer.next();
        System.out.print("\t\t\t\t\t ");
        while (itPlayer.hasNext()) {
            System.out.print("player" + k + " cards: " + itPlayer.next().getCards().size());
            k++;
            System.out.print("\t\t");
        }
        System.out.println("\n");
        if (turn == 0)
            System.out.println("\t\t\t\t\t\t\t\t\t " + "clockwise");
        else if (turn == 1)
            System.out.println("\t\t\t\t\t\t\t\t\t " + "anticlockwise");

        System.out.println("\t\t\t\t\t\t\t\t\t " + "player" + (playerTurn + 1) + " turn: ");

    }

}