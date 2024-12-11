package com.csc;

import java.util.Random;
import java.util.Scanner;

public class tictactoe {
    Scanner input = new Scanner(System.in);
    private static char player = ' ';
    private static char P1 = ' ';
    private static char P2 = ' ';
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
                if (board[i][j] != P1 && board[i][j] != P2)
                    return false;
        System.out.print("Draw!!\n");
        return true;
    }

    private static void playerswitch() {
        if (player == P2)
            player = P1;
        else
            player = P2;
    }

    private boolean again() {
        System.out.println("Play again?\n(y): Yes\n(n): No");
        String query = input.next();
        while (!"y".equals(query) && !"n".equals(query)) {
            System.out.print("Invalid option.\n\"Play again?\n(1): Yes\n(2): No\n");
            query = input.next();
        }
        if ("y".equals(query)) {
            int count = 1;
            for (int i = 0; i < 3; i++)
                for (int j = 0; j < 3; j++) {
                    board[i][j] = (char) ((char) count + '0');
                    count += 1;
                }
            if (player == P2)
                playerswitch();
            layout();
            return true;
        }
        return false;
    }

    private static boolean isvalid(String turn) {
        if (!Character.isDigit(turn.charAt(0)) || turn.length() != 1)
            return false;
        int move = Integer.parseInt(turn);
        if (move < 1 || move > 9)
            return false;
        int row = (move - 1) / 3;
        int column = (move - 1) % 3;
        return board[row][column] != P1 && board[row][column] != P2;
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

    public static String randomgenerator() {
        Random random = new Random();
        int min = 1;
        int limit = 10;
        String number = Integer.toString(random.nextInt((limit - min) + min));
        while (!isvalid(number))
            number = Integer.toString(random.nextInt((limit - min) + min));
        return number;
    }

    public void computer() {
        String set = randomgenerator();
        System.out.print(String.format("The Computer has moved to %s\n", set));
        place(set);
        if (winner() || isdraw()) {
            if (winner())
                System.out.print(String.format("Player %c Wins!!\n", player));
            if (!again())
                System.exit(0);
        } else
            playerswitch();
    }

    private void turn(int choice) {
        layout();
        tictactoe.player = P1;
        while (true) {
            System.out.print(String.format("Player %c, make your move(1-9): ", player));
            String turn = input.next();
            while (!isvalid(turn)) {
                layout();
                System.out.print(String.format("Invalid move.\nPlayer %c, Enter your move(1-9): ", player));
                turn = input.next();
            }
            place(turn);
            if (winner() || isdraw()) {
                if (winner())
                    System.out.print(String.format("Player %c Wins!!\n", player));
                if (again())
                    continue;
                else
                    System.exit(0);
            }
            playerswitch();
            if (choice == 2)
                computer();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        tictactoe bob = new tictactoe();
        try (Scanner choice = new Scanner(System.in)) {
            int option;
            String C1;
            String C2;
            System.out.print("enter your custom markers for P1 and P2('Default' for X and O): ");
            C1 = choice.next();
            C2 = choice.next();
            if (C1.contains("Default") || C2.contains("Default")) {
                C1 = "X";
                C2 = "O";
            }
            while (C1.length() != 1 || C1.contains(" ") || C2.length() != 1
                    || C2.contains(" ") || C1.equals(C2)) {
                System.out.print("Invalid Markers. Enter your markers: ");
                C1 = choice.next();
                C2 = choice.next();
            }
            System.out.print(
                    "Welcome to TicTacToe!!!!\nChoose a game mode:\n(1) Human vs. Human\n(2) Human vs Computer\n");
            option = choice.nextInt();
            System.out.print("Let's Play!!");
            tictactoe.P1 = C1.charAt(0);
            tictactoe.P2 = C2.charAt(0);
            bob.turn(option);
        }
    }
}