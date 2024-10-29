package com.csc;

import java.util.Random;
import java.util.Scanner;

public class tictactoe {
    Scanner input = new Scanner(System.in);
    private static char player = 'X';
    private static final char[][] board = { { '1', '2', '3' }, { '4', '5', '6' }, { '7', '8', '9' } };

    public static boolean winner() {
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == player && board[i][1] == player && board[i][2] == player ||
                    board[0][i] == player && board[1][i] == player && board[2][i] == player)
                return true;
        }
        return board[0][0] == player && board[1][1] == player && board[2][2] == player
                || board[0][2] == player && board[1][1] == player && board[2][0] == player;
    }

    public static boolean isdraw() {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (board[i][j] != 'X' && board[i][j] != 'O')
                    return false;
        System.out.print("Draw!!\n");
        return true;
    }

    private static void playerswitch() {
        if (player == 'X')
            player = 'O';
        else
            player = 'X';
    }

    private boolean again() {
        System.out.println("Play again?\n(1) Yes\n(2) No");
        int query = input.nextInt();
        while (query != 1 && query != 2) {
            System.out.print("Invalid option.\n\"Play again?\n(1) Yes\n(2) No\n");
            query = input.nextInt();
        }
        if (query == 1) {
            int count = 1;
            for (int i = 0; i < 3; i++)
                for (int j = 0; j < 3; j++) {
                    board[i][j] = (char) ((char) count + '0');
                    count += 1;
                }
            if (player == 'O')
                playerswitch();
            return true;
        }
        return false;
    }

    private static boolean isvalid(String turn) {
        if (!Character.isDigit(turn.charAt(0)))
            return false;
        int move = Integer.parseInt(turn);
        if (move < 1 || move > 9)
            return false;
        int row = (move - 1) / 3;
        int column = (move - 1) % 3;
        return board[row][column] != 'X' && board[row][column] != 'O';
    }

    public static void layout() {
        System.out.println("\n");
        for (int i = 0; i < 3; i++) {
            System.out.println(board[i][0] + " | " + board[i][1] + " | " + board[i][2]);
            if (i < 2)
                System.out.println("--+---+--");
        }
        System.out.println("\n");
    }

    private static void place(String index) {
        int move = Integer.parseInt(index);
        int row = (move - 1) / 3;
        int column = (move - 1) % 3;
        board[row][column] = player;
        layout();
    }

    public String randomgenerator() {
        Random random = new Random();
        int min = 1;
        int limit = 10;
        String number = Integer.toString(random.nextInt((limit - min) + min));
        return number;
    }

    private void turn(int choice) {
        layout();
        while (true) {
            System.out.print(String.format("Player %c, make your move(1-9): ", player));
            String turn = input.next();
            while (!isvalid(turn)) {
                layout();
                System.out.print(String.format("Invalid move.\nPlayer %c, Enter your move(1-9): ", player));
                turn = input.next();
            }
            place(turn);
            if (winner() == true || isdraw() == true) {
                if (winner() == true)
                    System.out.print(String.format("Player %c Wins!!\n", player));
                if (again() == true) {
                    layout();
                    continue;
                } else
                    System.exit(0);
            }
            playerswitch();
            if (choice == 2) {
                String set = randomgenerator();
                while (!isvalid(set))
                    set = randomgenerator();
                System.out.print(String.format("The Computer has moved to %s\n", set));
                place(set);
                if (winner() == true || isdraw()) {
                    if (winner() == true)
                        System.out.print(String.format("Player %c Wins!!\n", player));
                    if (again() == true) {
                        layout();
                        continue;
                    } else
                        System.exit(0);
                }
                playerswitch();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        tictactoe bob = new tictactoe();
        try (Scanner choice = new Scanner(System.in)) {
            int option;
            System.out.print("Welcome to TicTacToe!!!!\n");
            System.out.print("Choose a game mode:\n");
            System.out.print("(1) Human vs. Human\n(2) Human vs Computer\n");
            option = choice.nextInt();
            System.out.print("Let's Play!!");
            bob.turn(option);
        }

    }
}
