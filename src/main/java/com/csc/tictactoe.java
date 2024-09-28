package com.csc;

import java.util.Scanner;

public class tictactoe {
    Scanner input = new Scanner(System.in);
    private static char player = 'X';
    private static final char[][] board = { { ' ', ' ', ' ' }, { ' ', ' ', ' ' }, { ' ', ' ', ' ' } };

    public static boolean winner() {
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == player && board[i][1] == player && board[i][2] == player ||
                    board[i][0] == player && board[i][1] == player && board[i][2] == player)
                return true;
        }
        return board[0][0] == player && board[1][1] == player && board[2][2] == player
                || board[0][2] == player && board[1][1] == player && board[2][0] == player;
    }

    private static boolean isdraw() {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (board[i][j] == ' ')
                    return false;
        System.out.print("Draw!!");
        return true;
    }

    private static void playerswitch() {
        if (player == 'X')
            player = 'O';
        else
            player = 'X';
    }

    private static boolean again(String input) {
        if ("y".equals(input)) {
            for (int i = 0; i < 3; i++)
                for (int j = 0; j < 3; j++)
                    board[i][j] = ' ';
            if (player == 'O')
                playerswitch();
            return true;
        }
        return false;
    }

    private static boolean isvalid(String turn) {
        if (turn.length() != 1 || !Character.isDigit(turn.charAt(0)))
            return false;
        int move = Integer.parseInt(turn);
        if (move < 1 || move > 9)
            return false;
        int row = (move - 1) / 3;
        int column = (move - 1) % 3;
        return board[row][column] == ' ';
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

    private void turn() {
        while (true) {
            System.out.print(String.format("Player %c, make your move(0-9): ", player));
            String turn = input.next();
            while (!isvalid(turn)) {
                layout();
                System.out.print(String.format("Invalid move player %c.\n Enter your move :", player));
                turn = input.next();
            }
            place(turn);
            if (winner() == true || isdraw()) {
                if (winner() == true)
                    System.out.print(String.format("PLayer %c Wins!!\n", player));
                System.out.println("Play again?");
                String query = input.next();
                if (again(query) == true)
                    continue;
                else
                    System.exit(0);
            }
            playerswitch();
        }
    }

    public static void main(String[] args) {
        tictactoe bob = new tictactoe();
        System.out.print("Welcome to TicTacToe!!!!\n");
        bob.turn();
    }
}