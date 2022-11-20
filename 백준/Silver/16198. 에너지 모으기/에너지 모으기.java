import java.util.*;
import java.util.stream.Collectors;
import java.io.*;

public class Main {

	static int N;
	static List<Integer> weight;
	static int Max;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.valueOf(br.readLine());
		weight = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).boxed().collect(Collectors.toList());
		Max = Integer.MIN_VALUE;
		
		Rec(0, 0);
		System.out.println(Max);
		br.close();
	}

	private static void Rec(int idx, int energy) {
		if(idx == N-2) {
			Max = Math.max(Max, energy);
			return;
		}
		
		int ball, sum;
		for (int i = 1; i < weight.size()-1; i++) {
			ball = weight.get(i);
			sum = weight.get(i-1) * weight.get(i+1);
			weight.remove(i);
			Rec(idx+1, energy + sum);
			weight.add(i, ball);
		}
	}

}