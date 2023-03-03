import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	
	static int[] building;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.valueOf(br.readLine());
		building = Arrays.stream(br.readLine().split(" "))
				.mapToInt(Integer::parseInt).toArray();
		br.close();
		
		int maxView = 0;
		for (int i = 0; i < t; i++) {
			maxView = Math.max(maxView, checkView(i));
		}
		
		System.out.println(maxView);
	}
	
	private static int checkView(int buildingNumber) {
		int view = 0;
		double minSlope = Double.MAX_VALUE;
		// 역방향
		for (int i = buildingNumber-1; i >= 0; i--) {
			double nowSlope = (double)(building[buildingNumber] - building[i])/(buildingNumber-i);
			if(minSlope <= nowSlope)
				continue;
			
			minSlope = nowSlope;
			view++;
		}
		
		minSlope = Integer.MAX_VALUE;
		// 정방향
		for (int i = buildingNumber+1; i < building.length; i++) {
			double nowSlope = (double)(building[buildingNumber] - building[i])/(i-buildingNumber);
			if(minSlope <= nowSlope)
				continue;
			
			minSlope = nowSlope;
			view++;
		}
		
		return view;
	}

}
