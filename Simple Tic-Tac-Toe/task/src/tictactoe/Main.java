package tictactoe;

import java.util.Scanner;

public class Main {

    // Keeping count of number of plays that has happened during the game.
    static int gameRun = 0;

    public static void main(String[] args) {
        /*
        Reads in user inputs in character array form from standard input.
         */
        Scanner scanner = new Scanner(System.in);
        char[] userInput = {' ',' ',' ',' ',' ',' ',' ',' ',' '};

        printCurrentGameState(userInput);
        getUserInputForNextTurnAndUpdateGameState(scanner, userInput);
        printCurrentGameState(userInput);
        while (!checkGameWinner(userInput)) {
            getUserInputForNextTurnAndUpdateGameState(scanner, userInput);
            checkGameWinner(userInput);
            printCurrentGameState(userInput);
        }
    }

    /*
    Interactive sessions getting user inputs into empty slots

    */
    private static void getUserInputForNextTurnAndUpdateGameState(Scanner scanner, char[] userInput) {
        int yCord = 0;
        int xCord = 0;

        boolean validCord;
        do {
            System.out.println("Enter the coordinates: ");
            try {
                xCord = Integer.parseInt(scanner.next());
                yCord = Integer.parseInt(scanner.next());
            } catch (NumberFormatException e) {

            }


            if ((xCord > 0 && xCord < 4) && (yCord > 0 && yCord < 4)) {
                validCord = true;
                if (xCord == 1 && userInput[xCord + yCord - 2] == 'X' ||
                        xCord == 2 && userInput[xCord + yCord] == 'X' ||
                        xCord == 3 && userInput[xCord + yCord + 2] == 'X' ||
                        xCord == 1 && userInput[xCord + yCord - 2] == 'O' ||
                        xCord == 2 && userInput[xCord + yCord] == 'O' ||
                        xCord == 3 && userInput[xCord + yCord + 2] == 'O') {
                    System.out.println("This cell is occupied! Choose another one!");
                } else break;
            } else System.out.println("Coordinates should be from 1 to 3!");

            if (Character.isAlphabetic(xCord) || Character.isAlphabetic(yCord)) {
                System.out.println("You should enter numbers!");
            }
        } while (true);


        /*
        Upon supply of valid coordinates, the game is updated appropriately
         */
        if (validCord) {
            gameRun++;
            char checkRunPlayer = gameRun % 2 == 0 ? 'O' : 'X';

            switch (xCord) {
                case 1:
                    userInput[xCord + yCord - 2] = checkRunPlayer;
                    break;

                case 2:
                    userInput[xCord + yCord] = checkRunPlayer;
                    break;

                case 3:
                    userInput[xCord + yCord + 2] = checkRunPlayer;
                    break;

                default:
                    break;
            }
        }
    }

    private static boolean checkGameWinner(char[] userInput) {
        int countX = 0;
        int countO = 0;

        // X Wins Conditions
        boolean xWins = userInput[0] == 'X' && userInput[1] == 'X' && userInput[2] == 'X' ||
                userInput[3] == 'X' && userInput[4] == 'X' && userInput[5] == 'X' ||
                userInput[6] == 'X' && userInput[7] == 'X' && userInput[8] == 'X' ||
                userInput[0] == 'X' && userInput[3] == 'X' && userInput[6] == 'X' ||
                userInput[1] == 'X' && userInput[4] == 'X' && userInput[7] == 'X' ||
                userInput[2] == 'X' && userInput[5] == 'X' && userInput[8] == 'X' ||
                userInput[0] == 'X' && userInput[4] == 'X' && userInput[8] == 'X' ||
                userInput[2] == 'X' && userInput[4] == 'X' && userInput[6] == 'X';

        // O Wind Conditions
        boolean oWins = userInput[0] == 'O' && userInput[1] == 'O' && userInput[2] == 'O' ||
                userInput[3] == 'O' && userInput[4] == 'O' && userInput[5] == 'O' ||
                userInput[6] == 'O' && userInput[7] == 'O' && userInput[8] == 'O' ||
                userInput[0] == 'O' && userInput[3] == 'O' && userInput[6] == 'O' ||
                userInput[1] == 'O' && userInput[4] == 'O' && userInput[7] == 'O' ||
                userInput[2] == 'O' && userInput[5] == 'O' && userInput[8] == 'O' ||
                userInput[0] == 'O' && userInput[4] == 'O' && userInput[8] == 'O' ||
                userInput[2] == 'O' && userInput[4] == 'O' && userInput[6] == 'O';

        // Dash '_' Present Scenario
        boolean dash = userInput[0] == '_' || userInput[1] == '_' || userInput[2] == '_' ||
                userInput[3] == '_' || userInput[4] == '_' || userInput[5] == '_' ||
                userInput[6] == '_' || userInput[7] == '_' || userInput[8] == '_';

        // Blank ' ' Present Scenario
        boolean blank = userInput[0] == ' ' || userInput[1] == ' ' || userInput[2] == ' ' ||
                userInput[3] == ' ' || userInput[4] == ' ' || userInput[5] == ' ' ||
                userInput[6] == ' ' || userInput[7] == ' ' || userInput[8] == ' ';

        // Checking one-sided game and computes the excesses
        for (int count = 0; count < userInput.length; count++) {
            if (userInput[count] == 'X') {
                countX++;
            }

            if (userInput[count] == 'O') {
                countO++;
            }
        }
        boolean oneSided = Math.abs(countX - countO) > 1;

        if (xWins && oWins || oneSided) {
            System.out.println("Impossible");
            return true;
        } else if (oWins) {
            System.out.println("O wins");
            return true;
        } else if (xWins) {
            System.out.println("X wins");
            return true;
        } else if (!dash && !blank) {
            System.out.println("Draw");
            return true;
        } else {
            System.out.println("Game not finished");
        }
        return false;
    }

    /*
    This method prints output current state of grid for a tic-tac-toe game after user inputs
    */
    private static void printCurrentGameState(char[] userInput) {
        System.out.println("---------");
        System.out.println("| " + userInput[0] + " " + userInput[1] + " " + userInput[2] + " |" );
        System.out.println("| " + userInput[3] + " " + userInput[4] + " " + userInput[5] + " |" );
        System.out.println("| " + userInput[6] + " " + userInput[7] + " " + userInput[8] + " |" );
        System.out.println("---------");
    }
}