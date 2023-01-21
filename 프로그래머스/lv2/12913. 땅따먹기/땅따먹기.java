import java.util.Arrays;
class Solution {
	static int solution(int[][] land) {
		int max;
		for (int i = 1; i < land.length; i++) {
			for (int j = 0; j < 4; j++) {
				max = 0;
				for (int k = 0; k < 4; k++) {
					if(j == k)
						continue;
					max = Math.max(max, land[i][j] + land[i-1][k]);
				}
				land[i][j] = max;
			}
		}
		
		return Arrays.stream(land[land.length-1]).max().getAsInt();
    }
}