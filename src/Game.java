import java.util.Scanner;

public class Game {

    public Game() {
        boolean playAgain = false;
        do {
            TicTacToe game = new TicTacToe();
            Scanner input = new Scanner(System.in);
            
            do {
                System.out.println("Enter row: ");
                int x = input.nextInt();

                System.out.println("Enter column: ");
                int y = input.nextInt();

                game.move(x, y, 'X');
                game.randomOpponentMove();
                System.out.println(game.toString());
            } while (!game.isGameOver());

            System.out.println("Play again? (y/n)");
            String yesOrNo = input.next();

            if (yesOrNo == "y")
                playAgain = true;

        } while (playAgain);
    }


    public static void main(String[] args) {
        new Game();
    }
}
