import java.util.ArrayList;

public class Playground {
    public void printCard(Card card){
        
    String space = "\t\t\t\t\t\t\t\t\t\t   ";
        if(card.getDigital() >= 0){
          
           changeColor(card.getColor());
           System.out.println("               " + ConsoleColors.RESET);
           changeColor(card.getColor());
           System.out.println("       "+ card.getDigital() + "       "+ConsoleColors.RESET);
           changeColor(card.getColor());
           System.out.println("               " + ConsoleColors.RESET);
           
            
          
        }
        else if(card.getReverse() == 1){
            changeColor(card.getColor());
            System.out.println("               " + ConsoleColors.RESET);
            changeColor(card.getColor());
            System.out.println("    "+ "reverse" + "    "+ConsoleColors.RESET);
            changeColor(card.getColor());
            System.out.println("               " + ConsoleColors.RESET);
        }


        else if(card.getSkip() == 1){
             
           changeColor(card.getColor());
           System.out.println("               " + ConsoleColors.RESET);
           changeColor(card.getColor());
           System.out.println("     "+ "skip" + "      "+ConsoleColors.RESET);
           changeColor(card.getColor());
           System.out.println("               " + ConsoleColors.RESET);
        }

        else if(card.getTakeTwo() == 1){
             
           changeColor(card.getColor());
           System.out.println("               " + ConsoleColors.RESET);
           changeColor(card.getColor());
           System.out.println("      "+ "+2" + "       "+ConsoleColors.RESET);
           changeColor(card.getColor());
           System.out.println("               " + ConsoleColors.RESET);
        }

        else if(card.getBlackActive() == 1){
             
           changeColor("black");
           System.out.println("               " + ConsoleColors.RESET);
           changeColor("black");
           System.out.println(" "+ "change color" + "  "+ConsoleColors.RESET);
           changeColor("black");
           System.out.println("               " + ConsoleColors.RESET);
        }

        else if(card.getBlackActive4() == 1){
 
            changeColor("black");
            System.out.println("               " + ConsoleColors.RESET);
            changeColor("black");
            System.out.println("      "+ "+4" + "       "+ConsoleColors.RESET);
            changeColor("black");
            System.out.println("               " + ConsoleColors.RESET);
        }
    }

    public void changeColor(String color){
      
       if(color.equals("red"))
        System.out.print(ConsoleColors.RED_BACKGROUND_BRIGHT);

        else if(color.equals("yellow"))
        System.out.print(ConsoleColors.YELLOW_BACKGROUND_BRIGHT);

        else if(color.equals("green"))
        System.out.print(ConsoleColors.GREEN_BACKGROUND_BRIGHT);

        else if(color.equals("blue"))
        System.out.print(ConsoleColors.BLUE_BACKGROUND_BRIGHT);

        else if(color.equals("black"))
        System.out.print(ConsoleColors.BLACK_BACKGROUND_BRIGHT);

    
        
    }

    public void printPlayground(ArrayList<Player> players){

    }


}