import java.util.Scanner;

/**
 * this class represent the uno game
 * 
 * @author sevda imany
 * @version 0.0
 */
public class Uno{

    
    
   
    public static void main(final String[] args){
      
         Game game;
        Scanner sc = new Scanner(System.in);
         while(true){
             menu();
          int choose = sc.nextInt();
          if(choose == 1 || choose == 2){
            game = new Game();
            game.game(choose);
          }
          else if(choose == 3){
              clearScreen();
              System.out.println("\n\n\t\t\t\t\t\t\t\t\t\t   "+" EndGame.");
              return;
          }
        }
    }

    /**
     * this method prints menu
     */
    public static void menu(){
        clearScreen();
        System.out.println("\n\n\t\t\t\t\t\t\t\t\t\t   "+ConsoleColors.GREEN_BOLD_BRIGHT+"Please choose:"+ConsoleColors.RESET);
        System.out.println("\n\n\t\t\t\t\t\t\t\t\t\t   "+ConsoleColors.GREEN_BOLD_BRIGHT+"1)Play with Ai"+ConsoleColors.RESET+"\n\n\t\t\t\t\t\t\t\t\t\t   "+ConsoleColors.GREEN_BOLD_BRIGHT+"2)play with friends."+ConsoleColors.RESET+"\n\n\t\t\t\t\t\t\t\t\t\t   "+ConsoleColors.GREEN_BOLD_BRIGHT+"3)End game."+"\n\n\t\t\t\t\t\t\t\t\t\t   "+ConsoleColors.RESET);
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}