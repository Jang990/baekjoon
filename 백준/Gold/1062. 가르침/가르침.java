import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	
	static int max;
	static boolean[] eraserd;
	static String[] words;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = br.readLine();
		int[] inputNK = Arrays.stream(input.split(" ")).mapToInt(Integer::valueOf).toArray();
		int N = inputNK[0];
		int K = inputNK[1];
		
		if(K < 5) {
			// antic - 5
			System.out.println(max);
			return;
		}
		
		
		max = 0;
		words = new String[N];
		eraserd = new boolean[26];
		eraserd['a' - 97] = true;
		eraserd['n' - 97] = true;
		eraserd['t' - 97] = true;
		eraserd['i' - 97] = true;
		eraserd['c' - 97] = true;
		
		for (int i = 0; i < N; i++) {
			String word = br.readLine();
			words[i] = word.substring(4, word.length()-4);
		}
		br.close();
		
		K -= 5;
		
		Rec(K, 0);
		
		System.out.println(max);
	}
	private static void Rec(int k, int start) {
		if(k == 0) {
			int cnt = 0;
			for (int i = 0; i < words.length; i++) {
				String word = words[i];
				boolean complete = true;
				for (int j = 0; j < word.length(); j++) {
					char c = word.charAt(j);
					if(!eraserd[c-97]) {
						complete = false;
						break;
					}
				}
				
				if(complete)
					cnt++;
			}
			max = Math.max(max, cnt);
			
		}
		
		for (int i = start; i < eraserd.length; i++) {
			if(eraserd[i])
				continue;
			
			eraserd[i] = true;
			Rec(k-1, i+1);
			eraserd[i] = false;
		}
	}

}
