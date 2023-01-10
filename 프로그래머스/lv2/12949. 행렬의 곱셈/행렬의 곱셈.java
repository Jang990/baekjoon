class Solution {
    public int[][] solution(int[][] arr1, int[][] arr2) {
        int[][] answer = new int[arr1.length][arr2[0].length];
        int result;
        for (int i = 0; i < answer.length; i++) {
			for (int j = 0; j < answer[i].length; j++) {
				result = 0;
				for (int k = 0; k < arr2.length; k++) {
					result += arr1[i][k] * arr2[k][j];
				}
				
				answer[i][j] = result;  
			}
		}
        
        return answer;
    }
}