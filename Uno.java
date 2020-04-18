import java.util.Scanner;

public class Uno{

    public static void main(final String[] args){
      
         Game game;
        Scanner sc = new Scanner(System.in);
         while(true){
             menu();
          int choose = sc.nextInt();
          if(choose == 1){
            game = new Game();
            game.game();
          }
          else if(choose == 2){
              clearScreen();
              System.out.println("\n\n\t\t\t\t\t\t\t\t\t\t   "+" EndGame.");
              return;
          }
             
         }
    }

    public static void menu(){
        clearScreen();
        System.out.println("\n\n\t\t\t\t\t\t\t\t\t\t   "+ConsoleColors.GREEN_BOLD_BRIGHT+"Please choose:"+ConsoleColors.RESET);
        System.out.println("\n\n\t\t\t\t\t\t\t\t\t\t   "+ConsoleColors.GREEN_BOLD_BRIGHT+"1)Play online"+ConsoleColors.RESET+"\n\n\t\t\t\t\t\t\t\t\t\t   "+ConsoleColors.GREEN_BOLD_BRIGHT+"2)End game."+"\n\n\t\t\t\t\t\t\t\t\t\t   "+ConsoleColors.RESET);
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}