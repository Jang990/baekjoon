import java.util.Arrays;
import java.util.Comparator;
class Solution {
    public int solution(int[][] data, int col, int row_begin, int row_end) {
        int answer = 0;
        Arrays.sort(data, new Comparator<int[]>() {
        	@Override
        	public int compare(int[] o1, int[] o2) {
        		if(o1[col-1] != o2[col-1])
        			return o1[col-1] - o2[col-1]; // 오름차순
        		if(o1[0] != o2[0])
        			return o2[0] - o1[0]; // 내림차순
        			
        		return 0;
        	}
		});
        
        for (int i = row_begin-1; i < row_end; i++) {
        	int s_i = 0;
			for (int j = 0; j < data[i].length; j++) {
				s_i += data[i][j]%(i+1);
			}
			answer ^= s_i;
		}
        
        return answer;
    }
}