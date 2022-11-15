import java.util.*;
import java.io.*;

public class Main {

	static int[] answers;
	private static int cnt;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		cnt = 0;
		answers = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		
		Rec(0, 0, new int[10]);

		System.out.println(cnt);
		
		br.close();
	}

	private static void Rec(int idx, int correct, int[] myAnswers) {
		if(10 - idx + correct < 5)
			return;
		
		if(idx == 10) {
			if(correct >= 5) {
				cnt++;
			}
			return;
		}

		int checkNum = 0;
		if(idx >= 2 && myAnswers[idx-1] == myAnswers[idx-2]) {
			checkNum = myAnswers[idx-1];
		}
		
		for (int i = 1; i <= 5; i++) {
			if(checkNum == i)
				continue;
			
			myAnswers[idx] = i;
			if(answers[idx] == i) {
				Rec(idx+1, correct+1, myAnswers);
				continue;
			}
			else {
				Rec(idx+1, correct, myAnswers);
			}
			myAnswers[idx] = 0;
			
		}
		
	}

}