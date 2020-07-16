import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * Game class represet a game it holds a list of draw pile cards and a list of
 * players
 * 
 * @author sevda imany
 * @version 0.0
 */
public class Game {

    private LinkedList<Card> cards;
    private ArrayList<Player> players;
    private int numplayers;

    /**
     * get draw pile cards
     * 
     * @return LinkedList<Card>
     */
    public LinkedList<Card> getCards() {
        return cards;
    }

    /**
     * get a list of all players
     * 
     * @return ArrayList<Player>
     */
    public ArrayList<Player> getPlayers() {
        return players;
    }

    /**
     * creat a new game
     */
    public Game() {
        cards = new LinkedList<Card>();
        players = new ArrayList<Player>();
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    /**
     * this method play uno game
     * 
     * @param choose 1 for playing online and 2 for playing with friends
     */
    public void game(int choose) {
        Playground playground = new Playground();
        int playerTurn = 0;
        int turn = 0;
        startGame();
        if (cards.getFirst().getReverse() == 1)
            turn = 1;
        else if (cards.getFirst().getSkip() == 1)
            playerTurn++;

        else if (cards.getFirst().getTakeTwo() == 1) {
            for (int i = 0; i < 2; i++) {
                players.get(playerTurn).getCards().add(cards.getLast());
                cards.removeLast();
            }
        }
        playground.printPlayground(players, cards, turn, playerTurn, choose);

        int m = 0;

        while (true) {
            try {

                if (m > 10 && m < 20) {
                    for (int i = 0; i < 4 * (m - 10); i++) {
                        players.get(playerTurn).getCards().add(cards.getLast());
                        cards.removeLast();
                    }
                    if (turn == 0) {
                        playerTurn++;
                        if (playerTurn == numplayers)
                            playerTurn = 0;
                    } else if (turn == 1) {
                        
                        playerTurn--;
                        if (playerTurn == -1)
                            playerTurn = numplayers - 1;
                    }
                    m = 1;

                    clearScreen();
                    playground.printPlayground(players, cards, turn, playerTurn, choose);
                } else if (m > 20 && m < 30) {
                    for (int i = 0; i < 2 * (m - 20); i++) {
                        players.get(playerTurn).getCards().add(cards.getLast());
                        cards.removeLast();
                    }

                    clearScreen();
                    playground.printPlayground(players, cards, turn, playerTurn, choose);
                } else if (m == 4) {
                    for (int i = 0; i < 4; i++) {
                        players.get(playerTurn).getCards().add(cards.getLast());
                        cards.removeLast();
                    }
                    if (turn == 0) {
                        playerTurn++;
                        if (playerTurn == numplayers)
                            playerTurn = 0;
                    } else if (turn == 1) {
                        playerTurn--;
                        if (playerTurn == -1)
                            playerTurn = numplayers - 1;
                    }
                    m = 1;

                    clearScreen();
                    playground.printPlayground(players, cards, turn, playerTurn, choose);
                }

                if (m == 5) {
                    for (int i = 0; i < 2; i++) {
                        players.get(playerTurn).getCards().add(cards.getLast());
                        cards.removeLast();
                    }

                    clearScreen();
                    playground.printPlayground(players, cards, turn, playerTurn, choose);
                }

                m = choose(playerTurn, choose);

                if (m == 0) {
                    if (turn == 0)
                        turn = 1;
                    else
                        turn = 0;
                    m = 1;

                } else if (m == -1) {
                    players.get(playerTurn).getCards().add(cards.getLast());
                    cards.removeLast();
                    m = 1;
                } else if (m == 3) {

                    if (turn == 0) {
                        playerTurn++;
                        if (playerTurn == numplayers)
                            playerTurn = 0;
                    } else if (turn == 1) {
                        playerTurn--;
                        if (playerTurn == -1)
                            playerTurn = numplayers - 1;
                    }
                    m = 1;
                }

                if (turn == 0) {
                    playerTurn++;
                    if (playerTurn == numplayers)
                        playerTurn = 0;
                } else if (turn == 1) {
                    playerTurn--;
                    if (playerTurn == -1)
                        playerTurn = numplayers - 1;
                }

                TimeUnit.SECONDS.sleep(4);
                clearScreen();
                playground.printPlayground(players, cards, turn, playerTurn, choose);

                if (checkEndOfGame()) {
                    winner();
                    return;
                }

            } catch (InterruptedException e) {
                System.out.println("Error");
            }
        }
    }

    /**
     * this method is for starting a game and add players to players list it shuffle
     * all cards and give each player 7 cards and check that the first draw pile
     * card dont be wild!
     */
    private void startGame() {
        addAllCards();
        Collections.shuffle(cards);
        int numPlayers = numPlayers();
        for (int i = 0; i < numPlayers; i++) {
            players.add(new Player());
        }

        for (int i = 0; i < numPlayers; i++) {
            for (int j = 0; j < 7; j++) {
                players.get(i).getCards().add(cards.getFirst());
                cards.removeFirst();
            }
        }
        while (cards.getFirst().getColor().equals("black")) {
            cards.addLast(cards.getFirst());
            cards.removeFirst();
        }
    }

    /**
     * this method is for the first of a game and add 108 cards of a game to the
     * cards list
     */
    private void addAllCards() {
        for (int j = 0; j < 2; j++) {
            for (int i = 1; i < 10; i++) {
                cards.add(new Card(i, "red", 0, 0, 0, 0, 0));
            }
        }

        for (int j = 0; j < 2; j++) {
            for (int i = 1; i < 10; i++) {
                cards.add(new Card(i, "blue", 0, 0, 0, 0, 0));
            }
        }

        for (int j = 0; j < 2; j++) {
            for (int i = 1; i < 10; i++) {
                cards.add(new Card(i, "yellow", 0, 0, 0, 0, 0));
            }
        }

        for (int j = 0; j < 2; j++) {
            for (int i = 1; i < 10; i++) {
                cards.add(new Card(i, "green", 0, 0, 0, 0, 0));
            }
        }

        String color = null;
        for (int j = 0; j < 2; j++) {
            for (int k = 0; k < 4; k++) {
                if (k == 0)
                    color = "red";
                else if (k == 1)
                    color = "blue";
                else if (k == 2)
                    color = "yellow";
                else if (k == 3)
                    color = "green";
                for (int i = 0; i < 3; i++) {
                    int n = 0;
                    int m = 0;
                    int l = 0;
                    if (i == 0)
                        n = 1;
                    else if (i == 1)
                        m = 1;
                    else if (i == 2)
                        l = 1;
                    cards.add(new Card(-1, color, n, m, l, 0, 0));
                }
            }
        }

        for (int i = 0; i < 4; i++)
            cards.add(new Card(-1, "black", 0, 0, 0, 1, 0));

        for (int i = 0; i < 4; i++)
            cards.add(new Card(-1, "black", 0, 0, 0, 0, 1));

        for (int i = 0; i < 4; i++) {
            if (i == 0)
                color = "red";
            else if (i == 1)
                color = "blue";
            else if (i == 2)
                color = "yellow";
            else if (i == 3)
                color = "green";

            cards.add(new Card(0, color, 0, 0, 0, 0, 0));
        }
    }

    /**
     * this method ask user that wants to play with how many player
     * 
     * @return number of players
     */
    private int numPlayers() {
        Scanner sc = new Scanner(System.in);
        clearScreen();
        System.out.println("\n\n\t\t\t\t\t\t\t\t\t\t   " + ConsoleColors.YELLOW_BOLD_BRIGHT
                + "Please choose a number of players:(2 < n < 6)" + ConsoleColors.RESET);
        int numPlayer = sc.nextInt();
        this.numplayers = numPlayer;
        return numPlayer;
    }

    /**
     * this method choose a card for each player
     * 
     * @param player
     * @param chooseMenu if 1 play online and if 2 play with friends and ask each
     *                   player which card wants to choose
     * @return -1 if player can not play with his cards , 0 if the chosen card is
     *         reverse, 3 if the chosen card is skip ,4 if the chosen card is
     *         wildcard+4 and didnt com on another +4 5 if the chosen card is +2 and
     *         didnt come on another +2 , a number between 20 and 30 if the chosen
     *         card is +2 and come on another +2 (a num of +2 come on each other is
     *         n -20) a number between 10 and 20 if the chosen cards is +4 and come
     *         on another +4 (a num of +4 come on each other is (n -10) ) otherwise
     *         return 1
     */
    private int choose(int player, int chooseMenu) {

        if (chooseMenu == 1) {
            if (player == 0) {
                int n = playYourTurn(0);
                return n;

            } else {
                Iterator<Card> it = players.get(player).getCards().iterator();
                Random random = new Random();
                int rand = random.nextInt(4);
                String color = "red";
                if (rand == 0)
                    color = "red";
                else if (rand == 1)
                    color = "blue";
                else if (rand == 2)
                    color = "green";
                else if (rand == 3)
                    color = "yellow";

                if (cards.getFirst().getBlackActive4() == 1) {
                    while (it.hasNext()) {
                        Card card = it.next();
                        if (card.getBlackActive4() == 1) {
                            int n = 10;
                            cards.addFirst(card);
                            players.get(player).getCards().remove(card);
                            cards.getFirst().setColor(color);
                            Iterator<Card> it5 = cards.iterator();
                            while (it5.hasNext()) {
                                if (it5.next().getBlackActive4() == 1) {
                                    n++;
                                } else
                                    break;
                            }
                            return n;
                        }

                    }
                } else if (cards.getFirst().getTakeTwo() == 1) {
                    it = players.get(player).getCards().iterator();
                    while (it.hasNext()) {
                        Card card = it.next();
                        if (card.getTakeTwo() == 1) {
                            int n = 20;
                            cards.addFirst(card);
                            players.get(player).getCards().remove(card);
                            Iterator<Card> it6 = cards.iterator();
                            while (it6.hasNext()) {
                                if (it6.next().getTakeTwo() == 1) {
                                    n++;
                                } else
                                    break;
                            }
                            return n;
                        }

                    }
                }

                else if (cards.getFirst().getSkip() == 1) {
                    it = players.get(player).getCards().iterator();
                    while (it.hasNext()) {
                        Card card = it.next();
                        if (card.getSkip() == 1) {
                            cards.addFirst(card);
                            players.get(player).getCards().remove(card);
                            return 3;
                        }

                    }
                }

                else if (cards.getFirst().getReverse() == 1) {
                    it = players.get(player).getCards().iterator();
                    while (it.hasNext()) {
                        Card card = it.next();
                        if (card.getReverse() == 1) {
                            cards.addFirst(card);
                            players.get(player).getCards().remove(card);
                            return 0;
                        }

                    }
                } else {
                    it = players.get(player).getCards().iterator();
                    while (it.hasNext()) {
                        Card card = it.next();
                        if (card.getDigital() >= 0 && card.getDigital() == cards.getFirst().getDigital()) {
                            cards.addFirst(card);
                            players.get(player).getCards().remove(card);
                            return 1;
                        }

                        if (card.getColor().equals(cards.getFirst().getColor())) {
                            cards.addFirst(card);
                            players.get(player).getCards().remove(card);
                            if (cards.getFirst().getReverse() == 1)
                                return 0;

                            else if (cards.getFirst().getSkip() == 1)
                                return 3;

                            else if (cards.getFirst().getTakeTwo() == 1)
                                return 5;
                            else
                                return 1;
                        }
                    }
                }
                it = players.get(player).getCards().iterator();
                while (it.hasNext()) {
                    Card card = it.next();
                    if (card.getBlackActive() == 1) {
                        cards.addFirst(card);
                        players.get(player).getCards().remove(card);
                        cards.getFirst().setColor(color);
                        return 1;
                    }
                }

                it = players.get(player).getCards().iterator();
                while (it.hasNext()) {
                    Card card = it.next();
                    if (card.getBlackActive4() == 1) {
                        cards.addFirst(card);
                        players.get(player).getCards().remove(card);
                        cards.getFirst().setColor(color);
                        return 4;
                    }
                }

                return -1;
            }
        } else {
            int n = playYourTurn(player);
            return n;
        }
    }

  
    /**
     * this method check if the chosen card is acceptable or not
     * @param numCard
     * @param player
     * @return {@code true} if the chosen card is acceptable otherwise return {@code false} 
     */
    private boolean check(int numCard, int player) {
        if (players.get(player).getCards().get(numCard).getBlackActive4() == 1
                && cards.getFirst().getBlackActive4() == 1)
            return true;

        else if (players.get(player).getCards().get(numCard).getBlackActive4() == 1
                || players.get(player).getCards().get(numCard).getBlackActive() == 1) {
            if (!check(player))
                return true;

        }
        if (players.get(player).getCards().get(numCard).getReverse() == 1 && cards.getFirst().getReverse() == 1)
            return true;
        else if (players.get(player).getCards().get(numCard).getTakeTwo() == 1 && cards.getFirst().getTakeTwo() == 1)
            return true;
        else if (players.get(player).getCards().get(numCard).getSkip() == 1 && cards.getFirst().getSkip() == 1)
            return true;
        else if (players.get(player).getCards().get(numCard).getDigital() >= 0
                && players.get(player).getCards().get(numCard).getDigital() == cards.getFirst().getDigital())
            return true;
        else if (players.get(player).getCards().get(numCard).getColor().equals(cards.getFirst().getColor()))
            return true;

        return false;

    }

    /**
     * this method check if the player can play with his cards or not
     * @param player
     * @return {@code true} if the player has a choice to play otherwise return {@code false}
     */
    private boolean check(int player) {
        int n = players.get(player).getCards().size();
        for (int i = 0; i < n; i++) {
            if (players.get(player).getCards().get(i).getReverse() == 1 && cards.getFirst().getReverse() == 1)
                return true;
            else if (players.get(player).getCards().get(i).getTakeTwo() == 1 && cards.getFirst().getTakeTwo() == 1)
                return true;
            else if (players.get(player).getCards().get(i).getSkip() == 1 && cards.getFirst().getSkip() == 1)
                return true;
            else if (players.get(player).getCards().get(i).getDigital() >= 0
                    && players.get(player).getCards().get(i).getDigital() == cards.getFirst().getDigital())
                return true;
            else if (players.get(player).getCards().get(i).getColor().equals(cards.getFirst().getColor()))
                return true;

        }
        return false;
    }

   /**
     * this method check if the player can play with his cards or not
     * @param player
     * @return {@code true} if the player has a choice to play otherwise return {@code false}
     */
    private boolean checkPlay(int player) {
        int n = players.get(player).getCards().size();
        for (int i = 0; i < n; i++) {
            if (players.get(player).getCards().get(i).getBlackActive4() == 1
                    || players.get(player).getCards().get(i).getBlackActive() == 1)
                return true;
        }

        if (check(player))
            return true;

        return false;
    }

    /**
     * this method check if the game ends or nor
     * @return {@code true} if the game ends otherwise {@code false}
     */
    private boolean checkEndOfGame() {
        int numPlayer = players.size();
        for (int i = 0; i < numPlayer; i++) {
            if (players.get(i).getCards().size() == 0)
                return true;
        }
        return false;
    }

    /**
     * this method sum all players scores and print winner
     */
    private void winner() {
        ArrayList<Integer> scores = new ArrayList<Integer>();
        int numPlayers = players.size();
        for (int i = 0; i < numPlayers; i++) {
            int numPlayerCards = players.get(i).getCards().size();
            int sum = 0;
            for (int j = 0; j < numPlayerCards; j++) {
                sum += scores(players.get(i).getCards().get(j));
            }
            scores.add(sum);
        }
        printScores(scores);
        ArrayList<Integer> numwinners = new ArrayList<Integer>();
        int min = Collections.min(scores);
        for (int i = 0; i < numPlayers; i++) {
            if (min == scores.get(i))
                numwinners.add(i);
        }

        System.out.print("\n\n\t\t\t\t\t\t\t\t\t\t   ");
        for (int i = 0; i < numwinners.size(); i++) {
            System.out.print(ConsoleColors.PURPLE_BOLD_BRIGHT + "player" + (numwinners.get(i) + 1) + ", ");
        }
        System.out.println("win the game." + ConsoleColors.RESET);
        Scanner sc = new Scanner(System.in);
        sc.nextLine();
    }

    /**
     * this method give each card a score
     * @param card
     * @return score of given card 
     */
    private int scores(Card card) {
        int score = 0;
        if (card.getDigital() >= 0)
            score = card.getDigital();
        else if (card.getBlackActive() == 1 || card.getBlackActive4() == 1)
            score = 50;
        else
            score = 20;

        return score;
    }

    /**
     * this method print score of all players at the end of the game
     * @param scores
     */
    private void printScores(ArrayList<Integer> scores) {
        clearScreen();
        for (int i = 0; i < players.size(); i++) {
            System.out.println("\n\n\t\t\t\t\t\t\t\t\t\t   " + ConsoleColors.CYAN_BOLD_BRIGHT + "Player" + (i + 1)
                    + " scores: " + scores.get(i) + ConsoleColors.RESET);
        }
    }

    /**
     * this method ask player to choose a card to play and check that the card be acceptable
     * @param player
     * @return -1 if player can not play with his cards , 0 if the chosen card is
     *         reverse, 3 if the chosen card is skip ,4 if the chosen card is
     *         wildcard+4 and didnt com on another +4 5 if the chosen card is +2 and
     *         didnt come on another +2 , a number between 20 and 30 if the chosen
     *         card is +2 and come on another +2 (a num of +2 come on each other is
     *         n -20) a number between 10 and 20 if the chosen cards is +4 and come
     *         on another +4 (a num of +4 come on each other is (n -10) ) otherwise
     *         return 1
     */
    private int playYourTurn(int player) {
        if (checkPlay(player) == false)
            return -1;

        Scanner sc = new Scanner(System.in);
        System.out.println(ConsoleColors.WHITE_BOLD_BRIGHT + "please choose your card: " + ConsoleColors.RESET);
        int choose = sc.nextInt();
        while (!check(choose - 1, player)) {
            System.out.println(ConsoleColors.WHITE_BOLD_BRIGHT + "please choose another card: " + ConsoleColors.RESET);
            choose = sc.nextInt();
        }

        cards.addFirst(players.get(player).getCards().get(choose - 1));
        players.get(player).getCards().remove(choose - 1);
        if (cards.getFirst().getBlackActive4() == 1 || cards.getFirst().getBlackActive() == 1) {
            sc.nextLine();
            System.out.println(ConsoleColors.WHITE_BOLD_BRIGHT + "Please choose a color: " + ConsoleColors.RESET);
            String color = sc.nextLine();
            cards.getFirst().setColor(color);
        }

        if (cards.getFirst().getReverse() == 1)
            return 0;
        else if (cards.getFirst().getBlackActive4() == 1 && cards.get(1).getBlackActive4() == 1) {
            int n = 10;
            Iterator<Card> it5 = cards.iterator();
            while (it5.hasNext()) {
                if (it5.next().getBlackActive4() == 1) {
                    n++;
                } else
                    break;
            }
            return n;
        } else if (cards.getFirst().getTakeTwo() == 1 && cards.get(1).getTakeTwo() == 1) {
            int n = 20;
            Iterator<Card> it6 = cards.iterator();
            while (it6.hasNext()) {
                if (it6.next().getTakeTwo() == 1) {
                    n++;
                } else
                    break;
            }
            return n;
        } else if (cards.getFirst().getSkip() == 1)
            return 3;
        else if (cards.getFirst().getTakeTwo() == 1) {
            return 5;
        } else if (cards.getFirst().getBlackActive4() == 1)
            return 4;
        else
            return 1;

    }
}