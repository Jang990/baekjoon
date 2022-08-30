import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int caseCnt = Integer.parseInt(sc.nextLine());
		long[][] minMax = new long[caseCnt][2];
		for (int i = 0; i < caseCnt; i++) {
			long numberCnt = Long.parseLong(sc.nextLine());
			String numberStr = sc.nextLine();
			long[] numberArr = Arrays.stream(numberStr.split(" ")).mapToLong(Long::parseLong).toArray();
			minMax[i][0] = numberArr[0];
			minMax[i][1] = numberArr[0];
			for (int j = 1; j < numberArr.length; j++) {
				if(minMax[i][0] > numberArr[j]) minMax[i][0] = numberArr[j];
				if(minMax[i][1] < numberArr[j]) minMax[i][1] = numberArr[j];
			}
		}
		
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < minMax.length; i++) {
			for (int j = 0; j < minMax[i].length; j++) {
				sb.append(minMax[i][j] + " ");
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
		
		sc.close();
	}
}