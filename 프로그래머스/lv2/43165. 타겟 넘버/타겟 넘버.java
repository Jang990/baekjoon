class Solution {
    int[] numberArr;
	int answer = 0;
	int targetNum;
	public int solution(int[] numbers, int target) {
        numberArr = numbers;
        targetNum = target;
        DFS(0, 0);
        
        return answer;
    }
	private void DFS(int idx, int result) {
		if(idx >= numberArr.length) {
			if(result == targetNum) 
				answer++;
			return;
		}
		
		DFS(idx+1, result + numberArr[idx]);
		DFS(idx+1, result - numberArr[idx]);
	}
}