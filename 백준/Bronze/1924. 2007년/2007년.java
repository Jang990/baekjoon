import java.io.*;
import java.util.StringTokenizer;

public class Main {
	static int[] monthList = {0 , 31,28,31,30,31,30,31,31,30,31,30,31};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		br.close();
		
		int month = Integer.valueOf(st.nextToken());
		int day = Integer.valueOf(st.nextToken());
		
		for (int i = 1; i < month; i++) {
			day += monthList[i];
		}
		
		String output = "";
		switch (day%7) {
		case 1:
			output = "MON";
			break;
		case 2:
			output = "TUE";
			break;
		case 3:
			output = "WED";
			break;
		case 4:
			output = "THU";
			break;
		case 5:
			output = "FRI";
			break;
		case 6:
			output = "SAT";
			break;
		case 0:
			output = "SUN";
			break;
		}
		
		System.out.println(output);
	}

}