import java.io.BufferedReader; 
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
	
	static int[] button;
	static int min, channel;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		channel = Integer.parseInt(br.readLine());
		min = Math.abs(channel - 100);
		int length = String.valueOf(channel).length();
		if(98 <= channel && channel <= 102) {
			System.out.println(min);
			return;
		}
		
		int brokenBtnNum = Integer.parseInt(br.readLine());
		List<Integer> brokenBtn;
		if(brokenBtnNum == 0)
			brokenBtn = new ArrayList<>();
		else 
			brokenBtn = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).boxed().collect(Collectors.toList());
		
		button = new int[10-brokenBtnNum];
		int idx = 0;
		for (int i = 0; i < 10; i++) {
			if(brokenBtn.contains(i))
				continue;
			
			button[idx] = i;
			idx++;
		}
		
		Rec(0,0,length+1);
		Rec(0,0, length);
		if(length > 1)
			Rec(0,0,length-1);
		
		System.out.println(min);
		br.close();
	}
	
	private static void Rec(int movedChannel, int depth, int length) {
		if(depth == length) {
			min = Math.min(min, Math.abs(channel - movedChannel)+depth);
			return;
		}
		
		for (int i = 0; i < button.length; i++) {
			Rec(movedChannel * 10 + button[i], depth+1, length);
		}
	}

}
