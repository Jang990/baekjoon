import java.util.*;
import java.io.*;

public class Main {
	static int len, firstNum, Min = Integer.MAX_VALUE;
	static int[] arr, outArr;
	static String inputStr;
	static boolean[] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		inputStr = br.readLine();
		len = inputStr.length();
		arr = new int[len];
		outArr = new int[len];
		visited = new boolean[len];
		
		firstNum = inputStr.charAt(0) - '0';
		
		for (int i = 0; i < len; i++) {
			arr[i] = inputStr.charAt(i) - '0';
		}
		
		arr = Arrays.stream(arr).sorted().toArray();
		
		Rec(0);
		
		if(Min == Integer.MAX_VALUE) 
			System.out.println(0);
		else 
			System.out.println(Min);
		
		
		br.close();
	}

	private static void Rec(int idx) {
		if(idx == len) {
			String str = "";
			for (int i = 0; i < outArr.length; i++) {
				str += outArr[i];
			}
			if(!inputStr.equals(str) && Integer.valueOf(str) > Integer.valueOf(inputStr))
				Min = Math.min(Min, Integer.valueOf(str));
			return;
		}
		
		for (int i = 0; i < arr.length; i++) {
			if(idx == 0 && arr[i] < firstNum)
				continue;
			
			if(visited[i]) 
				continue;
			
			outArr[idx] = arr[i];
			visited[i] = true;
			Rec(idx+1);
			visited[i] = false;
		}
		
	}

}