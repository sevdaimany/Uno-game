import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Game {

    private LinkedList<Card> cards;
    private ArrayList<Player> players;
    private int numplayers;

    public LinkedList<Card> getCards() {
        return cards;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public Game() {
        cards = new LinkedList<Card>();
        players = new ArrayList<Player>();
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public void game() {
        Playground playground = new Playground();
        int playerTurn = 0;
        int turn = 0;
        startGame();
        if (cards.getFirst().getReverse() == 1)
            turn = 1;
        else if (cards.getFirst().getSkip() == 1)
            playerTurn++;
        play(playerTurn);
        playground.printPlayground(players, cards, turn, playerTurn);

        int m = 0;

        while (true) {
            try {
                /*
                 * for (int i = 0; i < m; i++) { play(playerTurn); clearScreen();
                 * playground.printPlayground(players, cards, turn, playerTurn); }
                 */
                if (m > 10 && m < 20) {
                    for (int i = 0; i < 4 * (m - 10); i++) {
                        players.get(playerTurn).addCard(cards.getLast());
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
                    playground.printPlayground(players, cards, turn, playerTurn);
                } else if (m > 20 && m < 30) {
                    for (int i = 0; i < 2 * (m - 20); i++) {
                        players.get(playerTurn).addCard(cards.getLast());
                        cards.removeLast();
                    }

                    clearScreen();
                    playground.printPlayground(players, cards, turn, playerTurn);
                } else if (m == 4) {
                    for (int i = 0; i < 4; i++) {
                        players.get(playerTurn).addCard(cards.getLast());
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
                    playground.printPlayground(players, cards, turn, playerTurn);
                }

                if (m == 5) {
                    for (int i = 0; i < 2; i++) {
                        players.get(playerTurn).addCard(cards.getLast());
                        cards.removeLast();
                    }

                    clearScreen();
                    playground.printPlayground(players, cards, turn, playerTurn);
                }

                m = choose(playerTurn);

                if (m == 0) {
                    if (turn == 0)
                        turn = 1;
                    else
                        turn = 0;
                    m = 1;

                } else if (m == -1) {
                    players.get(playerTurn).addCard(cards.getLast());
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
                playground.printPlayground(players, cards, turn, playerTurn);

                if (endOfGame()) {
                    winner();
                    return;
                }

            } catch (InterruptedException e) {
                System.out.println("Error");
            }
        }
    }

    public void startGame() {
        addAllCards();
        Collections.shuffle(cards);
        int numPlayers = numPlayers();
        for (int i = 0; i < numPlayers; i++) {
            players.add(new Player());
        }

        for (int i = 0; i < numPlayers; i++) {
            for (int j = 0; j < 7; j++) {
                players.get(i).addCard(cards.getFirst());
                cards.removeFirst();
            }
        }
        while (cards.getFirst().getColor().equals("black")) {
            cards.addLast(cards.getFirst());
            cards.removeFirst();
        }
    }

    public void addAllCards() {
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

    public int numPlayers() {
        Scanner sc = new Scanner(System.in);
        clearScreen();
        System.out.println("\n\n\t\t\t\t\t\t\t\t\t\t   " +ConsoleColors.YELLOW_BOLD_BRIGHT+ "Please choose a number of players:(2 < n < 6)"+ConsoleColors.RESET);
        int numPlayer = sc.nextInt();
        this.numplayers = numPlayer;
        return numPlayer;
    }

    public int choose(int player) {

        if (player == 0) {
            if (checkPlay() == false)
                return -1;

            Scanner sc = new Scanner(System.in);
            System.out.println(ConsoleColors.WHITE_BOLD_BRIGHT+"please choose your card: "+ConsoleColors.RESET);
            int choose = sc.nextInt();
            while (!check(choose - 1)) {
                System.out.println(ConsoleColors.WHITE_BOLD_BRIGHT+"please choose another card: "+ConsoleColors.RESET);
                choose = sc.nextInt();
            }

            cards.addFirst(players.get(0).getCards().get(choose - 1));
            players.get(0).getCards().remove(choose - 1);
            if (cards.getFirst().getBlackActive4() == 1 || cards.getFirst().getBlackActive() == 1) {
                sc.nextLine();
                System.out.println(ConsoleColors.WHITE_BOLD_BRIGHT+"Please choose a color: "+ConsoleColors.RESET);
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
    }

    public void play(int turn) {

        if (cards.getFirst().getBlackActive4() == 1) {
            for (int i = 0; i < 4; i++) {
                players.get(turn).addCard(cards.getLast());
                cards.removeLast();
            }
        }

        else if (cards.getFirst().getTakeTwo() == 1) {
            for (int i = 0; i < 2; i++) {
                players.get(turn).addCard(cards.getLast());
                cards.removeLast();
            }
        }

    }

    public boolean check(int numCard) {
        if (players.get(0).getCards().get(numCard).getBlackActive4() == 1 && cards.getFirst().getBlackActive4() == 1)
            return true;

        else if (players.get(0).getCards().get(numCard).getBlackActive4() == 1
                || players.get(0).getCards().get(numCard).getBlackActive() == 1) {
            if (!check())
                return true;

        }
        if (players.get(0).getCards().get(numCard).getReverse() == 1 && cards.getFirst().getReverse() == 1)
            return true;
        else if (players.get(0).getCards().get(numCard).getTakeTwo() == 1 && cards.getFirst().getTakeTwo() == 1)
            return true;
        else if (players.get(0).getCards().get(numCard).getSkip() == 1 && cards.getFirst().getSkip() == 1)
            return true;
        else if (players.get(0).getCards().get(numCard).getDigital() >= 0
                && players.get(0).getCards().get(numCard).getDigital() == cards.getFirst().getDigital())
            return true;
        else if (players.get(0).getCards().get(numCard).getColor().equals(cards.getFirst().getColor()))
            return true;

        return false;

    }

    public boolean check() {
        int n = players.get(0).getCards().size();
        for (int i = 0; i < n; i++) {
            if (players.get(0).getCards().get(i).getReverse() == 1 && cards.getFirst().getReverse() == 1)
                return true;
            else if (players.get(0).getCards().get(i).getTakeTwo() == 1 && cards.getFirst().getTakeTwo() == 1)
                return true;
            else if (players.get(0).getCards().get(i).getSkip() == 1 && cards.getFirst().getSkip() == 1)
                return true;
            else if (players.get(0).getCards().get(i).getDigital() >= 0
                    && players.get(0).getCards().get(i).getDigital() == cards.getFirst().getDigital())
                return true;
            else if (players.get(0).getCards().get(i).getColor().equals(cards.getFirst().getColor()))
                return true;

        }
        return false;
    }

    public boolean checkPlay() {
        int n = players.get(0).getCards().size();
        for (int i = 0; i < n; i++) {
            if (players.get(0).getCards().get(i).getBlackActive4() == 1
                    || players.get(0).getCards().get(i).getBlackActive() == 1)
                return true;
        }

        if (check())
            return true;

        return false;
    }

    public boolean endOfGame() {
        int numPlayer = players.size();
        for (int i = 0; i < numPlayer; i++) {
            if (players.get(i).getCards().size() == 0)
                return true;
        }
        return false;
    }

    public void winner() {
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

        
        System.out.println("\n\n\t\t\t\t\t\t\t\t\t\t   " );
        for (int i = 0; i < numwinners.size(); i++) {
            System.out.print(ConsoleColors.PURPLE_BOLD_BRIGHT +"player" + (numwinners.get(i) + 1) + ", ");
        }
        System.out.println("win the game." + ConsoleColors.RESET);
        Scanner sc = new Scanner(System.in);
        sc.nextLine();
    }

    public int scores(Card card) {
        int score = 0;
        if (card.getDigital() >= 0)
            score = card.getDigital();
        else if (card.getBlackActive() == 1 || card.getBlackActive4() == 1)
            score = 50;
        else
            score = 20;

        return score;
    }

    public void printScores(ArrayList<Integer> scores) {
        clearScreen();
        for (int i = 0; i < players.size(); i++) {
            System.out.println("\n\n\t\t\t\t\t\t\t\t\t\t   " +ConsoleColors.CYAN_BOLD_BRIGHT +"Player" + (i + 1) + " scores: " + scores.get(i) + ConsoleColors.RESET);
        }
    }

}