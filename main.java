import java.util.Scanner;
import java.util.Random;

public class main {
    public static void PrintBoard(char b[][]){
        System.out.println("----------");
        System.out.println(b[0][0] + " | " + b[0][1] + " | " + b[0][2]);
        System.out.println("---------");
        System.out.println(b[1][0] + " | " + b[1][1] + " | " + b[1][2]);
        System.out.println("---------");
        System.out.println(b[2][0] + " | " + b[2][1] + " | " + b[2][2]);
        System.out.println("----------");
    }
    public static boolean isAvailable(int x[], int p){
        boolean flag = false;
        for(int i=0; i<x.length; i++){
            if(x[i] == p){
                flag = true;
                x[i] = 10;
                break;
            }
        }
        return flag;
    }
    public static boolean AnyPositionAvailable(int x[]){
        boolean flag = true;
        if(x[0]==10 && x[1]==10 &&x[2]==10 &&x[3]==10 &&x[4]==10 &&x[5]==10 &&x[6]==10 &&x[7]==10 &&x[8]==10){
            flag = false;
        }
        return flag;
    }
    public static String check_winner(char b[][]){
        String line = "";
        boolean continue_check = true;
        for(int i=0;i<8; i++){
            if(continue_check = true){
                switch(i){
                case 0: line += String.valueOf(b[0][0]) + b[0][1] + b[0][2]; 
                        break;
                case 1: line += String.valueOf(b[1][0]) + b[1][1] + b[1][2]; 
                        break;
                case 2: line += String.valueOf(b[2][0]) + b[2][1] + b[2][2]; 
                        break;
                case 3: line += String.valueOf(b[0][0]) + b[1][0] + b[2][0]; 
                        break;
                case 4: line += String.valueOf(b[0][1]) + b[1][1] + b[2][1]; 
                        break;
                case 5: line += String.valueOf(b[0][2]) + b[1][2] + b[2][2]; 
                        break;
                case 6: line += String.valueOf(b[0][0]) + b[1][1] + b[2][2]; 
                        break;
                case 7: line += String.valueOf(b[0][2]) + b[1][1] + b[2][0]; 
                        break;
                }
                if(line.equals("XXX") || line.equals("OOO")){
                    continue_check = false;
                    break;
                }
                line = "";
            }
        }
        if(continue_check == false){
                return line;
            }else{
                return "";
            }
    }
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();

        boolean computer_turn = true;
        String winner = "";
        char board[][] = {{' ', ' ', ' '}, {' ', ' ', ' '}, {' ', ' ', ' '}};
        int pos[] = {1,2,3,4,5,6,7,8,9};

        System.out.println("Enter your name: ");
        String player_name = sc.nextLine();

        while(true){
            System.out.println("Do you want to play Tic-Tac-Toe ? [y/n]: ");
            char ch = Character.toLowerCase(sc.next().charAt(0));
            if(ch == 'y'){
                PrintBoard(board);
                System.out.println("You play as 'X'.");
                while(true){
                    if(winner.equals("Player")){
                        System.out.println("Winner is 'X' [" + player_name + "].");
                        System.exit(0);
                    }else if(winner.equals("Computer")){
                        System.out.println("Winner is 'O' [Computer].");
                        System.exit(0);
                    }
                    System.out.println("Enter position [1-9]: ");
                    int input = sc.nextInt();
                    if(input<1 || input > 9){
                        System.out.println("Invalid position. Retry.");
                        continue;
                    }
                    if(isAvailable(pos, input) == true){
                        if(input <= 3){
                            board[0][input-1] = 'X';
                        }else if(input >3 && input <=6){
                            board[1][input-4] = 'X';
                        }else{
                            board[2][input-7] = 'X';
                        }
                    }else{
                        System.out.println("Invalid position. Retry.");
                        continue;
                    }

                    System.out.println("Player's Turn.");
                    PrintBoard(board);
                    
                    if((check_winner(board)).equals("XXX")) {
                        winner = "Player";
                        continue;
                    }else if((check_winner(board)).equals("OOO")) {
                        winner = "Computer";
                        continue;
                    }
                
                    while(computer_turn==true){
                        int y = rand.nextInt(9) + 1;
                        if(isAvailable(pos, y) == true || AnyPositionAvailable(pos) == false){
                            if(y <= 3){
                                board[0][y-1] = 'O';
                            }else if(y >3 && y <=6){
                                board[1][y-4] = 'O';
                            }else{
                                board[2][y-7] = 'O';
                            }
                            computer_turn = false;
                        }
                    }
                    System.out.println("Computer's Turn.");
                    computer_turn = true;
                    PrintBoard(board);
                    if((check_winner(board)).equals("XXX")) {
                        winner = "Player";
                        continue;
                    }else if((check_winner(board)).equals("OOO")) {
                        winner = "Computer";
                        continue;
                    }
                    if(AnyPositionAvailable(pos) == false){
                        System.out.println("Match is a draw.");
                        break;
                    }
                }
            }else if(ch == 'n'){
                System.exit(0);
            }else{
                System.out.println("Invalid choice. Retry.");
                continue;
            }
        }
    }
}

