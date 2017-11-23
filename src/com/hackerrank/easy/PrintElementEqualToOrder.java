package com.hackerrank.easy;

public class PrintElementEqualToOrder {

	public static void main(String[] args) {
		int[] ar =  {-1, 2, 3, 5, 4, 6};
		for (int i = 0; i < ar.length; i++) {
			if(ar[i] == i+1){
				System.out.println(ar[i]);
			}
		}
	}

}
