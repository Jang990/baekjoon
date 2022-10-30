import java.io.*;
import java.util.*;

public class Main {

	static int[] inputArr = new int[9];
	static int[] outArr = new int[9];
	static boolean[] visited = new boolean[9+1];
	static int Min = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st;
		for (int i = 0; i < 3; i++) {
			st = new StringTokenizer(br.readLine());
			inputArr[i*3] = Integer.valueOf(st.nextToken());
			inputArr[i*3 + 1] = Integer.valueOf(st.nextToken());
			inputArr[i*3 + 2] = Integer.valueOf(st.nextToken());
		}
		
		DFS(0);
		
		System.out.println(Min);
		
		br.close();
	}

	private static void DFS(int idx) {
		if(idx == 9) {
			if(isMagicSquare()) {
				Min = Math.min(Min, calcABS());
			}
			return;
		}
		
		for (int i = 1; i <= 9; i++) {
			if(visited[i]) 
				continue;
			
			visited[i] = true;
			outArr[idx] = i;
			DFS(idx+1);
			visited[i] = false;
		}
	}

	private static int calcABS() {
		int sum = 0;
		for (int i = 0; i < inputArr.length; i++) {
			sum += Math.abs(inputArr[i] - outArr[i]); 
		}
		
		return sum;
	}

	private static boolean isMagicSquare() {
		int sum = outArr[0] + outArr[1] + outArr[2];
		int tSum = 0;
		
		for (int i = 1; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				tSum += outArr[i*3 +j];
			}
			if(tSum != sum) 
				return false;
			
			tSum = 0;
		}
		
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				tSum += outArr[j*3 + i];
			}
			if(tSum != sum) 
				return false;
			
			tSum = 0;
		}
		
		tSum = outArr[0] + outArr[4] + outArr[8];
		if(tSum != sum)
			return false;
		
		tSum = outArr[2] + outArr[4] + outArr[6];
		if(tSum != sum)
			return false;
		
		return true;
	}

}