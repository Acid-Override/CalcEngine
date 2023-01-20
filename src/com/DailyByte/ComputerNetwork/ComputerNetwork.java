package com.DailyByte.ComputerNetwork;

/**
 * You are given a two-dimensional matrix containing only ones and zeroes representing a computer network.
 * Every one in the matrix represents a server and every zero represents an empty space.
 * Two servers within the network can communicate if they are either in the same row or the same column.
 * Return the total number of servers that can communicate with at least one other server.
 *
 * Ex: Given the following matrix…
 *
 * matrix = [
 *   [1, 0],
 *   [1, 0],
 * ], return 2 (both servers are in the same column and can therefore communicate with one another).
 * Ex: Given the following matrix…
 *
 * matrix = [
 *   [1, 0],
 *   [0, 1],
 * ], return 0.
 */
public class ComputerNetwork {

    public Integer computerNetwork(int[][] matrix) {
        int rows[] = new int[matrix.length];
        int cols[] = new int[matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 1) {
                    rows[i]++;
                    cols[j]++;
                }
            }
        }

        int servers = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 1 && (rows[i] > 1 || cols[j] > 1)) {
                    servers++;
                }
            }
        }

        return servers;
    }
}
