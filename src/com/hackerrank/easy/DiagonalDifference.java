package com.hackerrank.easy;

import java.util.Scanner;

public class DiagonalDifference {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		scanner.nextLine();
		int[][] matrix = new int[n][n];
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				matrix[i][j] = scanner.nextInt();
			}
			scanner.nextLine();
		}
		scanner.close();
		int primaryDiagonal = 0, secondaryDiagonal = 0;
		for (int i = 0, j = 0, k = 0, l = n - 1; i < n; i++, j++, k++, l--) {
			primaryDiagonal = primaryDiagonal + matrix[i][j];
			secondaryDiagonal = secondaryDiagonal + matrix[k][l];
		}
		System.out.println(Math.abs(primaryDiagonal-secondaryDiagonal));
	}

}
