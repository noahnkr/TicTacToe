import java.util.Scanner;

public class Game {

    public static void main(String[] args) {
        boolean playAgain = false;
        do {
            TicTacToe game = new TicTacToe();
            Scanner input = new Scanner(System.in);
            System.out.println(game.toString());
            
            do {
                System.out.println("Enter position: (0-8)");
                int pos = input.nextInt();

                game.move(pos / 3, pos % 3, 1);
                int move = game.findBestMove();
                System.out.println(move);
                game.move(move / 3, pos % 3, -1);

                System.out.println(game.toString());
                
            } while (!game.isGameOver());

            System.out.println("Play again? (y/n)");
            String yesOrNo = input.next();

            if (yesOrNo == "y")
                playAgain = true;

        } while (playAgain);
    }


    
}
