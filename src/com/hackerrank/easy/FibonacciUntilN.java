package com.hackerrank.easy;

public class FibonacciUntilN {

	public static void main(String[] args) {
		int a = 0, b = 1, n = 5;
		System.out.print(a + ", " + b);
		for(int i = 2; i < n; i++){
			int c = a + b;
			a = b;
			b = c;
			System.out.print(", " + b);
		}
	}

}
