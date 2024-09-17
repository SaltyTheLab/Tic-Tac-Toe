package com.csc;

import java.util.Scanner;

public class tictactoe {
    Scanner input = new Scanner(System.in);
    char[][] board = {
            { ' ', ' ', ' ' },
            { ' ', ' ', ' ' },
            { ' ', ' ', ' ' } };

    public void winner(char[][] board) {
        if (board[0][0] == 'X' && board[0][1] == 'X' && board[0][2] == 'X' // check for P1
                || board[1][0] == 'X' && board[1][1] == 'X' && board[1][2] == 'X'
                || board[2][0] == 'X' && board[2][1] == 'X' && board[2][2] == 'X'
                || board[0][0] == 'X' && board[1][0] == 'X' && board[2][0] == 'X'
                || board[0][1] == 'X' && board[1][1] == 'X' && board[2][1] == 'X'
                || board[0][2] == 'X' && board[1][2] == 'X' && board[2][2] == 'X'
                || board[0][0] == 'X' && board[1][1] == 'X' && board[2][2] == 'X'
                || board[0][2] == 'X' && board[1][1] == 'X' && board[2][0] == 'X') {
            System.out.print("Player 1 wins!!");
            System.exit(0);
        }
        if (board[0][0] == 'O' && board[0][1] == 'O' && board[0][2] == 'O'// check for P2
                || board[1][0] == 'O' && board[1][1] == 'O' && board[1][2] == 'O'
                || board[2][0] == 'O' && board[2][1] == 'O' && board[2][2] == 'O'
                || board[0][0] == 'O' && board[1][0] == 'O' && board[2][0] == 'O'
                || board[0][1] == 'O' && board[1][1] == 'O' && board[2][1] == 'O'
                || board[0][2] == 'O' && board[1][2] == 'O' && board[2][2] == 'O'
                || board[0][0] == 'O' && board[1][1] == 'O' && board[2][2] == 'O'
                || board[0][2] == 'O' && board[1][1] == 'O' && board[2][0] == 'O') {
            System.out.print("Player 2 wins!!");
            System.exit(0);
        }
    }

    public static boolean isempty(char[][] board, int turn) {
        return switch (turn) {
            case 1 -> board[0][0] == ' ';
            case 2 -> board[0][1] == ' ';
            case 3 -> board[0][2] == ' ';
            case 4 -> board[1][0] == ' ';
            case 5 -> board[1][1] == ' ';
            case 6 -> board[1][2] == ' ';
            case 7 -> board[2][0] == ' ';
            case 8 -> board[2][1] == ' ';
            case 9 -> board[2][2] == ' ';
            default -> false;
        };
    }

    public void layout() {
        System.out.println(board[0][0] + " | " + board[0][1] + " | " + board[0][2]);
        System.out.println("- + - + -");
        System.out.println(board[1][0] + " | " + board[1][1] + " | " + board[1][2]);
        System.out.println("- + - + -");
        System.out.println(board[2][0] + " | " + board[2][1] + " | " + board[2][2]);
    }

    public void turn() {
        int turn;
        int player = 0;
        int count = 0;
        while (true) {
            if (player == 0)// change the prompt depending on players turn
                System.out.print(" Player 1 - enter your move(0-9):\n");
            else
                System.out.print(" Player 2 - enter your move(0-9):\n");
            turn = input.nextInt();// input validation and space check
            while (turn < 1 || turn > 9 || isempty(board, turn) == false) {
                System.out.println("Invalid move. Enter your move: ");
                turn = input.nextInt();
            }
            if (player == 0) {
                player = 1;
                switch (turn) {
                    case 1 -> board[0][0] = 'X';
                    case 2 -> board[0][1] = 'X';
                    case 3 -> board[0][2] = 'X';
                    case 4 -> board[1][0] = 'X';
                    case 5 -> board[1][1] = 'X';
                    case 6 -> board[1][2] = 'X';
                    case 7 -> board[2][0] = 'X';
                    case 8 -> board[2][1] = 'X';
                    case 9 -> board[2][2] = 'X';
                }
            } else {
                player = 0;
                switch (turn) {
                    case 1 -> board[0][0] = 'O';
                    case 2 -> board[0][1] = 'O';
                    case 3 -> board[0][2] = 'O';
                    case 4 -> board[1][0] = 'O';
                    case 5 -> board[1][1] = 'O';
                    case 6 -> board[1][2] = 'O';
                    case 7 -> board[2][0] = 'O';
                    case 8 -> board[2][1] = 'O';
                    case 9 -> board[2][2] = 'O';
                }
            }
            layout();
            winner(board);
            count++;
            if (count == 9) {
                System.out.print("draw!");
                System.exit(0);
            }
        }
    }

    public static void main(String[] args) {
        tictactoe bob = new tictactoe();
        System.out.print("Welcome to TicTacToe!!!!\n");
        bob.layout();
        bob.turn();
    }
}