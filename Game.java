import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
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

        int m = 1;

        while (true) {
            try {
                for (int i = 0; i < m; i++) {
                    play(playerTurn);
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
                            playerTurn = numplayers-1;
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
                        playerTurn = numplayers-1;
                }

                TimeUnit.SECONDS.sleep(4);
                clearScreen();
                playground.printPlayground(players, cards, turn, playerTurn);

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
        System.out.println("\n\n\t\t\t\t\t\t\t\t\t\t   " + "Please choose a number of players:(2 < n < 6)");
        int numPlayer = sc.nextInt();
        this.numplayers = numPlayer;
        return numPlayer;
    }

    public int choose(int player) {

        if (player == 0) {
            if (checkPlay() == false)
                return -1;

            Scanner sc = new Scanner(System.in);
            System.out.println("please choose your card: ");
            int choose = sc.nextInt();
            while (!check(choose - 1)) {
                System.out.println("please choose another card: ");
                choose = sc.nextInt();
            }

            cards.addFirst(players.get(0).getCards().get(choose - 1));
            players.get(0).getCards().remove(choose - 1);
            if (cards.getFirst().getBlackActive4() == 1 || cards.getFirst().getBlackActive() == 1) {
                sc.nextLine();
                System.out.println("Please choose a color: ");
                String color = sc.nextLine();
                cards.getFirst().setColor(color);
            }

            if (cards.getFirst().getReverse() == 1)
                return 0;
            else if ((cards.getFirst().getBlackActive4() == 1 && cards.get(1).getBlackActive4() == 1)
                    || (cards.getFirst().getTakeTwo() == 1 && cards.get(1).getTakeTwo() == 1))
                return 2;
            else if (cards.getFirst().getSkip() == 1)
                return 3;
            else
                return 1;

        } else {
            Iterator<Card> it = players.get(player).getCards().iterator();

            if (cards.getFirst().getBlackActive4() == 1) {
                while (it.hasNext()) {
                    Card card = it.next();
                    if (card.getBlackActive4() == 1) {
                        cards.addFirst(card);
                        players.get(player).getCards().remove(card);
                        cards.getFirst().setColor("red");
                        return 2;
                    }

                }
            } else if (cards.getFirst().getTakeTwo() == 1) {
                it = players.get(player).getCards().iterator();
                while (it.hasNext()) {
                    Card card = it.next();
                    if (card.getTakeTwo() == 1) {
                        cards.addFirst(card);
                        players.get(player).getCards().remove(card);
                        return 2;
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
                    cards.getFirst().setColor("red");
                    return 1;
                }
            }

            it = players.get(player).getCards().iterator();
            while (it.hasNext()) {
                Card card = it.next();
                if (card.getBlackActive4() == 1) {
                    cards.addFirst(card);
                    players.get(player).getCards().remove(card);
                    cards.getFirst().setColor("red");
                    return 1;
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
        int max = Collections.max(scores);
        for (int i = 0; i < numPlayers; i++) {
            if (max == scores.get(i))
                numwinners.add(i);
        }
        for (int i = 0; i < numwinners.size(); i++) {
            System.out.print("player" + (i + 1) + ", ");
        }
        System.out.println("win the game.");
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
        for (int i = 0; i < players.size(); i++) {
            System.out.println("Player" + (i + 1) + " scores: " + scores.get(i));
        }
    }
}