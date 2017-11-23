package com.hackerrank.medium;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

import org.practice.utils.ArrayUtils;

class Vertex{
	int x, y;
	public Vertex(int x, int y){
		this.x = x;
		this.y = y;
	}
}

class LineOneComparator implements Comparator<Vertex>{
	@Override
	public int compare(Vertex v1, Vertex v2) {
		if(v1.x >= v2.x){
			return 1;
		}else{
			return -1;
		}
	}
}

class LineTwoComparator implements Comparator<Vertex>{
	@Override
	public int compare(Vertex v1, Vertex v2) {
		if(v1.y >= v2.y){
			return 1;
		}else{
			return -1;
		}
	}
}

class LineThreeComparator implements Comparator<Vertex>{
	@Override
	public int compare(Vertex v1, Vertex v2) {
		if(v1.x <= v2.x){
			return 1;
		}else{
			return -1;
		}
	}
}

class LineFourComparator implements Comparator<Vertex>{
	@Override
	public int compare(Vertex v1, Vertex v2) {
		if(v1.y <= v2.y){
			return 1;
		}else{
			return -1;
		}
	}
}

public class ChargingBatteries {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int k = scanner.nextInt();
        scanner.nextLine();
        Vertex[] vertices = new Vertex[m];
        for(int i = 0; i < m; i++){
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            scanner.nextLine();
            Vertex v = new Vertex(x, y);
            vertices[i] = v;
        }
        
//        printVertices(vertices);
        System.out.println("===============");
        vertices  = orderVertices(vertices, n, k);
        printVertices(vertices);
        int[] distances = findDistances(vertices);
        ArrayUtils.printArray(distances);
        System.out.println(findMinimumDistance(distances, k));
        scanner.close();
	}

	private static int findMinimumDistance(int[] distances, int k) {
		int minDistance = 0;
		//take first k-1 distances
		for(int i = 0; i < k-1; i++){
			minDistance += distances[i];
		}
		//remove last and add next;
		int currentDistance = minDistance;
		for(int i = 0, j = k-1; i < k; i++, j++){
			int newDistance = currentDistance - distances[i] + distances[j];
			if(newDistance < minDistance){
				minDistance = newDistance;
			}
			currentDistance = newDistance;
		}
		return minDistance;
	}

	private static int[] findDistances(Vertex[] vertices) {
		int[] distances = new int[vertices.length-1];
		for (int i = 0; i < distances.length; i++) {
			distances[i] = distanceBetweenVertices(vertices[i], vertices[i+1]);
		}
		return distances;
	}

	private static int distanceBetweenVertices(Vertex v1, Vertex v2) {
		return Math.abs(v1.x - v2.x) + Math.abs(v1.y - v2.y);
	}

	private static Vertex[] orderVertices(Vertex[] vertices, int n, int k) {
		//line1 where x > 0 & y = 0 & x <= n
		//line2 where x == n && y > 0 && y <= n
		//line3 where x >= 0 && y == 0 && x <= n
		//line1 where x == 0 && y > 0 && y < n
		//put the vertices into array lists based on the above criteria
		PriorityQueue<Vertex> lineOne = new PriorityQueue<Vertex>(new LineOneComparator());
		PriorityQueue<Vertex> lineTwo = new PriorityQueue<Vertex>(new LineTwoComparator());
		PriorityQueue<Vertex> lineThree = new PriorityQueue<Vertex>(new LineThreeComparator());
		PriorityQueue<Vertex> lineFour = new PriorityQueue<Vertex>(new LineFourComparator());
		for (int i = 0; i < vertices.length; i++) {
			Vertex vertex = vertices[i];
			if(vertex.x >= 0 && vertex.y == 0 && vertex.x <= n){
				lineOne.add(vertex);
				continue;
			}

			if(vertex.x == n && vertex.y > 0 && vertex.y <= n){
				lineTwo.add(vertex);
				continue;
			}

			if(vertex.y == n && vertex.x >= 0 && vertex.x < n){
				lineThree.add(vertex);
				continue;
			}
			
			if(vertex.x == 0 && vertex.y > 0 && vertex.y < n){
				lineFour.add(vertex);
				continue;
			}
		}
		ArrayList<Vertex> orderedVerticesList = new ArrayList<Vertex>();
		orderedVerticesList.addAll(lineOne);
		orderedVerticesList.addAll(lineTwo);
		orderedVerticesList.addAll(lineThree);
		orderedVerticesList.addAll(lineFour);
		
		//add first k-1 vertices to end of the list
		for(int i = 0; i < k-1; i++){
			Vertex v = orderedVerticesList.get(i);
			orderedVerticesList.add(v);
		}
		Vertex[] sample = {};
		return orderedVerticesList.toArray(sample);
	}

	private static void printVertices(Vertex[] vertices) {
		for (int i = 0; i < vertices.length; i++) {
			System.out.println(vertices[i].x + " " + vertices[i].y);
		}
	}

}
